package com.kelvn.service;

import com.kelvn.utils.HelperUtils;
import com.kelvn.utils.ServletUtils;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LoggingService {
  public void logRequest(HttpServletRequest request, Object body) {
    StringBuilder reqMessage = new StringBuilder();
    Map<String, String> parameters = ServletUtils.getParameters(request);

    reqMessage.append("REQUEST: ");
    reqMessage.append("method = ").append(request.getMethod()).append(" | ");
    reqMessage.append("path = ").append(request.getRequestURI()).append(" | ");
    reqMessage.append("parameters = ").append(parameters).append(" | ");
    reqMessage.append("body = ").append(body != null ? HelperUtils.inspect(body) : null);

    log.info(reqMessage.toString());
  }

  public void logResponse(HttpServletRequest request, HttpServletResponse response, Object body) {
    StringBuilder respMessage = new StringBuilder();
    Map<String, String> headers = ServletUtils.getHeaders(response);

    respMessage.append("RESPONSE: ");
    respMessage.append("method = ").append(request.getMethod()).append(" | ");
    respMessage.append("ResponseBody = ").append(HelperUtils.inspect(body)).append(" | ");
    respMessage.append("ResponseHeaders = ").append(headers);

    log.info(respMessage.toString());
  }
}
