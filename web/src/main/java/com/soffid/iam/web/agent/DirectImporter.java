package com.soffid.iam.web.agent;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.AttributeMapping;
import com.soffid.iam.api.DataType;
import com.soffid.iam.api.ObjectMapping;
import com.soffid.iam.api.ObjectMappingProperty;
import com.soffid.iam.api.ObjectMappingTrigger;
import com.soffid.iam.api.ReconcileTrigger;
import com.soffid.iam.api.System;
import com.soffid.iam.service.ejb.AdditionalDataService;
import com.soffid.iam.service.ejb.DispatcherService;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.datamodel.DataModelNode;
import es.caib.zkib.datamodel.DataNodeCollection;
import es.caib.zkib.datasource.DataSource;
import es.caib.zkib.datasource.XPathUtils;

public class DirectImporter extends Importer {
	private System system;
	private DispatcherService svc;
	private AdditionalDataService additionalDataService;

	public DirectImporter(System system) throws NamingException, CreateException {
		this.system = system;
		svc = EJBLocator.getDispatcherService();
		additionalDataService = EJBLocator.getAdditionalDataService();
	}

	@Override
	protected void createProperty(DataSource ds, String path, ObjectMappingProperty omp, ObjectMapping om) throws Exception {
		omp.setObjectId(om.getId());
		svc.create(omp);
	}

	@Override
	protected void createObjectMappingTrigger(DataSource ds, String path, ObjectMappingTrigger omp, ObjectMapping om) throws Exception {
		omp.setObjectId(om.getId());
		svc.create(omp);
	}

	@Override
	protected void createAttribute(DataSource ds, String path, AttributeMapping am, ObjectMapping om) throws Exception {
		am.setObjectId(om.getId());
		svc.create(am);
	}

	@Override
	protected void createMetadata(DataSource ds, DataType dt) throws Exception {
		dt.setSystemName(system.getName());
		additionalDataService.create(dt);
	}

	@Override
	protected void createReconcileTrigger(DataSource ds, ReconcileTrigger om) throws Exception {
		om.setSystem(system.getName());
		svc.create(om);
	}

	@Override
	protected String createObject(DataSource ds, ObjectMapping om) throws Exception {
		om.setDispatcherId(system.getId());
		ObjectMapping om2 = svc.create(om);
		om.setId(om2.getId());
		return "";
	}

	@Override
	protected void clearMappings(DataSource ds) throws InternalErrorException {
		for (ObjectMapping om: svc.findObjectMappingsByDispatcher(system.getId())) {
			svc.delete(om);
		}
		
		for (ReconcileTrigger rt: svc.findReconcileTriggersByDispatcher(system.getId()))
			svc.delete(rt);
		
		for (DataType md: additionalDataService.findSystemDataTypes(system.getName()))
			additionalDataService.delete(md);
	}

}
