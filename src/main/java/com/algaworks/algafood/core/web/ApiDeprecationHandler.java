package com.algaworks.algafood.core.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class ApiDeprecationHandler implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		if (request.getRequestURI().startsWith("/v1/")) {
			
			//20.18. Depreciando uma versão da API
			//response.addHeader("X-AlgaFood-Deprecated", "Essa versão da API está depreciada e será descontinuada a partir de 01/06/2023. Use a versão mais atual da API.");
			
			//20.19. Desligando uma versão da API
			response.setStatus(HttpStatus.GONE.value());
			return false;
		}
		return true;
	}
}