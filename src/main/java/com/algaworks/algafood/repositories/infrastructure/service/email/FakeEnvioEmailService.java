package com.algaworks.algafood.repositories.infrastructure.service.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FakeEnvioEmailService extends SmtpEnvioEmailService {
	
	private static final Logger log = LoggerFactory.getLogger("FakeEnvioEmailService");
	
	@Override
    public void enviar(Mensagem mensagem) {
        // Foi necessário alterar o modificador de acesso do método processarTemplate
        // da classe pai para "protected", para poder chamar aqui
        String corpo = processarTemplate(mensagem);

        log.info("[FAKE E-MAIL] Para: {}\n{}", mensagem.getDestinatarios(), corpo);
    }
}