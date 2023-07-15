package com.kelvn.utils;

public class RegexUtils {
  /**
   * ^ asserts the start of the string. [\\w\\s\\-()]+ matches one or more word characters
   * (alphanumeric and underscore), whitespace characters, hyphens, parentheses, or underscores. \\.
   * matches a literal dot character. [a-zA-Z]{2,4} matches two to four alphabetic characters. You
   * can adjust the range as needed. $ asserts the end of the string.
   */
  public static final String simpleFilename = "^[\\w\\s\\-()]+\\.[a-zA-Z]{2,4}$";
}
