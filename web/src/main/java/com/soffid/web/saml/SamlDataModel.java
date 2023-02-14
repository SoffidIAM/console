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
import com.soffid.iam.api.UserDomain;
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
				c.maintenanceMode = "true".equals(ConfigurationCache.getProperty("soffid.auth.maintenance"));
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
				
				c.enableLinotp = "true".equals(ConfigurationCache.getProperty("soffid.linotp.enabled"));
				c.linotpUser = ConfigurationCache.getProperty("soffid.linotp.user");
				c.linotpPassword = ConfigurationCache.getProperty("soffid.linotp.password");
				c.linotpServer = ConfigurationCache.getProperty("soffid.linotp.server");
				c.linotpUserDomain = ConfigurationCache.getProperty("soffid.linotp.userDomain");
				
				c.requireToken = ConfigurationCache.getProperty("soffid.otp.required");
				c.optionalToken = ConfigurationCache.getProperty("soffid.otp.optional");
				String tokenTimeoutString = ConfigurationCache.getProperty("soffid.otp.timeout");
				c.tokenTimeout = tokenTimeoutString == null || tokenTimeoutString.isEmpty() ? null: Long.valueOf( tokenTimeoutString );

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

		addFinder("userDomain", new Finder() {
			
			public Object newInstance() throws Exception {
				return null;
			}
			
			public Collection find() throws Exception {
				LinkedList<UserDomain> list = new LinkedList<UserDomain>();
				UserDomain empty = new UserDomain();
				empty.setDescription("- select -");
				list.add(empty);
				list.addAll(  EJBLocator.getUserDomainService().findAllUserDomain() );
				return list;
			}
		}, SimpleDataNode.class);
}
}