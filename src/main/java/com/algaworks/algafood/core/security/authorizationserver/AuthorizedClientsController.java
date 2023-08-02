package com.algaworks.algafood.core.security.authorizationserver;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.server.authorization.OAuth2Authorization;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizedClientsController { //27.20. Criando uma página de listagem dos clientes com consentimentos permitidos
	
	@Autowired
	private RegisteredClientRepository clientRepository;
	
	@Autowired
	private OAuth2AuthorizationService authorizationService;
	
	@Autowired
	private OAuth2AuthorizationConsentService consentService;
	
	@Autowired
	private OAuth2AuthorizationQueryService oAuth2AuthorizationQueryService;
	
	@GetMapping("/oauth2/authorized-clients")
	public String clientList(Principal principal, Model model) {
		
		List<RegisteredClient> clients = oAuth2AuthorizationQueryService.listClientsWithConsent(principal.getName());
		model.addAttribute("clients", clients);
		
		return "pages/authorized-clients";
	}
	
	
	@PostMapping("/oauth2/authorized-clients/revoke")
	public String revoke(Principal principal, Model model, @RequestParam(OAuth2ParameterNames.CLIENT_ID) String clientId) {
		
		//27.21. Revogando consentimentos e autorizações dos clientes
		
		var client = this.clientRepository.findByClientId(clientId);
		
		if (client == null) {
			throw new AccessDeniedException(String.format("Cliente de ID %s não foi encontrado.", clientId));
		}
		
		var consent = this.consentService.findById(client.getId(), principal.getName());
		
		List<OAuth2Authorization> authorizations = this.oAuth2AuthorizationQueryService.listAuthorizations(principal.getName(), client.getId());
		
		if (consent != null) {
			this.consentService.remove(consent);
		}
		
		authorizations.forEach(auth -> this.authorizationService.remove(auth));
		
		return "redirect:/oauth2/authorized-clients";
	}
}