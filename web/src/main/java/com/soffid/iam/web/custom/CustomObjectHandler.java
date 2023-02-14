package com.soffid.iam.web.custom;

import java.io.IOException;
import java.util.Arrays;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.UiException;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.CustomObjectType;
import com.soffid.iam.common.security.SoffidPrincipal;
import com.soffid.iam.utils.Security;
import com.soffid.iam.web.component.FrameHandler;
import com.soffid.iam.web.component.ObjectAttributesDiv;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.datasource.XPathUtils;


public class CustomObjectHandler extends FrameHandler {
	String type;
	private boolean readonly;
	private boolean read;
	private boolean write;
	
	public CustomObjectHandler() throws InternalErrorException {
		super();
	}

	
	public String getType() {
		return type;
	}

	
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public void afterCompose() {
		getModel().getJXPathContext().getVariables().declareVariable("objectType", type);
		setUrl("/custom/custom.zul?type="+type);
		read = false;
		write = false;
		try {
			CustomObjectType dm = EJBLocator.getAdditionalDataService().findCustomObjectTypeByName(type);
			if (dm != null) {
				if (Boolean.FALSE.equals(dm.getPublicAccess())) {
					SoffidPrincipal principal = Security.getSoffidPrincipal();
					if (dm.getManagerRoles() != null) {
						for (String role: dm.getManagerRoles())
							if (Arrays.binarySearch(principal.getSoffidRoles(), role) >= 0)
								write = true;;
					}
					if (dm.getUserRoles() != null) {
						for (String role: dm.getUserRoles())
							if (Arrays.binarySearch(principal.getSoffidRoles(), role) >= 0)
								read = true;
					}
					
				} else {
					read = write = true;
				}
			}
		} catch (Exception e) {
			throw new UiException(e);
		}
		if (!read && !write)
			throw new SecurityException("Not allowed to query custom objects of type "+type);
		ObjectAttributesDiv d = (ObjectAttributesDiv) getFellow("attributes");
		d.setReadonly(! write);
		if (!write) {
			Component b = getFellowIfAny("addButton");
			if (b != null) b.detach();
			b = getFellowIfAny("removeButton");
			if (b != null) b.detach();
			b = getFellowIfAny("addMenu");
			if (b != null) b.detach();
			b = getFellowIfAny("removeMenu");
			if (b != null) b.detach();
			b = getFellowIfAny("importMenu");
			if (b != null) b.detach();
		}
	}


	@Override
	public void addNew() throws Exception {
		super.addNew();
		XPathUtils.setValue(getListbox(), "type", type);
	}
	
	public void importCsv() throws IOException, CommitException, InternalErrorException, NamingException, CreateException {
		new CustomObjectImporter(type).importCsv(this);
	}

}
