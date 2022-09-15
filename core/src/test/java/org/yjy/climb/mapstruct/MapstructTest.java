package org.yjy.climb.mapstruct;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Mapstruct 单元测试
 */
public class MapstructTest {

	private Bar bar;

	private BarLom barLom;

	private IMapper iMapper;

	String name = "aaaaaaaaaa";
	Integer age = 23;

	@BeforeEach
	public void setUp(){
		bar = new Bar(name, age);
		barLom = new BarLom(name, age);
		iMapper = Mappers.getMapper(IMapper.class);
	}

	@Test
	public void testMap(){
		Foo foo = iMapper.toFoo(bar);
		assert foo!= null && name.equals(foo.getName()) && age.equals(foo.getAge());
	}

	@Test
	public void testMapLom(){
		FooLom foo = iMapper.toFooLom(barLom);
		assert foo!= null && name.equals(foo.getName()) && age.equals(foo.getAge());
	}
}

class Foo{
	private String name;
	private Integer age;

	public Foo() {
	}

	public Foo(String name, Integer age) {
		this.name = name;
		this.age = age;
	}

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

	public Bar() {
	}

	public Bar(String name, Integer age) {
		this.name = name;
		this.age = age;
	}

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

@Data
@AllArgsConstructor
@NoArgsConstructor
class FooLom{
	private String name;
	private Integer age;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class BarLom{
	private String name;
	private Integer age;
}
@Mapper
interface IMapper{
	Foo toFoo(Bar bar);

	FooLom toFooLom(BarLom barLom);
}
