package com.yjy.climb.modules.auth.web;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloResource {

	@GetMapping("/say")
	public String sayHello(){
		return "hello";
	}
}
