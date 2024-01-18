package com.soffid.iam.web.issue;

import java.util.Date;
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
import com.soffid.iam.api.PasswordDomainStatus;
import com.soffid.iam.api.System;
import com.soffid.iam.api.UserAccount;

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
				if (account instanceof UserAccount) {
					System s = EJBLocator.getDispatcherService().findDispatcherByName(account.getSystem());
					for (PasswordDomainStatus pd: 
						EJBLocator.getUserService().findPasswordDomainStatus(((UserAccount) account).getUser())) {
						if (pd.getDomainName().equals(s.getPasswordsDomain())) {
							if (pd.getLockedUntil() != null &&
									pd.getLockedUntil().after(new Date()))
								return;
						}
					}
				}
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
			} else if (account instanceof UserAccount) {
				System s = EJBLocator.getDispatcherService().findDispatcherByName(account.getSystem());
				EJBLocator.getUserService().unlockPasswordDomain(((UserAccount) account).getUser(), s.getPasswordsDomain());
				String msg = String.format(Labels.getLabel("issues.accountUnlocked"),
						account.getLoginName());
				EJBLocator.getIssueService().registerAction(issue, msg);
				if (issues.size() == 1)
					Missatgebox.avis(msg);
			}
		}
	}
}
