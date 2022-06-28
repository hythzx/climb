package com.yjy.climb.captcha;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;

@Builder
public class CaptchaInfoBase implements ICaptchaInfo{

	@JsonIgnore
	protected String code;

	protected String key;

	public CaptchaInfoBase() {
	}

	public CaptchaInfoBase(String code, String key) {
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
