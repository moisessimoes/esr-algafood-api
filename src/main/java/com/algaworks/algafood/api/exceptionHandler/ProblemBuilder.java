package com.algaworks.algafood.api.exceptionHandler;

import java.time.LocalDateTime;
import java.util.List;

public class ProblemBuilder {
	
	//Classe implementa o Builder Pattern para simplificar a criacao de objeto Problem para ser exibido nos erros de API.
	
	private Problem problem;
	
	/**
     * Cria uma instancia para o objeto Problem.
     */
	public ProblemBuilder() {
		this.problem = new Problem();
	}
	
	
	/**
     * Cria uma instancia para ProblemBuilder
     * @return uma instancia de ProblemBuilder
     */
	public static ProblemBuilder builder() {
		return new ProblemBuilder();
	}
	
	
	/**
     * Adiciona todos os campos de Problem
     * 
     */
	
	public ProblemBuilder addStatus(Integer status) {
		this.problem.setStatus(status);
		return this;
	}
	
	public ProblemBuilder addLocalDateTime() {
		this.problem.setTimestamp(LocalDateTime.now());
		return this;
	}
	
	public ProblemBuilder addType(String type) {
		this.problem.setType(type);
		return this;
	}
	
	public ProblemBuilder addTitle(String title) {
		this.problem.setTitle(title);
		return this;
	}
	
	public ProblemBuilder addDetail(String detail) {
		this.problem.setDetail(detail);
		return this;
	}
	
	public ProblemBuilder addUserMessage(String userMessage) {
		this.problem.setUserMessage(userMessage);
		return this;
	}
	
	public ProblemBuilder addFields(List<Field> fields) {
		this.problem.setFields(fields);
		return this;
	}
	
	
	/**
     * Para recuperar a instancia de Problem.
     * @return retorna o objeto Problem atual.
     */
	public Problem build() {
		return this.problem;
	}
}