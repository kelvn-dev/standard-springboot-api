package com.kelvn.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.kelvn.dto.external.response.GoogleAccountResponseDTO;
import com.kelvn.dto.external.response.MetaAccountResponseDTO;
import com.kelvn.dto.request.AccountRequestDTO;
import com.kelvn.dto.request.GoogleAuthRequestDTO;
import com.kelvn.dto.request.MetaAuthRequestDTO;
import com.kelvn.dto.response.AccountResponseDTO;
import com.kelvn.dto.response.extend.ExtAccountResponseDTO;
import com.kelvn.exception.ConflictException;
import com.kelvn.exception.NotFoundException;
import com.kelvn.model.Account;
import com.kelvn.model.GoogleAccount;
import com.kelvn.model.MetaAccount;
import com.kelvn.repository.AccountRepository;
import com.kelvn.repository.GoogleAccountRepository;
import com.kelvn.repository.MetaAccountRepository;
import com.kelvn.service.external.GoogleService;
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
  private final GoogleAccountRepository googleAccountRepository;
  private final GoogleService googleService;

  public AccountService(AccountRepository repository, MappingUtils mappingUtils, SendgridService sendgridService, PasswordEncoder passwordEncoder, MetaAccountRepository metaAccountRepository, GoogleService googleService, GoogleAccountRepository googleAccountRepository) {
    super(repository, mappingUtils);
    this.sendgridService = sendgridService;
    this.passwordEncoder = passwordEncoder;
    this.metaAccountRepository = metaAccountRepository;
    this.googleAccountRepository = googleAccountRepository;
    this.googleService = googleService;
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

  public AccountResponseDTO signupWithMeta(MetaAuthRequestDTO requestDTO) {
    MetaService metaService = new MetaService(requestDTO.getAccessToken());
    MetaAccountResponseDTO metaAccountResponseDTO = metaService.getProfile();
    MetaAccount metaAccount = metaAccountRepository.findByMetaAccountId(metaAccountResponseDTO.getId()).orElse(null);
    if (metaAccount != null) {
      throw new ConflictException(MetaAccount.class, "email", metaAccount.getEmail());
    }
    metaAccount = mappingUtils.mapFromDTO(metaAccountResponseDTO, MetaAccount.class);

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

  public AccountResponseDTO signupWithGoogle(GoogleAuthRequestDTO requestDTO) {
    GoogleIdToken.Payload payload = googleService.verifyToken(requestDTO.getIdToken());
    GoogleAccountResponseDTO googleAccountResponseDTO = mappingUtils.mapToDTO(payload, GoogleAccountResponseDTO.class);
    System.out.println(payload.getSubject());
    System.out.println(googleAccountResponseDTO.getSub());
    System.out.println(googleAccountResponseDTO.getEmail());
    GoogleAccount googleAccount = googleAccountRepository.findBySub(googleAccountResponseDTO.getSub()).orElse(null);
    if (googleAccount != null) {
      throw new ConflictException(GoogleAccount.class, "email", googleAccount.getEmail());
    }
    googleAccount = mappingUtils.mapFromDTO(googleAccountResponseDTO, GoogleAccount.class);

    Account account = new Account();
    account.setEmail(googleAccount.getEmail());
    account.setUsername(googleAccount.getName());
    account.setPassword(passwordEncoder.encode(googleAccount.getSub()));
    account.setGoogleAccount(googleAccount);

//    String token = UUID.randomUUID().toString(); // Need alternative approach
//    String link = SERVER_URI.concat("/api/v1/webapp/verify?token=").concat(token);
//    sendgridService.sendRegistrationEmail(requestDTO.getEmail(), requestDTO.getUsername(), link);

    return super.create(account);
  }

}
