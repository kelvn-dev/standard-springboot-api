package com.kelvn.dto.request;

import com.kelvn.dto.BaseDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class FacebookAuthRequestDTO implements BaseDTO {
  @NotNull
  private String accessToken;
}
