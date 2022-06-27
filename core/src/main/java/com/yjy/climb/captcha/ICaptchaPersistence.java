package com.yjy.climb.captcha;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * 验证码持久化对象
 */
public interface ICaptchaPersistence {

	/**
	 * 保存验证码信息
	 * @param key 验证码key
	 * @param code 验证码内容
	 * @param timeout 验证码过期时间，默认5分钟
	 * @param timeUnit 验证码过期时间单位，默认分钟
	 */
	void save(String key, String code, Long timeout, TimeUnit timeUnit);

	/**
	 * 通过验证码key获取验证码内容
	 * @param key 验证码key
	 * @return 验证码内容
	 */
	Optional<String> getCode(String key);

}
