package com.algaworks.algafood.api.model;

import io.swagger.annotations.ApiModelProperty;

//@ApiModel(value = "Cidade", description = "Representa uma cidade")
public class CidadeModel {
	
	@ApiModelProperty(position = 0, example = "1")
	private Long id;
	
	@ApiModelProperty(position = 1, example = "Detroit")
	private String nome;
	
	@ApiModelProperty(position = 2)
	private EstadoModel estado;
	
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
	public EstadoModel getEstado() {
		return estado;
	}
	public void setEstado(EstadoModel estado) {
		this.estado = estado;
	}
}