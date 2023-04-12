package com.kelvn.controller.api;

import com.kelvn.controller.request.AccountRequest;
import com.kelvn.dto.AccountDto;
import com.kelvn.dto.mapper.AccountDtoMapper;
import com.kelvn.model.Account;
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
  public ResponseEntity<Account> create(@RequestBody AccountRequest accountRequest) {
    AccountDto accountDto = new AccountDtoMapper().map(accountRequest);
    Account account = accountService.create(accountDto);
    return ResponseEntity.ok(account);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Account> getById(@PathVariable UUID id) {
    Account account = accountService.getById(id);
    return ResponseEntity.ok(account);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Account> updateById(@PathVariable UUID id, @RequestBody AccountRequest accountRequest) {
    AccountDto accountDto = new AccountDtoMapper().map(accountRequest);
    Account account = accountService.updateById(id, accountDto);
    return ResponseEntity.ok(account);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Account> deleteById(@PathVariable UUID id) {
    accountService.deleteById(id);
    return ResponseEntity.ok(null);
  }

}
