package com.kelvn.service.external.stripe;

import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import java.util.Map;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@Service
public class StripeCustomerService extends StripeService {

  @SneakyThrows(StripeException.class)
  public Customer create(Map<String, Object> params) {
    return Customer.create(params, requestOptions);
  }
}
