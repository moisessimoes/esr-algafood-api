package com.algaworks.algafood.core.springdoc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

import com.algaworks.algafood.api.exceptionHandler.Field;
import com.algaworks.algafood.api.exceptionHandler.Problem;

import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.OAuthScope;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.tags.Tag;

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
	
	private static final String BadRequestResponse = "BadRequestResponse";
	private static final String NotFoundResponse = "NotFoundResponse";
	private static final String NotAcceptableResponse = "NotAcceptableResponse";
	private static final String InternalServerErrorResponse = "InternalServerErrorResponse";
	
	@Bean
	public OpenAPI openApi() {
		
		return new OpenAPI().info(getInfo())
							.tags(getTags())
							.components(new Components().schemas(getSchemas())
							.responses(getResponses()));
	}
	
	
	
	@Bean
	public OpenApiCustomiser openApiCustomiser() {
		
		//26.11. Descrevendo códigos de status de respostas de forma global
		
		return openApi -> {
			
			  openApi.getPaths()
					 .values()
					 .forEach(pathItem -> pathItem.readOperationsMap()
							 .forEach((httpMethod, operation) -> {
								 
								 var responses = operation.getResponses();
								 
								 switch (httpMethod) {
								 
								case GET:
									
									//responses.addApiResponse("404", new ApiResponse().$ref(NotFoundResponse));
									responses.addApiResponse("406", new ApiResponse().$ref(NotAcceptableResponse));
									responses.addApiResponse("500", new ApiResponse().$ref(InternalServerErrorResponse));
									
									break;
									
								case POST:
									
									responses.addApiResponse("400", new ApiResponse().$ref(BadRequestResponse));
									responses.addApiResponse("500", new ApiResponse().$ref(InternalServerErrorResponse));
									
									break;
									
								case PUT:
								
									//responses.addApiResponse("404", new ApiResponse().$ref(NotFoundResponse));
									responses.addApiResponse("400", new ApiResponse().$ref(BadRequestResponse));
									responses.addApiResponse("500", new ApiResponse().$ref(InternalServerErrorResponse));
									
									break;
									
								case DELETE:
									
									//responses.addApiResponse("404", new ApiResponse().$ref(NotFoundResponse));
									responses.addApiResponse("500", new ApiResponse().$ref(InternalServerErrorResponse));
									
									break;

								default:
									responses.addApiResponse("500", new ApiResponse().$ref(InternalServerErrorResponse));
									break;
								}
								 
							 })
					);
			};
	}
	
	
	@SuppressWarnings("rawtypes")
	private Map<String, Schema> getSchemas() {
		
		//26.14. Descrevendo o modelo de representação de problema
		
		final Map<String, Schema> schemaMap = new HashMap<>();
		
		var problemSchema = ModelConverters.getInstance().read(Problem.class);
		var fieldSchema = ModelConverters.getInstance().read(Field.class);
		
		schemaMap.putAll(problemSchema);
		schemaMap.putAll(fieldSchema);
		
		return schemaMap;
	}
	
	
	private Map<String, ApiResponse> getResponses() {
		
		//26.15. Referenciando modelo de representação de problema com códigos de status de erro
		
		final Map<String, ApiResponse> apiResponseMap = new HashMap<>();
		
		var content = new Content().addMediaType(MediaType.APPLICATION_JSON_VALUE, 
												new io.swagger.v3.oas.models.media.MediaType().schema(new Schema<Problem>().$ref("Problema")));
		
		apiResponseMap.put(BadRequestResponse, new ApiResponse().description("Requisição inválida").content(content));
		apiResponseMap.put(NotFoundResponse, new ApiResponse().description("Recurso não encontrado").content(content));
		apiResponseMap.put(NotAcceptableResponse, new ApiResponse().description("Recurso não possui representação que poderia ser aceita pelo consumidor").content(content));
		apiResponseMap.put(InternalServerErrorResponse, new ApiResponse().description("Erro interno no servidor").content(content));
		
		return apiResponseMap;
	}
	
	
//	@Bean
//	public OpenApiCustomiser openApiCustomiser() {
//		
//		//26.11. Descrevendo códigos de status de respostas de forma global
//		
//		return openApi -> {
//			
//			openApi.getPaths()
//			.values()
//			.stream()
//			.flatMap(pathItem -> pathItem.readOperations().stream())
//			.forEach(operation -> {
//				
//				var responses = operation.getResponses();
//				
//				var apiResponseInternalError = new ApiResponse().description("Erro interno no servidor");
//				var apiResponseNotFoundError = new ApiResponse().description("Recurso não encontrado");
//				var apiResponseNoRepresentationError = new ApiResponse().description("Recurso não possui representação que poderia ser aceita pelo consumidor");
//				
//				responses.addApiResponse("500", apiResponseInternalError);
//				responses.addApiResponse("404", apiResponseNotFoundError);
//				responses.addApiResponse("406", apiResponseNoRepresentationError);
//				
//			});
//		};
//	}
	
	
	private Info getInfo() {
		
		return new Info().title("AlgaFood API")
						 .version("v1")
						 .description("REST API do AlgaFood")
						 .license(new License().name("Spring Doc")
									  		   .url("https://springdoc.org/"));
	}
	
	
	private List<Tag> getTags() {
		
		return Arrays.asList(
				
				new Tag().name("Cidades").description("Gerencia as Cidades"),
				new Tag().name("Cozinhas").description("Gerencia as Cozinhas"),
				new Tag().name("Estados").description("Gerencia os Estados"),
				new Tag().name("Usuários").description("Gerencia os Usuários"),
				new Tag().name("Formas de Pagamento").description("Gerencia as Formas de Pagamento"),
				new Tag().name("Pedidos").description("Gerencia os Pedidos"),
				new Tag().name("Grupos").description("Gerencia os Grupos"),
				new Tag().name("Permissões").description("Gerencia as Permissões"),
				new Tag().name("Restaurantes").description("Gerencia os Restaurantes"),
				new Tag().name("Estatísticas").description("Gerencia as Estatísticas"),
				new Tag().name("Restaurante - Formas de Pagamento").description("Gerencia as Formas de Pagamento dos Restaurantes"),
				new Tag().name("Restaurante - Produtos").description("Gerencia os Produtos dos Restaurantes"),
				new Tag().name("Restaurante - Responsáveis").description("Gerencia os Responsáveis dos Restaurantes"),
				new Tag().name("Ponto de Entrada Raíz")
			);
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