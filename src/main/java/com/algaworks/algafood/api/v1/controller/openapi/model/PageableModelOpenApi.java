package com.algaworks.algafood.api.v1.controller.openapi.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Pageable")
public class PageableModelOpenApi {
	
	//18.20. Corrigindo documentação com substituição de Pageable
	
	@ApiModelProperty(example = "0", value = "Número da página (começa em 0)")
	private int page;
	
	@ApiModelProperty(example = "10", value = "Número de elementos por página")
	private int size;
	
	@ApiModelProperty(example = "nome,asc", value = "Nome da propriedade para ordenação dos elementos")
	private List<String> sort;
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public List<String> getSort() {
		return sort;
	}
	public void setSort(List<String> sort) {
		this.sort = sort;
	}
}