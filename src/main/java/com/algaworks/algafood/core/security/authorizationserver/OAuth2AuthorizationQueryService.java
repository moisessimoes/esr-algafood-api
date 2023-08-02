package com.algaworks.algafood.core.security.authorizationserver;

import java.util.List;

import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;

public interface OAuth2AuthorizationQueryService { 
	
	////27.20. Criando uma página de listagem dos clientes com consentimentos permitidos
	List<RegisteredClient> listClientsWithConsent(String principalName);
	
	//27.21. Revogando consentimentos e autorizações dos clientes
	List<OAuth2Authorization> listAuthorizations(String principalName, String clientId);

}