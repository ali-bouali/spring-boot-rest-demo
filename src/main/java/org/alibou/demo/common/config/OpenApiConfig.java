package org.alibou.demo.common.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

  @Value("${classroom.openapi.base-url}")
  private String baseUrl;

  @Bean
  public OpenAPI customOpenAPI() {

    Server server = new Server();
    server.setUrl(baseUrl);
    server.setDescription("Server URL");

    final String workspaceUserSessionToken = "WorkspaceUserSessionToken";
    final String superAdminSessionToken = "SuperadminSessionToken";

    return new OpenAPI().info(new Info().title("FailTracker24 API").
            description("This is the OpenAPI definition for " +
                "Classroom APIs")
            .version("v1.0.0")).
        components(new Components().addSecuritySchemes(superAdminSessionToken,
                new SecurityScheme().name(
                        superAdminSessionToken)
                    .type(
                        SecurityScheme.Type.HTTP)
                    .scheme("bearer")
                    .bearerFormat("JWT"))
            .addSecuritySchemes(workspaceUserSessionToken,
                new SecurityScheme().name(
                        workspaceUserSessionToken)
                    .type(
                        SecurityScheme.Type.HTTP)
                    .scheme("bearer")
                    .bearerFormat("JWT")))
        .addSecurityItem(new SecurityRequirement().addList(superAdminSessionToken))
        .addSecurityItem(new SecurityRequirement().addList(workspaceUserSessionToken))
        .servers(List.of(server));
  }
}