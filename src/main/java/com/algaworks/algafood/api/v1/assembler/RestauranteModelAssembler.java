package com.algaworks.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.AlgaLinks;
import com.algaworks.algafood.api.v1.controller.RestauranteController;
import com.algaworks.algafood.api.v1.model.RestauranteModel;
import com.algaworks.algafood.core.security.AlgaSecurity;
import com.algaworks.algafood.domain.model.Restaurante;

@Component
public class RestauranteModelAssembler extends RepresentationModelAssemblerSupport<Restaurante, RestauranteModel> {
	
	@Autowired
	private AlgaLinks algaLinks;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AlgaSecurity algaSecurity; 
	
	public RestauranteModelAssembler() {
		super(RestauranteController.class, RestauranteModel.class);
	}
	
	public RestauranteModel toModel(Restaurante restaurante) {
		
		RestauranteModel restauranteModel = createModelWithId(restaurante.getId(), restaurante);
		
		modelMapper.map(restaurante, restauranteModel);
		
		if (algaSecurity.podeConsultar()) {
			
			restauranteModel.add(algaLinks.linkToRestaurantes("restaurantes"));
			restauranteModel.getCozinha().add(algaLinks.linkToCozinha(restaurante.getCozinha().getId()));
			restauranteModel.add(algaLinks.linkToRestauranteFormasPagamento(restaurante.getId(), "formas-pagamento"));
			restauranteModel.add(algaLinks.linkToProdutos(restaurante.getId(), "produtos"));
			
			if (restauranteModel.getEndereco() != null && restauranteModel.getEndereco().getCidade() != null) {
				restauranteModel.getEndereco().getCidade().add(algaLinks.linkToCidade(restaurante.getEndereco().getCidade().getId()));
			}
		}
		
        if (algaSecurity.podeGerenciarCadastroRestaurante()) {
        	
        	restauranteModel.add(algaLinks.linkToResponsaveisRestaurante(restaurante.getId(), "responsaveis"));
        	
        	if (restaurante.ativacaoPermitida()) {
        		restauranteModel.add(algaLinks.linkToRestauranteAtivacao(restaurante.getId(), "ativar"));
        	}
        	
        	if (restaurante.inativacaoPermitida()) {
        		restauranteModel.add(algaLinks.linkToRestauranteInativacao(restaurante.getId(), "inativar"));
        	}
        }
        
        if (algaSecurity.podeGerenciarFuncionamentoRestaurante(restaurante.getId())) {
        	
        	if (restaurante.aberturaPermitida()) {
        		restauranteModel.add(algaLinks.linkToRestauranteAbertura(restaurante.getId(), "abrir"));
        	}
        	
        	if (restaurante.fechamentoPermitido()) {
        		restauranteModel.add(algaLinks.linkToRestauranteFechamento(restaurante.getId(), "fechar"));
        	}
        }
        
		return restauranteModel;
	}
	
	@Override
	public CollectionModel<RestauranteModel> toCollectionModel(Iterable<? extends Restaurante> entities) {
		
		CollectionModel<RestauranteModel> collectionModel = super.toCollectionModel(entities);
		
		if (algaSecurity.podeConsultar()) {
			collectionModel.add(algaLinks.linkToRestaurantes());
		}
		
		return collectionModel;
	}
	
//	public List<RestauranteModel> toCollectionModel(List<Restaurante> restaurantes) {
//		return restaurantes.stream().map(restaurante -> toModel(restaurante)).collect(Collectors.toList());
//	}
}