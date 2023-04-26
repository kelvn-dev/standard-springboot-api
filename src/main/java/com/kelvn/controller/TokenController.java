package com.kelvn.controller;

import com.kelvn.dto.Token;
import com.kelvn.dto.request.AuthRequestDTO;
import com.kelvn.dto.response.AuthResponseDTO;
import com.kelvn.service.TokenService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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

  private final TokenService tokenService;
  private final AuthenticationManager authenticationManager;

  @GetMapping("/token")
  public Token getToken(JwtAuthenticationToken jwtToken) {
    return new Token(jwtToken.getToken(), jwtToken.getAuthorities());
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody @Valid AuthRequestDTO request) {
    try {
      Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
      String token = tokenService.generateToken(authentication);
      return ResponseEntity.ok(new AuthResponseDTO(token));
    }
    catch (BadCredentialsException exception) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
  }

}
