package com.yjy.climb.exception.management;

import java.io.Serial;

import com.yjy.climb.exception.BadRequestAlertException;
import com.yjy.climb.exception.ErrorConstants;

public class MobileAlreadyUsedException extends BadRequestAlertException {

	@Serial
	private static final long serialVersionUID = 1L;

    public MobileAlreadyUsedException() {
        super(ErrorConstants.EMAIL_ALREADY_USED_TYPE, "手机号已被使用", "userManagement", "emailexists");
    }
}
