package com.kelvn.controller.admin;

import com.kelvn.controller.SecuredRestController;
import com.kelvn.dto.api.ApiPageableResponse;
import com.kelvn.dto.request.GroupRequestDTO;
import com.kelvn.dto.response.GroupResponseDTO;
import com.kelvn.dto.response.extend.ExtGroupResponseDTO;
import com.kelvn.exception.NotFoundException;
import com.kelvn.service.GroupService;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "AppGroup")
@RestController
@RequestMapping("/v1/groups")
@RequiredArgsConstructor
public class GroupController implements SecuredRestController {

  private final GroupService groupService;

  @PostMapping()
  public ResponseEntity<GroupResponseDTO> create(@RequestBody GroupRequestDTO groupRequestDTO) {
    GroupResponseDTO groupResponseDTO = groupService.create(groupRequestDTO);
    return ResponseEntity.ok(groupResponseDTO);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ExtGroupResponseDTO> getById(@PathVariable UUID id)
      throws NotFoundException {
    ExtGroupResponseDTO extGroupResponseDTO = groupService.getById(id, false);
    return ResponseEntity.ok(extGroupResponseDTO);
  }

  @PutMapping("/{id}")
  public ResponseEntity<GroupResponseDTO> updateById(
      @PathVariable UUID id, @RequestBody GroupRequestDTO groupRequestDTO)
      throws NotFoundException {
    GroupResponseDTO groupResponseDTO = groupService.updateById(id, groupRequestDTO);
    return ResponseEntity.ok(groupResponseDTO);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Object> deleteById(@PathVariable UUID id) {
    groupService.deleteById(id);
    return ResponseEntity.ok(null);
  }

  @GetMapping
  public ResponseEntity<ApiPageableResponse> getList(
      @PageableDefault(
              sort = {"createdAt"},
              direction = Sort.Direction.DESC)
          @ParameterObject
          Pageable pageable,
      @RequestParam(required = false) String[] filter) {
    return ResponseEntity.ok(groupService.getList(filter, pageable));
  }
}
