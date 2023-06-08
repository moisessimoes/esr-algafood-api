package com.algaworks.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.AlgaLinks;
import com.algaworks.algafood.api.v1.controller.RestauranteController;
import com.algaworks.algafood.api.v1.model.RestauranteBasicoModel;
import com.algaworks.algafood.core.security.AlgaSecurity;
import com.algaworks.algafood.domain.model.Restaurante;

@Component
public class RestauranteBasicoModelAssembler extends RepresentationModelAssemblerSupport<Restaurante, RestauranteBasicoModel> {
	
	@Autowired
	private AlgaLinks algaLinks;
	
	@Autowired
    private ModelMapper modelMapper;
    
	@Autowired
	private AlgaSecurity algaSecurity; 
    
    public RestauranteBasicoModelAssembler() {
        super(RestauranteController.class, RestauranteBasicoModel.class);
    }
    
    @Override
    public RestauranteBasicoModel toModel(Restaurante restaurante) {
    	
        RestauranteBasicoModel restauranteModel = createModelWithId(restaurante.getId(), restaurante);
        
        modelMapper.map(restaurante, restauranteModel);
        
        if (algaSecurity.podeConsultar()) {
        	
        	restauranteModel.add(algaLinks.linkToRestaurantes("restaurantes"));
        	restauranteModel.getCozinha().add(algaLinks.linkToCozinha(restaurante.getCozinha().getId()));
        }
        
        
//        if (restaurante.ativacaoPermitida()) {
//        	restauranteModel.add(algaLinks.linkToRestauranteAtivacao(restaurante.getId(), "ativar"));
//        }
//
//        if (restaurante.inativacaoPermitida()) {
//        	restauranteModel.add(algaLinks.linkToRestauranteInativacao(restaurante.getId(), "inativar"));
//        }
//
//        if (restaurante.aberturaPermitida()) {
//        	restauranteModel.add(algaLinks.linkToRestauranteAbertura(restaurante.getId(), "abrir"));
//        }
//
//        if (restaurante.fechamentoPermitido()) {
//        	restauranteModel.add(algaLinks.linkToRestauranteFechamento(restaurante.getId(), "fechar"));
//        }
        
        return restauranteModel;
    }
    
    @Override
    public CollectionModel<RestauranteBasicoModel> toCollectionModel(Iterable<? extends Restaurante> entities) {
       
    	CollectionModel<RestauranteBasicoModel> collectionModel = super.toCollectionModel(entities);
		
		if (algaSecurity.podeConsultar()) {
			collectionModel.add(algaLinks.linkToRestaurantes());
		}
				
		return collectionModel;
    }   
}