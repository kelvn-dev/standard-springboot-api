package com.kelvn.converter;

import com.kelvn.enums.ContentDisposition;
import org.springframework.core.convert.converter.Converter;

public class ContentDispositionConverter implements Converter<String, ContentDisposition> {

  @Override
  public ContentDisposition convert(String source) {
    try {
      return ContentDisposition.valueOf(source.toUpperCase());
    } catch (IllegalArgumentException e) {
      // TODO: 400
      throw new RuntimeException();
    }
  }
}
