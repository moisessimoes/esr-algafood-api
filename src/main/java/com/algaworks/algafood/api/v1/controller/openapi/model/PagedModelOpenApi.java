package com.algaworks.algafood.api.v1.controller.openapi.model;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class PagedModelOpenApi<T> {
	
	//18.21. Corrigindo documentação com substituição de Page
	
	private List<T> content;
	
	@ApiModelProperty(example = "10", value = "Número de elementos por página")
	private Long size;
	
	@ApiModelProperty(example = "50", value = "Total de elementos")
	private Long totalElements;
	
	@ApiModelProperty(example = "5", value = "Total de páginas")
	private Long totalPages;
	
	@ApiModelProperty(example = "0", value = "Número da página (começa em 0)")
	private Long number;

	public List<T> getContent() {
		return content;
	}

	public void setContent(List<T> content) {
		this.content = content;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public Long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(Long totalElements) {
		this.totalElements = totalElements;
	}

	public Long getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Long totalPages) {
		this.totalPages = totalPages;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}
}