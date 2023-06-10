package com.kelvn.dto.external.response;

import com.kelvn.dto.BaseDTO;
import com.kelvn.model.GoogleAccount;
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
public class GoogleAccountResponseDTO implements BaseDTO {
  private String sub;
  private String name;
  private String email;
  private String email_verified;
  private String family_name;
  private String given_name;
  private String picture;

  @Override
  public ModelMapper updateModelMapper(ModelMapper mapper, MappingUtils utils) {
    mapper.addMappings(GoogleAccountMap(utils));
    return mapper;
  }

  public PropertyMap<GoogleAccountResponseDTO, GoogleAccount> GoogleAccountMap(MappingUtils utils) {
    return new PropertyMap<GoogleAccountResponseDTO, GoogleAccount>() {
      @Override
      protected void configure() {
        Converter<GoogleAccountResponseDTO, String> mapGoogleAccountId = new AbstractConverter<GoogleAccountResponseDTO, String>() {
          @Override
          protected String convert(GoogleAccountResponseDTO googleAccountResponseDTO) {
            return googleAccountResponseDTO.getSub();
          }
        };

        Converter<GoogleAccountResponseDTO, UUID> mapId = new AbstractConverter<GoogleAccountResponseDTO, UUID>() {
          @Override
          protected UUID convert(GoogleAccountResponseDTO fbProfileResDTO) {
            return null;
          }
        };

        using(mapId).map(source, destination.getId());
        using(mapGoogleAccountId).map(source, destination.getSub());
      }
    };
  }
}
