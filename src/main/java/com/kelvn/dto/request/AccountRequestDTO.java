package com.kelvn.dto.request;

import com.kelvn.dto.BaseDTO;
import java.util.UUID;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class AccountRequestDTO implements BaseDTO {

	@NotNull private String username;

	@NotNull @Email private String email;

	@NotNull private String password;

	private UUID groupId;
}
