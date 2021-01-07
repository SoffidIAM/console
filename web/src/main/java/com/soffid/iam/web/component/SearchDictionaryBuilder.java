package com.soffid.iam.web.component;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.util.resource.Labels;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.ServiceLocator;
import com.soffid.iam.api.CustomObjectType;
import com.soffid.iam.api.DataType;
import com.soffid.iam.api.MetadataScope;
import com.soffid.iam.api.Server;
import com.soffid.iam.utils.Security;
import com.soffid.iam.web.SearchAttributeDefinition;
import com.soffid.iam.web.SearchDictionary;

import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.comu.Dispatcher;
import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.seycon.ng.exception.InternalErrorException;

public class SearchDictionaryBuilder {

	private static final String COM_SOFFID_IAM_API_CUSTOM_OBJECT = "com.soffid.iam.api.CustomObject#";	
	@SuppressWarnings("serial")
	private static LinkedList<String> EXCLUSIONS = new LinkedList<String>() {{
		 add("com.soffid.iam.api.User.passwordMaxAge");
		 add("com.soffid.iam.api.User.phoneNumber");
		 add("com.soffid.iam.api.User.nationalID");
		 add("com.soffid.iam.api.Application.relativeName");
		 add("com.soffid.iam.api.Application.source");
		 add("com.soffid.iam.api.Application.owner");
		 add("com.soffid.iam.api.Application.executable");
		 add("com.soffid.iam.api.Application.database");
		 add("com.soffid.iam.api.Application.ownerName");
		 add("com.soffid.iam.api.Group.driveServerName");
		 add("com.soffid.iam.api.Group.section");
		 add("com.soffid.iam.api.CustomObject.type");
		 add("com.soffid.iam.api.System.param0");
		 add("com.soffid.iam.api.System.param1");
		 add("com.soffid.iam.api.System.param2");
		 add("com.soffid.iam.api.System.param3");
		 add("com.soffid.iam.api.System.param4");
		 add("com.soffid.iam.api.System.param5");
		 add("com.soffid.iam.api.System.param6");
		 add("com.soffid.iam.api.System.param7");
		 add("com.soffid.iam.api.System.param8");
		 add("com.soffid.iam.api.System.param9");
		 add("com.soffid.iam.api.System.blobParam");
	}};
	private static Map<String, SearchDictionary> map = new HashMap<String, SearchDictionary>();

	public static SearchDictionary build (String clazz) throws ClassNotFoundException, InternalErrorException, NamingException, CreateException
	{
		SearchDictionary sd = map.get(clazz);
		if (sd == null)
		{
			if (clazz.startsWith(COM_SOFFID_IAM_API_CUSTOM_OBJECT))
				sd = generateDefaultBuilder("com.soffid.iam.api.CustomObject");
			else
				sd = generateDefaultBuilder(clazz);
			if (clazz.equals("com.soffid.iam.api.User")) {
				addUserJoins(sd);
			} else if (clazz.equals("com.soffid.iam.api.Role")) {
				addRoleJoins(sd);
			} else if (clazz.equals("com.soffid.iam.api.Application")) {
				addApplicationJoins(sd);
			} else if (clazz.equals("com.soffid.iam.api.Group")) {
				addGroupJoins(sd);
			} else if (clazz.equals("com.soffid.iam.api.Account")) {
				addAccountsJoins(sd);
			}
			map.put(clazz, sd);
		}
		
		// Add tenant dependent attributes
		if (clazz.equals("com.soffid.iam.api.User")) {
			sd = addUserAttributes (sd, MetadataScope.USER);
		} else if (clazz.equals("com.soffid.iam.api.Role")) {
			sd = addAttributes (sd, MetadataScope.ROLE);
		} else if (clazz.equals("com.soffid.iam.api.Application")) {
			sd = addAttributes (sd, MetadataScope.APPLICATION);
		} else if (clazz.equals("com.soffid.iam.api.Group")) {
			sd = addAttributes (sd, MetadataScope.GROUP);
		} else if (clazz.equals("com.soffid.iam.api.Account")) {
			sd = addAccountAttributes (sd);
		} else if (clazz.equals("com.soffid.iam.api.System")) {
			sd = addSystemAttributes (sd);
		} else if (clazz.equals("com.soffid.iam.api.AttributeTranslation")) {
			sd = addAttributeTranslationAttributes (sd);
		}
		if (clazz.startsWith(COM_SOFFID_IAM_API_CUSTOM_OBJECT))
			sd = addCustomAttributes (sd, MetadataScope.CUSTOM, clazz.substring(COM_SOFFID_IAM_API_CUSTOM_OBJECT.length()));
		return sd;
	}

