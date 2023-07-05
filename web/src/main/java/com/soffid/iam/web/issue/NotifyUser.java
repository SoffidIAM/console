package com.soffid.iam.web.issue;

import java.util.Map;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.zul.Window;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.Issue;

import es.caib.seycon.ng.exception.InternalErrorException;


public class NotifyUser implements ManualActionHandler {

	@Override
	public void init(Window w, Issue issue) throws InternalErrorException {
	}

	@Override
	public void process(Window w, Issue issue, Map<String, Object> parameters) throws InternalErrorException, NamingException, CreateException {
		EJBLocator.getIssueService().notify(issue, 
				(String) parameters.get("to"), 
				(String) parameters.get("subject"), 
				(String) parameters.get("body"));
	}

}
