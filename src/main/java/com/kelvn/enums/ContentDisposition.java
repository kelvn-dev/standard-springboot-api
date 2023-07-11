package com.kelvn.enums;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ContentDisposition {
  INLINE("inline"),
  ATTACHMENT("attachment");

  private final String value;

  public String toString() {
    return String.valueOf(this.value);
  }
}
