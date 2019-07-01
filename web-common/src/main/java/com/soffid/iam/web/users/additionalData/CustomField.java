package com.soffid.iam.web.users.additionalData;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.HtmlBasedComponent;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.impl.InputElement;

import com.soffid.iam.api.DataType;
import com.soffid.iam.api.MetadataScope;

import es.caib.seycon.ng.comu.TipusDada;
import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.binder.SingletonBinder;
import es.caib.zkib.datasource.DataSource;
import es.caib.zkib.events.XPathEvent;
import es.caib.zkib.events.XPathRerunEvent;
import es.caib.zkib.events.XPathSubscriber;

public class CustomField extends Div implements XPathSubscriber {
	private static final long serialVersionUID = 1L;

	String dataType;
	String label;
	Integer maxLength;
	boolean multiValue;
	String[] listOfValues;
	boolean readonly;
	String dataObjectType; 
	String bind;	
	String validationScript;
	String visibilityScript;
	private SingletonBinder binder = new SingletonBinder(this);
	private SearchFilter searchFilter;
	boolean visible = true;
	boolean required = false;
	boolean hideUserName = false;
	boolean raisePrivileges = false;
	
	private List<TipusDada> dataTypes;

	private DataType dataTypeObj;

	private InputField2 input;

	private Object ownerObject;

	private String filterExpression;

	public void updateMetadata() {
		dataTypeObj = new DataType();
		dataTypeObj.setDataObjectType(dataObjectType);
		dataTypeObj.setLabel(label);
		dataTypeObj.setMultiValued(multiValue);
		dataTypeObj.setRequired(false);
		dataTypeObj.setScope(null);
		dataTypeObj.setSize(maxLength);
		dataTypeObj.setType(TypeEnumeration.STRING_TYPE);
		dataTypeObj.setRequired(required);
		dataTypeObj.setFilterExpression(filterExpression);
		if (listOfValues != null)
			dataTypeObj.setValues(Arrays.asList(listOfValues));

		List names = TypeEnumeration.names();
		for ( int i = 0; i < names.size(); i++)
		{
			String name = (String) names.get(i);
			TypeEnumeration type = TypeEnumeration.fromString( (String) TypeEnumeration.literals().get(i) );
			if (type.toString().equals(dataType) ||
					name.equalsIgnoreCase(dataType) ||
					name.toLowerCase().equals(dataType.toLowerCase()+"_type") ) {
				dataTypeObj.setType( type );
			} 
		}
		dataTypeObj.setValidationExpression(validationScript);
		dataTypeObj.setVisibilityExpression(visibilityScript);
		refresh ();
	}

	public CustomField(){
		super();
		setSclass("inputField");
	}
	
	public void onCreate () throws NamingException, CreateException, InternalErrorException, IOException
	{
		updateMetadata();
	}
	
	public void onUpdate(XPathEvent arg0) 
	{
		try {
			if (arg0 instanceof XPathRerunEvent)
			{
				refresh ();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void refresh() {
		if (dataTypeObj != null)
		{
			while (getFirstChild() != null)
			{
				HtmlBasedComponent v = (HtmlBasedComponent) getFirstChild();
				if ( v.getSclass() != null && v.getSclass().contains("inputField_tail"))
						break;
				removeChild(v);
			}
			Component last = getFirstChild();
	
			Label l = new Label(label);
			if ( dataTypeObj.getType() == TypeEnumeration.SEPARATOR)
				l.setSclass(getSclass()+"_label separator_label");
			else
				l.setSclass(getSclass()+"_label");
			insertBefore(l, last);
			input = new InputField2();
			insertBefore(input, last);
			input.setDataType(dataTypeObj);
			input.setSclass(getSclass()+"_input");
			input.setReadonly(readonly);
			input.setBind(binder.getDataPath());
			input.setSearchFilter(searchFilter);
			input.setOwnerObject(ownerObject);
			input.setHideUserName(hideUserName);
			input.setRaisePrivileges(raisePrivileges);
			try {
				input.createField();
			} catch (Exception e) {
				throw new UiException(e);
			};
			final CustomField inputField3 = this;
			input.addEventListener("onChange", new EventListener() {
				public void onEvent(Event event) throws Exception {
					Events.postEvent(new Event("onChange", inputField3));
				}
			});
			adjustVisibility();
		}
	}

	public DataSource getDataSource() {
		return binder.getDataSource();
	}

	public String getXPath() {
		return binder.getXPath();
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
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

	public boolean isReadonly() {
		return readonly;
	}

	public void setReadonly(boolean readonly) {
		if (readonly != this.readonly)
		{
			this.readonly = readonly;
			refresh();
		}
	}

	public String getValidationScript() {
		return validationScript;
	}

	public void setValidationScript(String validationScript) {
		this.validationScript = validationScript;
	}

	public String getVisibilityScript() {
		return visibilityScript;
	}

	public void setVisibilityScript(String visibilityScript) {
		this.visibilityScript = visibilityScript;
	}
	
	public void setBind (String bind)
	{
		binder.setDataPath(bind);
		refresh();
	}

	public String getBind ()
	{
		return binder.getDataPath();
	}

	public void setPage(Page page) {
		super.setPage(page);
		binder.setPage(page);
		refresh();
	}
	
	public void setOldValue() {
		binder.setOldValue();
	}

	public void setParent(Component parent) {
		super.setParent(parent);
		binder.setParent(parent);
		refresh();
	}

	public Object clone() {
		CustomField clone = (CustomField) super.clone();
		clone.binder = new SingletonBinder (clone);
		clone.binder.setDataPath(binder.getDataPath());
		return clone;
	}

	public SearchFilter getSearchFilter() {
		return searchFilter;
	}

	public void setSearchFilter(SearchFilter searchFilter) {
		this.searchFilter = searchFilter;
	}

	public String getDataObjectType() {
		return dataObjectType;
	}

	public void setDataObjectType(String customObjectType) {
		this.dataObjectType = customObjectType;
	}

	public InputElement getInputElement () {
		for (Component c: (List<Component>) getChildren())
		{
			InputElement ie = findInputElementOnChild (c);
			if (ie != null)
				return ie;
		}
		return null;
	}

	private InputElement findInputElementOnChild(Component c) {
		if (c instanceof InputElement)
			return (InputElement) c;
		for (Component child: (List<Component>) c.getChildren())
		{
			InputElement ie = findInputElementOnChild (child);
			if (ie != null)
				return ie;
		}
		return null;
		
	}

	public Object getValue() {
		return binder.getValue();
	}
	
	public boolean validate () {
		return input.attributeValidateAll();
	}

	public Object getOwnerObject() {
		return ownerObject;
	}

	public void setOwnerObject(Object ownerObject) {
		this.ownerObject = ownerObject;
	}
	
	public void adjustVisibility ()
	{
		super.setVisible ( visible && input.attributeVisible() );
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
	}
}