package com.yjy.climb.message.sms;

import com.yjy.climb.message.IMessageResponse;
import com.yjy.climb.message.IMessageService;
import com.yjy.climb.message.MessageTypeEnum;

/**
 * 发送短信接口
 */
public interface ISmsMessageService extends IMessageService<SmsMessageRequest, IMessageResponse> {

	@Override
	default boolean test(MessageTypeEnum messageType){
		return messageType != null && messageType.equals(MessageTypeEnum.SMS);
	};

}
