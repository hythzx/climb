package com.yjy.climb.modules.auth.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.yjy.climb.exception.ErrorConstants.System;
import com.yjy.climb.exception.system.SystemException;
import com.yjy.climb.exception.user.UserExistException;
import com.yjy.climb.modules.auth.domain.SysRole;
import com.yjy.climb.modules.auth.domain.SysUser;
import com.yjy.climb.modules.auth.repository.SysRoleRepository;
import com.yjy.climb.modules.auth.repository.SysUserRepository;
import com.yjy.climb.modules.auth.service.BaseService;
import com.yjy.climb.modules.auth.service.UserManagementService;
import com.yjy.climb.modules.auth.service.dto.RoleWithAuthorityDTO;
import com.yjy.climb.modules.auth.service.dto.UserDTO;
import com.yjy.climb.modules.auth.service.dto.UserWithRoleDTO;
import com.yjy.climb.modules.auth.service.mapper.UserCreateMapper;
import com.yjy.climb.modules.auth.service.mapper.UserMapper;
import com.yjy.climb.modules.auth.service.mapper.UserWithRoleMapper;
import com.yjy.climb.modules.auth.service.vo.UserCreateVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class UserManagementServiceImpl implements BaseService, UserManagementService {


	private final SysUserRepository userRepository;

	private final UserMapper userMapper;

	private final UserWithRoleMapper userWithRoleMapper;

	private final UserCreateMapper userCreateMapper;

	private final SysRoleRepository roleRepository;

	private final PasswordEncoder passwordEncoder;

	public UserManagementServiceImpl(SysUserRepository userRepository, UserMapper userMapper, UserWithRoleMapper userWithRoleMapper, UserCreateMapper userCreateMapper, SysRoleRepository roleRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
		this.userWithRoleMapper = userWithRoleMapper;
		this.userCreateMapper = userCreateMapper;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}

	/**
	 * 创建用户，包含角色信息
	 *
	 * @param userCreateVO UserCreateVO
	 * @return userWithRoleDTO
	 */
	@Override
	public UserWithRoleDTO create(UserCreateVO userCreateVO) {
		validator(userCreateVO);
		// 判断用户名、电话和电子邮箱是否存在
		Optional<SysUser> userOptional = userRepository.findFirstByLogin(userCreateVO.getLogin());
		if (userOptional.isPresent()) throw new UserExistException();
		if (StringUtils.isNotBlank(userCreateVO.getMobile())){
			userOptional = userRepository.findFirstByMobile(userCreateVO.getMobile());
			if (userOptional.isPresent()) throw new UserExistException("手机号已存在");
		}
		if (StringUtils.isNotBlank(userCreateVO.getEmail())){
			userOptional = userRepository.findFirstByEmail(userCreateVO.getEmail());
			if (userOptional.isPresent()) throw new UserExistException("电子邮箱已存在");
		}
		// 密码加密
		String password = passwordEncoder.encode(userCreateVO.getPassword());
		userCreateVO.setPassword(password);
		List<SysRole> roleList = roleRepository.findAllById(userCreateVO.getRoleIds());
		SysUser user = userCreateMapper.toEntity(userCreateVO);
		userRepository.save(user);
		user.setSysRoles(new HashSet<>(roleList));
		return userWithRoleMapper.toDto(user);
	}

	/**
	 * 设置用户的所有角色，覆盖原来的角色
	 *
	 * @param id 用户id
	 * @param roleIds 角色id列表
	 * @return
	 */
	@Override
	public UserWithRoleDTO setRoles(Long id, List<Long> roleIds) {
		SysUser user = getEntityElseThrow(id, userRepository);
		user.getSysRoles().clear();
		List<SysRole> roles = roleRepository.findAllById(roleIds);
		user.setSysRoles(new HashSet<>(roles));
		return userWithRoleMapper.toDto(user);
	}

	/**
	 * 移除用户对应的角色
	 *
	 * @param id 用户ID
	 * @param roles 角色id列表
	 * @return
	 */
	@Override
	public UserWithRoleDTO removeRoles(Long id, List<Long> roles) {
		SysUser user = getEntityElseThrow(id, userRepository);
		Set<SysRole> roleSet = user.getSysRoles().stream()
				.filter(sysRole -> roles.contains(sysRole.getId())).collect(Collectors.toSet());
		user.getSysRoles().removeAll(roleSet);
		return userWithRoleMapper.toDto(user);
	}

	/**
	 * 移除用户的单个角色
	 *
	 * @param id 用户id
	 * @param authorityId 角色id
	 * @return
	 */
	@Override
	public UserWithRoleDTO removeAuthority(Long id, Long authorityId) {
		SysUser user = getEntityElseThrow(id, userRepository);
		SysRole role = user.getSysRoles().stream().filter(sysRole -> sysRole.getId().equals(authorityId)).findFirst().orElseThrow(
				() -> new SystemException(System.IS_NOT_EXIST.getErrorMsg(),
						System.IS_NOT_EXIST.getErrorCode())
		);
		user.getSysRoles().remove(role);
		return userWithRoleMapper.toDto(user);
	}

	/**
	 * 为用户添加角色
	 *
	 * @param id 用户id
	 * @param roleId 角色id
	 * @return
	 */
	@Override
	public UserWithRoleDTO addRole(Long id, Long roleId) {
		SysUser user = getEntityElseThrow(id, userRepository);
		SysRole role = getEntityElseThrow(roleId, roleRepository);
		user.getSysRoles().add(role);
		return userWithRoleMapper.toDto(user);
	}

	/**
	 * 给用户添加多个角色
	 *
	 * @param id 用户id
	 * @param roles 角色id列表
	 * @return
	 */
	@Override
	public UserWithRoleDTO addRoles(Long id, List<Long> roles) {
		List<SysRole> roleList = findAllByIds(roleRepository, roles);
		SysUser user = getEntityElseThrow(id, userRepository);
		user.getSysRoles().addAll(roleList);
		return userWithRoleMapper.toDto(user);
	}

	/**
	 * 更新用户信息，不包含角色信息
	 *
	 * @param userDTO 用户DTO
	 * @return 用户DTO
	 */
	@Override
	public UserWithRoleDTO update(UserDTO userDTO) {
		validator(userDTO);
		SysUser user = getEntityElseThrow(userDTO.getId(), userRepository);
		userMapper.toEntity(userDTO, user);
		return userWithRoleMapper.toDto(user);
	}

	/**
	 * 更新用户信息，包含角色信息
	 *
	 * @param userWithRoleDTO 用户DTO
	 * @return
	 */
	@Override
	public UserWithRoleDTO update(UserWithRoleDTO userWithRoleDTO) {
		validator(userWithRoleDTO);
		SysUser user = getEntityElseThrow(userWithRoleDTO.getId(), userRepository);
		user.getSysRoles().clear();
		userWithRoleMapper.toEntity(userWithRoleDTO, user);
		return getUserWithRoleDTO(userWithRoleDTO, user);
	}

	/**
	 * 更新用户的部分信息，不包含角色
	 *
	 * @param userDTO 用户DTO
	 * @return
	 */
	@Override
	public UserDTO patch(UserDTO userDTO) {
		SysUser user = getEntityElseThrow(userDTO.getId(), userRepository);
		userMapper.partToEntity(userDTO, user);
		validator(user);
		return userMapper.toDto(user);
	}

	/**
	 * 更新用户的部分信息，包含角色
	 *
	 * @param userWithRoleDTO 用户DTO
	 * @return
	 */
	@Override
	public UserWithRoleDTO patch(UserWithRoleDTO userWithRoleDTO) {
		validator(userWithRoleDTO);
		SysUser user = getEntityElseThrow(userWithRoleDTO.getId(), userRepository);
		user.getSysRoles().clear();
		userWithRoleMapper.partToEntity(userWithRoleDTO, user);
		return getUserWithRoleDTO(userWithRoleDTO, user);
	}

	private UserWithRoleDTO getUserWithRoleDTO(UserWithRoleDTO userWithRoleDTO, SysUser user) {
		Set<Long> roles = userWithRoleDTO.getRoles().stream().map(RoleWithAuthorityDTO::getId).collect(Collectors.toSet());
		List<SysRole> newRoles = findAllByIds(roleRepository, roles);
		user.setSysRoles(new HashSet<>(newRoles));
		return userWithRoleMapper.toDto(user);
	}

	/**
	 * 使用entity创建用户
	 *
	 * @param sysUser 用户
	 * @return
	 */
	@Override
	public UserWithRoleDTO create(SysUser sysUser) {
		return userWithRoleMapper.toDto(userRepository.save(sysUser));
	}


	@Override
	public void delete(Long id) {
		SysUser user = getEntityElseThrow(id, userRepository);
		user.setDeleted(true);
		user.setEnabled(false);
		user.setActivated(false);
	}
}
