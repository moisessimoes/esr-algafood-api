package com.algaworks.algafood.core.jackson;

import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.model.mixin.CidadeMixIn;
import com.algaworks.algafood.api.model.mixin.CozinhaMixIn;
import com.algaworks.algafood.api.model.mixin.RestauranteMixIn;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.fasterxml.jackson.databind.module.SimpleModule;

@Component
public class JacksonMixInModule extends SimpleModule {

	private static final long serialVersionUID = 1L;
	
	public JacksonMixInModule() {
		
		setMixInAnnotation(Restaurante.class, RestauranteMixIn.class);
		setMixInAnnotation(Cozinha.class, CozinhaMixIn.class);
		setMixInAnnotation(Cidade.class, CidadeMixIn.class);
	}
}