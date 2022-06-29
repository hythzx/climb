package com.yjy.climb.message.email;

import com.yjy.climb.message.IMessageResponse;
import com.yjy.climb.message.IMessageService;
import com.yjy.climb.message.MessageTypeEnum;

/**
 * 发送电子邮件的接口
 */
public interface IEmailMessageService extends IMessageService<EmailMessageRequest, IMessageResponse> {

	@Override
	default boolean test(MessageTypeEnum messageType){
		return messageType != null && messageType.equals(MessageTypeEnum.E_MAIL);
	};
}
