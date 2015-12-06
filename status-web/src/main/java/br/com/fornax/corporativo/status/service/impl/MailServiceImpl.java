package br.com.fornax.corporativo.status.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import br.com.fornax.corporativo.status.model.Rede;
import br.com.fornax.corporativo.status.service.MailService;
import br.com.fornax.corporativo.status.util.DateFormat;

@Service
public class MailServiceImpl implements MailService{
	@Autowired(required=false)
	private JavaMailSenderImpl mail;
	
	@Autowired
	private VelocityEngine velocityEngine;
	
	private String remetente = "sigporto@fornax.com.br";
	
	private String templateEmailVpnFora = "vpnFora.vm";
	
	private String templateEmailVpnOk = "vpnOk.vm";
	
	private String destinatario = "fornax@fornax.com.br";
	
	
	@Override
	@Async
	public void enviaEmailConexaoFora(final Rede servico) {
		
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
                
                message.setTo(destinatario);                
                message.setSubject("[Aviso] - Conexão com a VPN da Porto está fora");
        		
        		ClassPathResource logo = 
                		new ClassPathResource("/mail/logo.jpg");
        		
        		Map<String, Object> objects = new HashMap<String, Object>();
                objects.put("dateFormat", new DateFormat());
                objects.put("servico", servico);

        		final String text = VelocityEngineUtils.mergeTemplateIntoString(
                        velocityEngine, templateEmailVpnFora, "ISO-8859-1", objects);
        		message.setText(text, true);
        		message.addInline("logo", logo);
        		
            }
        };
        mail.send(preparator);
        System.out.println("E-mail enviado com sucesso para "+destinatario);
		
	}
	
	@Override
	@Async
	public void enviaEmailConexaoEstabelecida(final Rede servico) {
		
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true);
                
                message.setTo(destinatario);                
                message.setSubject("[Aviso] - Conexão estabelecida com a VPN da Porto ");
        		
        		ClassPathResource logo = 
                		new ClassPathResource("/mail/logo.jpg");
        		
        		Map<String, Object> objects = new HashMap<String, Object>();
                objects.put("dateFormat", new DateFormat());
                objects.put("servico", servico);
        		

        		final String text = VelocityEngineUtils.mergeTemplateIntoString(
                        velocityEngine, templateEmailVpnOk, "ISO-8859-1", objects);
        		message.setText(text, true);
        		message.addInline("logo", logo);
            }
        };
        mail.send(preparator);
        System.out.println("E-mail enviado com sucesso para "+destinatario);
		
	}

}
