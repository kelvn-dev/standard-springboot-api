package com.kelvn.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.parameters.HeaderParameter;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Default Swagger-ui endpoint: http://localhost:8080/swagger-ui/index.html#
@Configuration
@OpenAPIDefinition(
    info =
        @Info(
            title = "Standard Springboot API",
            version = "v1.0",
            description =
                "A Spring Boot project implemented with common components to be reused as standard"
                    + " template",
            contact = @Contact(name = "Kelvin Vu", url = "https://github.com/kelvn-dev")))
@SecurityScheme(
    name = "bearerAuth",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    scheme = "bearer")
public class OpenApiConfig {

  private OperationCustomizer tenantHeaderOperationCustomizer() {
    return (operation, handlerMethod) -> {
      HeaderParameter tenantHeaderParam = new HeaderParameter();
      tenantHeaderParam.setName("X-Tenant-Id");
      tenantHeaderParam.setRequired(true);
      operation.addParametersItem(tenantHeaderParam);
      return operation;
    };
  }

  @Bean
  public GroupedOpenApi publicApi() {
    return GroupedOpenApi.builder()
        .group("add-tenant-header")
        .addOperationCustomizer(tenantHeaderOperationCustomizer())
        .build();
  }
}
