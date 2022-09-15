package com.yjy.climb.exception;

import java.net.URI;

import lombok.Getter;

public final class ErrorConstants {

    public static final String ERR_CONCURRENCY_FAILURE = "error.concurrencyFailure";
    public static final String ERR_VALIDATION = "error.validation";

	public static final String ERR_MSG = "系统错误，请稍后再试";
    public static final String PROBLEM_BASE_URL = "https://climb.yjy-group.com/problem";
    public static final URI DEFAULT_TYPE = URI.create(PROBLEM_BASE_URL + "/problem-with-message");
    public static final URI CONSTRAINT_VIOLATION_TYPE = URI.create(PROBLEM_BASE_URL + "/constraint-violation");
    public static final URI INVALID_PASSWORD_TYPE = URI.create(PROBLEM_BASE_URL + "/invalid-password");
    public static final URI EMAIL_ALREADY_USED_TYPE = URI.create(PROBLEM_BASE_URL + "/email-already-used");
    public static final URI LOGIN_ALREADY_USED_TYPE = URI.create(PROBLEM_BASE_URL + "/login-already-used");

    private ErrorConstants() {}


	/**
	 * 系统错误
	 */
	public static class System{
		public static final String ENTITY_NAME = "system";

		public static final Error NOT_NULL = new Error("notnull", "参数不能为空");

		public static final Error IS_NOT_EXIST = new Error("isnull", "数据不存在");
	}

	/**
	 * 验证码模块
	 */
	public static class Captcha{
		public static final String ENTITY_NAME = "captcha";
		/**
		 * 验证码过期
		 */
		public static final Error expire = new Error("expire", "验证码已失效");

		/**
		 * 验证码创建失败
		 */
		public static final Error createError = new Error("create.failure", "验证码创建失败");

		public static final Error verifyError = new Error("verify.failure", "验证码验证错误");

		public static final Error verifyParamNull = new Error("verify.notnull", "验证码参数缺失");


	}

	/**
	 * 消息模块
	 */
	public static class Message{

		public static final String ENTITY_NAME = "message";

		public static class Sms{
			public static final String ENTITY_NAME = "message.sms";

			// 短信发送错误
			public static final Error error = new Error("error", "短信发送错误");

			public static final Error sendError = new Error("senderror", "短信发送失败");

			public static final Error codeError = new Error("codeerror", "没有找到该短信模版配置");

			public static final Error noTemplateCode =  new Error("notemplatecode", "缺少短信模板参数");

		}
	}

	@Getter
	public static class Error {

		/**
		 * 错误编码
		 */
		private String code;

		/**
		 * 错误消息提示
		 */
		private String errorMsg;

		/**
		 * 数字错误码，类似于10023
		 */
		private String errorCode;

		private Error(){}

		public Error(String code, String errorMsg) {
			this.code = code;
			this.errorMsg = errorMsg;
		}

		public Error(String code, String errorMsg, String errorCode) {
			this.code = code;
			this.errorMsg = errorMsg;
			this.errorCode = errorCode;
		}
	}
}
