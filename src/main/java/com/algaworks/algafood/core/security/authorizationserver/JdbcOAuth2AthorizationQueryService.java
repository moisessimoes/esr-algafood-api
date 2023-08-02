package com.algaworks.algafood.core.security.authorizationserver;

import java.util.List;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;

public class JdbcOAuth2AthorizationQueryService implements OAuth2AuthorizationQueryService {
	
	//27.20. Criando uma página de listagem dos clientes com consentimentos permitidos
	
	private JdbcOperations jdbcOperations;
	private RowMapper<RegisteredClient> registeredClientRowMapper;
	private RowMapper<OAuth2Authorization> oAuth2AuthorizationRowMapper;
	
	private final String LIST_AUTHORIZED_CLIENTS = "select rc.* from oauth2_authorization_consent c " +
            "inner join oauth2_registered_client rc on rc.id = c.registered_client_id " +
            "where c.principal_name = ? ";
	
	private final String LIST_AUTHORIZATIONS_QUERY = "select a.* from oauth2_authorization a " +
            "inner join oauth2_registered_client c on c.id = a.registered_client_id " +
            "where a.principal_name = ? " +
            "and a.registered_client_id  = ? ";

	public JdbcOAuth2AthorizationQueryService(JdbcOperations jdbcOperations, RegisteredClientRepository registeredClientRepository) {
		this.jdbcOperations = jdbcOperations;
		this.registeredClientRowMapper = new JdbcRegisteredClientRepository.RegisteredClientRowMapper();
		this.oAuth2AuthorizationRowMapper = new JdbcOAuth2AuthorizationService.OAuth2AuthorizationRowMapper(registeredClientRepository);
	}



	@Override
	public List<RegisteredClient> listClientsWithConsent(String principalName) {
		return this.jdbcOperations.query(LIST_AUTHORIZED_CLIENTS, registeredClientRowMapper, principalName);
	}



	@Override
	public List<OAuth2Authorization> listAuthorizations(String principalName, String clientId) {
		//27.21. Revogando consentimentos e autorizações dos clientes
		return this.jdbcOperations.query(LIST_AUTHORIZATIONS_QUERY, oAuth2AuthorizationRowMapper, principalName, clientId);
	}
}