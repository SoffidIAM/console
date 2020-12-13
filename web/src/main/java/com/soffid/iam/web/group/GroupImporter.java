package com.soffid.iam.web.group;

import java.util.Collection;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.CrudHandler;
import com.soffid.iam.api.Group;
import com.soffid.iam.api.DataType;
import com.soffid.iam.service.crud.CrudGroupHandler;
import com.soffid.iam.service.ejb.GroupService;
import com.soffid.iam.web.component.CsvImporter;

import es.caib.seycon.ng.exception.InternalErrorException;

public class GroupImporter extends CsvImporter< Group > {
	private String type;
	private GroupService svc;
	private CrudHandler<Group> handler;

	public GroupImporter() throws NamingException, CreateException, InternalErrorException {
		this.type = type;
		svc = EJBLocator.getGroupService();
		handler = EJBLocator.getCrudRegistryService().getHandler(Group.class);
	}
	
	@Override
	protected Collection<DataType> getMetadata() throws InternalErrorException, NamingException, CreateException {
		return EJBLocator.getAdditionalDataService().findDataTypesByObjectTypeAndName2(Group.class.getName(), null);
	}

	@Override
	protected Group newObject() throws InternalErrorException, NamingException, CreateException {
		Group o = new Group();
		return o;
	}

	@Override
	protected CrudHandler<Group> getCrudHandler() throws InternalErrorException, NamingException, CreateException {
		return handler;
	}

	@Override
	protected Group load(Group object) throws InternalErrorException {
		if (object.getId() != null)
		{
			return svc.findGroupById(object.getId());
		}
		if (object.getName() != null)
			return svc.findGroupByGroupName(object.getName());
		return null;
	}

}
