package org.alibou.demo.common.security;


import lombok.RequiredArgsConstructor;
import org.alibou.demo.auditing.ApplicationAuditAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
@RequiredArgsConstructor
public class CustomBean {
  private final CustomUserDetailsService userService;

  private final PasswordEncoder passwordEncoder;
  @Bean
  public ApplicationAuditAware auditorAware() {
    return new ApplicationAuditAware();
  }

  @Bean
  public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userService);
    authProvider.setPasswordEncoder(passwordEncoder);
    return authProvider;
  }

  @Bean
  public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
    var builder = http.getSharedObject(AuthenticationManagerBuilder.class);
    builder.userDetailsService(userService).passwordEncoder(passwordEncoder);
    return builder.build();
  }


}
