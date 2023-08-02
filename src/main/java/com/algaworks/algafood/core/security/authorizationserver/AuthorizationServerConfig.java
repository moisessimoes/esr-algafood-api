package com.algaworks.algafood.core.security.authorizationserver;

import java.io.InputStream;
import java.security.KeyStore;
import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.authorization.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.OAuth2TokenFormat;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.ClientSettings;
import org.springframework.security.oauth2.server.authorization.config.ProviderSettings;
import org.springframework.security.oauth2.server.authorization.config.TokenSettings;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.algaworks.algafood.repositories.UsuarioRepository;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;

@Configuration
public class AuthorizationServerConfig {
	
	@Bean
	@Order(Ordered.HIGHEST_PRECEDENCE)
	public SecurityFilterChain authFilterChain(HttpSecurity httpSecurity) throws Exception {
		
		//OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(httpSecurity); //27.18. Customizando a página de consentimento do OAuth2
		//return httpSecurity.build();
		//return httpSecurity.formLogin(Customizer.withDefaults()).build(); //27.11. Implementando um cliente com o fluxo Authorization Code
		
		//====================================================================================
		//27.18. Customizando a página de consentimento do OAuth2
		OAuth2AuthorizationServerConfigurer<HttpSecurity> authorizationServerConfigurer = new OAuth2AuthorizationServerConfigurer<>();
		RequestMatcher endpointsMatcher = authorizationServerConfigurer.getEndpointsMatcher();
		
		authorizationServerConfigurer.authorizationEndpoint(customizer -> customizer.consentPage("/oauth2/consent"));

		httpSecurity.requestMatcher(endpointsMatcher)
					.authorizeRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated())
					.csrf(csrf -> csrf.ignoringRequestMatchers(endpointsMatcher))
					.apply(authorizationServerConfigurer);
		//====================================================================================
		
