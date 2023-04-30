package com.soffid.iam.service.crud;

import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.AsyncList;
import com.soffid.iam.api.CrudHandler;
import com.soffid.iam.api.CustomObject;
import com.soffid.iam.api.PagedResult;
import com.soffid.iam.service.ejb.CustomObjectService;

import es.caib.seycon.ng.exception.InternalErrorException;

public class CrudCustomObjectHandler implements CrudHandler<CustomObject> {
	private CustomObjectService ejb;
	String objectType;

	public CrudCustomObjectHandler(String objectType) {
		this.objectType = objectType;
	}
	
	public CustomObjectService getService() throws NamingException, CreateException {
		if (ejb == null)
			ejb = EJBLocator.getCustomObjectService();
		return ejb;
	}
	
	@Override
	public CustomObject create(CustomObject object) throws InternalErrorException, NamingException, CreateException {
		if (objectType != null)
			object.setType(objectType);
		return getService().createCustomObject(object);
	}

	@Override
	public PagedResult<CustomObject> read(String text, String filter, Integer start, Integer end) throws InternalErrorException, NamingException, CreateException {
		if (objectType != null) {
			return getService().findCustomObjectByTextAndJsonQuery(objectType, text, filter, start, end);
		}
		else
			return getService().findCustomObjectByTextAndJsonQuery(text, filter, start, end);
	}

	@Override
	public AsyncList<CustomObject> readAsync(String text, String filter) throws InternalErrorException, NamingException, CreateException {
		if (objectType != null)
			return getService().findCustomObjectByTextAndJsonQueryAsync(objectType, text, filter);
		else
			return getService().findCustomObjectByTextAndJsonQueryAsync(text, filter);
	}

	@Override
	public CustomObject update(CustomObject object) throws InternalErrorException, NamingException, CreateException {
		getService().updateCustomObject(object);
		return object;
	}

	@Override
	public void delete(CustomObject object) throws InternalErrorException, NamingException, CreateException {
		getService().deleteCustomObject(object);
	}

	public String getType() {
		return objectType;
	}

}
