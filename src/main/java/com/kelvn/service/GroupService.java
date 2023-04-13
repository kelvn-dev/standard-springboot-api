package com.kelvn.service;

import com.kelvn.dto.response.GroupResponseDTO;
import com.kelvn.dto.request.GroupRequestDTO;
import com.kelvn.dto.response.GroupWithoutAccountDTO;
import com.kelvn.exception.NotFoundException;
import com.kelvn.model.Group;
import com.kelvn.repository.GroupRepository;
import com.kelvn.utils.MappingUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GroupService {

  private final GroupRepository groupRepository;
  private final MappingUtils mappingUtils;

  public GroupWithoutAccountDTO create(GroupRequestDTO groupRequestDTO) {
    Group group = mappingUtils.mapFromDTO(groupRequestDTO, Group.class);
//    group.updateRelations();
    return mappingUtils.mapToDTO(groupRepository.save(group), GroupWithoutAccountDTO.class);
  }

  public GroupResponseDTO getById(UUID id, boolean noExceptioon) {
    Group group = groupRepository.findById(id).orElse(null);
    if (group == null && !noExceptioon) {
      throw new NotFoundException(Group.class, "id", id.toString());
    }
    return mappingUtils.mapToDTO(group, GroupResponseDTO.class);
  }

  public GroupWithoutAccountDTO updateById(UUID id, GroupRequestDTO groupRequestDTO) {
    Group group = groupRepository.findById(id).orElse(null);
    if (group == null) {
      throw new NotFoundException(Group.class, "id", id.toString());
    }
    Group payload = mappingUtils.mapFromDTO(groupRequestDTO, Group.class);
    ModelMapper modelMapper = mappingUtils.getSimpleMapper();
    modelMapper.map(payload, group);
    return mappingUtils.mapToDTO(groupRepository.save(group), GroupWithoutAccountDTO.class);
  }

  public void deleteById(UUID id) {
    Group group = groupRepository.findById(id).orElse(null);
    if (group == null) {
      throw new NotFoundException(Group.class, "id", id.toString());
    }
    groupRepository.delete(group);
  }
}
