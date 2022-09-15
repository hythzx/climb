package com.yjy.climb.modules.auth.domain;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TenantTest {

	private Tenant tenant;

	private final Validator validator =
			Validation.buildDefaultValidatorFactory().getValidator();

	@BeforeEach
	void setUp() {
		tenant = Tenant.builder()
				.name("ewfewfewf")
				.build();
	}


	@Test
	public void testRegx(){
		Set<ConstraintViolation<Tenant>> validate = validator.validate(tenant);
		assert validate.size() == 0;
	}
}
