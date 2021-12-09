package com.soffid.web.agent;

import java.util.Iterator;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.jbpm.graph.exe.Execution;
import org.zkoss.zk.au.out.AuScript;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Path;
import org.zkoss.zul.Grid;

import bsh.Primitive;

import com.soffid.iam.api.DataType;
import com.soffid.iam.api.MetadataScope;

import es.caib.seycon.ng.EJBLocator;
import es.caib.seycon.ng.ServiceLocator;
import es.caib.seycon.ng.comu.SoffidObjectType;
import es.caib.seycon.ng.comu.TipusDada;
import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.remote.RemoteServiceLocator;
import es.caib.zkib.binder.BindContext;
import es.caib.zkib.datasource.DataSource;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.jxpath.JXPathContext;
import es.caib.zkib.jxpath.Pointer;

public class ScriptEnviroment {
	/**
	 * Calculate available vars for Soffid load trigger
	 * 
	 * @param c
	 * @return
	 * @throws CreateException 
	 * @throws NamingException 
	 * @throws InternalErrorException 
	 */
	public String getLoadTriggerVars (Component c) throws InternalErrorException, NamingException, CreateException
	{
		SoffidObjectType type = (SoffidObjectType) XPathUtils.getValue(c, "@objectType");

		defineAccountAttributes(c);
		defineUserAttributes(c);
		defineGroupAttributes(c);
		defineRoleAttributes(c);

		if (type == SoffidObjectType.OBJECT_ACCOUNT)
		{
			return "{\"oldObject\":\"accountObject\",\"newObject\":\"accountObject\"}";
		}
		else if (type == SoffidObjectType.OBJECT_USER)
		{
			return "{\"oldObject\":\"userObject\",\"newObject\":\"userObject\"}";
		}
		else if (type == SoffidObjectType.OBJECT_GRANT)
		{
			return "{\"oldObject\":\"grantObject\",\"newObject\":\"grantObject\"}";
		}
		else
			return "{}";
	}
	
	/**
	 * Calculate available vars for Soffid load trigger
	 * 
	 * @param c
	 * @return
	 * @throws CreateException 
	 * @throws NamingException 
	 * @throws InternalErrorException 
	 */
	public String getTriggerVars (Component c) throws InternalErrorException, NamingException, CreateException
	{
		while ( ! (c instanceof Grid ))
			c = c.getParent();
		c = c.getParent();
		SoffidObjectType type = (SoffidObjectType) XPathUtils.getValue(c, "@soffidObject");

		defineAccountAttributes(c);
		defineUserAttributes(c);
		defineGroupAttributes(c);
		defineRoleAttributes(c);

		StringBuffer sb = new StringBuffer();
		sb.append("{\"serverService\":\"es.caib.seycon.ng.sync.servei.ServerService\",\"remoteServiceLocator\":\"es.caib.seycon.ng.remote.RemoteServiceLocator\","
				+ "\"serviceLocator\":\"es.caib.seycon.ng.ServiceLocator\",\"dispatcherService\":\"es.caib.seycon.ng.sync.engine.extobj.BSHAgentbject\"");

		if (type == SoffidObjectType.OBJECT_ACCOUNT)
		{
			sb.append(",\"oldObject\":\"java.util.Map\",\"newObject\":\"java.util.Map\", \"source\":\"accountObject\"}");
		}
		else if (type == SoffidObjectType.OBJECT_USER)
		{
			sb.append(",\"oldObject\":\"java.util.Map\",\"newObject\":\"java.util.Map\", \"source\":\"userObject\"}");
		}
		else if (type == SoffidObjectType.OBJECT_GROUP)
		{
			sb.append(",\"oldObject\":\"java.util.Map\",\"newObject\":\"java.util.Map\", \"source\":\"groupObject\"}");
		}
		else if (type == SoffidObjectType.OBJECT_ROLE)
		{
			sb.append(",\"oldObject\":\"java.util.Map\",\"newObject\":\"java.util.Map\", \"source\":\"roleObject\"}");
		}
		else if (type == SoffidObjectType.OBJECT_GRANT ||
				type == SoffidObjectType.OBJECT_ALL_GRANTED_GROUP ||
				type == SoffidObjectType.OBJECT_ALL_GRANTED_ROLES ||
				type == SoffidObjectType.OBJECT_GRANTED_GROUP ||
				type == SoffidObjectType.OBJECT_GRANTED_ROLE)
		{
			sb.append(",\"oldObject\":\"java.util.Map\",\"newObject\":\"java.util.Map\", \"source\":\"grantObject\"}");
			
		}
		else
			sb.append("}");
		return sb.toString();
	}
	
