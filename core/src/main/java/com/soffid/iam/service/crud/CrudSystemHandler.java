package com.soffid.iam.service.crud;

import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.AsyncList;
import com.soffid.iam.api.CrudHandler;
import com.soffid.iam.api.System;
import com.soffid.iam.service.ejb.DispatcherService;
import com.soffid.iam.service.ejb.MailListsService;

import es.caib.seycon.ng.exception.InternalErrorException;

public class CrudSystemHandler implements CrudHandler<com.soffid.iam.api.System> {
	private DispatcherService ejb;

	public DispatcherService getService() throws NamingException, CreateException {
		if (ejb == null)
			ejb = EJBLocator.getDispatcherService();
		return ejb;
	}
	
	@Override
	public System create(System object) throws InternalErrorException, NamingException, CreateException {
		return getService().create(object);
	}

	@Override
	public List<System> read(String text, String filter, Integer start, Integer end) throws InternalErrorException, NamingException, CreateException {
		return getService().findSystemByTextAndFilter(text, filter, start, end);
	}

	@Override
	public AsyncList<System> readAsync(String text, String filter) throws InternalErrorException, NamingException, CreateException {
		return getService().findSystemByTextAndFilterAsync(text, filter);
	}

	@Override
	public System update(System object) throws InternalErrorException, NamingException, CreateException {
		return getService().update(object);
	}

	@Override
	public void delete(System object) throws InternalErrorException, NamingException, CreateException {
		getService().delete(object);
	}
}
