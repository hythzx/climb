package com.yjy.climb.modules.auth.domain;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "sys_user")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Audited
@EqualsAndHashCode(callSuper = false)
@ToString
public class SysUser extends AbstractAuditingEntity implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "id")
	private Long id;

	@Basic
	@Column(name = "nick_name", length = 32, nullable = false)
	@Length(max = 32, message = "昵称最多32个字")
	@NotNull(message = "昵称不能为空")
	@NotBlank(message = "昵称不能为空")
	@Pattern(regexp = "^[\\u4E00-\\u9FA5A-Za-z0-9_]+$", message = "昵称仅支持汉字、字母和数字,请勿留空格")
	private String nickName;

	@Basic
	@Column(name = "header_image", length = 256)
	@Length(max = 256, message = "头像地址过长")
	private String headerImage;

	@Basic
	@Column(name = "login", length = 32, nullable = false)
	@Length(max = 32, message = "登录名最多32个字符")
	@NotNull(message = "登录名不能为空")
	@NotBlank(message = "登录名不能为空")
	@Pattern(regexp = "^[A-Za-z0-9_]+$", message = "登录名仅支持字母和数字,请勿留空格")
	private String login;

	@Basic
	@Column(name = "password", length = 256, nullable = false)
	private String password;

	@Basic
	@Column(name = "mobile", length = 24)
	@Pattern(regexp = "^1[0-9]{10}", message = "请输入正确的手机号")
	private String mobile;

	@Basic
	@Column(name = "email")
	@Email(message = "请输入正确的邮箱")
	private String email;

	@Basic
	@Column(name = "birthday")
	private LocalDate birthday;

	@Basic
	@Column(name = "gender", length = 6)
	@Length(max = 6)
	private String gender;

	@Basic
	@Column(name = "activated")
	@JsonIgnore
	private Boolean activated;

	@Basic
	@Column(name = "enabled")
	@JsonIgnore
	private Boolean enabled;

	/**
	 * 用户对应的系统角色
	 */
	@JsonIgnore
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "sys_user_role",
			joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") },
			inverseJoinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "id") }
	)
	@BatchSize(size = 20)
	@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
	private Set<SysRole> sysRoles;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getHeaderImage() {
		return headerImage;
	}

	public void setHeaderImage(String headerImage) {
		this.headerImage = headerImage;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

	public Set<SysRole> getSysRoles() {
		return sysRoles;
	}

	public void setSysRoles(Set<SysRole> sysRoles) {
		this.sysRoles = sysRoles;
	}
}
