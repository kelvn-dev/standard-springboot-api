package com.kelvn.controller.webapp;

import com.kelvn.dto.request.AccountRequestDTO;
import com.kelvn.dto.request.FacebookAuthRequestDTO;
import com.kelvn.dto.response.AccountResponseDTO;
import com.kelvn.service.AccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

  @PostMapping("/signup")
  public ResponseEntity<AccountResponseDTO> signup(@RequestBody @Valid AccountRequestDTO requestDTO) {
    AccountResponseDTO responseDTO = accountService.signup(requestDTO);
    return ResponseEntity.ok(responseDTO);
  }

  @PostMapping("/facebook/signup")
  public ResponseEntity<?> signupWithFacebook(@RequestBody @Valid FacebookAuthRequestDTO requestDTO) {
    AccountResponseDTO responseDTO = accountService.signupWithFacebook(requestDTO);
    return ResponseEntity.ok(responseDTO);
  }

}
