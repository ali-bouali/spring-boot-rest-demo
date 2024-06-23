package org.alibou.demo.auth;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.alibou.demo.auth.dto.LoginRequest;
import org.alibou.demo.auth.dto.LoginResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Endpoints for authentication")
public class AuthenticationController {

  private final AuthService service;

  @Operation(summary = "Login user", description = "Authenticate user and return token")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successful login"),
      @ApiResponse(responseCode = "401", description = "Unauthorized")
  })
  @PostMapping("/login")
  public ResponseEntity<LoginResponse> login(
     @Valid @RequestBody LoginRequest request
  ) {

    return ResponseEntity.ok(service.login(request));
  }
}