		return httpSecurity.formLogin(customizer -> customizer.loginPage("/login")).build(); //27.17. Customizando a página de login do Authorization Server
	}
	
	
	@Bean
	public ProviderSettings providerSettings(AlgaFoodSecurityProperties algaFoodSecurityProperties) {
		
		return ProviderSettings.builder().issuer(algaFoodSecurityProperties.getProviderUrl()).build();
	}
	
	
	@Bean
	public RegisteredClientRepository registeredClientRepository(PasswordEncoder passwordEncoder, JdbcOperations jdbcOperations) {

		//27.9. Configurando a geração de Access Token JWT no Authorization Server
		
		RegisteredClient algafoodbackend = RegisteredClient.withId("1")
				.clientId("algafood-backend")
				.clientSecret(passwordEncoder.encode("backend123"))
				.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
				.authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
				.scope("READ")
				.tokenSettings(TokenSettings.builder()
						.accessTokenFormat(OAuth2TokenFormat.SELF_CONTAINED)
//						.accessTokenFormat(OAuth2TokenFormat.REFERENCE) //27.3. Configuração inicial do Authorization Server com Access Token opaco
						.accessTokenTimeToLive(Duration.ofMinutes(30))
						.build())
				.build();
		
		//=====================================================================================================
		//27.11. Implementando um cliente com o fluxo Authorization Code
		RegisteredClient algafoodweb = RegisteredClient.withId("2")
				.clientId("algafood-web")
				.clientSecret(passwordEncoder.encode("web123"))
				.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
				.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
				.authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN) //27.13. Implementando um cliente com o fluxo Refresh Token
				.scope("READ")
				.scope("WRITE")
				.tokenSettings(TokenSettings.builder()
						.accessTokenFormat(OAuth2TokenFormat.SELF_CONTAINED)
						.accessTokenTimeToLive(Duration.ofMinutes(15))
						.reuseRefreshTokens(false)
						.refreshTokenTimeToLive(Duration.ofDays(1))
						.build())
				.redirectUri("http://127.0.0.1:8080/authorize")//Essa URL não existe
				.redirectUri("http://127.0.0.1:8080/swagger-ui/oauth2-redirect.html") //Essa URL existe
				.clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
				.build();
		//=====================================================================================================
		//27.11. Implementando um cliente com o fluxo Authorization Code
		RegisteredClient foodanalytics = RegisteredClient.withId("3")
				.clientId("foodanalytics")
				.clientSecret(passwordEncoder.encode("web123"))
				.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
				.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
				.scope("READ")
				.scope("WRITE")
				.tokenSettings(TokenSettings.builder()
						.accessTokenFormat(OAuth2TokenFormat.SELF_CONTAINED)
						.accessTokenTimeToLive(Duration.ofMinutes(15))
						.build())
				.redirectUri("http://www.foodanalytics.local:8082")
				.clientSettings(ClientSettings.builder().requireAuthorizationConsent(false).build())
				.build();
		
		//return new InMemoryRegisteredClientRepository(Arrays.asList(algafoodbackend, algafoodweb, foodanalytics));
		
		//27.16. Implementado Repository de Clients do OAuth2 via JDBC
		var repository = new JdbcRegisteredClientRepository(jdbcOperations);
		
		repository.save(algafoodbackend);
		repository.save(algafoodweb);
		repository.save(foodanalytics);
		
		return repository;
	}
	
	
	@Bean
	public OAuth2AuthorizationService auth2AuthorizationService(JdbcOperations jdbcOperations, RegisteredClientRepository registeredClientRepository) {
		
		//27.7. Armazenando autorizações no banco de dados
		// Para essa aula, foi criado uma nova tabela e o seu script está na pasta de migrations
		
		return new JdbcOAuth2AuthorizationService(jdbcOperations, registeredClientRepository);
	}
	
	
	@Bean
	public JWKSource<SecurityContext> jwkSource(JwtKeyStoreProperties jwtKeyStoreProperties) throws Exception {
		
		//27.9. Configurando a geração de Access Token JWT no Authorization Server
		
		char[] keyStorePass = jwtKeyStoreProperties.getPassword().toCharArray();
		String keyPairAlias = jwtKeyStoreProperties.getKeypairAlias();
		
		Resource jksLocation = jwtKeyStoreProperties.getJksLocation();
		InputStream inputStream = jksLocation.getInputStream();
		KeyStore keyStore = KeyStore.getInstance("JKS");
		keyStore.load(inputStream, keyStorePass);
		
		RSAKey rsaKey = RSAKey.load(keyStore, keyPairAlias, keyStorePass);
		
		return new ImmutableJWKSet<>(new JWKSet(rsaKey));
	}
	
	
	@Bean
	public OAuth2TokenCustomizer<JwtEncodingContext> jwtCustomizer(UsuarioRepository usuarioRepository) {
		
		//27.14. Customizando o token JWT com dados do usuário
		
		return context -> {
			
			Authentication authentication = context.getPrincipal();
			
			if (authentication.getPrincipal() instanceof User) {
				
				User user = (User) authentication.getPrincipal();
				
				var usuario = usuarioRepository.findByEmail(user.getUsername()).orElseThrow();
				
				Set<String> authorities = new HashSet<>();
				
				user.getAuthorities().forEach(authority -> authorities.add(authority.getAuthority()));
				
				context.getClaims().claim("usuario_id", usuario.getId().toString());
				context.getClaims().claim("authorities", authorities);
			}
		};
	}
	
	
	
	@Bean
	public OAuth2AuthorizationConsentService consentService(JdbcOperations jdbcOperations, RegisteredClientRepository clientRepository) {
		
		//27.18. Customizando a página de consentimento do OAuth2
		//27.19. Armazenando autorizações de consentimento no banco de dados
		
		 return new JdbcOAuth2AuthorizationConsentService(jdbcOperations, clientRepository);
	}
	
	
	
	@Bean
	public OAuth2AuthorizationQueryService auth2AthorizationQueryService(JdbcOperations jdbcOperations, RegisteredClientRepository clientRepository) {
		//27.20. Criando uma página de listagem dos clientes com consentimentos permitidos
		return new JdbcOAuth2AthorizationQueryService(jdbcOperations, clientRepository);
	}
}