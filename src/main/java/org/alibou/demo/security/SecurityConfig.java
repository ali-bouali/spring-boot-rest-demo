package org.alibou.demo.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StringUtils;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private  final HttpRequestFilter filter;
    // private final AuthenticationProvider authProvider;

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
                                "/public/**",
                                "/auth/**"
                                )
                                .permitAll()
                                // .requestMatchers(POST, "students/**")
                                   //  .hasRole("admin")
                                // .requestMatchers(GET, "students/**")
                                    // .hasAnyRole("manager", "admin")
                                // .requestMatchers("/management") --> najem n7ottha fel controller @PreAuthorize()
                                    // .hasAnyRole("admin", "super_admin")
                            .anyRequest()
                                .authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                // .authenticationProvider(authProvider) --> kima fasserna, l auth provider mech lazem n7ottouh manuellement 5ater Spring bech ya3ref w yal9a direct PasswordEncode bean wel UserServiceDetails bean


                ;

        return http.build();
    }
}
