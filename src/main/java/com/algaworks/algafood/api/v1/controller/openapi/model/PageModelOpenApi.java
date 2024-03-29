package com.algaworks.algafood.api.v1.controller.openapi.model;

//@ApiModel("PageModel")
public class PageModelOpenApi {
	
	//@ApiModelProperty(example = "10", value = "Número de elementos por página")
	private Long size;
	
	//@ApiModelProperty(example = "50", value = "Total de elementos")
	private Long totalElements;
	
	//@ApiModelProperty(example = "5", value = "Total de páginas")
	private Long totalPages;
	
	//@ApiModelProperty(example = "0", value = "Número da página (começa em 0)")
	private Long number;

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