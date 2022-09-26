package com.yjy.climb.exception.user;

import java.io.Serial;

import com.yjy.climb.exception.ErrorConstants;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class UserExistException extends AbstractThrowableProblem {

	@Serial
	private static final long serialVersionUID = 1L;

	public UserExistException() {
		super(ErrorConstants.LOGIN_ALREADY_USED_TYPE, "用户名已存在", Status.BAD_REQUEST);
	}

	public UserExistException(String message) {
		super(ErrorConstants.LOGIN_ALREADY_USED_TYPE, message, Status.BAD_REQUEST);
	}

}
