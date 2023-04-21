package com.algaworks.algafood.api.v1.model;

import java.math.BigDecimal;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import io.swagger.annotations.ApiModelProperty;

@Relation(collectionRelation = "produtos")
public class ProdutoModel extends RepresentationModel<ProdutoModel> {
	
	@ApiModelProperty(position = 0, example = "1")
	private Long id;
	
	@ApiModelProperty(position = 1, example = "Cerveja Budweiser")
	private String nome;
	
	@ApiModelProperty(position = 2, example = "Cerveja Budweiser")
	private String descricao;
	
	@ApiModelProperty(position = 3, example = "7.00")
	private BigDecimal preco;
	
	@ApiModelProperty(position = 4, example = "true")
	private Boolean ativo;

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
}