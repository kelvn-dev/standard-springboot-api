//package com.kelvn.service.external;
//
//import com.kelvn.utils.ApiBinding;
//import org.springframework.context.annotation.Profile;
//import org.springframework.stereotype.Service;
//
//@Service
//public class FacebookService extends ApiBinding {
//  private static final String GRAPH_API_BASE_URL = "https://graph.facebook.com";
//
//  public FacebookService(String accessToken) {
//    super(accessToken);
//  }
//
//  public Profile getProfile() {
//    return restTemplate.getForObject(GRAPH_API_BASE_URL.concat("/me"), Profile.class);
//  }
//
//}
