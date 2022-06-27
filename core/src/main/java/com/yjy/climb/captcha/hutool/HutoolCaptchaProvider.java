package com.yjy.climb.captcha.hutool;

import java.util.Optional;
import java.util.UUID;

import cn.hutool.captcha.AbstractCaptcha;
import cn.hutool.captcha.CaptchaUtil;
import com.yjy.climb.captcha.ICaptcha;
import com.yjy.climb.captcha.ICaptchaInfo;
import com.yjy.climb.captcha.ICaptchaParam;
import com.yjy.climb.captcha.ICaptchaPersistence;
import com.yjy.climb.captcha.ImageCaptchaInfo;
import com.yjy.climb.exception.CaptchaExpireException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

import org.springframework.stereotype.Service;

import static org.slf4j.LoggerFactory.getLogger;

/**
 *
 */
@Service("hutoolCaptchaProvider")
public class HutoolCaptchaProvider implements ICaptcha {

	private final Logger log = getLogger(HutoolCaptchaProvider.class);

	/**
	 * 默认使用圆环作为验证码干扰策略,分circle/line/shear/gif
	 */
	private static final String DEFAULT_CAPTCHA_STRATEGY = "circle";

	/**
	 * 使用横线作为验证码干扰策略
	 */
	private static final String CAPTCHA_STRATEGY_LINE = "line";

	/**
	 * 文字扭曲
	 */
	private static final String CAPTCHA_STRATEGY_SHEAR = "shear";

	/**
	 * 动态图片
	 */
	private static final String CAPTCHA_STRATEGY_GIF = "gif";

	/**
	 * 验证码干扰策略
	 */
	private String captchaStrategy = DEFAULT_CAPTCHA_STRATEGY;

	private final ICaptchaPersistence captchaPersistence;

	public HutoolCaptchaProvider(ICaptchaPersistence captchaPersistence) {
		// 如果需要指定不同的干扰策略，只需要在应用启动的时间添加系统变量即可，参照以下代码:
		/*
		 * System.setProperty("captcha_strategy", "gif"); //gif图片
		 * System.setProperty("captcha_strategy", "line");  // 横线干扰
		 * System.setProperty("captcha_strategy", "circle"); // 圆圈，默认策略无需手动指定
		 * System.setProperty("captcha_strategy", "shear");  // 文字扭曲
		 */
		this.captchaPersistence = captchaPersistence;
		String captchaStrategyProperty = System.getProperty("captcha_strategy");
		if (captchaStrategyProperty != null && (captchaStrategyProperty.equals(DEFAULT_CAPTCHA_STRATEGY) || captchaStrategyProperty.equals(CAPTCHA_STRATEGY_LINE) || captchaStrategyProperty.equals(CAPTCHA_STRATEGY_SHEAR) || captchaStrategyProperty.equals(CAPTCHA_STRATEGY_GIF))) {
			captchaStrategy = captchaStrategyProperty;
		}
	}


	/**
	 * 使用默认参数创建验证码
	 *
	 * @return 验证码内容
	 */
	@Override
	public ICaptchaInfo create() {
		ICaptchaParam imageCaptchaInfo = new HutoolCaptchaParam();
		return create(imageCaptchaInfo);
	}

	/**
	 * 创建验证码
	 *
	 * @param captchaParam 验证码生成参数
	 * @return 验证码内容
	 */
	@Override
	public ICaptchaInfo create(ICaptchaParam captchaParam) {
		assert captchaParam instanceof HutoolCaptchaParam;
		log.debug("request to create image captcha by hutool, captcha params is : [{}]", captchaParam);
		HutoolCaptchaParam hutoolCaptchaParam = (HutoolCaptchaParam) captchaParam;
		AbstractCaptcha captcha;
		if (captchaStrategy.equals(DEFAULT_CAPTCHA_STRATEGY)) {
			captcha = CaptchaUtil.createCircleCaptcha(hutoolCaptchaParam.getWidth(), hutoolCaptchaParam.getHeight(), hutoolCaptchaParam.getCodeCount(), hutoolCaptchaParam.getCircleCount());
		}else if (captchaStrategy.equals(CAPTCHA_STRATEGY_LINE)){
			captcha = CaptchaUtil.createLineCaptcha(hutoolCaptchaParam.getWidth(), hutoolCaptchaParam.getHeight(), hutoolCaptchaParam.getCodeCount(), hutoolCaptchaParam.getCircleCount());
		}else if (captchaStrategy.equals(CAPTCHA_STRATEGY_SHEAR)){
			captcha = CaptchaUtil.createShearCaptcha(hutoolCaptchaParam.getWidth(), hutoolCaptchaParam.getHeight(), hutoolCaptchaParam.getCodeCount(), hutoolCaptchaParam.getThickness());
		}else {
			captcha = CaptchaUtil.createGifCaptcha(hutoolCaptchaParam.getWidth(), hutoolCaptchaParam.getHeight(), hutoolCaptchaParam.getCodeCount());
		}
		String code = captcha.getCode();
		String key = UUID.randomUUID().toString().replaceAll("-", "");
		captchaPersistence.save(key, code, hutoolCaptchaParam.getTimeout(), hutoolCaptchaParam.getTimeunit());
		return new ImageCaptchaInfo(key, code, captcha.getImageBase64Data());
	}

	/**
	 * 校验校验码
	 *
	 * @param code 用户输入的验证码内容
	 * @param key 该验证码的key
	 * @return 是否输入成功
	 */
	@Override
	public Boolean verify(String code, String key) throws CaptchaExpireException {
		assert StringUtils.isNotBlank(code) && StringUtils.isNotBlank(key);
		log.debug("request to verify captcha, key :[{}], code: [{}]", key, code);
		Optional<String> optional = captchaPersistence.getCode(key);
		if (optional.isPresent()){
			String realCode = optional.get();
			return code.equals(realCode);
		}
		throw new CaptchaExpireException();
	}
}
