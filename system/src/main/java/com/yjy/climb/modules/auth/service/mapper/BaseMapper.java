package com.yjy.climb.modules.auth.service.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;

import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;


/**
 * 基础Mapper
 * @param <D>
 * @param <E>
 */
public interface BaseMapper<D, E>{

	/**
	 * Entity转DTO
	 * @param e Entity
	 * @return DTO
	 */
	D toDto(E e);

	/**
	 * DTO转Entity
	 * @param d DTO
	 * @return Entity
	 */
	E toEntity(D d);

	/**
	 * 使用DTO更新Entity
	 * @param e Entity
	 * @param d DTO
	 * @return Entity
	 */
	E toEntity(D d,@MappingTarget E e);

	/**
	 * 使用DTO更新Entity
	 * @param e Entity
	 * @param d DTO
	 * @return DTO
	 */
	D toDto(E e, @MappingTarget D d);

	/**
	 * Entity转DTO，忽略null值
	 * @param e Entity
	 * @param d DTO
	 * @return DTO
	 */
	@BeanMapping(
			nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
			nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT
	)
	D partToDto(E e,@MappingTarget D d);

	/**
	 * 使用DTO更新Entity，忽略null值
	 * @param d DTO
	 * @param e Entity
	 * @return Entity
	 */
	@BeanMapping(
			nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
			nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT
	)
	E partToEntity(D d,@MappingTarget E e);

	List<D> toDto(List<E> es);

	List<E> toEntity(List<D> ds);
}
