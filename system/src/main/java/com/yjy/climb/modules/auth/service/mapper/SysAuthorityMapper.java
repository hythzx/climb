package com.yjy.climb.modules.auth.service.mapper;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.yjy.climb.modules.auth.domain.SysAuthority;
import com.yjy.climb.modules.auth.service.dto.SysAuthorityDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SysAuthorityMapper extends BaseMapper<SysAuthorityDTO, SysAuthority>{

	// 默认最顶级菜单的父ID是-1
	Long TOP_PARENT_ID = -1L;

	/**
	 * 将entity列表转为树结构的DTO列表
	 * @author zhaoyuan 2022.8.31
	 * @param sysAuthorities entity list
	 * @return dto tree list
	 */
	default List<SysAuthorityDTO> treeList(List<SysAuthority> sysAuthorities, Long parentId){
		if (parentId == null) parentId = TOP_PARENT_ID;
		return findChildren(toDto(sysAuthorities), parentId, new ArrayList<>());
	}

	default List<SysAuthorityDTO> findChildren(List<SysAuthorityDTO> list, Long id, List<SysAuthorityDTO> newList){
		for (SysAuthorityDTO sysAuthorityDTO : list) {
			if (id != null && id.equals(sysAuthorityDTO.getParentId())){
				newList.add(sysAuthorityDTO);
				List<SysAuthorityDTO> children = findChildren(list, sysAuthorityDTO.getId(), new ArrayList<>()).
						stream().sorted(Comparator.comparingInt(SysAuthorityDTO::getMenuSort)).toList();
				sysAuthorityDTO.setLeaf(children.isEmpty());
				sysAuthorityDTO.setChildren(children);
			}
		}
		return newList;
	}
}
