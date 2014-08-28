package com.soffid.iam.service;

import com.soffid.mda.annotation.Description;
import com.soffid.mda.annotation.Service;

@Service(internal=true)
@Description ("Service to send emails")
public class MailService {
	public void sendHtmlMail(String to, String subject, String body) {}

	public void sendTextMail(String to, String subject, String body) {}

}
