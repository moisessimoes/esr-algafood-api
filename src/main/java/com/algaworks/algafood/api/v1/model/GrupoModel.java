package com.algaworks.algafood.api.v1.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "grupos")
public class GrupoModel extends RepresentationModel<GrupoModel> {
	
	//@ApiModelProperty(position = 0, example = "1")
	private Long id;
	
	//@ApiModelProperty(position = 1, example = "Gerente")
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