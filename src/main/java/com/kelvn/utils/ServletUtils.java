package com.kelvn.utils;

import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletUtils {

  public static Map<String, String> getHeaders(HttpServletResponse response) {
    Map<String, String> headers = new HashMap<>();
    Collection<String> headerMap = response.getHeaderNames();
    for (String str : headerMap) {
      headers.put(str, response.getHeader(str));
    }
    return headers;
  }

  public static Map<String, String> getParameters(HttpServletRequest request) {
    Map<String, String> parameters = new HashMap<>();
    Enumeration<String> params = request.getParameterNames();
    while (params.hasMoreElements()) {
      String paramName = params.nextElement();
      String paramValue = request.getParameter(paramName);
      parameters.put(paramName, paramValue);
    }
    return parameters;
  }
}
