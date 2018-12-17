package com.soffid.iam.web.users.additionalData;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;

import com.soffid.iam.api.DataType;
import com.soffid.iam.api.MetadataScope;

import es.caib.seycon.ng.EJBLocator;
import es.caib.seycon.ng.comu.DadaUsuari;
import es.caib.seycon.ng.comu.TipusDada;
import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.binder.BindContext;
import es.caib.zkib.binder.SingletonBinder;
import es.caib.zkib.datamodel.DataModelNode;
import es.caib.zkib.datamodel.DataNode;
import es.caib.zkib.datamodel.xml.XmlDataNode;
import es.caib.zkib.datasource.DataSource;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.events.XPathEvent;
import es.caib.zkib.events.XPathRerunEvent;
import es.caib.zkib.events.XPathSubscriber;

public class UserAttributesDiv extends Div implements XPathSubscriber, BindContext {

	/**
	 * 
	 */
	
	String dataPath;
	boolean readonly;
	Object ownerObject;
	String ownerBind;
	String ownerContext;
	private static final long serialVersionUID = 1L;
	
	private SingletonBinder binder = new SingletonBinder(this);

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

	private void updateMetadata() {
		try {
			dataTypes = new LinkedList<TipusDada>(EJBLocator.getDadesAddicionalsService().findDataTypes(MetadataScope.USER));
			Collections.sort(dataTypes, new Comparator<TipusDada>() {
				public int compare(TipusDada o1, TipusDada o2) {
					return o1.getOrdre().compareTo(o2.getOrdre());
				}
			});
		} catch (Exception e) {
			throw new UiException(e);
		}
		refresh ();
	}

	public UserAttributesDiv(){
		super();
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
				if (ownerBind != null) {
					ownerObject = XPathUtils.getValue(getParent(), ownerBind);
					if (ownerObject instanceof DataNode)
						ownerObject = ((DataNode) ownerObject).getInstance();
				}
				refresh ();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	boolean recursive = false;
	private void refresh() {
		if (recursive)
			return;
		recursive = true ;
		try {
			while (getFirstChild() != null)
				removeChild(getFirstChild());
			List<XmlDataNode> attributes = (List<XmlDataNode>) binder.getValue();
			if (attributes != null)
			{
				for (TipusDada att: dataTypes)
				{
					// Find Datatype
					int position = 0;
					boolean found = false;
					DadaUsuari attValue = null;
					for ( Iterator<XmlDataNode> it = attributes.iterator(); it.hasNext();)
					{
						position ++;
						attValue = (DadaUsuari) it.next().getInstance();
						if (attValue.getCodiDada().equals(att.getCodi()))
						{
							Div d = new Div();
							appendChild(d);
							d.setSclass(getSclass()+"_row");
							Label l = new Label (att.getLabel());
							l.setSclass(getSclass()+"_label");
							d.appendChild(l);
							InputField2 input = new InputField2();
							if (att.getType() == TypeEnumeration.DATE_TYPE)
								input.setBind("["+position+"]/valorDadaDate");
							else
								input.setBind("["+position+"]/valorDada");
							input.setDataType( DataType.toDataType(att));
							input.setSclass(getSclass()+"_input");
							input.setReadonly(readonly);
							input.setOwnerObject(ownerObject);
							d.appendChild(input);
							try {
								input.createField();
							} catch (Exception e) {
								throw new UiException(e);
							};
							input.addEventListener("onChange", new EventListener() {
								public void onEvent(Event event) throws Exception {
									adjustVisibility();
									
								}
							});
							d.setVisible(input.attributeVisible());
							break;
						}
					}
				}
			}
		} finally {
			recursive = false;
		}
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

	public Map<String,Object> getAttributesMap ()
	{
		Map<String, Object> m = new HashMap<String, Object>();
		List<XmlDataNode> attributes = (List<XmlDataNode>) binder.getValue();
		if (attributes != null)
		{
			for ( Iterator<XmlDataNode> it = attributes.iterator(); it.hasNext();)
			{
				DadaUsuari attValue = (DadaUsuari) it.next().getInstance();
				if (attValue.getValorDadaDate() == null)
					m.put(attValue.getCodiDada(), attValue.getValorDada());
				else
					m.put(attValue.getCodiDada(), attValue.getValorDada());
			}
		}
		return m ;
	}
}