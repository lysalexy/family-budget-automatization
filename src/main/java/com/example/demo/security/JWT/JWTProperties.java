package com.example.demo.security.JWT;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "jwt")
public class JWTProperties {
    private String secretKey = "verySecretKey";
    private long validityImMs = 10000;

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public long getValidityImMs() {
        return validityImMs;
    }

    public void setValidityImMs(long validityImMs) {
        this.validityImMs = validityImMs;
    }
}
