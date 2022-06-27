package com.yjy.climb.modules.auth.web;

import com.yjy.climb.captcha.ICaptcha;
import com.yjy.climb.captcha.ICaptchaInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping("/api/captcha")
@Tag(name = "获取验证码接口", description = "验证码在线生成")
public class CaptchaResource {

	private final Logger log = getLogger(CaptchaResource.class);

	private final ICaptcha iCaptcha;

	public CaptchaResource(ICaptcha iCaptcha) {
		this.iCaptcha = iCaptcha;
	}

	@GetMapping("/image")
	@Operation(summary = "获取图形验证码,该验证码使用base64字符串")
	public ResponseEntity<ICaptchaInfo> getImageCaptcha(){
		ICaptchaInfo captchaInfo = iCaptcha.create();
		return ResponseEntity.ok(captchaInfo);
	}

	@GetMapping("/verify")
	@Operation(summary = "验证用户输入的验证码是否正确，仅作为测试使用")
	public ResponseEntity<Boolean> verifyCaptcha(String key, String code){
		return ResponseEntity.ok(iCaptcha.verify(code, key));
	}
}
