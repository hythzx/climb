package com.yjy.climb.web.resource;


import com.yjy.climb.security.SecurityUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping("/api")
@Tag(name = "hello", description = "测试接口类")
public class ApiResource {

	private final Logger log = getLogger(ApiResource.class);

	private final RedissonClient redissonClient;

	public ApiResource(RedissonClient redissonClient) {
		this.redissonClient = redissonClient;
	}

	@GetMapping("/hello")
	@Operation(description = "测试", summary = "返回hello字符串的接口")
	public String sayHello(){
		return SecurityUtils.getCurrentUserLogin().orElse("anymouse");
	}

	@GetMapping("/cache")
	@Cacheable(value = "api", keyGenerator = "keyGenerator")
	public String getCache(String key, String value){
		log.info("key: {}, value: {}", key, value);
		return value;
	}

	@GetMapping("/redis")
	public String redis(String key, String value){
		RMap<String, String> rs = redissonClient.getMap("rs");
		String s = rs.get(key);
		if (s == null) {
			rs.put(key, value);
			log.info("缓存不存在");
		}
		return value;
	}
}
