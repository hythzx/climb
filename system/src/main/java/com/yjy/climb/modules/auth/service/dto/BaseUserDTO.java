package com.yjy.climb.modules.auth.service.dto;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.yjy.climb.modules.auth.domain.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@AllArgsConstructor
public class BaseUserDTO implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	private Long id;

	@Length(max = 32, message = "昵称最多32个字")
	@NotNull(message = "昵称不能为空")
	@NotBlank(message = "昵称不能为空")
	@Pattern(regexp = "^[\\u4E00-\\u9FA5A-Za-z0-9_]+$", message = "昵称仅支持汉字、字母和数字,请勿留空格")
	private String nickName;

	@Length(max = 256, message = "头像地址过长")
	private String headerImage;

	@Length(max = 32, message = "登录名最多32个字符")
	@NotNull(message = "登录名不能为空")
	@NotBlank(message = "登录名不能为空")
	@Pattern(regexp = "^[A-Za-z0-9_]+$", message = "登录名仅支持字母和数字,请勿留空格")
	private String login;

	@Pattern(regexp = "^1[0-9]{10}", message = "请输入正确的手机号")
	private String mobile;

	@Email(message = "请输入正确的邮箱")
	private String email;

	private LocalDate birthday;

	private Gender gender;
}
