package com.kelvn.controller.api;

import com.kelvn.dto.response.GroupResponseDTO;
import com.kelvn.dto.request.GroupRequestDTO;
import com.kelvn.dto.response.GroupWithoutAccountDTO;
import com.kelvn.exception.NotFoundException;
import com.kelvn.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/groups")
@RequiredArgsConstructor
public class GroupController {

  private final GroupService groupService;

  @PostMapping()
  public ResponseEntity<GroupWithoutAccountDTO> create(@RequestBody GroupRequestDTO groupRequestDTO) {
    GroupWithoutAccountDTO groupResponseDTO = groupService.create(groupRequestDTO);
    return ResponseEntity.ok(groupResponseDTO);
  }

  @GetMapping("/{id}")
  public ResponseEntity<GroupResponseDTO> getById(@PathVariable UUID id) throws NotFoundException {
    GroupResponseDTO groupResponseDTO = groupService.getById(id, false);
    return ResponseEntity.ok(groupResponseDTO);
  }

  @PutMapping("/{id}")
  public ResponseEntity<GroupWithoutAccountDTO> updateById(
    @PathVariable UUID id,
    @RequestBody GroupRequestDTO groupRequestDTO
  ) throws NotFoundException {
    GroupWithoutAccountDTO groupResponseDTO = groupService.updateById(id, groupRequestDTO);
    return ResponseEntity.ok(groupResponseDTO);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteById(@PathVariable UUID id) {
    groupService.deleteById(id);
    return ResponseEntity.ok(null);
  }

}
