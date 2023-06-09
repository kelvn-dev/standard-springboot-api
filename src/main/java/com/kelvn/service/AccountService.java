package com.kelvn.service;

import com.kelvn.dto.external.response.MetaAccountResDTO;
import com.kelvn.dto.request.AccountRequestDTO;
import com.kelvn.dto.request.MetaAuthReqDTO;
import com.kelvn.dto.response.AccountResponseDTO;
import com.kelvn.dto.response.extend.ExtAccountResponseDTO;
import com.kelvn.exception.ConflictException;
import com.kelvn.exception.NotFoundException;
import com.kelvn.model.Account;
import com.kelvn.model.MetaAccount;
import com.kelvn.repository.AccountRepository;
import com.kelvn.repository.MetaAccountRepository;
import com.kelvn.service.external.MetaService;
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
  private final MetaAccountRepository metaAccountRepository;

  public AccountService(AccountRepository repository, MappingUtils mappingUtils, SendgridService sendgridService, PasswordEncoder passwordEncoder, MetaAccountRepository metaAccountRepository) {
    super(repository, mappingUtils);
    this.sendgridService = sendgridService;
    this.passwordEncoder = passwordEncoder;
    this.metaAccountRepository = metaAccountRepository;
  }

  @Override
  public ExtAccountResponseDTO getById(UUID id, boolean noException) {
    Account model = repository.findById(id).orElse(null);
    if (model == null && !noException) {
      throw new NotFoundException(modelClass, "id", id.toString());
    }
    return mappingUtils.mapToDTO(model, ExtAccountResponseDTO.class);
  }

  @Override
  public AccountResponseDTO create(AccountRequestDTO requestDTO) {
    if (repository.findByEmail(requestDTO.getEmail()).isPresent()) {
      throw new ConflictException(Account.class, "email", requestDTO.getEmail());
    }
    requestDTO.setPassword(passwordEncoder.encode(requestDTO.getPassword()));
    return super.create(requestDTO);
  }

  public AccountResponseDTO signup(AccountRequestDTO requestDTO) {
    AccountResponseDTO responseDTO = this.create(requestDTO);

    String token = UUID.randomUUID().toString(); // Need alternative approach
    String link = SERVER_URI.concat("/api/v1/webapp/verify?token=").concat(token);
    sendgridService.sendRegistrationEmail(requestDTO.getEmail(), requestDTO.getUsername(), link);

    return responseDTO;
  }

  public AccountResponseDTO signupWithMeta(MetaAuthReqDTO requestDTO) {
    MetaService metaService = new MetaService(requestDTO.getAccessToken());
    MetaAccountResDTO metaAccountResDTO = metaService.getProfile();
    MetaAccount metaAccount = metaAccountRepository.findByMetaAccountId(metaAccountResDTO.getId()).orElse(null);
    if (metaAccount != null) {
      throw new ConflictException(MetaAccount.class, "email", metaAccount.getEmail());
    }
    metaAccount = mappingUtils.mapFromDTO(metaAccountResDTO, MetaAccount.class);

    Account account = new Account();
    account.setEmail(metaAccount.getEmail());
    account.setUsername(metaAccount.getFirst_name().concat(metaAccount.getLast_name()));
    account.setPassword(passwordEncoder.encode(metaAccount.getMetaAccountId()));
    account.setMetaAccount(metaAccount);

//    String token = UUID.randomUUID().toString(); // Need alternative approach
//    String link = SERVER_URI.concat("/api/v1/webapp/verify?token=").concat(token);
//    sendgridService.sendRegistrationEmail(requestDTO.getEmail(), requestDTO.getUsername(), link);

    return super.create(account);
  }

}
