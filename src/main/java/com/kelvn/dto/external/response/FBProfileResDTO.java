package com.kelvn.dto.external.response;

import com.kelvn.dto.BaseDTO;
import com.kelvn.model.FBAccount;
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
public class FBProfileResDTO implements BaseDTO {
  private String id;
  private String name;
  private String email;
  private String first_name;
  private String last_name;

  @Override
  public ModelMapper updateModelMapper(ModelMapper mapper, MappingUtils utils) {
    mapper.addMappings(FBAccountMap(utils));
    return mapper;
  }

  public PropertyMap<FBProfileResDTO, FBAccount> FBAccountMap(MappingUtils utils) {
    return new PropertyMap<FBProfileResDTO, FBAccount>() {
      @Override
      protected void configure() {
        Converter<FBProfileResDTO, String> mapFBAccountId = new AbstractConverter<FBProfileResDTO, String>() {
          @Override
          protected String convert(FBProfileResDTO fbProfileResDTO) {
            return fbProfileResDTO.getId();
          }
        };

        Converter<FBProfileResDTO, UUID> mapId = new AbstractConverter<FBProfileResDTO, UUID>() {
          @Override
          protected UUID convert(FBProfileResDTO fbProfileResDTO) {
            return null;
          }
        };

        using(mapId).map(source, destination.getId());
        using(mapFBAccountId).map(source, destination.getFBAccountId());
      }
    };
  }
}