	private static SearchDictionary generateDefaultBuilder(String clazz) throws ClassNotFoundException {
		SearchDictionary sd;
		sd = new SearchDictionary();
		sd.setAttributes(new LinkedList<SearchAttributeDefinition>());
		sd.setTimestamp(System.currentTimeMillis());
		Class<?> cl = Class.forName(clazz);
		do {
			for (Field f: cl.getDeclaredFields())
			{
				SearchAttributeDefinition sad = new SearchAttributeDefinition();
				sad.setName(f.getName());
				String labelName = clazz+"."+f.getName();
				try {
					Labels.getRequiredLabel(labelName);
					sad.setLabelName(labelName);
				} catch (Exception e) {
					sad.setLocalizedName(f.getName());
				}
				if (! Modifier.isStatic(f.getModifiers()) && ! f.getName().equals("id") && ! EXCLUSIONS.contains(clazz+"."+f.getName()))
				{
					Class<?> t = f.getType();
					sad.setJavaType(t);
					if (Date.class.isAssignableFrom(t) ||
							Calendar.class.isAssignableFrom(t))
					{
						sad.setType(TypeEnumeration.DATE_TYPE);
					}
					else if (String.class.isAssignableFrom(t) ||
							Integer.class.isAssignableFrom(t) || 
							Long.class.isAssignableFrom(t))
					{
						sad.setType(TypeEnumeration.STRING_TYPE);
					}
					else if (Boolean.class.isAssignableFrom(t) || boolean.class.isAssignableFrom(t))
					{
						sad.setType(TypeEnumeration.STRING_TYPE);
						LinkedList<String> l = new LinkedList<String>();
						l.add("true");
						l.add("false");
						sad.setValues(l);
						l = new LinkedList<String>();
						l.add("True");
						l.add("False");
						sad.setLabels(l);
					}
					else 
					{
						try {
							@SuppressWarnings("unchecked")
							List<String> names = (List<String>) cl.getMethod("names").invoke(null);
							@SuppressWarnings("unchecked")
							List<String> values = (List<String>) cl.getMethod("values").invoke(null);
							sad.setValues(values);
							sad.setLabels(names);
							sad.setType(TypeEnumeration.STRING_TYPE);
						} catch (Exception e) {
							sad = null;
						}
					}
					
					if (sad != null)
						sd.getAttributes().add(sad);
				}
			}
			cl = cl.getSuperclass();
		} while (cl != null);
		return sd;
	}

	private static SearchDictionary addAttributes(SearchDictionary sd1, MetadataScope scope) throws InternalErrorException, NamingException, CreateException {
		return addAttributes(sd1, scope, "attributes");
	}

	private static SearchDictionary addUserAttributes(SearchDictionary sd1, MetadataScope scope) throws InternalErrorException, NamingException, CreateException {
		return addAttributes(sd1, scope, "userData");
	}

	private static SearchDictionary addAttributes(SearchDictionary sd1, MetadataScope scope, String attributesPath) throws InternalErrorException, NamingException, CreateException {
		SearchDictionary sd2 = new SearchDictionary(sd1);
		sd2.setAttributes( new LinkedList<SearchAttributeDefinition>(sd1.getAttributes()));
		for (DataType att: EJBLocator.getAdditionalDataService().findDataTypes(scope))
		{
			if (!TypeEnumeration.BINARY_TYPE.equals( att.getType() ) &&
				! TypeEnumeration.PHOTO_TYPE.equals(att.getType()))
			{
				SearchAttributeDefinition sad = new SearchAttributeDefinition();
				sad.setLocalizedName(att.getLabel());
				sad.setType(att.getType());
				sad.setName(attributesPath+"."+att.getCode());
				if (att.getValues() != null && ! att.getValues().isEmpty())
				{
					List<String> labels = new LinkedList<String>();
					List<String> values = new LinkedList<String>();
					for ( String s: att.getValues())
					{
						if (s.contains(":"))
						{
							values.add ( s.substring(0, s.indexOf(":")).trim());
							labels.add( s.substring(s.indexOf(":")).trim());
						}
						else
						{
							values.add(s);
							labels.add(s);
						}
					}
					sad.setLabels(labels);
					sad.setValues(values);
				}
				sd2.getAttributes().add(sad);
			}
		}
		return sd2;
	}

