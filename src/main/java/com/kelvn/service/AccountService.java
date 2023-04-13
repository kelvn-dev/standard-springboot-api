package com.kelvn.service;

import com.kelvn.dto.request.AccountRequestDTO;
import com.kelvn.dto.response.AccountResponseDTO;
import com.kelvn.exception.NotFoundException;
import com.kelvn.model.Account;
import com.kelvn.repository.AccountRepository;
import com.kelvn.utils.MappingUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountService {

  private final AccountRepository accountRepository;
  private final MappingUtils mappingUtils;

  public AccountResponseDTO create(AccountRequestDTO accountRequestDto) {
    Account account = mappingUtils.mapFromDTO(accountRequestDto, Account.class);
    return mappingUtils.mapToDTO(accountRepository.save(account), AccountResponseDTO.class);
  }

  public AccountResponseDTO getById(UUID id, boolean noException) {
    Account account = accountRepository.findById(id).orElse(null);
    if (account == null && !noException) {
      throw new NotFoundException(Account.class, "id", id.toString());
    }
    return mappingUtils.mapToDTO(account, AccountResponseDTO.class);
  }

  public AccountResponseDTO updateById(UUID id, AccountRequestDTO accountRequestDto) {
    Account account = accountRepository.findById(id).orElse(null);
    if (account == null) {
      throw new NotFoundException(Account.class, "id", id.toString());
    }
    Account payload = mappingUtils.mapFromDTO(accountRequestDto, Account.class);
    ModelMapper modelMapper = mappingUtils.getSimpleMapper();
    modelMapper.map(payload, account);
    return mappingUtils.mapToDTO(accountRepository.save(account), AccountResponseDTO.class);
  }

  public void deleteById(UUID id) {
    Account account = accountRepository.findById(id).orElse(null);
    if (account == null) {
      throw new NotFoundException(Account.class, "id", id.toString());
    }
    accountRepository.delete(account);
  }

}
