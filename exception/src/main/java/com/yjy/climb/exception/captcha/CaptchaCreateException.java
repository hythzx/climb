package com.yjy.climb.exception.captcha;

import java.io.Serial;

import com.yjy.climb.exception.BadRequestAlertException;
import com.yjy.climb.exception.ErrorConstants;
import com.yjy.climb.exception.ErrorConstants.Captcha;

public class CaptchaCreateException extends BadRequestAlertException {

    @Serial
	private static final long serialVersionUID = 1L;

    public CaptchaCreateException() {
        super(ErrorConstants.DEFAULT_TYPE, Captcha.createError.getErrorMsg(), Captcha.ENTITY_NAME, Captcha.createError.getCode());
    }

	public CaptchaCreateException(String message, String errorKey) {
		super(message, Captcha.ENTITY_NAME, errorKey);
	}
}
