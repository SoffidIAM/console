package com.soffid.iam.web.users.additionalData;

import java.io.IOException;
import java.io.Writer;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;

import com.soffid.iam.api.Account;
import com.soffid.iam.api.AttributeVisibilityEnum;
import com.soffid.iam.api.DataType;
import com.soffid.iam.api.MetadataScope;
import com.soffid.iam.api.Role;
import com.soffid.iam.api.User;
import com.soffid.iam.model.MetaDataEntity;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.ServiceLocator;
import es.caib.seycon.ng.comu.TipusDada;
import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.seycon.ng.comu.Usuari;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.binder.BindContext;
import es.caib.zkib.binder.CollectionBinder;
import es.caib.zkib.binder.SingletonBinder;
import es.caib.zkib.component.DataListbox;
import es.caib.zkib.datamodel.DataModelNode;
import es.caib.zkib.datamodel.DataNode;
import es.caib.zkib.datasource.DataSource;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.events.XPathEvent;
import es.caib.zkib.events.XPathRerunEvent;
import es.caib.zkib.events.XPathSubscriber;

public class AttributesDiv extends Div implements XPathSubscriber, BindContext {

	/**
	 * 
	 */
	
	MetadataScope scope;
	String system;
	String dataPath;
	boolean readonly;
	String objectType; 
	Object ownerObject;
	String ownerBind;
	String ownerContext;
	private static final long serialVersionUID = 1L;
	
	private SingletonBinder binder = new SingletonBinder(this);
	private SingletonBinder ownerBinder = new SingletonBinder(this);

	private List<TipusDada> dataTypes;

	
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

	public String getScope() {
		return scope.toString();
	}

	public void setScope(String scope) {
		this.scope = MetadataScope.fromString(scope);
		updateMetadata();
	}

	public void updateMetadata() {
		Security.nestedLogin(Security.ALL_PERMISSIONS);
		try {
			if (ownerBind != null) {
				try {
					ownerObject = ownerBinder.getValue();
					if (ownerObject instanceof DataNode)
						ownerObject = ((DataNode) ownerObject).getInstance();
				} catch (Exception e) {
					
				}
			}
			if (scope == null)
				return;
			else if (scope == MetadataScope.CUSTOM)
			{
				if (objectType == null || objectType.trim().isEmpty())
					return;
				dataTypes = new LinkedList<TipusDada>(ServiceLocator.instance().getDadesAddicionalsService().findDataTypesByObjectTypeAndName(objectType, null));
			}
			else if (scope == MetadataScope.CUSTOM)
			{
				if (objectType == null || objectType.trim().isEmpty())
					return;
				dataTypes = new LinkedList<TipusDada>(ServiceLocator.instance().getDadesAddicionalsService().findDataTypesByObjectTypeAndName(objectType, null));
			}
			else if (scope == MetadataScope.ACCOUNT)
			{
				String system = this.system;
				if (ownerObject != null && ownerObject instanceof Account)
				{
					system = ((Account)ownerObject).getSystem();
				}
				if (ownerObject != null && ownerObject instanceof es.caib.seycon.ng.comu.Account)
				{
					system = ((es.caib.seycon.ng.comu.Account)ownerObject).getDispatcher();
				}
						
				if (system == null || system.trim().isEmpty())
					return;
				List<TipusDada> systemDataTypes = ServiceLocator.instance().getDadesAddicionalsService().findSystemDataTypes(system);
				dataTypes = systemDataTypes == null ? new LinkedList<TipusDada>():  new LinkedList<TipusDada>(systemDataTypes);
			}
			else
				dataTypes = new LinkedList<TipusDada>(ServiceLocator.instance().getDadesAddicionalsService().findDataTypes(this.scope));
			Collections.sort(dataTypes, new Comparator<TipusDada>() {
				public int compare(TipusDada o1, TipusDada o2) {
					return o1.getOrdre().compareTo(o2.getOrdre());
				}
			});
		} catch (Exception e) {
			throw new UiException(e);
		} finally {
			Security.nestedLogoff();
		}
		refresh ();
	}

