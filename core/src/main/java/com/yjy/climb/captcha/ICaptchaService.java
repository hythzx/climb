package com.yjy.climb.captcha;

import javax.transaction.NotSupportedException;

import com.yjy.climb.exception.CaptchaExpireException;

/**
 * 验证码接口
 */
public interface ICaptchaService {


	/**
	 * 使用默认参数创建验证码
	 * @return 验证码内容
	 */
	ICaptchaInfo create() throws NotSupportedException;

	/**
	 * 创建验证码
	 * @param captchaParam 验证码生成参数
	 * @return 验证码内容
	 */
	ICaptchaInfo create(ICaptchaParam captchaParam);

	/**
	 * 校验校验码
	 * @param code 用户输入的验证码内容
	 * @param key 该验证码的key
	 * @return 是否输入成功
	 */
	Boolean verify(String code, String key) throws CaptchaExpireException;

}