package com.algaworks.algafood.core.squiggly;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.stereotype.Component;

@Component
public class TomcatCustomizer implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {
	
	// Referências:
	// - https://stackoverflow.com/a/53613678
	// - https://tomcat.apache.org/tomcat-8.5-doc/config/http.html
	// - https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html#howto-configure-webserver
	
	@Override
    public void customize(TomcatServletWebServerFactory factory) {
		
		/*O trecho abaixo está comentado, pois quando peguei durante a aula, aqui no Java
		 * mostrou que esse metodo setAttribute está depreciado
		 * 
		 * factory.addConnectorCustomizers(connector -> connector.setAttribute("relaxedQueryChars", "[]"));
		 * 
		 * Então resolvi usar o metodo que o Java recomendou, que é o está em uso abaixo
		 * 
		 * */
		
		factory.addConnectorCustomizers(connector -> connector.setProperty("relaxedQueryChars", "[]"));
    }
}