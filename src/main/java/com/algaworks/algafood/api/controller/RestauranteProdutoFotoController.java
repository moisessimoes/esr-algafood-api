package com.algaworks.algafood.api.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.algaworks.algafood.api.assembler.FotoProdutoModelAssembler;
import com.algaworks.algafood.api.model.FotoProdutoModel;
import com.algaworks.algafood.api.model.input.FotoProdutoInput;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.FotoProduto;
import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.service.CatalogoFotoProdutoService;
import com.algaworks.algafood.domain.service.FotoStorageService;
import com.algaworks.algafood.domain.service.ProdutoService;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/produtos/{produtoId}/foto")
public class RestauranteProdutoFotoController {
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private FotoStorageService fotoStorage;
	
	@Autowired
	private FotoProdutoModelAssembler fotoProdutoModelAssembler;
	
	@Autowired
	private CatalogoFotoProdutoService catalogoFotoProdutoService;
	
	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public FotoProdutoModel atualizarFoto(@PathVariable Long restauranteId, @PathVariable Long produtoId, @Valid FotoProdutoInput fotoProdutoInput) throws IOException {
		
		Produto produto = produtoService.buscarPorId(restauranteId, produtoId);
		
		MultipartFile file = fotoProdutoInput.getArquivo();
		
		FotoProduto foto = new FotoProduto();
		foto.setProduto(produto);
		foto.setNomeArquivo(file.getOriginalFilename());
		foto.setDescricao(fotoProdutoInput.getDescricao());
		foto.setContentType(file.getContentType());
		foto.setTamanho(file.getSize());
		
		FotoProduto fotoSalva = catalogoFotoProdutoService.salvar(foto, file.getInputStream());
		
		return fotoProdutoModelAssembler.toModel(fotoSalva);
	}
	
	
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public FotoProdutoModel buscarFoto(@PathVariable Long restauranteId, @PathVariable Long produtoId) {
		
		FotoProduto fotoProduto = catalogoFotoProdutoService.buscar(restauranteId, produtoId);
		return fotoProdutoModelAssembler.toModel(fotoProduto);
	}
	
	
	@GetMapping
	public ResponseEntity<InputStreamResource> servirFoto(@PathVariable Long restauranteId, 
														  @PathVariable Long produtoId,
														  @RequestHeader(name = "accept") String acceptHeader) throws HttpMediaTypeNotAcceptableException {
		
		try {
			
			FotoProduto fotoProduto = catalogoFotoProdutoService.buscar(restauranteId, produtoId);
			
			MediaType mediaType = MediaType.parseMediaType(fotoProduto.getContentType());
			List<MediaType> midiasAceitas = MediaType.parseMediaTypes(acceptHeader);
			
			verificarCompatibilidadeMediaType(mediaType, midiasAceitas);
			
			InputStream inputStream = fotoStorage.recuperar(fotoProduto.getNomeArquivo());
			return ResponseEntity.ok().contentType(mediaType).body(new InputStreamResource(inputStream));
			
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
		}
	}


	private void verificarCompatibilidadeMediaType(MediaType mediaType, List<MediaType> midiasAceitas) throws HttpMediaTypeNotAcceptableException {
		
		boolean compativel = midiasAceitas.stream().anyMatch(midiaAceita -> midiaAceita.isCompatibleWith(mediaType));
		
		if (!compativel) throw new HttpMediaTypeNotAcceptableException(midiasAceitas);
	}

//	private void transferirArquivo(FotoProdutoInput fotoProdutoInput) {
//		//14.2. Implementando upload de arquivo com multipart/form-data
//		
//		//Pegando o nome do arquivo que vou enviado
//		var nomeArquivo = UUID.randomUUID().toString() + "_" + fotoProdutoInput.getArquivo().getOriginalFilename();
//		
//		//Definindo o caminho em que esse arquivo vai ser salvo
//		//OBS: Esse caminho vai ser parametrizado depois
//		var arquivoFoto = Path.of("C:\\Users\\Moisés Simões\\Documents\\Curso Algaworks", nomeArquivo);
//		
//		System.out.println(fotoProdutoInput.getDescricao());
//		System.out.println(nomeArquivo);
//		System.out.println(fotoProdutoInput.getArquivo().getContentType());
//		
//		try {
//			fotoProdutoInput.getArquivo().transferTo(arquivoFoto);
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
//	}
}