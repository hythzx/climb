package com.yjy.climb.modules.auth.web;

import javax.transaction.NotSupportedException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.yjy.climb.captcha.ICaptchaResponse;
import com.yjy.climb.captcha.image.IImageCaptchaService;
import com.yjy.climb.captcha.sms.ISmsCaptchaService;
import com.yjy.climb.captcha.sms.SmsCaptchaRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import org.slf4j.Logger;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping("/api/captcha")
@Tag(name = "获取验证码接口", description = "验证码在线生成")
public class CaptchaResource {

	private final Logger log = getLogger(CaptchaResource.class);

	private final IImageCaptchaService imageCaptchaService;

	private final ISmsCaptchaService smsCaptchaService;


	public CaptchaResource(IImageCaptchaService imageCaptchaService, ISmsCaptchaService smsCaptchaService) {
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
		SmsCaptchaRequest smsCaptchaParam = SmsCaptchaRequest.builder().mobile(mobile).businessKey(SmsCaptchaRequest.code).build();
		ICaptchaResponse iCaptchaResponse = smsCaptchaService.create(smsCaptchaParam);
		return ResponseEntity.ok(iCaptchaResponse);
	}

	@PostMapping("/verify")
	@Operation(summary = "验证用户输入的验证码是否正确，仅作为测试使用")
	public ResponseEntity<Boolean> verifyCaptcha(@Valid @RequestBody @Parameter(description = "验证内容") CaptchaVerify captchaVerify){
		return ResponseEntity.ok(imageCaptchaService.verify(captchaVerify.code, captchaVerify.key));
	}

	@Data
	public static class CaptchaVerify{
		@NotNull(message = "验证码密钥不能为空")
		@Size(max = 36, message = "密钥长度不能超过64位")
		private String key;

		@NotNull(message = "验证码不能为空")
		@Size(max = 6, message = "验证码长度不能超过6位")
		private String code;
	}
}
