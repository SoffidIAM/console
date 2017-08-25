package com.soffid.web.saml;


import java.util.HashMap;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import com.soffid.iam.EJBLocator;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.datamodel.AbstractDataModel;
import es.caib.zkib.datamodel.DataContext;
import es.caib.zkib.datamodel.DataModelNode;
import es.caib.zkib.datamodel.SimpleDataNode;
import es.caib.zkib.datasource.CommitException;

public class SamlDataModel extends AbstractDataModel {
	private HashMap<String, Object> map;
	private DataContext ctx;
	private SimpleDataNode dataNode;
	@Override
	public void refresh() {
		try {
			dataNode.put("classicMethod", ! "false".equals(System.getProperty("soffid.auth.classic")));
			dataNode.put("samlMethod", "true".equals(System.getProperty("soffid.auth.saml")));
			dataNode.put("trustedAuthentication", "true".equals(System.getProperty("soffid.auth.trustedLogin")));
			dataNode.put("metadata", System.getProperty("soffid.saml.metadata.url"));
			dataNode.put("idps", EJBLocator.getSamlService().findIdentityProviders());
			dataNode.put("idp", System.getProperty("soffid.saml.metadata.url"));
			super.refresh();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public synchronized void commit() throws CommitException {
		// TODO Auto-generated method stub
		super.commit();
	}

	public SamlDataModel ()
	{
		ctx = new DataContext();
		map = new HashMap<String, Object>();
		ctx.setCustomData(map);
		dataNode = new SimpleDataNode(ctx);
		refresh();
	}

	public String getRootPath() {
		return "/";
	}

	@Override
	public DataModelNode getDataNode() {
		return dataNode;
	}

	@Override
	public Object getData() {
		return dataNode;
	}

	@Override
	public boolean isCommitPending() {
		// TODO Auto-generated method stub
		return super.isCommitPending();
	}

}
