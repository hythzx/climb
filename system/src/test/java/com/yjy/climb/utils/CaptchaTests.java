package com.yjy.climb.utils;


import java.util.UUID;

import com.yjy.climb.IntegrationTest;
import com.yjy.climb.captcha.ICaptchaResponse;
import com.yjy.climb.captcha.ICaptchaService;
import com.yjy.climb.captcha.image.ImageCaptchaResponse;
import com.yjy.climb.captcha.image.ImageCaptchaRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import static org.slf4j.LoggerFactory.getLogger;

@IntegrationTest
public class CaptchaTests {

	private final Logger log = getLogger(CaptchaTests.class);

	@Autowired
	@Qualifier(value = "imageCaptchaService")
	private ICaptchaService iCaptchaService;

	ICaptchaResponse captchaInfo;


	@BeforeEach
	public void init(){
		captchaInfo = iCaptchaService.create(new ImageCaptchaRequest());
	}

	@Test
	public void testCaptchaCreate(){
		assert captchaInfo instanceof ImageCaptchaResponse;
		ImageCaptchaResponse imageCaptchaInfo = (ImageCaptchaResponse) captchaInfo;
		log.info("key: [{}], code: [{}], base64Image:[{}]", captchaInfo.getKey(), captchaInfo.getCode(), imageCaptchaInfo.getBase64Content());
	}

	@Test
	public void captchaVerify(){
		Boolean verify = iCaptchaService.verify(captchaInfo.getCode(), captchaInfo.getKey());
		assert verify;
		Boolean verify1 = iCaptchaService.verify(UUID.randomUUID().toString(), captchaInfo.getKey());
		assert !verify1;
	}

}
