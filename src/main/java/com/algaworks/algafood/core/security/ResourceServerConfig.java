package com.algaworks.algafood.core.security;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

@SuppressWarnings("deprecation")
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //23.21. Method Security: Restringindo acesso com @PreAuthorize e SpEL
public class ResourceServerConfig extends WebSecurityConfigurerAdapter {
	
	//22.11. Configurando o Resource Server com a nova stack do Spring Security
	//Renomeando classe WebSecurityConfig -> ResourceServerConfig
	
//	private static final String[] FREE_PATHS = {
//			
//			"/v2/api-docs",
//            "/configuration/ui",
//            "/swagger-resources/**",
//            "/configuration/security",
//            "/swagger-ui.html",
//            "/webjars/**"
//	};
	
	
	//A versão atual do spring não suporta mais o WebSecurityConfigurerAdapter. Podemos obter o mesmo resultado utilizando o seguinte Bean:
//	@Bean
//	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
//		
//		//22.3. Configurando Spring Security com HTTP Basic
//		
//		http//.httpBasic()//.and()
//						//.authorizeRequests()
//						//.antMatchers("/v1/cozinhas/**").permitAll()
//						//.antMatchers(FREE_PATHS).permitAll()
//						//.anyRequest()
//						//.authenticated()
//		
//						//Definindo que não será usado sessão nas APIs, para que asssim seja necessário a autenticação todas as vezes que alguma API for chamada
////						.and()
////						.sessionManagement()
////						.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//		
//						//Desabilitando o csrf
////						.and()
//						.csrf().disable()
//						
//						//22.11. Configurando o Resource Server com a nova stack do Spring Security
//						//.and()
//						.cors()
//						.and()
//						//.authenticationManager(authenticationManager)
//						.oauth2ResourceServer()
//						//.opaqueToken();
//						.jwt() //23.6. Configurando o Resource Server para JWT assinado com chave simétrica
//						
//						//23.20. Carregando as Granted Authorities e restringindo acesso a endpoints na API
//						.jwtAuthenticationConverter(jwtAuthenticationConverter());
//		
//		return http.build();
//	}
	
	
	/*Devido a mudança para colocar o Authorization server junto do Resource server, houve conflito de instancias, então voltei para versão depreciada mesmo*/
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.formLogin()
			.loginPage("/login") //23.43. Customizando a página de login
			.and()
			.authorizeRequests()
			.antMatchers("/oauth/**").authenticated()
			.and()
			.csrf().disable()
			.cors()
			.and()
			.oauth2ResourceServer().jwt()
			.jwtAuthenticationConverter(jwtAuthenticationConverter());
	}
	
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		//22.9. Configurando o fluxo Authorization Server com Password Credentials e Opaque Tokens
		return super.authenticationManager();
	}
	
	
	private JwtAuthenticationConverter jwtAuthenticationConverter() {
		
		//23.20. Carregando as Granted Authorities e restringindo acesso a endpoints na API
		
		var jwtAuthenticationConverter = new JwtAuthenticationConverter();
		
		jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwt -> {
			
			var authorities = jwt.getClaimAsStringList("authorities");
			
			if (authorities == null) {
				authorities = Collections.emptyList();
			}
			
			//23.25. Carregando Granted Authorities dos escopos do OAuth2 no Resource Server
			var scopesAuthoritiewsConverter = new JwtGrantedAuthoritiesConverter();
			Collection<GrantedAuthority> grantedAuthorities = scopesAuthoritiewsConverter.convert(jwt);
			
			//Mesclando o array de authorities com o o arrays de scopes
			grantedAuthorities.addAll(authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
			//==============================================================================
			
			return grantedAuthorities;
		});
		
		return jwtAuthenticationConverter;
	}
	
	
//	@Bean
//	protected AuthenticationManager authenticationManager() throws Exception {
//		//22.9. Configurando o fluxo Authorization Server com Password Credentials e Opaque Tokens
//		return super.authenticationManager();
//	}
	
	
//	public void configure(WebSecurity web) throws Exception {
//    	
//        web.ignoring().antMatchers("/v2/api-docs",
//                                   "/configuration/ui",
//                                   "/swagger-resources/**",
//                                   "/configuration/security",
//                                   "/swagger-ui.html",
//                                   "/webjars/**");
//    }
	
	
//	@Bean
//	public JwtDecoder jwtDecoder() {
//		
//		//23.6. Configurando o Resource Server para JWT assinado com chave simétrica
//		
//		var secretKey = new SecretKeySpec("vnqienerq8904h89f4hnwsbrnfq90hr49fn9wrf49fhn9wfh349fgh9vnr4v9h34r9".getBytes(), "HmacSHA256");
//		
//		return NimbusJwtDecoder.withSecretKey(secretKey).build();
//	}
	
	

	//22.11. Configurando o Resource Server com a nova stack do Spring Security
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
}