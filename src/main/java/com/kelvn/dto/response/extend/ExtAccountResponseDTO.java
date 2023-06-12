package com.kelvn.dto.response.extend;

import com.kelvn.dto.response.AccountResponseDTO;
import com.kelvn.dto.response.GroupResponseDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ExtAccountResponseDTO extends AccountResponseDTO {

	private GroupResponseDTO group;
}
