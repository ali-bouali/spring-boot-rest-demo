package org.alibou.demo.common.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
@Slf4j
public class HttpRequestFilter  extends OncePerRequestFilter {

  private final JwtService jwtService;


  private final CustomUserDetailsService userService;

@Override
  protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

    String adminLoginPath = "/api/v1/admin/login";
    String userLoginPath = "/api/v1/login";
    String path = request.getRequestURI();
    return path.equals(adminLoginPath) || path.equals(userLoginPath);
  }
  @Override
  protected void doFilterInternal(HttpServletRequest request,
      HttpServletResponse response,
      FilterChain filterChain)
      throws ServletException, IOException {

    final String authHeader = request.getHeader("Authorization");
    final String jwt;
    final String userEmail;
    if (StringUtils.isEmpty(authHeader) || !StringUtils.startsWith(authHeader,
        "Bearer ")) {
      filterChain.doFilter(request,
          response);
      return;
    }
    jwt = authHeader.substring(7);
    log.debug("JWT - {}",
        jwt);
    userEmail = jwtService.extractUserName(jwt);
    if (StringUtils.isNotEmpty(userEmail) && SecurityContextHolder.getContext()
        .getAuthentication() == null) {
      UserDetails userDetails = userService.loadUserByUsername(userEmail);
      if (jwtService.isTokenValid(jwt,
          userDetails)) {
        log.debug("User - {}",
            userDetails);
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        UsernamePasswordAuthenticationToken authToken =
            new UsernamePasswordAuthenticationToken(userDetails,
                null,
                userDetails.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        context.setAuthentication(authToken);
        SecurityContextHolder.setContext(context);
      }
    }
    filterChain.doFilter(request,
        response);
  }
}
