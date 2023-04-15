package com.algaworks.algafood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.controller.RestauranteProdutoController;
import com.algaworks.algafood.api.model.ProdutoModel;
import com.algaworks.algafood.api.utils.AlgaLinks;
import com.algaworks.algafood.domain.model.Produto;

@Component
public class ProdutoModelAssembler extends RepresentationModelAssemblerSupport<Produto, ProdutoModel> {
	
	@Autowired
	private AlgaLinks algaLinks;
	
	@Autowired
	private ModelMapper modelMapper;
	
	 public ProdutoModelAssembler() {
		super(RestauranteProdutoController.class, ProdutoModel.class);
	}

    
    public ProdutoModel toModel(Produto produto) {
    	
    	ProdutoModel produtoModel = createModelWithId(produto.getId(), produto, produto.getRestaurante().getId());
    	
        modelMapper.map(produto, produtoModel);
        
        produtoModel.add(algaLinks.linkToProdutos(produto.getRestaurante().getId(), "produtos"));
        
        produtoModel.add(algaLinks.linkToFotoProduto(produto.getRestaurante().getId(), produto.getId(), "foto"));
        
        return produtoModel;
    }
    
    @Override
    public CollectionModel<ProdutoModel> toCollectionModel(Iterable<? extends Produto> entities) {
    	return super.toCollectionModel(entities);
    }
    
//    public List<ProdutoModel> toCollectionModel(List<Produto> produtos) {
//        return produtos.stream().map(produto -> toModel(produto)).collect(Collectors.toList());
//    } 
}