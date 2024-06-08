package org.alibou.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.util.StringUtils;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        List<String> roles = List.of("user");
        return new InMemoryUserDetailsManager(
                User.withUsername("mohammed")
                        .password("password")
                        .roles(StringUtils.toStringArray(roles))
                        .build(),
                User.withUsername("ali")
                        .password("password")
                        .roles(StringUtils.toStringArray(roles))
                        .disabled(true)
                        .build()
        );
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // nchoufouha ba3ed
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req ->
                        req.requestMatchers(
                                "/students/**"
                                )
                                    .permitAll()
                            .anyRequest()
                                .authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                ;

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
