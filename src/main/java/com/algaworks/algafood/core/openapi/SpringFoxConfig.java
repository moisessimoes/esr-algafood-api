package com.algaworks.algafood.core.openapi;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLStreamHandler;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.algaworks.algafood.api.controller.openapi.model.CozinhasModelOpenApi;
import com.algaworks.algafood.api.controller.openapi.model.PageableModelOpenApi;
import com.algaworks.algafood.api.exceptionHandler.Problem;
import com.algaworks.algafood.api.model.CozinhaModel;
import com.fasterxml.classmate.TypeResolver;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class) //18.11. Descrevendo restrições de validação de propriedades do modelo
public class SpringFoxConfig implements WebMvcConfigurer {
	
	@Bean
	public Docket apiDocket() {
		
		var typeResolver = new TypeResolver();
		
		return new Docket(DocumentationType.SWAGGER_2)
			  .select()
		      .apis(RequestHandlerSelectors.basePackage("com.algaworks.algafood.api"))
		      .paths(PathSelectors.any())
		      .build()
		      .useDefaultResponseMessages(false)
		      .globalResponseMessage(RequestMethod.GET, globalGetResponseMessages()) //18.12. Descrevendo códigos de status de respostas de forma global
		      .globalResponseMessage(RequestMethod.POST, globalPostResponseMessages()) //18.13. Desafio: descrevendo códigos de status de respostas de forma global
		      .globalResponseMessage(RequestMethod.PUT, globalPutResponseMessages()) //18.13. Desafio: descrevendo códigos de status de respostas de forma global
		      .globalResponseMessage(RequestMethod.DELETE, globalDeleteResponseMessages()) //18.13. Desafio: descrevendo códigos de status de respostas de forma global
		      .additionalModels(typeResolver.resolve(Problem.class))
		      .directModelSubstitute(Pageable.class, PageableModelOpenApi.class) //18.20. Corrigindo documentação com substituição de Pageable
		      .alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(Page.class, CozinhaModel.class), CozinhasModelOpenApi.class)) //18.21. Corrigindo documentação com substituição de Page
		      .ignoredParameterTypes(ServletWebRequest.class, URL.class, URI.class, URLStreamHandler.class, Resource.class, File.class, InputStream.class) //18.23. Ignorando tipos de parâmetros de operações na documentação
