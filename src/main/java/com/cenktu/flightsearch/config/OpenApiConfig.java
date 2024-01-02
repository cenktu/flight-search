package com.cenktu.flightsearch.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info= @Info(
                contact = @Contact(
                        name = "Ali Cenk Turpculu",
                        email = "cenk.turpculu@gmail.com"
                ),
                description = "OpenAPI documentation for Flight Search Application",
                title = "OpenAPI specification for Flight Search",
                version = "1.0",
                termsOfService = "Terms of Service"
        ),
        servers = {
                @Server(
                        description = "Localhost",
                        url = "http://localhost:8080"
                )
        }
)
public class OpenApiConfig {
}
