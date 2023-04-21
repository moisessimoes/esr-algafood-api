package com.algaworks.algafood.api.v2.model;

import org.springframework.hateoas.RepresentationModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("CozinhaModel")
public class CozinhaModelV2 extends RepresentationModel<CozinhaModelV2> {
	
	@ApiModelProperty(position = 0, example = "1")
	private Long idCozinha;
	
	@ApiModelProperty(position = 1, example = "Sulista")
	private String nomeCozinha;

	public Long getIdCozinha() {
		return idCozinha;
	}

	public void setIdCozinha(Long idCozinha) {
		this.idCozinha = idCozinha;
	}

	public String getNomeCozinha() {
		return nomeCozinha;
	}

	public void setNomeCozinha(String nomeCozinha) {
		this.nomeCozinha = nomeCozinha;
	}
}