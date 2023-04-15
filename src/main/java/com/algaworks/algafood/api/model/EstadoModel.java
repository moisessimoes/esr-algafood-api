package com.algaworks.algafood.api.model;

import org.springframework.hateoas.RepresentationModel;

import io.swagger.annotations.ApiModelProperty;

public class EstadoModel extends RepresentationModel<EstadoModel> {
	
	@ApiModelProperty(position = 0, example = "1")
	private Long id;
	
	@ApiModelProperty(position = 1, example = "Michigan")
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