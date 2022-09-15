package com.yjy.climb.modules.auth.repository;

import com.yjy.climb.modules.auth.domain.SysAuthority;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SysAuthorityRepository extends JpaRepository<SysAuthority, Long>, JpaSpecificationExecutor<SysAuthority> {
}
