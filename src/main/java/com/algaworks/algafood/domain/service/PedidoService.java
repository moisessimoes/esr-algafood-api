package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.exception.PedidoNaoEncontradoException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.model.Pedido;
import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.repositories.PedidoRepository;

@Service
public class PedidoService {
	
	@Autowired
	private CidadeService cidadeService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
    private PedidoRepository pedidoRepository;
	
	@Autowired
	private RestauranteService restauranteService;
	
	@Autowired
	private FormaPagamentoService formaPagamentoService;
	
	@Transactional
	public Pedido salvar(Pedido pedido) {
		
		validarPedido(pedido);
		validarItensPedido(pedido);
		
		pedido.setTaxaFrete(pedido.getRestaurante().getTaxaFrete());
		pedido.calcularValorTotal();
		
		return pedidoRepository.save(pedido);
	}
    
	
    public Pedido buscarPorId(String codigo) {
        return pedidoRepository.findByCodigo(codigo).orElseThrow(() -> new PedidoNaoEncontradoException(codigo));
    }
    
    
    private void validarPedido(Pedido pedido) {
    	
    	//Por hora, será usado o cliente fixo de ID 1
		Usuario usuario = usuarioService.buscarPorId(pedido.getCliente().getId());
    	Restaurante restaurante = restauranteService.buscarPorId(pedido.getRestaurante().getId());
    	Cidade cidade = cidadeService.buscarPorId(pedido.getEnderecoEntrega().getCidade().getId());
    	FormaPagamento formaPagamento = formaPagamentoService.buscarPorId(pedido.getFormaPagamento().getId());
    	
    	pedido.setCliente(usuario);
    	pedido.setRestaurante(restaurante);
    	pedido.setFormaPagamento(formaPagamento);
    	pedido.getEnderecoEntrega().setCidade(cidade);
    	
    	if (restaurante.naoAceitaFormaPagamento(formaPagamento)) {
    		throw new NegocioException(String.format("Forma de pagamento '%s' não é aceita por esse restaurante.", formaPagamento.getDescricao()));
    	}
    }
    
    private void validarItensPedido(Pedido pedido) {
    	
    	pedido.getItens().forEach(item -> {
    		
    		Produto produto = produtoService.buscarPorId(pedido.getRestaurante().getId(), item.getProduto().getId());
    		
    		item.setPedido(pedido);
    		item.setProduto(produto);
    		item.setPrecoUnitario(produto.getPreco());
    	});
    }
}