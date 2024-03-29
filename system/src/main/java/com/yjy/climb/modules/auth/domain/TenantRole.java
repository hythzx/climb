package com.yjy.climb.modules.auth.domain;


import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "tenant_role")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Audited
@EqualsAndHashCode(callSuper = false)
@ToString
public class TenantRole extends AbstractAuditingEntity implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "id")
	private long id;

	/**
	 * 角色名称
	 */
	@Basic
	@Column(name = "name", length = 32)
	@Length(max = 32, message = "角色名称请不要超过32个字符")
	@Pattern(regexp = "^[\\u4E00-\\u9FA5A-Za-z0-9_]+$", message = "请不要输入特殊字符")
	private String name;

	/**
	 * 租户
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tenant_id", referencedColumnName = "id")
	private Tenant tenant;


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}
}
