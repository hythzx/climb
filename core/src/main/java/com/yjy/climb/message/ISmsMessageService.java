package com.yjy.climb.message;

import com.yjy.climb.exception.SmsSendException;

/**
 * 发送短信接口
 */
public interface ISmsMessageService {

	/**
	 * 发送短信
	 * @param smsMessage
	 */
	void sendMessage(SmsMessageSenderParam smsMessage) throws SmsSendException;
}
