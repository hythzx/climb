package com.yjy.climb.message;

public interface IMessageResponse {

	// 是否发送成功
	Boolean getSuccess();

	// 消息发送状态码
	String getCode();

	// 状态码的描述
	String getMessage();
}
