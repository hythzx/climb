package com.yjy.climb.message.sms;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;

import com.yjy.climb.message.IMessageRequest;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SmsMessageRequest implements Serializable, IMessageRequest {

	@Serial
	private static final long serialVersionUID = 1L;

	// 手机号
	private String mobile;

	// 短信模版编码
	private String templateCode;

	// 配置的短信模版业务编码，参照application.ali-sms.templates, code和templateCode二选一，templateCode优先级较高
	private String code;

	// 短信参数
	private Map<String, String> params;

}
