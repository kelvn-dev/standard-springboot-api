package com.kelvn.service.external.stripe;

import com.kelvn.dto.external.request.StripeChargeRequestDTO;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.net.RequestOptions;
import com.stripe.param.ChargeCreateParams;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class StripeChargeSerivce extends StripeService {

  @SneakyThrows(StripeException.class)
  public Charge create(StripeChargeRequestDTO requestDTO) {
    ChargeCreateParams createParams = ChargeCreateParams.builder().setAmount(requestDTO.getAmount()).setCurrency(requestDTO.getCurrency()).setSource(requestDTO.getToken()).build();
    return Charge.create(createParams, requestOptions);
  }
}
