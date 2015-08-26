package com.soffid.iam.service;

import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Description;
import com.soffid.mda.annotation.Service;

import es.caib.seycon.ng.servei.ConfiguracioService;

@Service(internal=true)
@Description ("Service to send emails")
@Depends({ConfiguracioService.class})
public class MailService {
	public void sendHtmlMail(String to, String subject, String body) {}

	public void sendTextMail(String to, String subject, String body) {}

}
