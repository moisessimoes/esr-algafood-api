package com.algaworks.algafood.api.v2.model.input;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("CozinhaInput")
public class CozinhaInputV2 {
	
	@ApiModelProperty(position = 0, example = "Portuguesa", required = true)
	@NotBlank
	private String nomeCozinha;

	public String getNomeCozinha() {
		return nomeCozinha;
	}

	public void setNomeCozinha(String nomeCozinha) {
		this.nomeCozinha = nomeCozinha;
	}
}