package com.yjy.climb.modules.auth.service.mapper;


import com.yjy.climb.modules.auth.domain.SysAuthority;
import com.yjy.climb.modules.auth.service.vo.AuthorityOperatingRequestVO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorityRequestMapper extends BaseMapper<AuthorityOperatingRequestVO, SysAuthority>{


}
