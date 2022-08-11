package com.yjy.climb.modules.auth.domain.enums;

/**
 * 权限范围枚举，菜单权限分系统权限和租户权限，租户管理员只能分配租户权限给用户
 */
public enum AuthorityScope {

	SYSTEM("系统权限"), TENANT("租户权限");

	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	AuthorityScope(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "AuthorityScope{" +
				"description='" + description + '\'' +
				'}';
	}
}
