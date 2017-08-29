package com.soffid.iam.web.component;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.util.resource.Labels;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.DataType;
import com.soffid.iam.api.MetadataScope;
import com.soffid.iam.web.SearchAttributeDefinition;
import com.soffid.iam.web.SearchDictionary;

import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.seycon.ng.exception.InternalErrorException;

public class SearchDictionaryBuilder {
	static Map<String, SearchDictionary> map = new HashMap<String, SearchDictionary>();
	public static SearchDictionary build (String clazz) throws ClassNotFoundException, InternalErrorException, NamingException, CreateException
	{
		SearchDictionary sd = map.get(clazz);
		if (sd == null)
		{
			sd = generateDefaultBuilder(clazz);
			if (clazz.equals("com.soffid.iam.api.User")) {
				addUserJoins(sd);
			} else if (clazz.equals("com.soffid.iam.api.Role")) {
				addRoleJoins(sd);
			} else if (clazz.equals("com.soffid.iam.api.Application")) {
				addApplicationJoins(sd);
			}
			map.put(clazz, sd);
		}
		if (clazz.equals("com.soffid.iam.api.User")) {
			sd = addAttributes (sd, MetadataScope.USER);
		} else if (clazz.equals("com.soffid.iam.api.Role")) {
			sd = addAttributes (sd, MetadataScope.ROLE);
		} else if (clazz.equals("com.soffid.iam.api.Application")) {
			sd = addAttributes (sd, MetadataScope.APPLICATION);
		}
		return sd;
	}
	
	private static void addUserJoins(SearchDictionary sd) {
		addJoin (sd, "accounts.account.name", "auditoria.zul.account", TypeEnumeration.STRING_TYPE);
		addJoin (sd, "accounts.account.roles.role.name", "auditoria.zul.rol", TypeEnumeration.STRING_TYPE);
		
	}

	private static void addJoin(SearchDictionary sd, String name, String labelName, TypeEnumeration type) {
		SearchAttributeDefinition sad = new SearchAttributeDefinition();
		sad.setName(name);
		sad.setLabelName(labelName);
		sad.setType(type);
		sd.getAttributes().add(sad);
	}
	
	private static SearchDictionary generateDefaultBuilder(String clazz) throws ClassNotFoundException {
		SearchDictionary sd;
		sd = new SearchDictionary();
		sd.setAttributes(new LinkedList<SearchAttributeDefinition>());
		sd.setTimestamp(System.currentTimeMillis());
		Class<?> cl = Class.forName(clazz);
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
			if (! Modifier.isStatic(f.getModifiers()) && ! f.getName().equals("id"))
			{
				Class t = f.getType();
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
				else if (Boolean.class.isAssignableFrom(t))
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
						List<String> names = (List<String>) cl.getMethod("names").invoke(null);
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
		return sd;
	}

	private static void addRoleJoins(SearchDictionary sd) {
		addJoin (sd, "accounts.account.name", "auditoria.zul.account", TypeEnumeration.STRING_TYPE);
	}

	private static SearchDictionary addAttributes(SearchDictionary sd1, MetadataScope scope) throws InternalErrorException, NamingException, CreateException {
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
				sad.setName("attributes."+att.getCode());
				if (att.getValues() != null && ! att.getValues().isEmpty())
				{
					sad.setLabels(att.getValues());
					sad.setValues(att.getValues());
				}
				sd2.getAttributes().add(sad);
			}
				
		}
		return sd2;
	}

	private static void addApplicationJoins(SearchDictionary sd) {
		addJoin (sd, "roles.name", "auditoria.zul.rol", TypeEnumeration.STRING_TYPE);
	}
}
