package com.kelvn.service;

import com.kelvn.dto.request.AccountRequestDTO;
import com.kelvn.dto.response.AccountResponseDTO;
import com.kelvn.dto.response.extend.ExtAccountResponseDTO;
import com.kelvn.exception.ConflictException;
import com.kelvn.exception.NotFoundException;
import com.kelvn.model.Account;
import com.kelvn.model.AccountEntityGraph;
import com.kelvn.repository.AccountRepository;
import com.kelvn.utils.MappingUtils;
import java.util.Objects;
import java.util.UUID;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = "account", keyGenerator = "redisKeyGenerator")
public class AccountService
    extends BaseService<Account, AccountRequestDTO, AccountResponseDTO, AccountRepository> {

  //  @Value("${server.uri}")
  //  private String SERVER_URI;

  private final PasswordEncoder passwordEncoder;

  //  private final MetaAccountRepository metaAccountRepository;
  //  private final GoogleAccountRepository googleAccountRepository;
  //  private final GoogleService googleService;

  public AccountService(
      AccountRepository repository, MappingUtils mappingUtils, PasswordEncoder passwordEncoder
      //      MetaAccountRepository metaAccountRepository,
      //      GoogleService googleService,
      //      GoogleAccountRepository googleAccountRepository
      ) {
    super(repository, mappingUtils);
    this.passwordEncoder = passwordEncoder;
    //    this.metaAccountRepository = metaAccountRepository;
    //    this.googleAccountRepository = googleAccountRepository;
    //    this.googleService = googleService;
  }

  @Override
  @Cacheable
  public ExtAccountResponseDTO getById(UUID id, boolean noException) {
    Account model =
        repository.findById(id, AccountEntityGraph.____().group().____.____()).orElse(null);
    if (Objects.isNull(model) && !noException) {
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
    return this.create(requestDTO);
  }

  //  @Transactional
  //  public AccountResponseDTO signupWithMeta(MetaAuthRequestDTO requestDTO) {
  //    MetaService metaService = new MetaService(requestDTO.getAccessToken());
  //    MetaAccountResponseDTO metaAccountResponseDTO = metaService.getProfile();
  //
  //    MetaAccount metaAccount =
  //        metaAccountRepository.findByMetaAccountId(metaAccountResponseDTO.getId()).orElse(null);
  //    if (metaAccount != null) {
  //      throw new ConflictException(MetaAccount.class, "email", metaAccount.getEmail());
  //    }
  //    metaAccount = mappingUtils.mapFromDTO(metaAccountResponseDTO, MetaAccount.class);
  //
  //    Account account = repository.findByEmail(metaAccount.getEmail()).orElse(null);
  //    if (Objects.isNull(account)) {
  //      account = new Account();
  //      account.setEmail(metaAccount.getEmail());
  //      account.setUsername(metaAccount.getFirst_name().concat(metaAccount.getLast_name()));
  //      account.setPassword(passwordEncoder.encode(metaAccount.getMetaAccountId()));
  //      account.setMetaAccount(metaAccount);
  //      String token = UUID.randomUUID().toString(); // Need alternative approach
  //      sendgridService.sendRegistrationConfirmation(
  //          account.getEmail(),
  //          account.getUsername(),
  //          metaAccount.getMetaAccountId(),
  //          Source.META.getLabel(),
  //          token);
  //      return super.create(account);
  //    }
  //    account.setMetaAccount(metaAccount);
  //    return mappingUtils.mapToDTO(account, AccountResponseDTO.class);
  //  }

  // @Transactional
  // public AccountResponseDTO signupWithGoogle(GoogleAuthRequestDTO requestDTO) {
  // GoogleIdToken.Payload payload =
  // googleService.verifyToken(requestDTO.getIdToken());
  // GoogleAccountResponseDTO googleAccountResponseDTO =
  // mappingUtils.mapToDTO(payload,
  // GoogleAccountResponseDTO.class);
  //
  // GoogleAccount googleAccount =
  // googleAccountRepository.findBySub(googleAccountResponseDTO.getSub()).orElse(null);
  // if (googleAccount != null) {
  // throw new ConflictException(GoogleAccount.class, "email",
  // googleAccount.getEmail());
  // }
  // googleAccount = mappingUtils.mapFromDTO(googleAccountResponseDTO,
  // GoogleAccount.class);
  //
  // Account account =
  // repository.findByEmail(googleAccount.getEmail()).orElse(null);
  // if (Objects.isNull(account) ) {
  // account = new Account();
  // account.setEmail(googleAccount.getEmail());
  // account.setUsername(googleAccount.getName());
  // account.setPassword(passwordEncoder.encode(googleAccount.getSub()));
  // account.setGoogleAccount(googleAccount);
  // String token = UUID.randomUUID().toString(); // Need alternative approach
  // sendgridService.sendEmailVerification(account.getEmail(),
  // account.getUsername(),
  // googleAccount.getSub(), Source.GOOGLE.getLabel(), token);
  // return super.create(account);
  // }
  // String token = UUID.randomUUID().toString(); // Need alternative approach
  // sendgridService.sendEmailVerification(account.getEmail(),
  // account.getUsername(),
  // googleAccount.getSub(), Source.GOOGLE.getLabel(), token);
  // account.setGoogleAccount(googleAccount);
  // return mappingUtils.mapToDTO(account, AccountResponseDTO.class);
  // }

}
