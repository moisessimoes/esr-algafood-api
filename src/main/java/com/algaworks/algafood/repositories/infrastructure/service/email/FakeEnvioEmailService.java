package com.algaworks.algafood.repositories.infrastructure.service.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.algaworks.algafood.domain.service.EnvioEmailService;

public class FakeEnvioEmailService implements EnvioEmailService {
	
	private static final Logger log = LoggerFactory.getLogger("FakeEnvioEmailService");
	
	@Autowired
	private ProcessadorEmailTemplate processadorEmailTemplate;
	
	@Override
    public void enviar(Mensagem mensagem) {
        // Foi necessário alterar o modificador de acesso do método processarTemplate
        // da classe pai para "protected", para poder chamar aqui
        String corpo = processadorEmailTemplate.processarTemplate(mensagem);

        log.info("[FAKE E-MAIL] Para: {}\n{}", mensagem.getDestinatarios(), corpo);
    }
}