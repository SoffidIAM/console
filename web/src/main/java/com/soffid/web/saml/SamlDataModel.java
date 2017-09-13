package com.soffid.web.saml;


import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.apache.commons.beanutils.LazyDynaMap;

import com.soffid.iam.EJBLocator;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.datamodel.AbstractDataModel;
import es.caib.zkib.datamodel.DataContext;
import es.caib.zkib.datamodel.DataModelNode;
import es.caib.zkib.datamodel.Finder;
import es.caib.zkib.datamodel.SimpleDataNode;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.zkiblaf.Missatgebox;

public class SamlDataModel extends SimpleDataNode {
	public SamlDataModel(DataContext ctx) {
		super(ctx);
		addFinder("conf", new Finder() {
			
			public Object newInstance() throws Exception {
				return null;
			}
			
			public Collection find() throws Exception {
				SAMLConfig c = new SAMLConfig();
				c.classicMethod =  ! "false".equals(System.getProperty("soffid.auth.classic"));
				c.samlMethod = "true".equals(System.getProperty("soffid.auth.saml"));
				c.trustedAuthentication = "true".equals(System.getProperty("soffid.auth.trustedLogin"));
				c.metadata = System.getProperty("soffid.saml.metadata.url");
				c.idp =System.getProperty("soffid.saml.metadata.idp");
				return Collections.singleton(c);
			}
		}, SimpleDataNode.class);
		
		addFinder("idps", new Finder() {
			
			public Object newInstance() throws Exception {
				return null;
			}
			
			public Collection find() throws Exception {
				try {
					return EJBLocator.getSamlService().findIdentityProviders();
				} 
				catch (Exception e)
				{
					return new LinkedList<String>();
				}
			}
		}, SimpleDataNode.class);
	}
}