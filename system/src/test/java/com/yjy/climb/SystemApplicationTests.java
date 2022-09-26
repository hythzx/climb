package com.yjy.climb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;


class SystemApplicationTests {

	@Test
	void contextLoads() {

		List<Long> ids = Arrays.asList(1L, 2L, 3L, 4L, 5L, 6L, 7L);
		List<Long> id2s = Arrays.asList(2L, 4L, 12L, 23L);
		Set<Long> collect = ids.stream().filter(id2s::contains).collect(Collectors.toSet());
		System.out.println(collect);
	}

}
