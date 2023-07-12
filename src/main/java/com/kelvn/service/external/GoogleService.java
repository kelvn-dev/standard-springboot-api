package com.kelvn.service.external;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.kelvn.exception.UnauthorizedException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GoogleService {

  private final GoogleIdTokenVerifier googleIdTokenVerifier;

  public GoogleIdToken.Payload verifyToken(String idTokenString) {
    GoogleIdToken idToken;
    try {
      idToken = googleIdTokenVerifier.verify(idTokenString);
    } catch (GeneralSecurityException | IOException | IllegalArgumentException e) {
      throw new UnauthorizedException(e.getMessage());
    }

    if (idToken != null) {
      GoogleIdToken.Payload payload = idToken.getPayload();
      return payload;
    }
    throw new UnauthorizedException();
  }
}
