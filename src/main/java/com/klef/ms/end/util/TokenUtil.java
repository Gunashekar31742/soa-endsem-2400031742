package com.klef.ms.end.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TokenUtil {
    private final String validToken;

    public TokenUtil(@Value("${app.security.token}") String validToken) {
        this.validToken = validToken;
    }

    public boolean isValid(String token) {
        return token != null && MessageDigest.isEqual(
                validToken.getBytes(StandardCharsets.UTF_8),
                token.getBytes(StandardCharsets.UTF_8));
    }
}
