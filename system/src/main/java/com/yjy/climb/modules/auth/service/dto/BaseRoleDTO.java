package com.yjy.climb.modules.auth.service.dto;

import java.io.Serial;
import java.io.Serializable;

import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@AllArgsConstructor
public class BaseRoleDTO implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	protected Long id;

	@Length(max = 32, message = "角色名称请不要超过32个字符")
	@Pattern(regexp = "^[\\u4E00-\\u9FA5A-Za-z0-9_]+$", message = "请不要输入特殊字符")
	protected String name;

	@Length(max = 32, message = "角色编码请不要超过32个字符")
	@Pattern(regexp = "^[A-Za-z0-9_]+$", message = "请不要输入特殊字符")
	protected String roleCode;

	protected Boolean enabled;
}
