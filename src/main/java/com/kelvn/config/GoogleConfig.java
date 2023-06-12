package com.kelvn.config;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GoogleConfig {

  @Value("${spring.security.oauth2.client.google.client-id}")
  private String GOOGLE_CLIENT_ID;

  private final NetHttpTransport httpTransport = new NetHttpTransport();
  ;
  private final GsonFactory gsonFactory = new GsonFactory();

  @Bean
  public GoogleIdTokenVerifier googleIdTokenVerifier() {
    return new GoogleIdTokenVerifier.Builder(httpTransport, gsonFactory)
        // Specify the CLIENT_ID of the app that accesses the backend:
        .setAudience(Collections.singletonList(GOOGLE_CLIENT_ID))
        // Or, if multiple clients access the backend:
        // .setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
        .build();
  }
}
