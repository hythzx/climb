package com.yjy.climb.modules.auth.repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


import com.yjy.climb.IntegrationTest;
import com.yjy.climb.modules.auth.domain.SysAuthority;
import com.yjy.climb.modules.auth.domain.SysRole;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


@IntegrationTest
@Transactional
public class SysRoleRepositoryTest {

	@Autowired
	private SysRoleRepository sysRoleRepository;

	@Autowired
	private SysAuthorityRepository sysAuthorityRepository;

	private static final String ROLE_CODE = "AAAAAAAAAAAAA";

	private static final String ROLE_NAME = "BBBBBBBBBBBB";

	@Test
	public void saveRole(){
		Set<SysAuthority> sysAuthorityList = new HashSet<>(sysAuthorityRepository.findAll());
		assert sysAuthorityList.size() > 0;
		SysRole sysRole = SysRole.builder()
				.roleCode(ROLE_CODE)
				.name(ROLE_NAME)
				.enabled(true)
				.sysAuthorities(sysAuthorityList)
				.build();
		sysRoleRepository.save(sysRole);
		assert sysRole.getId() != null;
	}
}
