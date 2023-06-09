package com.kelvn.service;

import com.kelvn.dto.BaseDTO;
import com.kelvn.dto.api.ApiPageableResponse;
import com.kelvn.exception.NotFoundException;
import com.kelvn.model.BaseModel;
import com.kelvn.utils.HelperUtils;
import com.kelvn.utils.MappingUtils;
import com.kelvn.utils.PredicateUtils;
import com.kelvn.utils.SearchCriteria;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Service;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public abstract class BaseService <
    M extends BaseModel,
    REQ extends BaseDTO,
    RES extends BaseDTO,
    R extends JpaRepository<M, UUID> & QuerydslPredicateExecutor<M>
  > {

  protected final Class<M> modelClass = (Class<M>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
  protected final Class<RES> responseDtoClass = (Class<RES>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[2];
  protected final R repository;
  protected final MappingUtils mappingUtils;

  public RES create(REQ requestDTO) {
    M model = mappingUtils.mapFromDTO(requestDTO, modelClass);
    return mappingUtils.mapToDTO(repository.save(model), responseDtoClass);
  }

  public RES create(M model) {
    return mappingUtils.mapToDTO(repository.save(model), responseDtoClass);
  }

  public RES getById(UUID id, boolean noException) {
    M model = repository.findById(id).orElse(null);
    if (model == null && !noException) {
      throw new NotFoundException(modelClass, "id", id.toString());
    }
    return mappingUtils.mapToDTO(model, responseDtoClass);
  }

  public RES updateById(UUID id, REQ requestDTO) {
    M model = repository.findById(id).orElse(null);
    if (model == null) {
      throw new NotFoundException(modelClass, "id", id.toString());
    }
    M payload = mappingUtils.mapFromDTO(requestDTO, modelClass);
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

  public ApiPageableResponse getList(String[] filter, Pageable pageable) {
    List<SearchCriteria> criterias = HelperUtils.formatSearchCriteria(filter);
    BooleanExpression expression = PredicateUtils.getBooleanExpression(criterias, modelClass);
    Page<M> pagingModel = repository.findAll(expression, pageable);
    return formatPagingResponse(pagingModel);
  }

  public ApiPageableResponse formatPagingResponse(Page<M> page) {
    return ApiPageableResponse.builder().currentPage(page.getNumber() + 1).pageSize(page.getSize())
      .totalPages(page.getTotalPages()).totalElements(page.getTotalElements()).isFirst(page.isFirst()).isLast(page.isLast())
      .data(mappingUtils.mapListToDTO(page.getContent(), responseDtoClass))
      .build();
  }

}
