package com.yjy.climb.modules.auth.service;


import com.yjy.climb.modules.auth.domain.SysAuthority;
import com.yjy.climb.modules.auth.service.dto.SysAuthorityDTO;
import com.yjy.climb.modules.auth.service.vo.AuthorityOperatingRequestVO;

/**
 * 操作菜单的服务
 */
public interface AuthorityManagementService {

	/**
	 * 使用VO创建菜单，从前端发起
	 * @param authorityOperatingRequestVO
	 */
	SysAuthorityDTO create(AuthorityOperatingRequestVO authorityOperatingRequestVO);

	/**
	 * 使用VO更新菜单的所有信息，从前端发起
	 * @param authorityOperatingRequestVO
	 */
	SysAuthorityDTO update(AuthorityOperatingRequestVO authorityOperatingRequestVO);


	/**
	 * 使用VO更新菜单的部分信息，忽略null值属性
	 * @param authorityOperatingRequestVO
	 */
	SysAuthorityDTO partUpdate(AuthorityOperatingRequestVO authorityOperatingRequestVO);

	/**
	 * 使用entity创建菜单，从后端发起
	 * @param sysAuthority
	 */
	SysAuthorityDTO create(SysAuthority sysAuthority);

	/**
	 * 使用entity更新菜单，从后端发起
	 * @param sysAuthority
	 */
	SysAuthorityDTO update(SysAuthority sysAuthority);

	/**
	 * 删除菜单
	 * @param id
	 */
	void delete(Long id);
}
