package com.clinic.commoncore.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class KeycloakIssuerResolver {

    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String issuerUri;

    private final Environment env;

    public KeycloakIssuerResolver(Environment env) {
        this.env = env;
    }

    public String resolveIssuer() {
        // Modern profile check (replaces deprecated acceptsProfiles)
        boolean isDocker = Arrays.asList(env.getActiveProfiles()).contains("docker")
                || "docker".equals(System.getenv("SPRING_PROFILES_ACTIVE"));

        return isDocker ?
                issuerUri.replace("localhost", "keycloak") :
                issuerUri;
    }
}