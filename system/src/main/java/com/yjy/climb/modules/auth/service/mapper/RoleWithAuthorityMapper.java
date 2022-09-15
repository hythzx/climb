package com.yjy.climb.modules.auth.service.mapper;

import com.yjy.climb.modules.auth.domain.SysRole;
import com.yjy.climb.modules.auth.service.dto.RoleWithAuthorityDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { SysAuthorityMapper.class})
public interface RoleWithAuthorityMapper extends BaseMapper<RoleWithAuthorityDTO, SysRole> {
}
