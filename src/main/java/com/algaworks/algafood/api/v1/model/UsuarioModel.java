package com.algaworks.algafood.api.v1.model;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation("Usuarios")
public class UsuarioModel extends RepresentationModel<UsuarioModel> {
	
	//@ApiModelProperty(position = 0, example = "1")
	private Long id;
	
	//@ApiModelProperty(position = 1, example = "Markus")
	private String nome;
	
	//@ApiModelProperty(position = 2, example = "markus@mail.com")
	private String email;
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}