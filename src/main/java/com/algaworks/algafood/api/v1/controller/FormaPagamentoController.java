package com.algaworks.algafood.api.v1.controller;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.ShallowEtagHeaderFilter;

import com.algaworks.algafood.api.v1.assembler.FormaPagamentoInputDisassembler;
import com.algaworks.algafood.api.v1.assembler.FormaPagamentoModelAssembler;
import com.algaworks.algafood.api.v1.controller.openapi.controller.FormaPagamentoControllerOpenApi;
import com.algaworks.algafood.api.v1.model.FormaPagamentoModel;
import com.algaworks.algafood.api.v1.model.input.FormaPagamentoInput;
import com.algaworks.algafood.domain.exception.FormaPagamentoNaoEncontradoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.service.FormaPagamentoService;
import com.algaworks.algafood.repositories.FormaPagamentoRepository;

@RestController
@RequestMapping(path = "/v1/pagamentos", produces = MediaType.APPLICATION_JSON_VALUE)
public class FormaPagamentoController implements FormaPagamentoControllerOpenApi {

	@Autowired
	private FormaPagamentoService formaPagamentoService;
	
	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;
	
	@Autowired
	private FormaPagamentoModelAssembler formaPagamentoModelAssembler;
	
	@Autowired
	private FormaPagamentoInputDisassembler formaPagamentoInputDisassembler;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public FormaPagamento cadastrar(@RequestBody @Valid FormaPagamentoInput formaPagamento) {
		FormaPagamento pagamento = formaPagamentoInputDisassembler.toDomainObject(formaPagamento);
		return formaPagamentoService.salvar(pagamento);
	}
	
	
	@PutMapping("/{formaPagamentoId}")
	public FormaPagamento atualizar(@PathVariable Long formaPagamentoId, @RequestBody @Valid FormaPagamentoInput formaPagamento) {
		
		FormaPagamento pagamento = formaPagamentoService.buscarPorId(formaPagamentoId);
		
		formaPagamentoInputDisassembler.copyToDomainObject(formaPagamento, pagamento);
		
		try {
			return formaPagamentoService.salvar(pagamento);
			
		} catch (FormaPagamentoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	
	@GetMapping("/{formaPagamentoId}")
	public ResponseEntity<FormaPagamentoModel> buscar(@PathVariable Long formaPagamentoId, ServletWebRequest request) {
		
		//==================================================================================
		//17.10. Desafio: implementando requisições condicionais com Deep ETags
		
		ShallowEtagHeaderFilter.disableContentCaching(request.getRequest()); //Desabilita o Shallow eTag
		
		String eTag = "0";
		
		OffsetDateTime dataUltimaAtualizacao = formaPagamentoRepository.getLastUpdateById(formaPagamentoId);
		
		if (dataUltimaAtualizacao != null) {
			eTag = String.valueOf(dataUltimaAtualizacao.toEpochSecond());
		}
		
		if (request.checkNotModified(eTag)) {
			return null;
		}
		
		//==================================================================================
		
		
		//17.3. Desafio: adicionando o cabeçalho Cache-Control na resposta
		
		FormaPagamento formaPagamento = formaPagamentoService.buscarPorId(formaPagamentoId);
		
		FormaPagamentoModel formaPagamentoModel = formaPagamentoModelAssembler.toModel(formaPagamento);
		
		return ResponseEntity.ok()
							 .cacheControl(CacheControl.maxAge(15, TimeUnit.SECONDS))
							 .body(formaPagamentoModel);
	}
	
	
	@GetMapping
	public ResponseEntity<CollectionModel<FormaPagamentoModel>> listar(ServletWebRequest request) {
		
		//==================================================================================
		//17.9. Implementando requisições condicionais com Deep ETags
		
		ShallowEtagHeaderFilter.disableContentCaching(request.getRequest()); //Desabilita o Shallow eTag
		
		String eTag = "0";
		
		OffsetDateTime dataUltimaAtualizacao = formaPagamentoRepository.getLastUpdate();
		
		if (dataUltimaAtualizacao != null) {
			eTag = String.valueOf(dataUltimaAtualizacao.toEpochSecond());
		}
		
		//Se o que for passado no if-None-Match for igual ao eTag gerado, quer dizer que não houve alteração nos registros
		if (request.checkNotModified(eTag)) {
			return null;
		}
		
		//==================================================================================
		
		//17.2. Habilitando o cache com o cabeçalho Cache-Control e a diretiva max-age
		
		List<FormaPagamento> todasAsFormasPagamento = formaPagamentoRepository.findAll();
		
		CollectionModel<FormaPagamentoModel> todasAsFormasPagamentoModel = formaPagamentoModelAssembler.toCollectionModel(todasAsFormasPagamento); 
		
		/*O metodo cacheControl é o responsável por manter a representação do JSON 'fresca' por um periodo de tempo determinado
		 * 
		 * Apos isso, a requisição é feita normalmente
		 * 
		 * No exemplo abaixo, temos o seguinte:
		 * 
		 * Quando uma primeira requisição for feita nesse GET, vai ser feita a consulta no banco para buscar os dados
		 * 
		 * Se no intervalo de tempo determinado for feita uma nova requisição, a consulta no banco não vai ser feita,
		 * pois os dados estão armazenados em cache. Isso gera mais agilidade na hora da API trazer os dados.
		 * 
		 * - .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS)) Controla quanto tempo os dados ficaram disponiveis no cache
		 * 
		 * OBS: Isso é válido apenas para testar o navegador, pois o mesmo possui cache local
		 * 
		 * No postman não funciona o cache.
		 * 
		 * */
		
		//17.6. Adicionando outras diretivas de Cache-Control na resposta HTTP
		
		return ResponseEntity.ok()
//							 .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
//							 .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePrivate()) //resposta so pode ficar em cache local
							 .cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS).cachePublic()) //resposta pode ficar em cache local e compartilhado
//							 .cacheControl(CacheControl.noCache()) //se a resposta for cacheada, sempre vai ser necessário validar no servidor
//							 .cacheControl(CacheControl.noStore()) //nenhum cache pode armazenar a resposta
							 .eTag(eTag)
							 .body(todasAsFormasPagamentoModel);
	}
	
	
	@DeleteMapping("/{formaPagamentoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long formaPagamentoId) {
		formaPagamentoService.excluir(formaPagamentoId);
	}
}