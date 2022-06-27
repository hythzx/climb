package com.yjy.climb.utils;


import java.util.UUID;

import com.yjy.climb.IntegrationTest;
import com.yjy.climb.captcha.ICaptcha;
import com.yjy.climb.captcha.ICaptchaInfo;
import com.yjy.climb.captcha.ImageCaptchaInfo;
import com.yjy.climb.captcha.hutool.HutoolCaptchaParam;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;

import static org.slf4j.LoggerFactory.getLogger;

@IntegrationTest
public class CaptchaTests {

	private final Logger log = getLogger(CaptchaTests.class);

	@Autowired
	private ICaptcha iCaptcha;

	ICaptchaInfo captchaInfo;


	@BeforeEach
	public void init(){
		captchaInfo = iCaptcha.create(new HutoolCaptchaParam());
	}

	@Test
	public void testCaptchaCreate(){
		assert captchaInfo instanceof ImageCaptchaInfo;
		ImageCaptchaInfo imageCaptchaInfo = (ImageCaptchaInfo) captchaInfo;
		log.info("key: [{}], code: [{}], base64Image:[{}]", captchaInfo.getKey(), captchaInfo.getCode(), imageCaptchaInfo.getBase64Content());
	}

	@Test
	public void captchaVerify(){
		Boolean verify = iCaptcha.verify(captchaInfo.getCode(), captchaInfo.getKey());
		assert verify;
		Boolean verify1 = iCaptcha.verify(UUID.randomUUID().toString(), captchaInfo.getKey());
		assert !verify1;
	}

}
