package com.kelvn.service;

import com.kelvn.dto.AccountDto;
import com.kelvn.model.Account;
import com.kelvn.model.dto.AccountMapper;
import com.kelvn.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService {

  private final AccountRepository accountRepository;

  public Account create(AccountDto accountDto) {
    Account account = new AccountMapper().map(accountDto);
    accountRepository.save(account);
    return account;
  }

  public Account getById(UUID id) {
    return accountRepository.findById(id).orElse(null);
  }

  public Account updateById(UUID id, AccountDto accountDto) {
    Account account = getById(id);
    if (account != null) {
      account = new AccountMapper().map(accountDto);
      account.setId(id);
      accountRepository.save(account);
    }
    return account;
  }

  public void deleteById(UUID id) {
    accountRepository.deleteById(id);
  }
}
