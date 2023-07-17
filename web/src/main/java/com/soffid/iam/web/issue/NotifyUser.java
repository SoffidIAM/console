package com.soffid.iam.web.issue;

import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.zul.Window;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.Issue;

import es.caib.seycon.ng.exception.InternalErrorException;


public class NotifyUser implements ManualActionHandler {

	@Override
	public void init(Window w, List<Issue> issues) throws InternalErrorException {
	}

	@Override
	public void process(Window w, List<Issue> issues, Map<String, Object> parameters) throws InternalErrorException, NamingException, CreateException {
		for (Issue issue: issues)
			EJBLocator.getIssueService().notify(issue, 
				(String) parameters.get("to"), 
				(String) parameters.get("subject"), 
				(String) parameters.get("body"));
	}

}
