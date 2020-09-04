package com.soffid.iam.service.crud;

import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.AsyncList;
import com.soffid.iam.api.CrudHandler;
import com.soffid.iam.api.VaultFolder;
import com.soffid.iam.api.VaultFolder;
import com.soffid.iam.service.ejb.OrganizationalUnitTypeService;
import com.soffid.iam.service.ejb.VaultService;

import es.caib.seycon.ng.exception.InternalErrorException;

public class CrudVaultFolderHandler implements CrudHandler<VaultFolder> {
	private VaultService ejb;

	public VaultService getService() throws NamingException, CreateException {
		if (ejb == null)
			ejb = EJBLocator.getVaultService();
		return ejb;
	}
	
	@Override
	public VaultFolder create(VaultFolder object) throws InternalErrorException, NamingException, CreateException {
		return getService().create(object);
	}

	@Override
	public List<VaultFolder> read(String text, String filter, Integer start, Integer end) throws InternalErrorException, NamingException, CreateException {
		return getService().findFolderByTextAndJsonQuery(text, filter, start, end);
	}

	@Override
	public AsyncList<VaultFolder> readAsync(String text, String filter) throws InternalErrorException, NamingException, CreateException {
		return getService().findFolderByTextAndJsonQueryAsync(text, filter);
	}

	@Override
	public VaultFolder update(VaultFolder object) throws InternalErrorException, NamingException, CreateException {
		return getService().update(object);
	}

	@Override
	public void delete(VaultFolder object) throws InternalErrorException, NamingException, CreateException {
		getService().remove(object);
	}

}
