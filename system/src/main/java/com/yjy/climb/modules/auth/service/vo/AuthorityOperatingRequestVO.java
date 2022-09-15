package com.yjy.climb.modules.auth.service.vo;


import java.io.Serial;
import java.io.Serializable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.yjy.climb.modules.auth.domain.enums.AuthorityScope;
import com.yjy.climb.modules.auth.domain.enums.MenuType;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

/**
 * 菜单创建请求的bean
 */
@Data
@Builder
public class AuthorityOperatingRequestVO implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 权限应用范围，分系统权限和租户权限，系统权限由系统分配，租户权限可以由租户管理员分配
	 */
	@NotNull(message = "未设置权限范围")
	private AuthorityScope scope;

	/**
	 * 权限名称
	 */
	@Length(max = 32, message = "标题过长")
	@NotNull(message = "未设置权限标题")
	@NotBlank(message = "权限标题不能为空")
	@Pattern(regexp = "^[\\u4E00-\\u9FA5A-Za-z0-9_]+$", message = "标题仅支持汉字、字母和数字")
	private String title;

	/**
	 * 权限icon图标
	 */
	@Length(max = 24, message = "icon最大长度是24个字符")
	private String icon;


	private Long parentId;

	/**
	 * 权限菜单排序
	 */
	@Min(value = 0, message = "排序值必须大于0")
	private Integer menuSort;

	/**
	 * 菜单链接地址
	 */
	@Length(max = 256, message = "链接不要超过256个字符")
	private String url;

	/**
	 * 该菜单是否在新页面打开
	 */
	private Boolean blankTarget;

	/**
	 * 菜单类型
	 */
	private MenuType menuType;

	/**
	 * 是否显示，默认只有菜单是需要显示的
	 */
	private Boolean show;

	/**
	 * 权限标识符
	 */
	@Length(max = 32, message = "权限标识不要超过32个字符")
	@Pattern(regexp = "^[:A-Za-z0-9_]+$", message = "权限标识仅支持字母、数字、冒号和下划线")
	private String permission;

	/**
	 * 是否是默认跳转主页，如果是默认跳转主页，用户完成登录后会跳转至该页面
	 */
	private Boolean defaultRedirect;

	/**
	 * 默认跳转权重，如果存在多个默认跳转页面，则跳转到权重最高的页面
	 */
	private Integer redirectWeight;

}
