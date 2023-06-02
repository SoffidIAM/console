package com.soffid.iam.web.issue;

import java.util.LinkedList;
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
import com.soffid.iam.api.IssueUser;
import com.soffid.iam.api.User;

import es.caib.seycon.ng.exception.AccountAlreadyExistsException;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.zkiblaf.Missatgebox;


public class DisableUser implements ManualActionHandler {
	List<User> users = new LinkedList<>();
	StringBuffer userNames = new StringBuffer();
	@Override
	public void init(Window w, Issue issue) throws InternalErrorException, NamingException, CreateException {
		for (IssueUser user: issue.getUsers()) {
			if (user.getUserId() != null) {
				User u = EJBLocator.getUserService().findUserByUserId(user.getUserId());
				if (u != null && u.getActive( ).booleanValue()) {
					if (userNames.length() > 0)
						userNames.append(", ");
					userNames.append(u.getUserName());
					users.add(u);
				}
			}
		}
		if (users.isEmpty()) {
			w.setVisible(false);
			Missatgebox.avis(Labels.getLabel("issues.noUser"));
			return;
		}
		
		String msg = String.format(Labels.getLabel("issues.disableUser"),
				userNames.toString());
				
		w.getFellow("fields").appendChild(
				new Label(msg));
	}

	@Override
	public void process(Window w, Issue issue, Map<String, Object> parameters) throws InternalErrorException, NamingException, CreateException {
		for (User user: users) {
			user.setActive(false);
			EJBLocator.getUserService().update(user);
			String msg = String.format(Labels.getLabel("issues.disabledUsers"),
					user.getUserName());
			EJBLocator.getIssueService().registerAction(issue, msg);
		}
		w.setVisible(false);
		String msg = String.format(Labels.getLabel("issues.disabledUsers"),
				userNames.toString());
		Missatgebox.avis(msg);
	}
}
