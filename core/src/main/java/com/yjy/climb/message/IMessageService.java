package com.yjy.climb.message;


public interface IMessageService<R extends IMessageRequest, S extends IMessageResponse> {

	S sendMessage(R messageRequest) throws RuntimeException;

	/**
	 * 检测是否支持该消息类型，用于动态调用
	 * @param messageType 消息类型
	 * @return
	 */
	boolean test(MessageTypeEnum messageType);
}
