package com.yjy.climb.modules.auth.web;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@SecurityRequirement(name = "jwt")
public class HelloResource {

	@GetMapping("/say")
	public String sayHello(){
		return "hello";
	}
}
