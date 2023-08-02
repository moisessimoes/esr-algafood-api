package com.algaworks.algafood.core.security.authorizationserver.old;

//import java.security.KeyPair;
//import java.security.interfaces.RSAPublicKey;
//import java.util.Arrays;
//
//import javax.sql.DataSource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.CompositeTokenGranter;
//import org.springframework.security.oauth2.provider.TokenGranter;
//import org.springframework.security.oauth2.provider.approval.ApprovalStore;
//import org.springframework.security.oauth2.provider.approval.TokenApprovalStore;
//import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
//import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
//import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
//
//import com.nimbusds.jose.JWSAlgorithm;
//import com.nimbusds.jose.jwk.JWKSet;
//import com.nimbusds.jose.jwk.KeyUse;
//import com.nimbusds.jose.jwk.RSAKey;

//@SuppressWarnings("deprecation")
//@Configuration
//@EnableAuthorizationServer
//public class AuthorizationServerConfigOld extends AuthorizationServerConfigurerAdapter {
	
	//22.8. Criando o projeto do Authorization Server com Spring Security OAuth2
	
	/*A maioria do que está sendo usado aqui está depreciado, mas em um modulo
	 * posterior, isso será corrigido para uma versão mais atualizada*/
	
//	@Autowired
//	private UserDetailsService UserDetailsService;
//	
//	@Autowired
//	private AuthenticationManager authenticationManager;
//	
//	//23.10. Desafio: criando bean de propriedades de configuração do KeyStore
//	@Autowired
//	private JwtKeyStoreProperties jwtKeyStoreProperties;
//	
////	@Autowired
////	private RedisConnectionFactory redisConnectionFactory;
//	
//	@Autowired
//	private DataSource dataSource;
//	
//	
//	@Override
//	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//		
//		//23.36. Configurando os clientes OAuth2 em um banco de dados SQL
//		clients.jdbc(dataSource);
//		
//		//=======================================================================================
//		
//		//22.9. Configurando o fluxo Authorization Server com Password Credentials e Opaque Tokens
//		
////		clients.inMemory().withClient("algafood-web")
////						  .secret(passwordEncoder.encode("web123"))
////						  .authorizedGrantTypes("password", "refresh_token") //22.13. Configurando o Refresh Token Grant Type no Authorization Server
////						  .scopes("WRITE", "READ")
////						  .accessTokenValiditySeconds(60 * 60 * 6) //o padrão é 12 horas, mas agora está settado para seis horas de duração
////						  .refreshTokenValiditySeconds(60 * 24 * 60 * 60) //60 dias | 22.14. Configurando a validade e não reutilização de Refresh Tokens
////						  
////						  .and()
////						  
////						  .withClient("checktoken")
////						  .secret(passwordEncoder.encode("check123"))
////						  
////						  .and()
////						  
////						  .withClient("faturamento")
////						  .secret(passwordEncoder.encode("faturamento123"))
////						  .authorizedGrantTypes("client_credentials") //22.16. Configurando o Client Credentials Grant Type no Authorization Server
////						  .scopes("WRITE", "READ")
////						  
////						  .and()
////						  
////						  .withClient("foodanalytics")
////						  .secret(passwordEncoder.encode("")) //food123
////						  .authorizedGrantTypes("authorization_code") //22.18. Configurando o Authorization Code Grant Type
////						  .scopes("WRITE", "READ")
////						  .redirectUris("http://aplicacao_cliente")
////						  
////						  .and()
////						  .withClient("webadmin") //22.21. Configurando o fluxo Implicit Grant Type
////						  .authorizedGrantTypes("implicit")
////						  .scopes("WRITE", "READ")
////						  .redirectUris("http://aplicacao_cliente");
//						  
//	}
//	
//	
//	@Override
//	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//		//22.10. Configurando o endpoint de introspecção de tokens no Authorization Server
//		//security.checkTokenAccess("isAuthenticated()");
//		security.checkTokenAccess("permitAll()")
//				.tokenKeyAccess("permitAll()") //23.11. Extraindo a chave pública no formato PEM
//				.allowFormAuthenticationForClients();
//	}
//	
//	
//	@Override
//	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//		
//		var enhacerChain = new TokenEnhancerChain(); //23.16. Adicionando claims customizadas no payload do JWT
//		enhacerChain.setTokenEnhancers(Arrays.asList(new JwtCustomClaimsTokenEnhancer(), jwtAccessTokenConverter()));
//		
//		endpoints.authenticationManager(authenticationManager)
//				 .userDetailsService(UserDetailsService)
//				 
//				 //24.21. Resolvendo problemas com storage de Authorization Codes
//				 .authorizationCodeServices(new JdbcAuthorizationCodeServices(this.dataSource))
//				 
//				 .reuseRefreshTokens(false) //22.14. Configurando a validade e não reutilização de Refresh Tokens
//				 
//				 .accessTokenConverter(jwtAccessTokenConverter())
//				 
//				 .tokenEnhancer(enhacerChain) //23.16. Adicionando claims customizadas no payload do JWT
//				 
//				 .approvalStore(approvalStore(endpoints.getTokenStore())) //23.13. Revisando o fluxo de aprovação do Authorization Code com JWT
//				 
//				 .tokenGranter(tokenGranter(endpoints)); //22.23. Implementando o suporte a PKCE com o fluxo Authorization Code
//				 
//				 //.tokenStore(redisTokenStore()); //23.2. Configurando o RedisTokenStore
//	}
//	
//	
//	private ApprovalStore approvalStore(TokenStore tokenStore) {
//		
//		//23.13. Revisando o fluxo de aprovação do Authorization Code com JWT
//		
//		var approvalStore = new TokenApprovalStore();
//		approvalStore.setTokenStore(tokenStore);
//		
//		return approvalStore;
//	}
//	
//	
//	
//	@Bean
//	public JWKSet jwkSet() {
//		
//		//23.45. Implementando o endpoint do JSON Web Key Set (JWKS)
//		
//		RSAKey.Builder builder = new RSAKey.Builder((RSAPublicKey) keyPair().getPublic())
//																		    .keyUse(KeyUse.SIGNATURE)
//																		    .algorithm(JWSAlgorithm.RS256)
//																		    .keyID("algafood-key-id");
//		
//		return new JWKSet(builder.build());
//	}
//	
//	
//	@Bean
//	public JwtAccessTokenConverter jwtAccessTokenConverter() {
//		
//		//23.5. Gerando JWT com chave simétrica (HMAC SHA-256) no Authorization Server
//		
//		var jwtAccessTokenConverter = new JwtAccessTokenConverter();
//		//jwtAccessTokenConverter.setSigningKey("vnqienerq8904h89f4hnwsbrnfq90hr49fn9wrf49fhn9wfh349fgh9vnr4v9h34r9"); //Assinatura simetrica
//		
//		//23.9. Assinando o JWT com RSA SHA-256 (chave assimétrica)
//		
//		//23.41. Juntando o Resource Server com o Authorization Server no mesmo projeto
//		
//		//var jksResource = new ClassPathResource(jwtKeyStoreProperties.getPath());
//		
//		jwtAccessTokenConverter.setKeyPair(keyPair());
//		
//		return jwtAccessTokenConverter;
//	}
//	
//	
//	private KeyPair keyPair() {
//		
//		//23.45. Implementando o endpoint do JSON Web Key Set (JWKS)
//		
//		var keyStorePass = jwtKeyStoreProperties.getPassword();
//		var keyPairAlias = jwtKeyStoreProperties.getKeypairAlias();
//		var keyStoreFactory = new KeyStoreKeyFactory(jwtKeyStoreProperties.getJksLocation(), keyStorePass.toCharArray());
//		
//		return keyStoreFactory.getKeyPair(keyPairAlias);
//	}
//	
//	
////	private TokenStore redisTokenStore() {
////		//23.2. Configurando o RedisTokenStore
////		return new RedisTokenStore(redisConnectionFactory);
////	}
//	
//	
//	private TokenGranter tokenGranter(AuthorizationServerEndpointsConfigurer endpoints) {
//		
//		//22.23. Implementando o suporte a PKCE com o fluxo Authorization Code
//		
//		var pkceAuthorizationCodeTokenGranter = new PkceAuthorizationCodeTokenGranter(endpoints.getTokenServices(),
//																					  endpoints.getAuthorizationCodeServices(), 
//																					  endpoints.getClientDetailsService(), 
//																					  endpoints.getOAuth2RequestFactory());
//		
//		var granters = Arrays.asList(pkceAuthorizationCodeTokenGranter, endpoints.getTokenGranter());
//		
//		return new CompositeTokenGranter(granters);
//	}
//}