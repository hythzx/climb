package com.yjy.climb.captcha;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;

import org.springframework.stereotype.Service;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * 验证码持久化缓存的默认实现，使用redis作为持久化存储服务
 */
@Service("redisCaptchaPersistence")
public class RedisCaptchaPersistence implements ICaptchaPersistence{

	private final Logger log = getLogger(RedisCaptchaPersistence.class);

	private final RedissonClient redissonClient;

	private static final String CAPTCHA_CACHE_KEY = "[captcha]cache";

	public RedisCaptchaPersistence(RedissonClient redissonClient) {
		this.redissonClient = redissonClient;
	}

	/**
	 * 保存验证码信息
	 *
	 * @param key 验证码key
	 * @param code 验证码内容
	 * @param timeout 验证码过期时间，默认5分钟
	 * @param timeUnit 验证码过期时间单位，默认分钟
	 */
	@Override
	public void save(String key, String code, Long timeout, TimeUnit timeUnit) {
		log.debug("Request to persistence captcha,key is [{}], code is [{}]", key, code);
		redissonClient.getMapCache(CAPTCHA_CACHE_KEY).put(key, code, timeout, timeUnit);
		log.debug("captcha save success, key is [{}], code is [{}]", key, code);
	}

	/**
	 * 通过验证码key获取验证码内容
	 *
	 * @param key 验证码key
	 * @return 验证码内容
	 */
	@Override
	public Optional<String> getCode(String key) {
		assert StringUtils.isNotBlank(key);
		log.debug("request to get captcha code, the key is [{}]", key);
		String code = (String) redissonClient.getMapCache(CAPTCHA_CACHE_KEY).get(key);
		log.debug("get captcha code completed,code is [{}], key is [{}]", code, key);
		return code != null ? Optional.of(code): Optional.empty();
	}
}
