package com.kelvn.controller.api;

import com.kelvn.dto.request.AccountRequestDTO;
import com.kelvn.dto.response.AccountResponseDTO;
import com.kelvn.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {

  private final AccountService accountService;

  @PostMapping()
  public ResponseEntity<AccountResponseDTO> create(@RequestBody AccountRequestDTO accountRequestDTO) {
    AccountResponseDTO accountResponseDTO = accountService.create(accountRequestDTO);
    return ResponseEntity.ok(accountResponseDTO);
  }

  @GetMapping("/{id}")
  public ResponseEntity<AccountResponseDTO> getById(@PathVariable UUID id) {
    AccountResponseDTO accountResponseDTO = accountService.getById(id);
    return ResponseEntity.ok(accountResponseDTO);
  }

  @PutMapping("/{id}")
  public ResponseEntity<AccountResponseDTO> updateById(@PathVariable UUID id, @RequestBody AccountRequestDTO accountRequestDTO) {
    AccountResponseDTO accountResponseDTO = accountService.updateById(id, accountRequestDTO);
    return ResponseEntity.ok(accountResponseDTO);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteById(@PathVariable UUID id) {
    accountService.deleteById(id);
    return ResponseEntity.ok(null);
  }

}
