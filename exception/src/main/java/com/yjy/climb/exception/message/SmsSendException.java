package com.yjy.climb.exception.message;

import java.io.Serial;

import com.yjy.climb.exception.BadRequestAlertException;
import com.yjy.climb.exception.ErrorConstants;
import com.yjy.climb.exception.ErrorConstants.Message.Sms;

public class SmsSendException extends BadRequestAlertException {

	@Serial
	private static final long serialVersionUID = 1L;

    public SmsSendException() {
        super(ErrorConstants.DEFAULT_TYPE, Sms.error.getErrorMsg(), Sms.ENTITY_NAME, Sms.error.getErrorCode());
    }

	public SmsSendException(String defaultMessage, String errorKey) {
		super(defaultMessage, "message", errorKey);
	}
}
