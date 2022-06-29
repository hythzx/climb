package com.yjy.climb.captcha.hutool;

import java.util.UUID;

import cn.hutool.captcha.AbstractCaptcha;
import cn.hutool.captcha.CaptchaUtil;
import com.yjy.climb.captcha.AbstractCaptchaServiceBase;
import com.yjy.climb.captcha.ICaptchaResponse;
import com.yjy.climb.captcha.ICaptchaService;
import com.yjy.climb.captcha.ICaptchaRequest;
import com.yjy.climb.captcha.ICaptchaPersistence;
import org.slf4j.Logger;

import org.springframework.stereotype.Service;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * 使用hutool的验证码生成工具生成验证码 <a href="https://www.hutool.cn/docs/#/captcha/%E6%A6%82%E8%BF%B0">hutool captcha</a>
 */
@Service("hutoolImageCaptcha")
public class HutoolCaptchaServiceProvider extends AbstractCaptchaServiceBase implements ICaptchaService {

	private final Logger log = getLogger(HutoolCaptchaServiceProvider.class);

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

	public HutoolCaptchaServiceProvider(ICaptchaPersistence captchaPersistence) {
		super(captchaPersistence);
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
	public ICaptchaResponse create() {
		ICaptchaRequest imageCaptchaInfo = new HutoolCaptchaRequest();
		return create(imageCaptchaInfo);
	}

	/**
	 * 创建验证码
	 *
	 * @param captchaParam 验证码生成参数
	 * @return 验证码内容
	 */
	@Override
	public ICaptchaResponse create(ICaptchaRequest captchaParam) {
		assert captchaParam instanceof HutoolCaptchaRequest;
		log.debug("request to create image captcha by hutool, captcha params is : [{}]", captchaParam);
		HutoolCaptchaRequest hutoolCaptchaParam = (HutoolCaptchaRequest) captchaParam;
		AbstractCaptcha captcha = switch (captchaStrategy) {
			case DEFAULT_CAPTCHA_STRATEGY ->
					CaptchaUtil.createCircleCaptcha(hutoolCaptchaParam.getWidth(), hutoolCaptchaParam.getHeight(), hutoolCaptchaParam.getCodeCount(), hutoolCaptchaParam.getCircleCount());
			case CAPTCHA_STRATEGY_LINE ->
					CaptchaUtil.createLineCaptcha(hutoolCaptchaParam.getWidth(), hutoolCaptchaParam.getHeight(), hutoolCaptchaParam.getCodeCount(), hutoolCaptchaParam.getCircleCount());
			case CAPTCHA_STRATEGY_SHEAR ->
					CaptchaUtil.createShearCaptcha(hutoolCaptchaParam.getWidth(), hutoolCaptchaParam.getHeight(), hutoolCaptchaParam.getCodeCount(), hutoolCaptchaParam.getThickness());
			default ->
					CaptchaUtil.createGifCaptcha(hutoolCaptchaParam.getWidth(), hutoolCaptchaParam.getHeight(), hutoolCaptchaParam.getCodeCount());
		};
		String code = captcha.getCode();
		String key = UUID.randomUUID().toString().replaceAll("-", "");
		captchaPersistence.save(key, code, hutoolCaptchaParam.getTimeout(), hutoolCaptchaParam.getTimeunit());
		return new ImageCaptchaResponse(key, code, captcha.getImageBase64Data());
	}
}
