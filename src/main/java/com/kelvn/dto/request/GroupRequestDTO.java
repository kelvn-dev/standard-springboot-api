package com.kelvn.dto.request;

import com.kelvn.dto.BaseDTO;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class GroupRequestDTO implements BaseDTO {
  @NotBlank private String name;

  // @Override
  // public ModelMapper updateModelMapper(ModelMapper mapper, MappingUtils utils)
  // {
  // mapper.addMappings(accountMapping(utils));
  // return mapper;
  // }

  // public PropertyMap<GroupRequestDTO, AppGroup> accountMapping(MappingUtils utils)
  // {
  // return new PropertyMap<GroupRequestDTO, AppGroup>() {
  // @Override
  // protected void configure() {
  //
  // Converter<GroupRequestDTO, List<Account>> mapAccounts = new
  // AbstractConverter<GroupRequestDTO, List<Account>>() {
  // @Override
  // protected List<Account> convert(GroupRequestDTO groupRequestDTO) {
  // return utils.mapListFromDTO(groupRequestDTO.getAccounts(), Account.class);
  // }
  // };
  //
  // using(mapAccounts).map(source, destination.getAccounts());
  // }
  // };
  // }

}
