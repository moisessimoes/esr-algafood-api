package com.algaworks.algafood.api.view;

public interface RestauranteView {
	
	//Projeção de recursos com @JsonView do Jackson
	
	public interface Resumo {}
	public interface ApenasNome {}

}
