package com.algaworks.algafood.core.web;

import javax.servlet.Filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
//	@Autowired
//	private ApiDeprecationHandler apiDeprecationHandler;
	
	//16.6. Habilitando CORS globalmente no projeto da API
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		
		registry.addMapping("/**")
				.allowedMethods("*");
//				.allowedOrigins("*")
//				.maxAge(30)
	}
	
	
//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		//20.18. Depreciando uma versão da API
//		registry.addInterceptor(apiDeprecationHandler);
//	}
	
	
//	@Override
//	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//		//20.12. Definindo a versão padrão da API quando o Media Type não é informado
//		configurer.defaultContentType(AlgaMediaType.V2_APPLICATION_JSON);
//	}
	
	
	//17.5. Implementando requisições condicionais com Shallow ETags
	@Bean
	public Filter shallowEtagHeaderFilter() {
		return new ShallowEtagHeaderFilter();
	}
}