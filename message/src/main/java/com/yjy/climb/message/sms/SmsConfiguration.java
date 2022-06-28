package com.yjy.climb.message.sms;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.yjy.climb.config.ApplicationProperties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SmsConfiguration {

	private final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）

	private final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）

	@Bean
	public IAcsClient client(ApplicationProperties applicationProperties){
		System.setProperty("sun.net.client.defaultConnectTimeout", "1000");
		System.setProperty("sun.net.client.defaultReadTimeout", "3000");
		DefaultProfile profile = DefaultProfile.getProfile(
				applicationProperties.getAliSms().getRegionId(),
				applicationProperties.getAliSms().getAccessKey(),
				applicationProperties.getAliSms().getSecretKey()
		);
		DefaultProfile.addEndpoint("cn-hangzhou", product, domain);
		return new DefaultAcsClient(profile);
	}
}
