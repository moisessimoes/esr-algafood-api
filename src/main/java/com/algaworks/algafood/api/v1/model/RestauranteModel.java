package com.algaworks.algafood.api.v1.model;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.v3.oas.annotations.media.Schema;

@Relation(collectionRelation = "restaurantes")
public class RestauranteModel extends RepresentationModel<RestauranteModel> {
	
	//Projeção de recursos com @JsonView do Jackson
	
	@Schema(example = "1")
	//@JsonView({RestauranteView.Resumo.class, RestauranteView.ApenasNome.class})
	private Long id;
	
	@Schema(example = "Turca")
	//@JsonView({RestauranteView.Resumo.class, RestauranteView.ApenasNome.class})
	private String nome;
	
	@Schema(example = "2.50")
	//@JsonView(RestauranteView.Resumo.class)
	private BigDecimal taxaFrete;
	
	//@JsonView(RestauranteView.Resumo.class)
	private CozinhaModel cozinha;
	
	private Boolean ativo;
	private Boolean aberto;
	private EnderecoModel endereco;
	
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
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	public Boolean getAberto() {
		return aberto;
	}
	public void setAberto(Boolean aberto) {
		this.aberto = aberto;
	}
	public EnderecoModel getEndereco() {
		return endereco;
	}
	public void setEndereco(EnderecoModel endereco) {
		this.endereco = endereco;
	}
}