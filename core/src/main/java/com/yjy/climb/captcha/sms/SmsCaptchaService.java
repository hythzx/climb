package com.yjy.climb.captcha.sms;

import java.util.HashMap;

import javax.transaction.NotSupportedException;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.RandomUtil;
import com.yjy.climb.captcha.AbstractCaptchaServiceBase;
import com.yjy.climb.captcha.CaptchaInfoBase;
import com.yjy.climb.captcha.ICaptchaInfo;
import com.yjy.climb.captcha.ICaptchaParam;
import com.yjy.climb.captcha.ICaptchaPersistence;
import com.yjy.climb.captcha.ICaptchaService;
import com.yjy.climb.message.ISmsMessageService;
import com.yjy.climb.message.SmsMessageSenderParam;
import org.slf4j.Logger;

import org.springframework.stereotype.Service;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * 发送短信验证码接口
 */
@Service("smsCaptcha")
public class SmsCaptchaService extends AbstractCaptchaServiceBase implements ICaptchaService {

	private final Logger log = getLogger(SmsCaptchaService.class);

	private final ISmsMessageService iSmsMessageService;

	private final ICaptchaPersistence iCaptchaPersistence;

	public SmsCaptchaService(ISmsMessageService iSmsMessageService, ICaptchaPersistence iCaptchaPersistence) {
		super(iCaptchaPersistence);
		this.iSmsMessageService = iSmsMessageService;
		this.iCaptchaPersistence = iCaptchaPersistence;
	}

	/**
	 * 使用默认参数创建验证码
	 *
	 * @return 验证码内容
	 */
	@Override
	public ICaptchaInfo create() throws NotSupportedException {
		throw new NotSupportedException("短信验证码不支持无参数模式");
	}

	/**
	 * 创建验证码
	 *
	 * @param captchaParam 验证码生成参数
	 * @return 验证码内容
	 */
	@Override
	public ICaptchaInfo create(ICaptchaParam captchaParam) {
		assert captchaParam instanceof SmsCaptchaParam;
		log.debug("request to send sms message, request param is :[{}]", captchaParam);
		SmsCaptchaParam smsCaptchaParam = (SmsCaptchaParam) captchaParam;
		// 短信验证码采用6位数数字的形式
		String code = String.valueOf(RandomUtil.randomInt(100000, 999999));
		SmsMessageSenderParam messageSenderParam = SmsMessageSenderParam.builder()
				.mobile(smsCaptchaParam.getMobile())
				.code(SmsCaptchaParam.code)
				.params(new HashMap<>() {{
					put("code", code);
				}}).build();
		iSmsMessageService.sendMessage(messageSenderParam);
		// 使用 "手机号-业务编码"的base64编码 作为缓存的key
		String key = Base64.encode(String.format("%s-%s", smsCaptchaParam.getMobile(), SmsCaptchaParam.code));
		iCaptchaPersistence.save(
				key,
				code,
				smsCaptchaParam.getTimeout(),
				smsCaptchaParam.getTimeunit()
		);
		return CaptchaInfoBase.builder().key(key).code(code).build();
	}
}
