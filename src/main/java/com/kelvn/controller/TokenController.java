package com.kelvn.controller;

import com.kelvn.dto.Token;
import com.kelvn.dto.request.AuthRequestDTO;
import com.kelvn.dto.request.MetaAuthReqDTO;
import com.kelvn.dto.response.AuthResponseDTO;
import com.kelvn.service.AuthService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class TokenController {

  private final AuthService authService;

  @GetMapping("/token/details")
  public Token getToken(JwtAuthenticationToken jwtToken) {
    return new Token(jwtToken.getToken(), jwtToken.getAuthorities());
  }

  @PostMapping("/token")
  public ResponseEntity<?> login(@RequestBody @Valid AuthRequestDTO request) {
    try {
      String token = authService.login(request.getEmail(), request.getPassword());
      return ResponseEntity.ok(new AuthResponseDTO(token));
    }
    catch (BadCredentialsException exception) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
  }

  @PostMapping("/meta/token")
  public ResponseEntity<?> loginWithMeta(@RequestBody @Valid MetaAuthReqDTO request) {
    try {
      String token = authService.loginWithMeta(request.getAccessToken());
      return ResponseEntity.ok(new AuthResponseDTO(token));
    }
    catch (BadCredentialsException exception) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
  }

}
