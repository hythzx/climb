package com.yjy.climb.captcha.sms;

import com.yjy.climb.captcha.ICaptchaParam;
import lombok.Builder;
import lombok.Data;

/**
 * 短信验证码参数
 */
@Data
@Builder
public class SmsCaptchaParam implements ICaptchaParam {

	/**
	 * 默认短信验证码的模版业务编码
	 */
	public static final String code = "verify";

	private String mobile;

}
