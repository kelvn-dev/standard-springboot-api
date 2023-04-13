package com.kelvn.dto.response;

import com.kelvn.dto.BaseDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class AccountResponseDTO implements BaseDTO {

  private UUID id;
  private String username;
  private String email;

}
