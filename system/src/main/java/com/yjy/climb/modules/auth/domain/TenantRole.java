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
@Table(name = "tenant_role")
public class TenantRole {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "id")
	private long id;

	@Basic
	@Column(name = "name")
	private String name;

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

	@Basic
	@Column(name = "tanant_id")
	private Long tanantId;

	@Basic
	@Column(name = "deleted")
	private Boolean deleted;

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

	public Long getTanantId() {
		return tanantId;
	}

	public void setTanantId(Long tanantId) {
		this.tanantId = tanantId;
	}

	public Boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		TenantRole that = (TenantRole) o;

		if (id != that.id) return false;
		if (name != null ? !name.equals(that.name) : that.name != null) return false;
		if (createdBy != null ? !createdBy.equals(that.createdBy) : that.createdBy != null)
			return false;
		if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null)
			return false;
		if (lastModifiedBy != null ? !lastModifiedBy.equals(that.lastModifiedBy) : that.lastModifiedBy != null)
			return false;
		if (lastModifiedDate != null ? !lastModifiedDate.equals(that.lastModifiedDate) : that.lastModifiedDate != null)
			return false;
		if (tanantId != null ? !tanantId.equals(that.tanantId) : that.tanantId != null)
			return false;
		if (deleted != null ? !deleted.equals(that.deleted) : that.deleted != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
		result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
		result = 31 * result + (lastModifiedBy != null ? lastModifiedBy.hashCode() : 0);
		result = 31 * result + (lastModifiedDate != null ? lastModifiedDate.hashCode() : 0);
		result = 31 * result + (tanantId != null ? tanantId.hashCode() : 0);
		result = 31 * result + (deleted != null ? deleted.hashCode() : 0);
		return result;
	}
}
