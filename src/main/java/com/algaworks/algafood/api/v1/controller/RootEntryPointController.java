package com.algaworks.algafood.api.v1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.v1.AlgaLinks;

import io.swagger.annotations.Api;

@Api(tags = "Ponto de Entrada Raíz")
@RestController
@RequestMapping(path = "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
public class RootEntryPointController { //19.36. Implementando o Root Entry Point da API
	
	@Autowired
	private AlgaLinks algaLinks;
	
	@GetMapping
	public RootEntryPointModel root() {
		
		var rootEntryModel = new RootEntryPointModel();
		
		rootEntryModel.add(algaLinks.linkToCozinhas("cozinhas"));
		rootEntryModel.add(algaLinks.linkToPedidos("pedidos"));
		rootEntryModel.add(algaLinks.linkToRestaurantes("restaurantes"));
		rootEntryModel.add(algaLinks.linkToGrupos("grupos"));
		rootEntryModel.add(algaLinks.linkToUsuarios("usuarios"));
		rootEntryModel.add(algaLinks.linkToPermissoes("permissoes"));
		rootEntryModel.add(algaLinks.linkToFormasPagamento("formas-pagamento"));
		rootEntryModel.add(algaLinks.linkToEstados("estados"));
		rootEntryModel.add(algaLinks.linkToCidades("cidades"));
		rootEntryModel.add(algaLinks.linkToEstatisticas("estatisticas"));
		
		return rootEntryModel;
	}
	
	private static class RootEntryPointModel extends RepresentationModel<RootEntryPointModel> {}
}