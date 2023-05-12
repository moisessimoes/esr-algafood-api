package com.algaworks.algafood.core.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class ResourceServerConfig {
	
	//22.11. Configurando o Resource Server com a nova stack do Spring Security
	//Renomeando classe WebSecurityConfig -> ResourceServerConfig
	
	
	
	
	//A versão atual do spring não suporta mais o WebSecurityConfigurerAdapter. Podemos obter o mesmo resultado utilizando o seguinte Bean:
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		
		//22.3. Configurando Spring Security com HTTP Basic
		
		http.httpBasic().and()
						.authorizeRequests()
						//.antMatchers("/v1/cozinhas/**").permitAll()
						.anyRequest()
						.authenticated()
		
						//Definindo que não será usado sessão nas APIs, para que asssim seja necessário a autenticação todas as vezes que alguma API for chamada
//						.and()
//						.sessionManagement()
//						.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		
						//Desabilitando o csrf
//						.and()
//						.csrf().disable();
						
						//22.11. Configurando o Resource Server com a nova stack do Spring Security
						.and()
						.cors()
						.and()
						.oauth2ResourceServer()
						.opaqueToken();
		
		return http.build();
	}
	
	
//	@Bean
//	public AuthenticationManager authenticationManagerBuilder() throws Exception {
//		
//		//22.4. Configurando autenticação de usuários em memória
//		
//		var auth = new AuthenticationManagerBuilder(null);
//		
//		auth.inMemoryAuthentication().withUser("moises")
//									 .password(passwordEncoder().encode("123"))
//									 .roles("ADMIN");
//		
//		return auth.;
//		
//	}
	
	//22.11. Configurando o Resource Server com a nova stack do Spring Security
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
}