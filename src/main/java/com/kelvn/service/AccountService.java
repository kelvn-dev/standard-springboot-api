package com.kelvn.service;

import com.kelvn.dto.request.AccountRequestDTO;
import com.kelvn.dto.response.AccountResponseDTO;
import com.kelvn.dto.response.extend.ExtAccountResponseDTO;
import com.kelvn.exception.NotFoundException;
import com.kelvn.model.Account;
import com.kelvn.repository.AccountRepository;
import com.kelvn.service.external.SendgridService;
import com.kelvn.utils.MappingUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AccountService extends BaseService<Account, AccountRequestDTO, AccountResponseDTO, AccountRepository> {

  @Value("${server.uri}")
  private String SERVER_URI;

  private final SendgridService sendgridService;

  public AccountService(AccountRepository repository, MappingUtils mappingUtils, SendgridService sendgridService) {
    super(repository, mappingUtils);
    this.sendgridService = sendgridService;
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
     Account account = mappingUtils.mapFromDTO(requestDTO, Account.class);
     account = repository.save(account);

    String token = UUID.randomUUID().toString(); // Need alternative approach
    String link = SERVER_URI.concat("/api/v1/webapp/verify?token=").concat(token);
    sendgridService.sendRegistrationEmail(account.getEmail(), account.getUsername(), link);

     return mappingUtils.mapToDTO(account, AccountResponseDTO.class);
  }
}
