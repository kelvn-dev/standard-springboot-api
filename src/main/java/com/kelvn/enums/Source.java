package com.kelvn.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Source {

  META("third-party identity (meta)"),
  GOOGLE("third-party identity (google)"),
  LOCAL("built-in system");

  private String label;
}
