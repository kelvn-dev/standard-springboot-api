package com.kelvn.dto.response;

import com.kelvn.dto.BaseDTO;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AccountResponseDTO implements BaseDTO {

  private UUID id;
  private String username;
  private String email;
}
