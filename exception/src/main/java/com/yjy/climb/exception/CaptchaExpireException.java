package com.yjy.climb.exception;

import java.io.Serial;

public class CaptchaExpireException extends BadRequestAlertException {

    @Serial
	private static final long serialVersionUID = 1L;

    public CaptchaExpireException() {
        super(ErrorConstants.DEFAULT_TYPE, "验证码已失效", "captcha", "expire");
    }
}