	/**
	 * Calculate available vars for managed system column
	 * 
	 * @param c
	 * @return
	 * @throws CreateException 
	 * @throws NamingException 
	 * @throws InternalErrorException 
	 */
	public String getSystemVars (Component c) throws InternalErrorException, NamingException, CreateException
	{
		while ( ! (c instanceof Grid ))
			c = c.getParent();
		c = c.getParent();
		SoffidObjectType type = (SoffidObjectType) XPathUtils.getValue(c, "@soffidObject");

		defineAccountAttributes(c);
		defineUserAttributes(c);
		defineGroupAttributes(c);
		defineRoleAttributes(c);

		return "{\"serverService\":\"es.caib.seycon.ng.sync.servei.ServerService\",\"remoteServiceLocator\":\"es.caib.seycon.ng.remote.RemoteServiceLocator\","
				+ "\"serviceLocator\":\"es.caib.seycon.ng.ServiceLocator\",\"dispatcherService\":\"es.caib.seycon.ng.sync.engine.extobj.BSHAgentbject\"}";
	}

	/**
	 * Calculate available vars for managed system column
	 * 
	 * @param c
	 * @return
	 * @throws CreateException 
	 * @throws NamingException 
	 * @throws InternalErrorException 
	 */
	public String getSoffidVars (Component c) throws InternalErrorException, NamingException, CreateException
	{
		while ( ! (c instanceof Grid ))
			c = c.getParent();
		c = c.getParent();
		return getSoffidVars2(c);
	}
	
