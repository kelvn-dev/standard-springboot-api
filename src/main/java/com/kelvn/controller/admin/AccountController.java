package com.kelvn.controller.admin;

import com.kelvn.controller.SecuredRestController;
import com.kelvn.dto.api.ApiPageableResponse;
import com.kelvn.dto.request.AccountRequestDTO;
import com.kelvn.dto.response.AccountResponseDTO;
import com.kelvn.exception.NotFoundException;
import com.kelvn.service.AccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.UUID;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Account")
@RestController
@RequestMapping("/v1/accounts")
@RequiredArgsConstructor
public class AccountController implements SecuredRestController {

  private final AccountService accountService;

  @PostMapping()
  public ResponseEntity<AccountResponseDTO> create(
      @RequestBody @Valid AccountRequestDTO accountRequestDTO) {
    AccountResponseDTO accountResponseDTO = accountService.create(accountRequestDTO);
    return ResponseEntity.ok(accountResponseDTO);
  }

  @GetMapping("/{id}")
  public ResponseEntity<AccountResponseDTO> getById(@PathVariable UUID id)
      throws NotFoundException {
    AccountResponseDTO accountResponseDTO = accountService.getById(id, false);
    return ResponseEntity.ok(accountResponseDTO);
  }

  @PutMapping("/{id}")
  public ResponseEntity<AccountResponseDTO> updateById(
      @PathVariable UUID id, @RequestBody @Valid AccountRequestDTO accountRequestDTO)
      throws NotFoundException {
    AccountResponseDTO accountResponseDTO = accountService.updateById(id, accountRequestDTO);
    return ResponseEntity.ok(accountResponseDTO);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteById(@PathVariable UUID id) throws NotFoundException {
    accountService.deleteById(id);
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
    return ResponseEntity.ok(accountService.getList(filter, pageable));
  }
}
