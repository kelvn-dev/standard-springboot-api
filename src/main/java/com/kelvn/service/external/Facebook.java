package com.kelvn.service.external;

import com.kelvn.dto.external.response.FBProfileResDTO;

public class Facebook extends ApiBinding {
  private static final String GRAPH_API_BASE_URL = "https://graph.facebook.com";

  public Facebook(String accessToken) {
    super(accessToken);
  }

  public FBProfileResDTO getProfile() {
    return restTemplate.getForObject(GRAPH_API_BASE_URL + "/me?fields=id,last_name,link,email,first_name,name", FBProfileResDTO.class);
  }
}
