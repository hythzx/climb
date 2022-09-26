package com.yjy.climb.modules.auth.service.dto;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

import com.yjy.climb.modules.auth.domain.enums.Gender;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class UserDTO extends BaseUserDTO implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	@Builder
	public UserDTO(Long id, String nickName, String headerImage, String login, String mobile, String email, LocalDate birthday, Gender gender) {
		super(id, nickName, headerImage, login, mobile, email, birthday, gender);
	}
}
