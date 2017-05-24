package com.soffid.iam.service;

import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Description;
import com.soffid.mda.annotation.Service;

import es.caib.seycon.ng.model.UsuariEntity;
import es.caib.seycon.ng.servei.UsuariService;

@Service(internal=true)
@Depends({UsuariService.class})
@Description ("Service to send emails")
public class MailService {
	public void sendHtmlMail(String to, String subject, String body) {}

	public void sendTextMail(String to, String subject, String body) {}

	public void sendHtmlMailToActors(String[] actors, String subject, String body) {}

	public void sendTextMailToActors(String[] actors, String subject, String body) {}
}
