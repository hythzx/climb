package com.yjy.climb.modules.auth.service;

import java.util.List;

import com.yjy.climb.modules.auth.domain.SysRole;
import com.yjy.climb.modules.auth.service.dto.RoleDTO;
import com.yjy.climb.modules.auth.service.dto.RoleWithAuthorityDTO;

/**
 * 角色管理接口
 */
public interface RoleManagementService {

	/**
	 * 创建角色，仅包含基本信息
	 * @param roleDTO roleDTO
	 * @return roleDTO
	 */
	RoleDTO create(RoleDTO roleDTO);

	/**
	 *  创建角色，包含权限信息
	 * @param roleWithAuthorityDTO roleWithAuthorityDTO
	 * @return roleWithAuthorityDTO
	 */
	RoleWithAuthorityDTO create(RoleWithAuthorityDTO roleWithAuthorityDTO);

	/**
	 * 设置角色的所有权限，覆盖原来的权限
	 * @param id 角色id
	 * @param authorities 权限id列表
	 * @return
	 */
	RoleWithAuthorityDTO setAuthorities(Long id, List<Long> authorities);

	/**
	 * 移除角色对应的权限
	 * @param id 角色ID
	 * @param authorities 权限id列表
	 * @return
	 */
	RoleWithAuthorityDTO removeAuthorities(Long id, List<Long> authorities);

	/**
	 * 移除角色的单个权限
	 * @param id 角色id
	 * @param authorityId 权限id
	 * @return
	 */
	RoleWithAuthorityDTO removeAuthority(Long id, Long authorityId);

	/**
	 * 为角色添加权限
	 * @param id 角色id
	 * @param authorityId 权限id
	 * @return
	 */
	RoleWithAuthorityDTO addAuthority(Long id, Long authorityId);

	/**
	 * 给角色添加多个权限
	 * @param id 角色id
	 * @param authorities 权限id列表
	 * @return
	 */
	RoleWithAuthorityDTO addAuthorities(Long id, List<Long> authorities);

	/**
	 * 更新角色信息，不包含权限信息
	 * @param roleDTO 角色DTO
	 * @return 角色DTO
	 */
	RoleWithAuthorityDTO update(RoleDTO roleDTO);

	/**
	 * 更新角色信息，包含权限信息
	 * @param roleWithAuthorityDTO 角色DTO
	 * @return
	 */
	RoleWithAuthorityDTO update(RoleWithAuthorityDTO roleWithAuthorityDTO);

	/**
	 * 更新角色的部分信息，不包含权限
	 * @param roleDTO 角色DTO
	 * @return
	 */
	RoleDTO patch(RoleDTO roleDTO);

	/**
	 * 更新角色的部分信息，包含权限
	 * @param roleWithAuthorityDTO 角色DTO
	 * @return
	 */
	RoleWithAuthorityDTO patch(RoleWithAuthorityDTO roleWithAuthorityDTO);

	/**
	 * 使用entity创建角色
	 * @param sysRole 角色
	 * @return
	 */
	RoleWithAuthorityDTO create(SysRole sysRole);

	/**
	 * 更新角色
	 * @param sysRole 角色
	 * @return
	 */
	RoleWithAuthorityDTO update(SysRole sysRole);

	void delete(Long id);

}
