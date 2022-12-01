package com.algaworks.algafood.api.controller;

import java.nio.file.Path;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.model.input.FotoProdutoInput;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {
	
	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public void atualizarFoto(@PathVariable Long restauranteId, @PathVariable Long produtoId, @Valid FotoProdutoInput fotoProdutoInput) {
		
		//14.2. Implementando upload de arquivo com multipart/form-data
		
		
		//Pegando o nome do arquivo que vou enviado
		var nomeArquivo = UUID.randomUUID().toString() + "_" + fotoProdutoInput.getArquivo().getOriginalFilename();
		
		//Definindo o caminho em que esse arquivo vai ser salvo
		//OBS: Esse caminho vai ser parametrizado depois
		var arquivoFoto = Path.of("C:\\Users\\Moisés Simões\\Documents\\Curso Algaworks", nomeArquivo);
		
		System.out.println(fotoProdutoInput.getDescricao());
		System.out.println(nomeArquivo);
		System.out.println(fotoProdutoInput.getArquivo().getContentType());
		
		try {
			fotoProdutoInput.getArquivo().transferTo(arquivoFoto);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	}
}