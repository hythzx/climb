package com.yjy.climb.message.wechat;

import com.yjy.climb.message.IMessageResponse;
import com.yjy.climb.message.IMessageService;
import com.yjy.climb.message.MessageTypeEnum;

/**
 * 发送微信消息
 */
public interface IWechatMessageService extends IMessageService<WechatMessageRequest, IMessageResponse> {

	@Override
	default boolean test(MessageTypeEnum messageType){
		return messageType != null && messageType.equals(MessageTypeEnum.WEI_XIN);
	};
}
