package org.alibou.demo.auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {
  String token;
}