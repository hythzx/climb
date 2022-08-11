package com.yjy.climb.modules.auth.domain;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tenant_user")
public class TenantUser {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "id")
	private long id;

	@Basic
	@Column(name = "tenant_id")
	private long tenantId;

	@Basic
	@Column(name = "user_id")
	private long userId;

	@Basic
	@Column(name = "activated")
	private Boolean activated;

	@Basic
	@Column(name = "enabled")
	private Boolean enabled;

	@Basic
	@Column(name = "name")
	private String name;

	@Basic
	@Column(name = "birthday")
	private Date birthday;

	@Basic
	@Column(name = "header_image")
	private String headerImage;

	@Basic
	@Column(name = "mobile")
	private String mobile;

	@Basic
	@Column(name = "email")
	private String email;

	@Basic
	@Column(name = "address")
	private String address;

	@Basic
	@Column(name = "expired_date")
	private Timestamp expiredDate;

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
	@Column(name = "deleted")
	private Boolean deleted;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getTenantId() {
		return tenantId;
	}

	public void setTenantId(long tenantId) {
		this.tenantId = tenantId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public Boolean getActivated() {
		return activated;
	}

	public void setActivated(Boolean activated) {
		this.activated = activated;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getHeaderImage() {
		return headerImage;
	}

	public void setHeaderImage(String headerImage) {
		this.headerImage = headerImage;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Timestamp getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(Timestamp expiredDate) {
		this.expiredDate = expiredDate;
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

		TenantUser that = (TenantUser) o;

		if (id != that.id) return false;
		if (tenantId != that.tenantId) return false;
		if (userId != that.userId) return false;
		if (activated != null ? !activated.equals(that.activated) : that.activated != null)
			return false;
		if (enabled != null ? !enabled.equals(that.enabled) : that.enabled != null)
			return false;
		if (name != null ? !name.equals(that.name) : that.name != null) return false;
		if (birthday != null ? !birthday.equals(that.birthday) : that.birthday != null)
			return false;
		if (headerImage != null ? !headerImage.equals(that.headerImage) : that.headerImage != null)
			return false;
		if (mobile != null ? !mobile.equals(that.mobile) : that.mobile != null)
			return false;
		if (email != null ? !email.equals(that.email) : that.email != null) return false;
		if (address != null ? !address.equals(that.address) : that.address != null)
			return false;
		if (expiredDate != null ? !expiredDate.equals(that.expiredDate) : that.expiredDate != null)
			return false;
		if (createdBy != null ? !createdBy.equals(that.createdBy) : that.createdBy != null)
			return false;
		if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null)
			return false;
		if (lastModifiedBy != null ? !lastModifiedBy.equals(that.lastModifiedBy) : that.lastModifiedBy != null)
			return false;
		if (lastModifiedDate != null ? !lastModifiedDate.equals(that.lastModifiedDate) : that.lastModifiedDate != null)
			return false;
		if (deleted != null ? !deleted.equals(that.deleted) : that.deleted != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + (int) (tenantId ^ (tenantId >>> 32));
		result = 31 * result + (int) (userId ^ (userId >>> 32));
		result = 31 * result + (activated != null ? activated.hashCode() : 0);
		result = 31 * result + (enabled != null ? enabled.hashCode() : 0);
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
		result = 31 * result + (headerImage != null ? headerImage.hashCode() : 0);
		result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
		result = 31 * result + (email != null ? email.hashCode() : 0);
		result = 31 * result + (address != null ? address.hashCode() : 0);
		result = 31 * result + (expiredDate != null ? expiredDate.hashCode() : 0);
		result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
		result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
		result = 31 * result + (lastModifiedBy != null ? lastModifiedBy.hashCode() : 0);
		result = 31 * result + (lastModifiedDate != null ? lastModifiedDate.hashCode() : 0);
		result = 31 * result + (deleted != null ? deleted.hashCode() : 0);
		return result;
	}
}
