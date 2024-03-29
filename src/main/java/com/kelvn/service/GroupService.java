package com.kelvn.service;

import com.kelvn.dto.request.GroupRequestDTO;
import com.kelvn.dto.response.GroupResponseDTO;
import com.kelvn.dto.response.extend.ExtGroupResponseDTO;
import com.kelvn.exception.NotFoundException;
import com.kelvn.model.AppGroup;
import com.kelvn.model.AppGroupEntityGraph;
import com.kelvn.repository.GroupRepository;
import com.kelvn.utils.MappingUtils;
import java.util.Objects;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class GroupService
    extends BaseService<AppGroup, GroupRequestDTO, GroupResponseDTO, GroupRepository> {

  public GroupService(GroupRepository repository, MappingUtils mappingUtils) {
    super(repository, mappingUtils);
  }

  @Override
  public ExtGroupResponseDTO getById(UUID id, boolean noException) {
    //    AppGroup model = repository.findById(id,
    // DynamicEntityGraph.loading().addPath("accounts").build()).orElse(null);
    AppGroup model =
        repository.findById(id, AppGroupEntityGraph.____().accounts().____.____()).orElse(null);
    if (Objects.isNull(model) && !noException) {
      throw new NotFoundException(modelClass, "id", id.toString());
    }
    return mappingUtils.mapToDTO(model, ExtGroupResponseDTO.class);
  }
}
