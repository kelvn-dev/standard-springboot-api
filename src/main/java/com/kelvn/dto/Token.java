package com.kelvn.dto;

import java.util.Collection;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

@Data
public class Token {
  private final Jwt token;
  private final Collection<GrantedAuthority> authorities;
}
