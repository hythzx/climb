package com.yjy.climb.modules.auth.service.dto;

import java.io.Serial;
import java.io.Serializable;


import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class RoleDTO extends BaseRoleDTO implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	@Builder
	public RoleDTO(Long id, String name, String roleCode, Boolean enabled) {
		super(id, name, roleCode, enabled);
	}
}
