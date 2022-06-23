package com.yjy.climb.druid;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest
public class DBPoolTest {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Test
	public void testJDBC(){
		jdbcTemplate.execute("show tables");
	}
}
