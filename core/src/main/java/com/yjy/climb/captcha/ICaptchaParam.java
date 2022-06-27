package com.yjy.climb.captcha;

import java.util.concurrent.TimeUnit;

/**
 * 生成验证码的参数，大多数情况并不需要额外的参数，但是涉及到图片验证码比如需要长度、干扰因子等需要额外的参数，短信验证码需要短信模版和参数名
 */
public interface ICaptchaParam {


	/**
	 * 验证码失效时间单位
	 * @return 时间单位
	 */
	default TimeUnit getTimeunit(){
		return TimeUnit.MINUTES;
	}

	/**
	 * 验证码失效时间
	 * @return 失效时间
	 */
	default Long getTimeout(){
		return 5L;
	}

}
