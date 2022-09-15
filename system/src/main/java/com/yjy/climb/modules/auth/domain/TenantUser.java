package com.yjy.climb.modules.auth.domain;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

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
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;

/**
 * 租户用户表，即用户在该租户下的信息
 */
@Entity
@Table(name = "tenant_user")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Audited
@EqualsAndHashCode(callSuper = false)
@ToString
public class TenantUser extends AbstractAuditingEntity implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "id")
	private long id;

	/**
	 * 职务
	 */
	@Basic
	@Column(name = "job", length = 32)
	@Length(max = 32, message = "职务最大支持32个字符")
	@Pattern(regexp = "^[\\u4E00-\\u9FA5A-Za-z0-9]+$", message = "请勿输入特殊字符")
	private String job;

	/**
	 * 工号
	 */
	@Basic
	@Column(name = "job_no", length = 32)
	@Length(max = 32, message = "工号最大支持32个字符")
	@Pattern(regexp = "^[A-Za-z0-9]+$", message = "请勿输入特殊字符")
	private String jobNo;

	/**
	 * 入职时间
	 */
	@Basic
	@Column(name = "entry_date")
	private LocalDate entryDate;

	/**
	 * 是否激活
	 */
	@Basic
	@Column(name = "activated")
	private Boolean activated;

	/**
	 * 是否启用
	 */
	@Basic
	@Column(name = "enabled")
	private Boolean enabled;

	/**
	 * 员工姓名
	 */
	@Basic
	@Column(name = "name", length = 32)
	@Length(max = 32, message = "员工姓名最大支持32个字符")
	@Pattern(regexp = "^[\\u4E00-\\u9FA5A-Za-z0-9]+$", message = "员工姓名请勿输入特殊字符")
	private String name;

	/**
	 * 生日
	 */
	@Basic
	@Column(name = "birthday")
	private LocalDate birthday;

	/**
	 * 头像
	 */
	@Basic
	@Column(name = "header_image", length = 256)
	@Length(max = 256, message = "头像地址过长")
	private String headerImage;

	/**
	 * 手机号
	 */
	@Basic
	@Column(name = "mobile", length = 24)
	@Pattern(regexp = "^1[0-9]{10}", message = "请输入正确的手机号")
	private String mobile;

	/**
	 * 电子邮箱
	 */
	@Basic
	@Column(name = "email", length = 64)
	@Length(max = 64, message = "邮箱长度最大支持64个字符")
	@Email(message = "请输入正确的邮箱地址")
	private String email;

	/**
	 * 过期时间
	 */
	@Basic
	@Column(name = "expired_date")
	private LocalDate expiredDate;

	/**
	 * 租户
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tenant_id", referencedColumnName = "id")
	private Tenant tenant;

	/**
	 * 该租户员工对应的系统用户
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private SysUser sysUser;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getJobNo() {
		return jobNo;
	}

	public void setJobNo(String jobNo) {
		this.jobNo = jobNo;
	}

	public LocalDate getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(LocalDate entryDate) {
		this.entryDate = entryDate;
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

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
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

	public LocalDate getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(LocalDate expiredDate) {
		this.expiredDate = expiredDate;
	}

	public Tenant getTenant() {
		return tenant;
	}

	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}
}
