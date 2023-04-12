package com.kelvn.dto;

import com.kelvn.utils.MappingUtils;
import org.modelmapper.ModelMapper;

public interface BaseDTO {

  default ModelMapper updateModelMapper(ModelMapper mapper, MappingUtils utils){
    return mapper;
  }

}
