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


public class AddComment implements ManualActionHandler {
	List<Host> hosts = new LinkedList<>();
	StringBuffer hostNames = new StringBuffer();
	@Override
	public void init(Window w, Issue issue) throws InternalErrorException, NamingException, CreateException {
	}

	@Override
	public void process(Window w, Issue issue, Map<String, Object> parameters) throws InternalErrorException, NamingException, CreateException {
		EJBLocator.getIssueService().registerAction(issue,
				Labels.getLabel("task.addcomentari") + ": " + parameters.get("subject"));
	}
}
