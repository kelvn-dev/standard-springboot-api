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
public class MetaAccountResDTO implements BaseDTO {
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

  public PropertyMap<MetaAccountResDTO, MetaAccount> MetaAccountMap(MappingUtils utils) {
    return new PropertyMap<MetaAccountResDTO, MetaAccount>() {
      @Override
      protected void configure() {
        Converter<MetaAccountResDTO, String> mapMetaAccountId = new AbstractConverter<MetaAccountResDTO, String>() {
          @Override
          protected String convert(MetaAccountResDTO fbProfileResDTO) {
            return fbProfileResDTO.getId();
          }
        };

        Converter<MetaAccountResDTO, UUID> mapId = new AbstractConverter<MetaAccountResDTO, UUID>() {
          @Override
          protected UUID convert(MetaAccountResDTO fbProfileResDTO) {
            return null;
          }
        };

        using(mapId).map(source, destination.getId());
        using(mapMetaAccountId).map(source, destination.getMetaAccountId());
      }
    };
  }
}
