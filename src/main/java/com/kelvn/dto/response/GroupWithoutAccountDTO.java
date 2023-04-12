package com.kelvn.dto.response;

import com.kelvn.dto.BaseDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class GroupWithoutAccountDTO implements BaseDTO {

  private UUID id;
  private String name;

}
