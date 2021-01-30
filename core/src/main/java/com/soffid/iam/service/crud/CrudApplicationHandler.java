package com.soffid.iam.service.crud;

import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.AsyncList;
import com.soffid.iam.api.CrudHandler;
import com.soffid.iam.api.PagedResult;
import com.soffid.iam.api.Application;
import com.soffid.iam.service.ejb.ApplicationService;

import es.caib.seycon.ng.exception.InternalErrorException;

public class CrudApplicationHandler implements CrudHandler<Application> {
	private ApplicationService ejb;

	public ApplicationService getService() throws NamingException, CreateException {
		if (ejb == null)
			ejb = EJBLocator.getApplicationService();
		return ejb;
	}
	
	@Override
	public Application create(Application object) throws InternalErrorException, NamingException, CreateException {
		return getService().create(object);
	}

	@Override
	public PagedResult<Application> read(String text, String filter, Integer start, Integer end) throws InternalErrorException, NamingException, CreateException {
		return getService().findApplicationByTextAndFilter(text, filter, start, end);
	}

	@Override
	public AsyncList<Application> readAsync(String text, String filter) throws InternalErrorException, NamingException, CreateException {
		return getService().findApplicationByTextAndFilterAsync(text, filter);
	}

	@Override
	public Application update(Application object) throws InternalErrorException, NamingException, CreateException {
		getService().update(object);
		return object;
	}

	@Override
	public void delete(Application object) throws InternalErrorException, NamingException, CreateException {
		getService().delete(object);
	}

}
