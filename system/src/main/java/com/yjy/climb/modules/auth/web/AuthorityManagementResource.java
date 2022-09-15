package com.yjy.climb.modules.auth.web;



import com.yjy.climb.modules.auth.service.AuthorityManagementService;
import com.yjy.climb.modules.auth.service.dto.SysAuthorityDTO;
import com.yjy.climb.modules.auth.service.vo.AuthorityOperatingRequestVO;
import com.yjy.climb.web.resource.BaseController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 设置系统的权限菜单
 */
@RestController
@RequestMapping("/api")
@Slf4j
@PreAuthorize(value = "hasAnyAuthority('ROLE_ADMIN', 'system:all', 'system:write')")
@Tag(name = "权限及菜单管理", description = "添加、编辑和删除权限菜单接口")
public class AuthorityManagementResource extends BaseController {

	private final AuthorityManagementService authorityManagementService;

	public AuthorityManagementResource(AuthorityManagementService authorityManagementService) {
		this.authorityManagementService = authorityManagementService;
	}


	@PostMapping("/authorities")
	@Operation(summary = "创建权限菜单")
	public ResponseEntity<SysAuthorityDTO> create(@Validated @RequestBody AuthorityOperatingRequestVO authorityOperatingRequestVO){
		SysAuthorityDTO sysAuthorityDTO = authorityManagementService.create(authorityOperatingRequestVO);
		return ResponseEntity.status(HttpStatus.CREATED).body(sysAuthorityDTO);
	}

	@PutMapping("/authorities")
	@Operation(summary = "修改权限菜单信息")
	public ResponseEntity<SysAuthorityDTO> update(@Validated @RequestBody AuthorityOperatingRequestVO authorityOperatingRequestVO){
		SysAuthorityDTO sysAuthorityDTO = authorityManagementService.update(authorityOperatingRequestVO);
		return ResponseEntity.status(HttpStatus.OK).body(sysAuthorityDTO);
	}

	@PatchMapping("/authorities")
	@Operation(summary = "修改权限菜单部分信息")
	public ResponseEntity<SysAuthorityDTO> partUpdate(@Validated @RequestBody AuthorityOperatingRequestVO authorityOperatingRequestVO){
		SysAuthorityDTO sysAuthorityDTO = authorityManagementService.partUpdate(authorityOperatingRequestVO);
		return ResponseEntity.status(HttpStatus.OK).body(sysAuthorityDTO);
	}

	@DeleteMapping("/authorities/{id}")
	@Operation(summary = "通过ID删除权限菜单")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		authorityManagementService.delete(id);
		return ResponseEntity.ok().build();
	}
}
