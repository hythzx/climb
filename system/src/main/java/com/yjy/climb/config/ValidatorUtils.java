package com.yjy.climb.config;

import javax.validation.Validation;
import javax.validation.Validator;

public class ValidatorUtils {

	public static final Validator validator =
			Validation.buildDefaultValidatorFactory().getValidator();
}