	public AttributesDiv(){
		super();
	}
	
	public void onCreate () throws NamingException, CreateException, InternalErrorException, IOException
	{
		deferUpdate();
	}
	
	public void onUpdate(XPathEvent arg0) 
	{
		try {
			if (arg0 instanceof XPathRerunEvent)
			{
				if (scope == MetadataScope.ACCOUNT)
					updateMetadata();
				else
				{
					if (ownerBind != null) {
						ownerObject = ownerBinder.getValue();
						if (ownerObject instanceof DataNode)
							ownerObject = ((DataNode) ownerObject).getInstance();
					}
					refresh ();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void refresh() {
		// Mind binder.getValue can trigger the onUpdate event
		Map<String, Object> attributes = (Map<String, Object>) binder.getValue();
		while (getFirstChild() != null)
			removeChild(getFirstChild());
		if (attributes == null)
		{
			attributes = new HashMap<String, Object>();
			try {
				binder.setValue(attributes);
			} catch (Exception e) {
				return;
			}
		}
		if (attributes != null)
		{
			List<InputField2> inputFields  = new LinkedList<InputField2>();
			for (TipusDada att: dataTypes)
			{
				AttributeVisibilityEnum v = getVisibility (att);
				if (v != AttributeVisibilityEnum.HIDDEN)
				{
					Div d = new Div();
					appendChild(d);
					d.setSclass(getSclass()+"_row");
					Label l = new Label (att.getLabel());
					if ( att.getType() == TypeEnumeration.SEPARATOR)
						l.setSclass(getSclass()+"_label separator_label");
					else
						l.setSclass(getSclass()+"_label");
					d.appendChild(l);
					InputField2 input = new InputField2();
					if (! attributes.containsKey(att.getCodi()))
						attributes.put(att.getCodi(), null);
					input.setBind("[@name='"+att.getCodi()+"']");
					input.setDataType( DataType.toDataType(att));
					input.setSclass(getSclass()+"_input");
					input.setReadonly(readonly || v == AttributeVisibilityEnum.READONLY);
					input.setOwnerObject(ownerObject);
					input.setOwnerContext(ownerContext);
					d.appendChild(input);
					try {
						input.createField();
						inputFields.add(input);
					} catch (Exception e) {
						throw new UiException(e);
					};
					input.addEventListener("onChange", new EventListener() {
						public void onEvent(Event event) throws Exception {
							adjustVisibility();
							
						}
					});
					d.setVisible(input.attributeVisible());
				}
			}
			for ( InputField2 input: inputFields)
				input.runOnLoadTrigger();
		}
	}

	private AttributeVisibilityEnum getVisibility(TipusDada tda) {
		if (Security.isUserInRole(Security.AUTO_METADATA_UPDATE_ALL))
			return AttributeVisibilityEnum.EDITABLE;

		String user = null;
		Object obj = getOwnerObject();
		if (obj == null)
			obj = ownerBinder.getValue();
		if (obj != null && obj instanceof User)
			user = ((User)obj).getUserName();
		else if (obj != null && obj instanceof Usuari)
			user = ((Usuari)obj).getCodi();
		
		if (user != null)
		{
			String currentUser = Security.getCurrentUser();
			if (currentUser != null && currentUser.equals(user))
					return tda.getUserVisibility() == null ? AttributeVisibilityEnum.HIDDEN
							: tda.getUserVisibility();
			if (Security.isUserInRole(Security.AUTO_METADATA_UPDATE_ALL))
				return AttributeVisibilityEnum.EDITABLE;
			else if (Security.isUserInRole(Security.AUTO_AUTHORIZATION_ALL))
				return tda.getAdminVisibility() == null ? AttributeVisibilityEnum.EDITABLE : tda.getAdminVisibility();
			else if (Security.isUserInRole(Security.AUTO_USER_METADATA_UPDATE))
				return tda.getOperatorVisibility() == null ? AttributeVisibilityEnum.EDITABLE : tda.getOperatorVisibility();
			else 
			{
				AttributeVisibilityEnum v = tda.getOperatorVisibility() == null ? AttributeVisibilityEnum.READONLY
						: tda.getOperatorVisibility();
				if (AttributeVisibilityEnum.EDITABLE.equals(v))
					v = AttributeVisibilityEnum.READONLY;
				return v;
			} 
		}
		else 
			return AttributeVisibilityEnum.EDITABLE ;
	}

	public void adjustVisibility() {
		for (Div d : (Collection<Div>)getChildren())
		{
			InputField2 input = (InputField2) d.getFirstChild().getNextSibling();
			input.setOwnerObject(ownerObject);
			input.setOwnerContext(ownerContext);
			d.setVisible(input.attributeVisible());
		}
	}

	
	public DataSource getDataSource() {
		return binder.getDataSource();
	}

	public String getXPath() {
		return binder.getXPath();
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
		updateMetadata();
	}

	public Object getOwnerObject() {
		return ownerObject;
	}

	public void setOwnerObject(Object ownerObject) {
		this.ownerObject = ownerObject;
	}

	public String getOwnerBind() {
		return ownerBind;
	}

	public void setOwnerBind(String ownerBind) {
		this.ownerBind = ownerBind;
		try {
			ownerBinder.setDataPath(ownerBind);
		} catch (Exception e) {}
	}

	public String getOwnerContext() {
		return ownerContext;
	}

	public void setOwnerContext(String ownerContext) {
		this.ownerContext = ownerContext;
	}

	public void validate() {
		for (Div d : (Collection<Div>)getChildren())
		{
			if (d.isVisible())
			{
				InputField2 input = (InputField2) d.getFirstChild().getNextSibling();
				input.setOwnerObject(ownerObject);
				input.setOwnerContext(ownerContext);
				if (!input.attributeValidateAll())
				{
					setFocus(true);
					throw new WrongValueException(this, "Value not accepted");					
				}
			}
		}
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String accountSystem) {
		this.system = accountSystem;
	}

    public Object clone() {
        AttributesDiv clone = (AttributesDiv) super.clone();
        clone.binder = new SingletonBinder(clone);
        clone.ownerBinder = new SingletonBinder(clone);
        clone.setDataPath(binder.getDataPath());
        clone.setOwnerBind(ownerBinder.getDataPath());
        clone.initListener = null;
        clone.deferUpdate();
        return clone;
    }

    public void setPage(Page page) {
        super.setPage(page);
        binder.setPage(page);
        ownerBinder.setPage(page);
		deferUpdate();
    }

    public void setParent(Component parent) {
        super.setParent(parent);
        binder.setParent(parent);
        ownerBinder.setParent(parent);
		deferUpdate();
    }

    
    OnInitEventListener initListener = null;
	private void deferUpdate() {
		if (initListener == null)
		{
			initListener = new OnInitEventListener();
			addEventListener("onInitData", initListener);
		}
		Events.postEvent(new Event("onInitData", this));
	}

	class OnInitEventListener implements EventListener {
		@Override
		public void onEvent(Event event) throws Exception {
			ownerBinder.invalidate();
			binder.invalidate();
			updateMetadata();
		}
		
	}

	public Map<String,InputField2> getInputFieldsMap() {
		Map<String, InputField2> r = new HashMap<String, InputField2>();
		for (Component c : (Collection<Component>)getChildren())
		{
			if (c instanceof Div)
			{
				Component cc = c.getFirstChild().getNextSibling();
				if (cc instanceof InputField2) {
					InputField2 inputField2 = (InputField2) cc;
					DataType dt = inputField2.getDataType();
					if (dt != null && dt.getCode() != null)
						r.put(dt.getCode(), inputField2);
				}
			}
		}
		return r;
	}
}
