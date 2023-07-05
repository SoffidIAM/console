package com.soffid.iam.service.impl;

import java.io.InputStream;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.soffid.iam.api.AttributeVisibilityEnum;
import com.soffid.iam.api.CustomObjectType;
import com.soffid.iam.api.DataType;
import com.soffid.iam.api.LetterCaseEnum;
import com.soffid.iam.model.CustomObjectEntity;
import com.soffid.iam.model.CustomObjectTypeEntityDao;
import com.soffid.iam.model.MetaDataEntity;
import com.soffid.iam.model.MetaDataEntityDao;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.seycon.ng.exception.InternalErrorException;

public class MetadataCache {
	Hashtable<String, MetadataCacheInstance> data = new Hashtable<>();
	List<CustomObjectType> types = new LinkedList<>();
	long lastTypesRefresh = 0;
	private MetaDataEntityDao metaDataEntityDao;
	private CustomObjectTypeEntityDao customObjectEntityTypeEntityDao;
	
	public MetadataCache(CustomObjectTypeEntityDao customObjectEntityTypeEntityDao, MetaDataEntityDao metaDataEntityDao) {
		this.metaDataEntityDao = metaDataEntityDao;
		this.customObjectEntityTypeEntityDao = customObjectEntityTypeEntityDao;
	}

	public void clear(String name) throws InternalErrorException {
		data.remove(Security.getCurrentTenantName()+"/"+name);
	}
	
	public void clear() throws InternalErrorException {
		lastTypesRefresh = 0;
	}

	public List<CustomObjectType> getCustomObjectTypes() {
		if (lastTypesRefresh < System.currentTimeMillis() - 60000) {
			types = customObjectEntityTypeEntityDao.toCustomObjectTypeList(customObjectEntityTypeEntityDao.loadAll());
			lastTypesRefresh = System.currentTimeMillis();
		}
		LinkedList<CustomObjectType> l = new LinkedList<CustomObjectType>();
		for (CustomObjectType t: types) {
			l.add(new CustomObjectType(t));
		}
		return l;
	}
	
	public List<DataType> get(String name) throws Exception {
		MetadataCacheInstance instance = data.get(Security.getCurrentTenantName()+"/"+name);
		if (instance == null)
			instance = new MetadataCacheInstance();
		if (instance.getTimestamp() < System.currentTimeMillis() - 60000) // 1 minute
		{
			instance.setTimestamp( System.currentTimeMillis() );
			List<MetaDataEntity> l = metaDataEntityDao.findByObjectTypeAndName(name, null);
			if (l.isEmpty()) { 
				String fileName = name.replace(".", "/") + ".ui.json";
				List<DataType> d = getDescriptorMetadata(fileName);
				if (d == null) {
					return new LinkedList<DataType>();
				}
				instance.setData(d);
			}
			else
				instance.setData(metaDataEntityDao.toDataTypeList(l));
		}
		return instance.getData();
	}

	protected List<DataType> getDescriptorMetadata(String resourceName)
			throws Exception {
		InputStream in = getClass().getClassLoader().getResourceAsStream(resourceName);
		if (in == null)
			return null;
		
		JSONObject o = new JSONObject(new JSONTokener(in));
		in.close();
		
		String className = o.getString("class");

		JSONArray atts = o.getJSONArray("attributes");
		
		long last =  0;
		List<DataType> result = new LinkedList<>();
		for (int i = 0; i < atts.length(); i++) {
			JSONObject att = atts.getJSONObject(i);
			String name = att.optString("name");
			String type = att.optString("type");
			String lettercase = att.optString("lettercase");
			boolean required = att.optBoolean("required", false);
			if ( resourceName.equals("com/soffid/iam/api/Host.ui.json") && 
					name.equals("networkCode"))
				required = true;
			boolean readonly = att.optBoolean("readonly", false);
			boolean hidden = att.optBoolean("hidden", false);
			boolean multiline = att.optBoolean("multiline", false);
			boolean searchCriteria = att.optBoolean("searchCriteria", false);
			boolean multivalue = att.optBoolean("multivalue", false);
			String customUiHandler = att.optString("custom_ui_handler");
			String separator = att.optString("separator");
			String validator = att.optString("validator");
			String length = att.optString("length");
			String filterExpression = att.optString("filter_expression");
			String enumeration = att.optString("enumeration");
			if (! hidden) {
				DataType md = new DataType();
				md.setAdminVisibility( hidden ? AttributeVisibilityEnum.HIDDEN :
					readonly ? AttributeVisibilityEnum.READONLY :
						AttributeVisibilityEnum.EDITABLE);
				md.setBuiltin(true);
				md.setEnumeration(enumeration);
				md.setFilterExpression(filterExpression);
				md.setLetterCase(lettercase != null && lettercase.toLowerCase().startsWith("u") ? LetterCaseEnum.UPPERCASE :
					lettercase != null && lettercase.toLowerCase().startsWith("l") ? LetterCaseEnum.LOWERCASE:
						LetterCaseEnum.MIXEDCASE);
				md.setMultiValued(multivalue);
				md.setName(name);
				md.setNlsLabel(className+"."+name);
				md.setOrder(last++);
				md.setRequired(required);
				md.setSearchCriteria(searchCriteria);
				md.setMultiLine(multiline);
				if (length != null && !length.trim().isEmpty())
					md.setSize(Integer.parseInt(length));
				md.setType(guessType (type));
				md.setValidator(validator);
				md.setReadOnly(readonly);
				md.setBuiltinHandler(customUiHandler);
				JSONArray values = att.optJSONArray("listOfValues");
				if (values != null) {
					List<String> v = new LinkedList<>();
					for (int j = 0; j < values.length(); j++) {
						v.add(values.optString(j));
					}
					md.setValues(v);
				}

				result.add(md);
			}
		}
		return result;
	}

	private TypeEnumeration guessType(String type) throws InternalErrorException {
		for (String value: (List<String>)TypeEnumeration.literals()) {
			if ( value.equalsIgnoreCase(type))
				return TypeEnumeration.fromString(value);
		}

		int pos = 0;
		for (String value: (List<String>)TypeEnumeration.names()) {
			if ( value.equalsIgnoreCase(type+"_TYPE"))
				return TypeEnumeration.fromString( (String) TypeEnumeration.literals().get(pos));
			pos ++;
		}

		pos = 0;
		for (String value: (List<String>)TypeEnumeration.names()) {
			if ( value.equalsIgnoreCase(type))
				return TypeEnumeration.fromString( (String) TypeEnumeration.literals().get(pos));
			pos ++;
		}
		
		throw new InternalErrorException ("Unknown data type "+type);
	}

}
