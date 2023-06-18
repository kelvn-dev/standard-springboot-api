package com.kelvn.config;

import com.kelvn.interceptor.LoggingInterceptor;
import com.kelvn.interceptor.ResponseHeaderInterceptor;
import com.kelvn.interceptor.TenantInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {
  private final LoggingInterceptor loggingInterceptor;
  private final ResponseHeaderInterceptor responseHeaderInterceptor;
  private final TenantInterceptor tenantInterceptor;

  @Override
  public void configurePathMatch(PathMatchConfigurer configurer) {
    configurer.addPathPrefix("/api", HandlerTypePredicate.forAnnotation(RestController.class));
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(loggingInterceptor).addPathPatterns("/api/v1/**").order(1);
    registry.addInterceptor(responseHeaderInterceptor).addPathPatterns("/api/v1/**").order(2);
    registry.addInterceptor(tenantInterceptor).addPathPatterns("/api/v1/**").order(3);
    WebMvcConfigurer.super.addInterceptors(registry);
  }
}
