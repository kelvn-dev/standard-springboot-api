package com.kelvn.dto.request;

import com.kelvn.dto.BaseDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class AccountRequestDTO implements BaseDTO {

  private String username;
  private String email;
  private String password;
  private UUID groupId;

}
