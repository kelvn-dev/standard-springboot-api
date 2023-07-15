package com.kelvn.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthRequestDTO {
  @NotBlank @Email private String email;

  @NotBlank private String password;
}
