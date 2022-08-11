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
@Table(name = "sys_tenant")
public class SysTenant {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "id")
	private long id;

	@Basic
	@Column(name = "name")
	private String name;

	@Basic
	@Column(name = "logo")
	private String logo;

	@Basic
	@Column(name = "contact")
	private String contact;

	@Basic
	@Column(name = "activated")
	private Boolean activated;

	@Basic
	@Column(name = "enabled")
	private Boolean enabled;

	@Basic
	@Column(name = "address")
	private String address;

	@Basic
	@Column(name = "email")
	private String email;

	@Basic
	@Column(name = "register_date")
	private Timestamp registerDate;

	@Basic
	@Column(name = "register_user")
	private Long registerUser;

	@Basic
	@Column(name = "user_limit")
	private Long userLimit;

	@Basic
	@Column(name = "actived_users")
	private Long activedUsers;

	@Basic
	@Column(name = "expired_date")
	private Timestamp expiredDate;

	@Basic
	@Column(name = "tenant_owner")
	private Long tenantOwner;

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

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Timestamp getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Timestamp registerDate) {
		this.registerDate = registerDate;
	}

	public Long getRegisterUser() {
		return registerUser;
	}

	public void setRegisterUser(Long registerUser) {
		this.registerUser = registerUser;
	}

	public Long getUserLimit() {
		return userLimit;
	}

	public void setUserLimit(Long userLimit) {
		this.userLimit = userLimit;
	}

	public Long getActivedUsers() {
		return activedUsers;
	}

	public void setActivedUsers(Long activedUsers) {
		this.activedUsers = activedUsers;
	}

	public Timestamp getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(Timestamp expiredDate) {
		this.expiredDate = expiredDate;
	}

	public Long getTenantOwner() {
		return tenantOwner;
	}

	public void setTenantOwner(Long tenantOwner) {
		this.tenantOwner = tenantOwner;
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

		SysTenant sysTenant = (SysTenant) o;

		if (id != sysTenant.id) return false;
		if (name != null ? !name.equals(sysTenant.name) : sysTenant.name != null)
			return false;
		if (logo != null ? !logo.equals(sysTenant.logo) : sysTenant.logo != null)
			return false;
		if (contact != null ? !contact.equals(sysTenant.contact) : sysTenant.contact != null)
			return false;
		if (activated != null ? !activated.equals(sysTenant.activated) : sysTenant.activated != null)
			return false;
		if (enabled != null ? !enabled.equals(sysTenant.enabled) : sysTenant.enabled != null)
			return false;
		if (address != null ? !address.equals(sysTenant.address) : sysTenant.address != null)
			return false;
		if (email != null ? !email.equals(sysTenant.email) : sysTenant.email != null)
			return false;
		if (registerDate != null ? !registerDate.equals(sysTenant.registerDate) : sysTenant.registerDate != null)
			return false;
		if (registerUser != null ? !registerUser.equals(sysTenant.registerUser) : sysTenant.registerUser != null)
			return false;
		if (userLimit != null ? !userLimit.equals(sysTenant.userLimit) : sysTenant.userLimit != null)
			return false;
		if (activedUsers != null ? !activedUsers.equals(sysTenant.activedUsers) : sysTenant.activedUsers != null)
			return false;
		if (expiredDate != null ? !expiredDate.equals(sysTenant.expiredDate) : sysTenant.expiredDate != null)
			return false;
		if (tenantOwner != null ? !tenantOwner.equals(sysTenant.tenantOwner) : sysTenant.tenantOwner != null)
			return false;
		if (createdBy != null ? !createdBy.equals(sysTenant.createdBy) : sysTenant.createdBy != null)
			return false;
		if (createdDate != null ? !createdDate.equals(sysTenant.createdDate) : sysTenant.createdDate != null)
			return false;
		if (lastModifiedBy != null ? !lastModifiedBy.equals(sysTenant.lastModifiedBy) : sysTenant.lastModifiedBy != null)
			return false;
		if (lastModifiedDate != null ? !lastModifiedDate.equals(sysTenant.lastModifiedDate) : sysTenant.lastModifiedDate != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + (logo != null ? logo.hashCode() : 0);
		result = 31 * result + (contact != null ? contact.hashCode() : 0);
		result = 31 * result + (activated != null ? activated.hashCode() : 0);
		result = 31 * result + (enabled != null ? enabled.hashCode() : 0);
		result = 31 * result + (address != null ? address.hashCode() : 0);
		result = 31 * result + (email != null ? email.hashCode() : 0);
		result = 31 * result + (registerDate != null ? registerDate.hashCode() : 0);
		result = 31 * result + (registerUser != null ? registerUser.hashCode() : 0);
		result = 31 * result + (userLimit != null ? userLimit.hashCode() : 0);
		result = 31 * result + (activedUsers != null ? activedUsers.hashCode() : 0);
		result = 31 * result + (expiredDate != null ? expiredDate.hashCode() : 0);
		result = 31 * result + (tenantOwner != null ? tenantOwner.hashCode() : 0);
		result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
		result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
		result = 31 * result + (lastModifiedBy != null ? lastModifiedBy.hashCode() : 0);
		result = 31 * result + (lastModifiedDate != null ? lastModifiedDate.hashCode() : 0);
		return result;
	}
}
