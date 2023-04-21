package com.algaworks.algafood.api.v1.controller.openapi.model;

import java.math.BigDecimal;

import com.algaworks.algafood.api.v1.model.CozinhaModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("RestauranteBasicoModelOpenApi")
public class RestauranteBasicoModelOpenApi {
	
	/*Devido as correções na documentação por causa do HATEOAS, essa classe foi substituida pela classe RestaurantesModelOpenApi*/
	
	@ApiModelProperty(example = "1")
	private Long id;
	
	@ApiModelProperty(example = "Brasileira")
	private String nome;
	
	@ApiModelProperty(example = "5.00")
	private BigDecimal taxaFrete;
	
	private CozinhaModel cozinha;

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

	public BigDecimal getTaxaFrete() {
		return taxaFrete;
	}

	public void setTaxaFrete(BigDecimal taxaFrete) {
		this.taxaFrete = taxaFrete;
	}

	public CozinhaModel getCozinha() {
		return cozinha;
	}

	public void setCozinha(CozinhaModel cozinha) {
		this.cozinha = cozinha;
	}
}