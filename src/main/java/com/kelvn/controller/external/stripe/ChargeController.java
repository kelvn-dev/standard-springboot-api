package com.kelvn.controller.external.stripe;

import com.kelvn.dto.external.request.StripeChargeRequestDTO;
import com.kelvn.service.external.stripe.StripeChargeSerivce;
import com.kelvn.service.external.stripe.StripeCustomerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Tag(name = "Stripe Charges")
@RestController
@RequestMapping("/stripe/charges")
@RequiredArgsConstructor
public class ChargeController {

  private final StripeChargeSerivce stripeChargeSerivce;

  @PostMapping()
  public ResponseEntity<String> create(@Valid @RequestBody StripeChargeRequestDTO requestDTO) {
    return ResponseEntity.ok(stripeChargeSerivce.create(requestDTO).toJson());
  }
}
