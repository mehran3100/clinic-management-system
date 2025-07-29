package com.clinic.commoncore.security;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;

@Configuration
@ConditionalOnProperty(name = "spring.security.oauth2.resourceserver.jwt.issuer-uri")
public class KeycloakJwtAutoConfiguration {

    @Bean
    public JwtDecoder jwtDecoder(KeycloakIssuerResolver resolver) {
        return JwtDecoders.fromIssuerLocation(resolver.resolveIssuer());
    }

    @Bean
    public KeycloakIssuerResolver issuerResolver(Environment env) {
        return new KeycloakIssuerResolver(env);
    }
}