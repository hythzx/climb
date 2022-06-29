package com.yjy.climb.exception.management;

import java.io.Serial;

import com.yjy.climb.exception.BadRequestAlertException;
import com.yjy.climb.exception.ErrorConstants;

public class LoginAlreadyUsedException extends BadRequestAlertException {

    @Serial
	private static final long serialVersionUID = 1L;

    public LoginAlreadyUsedException() {
        super(ErrorConstants.LOGIN_ALREADY_USED_TYPE, "用户名已存在", "userManagement", "userexists");
    }
}
