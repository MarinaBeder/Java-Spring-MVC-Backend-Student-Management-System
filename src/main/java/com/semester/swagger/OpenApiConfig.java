package com.semester.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(info = @Info(contact = @Contact(name = "Marina Beder", email = "marinabeder98@gmail.com"

), description = "I used @controller and @RestController to confirm that I can use two annotation", title = "Backend of Student Management System"

), servers = { @Server(description = "Local ENV", url = "http://localhost:9090") })
public class OpenApiConfig {
}