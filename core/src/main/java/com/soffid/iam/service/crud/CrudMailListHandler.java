package com.soffid.iam.service.crud;

import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.AsyncList;
import com.soffid.iam.api.CrudHandler;
import com.soffid.iam.api.MailList;
import com.soffid.iam.service.ejb.ApplicationService;
import com.soffid.iam.service.ejb.MailListsService;

import es.caib.seycon.ng.exception.InternalErrorException;

public class CrudMailListHandler implements CrudHandler<MailList> {
	private MailListsService ejb;

	public MailListsService getService() throws NamingException, CreateException {
		if (ejb == null)
			ejb = EJBLocator.getMailListsService();
		return ejb;
	}
	
	@Override
	public MailList create(MailList object) throws InternalErrorException, NamingException, CreateException {
		return getService().create(object);
	}

	@Override
	public List<MailList> read(String text, String filter, Integer start, Integer end) throws InternalErrorException, NamingException, CreateException {
		return getService().findMailListByTextAndFilter(text, filter, start, end);
	}

	@Override
	public AsyncList<MailList> readAsync(String text, String filter) throws InternalErrorException, NamingException, CreateException {
		return getService().findMailListByTextAndFilterAsync(text, filter);
	}

	@Override
	public MailList update(MailList object) throws InternalErrorException, NamingException, CreateException {
		getService().update(object);
		return object;
	}

	@Override
	public void delete(MailList object) throws InternalErrorException, NamingException, CreateException {
		getService().delete(object);
	}

}
