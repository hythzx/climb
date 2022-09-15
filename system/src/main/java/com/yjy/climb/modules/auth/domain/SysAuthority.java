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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.yjy.climb.modules.auth.domain.enums.AuthorityScope;
import com.yjy.climb.modules.auth.domain.enums.MenuType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Length;

/**
 * 权限表，可以存在菜单、按钮和权限标识
 */
@Entity
@Table(name = "sys_authority")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Audited
@EqualsAndHashCode(callSuper = false)
@ToString
public class SysAuthority extends AbstractAuditingEntity implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name = "id")
	private Long id;

	/**
	 * 权限应用范围，分系统权限和租户权限，系统权限由系统分配，租户权限可以由租户管理员分配
	 */
	@Basic
	@Column(name = "scope")
	@Enumerated(value = EnumType.STRING)
	@NotNull(message = "未设置权限范围")
	private AuthorityScope scope;

	/**
	 * 权限名称
	 */
	@Basic
	@Column(name = "title")
	@Length(max = 32, message = "标题过长")
	@NotNull(message = "未设置权限标题")
	@NotBlank(message = "权限标题不能为空")
	@Pattern(regexp = "^[\\u4E00-\\u9FA5A-Za-z0-9_]+$", message = "标题仅支持汉字、字母和数字")
	private String title;

	/**
	 * 权限icon图标
	 */
	@Basic
	@Column(name = "icon", length = 24)
	@Length(max = 24, message = "icon最大长度是24个字符")
	private String icon;

	/**
	 * 权限的父级ID，为了性能考虑，此处不使用ManyToOne形式关联
	 */
	@Basic
	@Column(name = "parent_id")
	private Long parentId;

	/**
	 * 权限菜单排序
	 */
	@Basic
	@Column(name = "menu_sort")
	@Min(value = 0, message = "排序值必须大于0")
	private Integer menuSort;

	/**
	 * 菜单链接地址
	 */
	@Basic
	@Column(name = "url", length = 256)
	@Length(max = 256, message = "链接不要超过256个字符")
	private String url;

	/**
	 * 该菜单是否在新页面打开
	 */
	@Basic
	@Column(name = "blank_target")
	private Boolean blankTarget;

	/**
	 * 菜单类型
	 */
	@Basic
	@Column(name = "menu_type")
	@Enumerated(value = EnumType.STRING)
	private MenuType menuType;

	/**
	 * 是否显示，默认只有菜单是需要显示的
	 */
	@Basic
	@Column(name = "is_show")
	private Boolean show;

	/**
	 * 权限标识符
	 */
	@Basic
	@Column(name = "permission", length = 32)
	@Length(max = 32, message = "权限标识不要超过32个字符")
	@Pattern(regexp = "^[:A-Za-z0-9_]+$", message = "权限标识仅支持字母、数字、冒号和下划线")
	private String permission;

	/**
	 * 是否是默认跳转主页，如果是默认跳转主页，用户完成登录后会跳转至该页面
	 */
	@Basic
	@Column(name = "default_redirect")
	private Boolean defaultRedirect;

	/**
	 * 默认跳转权重，如果存在多个默认跳转页面，则跳转到权重最高的页面
	 */
	@Basic
	@Column(name = "redirect_weight")
	private Integer redirectWeight;

	/**
	 * 是否启用
	 */
	@Basic
	@Column(name = "enabled")
	private Boolean enabled;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public MenuType getMenuType() {
		return menuType;
	}

	public void setMenuType(MenuType menuType) {
		this.menuType = menuType;
	}

	public Boolean getShow() {
		return show;
	}

	public void setShow(Boolean show) {
		this.show = show;
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
}
