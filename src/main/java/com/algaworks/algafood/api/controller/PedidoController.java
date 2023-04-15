package com.algaworks.algafood.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.assembler.PedidoInputDisassembler;
import com.algaworks.algafood.api.assembler.PedidoModelAssembler;
import com.algaworks.algafood.api.assembler.PedidoResumoModelAssembler;
import com.algaworks.algafood.api.controller.openapi.controller.PedidoControllerOpenApi;
import com.algaworks.algafood.api.model.PedidoModel;
import com.algaworks.algafood.api.model.PedidoResumoModel;
import com.algaworks.algafood.api.model.input.PedidoInput;
import com.algaworks.algafood.core.data.PageWrapper;
import com.algaworks.algafood.core.data.PageableTranslator;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.filter.PedidoFilter;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.service.PedidoService;
import com.algaworks.algafood.repositories.PedidoRepository;
import com.algaworks.algafood.specification.PedidoSpecs;
import com.google.common.collect.ImmutableMap;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;

@RestController
@RequestMapping(path = "/pedidos", produces = MediaType.APPLICATION_JSON_VALUE)
public class PedidoController implements PedidoControllerOpenApi {
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
    private PedidoRepository pedidoRepository;
    
    @Autowired
    private PedidoModelAssembler pedidoModelAssembler;
    
    @Autowired
    private PedidoInputDisassembler pedidoInputDisassembler;
    
    @Autowired
    private PedidoResumoModelAssembler pedidoResumoModelAssembler;
    
    @Autowired
	private PagedResourcesAssembler<Pedido> pagedResourcesAssembler;
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoModel adicionar(@RequestBody @Valid PedidoInput pedidoInput) {
    	
    	try {
    		Pedido novoPedido = pedidoInputDisassembler.toDomainObject(pedidoInput);
        	
        	// TODO pegar usuário autenticado
            novoPedido.setCliente(new Usuario());
            novoPedido.getCliente().setId(1L);
            
            novoPedido = pedidoService.salvar(novoPedido);
            
            return pedidoModelAssembler.toModel(novoPedido);
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
    }
    
    
    @ApiImplicitParams({ //18.26. Descrevendo parâmetros implícitos em operações
    	@ApiImplicitParam(name = "campos", value = "Nomes das propriedades para filtrar na resposta, separados por vírgula.", paramType = "query", type = "string")
    })
    @GetMapping
    public PagedModel<PedidoResumoModel> pesquisar(PedidoFilter filtro, @PageableDefault(size = 10) Pageable pageable) {
    	
    	Pageable pageableTraduzido = traduzirPageable(pageable);
    	
    	Page<Pedido> pedidosPage = pedidoRepository.findAll(PedidoSpecs.usandoFiltro(filtro), pageableTraduzido);
    	
//    	List<PedidoResumoModel> pedidosModel = pedidoResumoModelAssembler.toCollectionModel(pedidos.getContent());
//    	Page<PedidoResumoModel> pedidosModelPage = new PageImpl<>(pedidosModel, pageable, pedidos.getTotalElements());
    	
    	pedidosPage = new PageWrapper<>(pedidosPage, pageable); //19.17. Corrigindo links de paginação com ordenação
    	
    	PagedModel<PedidoResumoModel> pagedPedidosModel = pagedResourcesAssembler.toModel(pedidosPage, pedidoResumoModelAssembler);
    	
    	return pagedPedidosModel;
    }
    
    
    //Limitando os campos retornados pela API com @JsonFilter do Jackson
//    @GetMapping
//    public MappingJacksonValue listar(@RequestParam(required = false) String campos) {
//        List<Pedido> pedidos = pedidoRepository.findAll();
//        List<PedidoResumoModel> pedidosModel = pedidoResumoModelAssembler.toCollectionModel(pedidos);
//        
//        MappingJacksonValue pedidosWrapper = new MappingJacksonValue(pedidosModel);
//        
//        SimpleFilterProvider filterProvider = new SimpleFilterProvider();
//        filterProvider.addFilter("pedidoFilter", SimpleBeanPropertyFilter.serializeAll());
//        
//        if (StringUtils.isNotBlank(campos)) {
//        	filterProvider.addFilter("pedidoFilter", SimpleBeanPropertyFilter.filterOutAllExcept(campos.split(",")));
//        }
//        
//        pedidosWrapper.setFilters(filterProvider);
//        
//        return pedidosWrapper;
//    }

    
    @ApiImplicitParams({ //18.26. Descrevendo parâmetros implícitos em operações
    	@ApiImplicitParam(name = "campos", value = "Nomes das propriedades para filtrar na resposta, separados por vírgula.", paramType = "query", type = "string")
    })
    @GetMapping("/{codigoPedido}")
    public PedidoModel buscar(@PathVariable String codigoPedido) {
        Pedido pedido = pedidoService.buscarPorId(codigoPedido);
        return pedidoModelAssembler.toModel(pedido);
    }
    
    
    private Pageable traduzirPageable(Pageable pageable) {
    	
    	//Poderia usar tambem o Map.of, da propria API do Java, que aceita ate 10 chaves-valores
    	
    	var mapeamento = ImmutableMap.of(
    			"codigo", "codigo",
    			"subtotal", "subtotal",
    			"taxaFrete", "taxaFrete",
    			"valorTotal", "valorTotal",
    			"dataCriacao", "dataCriacao",
    			"nomerestaurante", "restaurante.nome",
    			"restaurante.id", "restaurante.id",
    			"cliente.id", "cliente.id",
    			"nomeCliente", "cliente.nome"
    	);
    	
    	return PageableTranslator.translate(pageable, mapeamento);
    }
}