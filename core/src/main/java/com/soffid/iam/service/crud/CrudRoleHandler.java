package com.soffid.iam.service.crud;

import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.AsyncList;
import com.soffid.iam.api.CrudHandler;
import com.soffid.iam.api.Role;
import com.soffid.iam.service.ejb.ApplicationService;

import es.caib.seycon.ng.exception.InternalErrorException;

public class CrudRoleHandler implements CrudHandler<Role> {
	private ApplicationService ejb;

	public ApplicationService getService() throws NamingException, CreateException {
		if (ejb == null)
			ejb = EJBLocator.getApplicationService();
		return ejb;
	}
	
	@Override
	public Role create(Role object) throws InternalErrorException, NamingException, CreateException {
		return getService().create(object);
	}

	@Override
	public List<Role> read(String text, String filter, Integer start, Integer end) throws InternalErrorException, NamingException, CreateException {
		return getService().findRoleByTextAndFilter(text, filter, start, end).getResources();
	}

	@Override
	public AsyncList<Role> readAsync(String text, String filter) throws InternalErrorException, NamingException, CreateException {
		return getService().findRoleByTextAndFilterAsync(text, filter);
	}

	@Override
	public Role update(Role object) throws InternalErrorException, NamingException, CreateException {
		getService().update(object);
		return object;
	}

	@Override
	public void delete(Role object) throws InternalErrorException, NamingException, CreateException {
		getService().delete(object);
	}

}
