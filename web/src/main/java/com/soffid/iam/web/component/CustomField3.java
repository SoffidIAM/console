package com.soffid.iam.web.component;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.UiException;

import com.soffid.iam.api.DataType;
import es.caib.seycon.ng.comu.TipusDada;
import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.datasource.DataSource;
import es.caib.zkib.events.XPathEvent;
import es.caib.zkib.events.XPathRerunEvent;

public class CustomField3 extends InputField3 {
	private static final long serialVersionUID = 1L;

	String placeholder;
	String dataTypeName;
	Integer maxLength;
	boolean multiValue;
	String[] listOfValues;
	String dataObjectType; 
	String bind;	
	String validationScript;
	String visibilityScript;
	boolean visible = true;
	boolean required = false;
	boolean hideUserName = false;
	boolean raisePrivileges = false;
	boolean noLabel = false;
	String enumeration = null;
	
	private List<TipusDada> dataTypes;

	private DataType dataTypeObj;

	private Object ownerObject;

	private String filterExpression;

	public void updateMetadata() {
		if (dataTypeName != null)
		{
			dataTypeObj = new DataType();
			dataTypeObj.setDataObjectType(dataObjectType);
			dataTypeObj.setMultiValued(multiValue);
			dataTypeObj.setReadOnly(isReadonly());
			dataTypeObj.setScope(null);
			dataTypeObj.setSize(maxLength);
			dataTypeObj.setRequired(required);
			dataTypeObj.setFilterExpression(filterExpression);
			if (listOfValues != null)
				dataTypeObj.setValues(Arrays.asList(listOfValues));
	
			List names = TypeEnumeration.names();
			for ( int i = 0; i < names.size(); i++)
			{
				String name = (String) names.get(i);
				TypeEnumeration type = TypeEnumeration.fromString( (String) TypeEnumeration.literals().get(i) );
				if (type.toString().equals(dataTypeName) ||
						name.equalsIgnoreCase(dataTypeName) ||
						name.toLowerCase().equals(dataTypeName.toLowerCase()+"_type") ) {
					dataTypeObj.setType( type );
				} 
			}
			if (dataTypeObj.getType() == null)
				throw new UiException("Wrong data type "+ dataTypeName+ " for "+toString());
			dataTypeObj.setValidationExpression(validationScript);
			dataTypeObj.setVisibilityExpression(visibilityScript);
			dataTypeObj.setEnumeration(enumeration);
			if (listOfValues != null) {
				List l = new LinkedList<>();
				for (String s: listOfValues) l.add(s);
				dataTypeObj.setValues(l);
			}
			setDisabled(dataTypeObj.isReadOnly());
			setReadonly(dataTypeObj.isReadOnly());
			refresh ();
		}
	}

	public CustomField3(){
		super();
	}
	
	public void onCreate () throws NamingException, CreateException, InternalErrorException, IOException
	{
		updateMetadata();
	}
	
	public void onUpdate(XPathEvent event) 
	{
		try {
			if (event instanceof XPathRerunEvent)
			{
				refresh ();
			}
			super.onUpdate(event);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void refresh() {
		if (dataTypeObj != null)
		{
			setDataType(dataTypeObj);
			try {
				createField();
			} catch (Exception e) {
				throw new UiException(e);
			};
			adjustVisibility();
		}
	}

	public void setDataType(String dataType) {
		this.dataTypeName = dataType;
	}

	public Integer getMaxLength() {
		return maxLength;
	}

	public void setMaxLength(Integer maxLength) {
		this.maxLength = maxLength;
	}

	public boolean isMultiValue() {
		return multiValue;
	}

	public void setMultiValue(boolean multiValue) {
		this.multiValue = multiValue;
		updateMetadata();
	}

	public String[] getListOfValues() {
		return listOfValues;
	}

	public void setListOfValues(String[] listOfValues) {
		this.listOfValues = listOfValues;
	}

	public void setListOfValues(String listOfValues) {
		this.listOfValues = listOfValues.trim().split("\\s*[,]+\\s*");
	}

	public void setReadonly(boolean readonly) {
		if (readonly != isReadonly())
		{
			super.setReadonly(readonly);
			updateMetadata();
		}
	}

	public String getValidationScript() {
		return validationScript;
	}

	public void setValidationScript(String validationScript) {
		this.validationScript = validationScript;
		updateMetadata();
	}

	public String getVisibilityScript() {
		return visibilityScript;
	}

	public void setVisibilityScript(String visibilityScript) {
		this.visibilityScript = visibilityScript;
		updateMetadata();
	}
	
	public Object clone() {
		CustomField3 clone = (CustomField3) super.clone();
		return clone;
	}

	public String getDataObjectType() {
		return dataObjectType;
	}

	public void setDataObjectType(String customObjectType) {
		this.dataObjectType = customObjectType;
	}

	public boolean validate () {
		return attributeValidateAll();
	}

	public Object getOwnerObject() {
		return ownerObject;
	}

	public void setOwnerObject(Object ownerObject) {
		this.ownerObject = ownerObject;
	}
	
	public void adjustVisibility ()
	{
		super.setVisible ( visible && attributeVisible() );
	}

	public boolean setVisible(boolean visible) {
		this.visible = visible;
		return super.setVisible(visible);
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public boolean isHideUserName() {
		return hideUserName;
	}

	public void setHideUserName(boolean hideUserName) {
		this.hideUserName = hideUserName;
	}

	public boolean isRaisePrivileges() {
		return raisePrivileges;
	}

	public void setRaisePrivileges(boolean raisePrivileges) {
		this.raisePrivileges = raisePrivileges;
	}

	public String getFilterExpression() {
		return filterExpression;
	}

	public void setFilterExpression(String filterExpression) {
		this.filterExpression = filterExpression;
		updateMetadata();
	}

	public boolean isNoLabel() {
		return noLabel;
	}

	public void setNoLabel(boolean noLabel) {
		this.noLabel = noLabel;
	}

	public String getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}

	
	public String getEnumeration() {
		return enumeration;
	}

	
	public void setEnumeration(String enumeration) {
		this.enumeration = enumeration;
	}
	
	public void setLabel(String label) {
		super.setLabel(label+" :");
		setPlaceholder(label);
	}
	
	public void afterCompose() {
		updateMetadata();
		super.afterCompose();
	}
	
}