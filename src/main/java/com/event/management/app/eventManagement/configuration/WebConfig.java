package com.event.management.app.eventManagement.configuration;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  // Configuration CORS pour Angular
  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/api/**")
      .allowedOrigins("http://localhost:4200") // Adresse Angular Dev Server
      .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
      .allowedHeaders("Authorization", "Content-Type", "X-Requested-With")
      .exposedHeaders("Authorization") // Important pour JWT
      .allowCredentials(true)
      .maxAge(3600);
  }

  // Configuration Swagger globale
  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
      .info(new Info()
        .title("API Gestion d'Événements")
        .version("1.0")
        .description("Documentation des APIs pour le système de gestion d'événements"))
      .addSecurityItem(new SecurityRequirement().addList("JWT"))
      .components(new io.swagger.v3.oas.models.Components()
        .addSecuritySchemes("JWT",
          new SecurityScheme()
            .name("JWT")
            .type(SecurityScheme.Type.HTTP)
            .scheme("bearer")
            .bearerFormat("JWT")));
  }

  // Groupement des APIs par fonctionnalité
  @Bean
  public GroupedOpenApi authApi() {
    return GroupedOpenApi.builder()
      .group("Authentification")
      .pathsToMatch("/api/auth/**")
      .build();
  }

  @Bean
  public GroupedOpenApi eventApi() {
    return GroupedOpenApi.builder()
      .group("Événements")
      .pathsToMatch("/api/events/**")
      .build();
  }

  // Forward Controller pour Angular routes (toutes les requêtes non API)
  @Controller
  static class ForwardController {
    @RequestMapping(value = "/{path:^(?!api$).*$}/**")
    public String forward() {
      // Rediriger vers index.html de Angular
      return "forward:/index.html";
    }
  }
}
