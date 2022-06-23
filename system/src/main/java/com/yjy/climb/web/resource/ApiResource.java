package com.yjy.climb.web.resource;


import com.yjy.climb.security.SecurityUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Tag(name = "hello", description = "测试接口类")
public class ApiResource {

	@GetMapping("/hello")
	@Operation(description = "测试", summary = "返回hello字符串的接口")
	public String sayHello(){
		return SecurityUtils.getCurrentUserLogin().orElse("anymouse");
	}
}
