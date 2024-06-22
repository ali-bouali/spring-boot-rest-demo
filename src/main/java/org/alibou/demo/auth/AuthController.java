package org.alibou.demo.auth;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService service;

  @PostMapping("/login")
  public ResponseEntity<LoginResponse> login(
     @Valid @RequestBody LoginRequest request
  ) {

    System.out.println("le maillllllllllllllllllll  " +request.email());
    return ResponseEntity.ok(service.login(request));
  }
}