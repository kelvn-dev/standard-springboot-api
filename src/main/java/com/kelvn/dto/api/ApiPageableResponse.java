package com.kelvn.dto.api;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApiPageableResponse {
  private int currentPage;
  private int pageSize;
  private int totalPages;
  private long totalElements;
  private boolean isLast;
  private boolean isFirst;
  private Object data;
}
