package com.yjy.climb.modules.auth.service.mapper;

import com.yjy.climb.modules.auth.domain.SysUser;
import com.yjy.climb.modules.auth.service.dto.UserDTO;
import com.yjy.climb.modules.auth.service.dto.UserWithRoleDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {RoleWithAuthorityMapper.class})
public interface UserWithRoleMapper extends BaseMapper<UserWithRoleDTO, SysUser>{
}
