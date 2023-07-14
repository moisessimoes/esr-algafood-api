package com.algaworks.algafood.api.v1.model.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import com.algaworks.algafood.core.validation.FileContentType;
import com.algaworks.algafood.core.validation.FileSize;

public class FotoProdutoInput {
	
	//@ApiModelProperty(hidden = true)
	@NotNull
	@FileSize(max = "900KB") //Valida o tamanho do arquivo
	@FileContentType(allowed = { MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE }) //Valida o tipo do arquivo
	private MultipartFile arquivo;
	
	//@ApiModelProperty(value = "Descrição da foto do produto", required = true)
	@NotBlank
	private String descricao;
	
	public MultipartFile getArquivo() {
		return arquivo;
	}
	public void setArquivo(MultipartFile arquivo) {
		this.arquivo = arquivo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}