package com.yjy.climb.exception.system;

import java.io.Serial;

import com.yjy.climb.exception.BadRequestAlertException;
import com.yjy.climb.exception.ErrorConstants;
import com.yjy.climb.exception.ErrorConstants.Error;
import com.yjy.climb.exception.ErrorConstants.System;

public class SystemException extends BadRequestAlertException {

	@Serial
	private static final long serialVersionUID = 1L;

	public SystemException(String defaultMessage, String errorKey) {
		super(ErrorConstants.DEFAULT_TYPE, defaultMessage, System.ENTITY_NAME, errorKey);
	}

	public SystemException(Error error) {
		super(ErrorConstants.DEFAULT_TYPE, error.getErrorMsg(), System.ENTITY_NAME, error.getErrorCode());
	}
}
