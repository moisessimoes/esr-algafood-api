package com.algaworks.algafood.core.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController { //23.43. Customizando a página de login
	
	@GetMapping("/login")
	public String login() {
		return "pages/login";
	}
	
	@GetMapping("/oauth/confirm_access")
	public String approval() {
		return "pages/approval";
	}
}