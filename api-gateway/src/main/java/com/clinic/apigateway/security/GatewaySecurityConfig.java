package com.clinic.apigateway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * Secures API Gateway in docker/prod:
 * - /actuator/health public
 * - Swagger UI only for ROLE_PARTNER or ROLE_ADMIN
 * - Aggregated /v3/api-docs require authentication
 */
@Profile("docker")
@Configuration
@EnableWebFluxSecurity
@Import(BaseSecurityConfig.class) // imports the @Bean reactiveJwtAuthConverter()
public class GatewaySecurityConfig {

    private final org.springframework.core.convert.converter.Converter<
            org.springframework.security.oauth2.jwt.Jwt,
            reactor.core.publisher.Mono<org.springframework.security.authentication.AbstractAuthenticationToken>> reactiveJwtAuthConverter;

    // explicit constructor injection (no @RequiredArgsConstructor)
    public GatewaySecurityConfig(
            org.springframework.core.convert.converter.Converter<
                    org.springframework.security.oauth2.jwt.Jwt,
                    reactor.core.publisher.Mono<org.springframework.security.authentication.AbstractAuthenticationToken>> reactiveJwtAuthConverter
    ) {
        this.reactiveJwtAuthConverter = reactiveJwtAuthConverter;
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(ex -> ex
                        .pathMatchers("/actuator/health", "/actuator/health/**").permitAll()
                        .pathMatchers("/swagger-ui.html", "/swagger-ui/**", "/webjars/**")
                        .hasAnyRole("PARTNER", "ADMIN")
                        .pathMatchers("/patient-service/v3/api-docs", "/appointment-service/v3/api-docs")
                        .authenticated()
                        .anyExchange().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt ->
                        // JDK17-safe, non-deprecated style
                        jwt.jwtAuthenticationConverter(reactiveJwtAuthConverter)
                ))
                .build();
    }
}
