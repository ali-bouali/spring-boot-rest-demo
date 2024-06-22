package org.alibou.demo.auth;


public record LoginRequest(
    String email,
    String password
) {
}