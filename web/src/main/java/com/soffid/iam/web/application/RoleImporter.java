package com.soffid.iam.web.application;

import java.util.Collection;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.CrudHandler;
import com.soffid.iam.api.User;
import com.soffid.iam.api.DataType;
import com.soffid.iam.api.Role;
import com.soffid.iam.service.crud.CrudUserHandler;
import com.soffid.iam.service.ejb.ApplicationService;
import com.soffid.iam.service.ejb.UserService;
import com.soffid.iam.web.component.CsvImporter;

import es.caib.seycon.ng.exception.InternalErrorException;

public class RoleImporter extends CsvImporter< Role > {
	private String type;
	private ApplicationService svc;
	private CrudHandler<Role> handler;

	public RoleImporter() throws NamingException, CreateException, InternalErrorException {
		this.type = type;
		svc = EJBLocator.getApplicationService();
		handler = EJBLocator.getCrudRegistryService().getHandler(Role.class);
	}
	
	@Override
	protected Collection<DataType> getMetadata() throws InternalErrorException, NamingException, CreateException {
		return EJBLocator.getAdditionalDataService().findDataTypesByObjectTypeAndName2(Role.class.getName(), null);
	}

	@Override
	protected Role newObject() throws InternalErrorException, NamingException, CreateException {
		Role o = new Role();
		return o;
	}

	@Override
	protected CrudHandler<Role> getCrudHandler() throws InternalErrorException, NamingException, CreateException {
		return handler;
	}

	@Override
	protected Role load(Role object) throws InternalErrorException {
		if (object.getId() != null)
		{
			return svc.findRoleById(object.getId());
		}
		if (object.getName() != null && object.getSystem() != null)
			return svc.findRoleByNameAndSystem(object.getName(), object.getSystem());
		return null;
	}

}
