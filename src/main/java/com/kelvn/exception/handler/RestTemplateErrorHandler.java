package com.kelvn.exception.handler;

import com.kelvn.exception.ServiceUnavailableException;
import com.kelvn.exception.UnauthorizedException;
import java.io.IOException;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

@Component
public class RestTemplateErrorHandler implements ResponseErrorHandler {

  @Override
  public boolean hasError(ClientHttpResponse response) throws IOException {
    return response.getStatusCode().is4xxClientError()
        || response.getStatusCode().is5xxServerError();
  }

  @Override
  public void handleError(ClientHttpResponse response) throws IOException {
    if (response.getStatusCode().is5xxServerError()) {
      switch (response.getStatusCode()) {
        case SERVICE_UNAVAILABLE:
          throw new ServiceUnavailableException(response.getBody().toString());
        default:
          throw new RuntimeException(response.getBody().toString());
      }
    } else {
      switch (response.getStatusCode()) {
        case UNAUTHORIZED:
          throw new UnauthorizedException();
        default:
          throw new RuntimeException(response.getBody().toString());
      }
    }
  }
}
