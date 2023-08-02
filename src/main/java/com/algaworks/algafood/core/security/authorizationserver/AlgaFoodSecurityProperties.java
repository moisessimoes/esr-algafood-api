package com.algaworks.algafood.core.security.authorizationserver;

import javax.validation.constraints.NotBlank;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Validated
@ConfigurationProperties("algafood.auth")
public class AlgaFoodSecurityProperties { //27.3. Configuração inicial do Authorization Server com Access Token opaco
	
	@NotBlank
	private String providerUrl;

	public String getProviderUrl() {
		return providerUrl;
	}

	public void setProviderUrl(String providerUrl) {
		this.providerUrl = providerUrl;
	}
}