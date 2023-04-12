package com.kelvn.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.kelvn.dto.BaseDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class AccountWithoutGroupDTO implements BaseDTO {

  private UUID id;
  private String username;
  private String email;

}
