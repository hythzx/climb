package com.yjy.climb.utils;

import java.awt.Image;
import java.util.UUID;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

public class CaptchaTests {

	private final Logger log = getLogger(CaptchaTests.class);

	@Test
	public void testCaptcha(){
		CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(200, 100, 4, 20);
		String imageBase64 = captcha.getImageBase64();
		String code = captcha.getCode();
		log.info("{}", imageBase64);
		log.info("code: {}", code);
	}
}
