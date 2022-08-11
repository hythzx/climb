package com.yjy.climb.modules.auth.domain;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "sys_user")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
	private Boolean activated;

	@Basic
	@Column(name = "enabled")
	private Boolean enabled;


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


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		SysUser sysUser = (SysUser) o;

		if (id != sysUser.id) return false;
		if (nickName != null ? !nickName.equals(sysUser.nickName) : sysUser.nickName != null)
			return false;
		if (headerImage != null ? !headerImage.equals(sysUser.headerImage) : sysUser.headerImage != null)
			return false;
		if (login != null ? !login.equals(sysUser.login) : sysUser.login != null)
			return false;
		if (password != null ? !password.equals(sysUser.password) : sysUser.password != null)
			return false;
		if (mobile != null ? !mobile.equals(sysUser.mobile) : sysUser.mobile != null)
			return false;
		if (email != null ? !email.equals(sysUser.email) : sysUser.email != null)
			return false;
		if (birthday != null ? !birthday.equals(sysUser.birthday) : sysUser.birthday != null)
			return false;
		if (gender != null ? !gender.equals(sysUser.gender) : sysUser.gender != null)
			return false;
		if (activated != null ? !activated.equals(sysUser.activated) : sysUser.activated != null)
			return false;
		if (enabled != null ? !enabled.equals(sysUser.enabled) : sysUser.enabled != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + (nickName != null ? nickName.hashCode() : 0);
		result = 31 * result + (headerImage != null ? headerImage.hashCode() : 0);
		result = 31 * result + (login != null ? login.hashCode() : 0);
		result = 31 * result + (password != null ? password.hashCode() : 0);
		result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
		result = 31 * result + (email != null ? email.hashCode() : 0);
		result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
		result = 31 * result + (gender != null ? gender.hashCode() : 0);
		result = 31 * result + (activated != null ? activated.hashCode() : 0);
		result = 31 * result + (enabled != null ? enabled.hashCode() : 0);
		return result;
	}
}
