package com.yjy.climb.exception.captcha;

import java.io.Serial;

import com.yjy.climb.exception.BadRequestAlertException;
import com.yjy.climb.exception.ErrorConstants;
import com.yjy.climb.exception.ErrorConstants.Captcha;

public class CaptchaExpireException extends BadRequestAlertException {

    @Serial
	private static final long serialVersionUID = 1L;

    public CaptchaExpireException() {
        super(ErrorConstants.DEFAULT_TYPE, Captcha.expire.getErrorMsg(), Captcha.ENTITY_NAME, Captcha.expire.getCode());
    }

	public CaptchaExpireException(String defaultMessage) {
		super(defaultMessage, Captcha.ENTITY_NAME, Captcha.expire.getCode());
	}
}
