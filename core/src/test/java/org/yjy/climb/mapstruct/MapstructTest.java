package org.yjy.climb.mapstruct;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * Mapstruct 单元测试
 */
public class MapstructTest {

	private Bar bar;

	private IMapper iMapper;

	@BeforeEach
	public void setUp(){
		bar = new Bar();
		iMapper = Mappers.getMapper(IMapper.class);
	}

	@Test
	public void testMap(){
		String name = "aaaaaaaaaa";
		Integer age = 23;
		bar.setName(name);
		bar.setAge(age);
		Foo foo = iMapper.toFoo(bar);
		assert foo!= null && name.equals(foo.getName()) && age.equals(foo.getAge());
	}
}

class Foo{
	private String name;
	private Integer age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
}

class Bar{
	private String name;
	private Integer age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
}

@Mapper
interface IMapper{
	Foo toFoo(Bar bar);
}
