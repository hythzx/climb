package com.yjy.climb.captcha;

import javax.transaction.NotSupportedException;

import com.yjy.climb.exception.captcha.CaptchaCreateException;
import com.yjy.climb.exception.captcha.CaptchaExpireException;

/**
 * 验证码接口
 */
public interface ICaptchaService<R extends ICaptchaRequest, S extends ICaptchaResponse> {


	/**
	 * 使用默认参数创建验证码
	 * @return 验证码内容
	 */
	S create() throws NotSupportedException, CaptchaCreateException;

	/**
	 * 创建验证码
	 * @param captchaRequest 验证码生成参数
	 * @return 验证码内容
	 */
	S create(R captchaRequest) throws CaptchaCreateException;

	/**
	 * 校验校验码
	 * @param code 用户输入的验证码内容
	 * @param key 该验证码的key
	 * @return 是否输入成功
	 */
	Boolean verify(String code, String key) throws CaptchaExpireException;

}
