package com.kelvn.service;

import com.kelvn.dto.request.GroupRequestDTO;
import com.kelvn.dto.response.GroupResponseDTO;
import com.kelvn.dto.response.extend.ExtGroupResponseDTO;
import com.kelvn.exception.NotFoundException;
import com.kelvn.model.Group;
import com.kelvn.repository.GroupRepository;
import com.kelvn.utils.MappingUtils;
import java.util.Objects;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class GroupService
    extends BaseService<Group, GroupRequestDTO, GroupResponseDTO, GroupRepository> {

  public GroupService(GroupRepository repository, MappingUtils mappingUtils) {
    super(repository, mappingUtils);
  }

  @Override
  public ExtGroupResponseDTO getById(UUID id, boolean noException) {
    Group model = repository.findById(id).orElse(null);
    if (Objects.isNull(model) && !noException) {
      throw new NotFoundException(modelClass, "id", id.toString());
    }
    return mappingUtils.mapToDTO(model, ExtGroupResponseDTO.class);
  }
}
