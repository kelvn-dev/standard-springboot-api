package com.kelvn.service;

import com.kelvn.dto.BaseDTO;
import com.kelvn.dto.response.AccountResponseDTO;
import com.kelvn.exception.NotFoundException;
import com.kelvn.model.Account;
import com.kelvn.model.BaseModel;
import com.kelvn.utils.MappingUtils;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.lang.reflect.ParameterizedType;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public abstract class BaseService <
    M extends BaseModel,
    REQ extends BaseDTO,
    RES extends BaseDTO,
    R extends JpaRepository<M, UUID>
  > {

  protected final Class<M> modelClass = (Class<M>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
  protected final Class<RES> responseDtoClass = (Class<RES>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[2];
  protected final R repository;
  protected final MappingUtils mappingUtils;

  public RES create(REQ requestDto) {
    M model = mappingUtils.mapFromDTO(requestDto, modelClass);
    return mappingUtils.mapToDTO(repository.save(model), responseDtoClass);
  }

  public RES getById(UUID id, boolean noException) {
    M model = repository.findById(id).orElse(null);
    if (model == null && !noException) {
      throw new NotFoundException(modelClass, "id", id.toString());
    }
    return mappingUtils.mapToDTO(model, responseDtoClass);
  }

  public RES updateById(UUID id, REQ requestDto) {
    M model = repository.findById(id).orElse(null);
    if (model == null) {
      throw new NotFoundException(modelClass, "id", id.toString());
    }
    M payload = mappingUtils.mapFromDTO(requestDto, modelClass);
    ModelMapper modelMapper = mappingUtils.getSimpleMapper();
    modelMapper.map(payload, model);
    return mappingUtils.mapToDTO(repository.save(model), responseDtoClass);
  }

  public void deleteById(UUID id) {
    M model = repository.findById(id).orElse(null);
    if (model == null) {
      throw new NotFoundException(modelClass, "id", id.toString());
    }
    repository.delete(model);
  }


//  public Page<P> getAll(int pageIndex, int pageSize) {
//    Pageable pageable = PageRequest.of(pageIndex, pageSize);
//    Page<P> paginatedData = repository.findAll(pageable);
//    return paginatedData;
//  }

}
