package com.algaworks.algafood.api.v1.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

//@ApiModel(value = "Cidade", description = "Representa uma cidade")
@Relation(collectionRelation = "cidades")
public class CidadeModel extends RepresentationModel<CidadeModel> { //19.7. Adicionando hypermedia na representação de recurso único com HAL
	
	//@ApiModelProperty(position = 0, example = "1")
	private Long id;
	
	//@ApiModelProperty(position = 1, example = "Detroit")
	private String nome;
	
	//@ApiModelProperty(position = 2)
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