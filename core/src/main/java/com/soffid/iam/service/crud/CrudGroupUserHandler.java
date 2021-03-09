package com.soffid.iam.service.crud;

import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.AsyncList;
import com.soffid.iam.api.CrudHandler;
import com.soffid.iam.api.PagedResult;
import com.soffid.iam.api.GroupUser;
import com.soffid.iam.service.ejb.GroupService;

import es.caib.seycon.ng.exception.InternalErrorException;

public class CrudGroupUserHandler implements CrudHandler<GroupUser> {
	private GroupService ejb;

	public GroupService getService() throws NamingException, CreateException {
		if (ejb == null)
			ejb = EJBLocator.getGroupService();
		return ejb;
	}
	
	@Override
	public GroupUser create(GroupUser object) throws InternalErrorException, NamingException, CreateException {
		return getService().create(object);
	}

	@Override
	public PagedResult<GroupUser> read(String text, String filter, Integer start, Integer end) throws InternalErrorException, NamingException, CreateException {
		return getService().findGroupUserByJsonQuery(filter, start, end);
	}

	@Override
	public AsyncList<GroupUser> readAsync(String text, String filter) throws InternalErrorException, NamingException, CreateException {
		throw new InternalErrorException("Not implemented");
	}

	@Override
	public GroupUser update(GroupUser object) throws InternalErrorException, NamingException, CreateException {
		return getService().update(object);
	}

	@Override
	public void delete(GroupUser object) throws InternalErrorException, NamingException, CreateException {
		getService().delete(object);
	}

}
