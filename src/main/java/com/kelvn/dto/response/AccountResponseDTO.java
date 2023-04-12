package com.kelvn.dto.response;

import com.kelvn.dto.BaseDTO;
import com.kelvn.model.Group;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class AccountResponseDTO implements BaseDTO {

  private UUID id;
  private String username;
  private String email;
  private GroupWithoutAccountDTO group;

}
