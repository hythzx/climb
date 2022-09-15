package com.yjy.climb.modules.auth.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.yjy.climb.exception.ErrorConstants.System;
import com.yjy.climb.exception.system.SystemException;
import com.yjy.climb.modules.auth.domain.SysAuthority;
import com.yjy.climb.modules.auth.domain.SysRole;
import com.yjy.climb.modules.auth.repository.SysRoleRepository;
import com.yjy.climb.modules.auth.service.AuthorityReadService;
import com.yjy.climb.modules.auth.service.BaseService;
import com.yjy.climb.modules.auth.service.RoleManagementService;
import com.yjy.climb.modules.auth.service.dto.RoleDTO;
import com.yjy.climb.modules.auth.service.dto.RoleWithAuthorityDTO;
import com.yjy.climb.modules.auth.service.dto.SysAuthorityDTO;
import com.yjy.climb.modules.auth.service.mapper.RoleMapper;
import com.yjy.climb.modules.auth.service.mapper.RoleWithAuthorityMapper;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RoleManagementServiceImpl implements BaseService, RoleManagementService {

	private final SysRoleRepository roleRepository;

	private final RoleMapper roleMapper;

	private final RoleWithAuthorityMapper roleWithAuthorityMapper;

	private final AuthorityReadService authorityReadService;

	public RoleManagementServiceImpl(SysRoleRepository roleRepository, RoleMapper roleMapper, RoleWithAuthorityMapper roleWithAuthorityMapper, AuthorityReadService authorityReadService) {
		this.roleRepository = roleRepository;
		this.roleMapper = roleMapper;
		this.roleWithAuthorityMapper = roleWithAuthorityMapper;
		this.authorityReadService = authorityReadService;
	}


	/**
	 * 创建角色，仅包含基本信息
	 *
	 * @param roleDTO roleDTO
	 * @return roleDTO
	 */
	@Override
	public RoleDTO create(RoleDTO roleDTO) {
		SysRole sysRole = roleMapper.toEntity(roleDTO);
		roleRepository.save(sysRole);
		return roleMapper.toDto(sysRole);
	}

	/**
	 * 创建角色，包含权限信息
	 *
	 * @param roleWithAuthorityDTO roleWithAuthorityDTO
	 * @return roleWithAuthorityDTO
	 */
	@Override
	public RoleWithAuthorityDTO create(RoleWithAuthorityDTO roleWithAuthorityDTO) {
		Set<SysAuthority> sysAuthorityList = null;
		if (roleWithAuthorityDTO.getSysAuthorities() != null){
			Set<Long> authoritiesIdSet = roleWithAuthorityDTO.getSysAuthorities().
					stream().map(SysAuthorityDTO::getId).collect(Collectors.toSet());
			List<SysAuthority> authorityList = authorityReadService.findByIds(authoritiesIdSet);
			if (!authorityList.isEmpty()){
				sysAuthorityList = new HashSet<>(authorityList);
			}
		}
		SysRole sysRole = roleWithAuthorityMapper.toEntity(roleWithAuthorityDTO);
		sysRole.setSysAuthorities(sysAuthorityList);
		roleRepository.save(sysRole);
		return roleWithAuthorityMapper.toDto(sysRole);
	}

	/**
	 * 设置角色的所有权限，覆盖原来的权限
	 *
	 * @param id 角色id
	 * @param authorities 权限id列表
	 * @return
	 */
	@Override
	public RoleWithAuthorityDTO setAuthorities(Long id, List<Long> authorities) {
		SysRole sysRole = roleRepository.findById(id).
				orElseThrow(() -> new SystemException(System.IS_NOT_EXIST));
		List<SysAuthority> sysAuthorities = authorityReadService.findByIds(authorities);
		sysRole.getSysAuthorities().clear();
		if (!sysAuthorities.isEmpty()){
			sysRole.getSysAuthorities().addAll(sysAuthorities);
		}
		return roleWithAuthorityMapper.toDto(sysRole);
	}

	/**
	 * 移除角色对应的权限
	 *
	 * @param id 角色ID
	 * @param authorities 权限id列表
	 * @return
	 */
	@Override
	public RoleWithAuthorityDTO removeAuthorities(Long id, List<Long> authorities) {
		return null;
	}

	/**
	 * 移除角色的单个权限
	 *
	 * @param id 角色id
	 * @param authorityId 权限id
	 * @return
	 */
	@Override
	public RoleWithAuthorityDTO removeAuthority(Long id, Long authorityId) {
		SysRole sysRole = roleRepository.findById(id).
				orElseThrow(() -> new SystemException(System.IS_NOT_EXIST));
		Set<SysAuthority> authoritySet = sysRole.getSysAuthorities().stream().
				filter(sysAuthority1 -> sysAuthority1.getId().equals(authorityId)).
				collect(Collectors.toSet());
		if (!authoritySet.isEmpty()){
			sysRole.getSysAuthorities().removeAll(authoritySet);
		}
		return roleWithAuthorityMapper.toDto(sysRole);
	}

	/**
	 * 为角色添加权限
	 *
	 * @param id 角色id
	 * @param authorityId 权限id
	 * @return
	 */
	@Override
	public RoleWithAuthorityDTO addAuthority(Long id, Long authorityId) {
		SysAuthority sysAuthority = authorityReadService.findEntityById(authorityId).
				orElseThrow(() -> new SystemException(System.IS_NOT_EXIST));
		SysRole sysRole = roleRepository.findById(id).
				orElseThrow(() -> new SystemException(System.IS_NOT_EXIST));
		sysRole.getSysAuthorities().add(sysAuthority);
		return roleWithAuthorityMapper.toDto(sysRole);
	}

	/**
	 * 给角色添加多个权限
	 *
	 * @param id 角色id
	 * @param authorities 权限id列表
	 * @return
	 */
	@Override
	public RoleWithAuthorityDTO addAuthorities(Long id, List<Long> authorities) {
		return null;
	}

	/**
	 * 更新角色信息，不包含权限信息
	 *
	 * @param roleDTO 角色DTO
	 * @return 角色DTO
	 */
	@Override
	public RoleWithAuthorityDTO update(RoleDTO roleDTO) {
		return null;
	}

	/**
	 * 更新角色信息，包含权限信息
	 *
	 * @param roleWithAuthorityDTO 角色DTO
	 * @return
	 */
	@Override
	public RoleWithAuthorityDTO update(RoleWithAuthorityDTO roleWithAuthorityDTO) {
		return null;
	}

	/**
	 * 更新角色的部分信息，不包含权限
	 *
	 * @param roleDTO 角色DTO
	 * @return
	 */
	@Override
	public RoleDTO patch(RoleDTO roleDTO) {
		return null;
	}

	/**
	 * 更新角色的部分信息，包含权限
	 *
	 * @param roleWithAuthorityDTO 角色DTO
	 * @return
	 */
	@Override
	public RoleWithAuthorityDTO patch(RoleWithAuthorityDTO roleWithAuthorityDTO) {
		return null;
	}

	/**
	 * 使用entity创建角色
	 *
	 * @param sysRole 角色
	 * @return
	 */
	@Override
	public RoleWithAuthorityDTO create(SysRole sysRole) {
		return null;
	}

	/**
	 * 更新角色
	 *
	 * @param sysRole 角色
	 * @return
	 */
	@Override
	public RoleWithAuthorityDTO update(SysRole sysRole) {
		return null;
	}

	@Override
	public void delete(Long id) {

	}
}
