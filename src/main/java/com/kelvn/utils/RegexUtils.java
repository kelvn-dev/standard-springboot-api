package com.kelvn.utils;

public class RegexUtils {
  /**
   * @note ^ asserts the start of the string.
   * @note [\\w\\s\\-()]+ matches one or more word characters (alphanumeric and underscore),
   *     whitespace characters, hyphens, parentheses, or underscores.
   * @note \\.matches a literal dot character.
   * @note [a-zA-Z]{2,4} matches two to four alphabetic characters.
   * @note $ asserts the end of the string.
   */
  public static final String simpleFilename = "^[\\w\\s\\-()]+\\.[a-zA-Z]{2,4}$";
}
