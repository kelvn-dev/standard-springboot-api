package com.kelvn.dto.mapper;

import com.kelvn.controller.request.AccountRequest;
import com.kelvn.dto.AccountDto;

public class AccountDtoMapper {

  public AccountDto map(AccountRequest accountRequest) {
    return new AccountDto()
      .setUsername(accountRequest.getUsername())
      .setEmail(accountRequest.getEmail())
      .setPassword(accountRequest.getPassword());
  }

}