	private static void addJoin(SearchDictionary sd, String name, String labelName, TypeEnumeration type) {
		SearchAttributeDefinition sad = new SearchAttributeDefinition();
		sad.setName(name);
		sad.setLabelName(labelName);
		sad.setType(type);
		sd.getAttributes().add(sad);
	}

	private static void addUserJoins(SearchDictionary sd) {
		addJoin(sd, "accounts.account.name", "auditoria.zul.account", TypeEnumeration.STRING_TYPE);
		addJoin(sd, "accounts.account.roles.role.name", "auditoria.zul.rol", TypeEnumeration.STRING_TYPE);
		addJoin(sd, "secondaryGroups.group.name", "usuaris.zul.Grupsecundari-2", TypeEnumeration.STRING_TYPE);
		addJoin(sd, "accounts.account.system.name", "accounts.dispatcherValidation", TypeEnumeration.STRING_TYPE);
		addJoin(sd, "accounts.account.roles.role.informationSystem.name", "role.system", TypeEnumeration.STRING_TYPE);
	}

	private static void addRoleJoins(SearchDictionary sd) {
		addJoin (sd, "accounts.account.name", "auditoria.zul.account", TypeEnumeration.STRING_TYPE);
	}

	private static void addApplicationJoins(SearchDictionary sd) {
		addJoin(sd, "roles.name", "auditoria.zul.rol", TypeEnumeration.STRING_TYPE);
	}

	private static void addGroupJoins(SearchDictionary sd) {
		addJoin(sd, "homeServer.name", "grups.zul.Servidorofimatic-2", TypeEnumeration.STRING_TYPE);
	}

	private static void addAccountsJoins(SearchDictionary sd) {
		addJoin(sd, "acl.role.name", "acl.role.name", TypeEnumeration.STRING_TYPE);
		addJoin(sd, "acl.group.name", "acl.group.name", TypeEnumeration.STRING_TYPE);
		addJoin(sd, "acl.user.userName", "acl.user.userName", TypeEnumeration.STRING_TYPE);
		addJoin(sd, "roles.role.name", "roles.role.name", TypeEnumeration.STRING_TYPE);
		addJoin(sd, "users.user.userName", "users.user.userName", TypeEnumeration.STRING_TYPE);
		addAccountType(sd);
	}

	@SuppressWarnings("unchecked")
	private static void addAccountType(SearchDictionary sd) {
		SearchAttributeDefinition sad = new SearchAttributeDefinition();
		sad.setLabelName("seyconserver.zul.Tipus");
		sad.setType(TypeEnumeration.STRING_TYPE);
		sad.setName("type");
		sad.setJavaType(String.class);
		LinkedList<String> listLabels = new LinkedList<String>();
		for (Object s : AccountType.literals()) {
			String label = "accountType."+s.toString();
			listLabels.add(org.zkoss.util.resource.Labels.getLabel(label));
		}
		sad.setLabels(listLabels);
		sad.setValues(AccountType.literals());
		sd.getAttributes().add(sad);
	}



	private static SearchDictionary addCustomAttributes(SearchDictionary sd1, MetadataScope scope, String objectType) throws InternalErrorException, NamingException, CreateException {
		SearchDictionary sd2 = new SearchDictionary(sd1);
		sd2.setAttributes( new LinkedList<SearchAttributeDefinition>(sd1.getAttributes()));
		for (DataType att: EJBLocator.getAdditionalDataService().findDataTypesByObjectTypeAndName(objectType, null))
		{
			if (!TypeEnumeration.BINARY_TYPE.equals( att.getType() ) &&
				! TypeEnumeration.PHOTO_TYPE.equals(att.getType()))
			{
				SearchAttributeDefinition sad = new SearchAttributeDefinition();
				sad.setLocalizedName(att.getLabel());
				sad.setType(att.getType());
				sad.setName("attributes."+att.getCode());
				if (att.getValues() != null && ! att.getValues().isEmpty())
				{
					LinkedList<String> values = new LinkedList<String>();
					LinkedList<String> labels = new LinkedList<String>();
					for ( String s: att.getValues())
					{
						if (s.contains(":"))
						{
							values.add ( s.substring(0, s.indexOf(":")).trim());
							labels.add( s.substring(s.indexOf(":")+1).trim());
						}
						else
						{
							values.add(s);
							labels.add(s);
						}
					}
					sad.setLabels(labels);
					sad.setValues(values);
				}
				sd2.getAttributes().add(sad);
			}
		}
		return sd2;
	}

