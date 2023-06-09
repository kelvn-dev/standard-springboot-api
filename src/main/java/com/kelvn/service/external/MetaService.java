package com.kelvn.service.external;

import com.kelvn.dto.external.response.MetaAccountResDTO;

public class MetaService extends ApiBinding {
  private static final String GRAPH_API_BASE_URL = "https://graph.facebook.com";

  public MetaService(String accessToken) {
    super(accessToken);
  }

  public MetaAccountResDTO getProfile() {
    return restTemplate.getForObject(GRAPH_API_BASE_URL + "/me?fields=id,last_name,link,email,first_name,name", MetaAccountResDTO.class);
  }
}
