package com.kelvn.service.external;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.kelvn.exception.UnAuthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Service
@RequiredArgsConstructor
public class GoogleService {

  private final GoogleIdTokenVerifier googleIdTokenVerifier;

  public GoogleIdToken.Payload verifyToken(String idTokenString) {
    GoogleIdToken idToken;
    try {
      idToken = googleIdTokenVerifier.verify(idTokenString);
    } catch (GeneralSecurityException | IOException | IllegalArgumentException e) {
      throw new UnAuthorizedException(e.getMessage());
    }

    if (idToken != null) {
      GoogleIdToken.Payload payload = idToken.getPayload();
      return payload;
    }
    throw new UnAuthorizedException();
  }
}
