package com.kelvn.service.external.stripe;

import com.kelvn.utils.MappingUtils;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.net.ApiResource;
import com.stripe.net.RequestOptions;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.util.Map;

public abstract class StripeService {
  @Value("${stripe.api.secret-key}")
  private String stripeSecretKey;

  protected RequestOptions requestOptions;

  @PostConstruct
  public void init() {
    Stripe.apiKey = stripeSecretKey;
    requestOptions = RequestOptions.builder().setMaxNetworkRetries(3).build();
  }

}
