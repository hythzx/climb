package com.yjy.climb.message.sms;

import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.yjy.climb.config.ApplicationProperties;
import com.yjy.climb.config.ApplicationProperties.AliSms;
import com.yjy.climb.config.ApplicationProperties.AliSms.Template;
import com.yjy.climb.exception.SmsSendException;
import com.yjy.climb.message.ISmsMessageService;
import com.yjy.climb.message.SmsMessageSenderParam;
import org.slf4j.Logger;

import org.springframework.stereotype.Service;

import static org.slf4j.LoggerFactory.getLogger;


@Service("aliyunSmsMessageService")
public class AliyunSmsMessageService implements ISmsMessageService {

	private final Logger log = getLogger(AliyunSmsMessageService.class);

	private final ApplicationProperties applicationProperties;

	private final IAcsClient client;


	public AliyunSmsMessageService(ApplicationProperties applicationProperties, IAcsClient client) {
		this.applicationProperties = applicationProperties;
		this.client = client;
	}

	/**
	 * 发送短信
	 *
	 * @param smsMessage
	 */
	@Override
	public void sendMessage(SmsMessageSenderParam smsMessage) throws SmsSendException {
		log.debug("request to send sms message, param is : [{}]", smsMessage);
		AliSms aliSms = applicationProperties.getAliSms();
		String templateCode = null;
		if (smsMessage.getCode() != null){
			Template template = aliSms.getTemplates().get(smsMessage.getCode());
			if (template == null) {
				throw new SmsSendException("短信模版编号不存在", SmsSendException.CODE_ERROR);
			}
			templateCode = template.getCode();
		}else {
			if (smsMessage.getTemplateCode() == null) {
				throw new SmsSendException("缺少短信模板参数", SmsSendException.NO_TEMPLATE_CODE);
			}
			templateCode = smsMessage.getTemplateCode();
		}
		SendSmsRequest request = new SendSmsRequest();
		//必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式；发送国际/港澳台消息时，接收号码格式为国际区号+号码，如“85200000000”
		request.setPhoneNumbers(smsMessage.getMobile());
		//必填:短信签名-可在短信控制台中找到
		request.setSignName(applicationProperties.getAliSms().getSign());
		//必填:短信模板-可在短信控制台中找到，发送国际/港澳台消息时，请使用国际/港澳台短信模版
		request.setTemplateCode(templateCode);
		//可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
		//友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
		//参考：request.setTemplateParam("{\"变量1\":\"值1\",\"变量2\":\"值2\",\"变量3\":\"值3\"}")
		try {
			String params = new ObjectMapper().writeValueAsString(smsMessage.getParams());
			request.setTemplateParam(new Gson().toJson(smsMessage.getParams()));
			//可选-上行短信扩展码(扩展码字段控制在7位或以下，无特殊需求用户请忽略此字段)
			//request.setSmsUpExtendCode("90997");
			//可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
			//请求失败这里会抛ClientException异常
			SendSmsResponse sendSmsResponse = null;
			try {
				sendSmsResponse = client.getAcsResponse(request);
			}
			catch (ClientException e) {
				log.warn("sms send error, sms param is [{}]", smsMessage);
				throw new SmsSendException();
			}
			if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
				log.debug("sms send success, sms param is [{}]", smsMessage);
			}
		}
		catch (JsonProcessingException e) {
			throw new SmsSendException();
		}
	}
}
