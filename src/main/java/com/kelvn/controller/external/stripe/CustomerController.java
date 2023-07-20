package com.kelvn.controller.external.stripe;

import com.kelvn.service.external.stripe.StripeCustomerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Stripe Customers")
@RestController
@RequestMapping("/stripe/customers")
@RequiredArgsConstructor
public class CustomerController {

  private final StripeCustomerService stripeCustomerService;

  @PostMapping()
  public ResponseEntity<String> create(@RequestBody Map<String, Object> params) {
    return ResponseEntity.ok(stripeCustomerService.create(params).toJson());
  }
}
