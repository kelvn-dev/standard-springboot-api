package com.kelvn.utils;

import java.lang.reflect.Field;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import lombok.SneakyThrows;

public class HelperUtils {

  public static List<SearchCriteria> formatSearchCriteria(String[] filter) {
    List<SearchCriteria> criterias = new ArrayList<>();
    if (null != filter) {
      Collection<SearchCriteria> collect =
          Arrays.asList(filter).parallelStream()
              .map(HelperUtils::validateFilterPattern)
              .collect(Collectors.toList());
      criterias.addAll(collect);
    }
    return criterias;
  }

  public static SearchCriteria validateFilterPattern(String filter) {
    final Pattern pattern =
        Pattern.compile("([\\w.]+?)(:|<|>|=|!=|<=|>=|%|\\(\\))([\\w\\s\\(\\):@;,._-]+?)\\|");
    Matcher m = pattern.matcher(filter + "|");
    if (m.find()) {
      return SearchCriteria.builder()
          .key(m.group(1))
          .operator(m.group(2))
          .value(m.group(3))
          .build();
    } else {
      throw new RuntimeException("Invalid Filter format");
    }
  }

  public static Class<?> getPropertyType(Class<?> parent, String property) {
    List<String> propertyList = new LinkedList<>(Arrays.asList(property.split("\\.")));
    return getRecursiveType(parent, propertyList);
  }

  @SneakyThrows(NoSuchFieldException.class)
  public static Class<?> getRecursiveType(Class<?> parent, List<String> propertyList) {
    if (propertyList.size() > 1) {
      Field field = parent.getDeclaredField(propertyList.get(0));
      Class<?> child = field.getType();
      propertyList.remove(propertyList.get(0));
      if ("List".equals(child.getSimpleName())) {
        return child;
        // ParameterizedType type = (ParameterizedType) field.getGenericType();
        // return getRecursiveType((Class<?>) type.getActualTypeArguments()[0],
        // propertyList);
      }
      return getRecursiveType(child, propertyList);
    } else {
      if (parent.getSuperclass() != null) {
        for (Field field : parent.getSuperclass().getDeclaredFields()) {
          if (field.getName().equals(propertyList.get(0))) {
            return field.getType();
          }
        }
      }
      return parent.getDeclaredField(propertyList.get(0)).getType();
    }
  }

  public static String getEntityVariable(String simpleClassName) {
    char[] c = simpleClassName.toCharArray();
    c[0] = Character.toLowerCase(c[0]);
    return new String(c);
  }

  public static String[] getValueRange(String value) {
    if (value.startsWith(";")) {
      value = " ".concat(value);
    } else if (value.endsWith(";")) {
      value = value.concat(" ");
    }
    String[] valueRange = value.split(";");
    if (valueRange.length > 2) {
      throw new RuntimeException("Invalid Filter format");
    }
    return valueRange;
  }
}
