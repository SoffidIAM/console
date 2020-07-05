package com.soffid.iam.service.crud;

import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.AsyncList;
import com.soffid.iam.api.CrudHandler;
import com.soffid.iam.api.Group;
import com.soffid.iam.service.ejb.GroupService;

import es.caib.seycon.ng.exception.InternalErrorException;

public class CrudGroupHandler implements CrudHandler<Group> {
	private GroupService ejb;

	public GroupService getService() throws NamingException, CreateException {
		if (ejb == null)
			ejb = EJBLocator.getGroupService();
		return ejb;
	}
	
	@Override
	public Group create(Group object) throws InternalErrorException, NamingException, CreateException {
		return getService().create(object);
	}

	@Override
	public List<Group> read(String text, String filter, Integer start, Integer end) throws InternalErrorException, NamingException, CreateException {
		return getService().findGroupByTextAndFilter(text, filter, start, end);
	}

	@Override
	public AsyncList<Group> readAsync(String text, String filter) throws InternalErrorException, NamingException, CreateException {
		return getService().findGroupByTextAndFilterAsync(text, filter);
	}

	@Override
	public Group update(Group object) throws InternalErrorException, NamingException, CreateException {
		return getService().update(object);
	}

	@Override
	public void delete(Group object) throws InternalErrorException, NamingException, CreateException {
		getService().delete(object);
	}

}
