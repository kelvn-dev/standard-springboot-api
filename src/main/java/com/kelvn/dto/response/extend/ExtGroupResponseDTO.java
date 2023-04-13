package com.kelvn.dto.response.extend;

import com.kelvn.dto.response.GroupResponseDTO;
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

import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class ExtGroupResponseDTO extends GroupResponseDTO {

  private List<ExtAccountResponseDTO> accounts = new LinkedList<>();

  @Override
  public ModelMapper updateModelMapper(ModelMapper mapper, MappingUtils utils) {
    mapper.addMappings(groupMap(utils));
    return mapper;
  }

  public PropertyMap<Group, ExtGroupResponseDTO> groupMap(MappingUtils utils) {
    return new PropertyMap<Group, ExtGroupResponseDTO>() {
      @Override
      protected void configure() {

        Converter<Group, List<ExtAccountResponseDTO>> mapAccounts = new AbstractConverter<Group, List<ExtAccountResponseDTO>>() {
          @Override
          protected List<ExtAccountResponseDTO> convert(Group group) {
            return utils.mapListToDTO(group.getAccounts(), ExtAccountResponseDTO.class);
          }
        };

        using(mapAccounts).map(source, destination.getAccounts());
      }
    };
  }

}
