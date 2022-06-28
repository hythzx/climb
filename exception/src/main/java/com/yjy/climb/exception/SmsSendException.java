package com.yjy.climb.exception;

import java.io.Serial;

public class SmsSendException extends BadRequestAlertException {

	public static final String CODE_ERROR = "code-error";

	public static final String NO_TEMPLATE_CODE = "no-template-code";

    @Serial
	private static final long serialVersionUID = 1L;

    public SmsSendException() {
        super(ErrorConstants.DEFAULT_TYPE, "短信发送错误", "message", "error");
    }

	public SmsSendException(String defaultMessage, String errorKey) {
		super(defaultMessage, "message", errorKey);
	}
}
