package com.algaworks.algafood.api.v1.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "restaurantes")
public class RestauranteApenasNomeModel extends RepresentationModel<RestauranteApenasNomeModel> {
	
	//@ApiModelProperty(example = "1")
    private Long id;
    
    //@ApiModelProperty(example = "Thai Gourmet")
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