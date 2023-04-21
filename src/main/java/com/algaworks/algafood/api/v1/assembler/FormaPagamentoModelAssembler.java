package com.algaworks.algafood.api.v1.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.api.v1.AlgaLinks;
import com.algaworks.algafood.api.v1.controller.FormaPagamentoController;
import com.algaworks.algafood.api.v1.model.FormaPagamentoModel;
import com.algaworks.algafood.domain.model.FormaPagamento;

@Component
public class FormaPagamentoModelAssembler extends RepresentationModelAssemblerSupport<FormaPagamento, FormaPagamentoModel> {
	
	@Autowired
	private AlgaLinks algaLinks;

	@Autowired
	private ModelMapper modelMapper;
	
	public FormaPagamentoModelAssembler() {
		super(FormaPagamentoController.class, FormaPagamentoModel.class);
	}
	
	public FormaPagamentoModel toModel(FormaPagamento formaPagamento) {
		
		FormaPagamentoModel formaPagamentoModel = createModelWithId(formaPagamento.getId(), formaPagamento);
		
		modelMapper.map(formaPagamento, formaPagamentoModel);
		
		formaPagamentoModel.add(algaLinks.linkToFormasPagamento("formasPagamento"));
		
		//formaPagamentoModel.add(algaLinks.linkToRestauranteFormasPagamento(formaPagamento.getId()));
		
		return formaPagamentoModel;
	}
	
	@Override
	public CollectionModel<FormaPagamentoModel> toCollectionModel(Iterable<? extends FormaPagamento> entities) {
		return super.toCollectionModel(entities).add(algaLinks.linkToFormasPagamento());
	}
	
//	public List<FormaPagamentoModel> toCollectionModel(Collection<FormaPagamento> formasPagamento) {
//		return formasPagamento.stream().map(pgto -> toModel(pgto)).collect(Collectors.toList());
//	}
}