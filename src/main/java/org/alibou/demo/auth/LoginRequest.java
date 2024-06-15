package org.alibou.demo.auth;

import lombok.Data;

public record LoginRequest(
        String username,
        String password
) {
}
