package com.algaworks.algafood.domain.listener;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import com.algaworks.algafood.domain.event.PedidoConfirmadoEvent;
import com.algaworks.algafood.domain.service.EnvioEmailService;
import com.algaworks.algafood.domain.service.EnvioEmailService.Mensagem;

@Component
public class NotificacaoClientePedidoConfirmadoListener {
	
	@Autowired
	private EnvioEmailService envioEmail;
	
	@TransactionalEventListener
	public void aoConfirmarPedido(PedidoConfirmadoEvent event) {
		
		var mensagem = new Mensagem();
		mensagem.setAssunto(event.getPedido().getRestaurante().getNome() + " - Pedido Confirmado");
		mensagem.setCorpo("emails/pedido-confirmado.html");
		
		Map<String, Object> variaveisEmailTemplate = new HashMap<>();
		variaveisEmailTemplate.put("pedido", event.getPedido());
		
		mensagem.setVariaveis(variaveisEmailTemplate);
		
		Set<String> destinatarios = new HashSet<>();
		destinatarios.add(event.getPedido().getCliente().getEmail());
		
		mensagem.setDestinatarios(destinatarios);
		
		envioEmail.enviar(mensagem);
	}
}