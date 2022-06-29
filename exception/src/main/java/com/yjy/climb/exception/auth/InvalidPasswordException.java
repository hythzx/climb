package com.yjy.climb.exception.auth;

import java.io.Serial;

import com.yjy.climb.exception.ErrorConstants;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class InvalidPasswordException extends AbstractThrowableProblem {

    @Serial
	private static final long serialVersionUID = 1L;

    public InvalidPasswordException() {
        super(ErrorConstants.INVALID_PASSWORD_TYPE, "密码错误", Status.BAD_REQUEST);
    }
}
