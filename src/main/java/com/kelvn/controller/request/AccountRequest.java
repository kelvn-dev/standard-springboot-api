package com.kelvn.controller.request;

import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class AccountRequest {
  private String username;
  private String email;
  private String password;
}
