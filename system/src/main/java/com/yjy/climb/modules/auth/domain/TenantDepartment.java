package com.yjy.climb.modules.auth.domain;

import java.io.Serial;
import java.io.Serializable;

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
@Table(name = "tenant_department")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Audited
@EqualsAndHashCode(callSuper = false)
@ToString
public class TenantDepartment extends AbstractAuditingEntity implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "id")
	private long id;

	/**
	 * 上级部门ID，根级部门默认是-1
	 */
	@Basic
	@Column(name = "parent_id")
	private Long parentId;

	/**
	 * 部门名称
	 */
	@Basic
	@Column(name = "name", length = 64)
	@Length(max = 64, message = "部门名称请不要超过64个字符")
	@Pattern(regexp = "^[\\u4E00-\\u9FA5A-Za-z0-9_]+$", message = "请不要输入特殊字符")
	private String name;

	@Basic
	@Column(name = "icon", length = 32)
	@Length(max = 32, message = "ICON长度不超过32个字符")
	private String icon;

	/**
	 * 部门负责人
	 */
	@ManyToOne
	@JoinColumn(name = "leader", referencedColumnName = "id")
	private SysUser orgOwner;

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

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public SysUser getOrgOwner() {
		return orgOwner;
	}

	public void setOrgOwner(SysUser orgOwner) {
		this.orgOwner = orgOwner;
	}

	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}
}
