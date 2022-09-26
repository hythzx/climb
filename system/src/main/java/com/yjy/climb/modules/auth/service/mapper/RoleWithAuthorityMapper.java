package com.yjy.climb.modules.auth.service.mapper;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import com.yjy.climb.modules.auth.domain.SysRole;
import com.yjy.climb.modules.auth.service.dto.RoleWithAuthorityDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { SysAuthorityMapper.class})
public interface RoleWithAuthorityMapper extends BaseMapper<RoleWithAuthorityDTO, SysRole> {


	default Set<RoleWithAuthorityDTO> fromId(Set<Long> ids){
		if (ids == null) return new HashSet<>();
		return ids.stream().map(id -> RoleWithAuthorityDTO.builder().id(id).build()).collect(Collectors.toSet());
	}
}
