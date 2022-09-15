package com.yjy.climb.modules.auth.web;


import java.util.List;
import java.util.Optional;


import com.yjy.climb.exception.ErrorConstants.System;
import com.yjy.climb.exception.system.SystemException;
import com.yjy.climb.modules.auth.service.AuthorityReadService;
import com.yjy.climb.modules.auth.service.dto.SysAuthorityDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
@PreAuthorize(value = "hasAnyAuthority('ROLE_ADMIN', 'system:all', 'system:read')")
@Tag(name = "权限及菜单管理", description = "读取权限菜单接口")
public class AuthorityReadResource {

	private final AuthorityReadService authorityReadService;

	public AuthorityReadResource(AuthorityReadService authorityReadService) {
		this.authorityReadService = authorityReadService;
	}

	@GetMapping("/authorities/{id}/info")
	@Operation(summary = "通过菜单id获取菜单信息")
	public ResponseEntity<SysAuthorityDTO> findAuthorityById(@PathVariable Long id){
		Optional<SysAuthorityDTO> optionalSysAuthorityDTO = authorityReadService.findById(id);
		return ResponseEntity.ok(optionalSysAuthorityDTO.orElseThrow(() -> new SystemException(System.IS_NOT_EXIST)));
	}

	@GetMapping("/authorities/menu")
	@Operation(summary = "获取菜单树")
	public ResponseEntity<List<SysAuthorityDTO>> findMenuList(){
		List<SysAuthorityDTO> menuList = authorityReadService.getMenuList();
		return ResponseEntity.ok(menuList);
	}

	@GetMapping("/authorities/list")
	@Operation(summary = "获取菜列表")
	public ResponseEntity<List<SysAuthorityDTO>> findAuthorityList(){
		List<SysAuthorityDTO> authorityDTOList = authorityReadService.getAuthorityList();
		return ResponseEntity.ok(authorityDTOList);
	}

	@GetMapping("/authorities/tree")
	@Operation(summary = "获取权限菜单树")
	public ResponseEntity<List<SysAuthorityDTO>> findAuthorityTreeList(){
		List<SysAuthorityDTO> authorityTreeList = authorityReadService.getAuthorityTreeList();
		return ResponseEntity.ok(authorityTreeList);
	}
}
