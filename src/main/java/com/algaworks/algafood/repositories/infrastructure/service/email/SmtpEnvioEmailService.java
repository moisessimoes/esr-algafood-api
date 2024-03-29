package com.algaworks.algafood.repositories.infrastructure.service.email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.algaworks.algafood.core.email.EmailProperties;
import com.algaworks.algafood.domain.service.EnvioEmailService;

public class SmtpEnvioEmailService implements EnvioEmailService {
	
	//15.3. Implementando o serviço de infraestrutura de envio de e-mails com Spring

	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private EmailProperties emailProperties;
	
	@Autowired
	private ProcessadorEmailTemplate processadorEmailTemplate;
	
	//NAO FUNCIONA O ENVIO DO EMAIL!!!
	//ERRO: 550 The from address does not match a verified Sender Identity.
	
	@Override
	public void enviar(Mensagem mensagem) {
		
		try {
			
			MimeMessage mimeMessage = criarMimeMessage(mensagem);
			
			System.out.println("REMETENTE: " + emailProperties.getRemetente());
			System.out.println("DESTINATÁRIO: " + mensagem.getDestinatarios().toString());
			
			mailSender.send(mimeMessage);
			
		} catch (Exception e) {
			throw new EmailException("Não foi possível enviar e-mail.", e);
		}
	}
	
	
	protected MimeMessage criarMimeMessage(Mensagem mensagem) throws MessagingException {
		
	    String corpo = processadorEmailTemplate.processarTemplate(mensagem);
	    
	    MimeMessage mimeMessage = mailSender.createMimeMessage();
	    
	    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
	    helper.setFrom(emailProperties.getRemetente());
	    helper.setTo(mensagem.getDestinatarios().toArray(new String[0]));
	    helper.setSubject(mensagem.getAssunto());
	    helper.setText(corpo, true);
	    
	    return mimeMessage;
	}
}