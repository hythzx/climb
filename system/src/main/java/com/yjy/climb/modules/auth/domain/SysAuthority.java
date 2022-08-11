package com.yjy.climb.modules.auth.domain;

import java.io.Serial;
import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.yjy.climb.modules.auth.domain.enums.AuthorityScope;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "sys_authority")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysAuthority extends AbstractAuditingEntity implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "id")
	private long id;

	@Basic
	@Column(name = "scope")
	@Enumerated(value = EnumType.STRING)
	@NotNull(message = "未设置系统权限")
	private AuthorityScope scope;

	@Basic
	@Column(name = "title")
	@Length(max = 32, message = "标题过长")
	@NotNull(message = "未设置权限标题")
	@NotBlank(message = "权限标题不能为空")
	@Pattern(regexp = "^[\\u4E00-\\u9FA5A-Za-z0-9_]+$", message = "标题仅支持汉字、字母和数字")
	private String title;

	@Basic
	@Column(name = "icon", length = 24)
	@Length(max = 24, message = "icon最大长度是24个字符")
	private String icon;

	@Basic
	@Column(name = "parent_id")
	private Long parentId;

	@Basic
	@Column(name = "menu_sort")
	private Integer menuSort;

	@Basic
	@Column(name = "url")
	private String url;

	@Basic
	@Column(name = "blank_target")
	private Boolean blankTarget;

	@Basic
	@Column(name = "menu_type")
	private String menuType;

	@Basic
	@Column(name = "is_show")
	private Boolean isShow;

	@Basic
	@Column(name = "permission")
	private String permission;

	@Basic
	@Column(name = "default_redirect")
	private Boolean defaultRedirect;

	@Basic
	@Column(name = "redirect_weight")
	private Integer redirectWeight;

	@Basic
	@Column(name = "enabled")
	private Boolean enabled;


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public AuthorityScope getScope() {
		return scope;
	}

	public void setScope(AuthorityScope scope) {
		this.scope = scope;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Integer getMenuSort() {
		return menuSort;
	}

	public void setMenuSort(Integer menuSort) {
		this.menuSort = menuSort;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Boolean getBlankTarget() {
		return blankTarget;
	}

	public void setBlankTarget(Boolean blankTarget) {
		this.blankTarget = blankTarget;
	}

	public String getMenuType() {
		return menuType;
	}

	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}

	public Boolean getShow() {
		return isShow;
	}

	public void setShow(Boolean show) {
		isShow = show;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public Boolean getDefaultRedirect() {
		return defaultRedirect;
	}

	public void setDefaultRedirect(Boolean defaultRedirect) {
		this.defaultRedirect = defaultRedirect;
	}

	public Integer getRedirectWeight() {
		return redirectWeight;
	}

	public void setRedirectWeight(Integer redirectWeight) {
		this.redirectWeight = redirectWeight;
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

		SysAuthority that = (SysAuthority) o;

		if (id != that.id) return false;
		if (scope != null ? !scope.equals(that.scope) : that.scope != null) return false;
		if (title != null ? !title.equals(that.title) : that.title != null) return false;
		if (icon != null ? !icon.equals(that.icon) : that.icon != null) return false;
		if (parentId != null ? !parentId.equals(that.parentId) : that.parentId != null)
			return false;
		if (menuSort != null ? !menuSort.equals(that.menuSort) : that.menuSort != null)
			return false;
		if (url != null ? !url.equals(that.url) : that.url != null) return false;
		if (blankTarget != null ? !blankTarget.equals(that.blankTarget) : that.blankTarget != null)
			return false;
		if (menuType != null ? !menuType.equals(that.menuType) : that.menuType != null)
			return false;
		if (isShow != null ? !isShow.equals(that.isShow) : that.isShow != null)
			return false;
		if (permission != null ? !permission.equals(that.permission) : that.permission != null)
			return false;
		if (defaultRedirect != null ? !defaultRedirect.equals(that.defaultRedirect) : that.defaultRedirect != null)
			return false;
		if (redirectWeight != null ? !redirectWeight.equals(that.redirectWeight) : that.redirectWeight != null)
			return false;
		if (enabled != null ? !enabled.equals(that.enabled) : that.enabled != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + (scope != null ? scope.hashCode() : 0);
		result = 31 * result + (title != null ? title.hashCode() : 0);
		result = 31 * result + (icon != null ? icon.hashCode() : 0);
		result = 31 * result + (parentId != null ? parentId.hashCode() : 0);
		result = 31 * result + (menuSort != null ? menuSort.hashCode() : 0);
		result = 31 * result + (url != null ? url.hashCode() : 0);
		result = 31 * result + (blankTarget != null ? blankTarget.hashCode() : 0);
		result = 31 * result + (menuType != null ? menuType.hashCode() : 0);
		result = 31 * result + (isShow != null ? isShow.hashCode() : 0);
		result = 31 * result + (permission != null ? permission.hashCode() : 0);
		result = 31 * result + (defaultRedirect != null ? defaultRedirect.hashCode() : 0);
		result = 31 * result + (redirectWeight != null ? redirectWeight.hashCode() : 0);
		result = 31 * result + (enabled != null ? enabled.hashCode() : 0);
		return result;
	}
}
