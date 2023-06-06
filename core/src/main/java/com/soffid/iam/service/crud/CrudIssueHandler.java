package com.soffid.iam.service.crud;

import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.AsyncList;
import com.soffid.iam.api.CrudHandler;
import com.soffid.iam.api.PagedResult;
import com.soffid.iam.api.Issue;
import com.soffid.iam.service.ejb.IssueService;

import es.caib.seycon.ng.exception.InternalErrorException;

public class CrudIssueHandler implements CrudHandler<Issue> {
	private IssueService ejb;

	public IssueService getService() throws NamingException, CreateException {
		if (ejb == null)
			ejb = EJBLocator.getIssueService();
		return ejb;
	}
	
	@Override
	public Issue create(Issue object) throws InternalErrorException, NamingException, CreateException {
		throw new InternalErrorException("Method not supported");
	}

	@Override
	public PagedResult<Issue> read(String text, String filter, Integer start, Integer end) throws InternalErrorException, NamingException, CreateException {
		return getService().findIssuesByJsonQuery(filter, start, end);
	}

	@Override
	public AsyncList<Issue> readAsync(String text, String filter) throws InternalErrorException, NamingException, CreateException {
		return getService().findIssuesByJsonQueryAsync(filter);
	}

	@Override
	public Issue update(Issue object) throws InternalErrorException, NamingException, CreateException {
		throw new InternalErrorException("Method not supported");
	}

	@Override
	public void delete(Issue object) throws InternalErrorException, NamingException, CreateException {
		throw new InternalErrorException("Method not supported");
	}

}
