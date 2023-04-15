package com.algaworks.algafood.api.utils;

import java.net.URI;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public final class ResourceUriHelper {
	
	public static void addUriInResponseHeader(Object resourceId) {
		
		//19.2. Adicionando a URI do recurso criado no header da resposta (HATEOAS)
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(resourceId).toUri();
		
		//Capturando o response da requisição e adicionando o URI completa no cabeçalho
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
		response.setHeader(HttpHeaders.LOCATION, uri.toString());
	}
}