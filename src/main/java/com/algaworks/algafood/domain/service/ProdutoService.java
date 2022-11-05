package com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.ProdutoNaoEncontradoException;
import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.repositories.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private RestauranteService restauranteService;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public Produto salvar(Produto produto) {
		return produtoRepository.save(produto);
	}
	
	
	public Produto buscarPorId(Long restauranteId, Long produtoId) {
		return produtoRepository.findById(restauranteId, produtoId).orElseThrow(() -> new ProdutoNaoEncontradoException(produtoId, restauranteId));
	}
	
	
	public List<Produto> listar(Long restauranteId) {
		
		Restaurante restaurante = restauranteService.buscarPorId(restauranteId);
		return produtoRepository.findByRestaurante(restaurante);
	}
}