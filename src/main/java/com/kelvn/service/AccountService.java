package com.kelvn.service;

import com.kelvn.dto.request.AccountRequestDTO;
import com.kelvn.dto.response.AccountResponseDTO;
import com.kelvn.dto.response.extend.ExtAccountResponseDTO;
import com.kelvn.exception.ConflictException;
import com.kelvn.exception.NotFoundException;
import com.kelvn.model.Account;
import com.kelvn.repository.AccountRepository;
import com.kelvn.service.external.SendgridService;
import com.kelvn.utils.MappingUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AccountService extends BaseService<Account, AccountRequestDTO, AccountResponseDTO, AccountRepository> {

  @Value("${server.uri}")
  private String SERVER_URI;

  private final SendgridService sendgridService;
  private final PasswordEncoder passwordEncoder;

  public AccountService(AccountRepository repository, MappingUtils mappingUtils, SendgridService sendgridService, PasswordEncoder passwordEncoder) {
    super(repository, mappingUtils);
    this.sendgridService = sendgridService;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public ExtAccountResponseDTO getById(UUID id, boolean noException) {
    Account model = repository.findById(id).orElse(null);
    if (model == null && !noException) {
      throw new NotFoundException(modelClass, "id", id.toString());
    }
    return mappingUtils.mapToDTO(model, ExtAccountResponseDTO.class);
  }

  public AccountResponseDTO signup(AccountRequestDTO requestDTO) {
    if (repository.findByEmail(requestDTO.getEmail()).isPresent()) {
      throw new ConflictException(Account.class, "email", requestDTO.getEmail());
    }
     Account account = mappingUtils.mapFromDTO(requestDTO, Account.class);
    account.setPassword(passwordEncoder.encode(account.getPassword()));
     account = repository.save(account);

    String token = UUID.randomUUID().toString(); // Need alternative approach
    String link = SERVER_URI.concat("/api/v1/webapp/verify?token=").concat(token);
    sendgridService.sendRegistrationEmail(account.getEmail(), account.getUsername(), link);
     return mappingUtils.mapToDTO(account, AccountResponseDTO.class);
  }

}
