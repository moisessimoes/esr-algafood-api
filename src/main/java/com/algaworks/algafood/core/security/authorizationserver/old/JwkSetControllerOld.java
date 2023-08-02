package com.algaworks.algafood.core.security.authorizationserver.old;

//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.nimbusds.jose.jwk.JWKSet;

//@RestController
//public class JwkSetControllerOld { //23.45. Implementando o endpoint do JSON Web Key Set (JWKS)
//	
//	@Autowired
//	private JWKSet jwkSet;
//	
//	@GetMapping("/.well-known/jwks.json")
//	public Map<String, Object> keys() {
//		
//		return this.jwkSet.toJSONObject();
//	}
//}