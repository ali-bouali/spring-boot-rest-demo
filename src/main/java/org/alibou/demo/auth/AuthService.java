package org.alibou.demo.auth;


import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.alibou.demo.auth.dto.LoginRequest;
import org.alibou.demo.auth.dto.LoginResponse;
import org.alibou.demo.common.exception.UnauthorizedException;
import org.alibou.demo.common.security.JwtService;
import org.alibou.demo.user.User;
import org.alibou.demo.user.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final AuthenticationManager authenticationManager;
  private final JwtService jwtService;
  private final UserRepository userRepository;

  public LoginResponse login(LoginRequest request) {

    try {
      System.out.println("\n herekkkkkkkkkkkkkkkkkkk  "+request.email());

      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(request.email(), request.password()));
      System.out.println("\n fffffff  "+request.email());
      User user = userRepository.findUserByEmail(request.email())
          .orElseThrow(() -> new UnauthorizedException("Invalid email or password."));

      String jwtToken = jwtService.generateToken(user);
      LoginResponse response = LoginResponse.builder().token(jwtToken)
          .build();

      return response;
    } catch (AuthenticationException e) {
      handleAuthenticationFailure(request.email());
      throw new UnauthorizedException("Authentication failed. Invalid email or password.");
    }
  }

  private void handleAuthenticationFailure(String userEmail) throws EntityNotFoundException {
    System.out.println("\n hereeeeeeeeeeeeeeeeeeee  "+userEmail);
    User user = userRepository.findUserByEmail(userEmail)
        .orElseThrow(() -> new EntityNotFoundException("User not found"));

    userRepository.save(user);
  }
//  public LoginResponse login(LoginRequest request) {
//
//
//
//
//
//
//    System.out.println("\n loginloginloginloginloginloginloginloginloginloginloginloginloginloginloginloginloginloginloginlogin");
//
//    Authentication user = authenticationManager.authenticate(
//        new UsernamePasswordAuthenticationToken(
//            request.email(),
//            request.password()
//        )
//    );
//    Map<String, Object> claims = new HashMap<>();
//    claims.put("attr", "some value");
//
//    String token = jwtService.generateToken(claims, (UserDetails)user.getPrincipal());
//    System.out.println("\n hereeeeeeeeeeeeeeeeeeeeeeeeeeee");
//    return new LoginResponse(token);
//  }
}