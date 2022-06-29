package com.yjy.climb.message.sms;

import java.io.Serial;
import java.io.Serializable;

import com.yjy.climb.message.AbstractMessageResponse;

/**
 * 阿里云短信的返回类
 */
public class AliyunSmsMessageResponse extends AbstractMessageResponse implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	private String bizId;

	private String RequestId;

	public AliyunSmsMessageResponse(String bizId, String requestId) {
		this.bizId = bizId;
		RequestId = requestId;
	}

	public AliyunSmsMessageResponse(Boolean success, String message) {
		super(success, message);
	}

	public AliyunSmsMessageResponse(Boolean success, String code, String message, String bizId, String requestId) {
		super(success, code, message);
		this.bizId = bizId;
		RequestId = requestId;
	}



	public String getBizId() {
		return bizId;
	}

	public void setBizId(String bizId) {
		this.bizId = bizId;
	}

	public String getRequestId() {
		return RequestId;
	}

	public void setRequestId(String requestId) {
		RequestId = requestId;
	}
}
