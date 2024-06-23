package org.alibou.demo.auth.dto;


public record LoginRequest(
    String email,
    String password
) {
}