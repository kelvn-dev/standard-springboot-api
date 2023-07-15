package com.kelvn.dto.request;

import com.kelvn.dto.BaseDTO;
import java.util.UUID;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class AccountRequestDTO implements BaseDTO {

  @NotBlank private String username;

  @NotBlank @Email private String email;

  @NotBlank private String password;

  private UUID groupId;
}
