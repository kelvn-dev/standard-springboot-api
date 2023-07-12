package com.kelvn.enums;

public enum ContentDisposition {
  INLINE("inline"),
  ATTACHMENT("attachment");

  private String value;

  private ContentDisposition(String value) {
    this.value = value;
  }

  public String toString() {
    return String.valueOf(this.value);
  }
}
