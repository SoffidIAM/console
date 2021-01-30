package com.soffid.iam.service.crud;

import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.AsyncList;
import com.soffid.iam.api.CrudHandler;
import com.soffid.iam.api.PagedResult;
import com.soffid.iam.api.UserType;
import com.soffid.iam.service.ejb.UserDomainService;

import es.caib.seycon.ng.exception.InternalErrorException;

public class CrudUserTypeHandler implements CrudHandler<UserType> {
	private UserDomainService ejb;

	public UserDomainService getService() throws NamingException, CreateException {
		if (ejb == null)
			ejb = EJBLocator.getUserDomainService();
		return ejb;
	}
	
	@Override
	public UserType create(UserType object) throws InternalErrorException, NamingException, CreateException {
		return getService().create(object);
	}

	@Override
	public PagedResult<UserType> read(String text, String filter, Integer start, Integer end) throws InternalErrorException, NamingException, CreateException {
		return getService().findUserTypeByTextAndFilter(text, filter, start, end);
	}

	@Override
	public AsyncList<UserType> readAsync(String text, String filter) throws InternalErrorException, NamingException, CreateException {
		return getService().findUserTypeByTextAndFilterAsync(text, filter);
	}

	@Override
	public UserType update(UserType object) throws InternalErrorException, NamingException, CreateException {
		return getService().update(object);
	}

	@Override
	public void delete(UserType object) throws InternalErrorException, NamingException, CreateException {
		getService().delete(object);
	}

}
