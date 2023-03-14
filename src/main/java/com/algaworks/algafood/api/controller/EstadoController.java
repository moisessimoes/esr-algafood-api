package com.algaworks.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.assembler.EstadoInputDisassembler;
import com.algaworks.algafood.api.assembler.EstadoModelAssembler;
import com.algaworks.algafood.api.controller.openapi.controller.EstadoControllerOpenApi;
import com.algaworks.algafood.api.model.EstadoModel;
import com.algaworks.algafood.api.model.input.EstadoInput;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.service.EstadoService;
import com.algaworks.algafood.repositories.EstadoRepository;

@RestController
@RequestMapping(path = "/estados", produces = MediaType.APPLICATION_JSON_VALUE)
public class EstadoController implements EstadoControllerOpenApi {
	
	@Autowired
	private EstadoService estadoService;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private EstadoModelAssembler estadoModelAssembler;
	
	@Autowired
	private EstadoInputDisassembler estadoInputDisassembler;
	
	@GetMapping
	public List<EstadoModel> listar() {
		return estadoModelAssembler.toCollectionModel(estadoRepository.findAll());
	}
	
	
	@GetMapping("/{estadoId}")
	public EstadoModel buscar(@PathVariable("estadoId") Long estadoId) {
		return estadoModelAssembler.toModel(estadoService.buscarPorId(estadoId));
		//return estadoRepository.findById(estadoId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Estado adicionar(@RequestBody @Valid EstadoInput estado) {
		Estado state = estadoInputDisassembler.toDomainObject(estado);
		return estadoService.salvar(state);
	}
	
	
	@PutMapping("/{estadoId}")
	public Estado atualizar(@PathVariable("estadoId") Long estadoId, @RequestBody @Valid EstadoInput estado) {
		
		Estado state = estadoService.buscarPorId(estadoId);
		
		estadoInputDisassembler.copyToDomainObject(estado, state);
		
		//BeanUtils.copyProperties(estado, state, "id");
		return estadoService.salvar(state);
	}
	
	
	@DeleteMapping("/{estadoId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("estadoId") Long estadoId) {
		estadoService.excluir(estadoId);
	}
}