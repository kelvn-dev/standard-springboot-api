package com.kelvn.service;

import com.kelvn.dto.response.GroupResponseDTO;
import com.kelvn.dto.request.GroupRequestDTO;
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

  public GroupResponseDTO create(GroupRequestDTO groupRequestDTO) {
    Group group = mappingUtils.mapFromDTO(groupRequestDTO, Group.class);
//    group.updateRelations();
    return mappingUtils.mapToDTO(groupRepository.save(group), GroupResponseDTO.class);
  }

  public GroupResponseDTO getById(UUID id) {
    Group group = groupRepository.findById(id).orElse(null);
    return mappingUtils.mapToDTO(group, GroupResponseDTO.class);
  }

  public GroupResponseDTO updateById(UUID id, GroupRequestDTO groupRequestDTO) {
    Group group = groupRepository.findById(id).orElse(null);
    Group payload = mappingUtils.mapFromDTO(groupRequestDTO, Group.class);
    ModelMapper modelMapper = mappingUtils.getSimpleMapper();
    modelMapper.map(payload, group);
    return mappingUtils.mapToDTO(groupRepository.save(group), GroupResponseDTO.class);
  }

  public void deleteById(UUID id) {
    groupRepository.deleteById(id);
  }
}
