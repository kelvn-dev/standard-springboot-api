package com.kelvn.dto.external.response;

import com.kelvn.dto.BaseDTO;
import com.kelvn.model.MetaAccount;
import com.kelvn.utils.MappingUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class MetaAccountResponseDTO implements BaseDTO {
  private String id;
  private String name;
  private String email;
  private String first_name;
  private String last_name;

  @Override
  public ModelMapper updateModelMapper(ModelMapper mapper, MappingUtils utils) {
    mapper.addMappings(MetaAccountMap(utils));
    return mapper;
  }

  public PropertyMap<MetaAccountResponseDTO, MetaAccount> MetaAccountMap(MappingUtils utils) {
    return new PropertyMap<MetaAccountResponseDTO, MetaAccount>() {
      @Override
      protected void configure() {
        Converter<MetaAccountResponseDTO, String> mapMetaAccountId = new AbstractConverter<MetaAccountResponseDTO, String>() {
          @Override
          protected String convert(MetaAccountResponseDTO fbProfileResDTO) {
            return fbProfileResDTO.getId();
          }
        };

        Converter<MetaAccountResponseDTO, UUID> mapId = new AbstractConverter<MetaAccountResponseDTO, UUID>() {
          @Override
          protected UUID convert(MetaAccountResponseDTO fbProfileResDTO) {
            return null;
          }
        };

        using(mapId).map(source, destination.getId());
        using(mapMetaAccountId).map(source, destination.getMetaAccountId());
      }
    };
  }
}
