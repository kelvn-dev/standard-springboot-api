package com.kelvn.dto;

import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class AccountDto {
  private String username;
  private String email;
  private String password;
}
