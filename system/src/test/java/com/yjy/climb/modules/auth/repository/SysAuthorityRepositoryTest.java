package com.yjy.climb.modules.auth.repository;

import javax.transaction.Transactional;
import javax.validation.Validation;
import javax.validation.Validator;

import com.yjy.climb.IntegrationTest;
import com.yjy.climb.modules.auth.domain.SysAuthority;
import com.yjy.climb.modules.auth.domain.enums.AuthorityScope;
import com.yjy.climb.modules.auth.domain.enums.MenuType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@IntegrationTest
@Transactional
public class SysAuthorityRepositoryTest {

	@Autowired
	private SysAuthorityRepository sysAuthorityRepository;

	private final Validator validator =
			Validation.buildDefaultValidatorFactory().getValidator();

	@BeforeEach
	void setUp() {
	}

	@Test
	public void testSave(){

		SysAuthority sysAuthority = SysAuthority.builder()
				.parentId(-1L)
				.blankTarget(false)
				.enabled(true)
				.icon("dssad")
				.show(true)
				.scope(AuthorityScope.SYSTEM)
				.url("xxxxxx")
				.title("首页")
				.defaultRedirect(false)
				.menuSort(1)
				.menuType(MenuType.MENU)
				.permission("ada_qwd")
				.redirectWeight(0)
				.build();

		sysAuthorityRepository.save(sysAuthority);
		assertNotNull(sysAuthority.getId());
	}
}
