package com.kelvn.dto.response;

import com.kelvn.dto.request.AccountRequestDTO;
import com.kelvn.dto.BaseDTO;
import com.kelvn.model.Group;
import com.kelvn.utils.MappingUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class GroupResponseDTO implements BaseDTO {

  private UUID id;
  private String name;
  private List<AccountWithoutGroupDTO> accounts;

  @Override
  public ModelMapper updateModelMapper(ModelMapper mapper, MappingUtils utils) {
    mapper.addMappings(groupMap(utils));
    return mapper;
  }

  public PropertyMap<Group, GroupResponseDTO> groupMap(MappingUtils utils) {
    return new PropertyMap<Group, GroupResponseDTO>() {
      @Override
      protected void configure() {

        Converter<Group, List<AccountWithoutGroupDTO>> mapAccounts = new AbstractConverter<Group, List<AccountWithoutGroupDTO>>() {
          @Override
          protected List<AccountWithoutGroupDTO> convert(Group group) {
            return utils.mapListToDTO(group.getAccounts(), AccountWithoutGroupDTO.class);
          }
        };

        using(mapAccounts).map(source, destination.getAccounts());
      }
    };
  }

}
