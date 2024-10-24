package com.soffid.iam.service;

import java.util.Collection;

import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Description;
import com.soffid.mda.annotation.Service;

import es.caib.seycon.ng.servei.AplicacioService;
import es.caib.seycon.ng.servei.AutoritzacioService;
import es.caib.seycon.ng.servei.ConfiguracioService;
import es.caib.seycon.ng.servei.GrupService;
import es.caib.seycon.ng.model.DispatcherEntity;
import es.caib.seycon.ng.model.UsuariEntity;
import es.caib.seycon.ng.servei.UsuariService;

@Service(internal=true)
@Depends({UsuariService.class, ConfiguracioService.class, AutoritzacioService.class, GrupService.class, AplicacioService.class,
	DispatcherEntity.class
})
@Description ("Service to send emails")
public class MailService {
	public void sendHtmlMail(String to, String subject, String body) {}

	public void sendTextMail(String to, String subject, String body) {}

	public void sendHtmlMail(String to, String subject, String body, Collection mimeBodyParts) {}

	public void sendHtmlMailToActors(String[] actors, String subject, String body) {}

	public void sendTextMailToActors(String[] actors, String subject, String body) {}

	public void sendTextMailToActors(String[] actors, String subject, String body, Collection mimeBodyParts) {}

	public void sendHtmlMail(String to, String cc, String subject, String body) {}

	public void sendTextMail(String to, String cc, String subject, String body) {}

	public void sendHtmlMail(String to, String cc, String subject, String body, Collection mimeBodyParts) {}

	public void sendHtmlMailToActors(String[] actors, String cc, String subject, String body) {}

	public void sendTextMailToActors(String[] actors, String cc, String subject, String body) {}

	public void sendTextMailToActors(String[] actors, String cc, String subject, String body, Collection mimeBodyParts) {}
}
