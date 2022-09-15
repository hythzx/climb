package com.yjy.climb.modules.auth.service.mapper;

import com.yjy.climb.modules.auth.domain.SysRole;
import com.yjy.climb.modules.auth.service.dto.RoleDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { SysAuthorityMapper.class})
public interface RoleMapper extends BaseMapper<RoleDTO, SysRole> {
}
