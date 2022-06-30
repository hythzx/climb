package com.yjy.climb.captcha.sms;

import java.io.Serial;
import java.io.Serializable;

import com.yjy.climb.captcha.ICaptchaRequest;
import lombok.Builder;
import lombok.Data;

/**
 * 短信验证码参数
 */
@Data
@Builder
public class SmsCaptchaRequest implements ICaptchaRequest, Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	/**
	 * 默认短信验证码的模版业务编码
	 */
	public static final String code = "verify";

	private String mobile;

	/**
	 * 业务编码，如登录、注册、找回密码、实名认证，不同业务类型的验证码无法混用
	 */
	private String businessKey;

}
