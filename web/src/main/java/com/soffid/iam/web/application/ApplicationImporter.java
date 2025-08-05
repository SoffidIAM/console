package com.soffid.iam.web.application;

import java.util.Collection;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.Application;
import com.soffid.iam.api.CrudHandler;
import com.soffid.iam.api.User;
import com.soffid.iam.api.DataType;
import com.soffid.iam.service.crud.CrudUserHandler;
import com.soffid.iam.service.ejb.ApplicationService;
import com.soffid.iam.service.ejb.UserService;
import com.soffid.iam.web.component.CsvImporter;

import es.caib.seycon.ng.exception.InternalErrorException;

public class ApplicationImporter extends CsvImporter< Application > {
	private String type;
	private ApplicationService svc;
	private CrudHandler<Application> handler;

	public ApplicationImporter() throws NamingException, CreateException, InternalErrorException {
		this.type = type;
		svc = EJBLocator.getApplicationService();
		handler = EJBLocator.getCrudRegistryService().getHandler(Application.class);
	}
	
	@Override
	protected Collection<DataType> getMetadata() throws InternalErrorException, NamingException, CreateException {
		return EJBLocator.getAdditionalDataService().findDataTypesByObjectTypeAndName2(Application.class.getName(), null);
	}

	@Override
	protected Application newObject() throws InternalErrorException, NamingException, CreateException {
		Application o = new Application();
		return o;
	}

	@Override
	protected CrudHandler<Application> getCrudHandler() throws InternalErrorException, NamingException, CreateException {
		return handler;
	}

	@Override
	protected Application load(Application object) throws InternalErrorException {
		if (object.getId() != null)
		{
			for (Application app: svc.findApplicationByJsonQuery("id eq "+object.getId())) {
				return app;
			}
		}
		if (object.getName() != null)
			return svc.findApplicationByApplicationName(object.getName());
		return null;
	}

}
