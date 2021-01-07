package com.soffid.iam.service.crud;

import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.AsyncList;
import com.soffid.iam.api.CrudHandler;
import com.soffid.iam.api.User;
import com.soffid.iam.service.ejb.UserService;

import es.caib.seycon.ng.exception.InternalErrorException;

public class CrudUserHandler implements CrudHandler<User> {
	private UserService ejb;

	public UserService getService() throws NamingException, CreateException {
		if (ejb == null)
			ejb = EJBLocator.getUserService();
		return ejb;
	}
	
	@Override
	public User create(User object) throws InternalErrorException, NamingException, CreateException {
		return getService().create(object);
	}

	@Override
	public List<User> read(String text, String filter, Integer start, Integer end) throws InternalErrorException, NamingException, CreateException {
		return getService().findUserByTextAndFilter(text, filter, start, end).getResources();
	}

	@Override
	public AsyncList<User> readAsync(String text, String filter) throws InternalErrorException, NamingException, CreateException {
		return getService().findUserByTextAndFilterAsync(text, filter);
	}

	@Override
	public User update(User object) throws InternalErrorException, NamingException, CreateException {
		return getService().update(object);
	}

	@Override
	public void delete(User object) throws InternalErrorException, NamingException, CreateException {
		getService().delete(object);
	}

}
