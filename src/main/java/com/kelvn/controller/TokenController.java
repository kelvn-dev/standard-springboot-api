package com.kelvn.controller;

import com.kelvn.dto.Token;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/token")
public class TokenController {

  @GetMapping()
  public Token getToken(JwtAuthenticationToken jwtToken) {
    return new Token(jwtToken.getToken(), jwtToken.getAuthorities());
  }

}
