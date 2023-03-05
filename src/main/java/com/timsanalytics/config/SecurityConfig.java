package com.timsanalytics.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(jsr250Enabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()

                .authorizeHttpRequests(authorize -> authorize
                                .requestMatchers("/swagger-ui/**")
                                .permitAll()

                                .requestMatchers("/v3/api-docs/**")
                                .permitAll()

                                .requestMatchers("/api/v1/ping")
                                .permitAll()

                                // WIDE OPEN
//                .requestMatchers("/**")
//                .permitAll()

                                .anyRequest()
                                .authenticated() // all other endpoints require auth
                ).oauth2ResourceServer().jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()));

        return http.build();
    }

    private Converter<Jwt, ? extends AbstractAuthenticationToken> jwtAuthenticationConverter() {
        JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
        jwtConverter.setJwtGrantedAuthoritiesConverter(new RealmRoleConverter());
        return jwtConverter;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        //configuration.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
        configuration.setAllowedOrigins(
                Arrays.asList(
                        "http://localhost:4200",
                        "https://keycloak-client.timsanalytics.com"
                )
        );
        configuration.setAllowedMethods(
                Arrays.asList(
                        "GET",
                        "POST",
                        "PUT",
                        "PATCH",
                        "DELETE",
                        "OPTIONS"
                )
        );
        configuration.setAllowedHeaders(
                Arrays.asList(
                        "accept",
                        "authorization",
                        "access-control-allow-headers",
                        "access-control-allow-method",
                        "content-type",
                        "origin",
                        "responsetype",
                        "x-auth-token"
                )
        );
        configuration.setExposedHeaders(Collections.singletonList("x-auth-token"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}


