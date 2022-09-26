package com.yjy.climb.modules.auth.web;

import com.yjy.climb.modules.auth.service.UserManagementService;
import com.yjy.climb.modules.auth.service.dto.RoleWithAuthorityDTO;
import com.yjy.climb.modules.auth.service.dto.UserWithRoleDTO;
import com.yjy.climb.modules.auth.service.vo.UserCreateVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Tag(name = "用户管理", description = "用户管理接口，该接口仅对系统管理员开放")
@PreAuthorize(value = "hasAnyAuthority('system:all', 'system:write') or hasAnyRole('ADMIN')")
@Slf4j
public class UserManagementResource {

	private final UserManagementService userManagementService;

	public UserManagementResource(UserManagementService userManagementService) {
		this.userManagementService = userManagementService;
	}

	@PostMapping("/user")
	@Operation(summary = "创建用户，包含角色信息")
	public ResponseEntity<UserWithRoleDTO> create(@RequestBody UserCreateVO userCreateVO){
		UserWithRoleDTO userDTO = userManagementService.create(userCreateVO);
		return ResponseEntity.ok(userDTO);
	}
}