	public String getSoffidVars2 (Component c) throws InternalErrorException, NamingException, CreateException
	{
		SoffidObjectType type = (SoffidObjectType) XPathUtils.getValue(c, "@soffidObject");

		defineAccountAttributes(c);
		defineUserAttributes(c);
		defineGroupAttributes(c);
		defineRoleAttributes(c);

		StringBuffer sb = new StringBuffer();
		sb.append("{\"serverService\":\"es.caib.seycon.ng.sync.servei.ServerService\",\"remoteServiceLocator\":\"es.caib.seycon.ng.remote.RemoteServiceLocator\","
				+ "\"serviceLocator\":\"es.caib.seycon.ng.ServiceLocator\",\"dispatcherService\":\"es.caib.seycon.ng.sync.engine.extobj.BSHAgentbject\"");

		if (type == SoffidObjectType.OBJECT_ACCOUNT)
		{
			sb.append(", \"accountId\" : \"java.lang.Long\"")
				.append(", \"accountName\" : \"java.lang.String\"")
				.append(", \"passwordPolicy\" : \"java.lang.String\"")
				.append(", \"accountDescription\" : \"java.lang.String\"")
				.append(", \"accountDisabled\" : \"java.lang.String\"")
				.append(", \"active\" : \"java.lang.Boolean\"")
				.append(", \"type\" : \"java.lang.String\"")
				.append(", \"lastLogon\" : \"java.util.Calendar\"")
				.append(", \"lastUpdate\" : \"java.util.Calendar\"")
				.append(", \"lastPasswordUpdate\" : \"java.util.Calendar\"")
				.append(", \"passwordExpiration\" : \"java.util.Calendar\"")
				.append(", \"attributes\" : \"accountAttributes\"")
				.append(", \"grantedRoles\" : \"list<grantObject>\"")
				.append(", \"allGrantedRoles\" : \"list<grantObject>\"")
				.append(", \"granted\" : \"list<grantObject>\"")
				.append(", \"allGranted\" : \"list<grantObject>\"");
		}
		else if (type == SoffidObjectType.OBJECT_USER)
		{
			sb.append(", \"accountId\" : \"java.lang.Long\"")
			.append(", \"accountName\" : \"java.lang.String\"")
			.append(", \"system\" : \"java.lang.String\"")
			.append(", \"accountDescription\" : \"java.lang.String\"")
			.append(", \"accountDisabled\" : \"java.lang.String\"")
			.append(", \"active\" : \"java.lang.Boolean\"")
			.append(", \"mailAlias\" : \"java.lang.String\"")
			.append(", \"userName\" : \"java.lang.String\"")
			.append(", \"primaryGroup\" : \"java.lang.String\"")
			.append(", \"comments\" : \"java.lang.String\"")
			.append(", \"createdOn\" : \"java.util.Calendar\"")
			.append(", \"modifiedOn\" : \"java.util.Calendar\"")
			.append(", \"mailDomain\" : \"java.lang.String\"")
			.append(", \"fullName\" : \"java.lang.String\"")
			.append(", \"id\" : \"java.lang.Long\"")
			.append(", \"multiSession\" : \"java.lang.Boolean\"")
			.append(", \"firstName\" : \"java.lang.String\"")
			.append(", \"shortName\" : \"java.lang.String\"")
			.append(", \"lastName\" : \"java.lang.String\"")
			.append(", \"lastName2\" : \"java.lang.String\"")
			.append(", \"mailServer\" : \"java.lang.String\"")
			.append(", \"homeServer\" : \"java.lang.String\"")
			.append(", \"profileServer\" : \"java.lang.String\"")
			.append(", \"phone\" : \"java.lang.String\"")
			.append(", \"userType\" : \"java.lang.String\"")
			.append(", \"createdBy\" : \"java.lang.String\"")
			.append(", \"modifiedBy\" : \"java.lang.String\"")
			.append(", \"primaryGroupObject\" : \"groupObject\"")
			.append(", \"secondaryGroups\" : \"list<groupObject>\"")
			.append(", \"accountAttributes\" : \"accountAttributes\"")
			.append(", \"userAttributes\" : \"userAttributes\"")
			.append(", \"attributes\" : \"userAttributes\"") 
			.append(", \"grantedRoles\" : \"list<grantObject>\"")
			.append(", \"allGrantedRoles\" : \"list<grantObject>\"")
			.append(", \"granted\" : \"list<grantObject>\"")
			.append(", \"allGranted\" : \"list<grantObject>\"");
		}
		else if (type == SoffidObjectType.OBJECT_ROLE)
		{
			sb.append(", \"id\" : \"java.lang.Long\"")
			.append(", \"system\" : \"java.lang.String\"")
			.append(", \"name\" : \"java.lang.String\"")
			.append(", \"category\" : \"java.lang.Long\"")
			.append(", \"application\" : \"java.lang.String\"")
			.append(", \"passwordProtected\" : \"java.lang.Boolean\"")
			.append(", \"description\" : \"java.lang.String\"")
			.append(", \"wfmanaged\" : \"java.lang.Boolean\"")
			.append(", \"ownedRoles\" : \"list<grantObject>\"")
			.append(", \"ownerRoles\" : \"list<grantObject>\"")
			.append(", \"ownerGroups\" : \"list<groupObject>\"")
			.append(", \"domain\" : \"java.lang.String\"")
			.append(", \"grantedAccountNames\" : \"java.util.List\"")
			.append(", \"allGrantedAccountNames\" : \"java.util.List\"")
			.append(", \"grantedAccounts\" : \"list<grantObject>\"")
			.append(", \"allGrantedAccounts\" : \"list<grantObject>\"")
			.append(", \"attributes\" : \"roleAttributes\"");
		}
		else if (type == SoffidObjectType.OBJECT_GRANT ||
				type == SoffidObjectType.OBJECT_ALL_GRANTED_GROUP ||
				type == SoffidObjectType.OBJECT_ALL_GRANTED_ROLES ||
				type == SoffidObjectType.OBJECT_GRANTED_GROUP ||
				type == SoffidObjectType.OBJECT_GRANTED_ROLE)
		{
			sb.append(", \"id\" : \"java.lang.Long\"")
			.append(", \"grantedRole\" : \"java.lang.String\"")
			.append(", \"grantedRoleSystem\" : \"java.lang.String\"")
			.append(", \"grantedRoleId\" : \"java.lang.Long\"")
			.append(", \"domainValue\" : \"java.lang.String\"")
			.append(", \"ownerAccount\" : \"java.lang.String\"")
			.append(", \"ownerSystem\" : \"java.lang.String\"")
			.append(", \"ownerGroup\" : \"java.lang.String\"")
			.append(", \"ownerRoleId\" : \"java.lang.Long\"")
			.append(", \"ownerRoleName\" : \"java.lang.String\"")
			.append(", \"ownerUserObject\" : \"userObject\"")
			.append(", \"ownerUser\" : \"java.lang.String\"")
			.append(", \"ownerAccountObject\" : \"accountObject\"")
			.append(", \"grantedRoleObject\" : \"roleObject\"");
		}
		else if (type == SoffidObjectType.OBJECT_GROUP)
		{
			sb.append(", \"groupId\" : \"java.lang.Long\"")
			.append(", \"name\" : \"java.lang.String\"")
			.append(", \"system\" : \"java.lang.String\"")
			.append(", \"description\" : \"java.lang.String\"")
			.append(", \"parent\" : \"java.lang.String\"")
			.append(", \"disabled\" : \"java.lang.Boolean\"")
			.append(", \"server\" : \"java.lang.String\"")
			.append(", \"accountingGroup\" : \"java.lang.String\"")
			.append(", \"type\" : \"java.lang.String\"")
			.append(", \"driveLetter\" : \"java.lang.String\"")
			.append(", \"users\" : \"list<userObject>\"")
			.append(", \"userNames\" : \"java.util.List\"")
			.append(", \"allUsers\" : \"list<userObject>\"")
			.append(", \"allUserNames\" : \"java.util.List\"")
			.append(", \"grantedRoles\" : \"list<grantObject>\"")
			.append(", \"grantedRoleNames\" : \"java.util.List\"")
			.append(", \"attributes\" : \"groupAttributes\"");
		}
		sb.append("}");
		return sb.toString();
	}

