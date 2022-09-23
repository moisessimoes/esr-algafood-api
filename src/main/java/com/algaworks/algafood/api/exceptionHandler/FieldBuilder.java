package com.algaworks.algafood.api.exceptionHandler;

public class FieldBuilder {
	
	//Classe implementa o Builder Pattern para simplificar a criacoa de objeto Field para ser exibido nos erros de API.
	
	private Field field;
	
	/**
     * Cria uma instancia para o objeto Field.
     */
	public FieldBuilder() {
		this.field = new Field();
	}
	
	
	/**
     * Cria uma instancia para FieldBuilder
     * @return uma instancia de FieldBuilder
     */
	public static FieldBuilder builder() {
		return new FieldBuilder();
	}
	
	
	/**
     * Adiciona todos os campos de Field
     * 
     */
	
	public FieldBuilder addName(String name) {
		this.field.setName(name);
		return this;
	}
	
	public FieldBuilder addUserMessage(String userMessage) {
		this.field.setUserMessage(userMessage);
		return this;
	}
	
	/**
     * Para recuperar a instancia de Field.
     * @return retorna o objeto Field atual.
     */
	public Field build() {
		return this.field;
	}
}