package com.kelvn.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AuthRequestDTO {
	@NotNull @Email private String email;

	@NotNull private String password;
}
