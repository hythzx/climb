package com.yjy.climb.captcha;

/**
 * 验证码信息接口
 */
public interface ICaptchaInfo {

	/**
	 * 验证码内容
	 * @return 验证码内容
	 */
	String getCode();

	/**
	 * 验证码的key
	 * @return 验证码的key
	 */
	String getKey();

}
