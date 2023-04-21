package com.algaworks.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.AlgaLinks;
import com.algaworks.algafood.api.v1.controller.PedidoController;
import com.algaworks.algafood.api.v1.model.PedidoResumoModel;
import com.algaworks.algafood.domain.model.Pedido;

@Component
public class PedidoResumoModelAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoResumoModel> {
	
	@Autowired
	private AlgaLinks algaLinks;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public PedidoResumoModelAssembler() {
		super(PedidoController.class, PedidoResumoModel.class);
	}

    
    public PedidoResumoModel toModel(Pedido pedido) {
    	
    	PedidoResumoModel pedidoResumoModel = createModelWithId(pedido.getCodigo(), pedido);
    	
    	modelMapper.map(pedido, pedidoResumoModel);
    	
    	pedidoResumoModel.add(algaLinks.linkToPedidos("pedidos"));
    	
    	pedidoResumoModel.getCliente().add(algaLinks.linkToUsuario(pedido.getCliente().getId())); 
    	
    	pedidoResumoModel.getRestaurante().add(algaLinks.linkToRestaurante(pedido.getRestaurante().getId()));
    	
        return pedidoResumoModel; 
    }
    
    @Override
    public CollectionModel<PedidoResumoModel> toCollectionModel(Iterable<? extends Pedido> entities) {
    	return super.toCollectionModel(entities).add(algaLinks.linkToPedidos("pedidos"));
    }
    
//    public List<PedidoResumoModel> toCollectionModel(List<Pedido> pedidos) {
//        return pedidos.stream().map(pedido -> toModel(pedido)).collect(Collectors.toList());
//    }
}