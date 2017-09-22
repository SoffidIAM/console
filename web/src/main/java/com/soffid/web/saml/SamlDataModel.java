package com.soffid.web.saml;


import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.LazyDynaMap;
import org.zkoss.zk.ui.Executions;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.utils.ConfigurationCache;

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
				c.classicMethod =  ! "false".equals( ConfigurationCache.getProperty("soffid.auth.classic"));
				c.samlMethod = "true".equals(ConfigurationCache.getProperty("soffid.auth.saml"));
				c.trustedAuthentication = "true".equals(ConfigurationCache.getProperty("soffid.auth.trustedLogin"));
				c.alwaysTrust = "true".equals(ConfigurationCache.getProperty("soffid.saml.metadata.alwaysTrust"));
				c.metadata = ConfigurationCache.getProperty("soffid.saml.metadata.url");
				c.hostName = ConfigurationCache.getProperty("soffid.externalURL");
				if (c.hostName == null)
				{
					HttpServletRequest req = ((HttpServletRequest)Executions.getCurrent().getNativeRequest());
					c.hostName = req.getScheme()+"://"+req.getHeader("Host");
				}
				c.idp =ConfigurationCache.getProperty("soffid.saml.idp");
				String s = ConfigurationCache.getProperty("soffid.saml.metadata.cache");
				c.cache = 600;
				try {
					c.cache = Integer.parseInt(s);
				} catch (Exception e) {
				}
				return Collections.singleton(c);
			}
		}, SamlDataNode.class);
		
		addFinder("idps", new Finder() {
			
			public Object newInstance() throws Exception {
				return null;
			}
			
			public Collection find() throws Exception {
				List<SAMLIdP> l = new LinkedList<>();
				l.add(new SAMLIdP("- Select one -"));
				String md = (String) getDataSource().getVariables().getVariable("metadata");
				if (md != null)
				{
					for (String s: EJBLocator.getSamlService().findIdentityProviders(md))
						l.add(new SAMLIdP(s));
				}
				else
				{
					try {
						for (String s: EJBLocator.getSamlService().findIdentityProviders())
							l.add(new SAMLIdP(s));
					} 
					catch (Exception e)
					{
					}
				}						
				return l;
			}
		}, SimpleDataNode.class);
	}
}