package com.soffid.iam.web.custom;

import java.util.Collection;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.CrudHandler;
import com.soffid.iam.api.CustomObject;
import com.soffid.iam.api.DataType;
import com.soffid.iam.service.crud.CrudCustomObjectHandler;
import com.soffid.iam.service.ejb.CustomObjectService;
import com.soffid.iam.web.component.CsvImporter;

import es.caib.seycon.ng.exception.InternalErrorException;

public class CustomObjectImporter extends CsvImporter< CustomObject > {
	private String type;
	private CustomObjectService svc;
	private CrudCustomObjectHandler handler;

	public CustomObjectImporter(String type) throws NamingException, CreateException, InternalErrorException {
		this.type = type;
		svc = EJBLocator.getCustomObjectService();
		handler = (CrudCustomObjectHandler) EJBLocator.getCrudRegistryService().getHandler(CustomObject.class);
		handler.setType(type);
	}
	
	@Override
	protected Collection<DataType> getMetadata() throws InternalErrorException, NamingException, CreateException {
		return EJBLocator.getAdditionalDataService().findDataTypesByObjectTypeAndName2(type, null);
	}

	@Override
	protected CustomObject newObject() throws InternalErrorException, NamingException, CreateException {
		CustomObject o = new CustomObject();
		o.setType(type);
		return o;
	}

	@Override
	protected CrudHandler<CustomObject> getCrudHandler() throws InternalErrorException, NamingException, CreateException {
		return handler;
	}

	@Override
	protected CustomObject load(CustomObject object) throws InternalErrorException {
		if (object.getId() != null)
		{
			for (CustomObject o: svc.findCustomObjectByJsonQuery(type, "id eq \""+object.getId()+"\""))
				return o;
			return null;
		}
		if (object.getName() != null)
			return svc.findCustomObjectByTypeAndName(type, object.getName());
		return null;
	}

}
