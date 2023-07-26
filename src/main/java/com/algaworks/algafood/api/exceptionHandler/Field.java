package com.algaworks.algafood.api.exceptionHandler;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(example = "Campos")
public class Field {
	
	@Schema(example = "preco")
	private String name;
	
	@Schema(example = "O preço é inválido.")
	private String userMessage;
	
	public Field() {}

	public Field(String name, String userMessage) {
		super();
		this.name = name;
		this.userMessage = userMessage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}
}
