package com.yjy.climb.modules.auth.service;

import java.util.List;

import com.yjy.climb.modules.auth.domain.SysRole;
import com.yjy.climb.modules.auth.domain.SysUser;
import com.yjy.climb.modules.auth.service.dto.UserDTO;
import com.yjy.climb.modules.auth.service.dto.UserWithRoleDTO;
import com.yjy.climb.modules.auth.service.vo.UserCreateVO;

/**
 * 管理用户信息的服务
 */
public interface UserManagementService {

	/**
	 *  创建用户，包含角色信息
	 * @param userCreateVO UserCreateVO
	 * @return userWithRoleDTO
	 */
	UserWithRoleDTO create(UserCreateVO userCreateVO);

	/**
	 * 设置用户的所有角色，覆盖原来的角色
	 * @param id 用户id
	 * @param roles 角色id列表
	 * @return
	 */
	UserWithRoleDTO setRoles(Long id, List<Long> roles);

	/**
	 * 移除用户对应的角色
	 * @param id 用户ID
	 * @param roles 角色id列表
	 * @return
	 */
	UserWithRoleDTO removeRoles(Long id, List<Long> roles);

	/**
	 * 移除用户的单个角色
	 * @param id 用户id
	 * @param authorityId 角色id
	 * @return
	 */
	UserWithRoleDTO removeAuthority(Long id, Long authorityId);

	/**
	 * 为用户添加角色
	 * @param id 用户id
	 * @param roleId 角色id
	 * @return
	 */
	UserWithRoleDTO addRole(Long id, Long roleId);

	/**
	 * 给用户添加多个角色
	 * @param id 用户id
	 * @param roles 角色id列表
	 * @return
	 */
	UserWithRoleDTO addRoles(Long id, List<Long> roles);

	/**
	 * 更新用户信息，不包含角色信息
	 * @param userDTO 用户DTO
	 * @return 用户DTO
	 */
	UserWithRoleDTO update(UserDTO userDTO);

	/**
	 * 更新用户信息，包含角色信息
	 * @param userWithRoleDTO 用户DTO
	 * @return
	 */
	UserWithRoleDTO update(UserWithRoleDTO userWithRoleDTO);

	/**
	 * 更新用户的部分信息，不包含角色
	 * @param userDTO 用户DTO
	 * @return
	 */
	UserDTO patch(UserDTO userDTO);

	/**
	 * 更新用户的部分信息，包含角色
	 * @param userWithRoleDTO 用户DTO
	 * @return
	 */
	UserWithRoleDTO patch(UserWithRoleDTO userWithRoleDTO);

	/**
	 * 使用entity创建用户
	 * @param sysUser 用户
	 * @return
	 */
	UserWithRoleDTO create(SysUser sysUser);


	void delete(Long id);
}
