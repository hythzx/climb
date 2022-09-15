package com.yjy.climb.modules.auth.service.impl;


import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.yjy.climb.modules.auth.domain.SysAuthority;
import com.yjy.climb.modules.auth.domain.enums.MenuType;
import com.yjy.climb.modules.auth.repository.SysAuthorityRepository;
import com.yjy.climb.modules.auth.service.AuthorityManagementService;
import com.yjy.climb.modules.auth.service.AuthorityReadService;
import com.yjy.climb.modules.auth.service.BaseService;
import com.yjy.climb.modules.auth.service.dto.SysAuthorityDTO;
import com.yjy.climb.modules.auth.service.mapper.AuthorityRequestMapper;
import com.yjy.climb.modules.auth.service.mapper.SysAuthorityMapper;
import com.yjy.climb.modules.auth.service.vo.AuthorityOperatingRequestVO;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class AuthorityManagementServiceImpl implements BaseService, AuthorityManagementService, AuthorityReadService {

	private final SysAuthorityRepository sysAuthorityRepository;

	private final AuthorityRequestMapper authorityRequestMapper;

	private final SysAuthorityMapper sysAuthorityMapper;

	public AuthorityManagementServiceImpl(SysAuthorityRepository sysAuthorityRepository, AuthorityRequestMapper authorityRequestMapper, SysAuthorityMapper sysAuthorityMapper) {
		this.sysAuthorityRepository = sysAuthorityRepository;
		this.authorityRequestMapper = authorityRequestMapper;
		this.sysAuthorityMapper = sysAuthorityMapper;
	}


	/**
	 * 使用VO创建菜单，从前端发起
	 *
	 * @param authorityOperatingRequestVO
	 */
	@Override
	public SysAuthorityDTO create(AuthorityOperatingRequestVO authorityOperatingRequestVO) {
		SysAuthority sysAuthority = authorityRequestMapper.toEntity(authorityOperatingRequestVO);
		sysAuthority.setEnabled(true);
		sysAuthorityRepository.save(sysAuthority);
		return sysAuthorityMapper.toDto(sysAuthority);
	}


	/**
	 * 使用VO更新菜单信息，从前端发起，全量更新
	 * @param authorityOperatingRequestVO
	 */
	@Override
	public SysAuthorityDTO update(AuthorityOperatingRequestVO authorityOperatingRequestVO) {
		validator(authorityOperatingRequestVO);
		SysAuthority sysAuthority = authorityRequestMapper.toEntity(
				authorityOperatingRequestVO,
				getEntityElseThrow(authorityOperatingRequestVO.getId(), sysAuthorityRepository)
		);
		return sysAuthorityMapper.toDto(sysAuthority);
	}

	/**
	 * 使用VO更新菜单的部分信息，忽略null值属性
	 *
	 * @param authorityOperatingRequestVO
	 */
	@Override
	public SysAuthorityDTO partUpdate(AuthorityOperatingRequestVO authorityOperatingRequestVO) {
		SysAuthority sysAuthority = authorityRequestMapper.partToEntity(
				authorityOperatingRequestVO,
				getEntityElseThrow(authorityOperatingRequestVO.getId(), sysAuthorityRepository)
		);
		validator(sysAuthority);
		return sysAuthorityMapper.toDto(sysAuthority);
	}

	/**
	 * 使用entity创建菜单，从后端发起
	 *
	 * @param sysAuthority
	 */
	@Override
	public SysAuthorityDTO create(SysAuthority sysAuthority) {
		validator(sysAuthority);
		sysAuthorityRepository.save(sysAuthority);
		return sysAuthorityMapper.toDto(sysAuthority);
	}

	/**
	 * 使用entity更新菜单，从后端发起
	 *
	 * @param sysAuthority
	 */
	@Override
	public SysAuthorityDTO update(SysAuthority sysAuthority) {
		validator(sysAuthority);
		sysAuthorityRepository.save(sysAuthority);
		return sysAuthorityMapper.toDto(sysAuthority);
	}

	/**
	 * 删除菜单
	 *
	 * @param id
	 */
	@Override
	public void delete(Long id) {
		SysAuthority sysAuthority = getEntityElseThrow(id, sysAuthorityRepository);
		sysAuthority.setDeleted(true);
		sysAuthority.setEnabled(false);
	}

	/**
	 * 通过id获取菜单信息
	 *
	 * @param id
	 * @return
	 */
	@Override
	public Optional<SysAuthorityDTO> findById(Long id) {
		return getEntity(id, sysAuthorityRepository).map(sysAuthorityMapper::toDto);
	}

	/**
	 * 通过ID获取菜单实体信息
	 *
	 * @param id
	 * @return
	 */
	@Override
	public Optional<SysAuthority> findEntityById(Long id) {
		return getEntity(id, sysAuthorityRepository);
	}

	/**
	 * 通过ID列表查询菜单列表
	 *
	 * @param ids id列表
	 * @return
	 */
	@Override
	public List<SysAuthority> findByIds(Collection<Long> ids) {
		return sysAuthorityRepository.findAllById(ids);
	}

	@Override
	public List<SysAuthorityDTO> getMenuList() {
		List<SysAuthority> sysAuthorities = findAll(sysAuthorityRepository);
		return sysAuthorityMapper.treeList(sysAuthorities.stream().
				filter(sysAuthority -> MenuType.MENU.equals(sysAuthority.getMenuType())).toList(), null);
	}

	@Override
	public List<SysAuthorityDTO> getAuthorityList() {
		return sysAuthorityMapper.toDto(findAll(sysAuthorityRepository));
	}

	@Override
	public List<SysAuthorityDTO> getAuthorityTreeList() {
		return sysAuthorityMapper.treeList(findAll(sysAuthorityRepository), null);
	}
}
