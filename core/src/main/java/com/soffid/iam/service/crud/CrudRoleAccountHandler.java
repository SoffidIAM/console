package com.soffid.iam.service.crud;

import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.RoleAccount;
import com.soffid.iam.api.AsyncList;
import com.soffid.iam.api.CrudHandler;
import com.soffid.iam.api.PagedResult;
import com.soffid.iam.service.ejb.ApplicationService;

import es.caib.seycon.ng.exception.AccountAlreadyExistsException;
import es.caib.seycon.ng.exception.InternalErrorException;

public class CrudRoleAccountHandler implements CrudHandler<RoleAccount> {
	private ApplicationService ejb;

	public ApplicationService getService() throws NamingException, CreateException {
		if (ejb == null)
			ejb = EJBLocator.getApplicationService();
		return ejb;
	}
	
	@Override
	public RoleAccount create(RoleAccount object) throws InternalErrorException, NamingException, CreateException, AccountAlreadyExistsException {
		return getService().create(object);
	}

	@Override
	public PagedResult<RoleAccount> read(String text, String filter, Integer start, Integer end) throws InternalErrorException, NamingException, CreateException {
		return getService().findRoleAccountByJsonQuery(filter, start, end);
	}

	@Override
	public AsyncList<RoleAccount> readAsync(String text, String filter) throws InternalErrorException, NamingException, CreateException {
		throw new InternalErrorException("not implemented");
	}

	@Override
	public RoleAccount update(RoleAccount object) throws InternalErrorException, NamingException, CreateException, AccountAlreadyExistsException {
		throw new InternalErrorException("Not allowed to modify role-account links");
	}

	@Override
	public void delete(RoleAccount object) throws InternalErrorException, NamingException, CreateException {
		getService().delete(object);
	}

}
