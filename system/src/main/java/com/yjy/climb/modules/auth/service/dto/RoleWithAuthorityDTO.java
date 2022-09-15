package com.yjy.climb.modules.auth.service.dto;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;


import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class RoleWithAuthorityDTO extends BaseRoleDTO implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;


	@Builder
	public RoleWithAuthorityDTO(Long id, String name, String roleCode, Boolean enabled, Set<SysAuthorityDTO> sysAuthorities) {
		super(id, name, roleCode, enabled);
		this.sysAuthorities = sysAuthorities;
	}

	private Set<SysAuthorityDTO> sysAuthorities;
}
