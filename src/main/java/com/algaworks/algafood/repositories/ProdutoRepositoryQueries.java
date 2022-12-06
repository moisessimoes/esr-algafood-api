package com.algaworks.algafood.repositories;

import com.algaworks.algafood.domain.model.FotoProduto;

public interface ProdutoRepositoryQueries {
	
	FotoProduto salvar(FotoProduto foto);
	
	void delete(FotoProduto foto);

}