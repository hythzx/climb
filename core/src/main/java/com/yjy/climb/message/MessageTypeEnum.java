package com.yjy.climb.message;

public enum MessageTypeEnum {

	SMS("短信"), E_MAIL("电子邮件"), APP_PUSH("APP消息"), WEI_XIN("微信消息");

	private final String name;

	MessageTypeEnum(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
