package com.soffid.iam.web.issue;

import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.zul.Window;

import com.soffid.iam.api.Issue;

import es.caib.seycon.ng.exception.InternalErrorException;

public interface ManualActionHandler {
	void init(Window w, List<Issue> issue) throws InternalErrorException, NamingException, CreateException;
	void process(Window w, List<Issue> issue, Map<String,Object> parameters) throws InternalErrorException, NamingException, CreateException;
}
