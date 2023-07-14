package com.algaworks.algafood.core.springdoc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.OAuthScope;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
@SecurityScheme(name = "security_auth",
type = SecuritySchemeType.OAUTH2,
flows = @OAuthFlows(authorizationCode = @OAuthFlow(
        authorizationUrl = "${springdoc.oAuthFlow.authorizationUrl}",
        tokenUrl = "${springdoc.oAuthFlow.tokenUrl}",
        scopes = {
                @OAuthScope(name = "READ", description = "read scope"),
                @OAuthScope(name = "WRITE", description = "write scope")
        }
)))
public class SpringDocConfig {
	
	//26.3. Adicionando o SpringDoc no projeto
	
	@Bean
	public OpenAPI openApi() {
		
		return new OpenAPI().info(new Info().title("AlgaFood API")
											.version("v1")
											.description("REST API do AlgaFood")
											.license(new License().name("Spring Doc")
																  .url("https://springdoc.org/")));
	}
	
	
	//26.4. Configurando múltiplas documentações em um só projeto
//	@Bean
//	public GroupedOpenApi groupedOpenApiCliente() {
//		
//		return GroupedOpenApi.builder()
//							 .group("AlgaFood API Cliente")
//							 .pathsToMatch("/cliente/v1/**")
//							 .addOpenApiCustomiser(openApi -> {
//								 
//								 openApi.info(new Info().title("AlgaFood API Cliente")
//											.version("v1")
//											.description("REST API do AlgaFood")
//											.license(new License().name("Spring Doc")
//																  .url("https://springdoc.org/")));
//								 
//							 }).build();
//	}
}