package com.algaworks.algafood.api.model;

import java.math.BigDecimal;

public class RestauranteModel {
	
	private Long id;
	private String nomeString;
	private BigDecimal taxaFrete;
	private CozinhaModel cozinha;
	private Boolean ativo;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomeString() {
		return nomeString;
	}
	public void setNomeString(String nomeString) {
		this.nomeString = nomeString;
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
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
}