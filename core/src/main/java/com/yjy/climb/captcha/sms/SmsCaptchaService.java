package com.yjy.climb.captcha.sms;

import java.util.HashMap;

import javax.transaction.NotSupportedException;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.util.RandomUtil;
import com.yjy.climb.captcha.AbstractCaptchaServiceBase;
import com.yjy.climb.captcha.ICaptchaPersistence;
import com.yjy.climb.exception.ErrorConstants.Message.Sms;
import com.yjy.climb.exception.captcha.CaptchaCreateException;
import com.yjy.climb.message.IMessageResponse;
import com.yjy.climb.message.sms.ISmsMessageService;
import com.yjy.climb.message.sms.SmsMessageRequest;
import org.slf4j.Logger;

import org.springframework.stereotype.Service;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * 发送短信验证码接口
 */
@Service("smsCaptcha")
public class SmsCaptchaService extends AbstractCaptchaServiceBase<SmsCaptchaRequest, SmsCaptchaResponse>{

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
	public SmsCaptchaResponse create() throws NotSupportedException {
		throw new NotSupportedException("短信验证码不支持无参数模式");
	}

	/**
	 * 创建验证码
	 *
	 * @param smsCaptchaParam 验证码生成参数
	 * @return 验证码内容
	 */
	@Override
	public SmsCaptchaResponse create(SmsCaptchaRequest smsCaptchaParam) throws CaptchaCreateException {
		log.debug("request to send sms message, request param is :[{}]", smsCaptchaParam);
		// 短信验证码采用6位数数字的形式
		String code = String.valueOf(RandomUtil.randomInt(100000, 999999));
		SmsMessageRequest messageSenderParam = SmsMessageRequest.builder()
				.mobile(smsCaptchaParam.getMobile())
				.code(SmsCaptchaRequest.code)
				.params(new HashMap<>() {{
					put("code", code);
				}}).build();
		IMessageResponse messageResponse =  iSmsMessageService.sendMessage(messageSenderParam);
		if (messageResponse.getSuccess()) {
			// 使用 "手机号-业务编码"的base64编码 作为缓存的key
			String key = Base64.encode(String.format("%s-%s", smsCaptchaParam.getMobile(), SmsCaptchaRequest.code));
			iCaptchaPersistence.save(
					key,
					code,
					smsCaptchaParam.getTimeout(),
					smsCaptchaParam.getTimeunit()
			);
			return new SmsCaptchaResponse(code, key);
		}else {
			throw new CaptchaCreateException(Sms.sendError.getErrorMsg(), Sms.sendError.getErrorCode());
		}
	}
}
