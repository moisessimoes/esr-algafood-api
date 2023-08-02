package com.algaworks.algafood.core.security.authorizationserver.old;

//import java.util.HashMap;
//
//import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
//import org.springframework.security.oauth2.common.OAuth2AccessToken;
//import org.springframework.security.oauth2.provider.OAuth2Authentication;
//import org.springframework.security.oauth2.provider.token.TokenEnhancer;

//@SuppressWarnings("deprecation")
//public class JwtCustomClaimsTokenEnhancerOld implements TokenEnhancer {
//	
//	//23.16. Adicionando claims customizadas no payload do JWT
//
//	@Override
//	public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
//		
//		if (authentication.getPrincipal() instanceof AuthUser) {
//			
//			var authUser = (AuthUser) authentication.getPrincipal();
//			
//			var info = new HashMap<String, Object>();
//			info.put("nome_completo", authUser.getFullName());
//			info.put("usuario_id", authUser.getUserId());
//			
//			var oAuth2AcessToken = (DefaultOAuth2AccessToken) accessToken;
//			oAuth2AcessToken.setAdditionalInformation(info);
//		}
//		return accessToken;
//	}
//}