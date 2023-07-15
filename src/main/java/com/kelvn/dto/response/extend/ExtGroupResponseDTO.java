package com.kelvn.dto.response.extend;

import com.kelvn.dto.response.AccountResponseDTO;
import com.kelvn.dto.response.GroupResponseDTO;
import com.kelvn.model.AppGroup;
import com.kelvn.utils.MappingUtils;
import java.util.LinkedList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class ExtGroupResponseDTO extends GroupResponseDTO {

  private List<AccountResponseDTO> accounts = new LinkedList<>();

  @Override
  public ModelMapper updateModelMapper(ModelMapper mapper, MappingUtils utils) {
    mapper.addMappings(groupMap(utils));
    return mapper;
  }

  public PropertyMap<AppGroup, ExtGroupResponseDTO> groupMap(MappingUtils utils) {
    return new PropertyMap<AppGroup, ExtGroupResponseDTO>() {
      @Override
      protected void configure() {

        Converter<AppGroup, List<AccountResponseDTO>> mapAccounts =
            new AbstractConverter<AppGroup, List<AccountResponseDTO>>() {
              @Override
              protected List<AccountResponseDTO> convert(AppGroup group) {
                return utils.mapListToDTO(group.getAccounts(), AccountResponseDTO.class);
              }
            };

        using(mapAccounts).map(source, destination.getAccounts());
      }
    };
  }
}
