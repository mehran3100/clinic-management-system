package com.clinic.apigateway.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import reactor.core.publisher.Mono;

import java.util.*;

@Configuration
@EnableWebSecurity
@Slf4j
public class BaseSecurityConfig {

    @Bean
    public Converter<Jwt, Mono<AbstractAuthenticationToken>> reactiveJwtAuthConverter() {
        // 1) Include OAuth2 scopes (scope/scp) as SCOPE_*
        final JwtGrantedAuthoritiesConverter scopesConverter = new JwtGrantedAuthoritiesConverter();
        // (prefix is already "SCOPE_" by default; keep explicit for clarity)
        scopesConverter.setAuthorityPrefix("SCOPE_");

        return jwt -> {
            // start with scopes
            Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>(scopesConverter.convert(jwt));

            // 2) Realm roles: realm_access.roles -> ROLE_*
            Object realmAccessObj = jwt.getClaim("realm_access");
            if (realmAccessObj instanceof Map) {
                Object rolesObj = ((Map<?, ?>) realmAccessObj).get("roles");
                if (rolesObj instanceof Collection) {
                    for (Object role : (Collection<?>) rolesObj) {
                        if (role != null) {
                            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.toString()));
                        }
                    }
                } else {
                    log.debug("realm_access.roles missing or not a collection");
                }
            } else {
                log.debug("realm_access missing or not a map");
            }

            // 3) Client roles: resource_access.<client>.roles -> ROLE_*
            Object resourceAccessObj = jwt.getClaim("resource_access");
            if (resourceAccessObj instanceof Map) {
                Map<?, ?> resourceAccess = (Map<?, ?>) resourceAccessObj;
                for (Object clientEntryVal : resourceAccess.values()) {
                    if (clientEntryVal instanceof Map) {
                        Object clientRolesObj = ((Map<?, ?>) clientEntryVal).get("roles");
                        if (clientRolesObj instanceof Collection) {
                            for (Object role : (Collection<?>) clientRolesObj) {
                                if (role != null) {
                                    authorities.add(new SimpleGrantedAuthority("ROLE_" + role.toString()));
                                }
                            }
                        }
                    }
                }
            } else {
                log.debug("resource_access missing or not a map");
            }

            return Mono.just(new JwtAuthenticationToken(jwt, authorities));
        };
    }
}