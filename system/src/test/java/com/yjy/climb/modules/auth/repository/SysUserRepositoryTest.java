package com.yjy.climb.modules.auth.repository;


import java.time.LocalDate;
import java.util.Date;

import javax.transaction.Transactional;

import com.yjy.climb.IntegrationTest;
import com.yjy.climb.modules.auth.domain.SysUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@IntegrationTest
public class SysUserRepositoryTest {

	private SysUser user;

	@Autowired
	private SysUserRepository userRepository;

	@BeforeEach
	void setUp() {
		user = SysUser.builder()
				.login("21wqe21")
				.nickName("a12d3你好")
				.email("dwqdwqdqwdqwd@qq.com")
				.mobile("18551831902")
				.password("dfewfqwedqwdwqdqw")
				.enabled(true)
				.headerImage("dwqdwqdqwd")
				.activated(true)
				.birthday(LocalDate.ofYearDay(1991, 13))
				.build();
	}

	@Test
	@Transactional
	public void testAdd(){
		userRepository.save(user);
		assertNotNull(user.getId());
	}


}
