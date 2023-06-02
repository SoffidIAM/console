package com.soffid.iam.web.issue;

import java.util.Map;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.util.resource.Labels;
import org.zkoss.zul.Label;
import org.zkoss.zul.Window;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.Account;
import com.soffid.iam.api.AccountStatus;
import com.soffid.iam.api.Issue;

import es.caib.seycon.ng.exception.AccountAlreadyExistsException;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.zkiblaf.Missatgebox;


public class LockAccount implements ManualActionHandler {

	@Override
	public void init(Window w, Issue issue) throws InternalErrorException, NamingException, CreateException {
		if (issue.getAccount() == null) {
			w.setVisible(false);
			Missatgebox.avis(Labels.getLabel("issues.noAccount"));
			return;
		}
		Account account = EJBLocator.getAccountService().findAccountById(issue.getAccount().getId());
		issue.setAccount(account);
		if (issue.getAccount().getStatus() == AccountStatus.LOCKED ||
				issue.getAccount().getStatus() == AccountStatus.DISABLED ||
				issue.getAccount().getStatus() == AccountStatus.ARCHIVED ||
				issue.getAccount().getStatus() == AccountStatus.REMOVED ) {
			w.setVisible(false);
			String msg = String.format(Labels.getLabel("issues.accountAlreadyLocked"),
					issue.getAccount().getLoginName());
			Missatgebox.avis(msg);
			return;
		}
		
		String msg = String.format(Labels.getLabel("issues.lockAccount"),
				issue.getAccount().getLoginName(), issue.getAccount().getSystem());
				
		w.getFellow("fields").appendChild(
				new Label(msg));
	}

	@Override
	public void process(Window w, Issue issue, Map<String, Object> parameters) throws InternalErrorException, NamingException, CreateException {
		issue.getAccount().setStatus(AccountStatus.LOCKED);
		try {
			EJBLocator.getAccountService().updateAccount(issue.getAccount());
		} catch (AccountAlreadyExistsException e) {
			// Cannot happen
		}
		w.setVisible(false);
		String msg = String.format(Labels.getLabel("issues.accountLocked"),
				issue.getAccount().getLoginName());
		EJBLocator.getIssueService().registerAction(issue, msg);
		Missatgebox.avis(msg);
	}
}
