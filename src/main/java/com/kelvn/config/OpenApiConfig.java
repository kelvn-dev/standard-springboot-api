package com.kelvn.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Configuration;

// Swagger-ui endpoint: http://localhost:8080/swagger-ui/index.html#
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

  // Another way to config

  //  @Bean
  //  public OpenAPI customOpenAPI() {
  //    return new OpenAPI()
  //        .components(new Components())
  //        .info(
  //            new io.swagger.v3.oas.models.info.Info()
  //                .title("Standard Springboot API")
  //                .description(
  //                    "A project implemented with Spring Boot to be reused as standard template")
  //                .contact(
  //                    new io.swagger.v3.oas.models.info.Contact()
  //                        .name("Kelvin Vu")
  //                        .url("https://github.com/kelvn-dev")));
  //  }
}
