package com.kelvn.service;

import com.kelvn.dto.request.AccountRequestDTO;
import com.kelvn.dto.response.AccountResponseDTO;
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

  public AccountResponseDTO getById(UUID id) {
    Account account = accountRepository.findById(id).orElse(null);
    return mappingUtils.mapToDTO(account, AccountResponseDTO.class);
  }

  public AccountResponseDTO updateById(UUID id, AccountRequestDTO accountRequestDto) {
    Account payload = mappingUtils.mapFromDTO(accountRequestDto, Account.class);
    Account account = accountRepository.findById(id).orElse(null);
    ModelMapper modelMapper = mappingUtils.getSimpleMapper();
    modelMapper.map(payload, account);
    return mappingUtils.mapToDTO(accountRepository.save(account), AccountResponseDTO.class);
  }

  public void deleteById(UUID id) {
    accountRepository.deleteById(id);
  }

}
