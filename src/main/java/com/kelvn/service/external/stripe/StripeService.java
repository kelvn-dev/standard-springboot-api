package com.kelvn.service.external.stripe;

import com.stripe.Stripe;
import com.stripe.net.RequestOptions;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;

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
