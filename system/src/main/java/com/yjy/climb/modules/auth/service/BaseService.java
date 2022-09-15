package com.yjy.climb.modules.auth.service;


import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import com.yjy.climb.config.ValidatorUtils;
import com.yjy.climb.exception.ErrorConstants.System;
import com.yjy.climb.exception.system.SystemException;
import com.yjy.climb.modules.auth.domain.AbstractAuditingEntity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 基础的Service
 */
public interface BaseService {

	/**
	 * 通过ID获取entity数据，如果不存在则抛出异常
	 * @author zhaoyuan 2022.07.25
	 * @param id 数据ID
	 * @param repository JPA Repository
	 * @return 返回Entity，如不存在则抛出SystemException异常
	 * @param <E> Entity
	 * @param <R> Repository
	 */
	default  <E extends AbstractAuditingEntity, R extends JpaRepository<E, Long>> E getEntityElseThrow(Long id, R repository){
		if (null == id){
			throw new SystemException(System.NOT_NULL.getErrorMsg(), System.NOT_NULL.getErrorCode());
		}
		Optional<E> optionalE = repository.findById(id);
		E entity = optionalE.orElseThrow(() -> new SystemException(
				System.IS_NOT_EXIST.getErrorMsg(),
				System.IS_NOT_EXIST.getErrorCode())
		);
		if (entity.isDeleted()) throw new SystemException(System.IS_NOT_EXIST.getErrorMsg(),
				System.IS_NOT_EXIST.getErrorCode());
		return entity;
	}

	/**
	 * 获取所有数据
	 */
	default <E extends AbstractAuditingEntity, R extends JpaRepository<E, Long>> List<E> findAll(R repository){
		return repository.findAll();
	}

	/**
	 * 获取分页数据
	 */
	default <E extends AbstractAuditingEntity, R extends JpaRepository<E, Long>> Page<E> findAll(R repository, Pageable pageable){
		return repository.findAll(pageable);
	}

	/**
	 * 通过ID获取Entity，返回Optional
	 * @author zhaoyuan 2022.07.25
	 * @param id  数据ID
	 * @param repository JPA Repository
	 * @return 返回Entity
	 * @param <E> Entity
	 * @param <R> Repository
	 */
	default  <E extends AbstractAuditingEntity, R extends JpaRepository<E, Long>> Optional<E> getEntity(Long id, R repository){
		Optional<E> entityOptional = repository.findById(id);
		if (entityOptional.isEmpty() || entityOptional.get().isDeleted()) return Optional.empty();
		return entityOptional;
	}

	/**
	 * 校验单个bean
	 * @param t JavaBean
	 * @param <T> JavaBean类型
	 */
	default <T> void validator(T t){
		Set<ConstraintViolation<T>> validate = ValidatorUtils.validator.validate(t);
		if (!validate.isEmpty()){
			throw new ConstraintViolationException(validate);
		}
	}

	/**
	 * 按照分组校验bean
	 * @param t JavaBean
	 * @param group 分组
	 * @param <T> JavaBean类型
	 */
	default  <T> void validator(T t, Class<?>... group){
		Set<ConstraintViolation<T>> validate = ValidatorUtils.validator.validate(t, group);
		if (!validate.isEmpty()){
			throw new ConstraintViolationException(validate);
		}
	}
}
