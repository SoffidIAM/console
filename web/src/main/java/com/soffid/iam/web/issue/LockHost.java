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
import com.soffid.iam.api.Host;
import com.soffid.iam.api.Issue;
import com.soffid.iam.api.IssueHost;
import com.soffid.iam.api.Host;

import es.caib.seycon.ng.exception.AccountAlreadyExistsException;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.zkiblaf.Missatgebox;


public class LockHost implements ManualActionHandler {
	List<Host> hosts = new LinkedList<>();
	StringBuffer hostNames = new StringBuffer();
	@Override
	public void init(Window w, Issue issue) throws InternalErrorException, NamingException, CreateException {
		for (IssueHost host: issue.getHosts()) {
			if (host.getHostId() != null) {
				Host u = EJBLocator.getNetworkService().findHostById(host.getHostId());
				if (u != null && ! Boolean.TRUE.equals(u.getLocked( ))) {
					if (hostNames.length() > 0)
						hostNames.append(", ");
					hostNames.append(u.getName());
					hosts.add(u);
				}
			}
		}
		if (hosts.isEmpty()) {
			w.setVisible(false);
			Missatgebox.avis(Labels.getLabel("issues.noHost"));
			return;
		}
		
		String msg = String.format(Labels.getLabel("issues.disableHost"),
				hostNames.toString());
				
		w.getFellow("fields").appendChild(
				new Label(msg));
	}

	@Override
	public void process(Window w, Issue issue, Map<String, Object> parameters) throws InternalErrorException, NamingException, CreateException {
		for (Host host: hosts) {
			host.setLocked(true);
			EJBLocator.getNetworkService().update(host);
			String msg = String.format(Labels.getLabel("issues.disabledHosts"),
					host.getName());
			EJBLocator.getIssueService().registerAction(issue, msg);
		}
		w.setVisible(false);
		String msg = String.format(Labels.getLabel("issues.disabledHosts"),
				hostNames.toString());
		Missatgebox.avis(msg);
	}
}
