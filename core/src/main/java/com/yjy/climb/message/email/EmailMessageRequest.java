package com.yjy.climb.message.email;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;

import com.yjy.climb.message.IMessageRequest;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class EmailMessageRequest implements Serializable, IMessageRequest {

	@Serial
	private static final long serialVersionUID = 1L;

	// 邮件地址
	private String email;

	// 邮件标题
	private String title;

	// 邮件副标题
	private String subTitle;

	// 邮件内容
	private String content;

}
