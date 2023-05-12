package com.algaworks.algafood.api.v1.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;

@Relation(collectionRelation = "cozinhas")
public class CozinhaModel extends RepresentationModel<CozinhaModel> {
	
	@ApiModelProperty(position = 0, example = "1")
	//@JsonView(RestauranteView.Resumo.class)
	private Long id;
	
	@ApiModelProperty(position = 1, example = "Sulista")
	//@JsonView(RestauranteView.Resumo.class)
	private String nome;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
}