//		      .globalOperationParameters(Arrays.asList(
//		    		  //18.25. Descrevendo parâmetros globais em operações
//		    		  new ParameterBuilder().name("campos")
//		    		  						.description("Nomes das propriedades para filtrar na resposta, separados por vírgula.")
//		    		  						.parameterType("query")
//		    		  						.modelRef(new ModelRef("string"))
//		    		  						.build()
//		    		  ))
		      .apiInfo(apiInfo())
		      .tags(new Tag("Cidades", "Gerencia as cidades"))
		      .tags(new Tag("Cozinhas", "Gerencia as cozinhas"))
		      .tags(new Tag("Estados", "Gerencia os estados"))
		   	  .tags(new Tag("Estatisticas", "Gerencia as estatisticas"))
		   	  .tags(new Tag("Fluxo Pedido", "Gerencia os fluxos dos pedidos"))
		   	  .tags(new Tag("Formas de Pagamento", "Gerencia as formas de pagamento"))
		   	  .tags(new Tag("Grupos", "Gerencia os grupos"))
		   	  .tags(new Tag("Permissões", "Gerencia as permissões"))
		   	  .tags(new Tag("Pedidos", "Gerencia os pedidos"))
		   	  .tags(new Tag("Restaurantes", "Gerencia os restaurantes"))
		   	  .tags(new Tag("Restaurante - Formas de Pagamento", "Gerencia as formas de pagamento dos restaurantes"))
		   	  .tags(new Tag("Restaurante - Produtos", "Gerencia os produtos dos restaurantes"))
		   	  .tags(new Tag("Restaurante - Responsáveis", "Gerencia os responsáveis dos restaurantes"))
		   	  .tags(new Tag("Restaurante - Produtos - Fotos", "Gerencia as fotos dos produtos dos restaurantes"))
		   	  .tags(new Tag("Usuários", "Gerencia os usuários"))
		   	  .tags(new Tag("Grupo de Usuários", "Gerencia os grupos de usuários"));
	}
	
	
	private List<ResponseMessage> globalGetResponseMessages() {
		
		//18.12. Descrevendo códigos de status de respostas de forma global
		
		return Arrays.asList(
				
				new ResponseMessageBuilder().code(HttpStatus.INTERNAL_SERVER_ERROR.value())
											.message("Erro interno do servidor")
											.build(),
											
				new ResponseMessageBuilder().code(HttpStatus.NOT_ACCEPTABLE.value())
											.message("Recurso não possui representação que poderia ser aceita pelo consumidor")
											.build(),
				
				new ResponseMessageBuilder().code(HttpStatus.NOT_FOUND.value())
											.message("Recurso não encontrado")
											.build()
				
		);
	}
	
	
	private List<ResponseMessage> globalPostResponseMessages() {
		
		//18.13. Desafio: descrevendo códigos de status de respostas de forma global
		
		return Arrays.asList(
				
				new ResponseMessageBuilder().code(HttpStatus.INTERNAL_SERVER_ERROR.value())
									 .message("Erro interno do servidor")
									 .responseModel(new ModelRef("Problema"))
									 .build(),
				
				new ResponseMessageBuilder().code(HttpStatus.NOT_ACCEPTABLE.value())
								     .message("Recurso não possui representação que poderia ser aceita pelo consumidor")
								     .build(),
				
				new ResponseMessageBuilder().code(HttpStatus.BAD_REQUEST.value())
									 .message("As informações enviadas estão inválidas. Revise os dados e tente novamente.")
									 .responseModel(new ModelRef("Problema"))
									 .build(),
									 
				new ResponseMessageBuilder().code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
									 .message("Recurso não suporta o tipo de mídia informado")
									 .responseModel(new ModelRef("Problema"))
									 .build()
				
		);
	}
	
	
	private List<ResponseMessage> globalPutResponseMessages() {
		
		//18.13. Desafio: descrevendo códigos de status de respostas de forma global
		
		return Arrays.asList(
				
				new ResponseMessageBuilder().code(HttpStatus.INTERNAL_SERVER_ERROR.value())
									 .message("Erro interno do servidor")
									 .responseModel(new ModelRef("Problema"))
									 .build(),
				
				new ResponseMessageBuilder().code(HttpStatus.NOT_ACCEPTABLE.value())
									 .message("Recurso não possui representação que poderia ser aceita pelo consumidor")
									 .build(),
				
				new ResponseMessageBuilder().code(HttpStatus.BAD_REQUEST.value())
									 .message("As informações enviadas estão inválidas. Revise os dados e tente novamente.")
									 .responseModel(new ModelRef("Problema"))
									 .build(),
				
				new ResponseMessageBuilder().code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
									 .message("Recurso não suporta o tipo de mídia informado")
									 .responseModel(new ModelRef("Problema"))
									 .build()
				
		);
	}
	
	
	private List<ResponseMessage> globalDeleteResponseMessages() {
		
		//18.13. Desafio: descrevendo códigos de status de respostas de forma global
		
		return Arrays.asList(
				
				new ResponseMessageBuilder().code(HttpStatus.INTERNAL_SERVER_ERROR.value())
											.message("Erro interno do servidor")
											.responseModel(new ModelRef("Problema"))
											.build(),
				
				new ResponseMessageBuilder().code(HttpStatus.NOT_ACCEPTABLE.value())
											.message("Recurso não possui representação que poderia ser aceita pelo consumidor")
											.build(),
				
				new ResponseMessageBuilder().code(HttpStatus.BAD_REQUEST.value())
											.message("As informações enviadas estão inválidas. Revise os dados e tente novamente.")
											.responseModel(new ModelRef("Problema"))
											.build(),
				
				new ResponseMessageBuilder().code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
											.message("Recurso não suporta o tipo de mídia informado")
											.responseModel(new ModelRef("Problema"))
											.build()
				
		);
	}
	
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Algafood API")
								   .description("API aberta para clientes e restaurantes")
								   .version("1.0")
								   .contact(new Contact("AlgaWorks", "https://www.algaworks.com", "contato@algaworks.com.br"))
								   .build();
	}
	
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	}
}