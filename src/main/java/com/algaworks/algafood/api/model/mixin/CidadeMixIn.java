package com.algaworks.algafood.api.model.mixin;

import com.algaworks.algafood.domain.model.Estado;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class CidadeMixIn {
	
	@JsonIgnoreProperties(value = "nome", allowGetters = true)
	private Estado estado;

}