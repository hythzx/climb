package com.yjy.climb.web.resource;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;


import com.yjy.climb.config.ValidatorUtils;

public abstract class BaseController {


	protected <T> void valid(T t, Class<?> c ){
		Set<ConstraintViolation<T>> validate = ValidatorUtils.validator.validate(t, c);
		throw new ConstraintViolationException(validate);
	}
}
