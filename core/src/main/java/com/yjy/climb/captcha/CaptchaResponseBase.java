package com.yjy.climb.captcha;


import java.io.Serial;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;

@Builder
public class CaptchaResponseBase implements ICaptchaResponse, Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	protected String code;

	protected String key;

	public CaptchaResponseBase() {
	}

	public CaptchaResponseBase(String code, String key) {
		this.code = code;
		this.key = key;
	}

	/**
	 * 验证码内容
	 *
	 * @return 验证码内容
	 */
	@Override
	public String getCode() {
		return code;
	}

	/**
	 * 验证码的key
	 *
	 * @return 验证码的key
	 */
	@Override
	public String getKey() {
		return key;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
