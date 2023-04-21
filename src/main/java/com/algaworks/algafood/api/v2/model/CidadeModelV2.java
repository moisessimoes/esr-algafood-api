package com.algaworks.algafood.api.v2.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "CidadeModel")
@Relation(collectionRelation = "cidades")
public class CidadeModelV2 extends RepresentationModel<CidadeModelV2> { //20.11. Implementando o versionamento da API por Media Type
	
	@ApiModelProperty(position = 0, example = "1")
	private Long idCidade;
	
	@ApiModelProperty(position = 1, example = "Detroit")
	private String nomeCidade;
	
	@ApiModelProperty(position = 2, example = "1")
	private Long idEstado;
	
	@ApiModelProperty(position = 1, example = "Minchigan")
	private String nomeEstado;

	public Long getIdCidade() {
		return idCidade;
	}

	public void setIdCidade(Long idCidade) {
		this.idCidade = idCidade;
	}

	public String getNomeCidade() {
		return nomeCidade;
	}

	public void setNomeCidade(String nomeCidade) {
		this.nomeCidade = nomeCidade;
	}

	public Long getIdEstado() {
		return idEstado;
	}

	public void setIdEstado(Long idEstado) {
		this.idEstado = idEstado;
	}

	public String getNomeEstado() {
		return nomeEstado;
	}

	public void setNomeEstado(String nomeEstado) {
		this.nomeEstado = nomeEstado;
	}
}