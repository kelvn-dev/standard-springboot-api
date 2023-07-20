package com.kelvn.controller.external.stripe;

import com.google.gson.JsonSyntaxException;
import com.kelvn.exception.BadRequestException;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Customer;
import com.stripe.model.Event;
import com.stripe.model.EventDataObjectDeserializer;
import com.stripe.model.StripeObject;
import com.stripe.net.Webhook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/stripe/webhook")
public class WebhookController {

  @Value("${stripe.webhook.signinng-secret}")
  private String WEBHOOK_SECRET;

  @PostMapping
  public void webhook(@RequestHeader(value = "Stripe-Signature", required = true) String sigHeader, @RequestBody String payload) {
    Event event = null;

    try {
      event = Webhook.constructEvent(
        payload, sigHeader, WEBHOOK_SECRET
      );
    } catch (SignatureVerificationException | JsonSyntaxException e) {
      throw new BadRequestException(e.getMessage());
    }

    EventDataObjectDeserializer dataObjectDeserializer = event.getDataObjectDeserializer();
    StripeObject stripeObject = null;
    if (dataObjectDeserializer.getObject().isPresent()) {
      stripeObject = dataObjectDeserializer.getObject().get();
    } else {
      // Deserialization failed, probably due to an API version mismatch.
      throw new IllegalStateException(String.format("Unable to deserialize event data object for %s", event));
    }
    switch (event.getType()) {
      case "customer.created":
        Customer customer = (Customer) stripeObject;
        break;
      // TODO: handle other event types
      default:
        throw new BadRequestException("Unexpected event type");
    }
  }
}
