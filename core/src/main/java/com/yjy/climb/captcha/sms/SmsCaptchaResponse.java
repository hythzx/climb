package com.yjy.climb.captcha.sms;

import java.io.Serial;
import java.io.Serializable;

import com.yjy.climb.captcha.CaptchaResponseBase;

/**
 * 短信验证码返回信息
 */
public class SmsCaptchaResponse extends CaptchaResponseBase  implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	public SmsCaptchaResponse() {
		super();
	}

	public SmsCaptchaResponse(String code, String key) {
		super(code, key);
	}
}
