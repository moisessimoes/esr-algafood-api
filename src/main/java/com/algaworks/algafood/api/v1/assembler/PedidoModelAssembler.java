package com.algaworks.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.AlgaLinks;
import com.algaworks.algafood.api.v1.controller.PedidoController;
import com.algaworks.algafood.api.v1.model.PedidoModel;
import com.algaworks.algafood.domain.model.Pedido;

@Component
public class PedidoModelAssembler extends RepresentationModelAssemblerSupport<Pedido, PedidoModel> {
	
	@Autowired
	private AlgaLinks algaLinks;
	
	@Autowired
    private ModelMapper modelMapper;
	
	
	public PedidoModelAssembler() {
		super(PedidoController.class, PedidoModel.class);
	}

    public PedidoModel toModel(Pedido pedido) {
    	
    	PedidoModel pedidoModel = createModelWithId(pedido.getCodigo(), pedido); 
    			
    	modelMapper.map(pedido, pedidoModel);
    	
    	//pedidoModel.add(linkTo(PedidoController.class).withRel("pedidos"));
    	
    	pedidoModel.add(algaLinks.linkToPedidos("pedidos")); //19.20. Refatorando construção e inclusão de links em representation model
    	
    	//19.23. Adicionando links condicionalmente
    	if (pedido.podeSerConfirmado()) {
    		pedidoModel.add(algaLinks.linkToConfirmacao(pedido.getCodigo(), "confirmar"));
    	}
    	
    	if (pedido.podeSerEntregue()) {
    		pedidoModel.add(algaLinks.linkToEntrega(pedido.getCodigo(), "entregar"));
    	}
    	
    	if (pedido.podeSerCancelado()) {
    		pedidoModel.add(algaLinks.linkToCancelamento(pedido.getCodigo(), "cancelar"));
    	}
    	
    	
    	pedidoModel.getCliente().add(algaLinks.linkToUsuario(pedido.getCliente().getId()));
    	
    	pedidoModel.getRestaurante().add(algaLinks.linkToRestaurante(pedido.getRestaurante().getId()));
    	
    	pedidoModel.getFormaPagamento().add(algaLinks.linkToFormaPagamento(pedido.getFormaPagamento().getId()));
    	
    	pedidoModel.getEnderecoEntrega().getCidade().add(algaLinks.linkToCidade(pedido.getEnderecoEntrega().getCidade().getId()));
    	
    	pedidoModel.getItens().forEach(item -> {
            item.add(algaLinks.linkToProdutos(pedidoModel.getRestaurante().getId(), item.getProdutoId(), "produto"));
        });
    	
        return pedidoModel;
    }
    
    @Override
    public CollectionModel<PedidoModel> toCollectionModel(Iterable<? extends Pedido> entities) {
    	return super.toCollectionModel(entities).add(algaLinks.linkToPedidos("pedidos"));
    }
    
//    public List<PedidoModel> toCollectionModel(List<Pedido> pedidos) {
//        return pedidos.stream().map(pedido -> toModel(pedido)).collect(Collectors.toList());
//    }
}