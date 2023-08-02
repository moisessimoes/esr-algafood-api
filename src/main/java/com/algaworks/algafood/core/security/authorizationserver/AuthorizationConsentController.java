package com.algaworks.algafood.core.security.authorizationserver;

import java.security.Principal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizationConsentController { //27.18. Customizando a página de consentimento do OAuth2
	
	@Autowired
	private RegisteredClientRepository registeredClientRepository;
	
	@Autowired
	private OAuth2AuthorizationConsentService consentService;
	
	@GetMapping("/oauth2/consent")
	public String consent(Principal principal, 
						  Model model, 
						  @RequestParam(OAuth2ParameterNames.CLIENT_ID) String clientId, 
						  @RequestParam(OAuth2ParameterNames.SCOPE) String scope, 
						  @RequestParam(OAuth2ParameterNames.STATE) String state) {
		
		var client = this.registeredClientRepository.findByClientId(clientId);
		
		if (client == null) {
			throw new AccessDeniedException(String.format("Cliente de ID %s não foi encontrado.", clientId));
		}
		
		var consent = this.consentService.findById(client.getId(), principal.getName());
		
		String[] scopesArray = StringUtils.delimitedListToStringArray(scope, " ");
		Set<String> scopesToApprove = new HashSet<>(Set.of(scopesArray));
		
		Set<String> previouslyApprovedScopes;
		
		if (consent != null) {
			previouslyApprovedScopes = consent.getScopes();
			scopesToApprove.removeAll(previouslyApprovedScopes);
			
		} else {
			previouslyApprovedScopes = Collections.emptySet();
		}
		
		model.addAttribute("clientId", clientId);
		model.addAttribute("state", state);
		model.addAttribute("principalName", principal.getName());
		model.addAttribute("scopesParaAprovar", scopesToApprove);
		model.addAttribute("scopesAprovadosAnteriormente", previouslyApprovedScopes);
		
		return "pages/approval";
	}
}