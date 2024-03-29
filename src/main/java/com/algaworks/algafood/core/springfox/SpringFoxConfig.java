package com.algaworks.algafood.core.springfox;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
//@EnableSwagger2
//@Import(BeanValidatorPluginsConfiguration.class) //18.11. Descrevendo restrições de validação de propriedades do modelo
public class SpringFoxConfig implements WebMvcConfigurer {
	
	//Para remover a documentação de APIs desligadas, basta apagar o metodo Docket ou comentar a anoração @Bean
	
//	@Bean
//	public Docket apiDocketV1() {
//		
//		var typeResolver = new TypeResolver();
//		
//		return new Docket(DocumentationType.SWAGGER_2)
//			  .groupName("V1")
//			  .select()
//		      .apis(RequestHandlerSelectors.basePackage("com.algaworks.algafood.api"))
//		      //.paths(PathSelectors.any())
//		      .paths(PathSelectors.ant("/v1/**")) //20.16. Gerando documentação das versões da API com SpringFox e Swagger UI
//		      .build()
//		      .useDefaultResponseMessages(false)
//		      .globalResponseMessage(RequestMethod.GET, globalGetResponseMessages()) //18.12. Descrevendo códigos de status de respostas de forma global
//		      .globalResponseMessage(RequestMethod.POST, globalPostResponseMessages()) //18.13. Desafio: descrevendo códigos de status de respostas de forma global
//		      .globalResponseMessage(RequestMethod.PUT, globalPutResponseMessages()) //18.13. Desafio: descrevendo códigos de status de respostas de forma global
//		      .globalResponseMessage(RequestMethod.DELETE, globalDeleteResponseMessages()) //18.13. Desafio: descrevendo códigos de status de respostas de forma global
//		      
//		      .additionalModels(typeResolver.resolve(Problem.class))
//		      
//		      .directModelSubstitute(Pageable.class, PageableModelOpenApi.class) //18.20. Corrigindo documentação com substituição de Pageable
//		      
//		      .directModelSubstitute(Links.class, LinksModelOpenApi.class) //19.39. Corrigindo as propriedades de links na documentação
//		      
//		      //18.21. Corrigindo documentação com substituição de Page
//		      //.alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(Page.class, CozinhaModel.class), CozinhasModelOpenApi.class))
//		      
//		      //19.41. Corrigindo a paginação na documentação
//		      .alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(PagedModel.class, CozinhaModel.class), CozinhasModelOpenApi.class))
//		      
//		      //19.40. Corrigindo a documentação dos endpoints de cidades
//		      .alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(CollectionModel.class, CidadeModel.class), CidadesModelOpenApi.class))
//		      
//		      //19.42. Desafio: corrigindo a documentação dos endpoints de estados
//		      .alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(CollectionModel.class, EstadoModel.class), EstadosModelOpenApi.class))
//		      
//		      //19.43. Desafio: corrigindo a documentação dos endpoints de formas de pagamento
//		      .alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(CollectionModel.class, FormaPagamentoModel.class), FormasPagamentoModelOpenApi.class))
//		      
//		      //19.44. Desafio: corrigindo a documentação dos endpoints de grupos
//		      .alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(CollectionModel.class, GrupoModel.class), GruposModelOpenApi.class))
//		      
//		      //19.44. Desafio: corrigindo a documentação dos endpoints de grupos
//		      .alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(CollectionModel.class, PermissaoModel.class), GrupoPermissoesModelOpenApi.class))
//		      
//		      //19.45. Desafio: corrigindo a documentação dos endpoint de pedidos (paginação)
//		      .alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(PagedModel.class, PedidoResumoModel.class), PedidosModelOpenApi.class))
//		      
//		      //19.46. Desafio: corrigindo a documentação dos endpoints de produtos
//		      .alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(CollectionModel.class, ProdutoModel.class), ProdutosModelOpenApi.class))
//		      
//		      //19.47. Desafio: corrigindo a documentação dos endpoints de restaurantes e usuários
//		      .alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(CollectionModel.class, RestauranteBasicoModel.class), RestaurantesModelOpenApi.class))
//		      
//		      //19.47. Desafio: corrigindo a documentação dos endpoints de restaurantes e usuários
//		      .alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(CollectionModel.class, UsuarioModel.class), UsuariosModelOpenApi.class)) 
//		      
//		      //18.23. Ignorando tipos de parâmetros de operações na documentação
//		      .ignoredParameterTypes(ServletWebRequest.class, URL.class, URI.class, URLStreamHandler.class, Resource.class, File.class, InputStream.class)
//		      
////		      .globalOperationParameters(Arrays.asList(
////		    		  //18.25. Descrevendo parâmetros globais em operações
////		    		  new ParameterBuilder().name("campos")
////		    		  						.description("Nomes das propriedades para filtrar na resposta, separados por vírgula.")
////		    		  						.parameterType("query")
////		    		  						.modelRef(new ModelRef("string"))
////		    		  						.build()
////		    		  ))
//		      
//		      .apiInfo(apiInfoV1())
//		      .tags(new Tag("Cidades", "Gerencia as cidades"))
//		      .tags(new Tag("Cozinhas", "Gerencia as cozinhas"))
//		      .tags(new Tag("Estados", "Gerencia os estados"))
//		   	  .tags(new Tag("Estatisticas", "Gerencia as estatisticas"))
//		   	  .tags(new Tag("Fluxo Pedido", "Gerencia os fluxos dos pedidos"))
//		   	  .tags(new Tag("Formas de Pagamento", "Gerencia as formas de pagamento"))
//		   	  .tags(new Tag("Grupos", "Gerencia os grupos"))
//		   	  .tags(new Tag("Grupo de Permissões", "Gerencia os grupos de permissões"))
//		   	  .tags(new Tag("Permissões", "Gerencia as permissões"))
//		   	  .tags(new Tag("Pedidos", "Gerencia os pedidos"))
//		   	  .tags(new Tag("Restaurantes", "Gerencia os restaurantes"))
//		   	  .tags(new Tag("Restaurante - Formas de Pagamento", "Gerencia as formas de pagamento dos restaurantes"))
//		   	  .tags(new Tag("Restaurante - Produtos", "Gerencia os produtos dos restaurantes"))
//		   	  .tags(new Tag("Restaurante - Responsáveis", "Gerencia os responsáveis dos restaurantes"))
//		   	  .tags(new Tag("Restaurante - Produtos - Fotos", "Gerencia as fotos dos produtos dos restaurantes"))
//		   	  .tags(new Tag("Usuários", "Gerencia os usuários"))
//		   	  .tags(new Tag("Grupo de Usuários", "Gerencia os grupos de usuários"))
//		   	  .tags(new Tag("Ponto de Entrada Raíz", "Ponto de Entrada Raíz das APIs"))
//		   	  
//		
//		   	  //23.42. Ajustando a documentação da API para suporte a OAuth2
//			  .securitySchemes(Arrays.asList(securityScheme()))
//			  .securityContexts(Arrays.asList(securityContext()));
//	}
//	
//	//23.42. Ajustando a documentação da API para suporte a OAuth2
//	
//	private SecurityScheme securityScheme() {
//		
//		return new OAuthBuilder().name("AlgaFood")
//				                 .grantTypes(grantTypes())
//				                 .scopes(scopes())
//				                 .build();
//	}
//	
//	private SecurityContext securityContext() {
//		
//		var securityReference = SecurityReference.builder()
//												 .reference("AlgaFood")
//												 .scopes(scopes().toArray(new AuthorizationScope[0]))
//												 .build();
//		
//		return SecurityContext.builder()
//							  .securityReferences(Arrays.asList(securityReference))
//							  .forPaths(PathSelectors.any())
//							  .build();
//	}
//	
//	private List<GrantType> grantTypes() {
//		return Arrays.asList(new ResourceOwnerPasswordCredentialsGrant("/oauth/token"));
//	}
//	
//	private List<AuthorizationScope> scopes() {
//		return Arrays.asList(new AuthorizationScope("READ", "Acesso de leitura"), new AuthorizationScope("WRITE", "Acesso de escrita"));
//	}
//	
//	//==========================================================================================================
//	
//	
//	@Bean
//	public Docket apiDocketV2() {
//		
//		////20.16. Gerando documentação das versões da API com SpringFox e Swagger UI
//		
//		var typeResolver = new TypeResolver();
//		
//		return new Docket(DocumentationType.SWAGGER_2)
//				.groupName("V2")
//				.select()
//				.apis(RequestHandlerSelectors.basePackage("com.algaworks.algafood.api"))
//				//.paths(PathSelectors.any())
//				.paths(PathSelectors.ant("/v2/**")) //20.16. Gerando documentação das versões da API com SpringFox e Swagger UI
//				.build()
//				.useDefaultResponseMessages(false)
//				.globalResponseMessage(RequestMethod.GET, globalGetResponseMessages()) //18.12. Descrevendo códigos de status de respostas de forma global
//				.globalResponseMessage(RequestMethod.POST, globalPostResponseMessages()) //18.13. Desafio: descrevendo códigos de status de respostas de forma global
//				.globalResponseMessage(RequestMethod.PUT, globalPutResponseMessages()) //18.13. Desafio: descrevendo códigos de status de respostas de forma global
//				.globalResponseMessage(RequestMethod.DELETE, globalDeleteResponseMessages()) //18.13. Desafio: descrevendo códigos de status de respostas de forma global
//				
//				.additionalModels(typeResolver.resolve(Problem.class))
//				
//				.directModelSubstitute(Pageable.class, PageableModelOpenApi.class) //18.20. Corrigindo documentação com substituição de Pageable
//				
//				.directModelSubstitute(Links.class, LinksModelOpenApi.class) //19.39. Corrigindo as propriedades de links na documentação
//				
//				//18.21. Corrigindo documentação com substituição de Page
//				//.alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(Page.class, CozinhaModel.class), CozinhasModelOpenApi.class))
//				
//				//19.41. Corrigindo a paginação na documentação
//				.alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(PagedModel.class, CozinhaModelV2.class), CozinhasModelOpenApiV2.class))
//				
//				//19.40. Corrigindo a documentação dos endpoints de cidades
//				.alternateTypeRules(AlternateTypeRules.newRule(typeResolver.resolve(CollectionModel.class, CidadeModelV2.class), CidadesModelOpenApiV2.class))
//				
//				//18.23. Ignorando tipos de parâmetros de operações na documentação
//				.ignoredParameterTypes(ServletWebRequest.class, URL.class, URI.class, URLStreamHandler.class, Resource.class, File.class, InputStream.class)
//				
//				.apiInfo(apiInfoV2())
//				.tags(new Tag("Cidades", "Gerencia as cidades"))
//				.tags(new Tag("Cozinhas", "Gerencia as cozinhas"));
//	}
//	
//	
//	private List<ResponseMessage> globalGetResponseMessages() {
//		
//		//18.12. Descrevendo códigos de status de respostas de forma global
//		
//		return Arrays.asList(
//				
//				new ResponseMessageBuilder().code(HttpStatus.INTERNAL_SERVER_ERROR.value())
//											.message("Erro interno do servidor")
//											.build(),
//											
//				new ResponseMessageBuilder().code(HttpStatus.NOT_ACCEPTABLE.value())
//											.message("Recurso não possui representação que poderia ser aceita pelo consumidor")
//											.build(),
//				
//				new ResponseMessageBuilder().code(HttpStatus.NOT_FOUND.value())
//											.message("Recurso não encontrado")
//											.build()
//				
//		);
//	}
//	
//	
//	private List<ResponseMessage> globalPostResponseMessages() {
//		
//		//18.13. Desafio: descrevendo códigos de status de respostas de forma global
//		
//		return Arrays.asList(
//				
//				new ResponseMessageBuilder().code(HttpStatus.INTERNAL_SERVER_ERROR.value())
//									 .message("Erro interno do servidor")
//									 .responseModel(new ModelRef("Problema"))
//									 .build(),
//				
//				new ResponseMessageBuilder().code(HttpStatus.NOT_ACCEPTABLE.value())
//								     .message("Recurso não possui representação que poderia ser aceita pelo consumidor")
//								     .build(),
//				
//				new ResponseMessageBuilder().code(HttpStatus.BAD_REQUEST.value())
//									 .message("As informações enviadas estão inválidas. Revise os dados e tente novamente.")
//									 .responseModel(new ModelRef("Problema"))
//									 .build(),
//									 
//				new ResponseMessageBuilder().code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
//									 .message("Recurso não suporta o tipo de mídia informado")
//									 .responseModel(new ModelRef("Problema"))
//									 .build()
//				
//		);
//	}
//	
//	
//	private List<ResponseMessage> globalPutResponseMessages() {
//		
//		//18.13. Desafio: descrevendo códigos de status de respostas de forma global
//		
//		return Arrays.asList(
//				
//				new ResponseMessageBuilder().code(HttpStatus.INTERNAL_SERVER_ERROR.value())
//									 .message("Erro interno do servidor")
//									 .responseModel(new ModelRef("Problema"))
//									 .build(),
//				
//				new ResponseMessageBuilder().code(HttpStatus.NOT_ACCEPTABLE.value())
//									 .message("Recurso não possui representação que poderia ser aceita pelo consumidor")
//									 .build(),
//				
//				new ResponseMessageBuilder().code(HttpStatus.BAD_REQUEST.value())
//									 .message("As informações enviadas estão inválidas. Revise os dados e tente novamente.")
//									 .responseModel(new ModelRef("Problema"))
//									 .build(),
//				
//				new ResponseMessageBuilder().code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
//									 .message("Recurso não suporta o tipo de mídia informado")
//									 .responseModel(new ModelRef("Problema"))
//									 .build()
//				
//		);
//	}
//	
//	
//	private List<ResponseMessage> globalDeleteResponseMessages() {
//		
//		//18.13. Desafio: descrevendo códigos de status de respostas de forma global
//		
//		return Arrays.asList(
//				
//				new ResponseMessageBuilder().code(HttpStatus.INTERNAL_SERVER_ERROR.value())
//											.message("Erro interno do servidor")
//											.responseModel(new ModelRef("Problema"))
//											.build(),
//				
//				new ResponseMessageBuilder().code(HttpStatus.NOT_ACCEPTABLE.value())
//											.message("Recurso não possui representação que poderia ser aceita pelo consumidor")
//											.build(),
//				
//				new ResponseMessageBuilder().code(HttpStatus.BAD_REQUEST.value())
//											.message("As informações enviadas estão inválidas. Revise os dados e tente novamente.")
//											.responseModel(new ModelRef("Problema"))
//											.build(),
//				
//				new ResponseMessageBuilder().code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
//											.message("Recurso não suporta o tipo de mídia informado")
//											.responseModel(new ModelRef("Problema"))
//											.build()
//				
//		);
//	}
//	
//	
//	private ApiInfo apiInfoV1() {
//		return new ApiInfoBuilder().title("Algafood API - (Depreciada)")
//								   .description("API aberta para clientes e restaurantes. <br>"
//								   		+ "<strong>Essa versão da API está depreciada e será descontinuada a partir de 01/06/2023. Use a versão mais atual da API.")
//								   .version("1.0")
//								   .contact(new Contact("AlgaWorks", "https://www.algaworks.com", "contato@algaworks.com.br"))
//								   .build();
//	}
//	
//	private ApiInfo apiInfoV2() {
//		
//		////20.16. Gerando documentação das versões da API com SpringFox e Swagger UI
//		
//		return new ApiInfoBuilder().title("Algafood API")
//				.description("API aberta para clientes e restaurantes")
//				.version("2.0")
//				.contact(new Contact("AlgaWorks", "https://www.algaworks.com", "contato@algaworks.com.br"))
//				.build();
//	}
//	
//	
//	@Override
//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		
//		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
//		
//		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
//		
//	}
}