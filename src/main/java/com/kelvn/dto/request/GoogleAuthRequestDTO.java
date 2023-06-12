package com.kelvn.dto.request;

import com.kelvn.dto.BaseDTO;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class GoogleAuthRequestDTO implements BaseDTO {
	@NotNull private String idToken;
}
