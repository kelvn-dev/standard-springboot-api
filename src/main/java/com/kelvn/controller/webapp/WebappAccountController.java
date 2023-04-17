package com.kelvn.controller.webapp;

import com.kelvn.dto.request.AccountRequestDTO;
import com.kelvn.dto.response.AccountResponseDTO;
import com.kelvn.service.AccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "Webapp.Account")
@RestController
@RequestMapping("/api/v1/webapp/account")
@RequiredArgsConstructor
public class WebappAccountController {

  private final AccountService accountService;

  @PostMapping("/signup")
  public ResponseEntity<AccountResponseDTO> signup(@RequestBody @Valid AccountRequestDTO requestDTO) {
    AccountResponseDTO responseDTO = accountService.signup(requestDTO);
    return ResponseEntity.ok(responseDTO);
  }

}
