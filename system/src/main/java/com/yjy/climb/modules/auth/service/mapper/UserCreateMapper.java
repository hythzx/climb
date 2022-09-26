package com.yjy.climb.modules.auth.service.mapper;

import com.yjy.climb.modules.auth.domain.SysUser;
import com.yjy.climb.modules.auth.service.vo.UserCreateVO;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", uses = {RoleWithAuthorityMapper.class})
public interface UserCreateMapper extends BaseMapper<UserCreateVO, SysUser> {
}
