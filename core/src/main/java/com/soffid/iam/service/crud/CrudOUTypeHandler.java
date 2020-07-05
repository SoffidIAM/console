package com.soffid.iam.service.crud;

import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.AsyncList;
import com.soffid.iam.api.CrudHandler;
import com.soffid.iam.api.OUType;
import com.soffid.iam.service.ejb.OrganizationalUnitTypeService;

import es.caib.seycon.ng.exception.InternalErrorException;

public class CrudOUTypeHandler implements CrudHandler<OUType> {
	private OrganizationalUnitTypeService ejb;

	public OrganizationalUnitTypeService getService() throws NamingException, CreateException {
		if (ejb == null)
			ejb = EJBLocator.getOrganizationalUnitTypeService();
		return ejb;
	}
	
	@Override
	public OUType create(OUType object) throws InternalErrorException, NamingException, CreateException {
		return getService().create(object);
	}

	@Override
	public List<OUType> read(String text, String filter, Integer start, Integer end) throws InternalErrorException, NamingException, CreateException {
		return getService().findOUTypeByTextAndFilter(text, filter, start, end);
	}

	@Override
	public AsyncList<OUType> readAsync(String text, String filter) throws InternalErrorException, NamingException, CreateException {
		return getService().findOUTypeByTextAndFilterAsync(text, filter);
	}

	@Override
	public OUType update(OUType object) throws InternalErrorException, NamingException, CreateException {
		return getService().update(object);
	}

	@Override
	public void delete(OUType object) throws InternalErrorException, NamingException, CreateException {
		getService().delete(object);
	}

}
