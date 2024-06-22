package org.alibou.demo.common.security;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

//import org.alibou.demo.filters.HttpRequestFilter;
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {


  private static final String[] WHITE_LIST_URL = {
      "/v3/api-docs.yaml", "/api/v1/auth/login", "/api/v1/teachers/special/register/**",
      "/v2/api-docs", "/v3/api-docs/**", "/swagger-resources/**", "/configuration/ui",
      "/configuration/security", "/swagger-ui/**", "*/openapi.yml", "/api/v1/admin/login",
      "/api/v1/users/confirm**", "api/docs/**"
  };
  private final HttpRequestFilter httpRequestFilter;


  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    System.out.println("imherebro ");
    http
        .addFilterBefore(httpRequestFilter, UsernamePasswordAuthenticationFilter.class)
        .csrf(AbstractHttpConfigurer::disable).sessionManagement(
            session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(
            authorize -> authorize.requestMatchers(WHITE_LIST_URL).permitAll().anyRequest()
                .authenticated()).cors(cors -> cors.configurationSource(request -> {
          CorsConfiguration configuration = new CorsConfiguration();
          configuration.setAllowedOrigins(List.of("*"));
          configuration.setAllowedMethods(List.of("*"));
          configuration.setAllowedHeaders(List.of("*"));
          return configuration;
        }));

    return http.build();
  }

}
