package com.kelvn.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.interceptor.MethodInvocationRecoverer;
import org.springframework.retry.interceptor.RetryInterceptorBuilder;
import org.springframework.retry.interceptor.RetryOperationsInterceptor;

@Configuration
@RequiredArgsConstructor
public class RabbitMQConfig {
  private final CachingConnectionFactory cachingConnectionFactory;

  @Bean
  public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
    return new Jackson2JsonMessageConverter();
  }

  @Bean
  public RetryOperationsInterceptor retryInterceptor() {
    return RetryInterceptorBuilder.stateless()
        .maxAttempts(3)
        .backOffOptions(2000, 2.0, 10000)
        .recoverer(
            (MethodInvocationRecoverer<Void>)
                (args, cause) -> {
                  throw new AmqpRejectAndDontRequeueException(cause);
                })
        .build();
  }

  @Bean
  public RabbitTemplate rabbitTemplate(Jackson2JsonMessageConverter jackson2JsonMessageConverter) {
    RabbitTemplate rabbitTemplate = new RabbitTemplate(cachingConnectionFactory);
    rabbitTemplate.setMessageConverter(jackson2JsonMessageConverter);
    return rabbitTemplate;
  }

  @Bean
  public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
      SimpleRabbitListenerContainerFactoryConfigurer configurer) {
    SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
    configurer.configure(factory, cachingConnectionFactory);
    factory.setAcknowledgeMode(AcknowledgeMode.AUTO);
    factory.setAdviceChain(retryInterceptor());
    return factory;
  }

  @Bean
  public Declarables createDeadLetterAccountRegistrationSchema() {
    return new Declarables(
        new DirectExchange("x.account-registration-failure"),
        new Queue("q.fall-back-registration-confirmation-email"),
        new Binding(
            "q.fall-back-registration-confirmation-email",
            Binding.DestinationType.QUEUE,
            "x.account-registration-failure",
            "fall-back-registration-confirmation-email",
            null));
  }

  @Bean
  public Declarables createAccountRegistrationSchema() {
    return new Declarables(
        new FanoutExchange("x.account-registration"),
        QueueBuilder.durable("q.registration-confirmation-email")
            .deadLetterExchange("x.account-registration-failure")
            .deadLetterRoutingKey("fall-back-registration-confirmation-email")
            .build(),
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
