package com.soffid.iam.web.component;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.apache.commons.beanutils.PropertyUtils;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Div;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.Account;
import com.soffid.iam.api.AttributeVisibilityEnum;
import com.soffid.iam.api.DataType;
import com.soffid.iam.utils.Security;
import com.soffid.iam.web.WebDataType;

import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.binder.BindContext;
import es.caib.zkib.binder.SingletonBinder;
import es.caib.zkib.datamodel.xml.XmlDataNode;
import es.caib.zkib.datasource.DataSource;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.events.XPathEvent;
import es.caib.zkib.events.XPathRerunEvent;
import es.caib.zkib.events.XPathSubscriber;

public class ObjectAttributesDiv extends Div implements XPathSubscriber, BindContext {

	/**
	 * 
	 */
	
	String dataPath;
	boolean readonly;
	Object ownerObject;
	String ownerContext;
	String objectType;
	String system; 
	boolean hidebuiltin;
	private static final long serialVersionUID = 1L;
	List<InputField3> fields = new LinkedList<>();
	
	private SingletonBinder binder = new SingletonBinder(this);

	private List<DataType> dataTypes;

	
	public boolean isReadonly() {
		return readonly;
	}

	public void setReadonly(boolean readonly) {
		this.readonly = readonly;
	}

	public String getDataPath() {
		return dataPath;
	}
	
	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
		binder.setDataPath(dataPath);
	}

	public void updateMetadata() {
		try {
			if (Account.class.getName().equals(objectType)) {
				try {
					system = (String) XPathUtils.getValue((Component) this, "@system");
				} catch (Exception e) {}
			}
			
			if (objectType != null)
				dataTypes = new LinkedList<DataType>(
					EJBLocator.getAdditionalDataService()
					.findDataTypesByObjectTypeAndName2(objectType, null));
			else
				dataTypes = new LinkedList<>();
			
			Collections.sort(dataTypes, new Comparator<DataType>() {
				public int compare(DataType o1, DataType o2) {
					return o1.getOrder().compareTo(o2.getOrder());
				}
			});
			if (system != null) {
				List<DataType> dataTypes2 = 
						EJBLocator.getAdditionalDataService()
						.findSystemDataTypes(system);
				if (dataTypes2 != null) {
					Collections.sort(dataTypes2, new Comparator<DataType>() {
						public int compare(DataType o1, DataType o2) {
							return o1.getOrder().compareTo(o2.getOrder());
						}
					});
					if (!dataTypes2.isEmpty() && dataTypes2.get(0).getType() != TypeEnumeration.SEPARATOR)
					{
						DataType dt = new DataType();
						dt.setType(TypeEnumeration.SEPARATOR);
						dt.setLabel(Labels.getLabel("com.soffid.iam.api.Account._custom"));
						dataTypes.add(dt);
					}
					dataTypes.addAll(dataTypes2);
				}
			}
		} catch (Exception e) {
			throw new UiException(e);
		}
		refresh ();
	}

	public ObjectAttributesDiv(){
		super();
	}
	
	public void onCreate () throws NamingException, CreateException, InternalErrorException, IOException
	{
		updateMetadata();
	}


	public void onUpdate(XPathEvent event) 
	{
		try {
			if (binder.isValid() && binder.getValue() != null) {
				if (event instanceof XPathRerunEvent)
				{
					updateMetadata();
				}
				else
				{
					adjustVisibility();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new UiException("Error generating user interface", e);
		}
	}
	
	boolean recursive = false;
	public void refresh() {
		if (recursive)
			return;
		recursive = true ;
		Div section = null;
		boolean emptySection = true;
		try {
			while (getFirstChild() != null)
				removeChild(getFirstChild());
			fields.clear();
			ownerObject = binder.getValue();
			if (ownerObject != null)
			{
				String userName = null;
				try {
					userName = (String) PropertyUtils.getProperty(ownerObject, "userName");
				} catch (Exception e) {}
				String currentUser = Security.getCurrentUser();
				List<InputField3> inputFields = new LinkedList<InputField3>();
				for (DataType att: dataTypes)
				{
					if (Boolean.FALSE.equals(att.getBuiltin()) || ! hidebuiltin) {
						InputField3 inputField = new InputField3();
						WebDataType webDataType = new WebDataType( att );
						inputField.setDataType( webDataType );
						if ( att.getBuiltin() != null && att.getBuiltin().booleanValue())
							inputField.setBind(att.getName());
						else
							inputField.setBind("/attributes[@name='"+att.getName()+"']");
						inputField.setReadonly(readonly);
						inputField.setOwnerObject(ownerObject);
						if ( att.getType() == TypeEnumeration.SEPARATOR) {
							if (section != null && emptySection)
								section.setVisible(false);
							section = new Div();
							section.setSclass("section");
							appendChild(section);
							emptySection = true;
						}
						if (section == null)
							appendChild(inputField);
						else
							section.appendChild(inputField);
						inputField.afterCompose();
						try {
							inputField.createField();
							inputFields.add(inputField);
						} catch (Exception e) {
							throw new UiException(e);
						};
						inputField.addEventListener("onChange", new EventListener() {
							public void onEvent(Event event) throws Exception {
								adjustVisibility();
								
							}
						});
						boolean visible = inputField.attributeVisible();
						if (att.getCustomObjectType().equals("com.soffid.iam.api.User")) {
							AttributeVisibilityEnum vis = 
								currentUser != null && currentUser.equals(userName) ? att.getUserVisibility() : 
								Security.isUserInRole(Security.AUTO_AUTHORIZATION_ALL) ? att.getAdminVisibility() :
								att.getOperatorVisibility();
							if (vis == AttributeVisibilityEnum.HIDDEN)
								visible = false;
							else if (vis == AttributeVisibilityEnum.READONLY)
								inputField.setReadonly(true);
						}
						inputField.setVisible(visible);
						if ( att.getType() == TypeEnumeration.SEPARATOR) {
							if (!visible) section.setVisible(false);
						} else {
							if (visible) emptySection = false;
						}
						fields.add(inputField);
						
					}
				}
				for ( InputField3 input: inputFields)
					input.runOnLoadTrigger();
			}
		} finally {
			recursive = false;
		}
	}

	public void adjustVisibility() {
		InputField3 container = null;
		boolean visible = false;
		for (InputField3 input : fields)
		{
			if (input.getDataType() != null && input.getDataType().getType() == TypeEnumeration.SEPARATOR) {
				if (container != null) {
					container.setVisible(visible);
					container.getParent().setVisible(visible);
				}
				container = input;
				visible = false;
			}
			input.setOwnerObject(ownerObject); 
			input.setOwnerContext(ownerContext);
			boolean v = input.attributeVisible();
			if (input != container && container != null && v) visible = true;
			input.setVisible(v);
		}
		if (container != null) {
			container.setVisible(visible);
			container.getParent().setVisible(visible);
		}
	}
	
	public DataSource getDataSource() {
		return binder.getDataSource();
	}

	public String getXPath() {
		return binder.getXPath();
	}

	public Object getOwnerObject() {
		return ownerObject;
	}

	public void setOwnerObject(Object ownerObject) {
		this.ownerObject = ownerObject;
	}

	public String getOwnerContext() {
		return ownerContext;
	}

	public void setOwnerContext(String ownerContext) {
		this.ownerContext = ownerContext;
	}

	public boolean validate() {
		boolean ok = true;
		for (InputField3 input : fields)
		{
			if (input.isVisible())
			{
				input.setOwnerObject(ownerObject);
				input.setOwnerContext(ownerContext);
				if (!input.attributeValidateAll())
				{
					if (ok) input.setFocus(true);
					ok = false;
				}
			}
		}
		return ok;
	}

	public Map<String,Object> getAttributesMap ()
	{
		Object obj = binder.getValue();
		try {
			return (Map<String, Object>) PropertyUtils.getProperty(obj, "attributes");
		} catch (Exception e) {
			return null;
		}
	}

	public Map<String,InputField3> getInputFieldsMap() {
		Map<String, InputField3> r = new HashMap<String, InputField3>();
		for (Component c : fields)
		{
			if (c instanceof InputField3) {
				InputField3 inputField3 = (InputField3) c;
				DataType dt = inputField3.getDataType();
				if (dt != null && dt.getCode() != null)
					r.put(dt.getCode(), (InputField3) c);
			}
		}
		return r;
	}

	
	public String getObjectType() {
		return objectType;
	}

	
	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	
	public String getSystem() {
		return system;
	}

	
	public void setSystem(String system) {
		this.system = system;
	}

	
	public List<DataType> getDataTypes() {
		return dataTypes;
	}

	
	public void setDataTypes(List<DataType> dataTypes) {
		this.dataTypes = dataTypes;
	}

	public DataType getDataType(String name) {
		if (dataTypes == null) return null;
		for (DataType dt: dataTypes)
			if (dt.getName().equals(name))
				return dt;
		return null;
	}

	
	public boolean isHidebuiltin() {
		return hidebuiltin;
	}

	
	public void setHidebuiltin(boolean hidebuiltin) {
		this.hidebuiltin = hidebuiltin;
	}
}