	private static SearchDictionary addAccountAttributes(SearchDictionary sd1) throws InternalErrorException, NamingException, CreateException {
		SearchDictionary sd2 = new SearchDictionary(sd1);
		sd2.setAttributes( new LinkedList<SearchAttributeDefinition>(sd1.getAttributes()));
		for (com.soffid.iam.api.System d: EJBLocator.getDispatcherService().findAllActiveDispatchers())
		{
			for (DataType att: EJBLocator.getAdditionalDataService().findSystemDataTypes(d.getName()))
			{
				if (!TypeEnumeration.BINARY_TYPE.equals(att.getType()) && !TypeEnumeration.PHOTO_TYPE.equals(att.getType()) &&
					att.getLabel() != null && att.getType() != null && att.getCode() != null)
				{
					SearchAttributeDefinition sad = new SearchAttributeDefinition();
					sad.setLocalizedName(att.getLabel());
					sad.setType(att.getType());
					sad.setName("attributes."+att.getCode());
					if (att.getValues() != null && ! att.getValues().isEmpty()) {
						LinkedList<String> values = new LinkedList<String>();
						LinkedList<String> labels = new LinkedList<String>();
						for ( String s: att.getValues())
						{
							if (s.contains(":"))
							{
								values.add ( s.substring(0, s.indexOf(":")).trim());
								labels.add( s.substring(s.indexOf(":")+1).trim());
							}
							else
							{
								values.add(s);
								labels.add(s);
							}
						}
						sad.setLabels(labels);
						sad.setValues(values);
					}
					sd2.getAttributes().add(sad);
				}
			}
		}
		// Replace system attribute
		try {
			Collection<com.soffid.iam.api.System> list = EJBLocator.getDispatcherService().findDispatchersByFilter(null, null, null, null, null, null);
			if (list.size() < 25)
			{
				for ( SearchAttributeDefinition sad: sd2.getAttributes())
				{
					if (sad.getName().equals("system"))
					{
						sd2.getAttributes().remove(sad);
						sad = new SearchAttributeDefinition(sad);
						List<String> dispatchers = new LinkedList<String>();
						for (com.soffid.iam.api.System s: list) {
							dispatchers.add(s.getName());
						}
						Collections.sort(dispatchers);
						sad.setValues(dispatchers);
						sad.setLabels(dispatchers);
						sd2.getAttributes().add(sad);
					}
				}
			}
		} catch (Exception e) {
			// Cannot retrieve accounts list
		}
		return sd2;
	}

	private static SearchDictionary addAuditAttributes(SearchDictionary sd1) throws InternalErrorException, NamingException, CreateException {
		SearchDictionary sd2 = new SearchDictionary(sd1);
		LinkedList<SearchAttributeDefinition> attributes = new LinkedList<SearchAttributeDefinition>(sd1.getAttributes());
		sd2.setAttributes( attributes);

		for ( Iterator<SearchAttributeDefinition> it = attributes.iterator(); it.hasNext();)
		{
			SearchAttributeDefinition sad = it.next();
			if (sad.getName().equals("mailDomainBelogns") ||
					sad.getName().equals("file") ||
					sad.getName().equals("fileName") )
				it.remove();
			else if (sad.getName().equals("customObjectType"))
			{
				List<String> types = new LinkedList<String>();
				List<String> labels = new LinkedList<String>();
				List<CustomObjectType> objectTypes = new LinkedList<CustomObjectType>( 
						ServiceLocator.instance().getAdditionalDataService().findCustomObjectTypeByJsonQuery("") );
				Collections.sort(objectTypes, new Comparator<CustomObjectType>() {
					@Override
					public int compare(CustomObjectType o1, CustomObjectType o2) {
						return o1.getDescription().compareTo(o2.getDescription());
					}
				});
				for (CustomObjectType ot: objectTypes)
				{
					types.add(ot.getName());
					labels.add(ot.getDescription());
				}
				sad.setValues(types);
				sad.setLabels(labels);
			}
			else if (sad.getName().equals("database"))
			{
				List<String> types = new LinkedList<String>();
				List<String> labels = new LinkedList<String>();
				List<com.soffid.iam.api.System> systems = new LinkedList<com.soffid.iam.api.System>( 
						ServiceLocator.instance().getDispatcherService().findDispatchersByFilter(null, null, null, null, null, null));
				if (systems.size() <= 15)
				{
					Collections.sort(systems, new Comparator<com.soffid.iam.api.System>() {
						@Override
						public int compare(com.soffid.iam.api.System o1, com.soffid.iam.api.System o2) {
							return o1.getDescription().compareTo(o2.getDescription());
						}
					});
					for (com.soffid.iam.api.System ot: systems)
					{
						types.add(ot.getName());
						labels.add(ot.getDescription());
					}
					sad.setValues(types);
					sad.setLabels(labels);
				}
			}
		}
		return sd2;
	}
	
