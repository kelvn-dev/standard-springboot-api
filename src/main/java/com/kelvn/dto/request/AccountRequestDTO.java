package com.kelvn.dto.request;

import com.kelvn.dto.BaseDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.UUID;

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
