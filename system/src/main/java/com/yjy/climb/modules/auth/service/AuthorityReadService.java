package com.yjy.climb.modules.auth.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.yjy.climb.modules.auth.domain.SysAuthority;
import com.yjy.climb.modules.auth.service.dto.SysAuthorityDTO;

/**
 * 读取菜单服务
 */
public interface AuthorityReadService {

	/**
	 * 通过id获取菜单信息
	 * @param id
	 * @return
	 */
	Optional<SysAuthorityDTO> findById(Long id);

	/**
	 * 通过ID获取菜单实体信息
	 * @param id
	 * @return
	 */
	Optional<SysAuthority> findEntityById(Long id);

	/**
	 * 通过ID列表查询菜单列表
	 * @param ids id列表
	 * @return
	 */
	List<SysAuthority> findByIds(Collection<Long> ids);

	/**
	 * 获取菜单列表，过滤按钮和接口
	 * @return authority dto list
	 */
	List<SysAuthorityDTO> getMenuList();

	/**
	 * 获取菜单权限列表，包含按钮和接口
	 * @return authority dto list
	 */
	List<SysAuthorityDTO> getAuthorityList();

	/**
	 * 获取权限菜单树，包含菜单、按钮和接口
	 * @return authority dto list
	 */
	List<SysAuthorityDTO> getAuthorityTreeList();
}
