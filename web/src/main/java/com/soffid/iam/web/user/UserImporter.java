package com.soffid.iam.web.user;

import java.util.Collection;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.CrudHandler;
import com.soffid.iam.api.User;
import com.soffid.iam.api.DataType;
import com.soffid.iam.service.crud.CrudUserHandler;
import com.soffid.iam.service.ejb.UserService;
import com.soffid.iam.web.component.CsvImporter;

import es.caib.seycon.ng.exception.InternalErrorException;

public class UserImporter extends CsvImporter< User > {
	private String type;
	private UserService svc;
	private CrudHandler<User> handler;

	public UserImporter() throws NamingException, CreateException, InternalErrorException {
		this.type = type;
		svc = EJBLocator.getUserService();
		handler = EJBLocator.getCrudRegistryService().getHandler(User.class);
	}
	
	@Override
	protected Collection<DataType> getMetadata() throws InternalErrorException, NamingException, CreateException {
		return EJBLocator.getAdditionalDataService().findDataTypesByObjectTypeAndName2(User.class.getName(), null);
	}

	@Override
	protected User newObject() throws InternalErrorException, NamingException, CreateException {
		User o = new User();
		o.setActive(true);
		return o;
	}

	@Override
	protected CrudHandler<User> getCrudHandler() throws InternalErrorException, NamingException, CreateException {
		return handler;
	}

	@Override
	protected User load(User object) throws InternalErrorException {
		if (object.getId() != null)
		{
			return svc.findUserByUserId(object.getId());
		}
		if (object.getUserName() != null)
			return svc.findUserByUserName(object.getUserName());
		return null;
	}

}
