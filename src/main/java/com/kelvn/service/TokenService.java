package com.kelvn.service;

import com.kelvn.model.Account;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {
	@Value("${jwt.validity.hours}")
	private String JWT_VALIDITY_HOUR;

	private final JwtEncoder jwtEncoder;

	public String generateToken(Authentication authentication) {
		Instant now = Instant.now();
		Account account = (Account) authentication.getPrincipal();
		String scope =
				authentication.getAuthorities().stream()
						.map(GrantedAuthority::getAuthority)
						.collect(Collectors.joining(" "));
		JwtClaimsSet claims =
				JwtClaimsSet.builder()
						.issuer("http://foobar.com")
						.issuedAt(now)
						.expiresAt(now.plus(Long.parseLong(JWT_VALIDITY_HOUR), ChronoUnit.HOURS))
						.subject(account.getId().toString())
						.claim("email", account.getEmail())
						.claim("scope", scope)
						.build();
		return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
	}
}
