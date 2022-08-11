package com.yjy.climb.modules.auth.domain;

import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sys_role")
public class SysRole {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "id")
	private long id;

	@Basic
	@Column(name = "name")
	private String name;

	@Basic
	@Column(name = "role_code")
	private String roleCode;

	@Basic
	@Column(name = "enabled")
	private Boolean enabled;

	@Basic
	@Column(name = "deleted")
	private Boolean deleted;

	@Basic
	@Column(name = "created_by")
	private String createdBy;

	@Basic
	@Column(name = "created_date")
	private Timestamp createdDate;

	@Basic
	@Column(name = "last_modified_by")
	private String lastModifiedBy;

	@Basic
	@Column(name = "last_modified_date")
	private Timestamp lastModifiedDate;

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

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	public void setLastModifiedBy(String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	public Timestamp getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Timestamp lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		SysRole sysRole = (SysRole) o;

		if (id != sysRole.id) return false;
		if (name != null ? !name.equals(sysRole.name) : sysRole.name != null)
			return false;
		if (roleCode != null ? !roleCode.equals(sysRole.roleCode) : sysRole.roleCode != null)
			return false;
		if (enabled != null ? !enabled.equals(sysRole.enabled) : sysRole.enabled != null)
			return false;
		if (deleted != null ? !deleted.equals(sysRole.deleted) : sysRole.deleted != null)
			return false;
		if (createdBy != null ? !createdBy.equals(sysRole.createdBy) : sysRole.createdBy != null)
			return false;
		if (createdDate != null ? !createdDate.equals(sysRole.createdDate) : sysRole.createdDate != null)
			return false;
		if (lastModifiedBy != null ? !lastModifiedBy.equals(sysRole.lastModifiedBy) : sysRole.lastModifiedBy != null)
			return false;
		if (lastModifiedDate != null ? !lastModifiedDate.equals(sysRole.lastModifiedDate) : sysRole.lastModifiedDate != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (roleCode != null ? roleCode.hashCode() : 0);
		result = 31 * result + (enabled != null ? enabled.hashCode() : 0);
		result = 31 * result + (deleted != null ? deleted.hashCode() : 0);
		result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
		result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
		result = 31 * result + (lastModifiedBy != null ? lastModifiedBy.hashCode() : 0);
		result = 31 * result + (lastModifiedDate != null ? lastModifiedDate.hashCode() : 0);
		return result;
	}
}
