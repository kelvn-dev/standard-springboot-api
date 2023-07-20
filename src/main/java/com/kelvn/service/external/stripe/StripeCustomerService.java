package com.kelvn.service.external.stripe;

import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.net.RequestOptions;
import com.stripe.param.CustomerCreateParams;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class StripeCustomerService extends StripeService {

  @SneakyThrows(StripeException.class)
  public Customer create(Map<String, Object> params) {
    RequestOptions requestOptions = RequestOptions.builder().setMaxNetworkRetries(3).build();
    return Customer.create(params, requestOptions);
  }
}
