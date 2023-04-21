package com.algaworks.algafood.api.v2.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("CidadeInput")
public class CidadeInputV2 { //20.11. Implementando o versionamento da API por Media Type

	@ApiModelProperty(position = 0, example = "Detroit", required = true)
	@NotBlank
	private String nomeCidade;
	
	@ApiModelProperty(position = 1, example = "1", required = true)
	@NotNull
	private Long idEstado;

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
}