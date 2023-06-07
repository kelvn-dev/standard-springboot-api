package com.kelvn.controller.webapp;

import com.kelvn.dto.request.AccountRequestDTO;
import com.kelvn.dto.request.FacebookAuthRequestDTO;
import com.kelvn.dto.response.AccountResponseDTO;
import com.kelvn.model.FacebookAccount;
import com.kelvn.service.AccountService;
import com.kelvn.utils.constant.FB;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Tag(name = "Webapp.Account")
@RestController
@RequestMapping("/v1/webapp/account")
@RequiredArgsConstructor
public class WebappAccountController {

  private final AccountService accountService;
//  private final FacebookService facebookService;

  @PostMapping("/signup")
  public ResponseEntity<AccountResponseDTO> signup(@RequestBody @Valid AccountRequestDTO requestDTO) {
    AccountResponseDTO responseDTO = accountService.signup(requestDTO);
    return ResponseEntity.ok(responseDTO);
  }

  @PostMapping("/facebook/signup")
  public ResponseEntity<?> signupByFacebook(@RequestBody @Valid FacebookAuthRequestDTO requestDTO) {
//    FacebookAuthRequestDTO responseDTO = accountService.signup(requestDTO);
//    Profile profile = facebookService.getProfile();
    Facebook facebook = new FacebookTemplate(requestDTO.getAccessToken());
    return ResponseEntity.ok(facebook.fetchObject(FB.LOGGED_USER_ID, FacebookAccount.class, FB.USER_FIELD_ID, FB.USER_FIELD_EMAIL,
      FB.USER_FIELD_FIRST_NAME, FB.USER_FIELD_LAST_NAME));
  }

}
