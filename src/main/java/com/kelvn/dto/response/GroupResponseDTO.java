package com.kelvn.dto.response;

import com.kelvn.dto.BaseDTO;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GroupResponseDTO implements BaseDTO {

	private UUID id;
	private String name;
}
