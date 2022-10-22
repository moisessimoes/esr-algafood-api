package com.algaworks.algafood.api.model.mixin;

import java.util.List;

import com.algaworks.algafood.domain.model.Restaurante;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class CozinhaMixIn {
	
	////Criando classes de mixin para usar as anotações do Jackson
	
	@JsonIgnore
	private List<Restaurante> restaurantes;

}
