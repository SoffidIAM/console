package com.soffid.iam.service.crud;

import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.AsyncList;
import com.soffid.iam.api.CrudHandler;
import com.soffid.iam.api.DomainValue;
import com.soffid.iam.service.ejb.DispatcherService;
import com.soffid.iam.service.ejb.DomainService;
import com.soffid.iam.service.ejb.MailListsService;

import es.caib.seycon.ng.exception.InternalErrorException;

public class CrudDomainValueHandler implements CrudHandler<com.soffid.iam.api.DomainValue> {
	private DomainService ejb;

	public DomainService getService() throws NamingException, CreateException {
		if (ejb == null)
			ejb = EJBLocator.getDomainService();
		return ejb;
	}
	
	@Override
	public DomainValue create(DomainValue object) throws InternalErrorException, NamingException, CreateException {
		return getService().create(object);
	}

	@Override
	public List<DomainValue> read(String text, String filter, Integer start, Integer end) throws InternalErrorException, NamingException, CreateException {
		return getService().findDomainValueByTextAndFilter(text, filter, start, end);
	}

	@Override
	public AsyncList<DomainValue> readAsync(String text, String filter) throws InternalErrorException, NamingException, CreateException {
		return getService().findDomainValueByTextAndFilterAsync(text, filter);
	}

	@Override
	public DomainValue update(DomainValue object) throws InternalErrorException, NamingException, CreateException {
		return getService().update(object);
	}

	@Override
	public void delete(DomainValue object) throws InternalErrorException, NamingException, CreateException {
		getService().delete(object);
	}
}
