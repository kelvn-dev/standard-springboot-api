package com.kelvn.service;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.kelvn.dto.external.response.MetaAccountResponseDTO;
import com.kelvn.model.GoogleAccount;
import com.kelvn.model.MetaAccount;
import com.kelvn.repository.GoogleAccountRepository;
import com.kelvn.repository.MetaAccountRepository;
import com.kelvn.service.external.GoogleService;
import com.kelvn.service.external.MetaService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
  private final TokenService tokenService;
  private final AuthenticationManager authenticationManager;
  private final MetaAccountRepository metaAccountRepository;
  private final GoogleAccountRepository googleAccountRepository;
  private final GoogleService googleService;

  public String login(String email, String password) {
    Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(email, password));
    String token = tokenService.generateToken(authentication);
    return token;
  }

  public String loginWithMeta(String accessToken) {
    MetaService metaService = new MetaService(accessToken);
    MetaAccountResponseDTO metaAccountResponseDTO = metaService.getProfile();
    MetaAccount metaAccount =
        metaAccountRepository.findByMetaAccountId(metaAccountResponseDTO.getId()).orElse(null);
    if (metaAccount == null) {
      throw new BadCredentialsException(null);
    }
    Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                metaAccount.getEmail(), metaAccount.getMetaAccountId()));
    String token = tokenService.generateToken(authentication);
    return token;
  }

  public String loginWithGoogle(String idToken) {
    GoogleIdToken.Payload payload = googleService.verifyToken(idToken);
    GoogleAccount googleAccount =
        googleAccountRepository.findBySub(payload.getSubject()).orElse(null);
    if (googleAccount == null) {
      throw new BadCredentialsException(null);
    }
    Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                googleAccount.getEmail(), googleAccount.getSub()));
    String token = tokenService.generateToken(authentication);
    return token;
  }
}
