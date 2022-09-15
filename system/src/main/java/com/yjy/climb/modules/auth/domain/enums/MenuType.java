package com.yjy.climb.modules.auth.domain.enums;

public enum MenuType {

	MENU("菜单"), BUTTON("按钮"), API("接口");

	private String description;

	MenuType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
