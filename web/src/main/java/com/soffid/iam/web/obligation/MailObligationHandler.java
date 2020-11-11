package com.soffid.iam.web.obligation;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.zkoss.util.resource.Labels;

import com.soffid.iam.ServiceLocator;
import com.soffid.iam.api.Account;
import com.soffid.iam.utils.MailUtils;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.zkiblaf.Application;
import es.caib.zkib.zkiblaf.Missatgebox;

public class MailObligationHandler {

	public void sendMail( Map<String, String> map) throws InternalErrorException {
		String account = map.get("account");
		String systemName = map.get("systemName");
		
		Account acc = ServiceLocator.instance().getAccountService().findAccount(account, systemName);
		if (acc != null) {
			String subject = "Service account usage notification";
			if (map.get("subject") != null)
				subject = map.get("subject");
			subject += " "+ acc.getLoginName() + " @ "+ acc.getServerName() ;
			
			
			
			String body = String.format("The user %s has accessed to:\nAccount name: %s\nSystem name :%s\nLogin name  :%s\nServer      : %s\nAction      : %s\n",
					Security.getSoffidPrincipal().getFullName(),
					account,
					systemName,
					acc.getLoginName(),
					acc.getServerName(),
					map.get("action"));
					
			if (map.get("sendTo") != null)
				ServiceLocator.instance().getMailService().sendTextMail(map.get("sendTo"), subject, body);
			else {
				List<String> target = new LinkedList<>();
				if (acc.getOwnerUsers() != null)
					target.addAll(acc.getOwnerUsers());
				if (acc.getOwnerRoles() != null)
					target.addAll(acc.getOwnerRoles());
				if (acc.getOwnerGroups() != null)
					target.addAll(acc.getOwnerGroups());
				ServiceLocator.instance().getMailService().sendTextMailToActors(
						target.toArray(new String[target.size()]), 
						subject, 
						body);
			}
		}
	}
}
