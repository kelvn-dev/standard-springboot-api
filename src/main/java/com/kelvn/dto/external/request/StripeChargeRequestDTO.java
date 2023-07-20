package com.kelvn.dto.external.request;

import com.kelvn.dto.BaseDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class StripeChargeRequestDTO {
  @NotBlank
  private String token;

  @NotNull
  @Min(0)
  private long amount;

  @NotBlank
  private String currency;
}
