package com.yjy.climb.modules.auth.web;

import javax.transaction.NotSupportedException;
import javax.validation.constraints.NotNull;

import com.yjy.climb.captcha.ICaptchaResponse;
import com.yjy.climb.captcha.ICaptchaService;
import com.yjy.climb.captcha.sms.SmsCaptchaRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping("/api/captcha")
@Tag(name = "获取验证码接口", description = "验证码在线生成")
public class CaptchaResource {

	private final Logger log = getLogger(CaptchaResource.class);

	private final ICaptchaService imageCaptchaService;

	private final ICaptchaService smsCaptchaService;


	public CaptchaResource(
			@Qualifier("imageCaptcha") ICaptchaService imageCaptchaService,
			@Qualifier("smsCaptcha") ICaptchaService smsCaptchaService) {
		this.imageCaptchaService = imageCaptchaService;
		this.smsCaptchaService = smsCaptchaService;
	}

	@GetMapping("/image")
	@Operation(summary = "获取图形验证码,该验证码使用base64字符串")
	public ResponseEntity<ICaptchaResponse> getImageCaptcha() throws NotSupportedException {
		ICaptchaResponse captchaInfo = imageCaptchaService.create();
		return ResponseEntity.ok(captchaInfo);
	}

	@GetMapping("/sms")
	@Operation(summary = "获取短信验证码")
	public ResponseEntity<ICaptchaResponse> getSmsCaptcha(@RequestParam String mobile){
		SmsCaptchaRequest smsCaptchaParam = SmsCaptchaRequest.builder().mobile(mobile).build();
		ICaptchaResponse iCaptchaResponse = smsCaptchaService.create(smsCaptchaParam);
		return ResponseEntity.ok(iCaptchaResponse);
	}

	@GetMapping("/verify")
	@Operation(summary = "验证用户输入的验证码是否正确，仅作为测试使用")
	public ResponseEntity<Boolean> verifyCaptcha(@Validated @NotNull(message = "key必填") String key,@RequestParam String code){
		return ResponseEntity.ok(imageCaptchaService.verify(code, key));
	}
}
