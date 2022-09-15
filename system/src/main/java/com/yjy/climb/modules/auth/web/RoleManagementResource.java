package com.yjy.climb.modules.auth.web;



import java.util.List;

import javax.transaction.Transactional;

import com.yjy.climb.modules.auth.service.RoleManagementService;
import com.yjy.climb.modules.auth.service.dto.RoleWithAuthorityDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Transactional
@Slf4j
@RequestMapping("/api")
@Tag(name = "角色管理", description = "角色管理接口，该接口仅对系统管理员开放")
@PreAuthorize(value = "hasAnyAuthority('system:all', 'system:write') and hasAnyRole('ADMIN')")
public class RoleManagementResource {

	private final RoleManagementService roleManagementService;

	public RoleManagementResource(RoleManagementService roleManagementService) {
		this.roleManagementService = roleManagementService;
	}

	@PostMapping("/roles")
	@Operation(summary = "创建角色，包含权限信息")
	public ResponseEntity<RoleWithAuthorityDTO> create(@RequestBody RoleWithAuthorityDTO roleDTO){
		RoleWithAuthorityDTO roleWithAuthorityDTO = roleManagementService.create(roleDTO);
		return ResponseEntity.ok(roleWithAuthorityDTO);
	}

	@PostMapping("/roles/{roleId}/add/authority/{id}")
	@Operation(summary = "给角色添加一个权限")
	public ResponseEntity<RoleWithAuthorityDTO> addAuthority(@PathVariable Long roleId, @PathVariable Long id){
		RoleWithAuthorityDTO roleWithAuthorityDTO = roleManagementService.addAuthority(roleId, id);
		return ResponseEntity.ok(roleWithAuthorityDTO);
	}

	@PostMapping("/roles/{roleId}/remove/authority/{id}")
	@Operation(summary = "给角色移除一个权限")
	public ResponseEntity<RoleWithAuthorityDTO> removeAuthority(@PathVariable Long roleId, @PathVariable Long id){
		RoleWithAuthorityDTO roleWithAuthorityDTO = roleManagementService.removeAuthority(roleId, id);
		return ResponseEntity.ok(roleWithAuthorityDTO);
	}


	@PutMapping("/roles/{id}/authorities")
	@Operation(summary = "设置角色的权限")
	public ResponseEntity<RoleWithAuthorityDTO> setRoleAuthorities(@PathVariable Long id, @RequestBody List<Long> authorities){
		RoleWithAuthorityDTO roleWithAuthorityDTO = roleManagementService.setAuthorities(id, authorities);
		return ResponseEntity.ok(roleWithAuthorityDTO);
	}
}
