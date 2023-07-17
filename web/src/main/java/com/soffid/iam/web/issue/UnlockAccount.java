package com.soffid.iam.web.issue;

import java.util.List;
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


public class UnlockAccount implements ManualActionHandler {

	@Override
	public void init(Window w, List<Issue> issues) throws InternalErrorException, NamingException, CreateException {
		if (issues.size() == 1) {
			Issue issue = issues.iterator().next();
			if (issue.getAccount() == null) {
				w.setVisible(false);
				Missatgebox.avis(Labels.getLabel("issues.noAccount"));
				return;
			}
			Account account = EJBLocator.getAccountService().findAccount(issue.getAccount());
			if (account.getStatus() != AccountStatus.LOCKED ) {
				w.setVisible(false);
				String msg = String.format(Labels.getLabel("issues.accountNotLocked"),
						account.getLoginName());
				Missatgebox.avis(msg);
				return;
			}
		}

		StringBuffer sb = new StringBuffer();
		for (Issue issue: issues) {
			Account account = EJBLocator.getAccountService().findAccount(issue.getAccount());
			String msg = String.format(Labels.getLabel("issues.lockAccount"),
					account.getLoginName(), account.getSystem());
			sb.append(msg).append("\n");
		}
				
		Label lb = new Label(sb.toString());
		lb.setMultiline(true);
		w.getFellow("fields").appendChild(lb);
	}

	@Override
	public void process(Window w, List<Issue> issues, Map<String, Object> parameters) throws InternalErrorException, NamingException, CreateException {
		for (Issue issue: issues) {
			Account account = EJBLocator.getAccountService().findAccount(issue.getAccount());
			if (account.getStatus() == AccountStatus.LOCKED) {
				account.setStatus(AccountStatus.ACTIVE);
				try {
					EJBLocator.getAccountService().updateAccount(account);
				} catch (AccountAlreadyExistsException e) {
					// Cannot happen
				}
				w.setVisible(false);
				String msg = String.format(Labels.getLabel("issues.accountUnlocked"),
						account.getLoginName());
				EJBLocator.getIssueService().registerAction(issue, msg);
				if (issues.size() == 1)
					Missatgebox.avis(msg);
			}
		}
	}
}
