package com.kelvn.model.dto;

import com.kelvn.dto.AccountDto;
import com.kelvn.model.Account;

public class AccountMapper {

  public Account map(AccountDto accountDto) {
    return new Account()
      .setUsername(accountDto.getUsername())
      .setEmail(accountDto.getEmail())
      .setPassword(accountDto.getPassword());
  }
}