	private static SearchDictionary addSystemAttributes(SearchDictionary sd1) throws InternalErrorException, NamingException, CreateException {
		SearchDictionary sd2 = new SearchDictionary(sd1);
		sd2.setAttributes( new LinkedList<SearchAttributeDefinition>(sd1.getAttributes()));
		// Replace system attribute
		try {
			for ( SearchAttributeDefinition sad: sd2.getAttributes())
			{
				if (sad.getName().equals("url") || sad.getName().equals("url2"))
				{
					sd2.getAttributes().remove(sad);
					addServerURL(sad.getName(), sd2);
				}
			}
		} catch (Exception e) {
			// Cannot retrieve accounts list
		}
		return sd2;
	}

	private static void addServerURL(String name, SearchDictionary sd) throws InternalErrorException, NamingException, CreateException {
		SearchAttributeDefinition sad = new SearchAttributeDefinition();
		sad.setLabelName("com.soffid.iam.api.System.url");
		sad.setType(TypeEnumeration.STRING_TYPE);
		sad.setName(name);
		sad.setJavaType(String.class);
		LinkedList<String> listLabels = new LinkedList<String>();
		LinkedList<String> valueLabels = new LinkedList<String>();
		Security.nestedLogin(Security.ALL_PERMISSIONS);
		try {
			listLabels.add (org.zkoss.util.resource.Labels.getLabel("agents.disabledAgent"));
			valueLabels.add(null);
			listLabels.add (org.zkoss.util.resource.Labels.getLabel("agents.local"));
			valueLabels.add("local");
			
			for (Server server: EJBLocator.getDispatcherService().findAllServers()) {
				listLabels.add(server.getName());
				valueLabels.add(server.getUrl());
			}
		} finally {
			Security.nestedLogoff();
		}
		sad.setLabels(listLabels);
		sad.setValues(valueLabels);
		sd.getAttributes().add(sad);
	}

	private static SearchDictionary addAttributeTranslationAttributes(SearchDictionary sd1) throws InternalErrorException, NamingException, CreateException {
		SearchDictionary sd2 = new SearchDictionary(sd1);
		sd2.setAttributes( new LinkedList<SearchAttributeDefinition>(sd1.getAttributes()));
		// Replace domain attribute
		try {
			for ( SearchAttributeDefinition sad: sd2.getAttributes())
			{
				if (sad.getName().equals("domain"))
				{
					sd2.getAttributes().remove(sad);
					addTranslationAttributeDomain(sad.getName(), sd2);
				}
			}
		} catch (Exception e) {
			// Cannot retrieve accounts list
		}
		return sd2;
	}

	@SuppressWarnings("unchecked")
	private static void addTranslationAttributeDomain(String name, SearchDictionary sd) throws InternalErrorException, NamingException, CreateException {
		SearchAttributeDefinition sad = new SearchAttributeDefinition();
		sad.setLabelName("com.soffid.iam.api.AttributeTranslation.domain");
		sad.setType(TypeEnumeration.STRING_TYPE);
		sad.setName(name);
		sad.setJavaType(String.class);
		LinkedList<String> listLabels = new LinkedList<String>();
		LinkedList<String> valueLabels = new LinkedList<String>();
		Security.nestedLogin(Security.ALL_PERMISSIONS);
		try {
			for (String domain: EJBLocator.getAttributeTranslationService().findDomains()) {
				listLabels.add(domain);
				valueLabels.add(domain);
			}
		} finally {
			Security.nestedLogoff();
		}
		sad.setLabels(listLabels);
		sad.setValues(valueLabels);
		sd.getAttributes().add(sad);
	}


}