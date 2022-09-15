package com.yjy.climb.modules.auth.domain;

import java.io.Serial;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "sys_tenant")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Audited
@EqualsAndHashCode(callSuper = false)
@ToString
public class Tenant extends AbstractAuditingEntity implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;


	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "id")
	private long id;

	@Basic
	@Column(name = "name", length = 128)
	@NotNull(message = "名称不可为空")
	@NotBlank(message = "名称不可为空")
	@Length(max = 128, message = "名称过长")
	@Pattern(regexp = "^[\\u4E00-\\u9FA5A-Za-z0-9]+$", message = "请勿输入特殊字符")
	private String name;

	@Basic
	@Column(name = "logo", length = 256)
	@Length(max = 256, message = "logo地址过长")
	private String logo;

	@Basic
	@Column(name = "contact", length = 24)
	@Length(max = 24, message = "名称过长")
	@Pattern(regexp = "^[\\u4E00-\\u9FA5]+$", message = "请勿输入特殊字符")
	private String contact;

	@Basic
	@Column(name = "activated")
	private Boolean activated;

	@Basic
	@Column(name = "enabled")
	private Boolean enabled;

	@Basic
	@Column(name = "address", length = 128)
	@Length(max = 128, message = "地址过长")
	@Pattern(regexp = "^[\\u4E00-\\u9FA5A-Za-z0-9]+$", message = "请勿输入特殊字符")
	private String address;

	@Basic
	@Column(name = "email", length = 128)
	@Email(message = "请输入正确的邮箱地址")
	private String email;

	@Basic
	@Column(name = "register_date")
	private Instant registerDate;

	/**
	 * 该企业的注册者
	 */
	@ManyToOne(optional = false)
	@JoinColumn(name = "register_user", referencedColumnName = "id")
	private SysUser registerUser;

	/**
	 * 用户数限制
	 */
	@Basic
	@Column(name = "user_limit")
	@Min(value = 1, message = "租户的用户限制必须大于1")
	private Long userLimit;

	/**
	 * 过期时间
	 */
	@Basic
	@Column(name = "expired_date")
	private LocalDate expiredDate;

	/**
	 * 企业所有者,此处仅存储用户ID，根据业务需要获取用户信息
	 */
	@Basic
	@Column(name = "tenant_owner")
	private Long tenantOwner;

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

	public Instant getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Instant registerDate) {
		this.registerDate = registerDate;
	}

	public SysUser getRegisterUser() {
		return registerUser;
	}

	public void setRegisterUser(SysUser registerUser) {
		this.registerUser = registerUser;
	}

	public Long getUserLimit() {
		return userLimit;
	}

	public void setUserLimit(Long userLimit) {
		this.userLimit = userLimit;
	}

	public LocalDate getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(LocalDate expiredDate) {
		this.expiredDate = expiredDate;
	}

	public Long getTenantOwner() {
		return tenantOwner;
	}

	public void setTenantOwner(Long tenantOwner) {
		this.tenantOwner = tenantOwner;
	}
}
