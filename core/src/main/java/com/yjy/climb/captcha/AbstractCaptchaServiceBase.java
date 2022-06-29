package com.yjy.climb.captcha;

import java.io.Serial;
import java.io.Serializable;
import java.util.Optional;

import javax.validation.constraints.NotNull;

import com.yjy.climb.exception.ErrorConstants.Captcha;
import com.yjy.climb.exception.captcha.CaptchaExpireException;
import com.yjy.climb.exception.captcha.CaptchaVerifyException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;


import static org.slf4j.LoggerFactory.getLogger;

/**
 * 验证码抽象类，实现简单的验证码校验
 */
public abstract class AbstractCaptchaServiceBase<R extends ICaptchaRequest, S extends ICaptchaResponse> implements ICaptchaService<R, S>, Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	private final Logger log = getLogger(AbstractCaptchaServiceBase.class);

	private final ICaptchaPersistence persistence;

	protected AbstractCaptchaServiceBase(ICaptchaPersistence persistence) {
		this.persistence = persistence;
	}

	/**
	 * 校验校验码
	 *
	 * @param code 用户输入的验证码内容
	 * @param key 该验证码的key
	 * @return 是否输入成功
	 */
	@Override
	public Boolean verify(@NotNull String code, @NotNull String key) throws CaptchaExpireException{
		assert StringUtils.isNotBlank(code) && StringUtils.isNotBlank(key);
		if (StringUtils.isBlank(code) || StringUtils.isBlank(key)){
			throw new CaptchaVerifyException(Captcha.verifyParamNull.getErrorMsg(), Captcha.verifyParamNull.getCode());
		}
		log.debug("request to verify captcha, key :[{}], code: [{}]", key, code);
		Optional<String> optional = persistence.getCode(key);
		if (optional.isPresent()){
			String realCode = optional.get();
			return code.equals(realCode);
		}
		throw new CaptchaExpireException();
	}
}
