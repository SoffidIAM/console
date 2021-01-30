package com.soffid.iam.service.crud;

import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.AsyncList;
import com.soffid.iam.api.CrudHandler;
import com.soffid.iam.api.MailDomain;
import com.soffid.iam.api.PagedResult;
import com.soffid.iam.service.ejb.MailListsService;

import es.caib.seycon.ng.exception.InternalErrorException;

public class CrudMailDomainHandler implements CrudHandler<MailDomain> {
	private MailListsService ejb;

	public MailListsService getService() throws NamingException, CreateException {
		if (ejb == null)
			ejb = EJBLocator.getMailListsService();
		return ejb;
	}
	
	@Override
	public MailDomain create(MailDomain object) throws InternalErrorException, NamingException, CreateException {
		return getService().create(object);
	}

	@Override
	public PagedResult<MailDomain> read(String text, String filter, Integer start, Integer end) throws InternalErrorException, NamingException, CreateException {
		return getService().findMailDomainsByTextAndFilter(text, filter, start, end);
	}

	@Override
	public AsyncList<MailDomain> readAsync(String text, String filter) throws InternalErrorException, NamingException, CreateException {
		return getService().findMailDomainsByTextAndFilterAsync(text, filter);
	}

	@Override
	public MailDomain update(MailDomain object) throws InternalErrorException, NamingException, CreateException {
		return getService().update(object);
	}

	@Override
	public void delete(MailDomain object) throws InternalErrorException, NamingException, CreateException {
		getService().delete(object);
	}

}
