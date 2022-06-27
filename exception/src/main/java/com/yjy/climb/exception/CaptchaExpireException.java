package com.yjy.climb.exception;

public class CaptchaExpireException extends BadRequestAlertException {

    private static final long serialVersionUID = 1L;

    public CaptchaExpireException() {
        super(ErrorConstants.DEFAULT_TYPE, "验证码已失效", "captcha", "expire");
    }
}
