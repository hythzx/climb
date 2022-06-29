package com.yjy.climb.message.app;

import com.yjy.climb.message.IMessageResponse;
import com.yjy.climb.message.IMessageService;
import com.yjy.climb.message.MessageTypeEnum;

/**
 * 发送APP消息
 */
public interface IAppMessageService extends IMessageService<AppMessageRequest, IMessageResponse> {

	@Override
	default boolean test(MessageTypeEnum messageType){
		return messageType != null && messageType.equals(MessageTypeEnum.APP_PUSH);
	};
}
