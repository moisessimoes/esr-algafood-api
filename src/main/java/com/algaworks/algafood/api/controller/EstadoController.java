package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.service.EstadoService;
import com.algaworks.algafood.repositories.EstadoRepository;

@RestController
@RequestMapping("/estados")
public class EstadoController {
	
	@Autowired
	private EstadoService estadoService;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@GetMapping
	public List<Estado> listar() {
		return estadoRepository.findAll();
	}
	
	
	@GetMapping("/{estadoId}")
	public Estado buscar(@PathVariable("estadoId") Long estadoId) {
		return estadoService.buscarPorId(estadoId);
		//return estadoRepository.findById(estadoId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Estado adicionar(@RequestBody Estado estado) {
		return estadoService.salvar(estado);
	}
	
	
	@PutMapping("/{estadoId}")
	public Estado atualizar(@PathVariable("estadoId") Long estadoId, @RequestBody Estado estado) {
		
		Estado state = estadoService.buscarPorId(estadoId);
		
		BeanUtils.copyProperties(estado, state, "id");
		return estadoService.salvar(state);
	}
	
	
	@DeleteMapping("/{estadoId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("estadoId") Long estadoId) {
		estadoService.excluir(estadoId);
	}
}