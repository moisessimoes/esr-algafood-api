package com.algaworks.algafood.domain.service;

import java.io.InputStream;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.model.FotoProduto;
import com.algaworks.algafood.domain.service.FotoStorageService.NovaFoto;
import com.algaworks.algafood.repositories.ProdutoRepository;

@Service
public class CatalogoFotoProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private FotoStorageService fotoStorageService;
	
	@Transactional
	public FotoProduto salvar(FotoProduto foto, InputStream dadosArquivo) {
		
		Long restauranteId = foto.getRestauranteId();
		Long produtoId = foto.getProduto().getId();
		
		String nomeNovoArquivo = fotoStorageService.generateFileName(foto.getNomeArquivo());
		String nomeArquivoExistente = null;
		
		Optional<FotoProduto> fotoExistente = produtoRepository.findFotoById(restauranteId, produtoId);
		
		if (fotoExistente.isPresent()) {
			
			nomeArquivoExistente = fotoExistente.get().getNomeArquivo();
			
			//Exclui foto existente
			produtoRepository.delete(fotoExistente.get());
		}
		
		foto.setNomeArquivo(nomeNovoArquivo);
		foto = produtoRepository.salvar(foto);
		produtoRepository.flush();
		
		NovaFoto novaFoto = new NovaFoto(foto.getNomeArquivo(), dadosArquivo); 
		
		fotoStorageService.substituirArquivo(nomeArquivoExistente, novaFoto);
		
		return foto;
	}
}