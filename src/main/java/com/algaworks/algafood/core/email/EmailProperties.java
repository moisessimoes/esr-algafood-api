package com.algaworks.algafood.core.email;

import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Validated
@Component
@ConfigurationProperties("algafood.email")
public class EmailProperties {
	
	//15.3. Implementando o serviço de infraestrutura de envio de e-mails com Spring
	
	@NotNull
	private String remetente;
	
	// Atribuimos FAKE como padrão
	// Isso evita o problema de enviar e-mails de verdade caso você esqueça
	// de definir a propriedade
	private Implementacao impl = Implementacao.FAKE;
	
	private Sandbox sandbox = new Sandbox();
	
	public enum Implementacao {
		
		SMTP, SANDBOX, FAKE;
	}
	
	public class Sandbox {
		
		private String destinatario;

		public String getDestinatario() {
			return destinatario;
		}

		public void setDestinatario(String destinatario) {
			this.destinatario = destinatario;
		}
	}

	public String getRemetente() {
		return remetente;
	}

	public void setRemetente(String remetente) {
		this.remetente = remetente;
	}

	public Implementacao getImpl() {
		return impl;
	}

	public void setImpl(Implementacao impl) {
		this.impl = impl;
	}

	public Sandbox getSandbox() {
		return sandbox;
	}

	public void setSandbox(Sandbox sandbox) {
		this.sandbox = sandbox;
	}
}