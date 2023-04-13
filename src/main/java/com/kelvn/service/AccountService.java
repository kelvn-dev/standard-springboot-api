package com.kelvn.service;

import com.kelvn.dto.request.AccountRequestDTO;
import com.kelvn.dto.response.AccountResponseDTO;
import com.kelvn.dto.response.extend.ExtAccountResponseDTO;
import com.kelvn.exception.NotFoundException;
import com.kelvn.model.Account;
import com.kelvn.repository.AccountRepository;
import com.kelvn.utils.MappingUtils;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AccountService extends BaseService<Account, AccountRequestDTO, AccountResponseDTO, AccountRepository> {

  public AccountService(AccountRepository repository, MappingUtils mappingUtils) {
    super(repository, mappingUtils);
  }

  @Override
  public ExtAccountResponseDTO getById(UUID id, boolean noException) {
    Account model = repository.findById(id).orElse(null);
    if (model == null && !noException) {
      throw new NotFoundException(modelClass, "id", id.toString());
    }
    return mappingUtils.mapToDTO(model, ExtAccountResponseDTO.class);
  }
}
