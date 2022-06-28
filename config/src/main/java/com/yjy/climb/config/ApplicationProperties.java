package com.yjy.climb.config;

import java.util.Map;

import lombok.Data;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application")
public class ApplicationProperties {

	private final AliSms aliSms = new AliSms();

	/**
	 * 短信配置类，目前仅支持阿里云短信，后续增加其它短信平台
	 */
	@Data
	public static class AliSms {

		private String regionId = "cn-hangzhou";

		private String accessKey;

		private String secretKey;
		/**
		 * 短信签名
		 */
		private String sign;

		private Map<String, Template> templates;

		/**
		 * 短信模版
		 */
		@Data
		public static class Template{
			/**
			 * 模版描述
			 */
			private String description;

			/**
			 * 短信模版编号
			 */
			private String code;

			/**
			 * 短信模版参数，以逗号隔开
			 */
			private String params;
		}

	}

	public AliSms getAliSms() {
		return aliSms;
	}
}
