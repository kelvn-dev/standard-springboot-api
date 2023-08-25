package com.kelvn.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RabbitMQConfig {
  private final CachingConnectionFactory cachingConnectionFactory;

  @Bean
  public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
    return new Jackson2JsonMessageConverter();
  }

  @Bean
  public RabbitTemplate rabbitTemplate(Jackson2JsonMessageConverter jackson2JsonMessageConverter) {
    RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory);
    rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter);
    return rabbitTemplate;
  }

  @Bean
  public Declarables createAccountRegistrationSchema() {
    return new Declarables(
        new FanoutExchange("x.account-registration"),
        new Queue("q.registration-confirmation-email"),
        // new Queue(...),
        new Binding(
            "q.registration-confirmation-email",
            Binding.DestinationType.QUEUE,
            "x.account-registration",
            "registration-confirmation-email",
            null)
        // new Binding(...)
        );
  }
}
