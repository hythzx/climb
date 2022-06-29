package com.yjy.climb.message.app;

import java.io.Serial;
import java.io.Serializable;

import com.yjy.climb.message.IMessageRequest;
import lombok.Builder;
import lombok.Data;

/**
 * 发送APP消息的请求类
 */
@Data
@Builder
public class AppMessageRequest implements Serializable, IMessageRequest {

	@Serial
	private static final long serialVersionUID = 1L;
}
