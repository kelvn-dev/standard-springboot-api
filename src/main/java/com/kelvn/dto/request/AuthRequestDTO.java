package com.kelvn.dto.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class AuthRequest {
  @NotNull @Email
  private String email;
  @NotNull private String password;
}
