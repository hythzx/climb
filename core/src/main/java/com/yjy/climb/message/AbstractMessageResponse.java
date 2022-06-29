package com.yjy.climb.message;

import java.io.Serial;
import java.io.Serializable;

import lombok.Builder;


public abstract class AbstractMessageResponse implements IMessageResponse, Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	private Boolean success;

	private String code;

	private String message;

	public AbstractMessageResponse() {
	}

	public AbstractMessageResponse(Boolean success) {
		this.success = success;
	}

	public AbstractMessageResponse(Boolean success, String message) {
		this.success = success;
		this.message = message;
	}


	public AbstractMessageResponse(Boolean success, String code, String message) {
		this.success = success;
		this.code = code;
		this.message = message;
	}

	@Override
	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	@Override
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
