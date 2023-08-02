package com.algaworks.algafood.core.security;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class ResourceServerConfig { 
	
	@Bean
	public SecurityFilterChain resourceServerFilterChain(HttpSecurity httpSecurity) throws Exception {
		
		httpSecurity.authorizeRequests()
					.antMatchers("/oauth2/**").authenticated()
					.and()
					.csrf().disable()
					.cors()
					.and()
					.oauth2ResourceServer().jwt().jwtAuthenticationConverter(jwtAuthenticationConverter());
					//.opaqueToken(); //27.6. Configurando o Resource Server com token opaco
		
		//return httpSecurity.build();
		//return httpSecurity.formLogin(Customizer.withDefaults()).build(); //27.11. Implementando um cliente com o fluxo Authorization Code
		return httpSecurity.formLogin(customizer -> customizer.loginPage("/login")).build(); //27.17. Customizando a página de login do Authorization Server
	}
	
	
	private JwtAuthenticationConverter jwtAuthenticationConverter() {
		
		//27.15. Lendo informações customizadas do JWT no Resource Server
		
		var converter = new JwtAuthenticationConverter();
		
		converter.setJwtGrantedAuthoritiesConverter(jwt -> {
			
			List<String> authorities = jwt.getClaimAsStringList("authorities");
			
			if (authorities == null || authorities.isEmpty()) return Collections.emptyList();
			
			var authoritiesConverter = new JwtGrantedAuthoritiesConverter();
			Collection<GrantedAuthority> grantedAuthorities = authoritiesConverter.convert(jwt);
			
			grantedAuthorities.addAll(authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
			
			return grantedAuthorities;
		});
		
		return converter;
	}
}