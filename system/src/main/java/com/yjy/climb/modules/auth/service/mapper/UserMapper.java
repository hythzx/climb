package com.yjy.climb.modules.auth.service.mapper;

import com.yjy.climb.modules.auth.domain.SysUser;
import com.yjy.climb.modules.auth.service.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {})
public interface UserMapper extends BaseMapper<UserDTO, SysUser>{
}
