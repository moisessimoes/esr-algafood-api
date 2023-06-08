package com.algaworks.algafood.core.security.authorizationserver;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Validated
@Component
@ConfigurationProperties("algafood.jwt.keystore")
public class JwtKeyStoreProperties {
	
	//23.10. Desafio: criando bean de propriedades de configuração do KeyStore
	
	//23.41. Juntando o Resource Server com o Authorization Server no mesmo projeto
	
	@NotNull
    private Resource jksLocation;
    
    @NotBlank
    private String password;
    
    @NotBlank
    private String keypairAlias;

	public Resource getJksLocation() {
		return jksLocation;
	}

	public void setJksLocation(Resource jksLocation) {
		this.jksLocation = jksLocation;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getKeypairAlias() {
		return keypairAlias;
	}

	public void setKeypairAlias(String keypairAlias) {
		this.keypairAlias = keypairAlias;
	}
}