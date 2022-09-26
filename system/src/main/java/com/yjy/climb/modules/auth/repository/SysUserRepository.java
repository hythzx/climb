package com.yjy.climb.modules.auth.repository;

import java.util.Optional;

import com.yjy.climb.modules.auth.domain.SysUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserRepository extends JpaRepository<SysUser, Long>, JpaSpecificationExecutor<SysUser> {

	Optional<SysUser> findFirstByLogin(String login);

	Optional<SysUser> findFirstByMobile(String mobile);

	Optional<SysUser> findFirstByEmail(String email);
}
