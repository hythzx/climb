package com.yjy.climb.message.wechat;

import java.io.Serial;
import java.io.Serializable;

import com.yjy.climb.message.IMessageRequest;
import lombok.Builder;
import lombok.Data;

/**
 * 微信消息的请求类
 */
@Data
@Builder
public class WechatMessageRequest implements Serializable, IMessageRequest {

	@Serial
	private static final long serialVersionUID = 1L;
}
