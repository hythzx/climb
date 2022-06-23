package com.yjy.climb.exception;

public class MobileAlreadyUsedException extends BadRequestAlertException {

    private static final long serialVersionUID = 1L;

    public MobileAlreadyUsedException() {
        super(ErrorConstants.EMAIL_ALREADY_USED_TYPE, "手机号已被使用", "userManagement", "emailexists");
    }
}