	private void defineAccountAttributes(Component c)
	{
		StringBuffer sb = new StringBuffer();
				
		DataSource lb = (DataSource) Path.getComponent(c.getPage(), "/esquema/lista/listbox");
		JXPathContext ctx = lb.getJXPathContext();
		for (Iterator<Pointer> it = ctx.iteratePointers("/metadata"); it.hasNext(); )
		{
			Pointer p = it.next();
			String name = (String) ctx.getValue(p.asPath()+"/@codi");
			TypeEnumeration t = (TypeEnumeration) ctx.getValue(p.asPath()+"/@type");
			if ( sb.length() > 0)
				sb.append(",");
			sb.append("'{\"").append(name).append("\"}':\"");
			if (t == TypeEnumeration.BINARY_TYPE || t == TypeEnumeration.PHOTO_TYPE)
				sb.append("byte");
			else if (t == TypeEnumeration.DATE_TYPE)
				sb.append("java.util.Calendar");
			else
				sb.append("java.lang.String");
			sb.append("\"");
		}
		Executions.getCurrent().addAuResponse(null,
				new AuScript(null, "CodeMirrorJavaTypes[\"accountAttributes\"]={"+sb.toString()+"};")); 
		
	}

	private void defineUserAttributes(Component c) throws InternalErrorException, NamingException, CreateException
	{
		StringBuffer sb = new StringBuffer();
		
		for (TipusDada td: EJBLocator.getDadesAddicionalsService().getTipusDades())
		{
			if ( sb.length() > 0)
				sb.append(",");
			sb.append("'{\"").append(td.getCodi()).append("\"}':\"");
			TypeEnumeration t = td.getType();
			if (t == TypeEnumeration.BINARY_TYPE || t == TypeEnumeration.PHOTO_TYPE)
				sb.append("byte");
			else if (t == TypeEnumeration.DATE_TYPE)
				sb.append("java.util.Calendar");
			else
				sb.append("java.lang.String");
			sb.append("\"");
		}
		Executions.getCurrent().addAuResponse(null,
				new AuScript(null, "CodeMirrorJavaTypes[\"userAttributes\"]={"+sb.toString()+"};")); 
		
	}

	private void defineRoleAttributes(Component c) throws InternalErrorException, NamingException, CreateException
	{
		StringBuffer sb = new StringBuffer();
		
		for (TipusDada td: EJBLocator.getDadesAddicionalsService().findDataTypes(MetadataScope.ROLE))
		{
			if ( sb.length() > 0)
				sb.append(",");
			sb.append("'{\"").append(td.getCodi()).append("\"}':\"");
			TypeEnumeration t = td.getType();
			if (t == TypeEnumeration.BINARY_TYPE || t == TypeEnumeration.PHOTO_TYPE)
				sb.append("byte");
			else if (t == TypeEnumeration.DATE_TYPE)
				sb.append("java.util.Calendar");
			else
				sb.append("java.lang.String");
			sb.append("\"");
		}
		Executions.getCurrent().addAuResponse(null,
				new AuScript(null, "CodeMirrorJavaTypes[\"roleAttributes\"]={"+sb.toString()+"};")); 
		
	}

	private void defineGroupAttributes(Component c) throws InternalErrorException, NamingException, CreateException
	{
		StringBuffer sb = new StringBuffer();
		
		for (TipusDada td: EJBLocator.getDadesAddicionalsService().findDataTypes(MetadataScope.GROUP))
		{
			if ( sb.length() > 0)
				sb.append(",");
			sb.append("'{\"").append(td.getCodi()).append("\"}':\"");
			TypeEnumeration t = td.getType();
			if (t == TypeEnumeration.BINARY_TYPE || t == TypeEnumeration.PHOTO_TYPE)
				sb.append("byte");
			else if (t == TypeEnumeration.DATE_TYPE)
				sb.append("java.util.Calendar");
			else
				sb.append("java.lang.String");
			sb.append("\"");
		}
		Executions.getCurrent().addAuResponse(null,
				new AuScript(null, "CodeMirrorJavaTypes[\"groupAttributes\"]={"+sb.toString()+"};")); 
		
	}
}