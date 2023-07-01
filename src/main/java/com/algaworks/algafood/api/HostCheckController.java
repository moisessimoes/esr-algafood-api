package com.algaworks.algafood.api;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HostCheckController {
	
	//24.16. Entendendo o Poor Man's Load Balancer (DNS Round Robin)
	
	@GetMapping("/hostcheck")
	public String checkHost() throws UnknownHostException {
		
		//Retorna o IP da maquina que está respondendo a requisição
		return InetAddress.getLocalHost().getHostAddress() + " - " + InetAddress.getLocalHost().getHostName();
	}
}