package com.yjy.climb.exception.captcha;

import java.io.Serial;

import com.yjy.climb.exception.BadRequestAlertException;
import com.yjy.climb.exception.ErrorConstants;
import com.yjy.climb.exception.ErrorConstants.Captcha;

public class CaptchaVerifyException extends BadRequestAlertException {

    @Serial
	private static final long serialVersionUID = 1L;

    public CaptchaVerifyException() {
        super(ErrorConstants.DEFAULT_TYPE, Captcha.verifyError.getErrorMsg(), Captcha.ENTITY_NAME, Captcha.verifyError.getCode());
    }

	public CaptchaVerifyException(String defaultMessage, String errorKey) {
		super(defaultMessage, Captcha.ENTITY_NAME, errorKey);
	}
}
