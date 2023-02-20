package com.timsanalytics.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI configure() {
        final String title = "Keycloak Server";
        final String securitySchemeName = "oidc";
        final String oidcUrl = "https://keycloak.timsanalytics.com/realms/vision/.well-known/openid-configuration";

        return new OpenAPI().addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(new Components().addSecuritySchemes(securitySchemeName,
                        new SecurityScheme().name(securitySchemeName)
                                .type(SecurityScheme.Type.OPENIDCONNECT)
                                .openIdConnectUrl(oidcUrl)))
                .info(new Info().title(title));
    }

}
