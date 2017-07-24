package com.soffid.iam.web.users.additionalData;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.zk.ui.UiException;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;

import com.soffid.iam.api.DataType;
import com.soffid.iam.api.MetadataScope;

import es.caib.seycon.ng.EJBLocator;
import es.caib.seycon.ng.comu.TipusDada;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.binder.BindContext;
import es.caib.zkib.binder.SingletonBinder;
import es.caib.zkib.datasource.DataSource;
import es.caib.zkib.events.XPathEvent;
import es.caib.zkib.events.XPathRerunEvent;
import es.caib.zkib.events.XPathSubscriber;
import es.caib.zkib.events.XPathValueEvent;

public class AttributesDiv extends Div implements XPathSubscriber, BindContext {

	/**
	 * 
	 */
	
	MetadataScope scope;
	String dataPath;
	boolean readonly;
	
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

	private void updateMetadata() {
		try {
			dataTypes = new LinkedList<TipusDada>(EJBLocator.getDadesAddicionalsService().findDataTypes(this.scope));
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

	private static final long serialVersionUID = 1L;
	
	private SingletonBinder binder = new SingletonBinder(this);

	private List<TipusDada> dataTypes;

	public AttributesDiv(){
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
				refresh ();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void refresh() {
		while (getFirstChild() != null)
			removeChild(getFirstChild());
		Map<String, Object> attributes = (Map<String, Object>) binder.getValue();
		if (attributes != null)
		{
			for (TipusDada att: dataTypes)
			{
				Div d = new Div();
				appendChild(d);
				d.setSclass(getSclass()+"_row");
				Label l = new Label (att.getLabel());
				l.setSclass(getSclass()+"_label");
				d.appendChild(l);
				InputField2 input = new InputField2();
				if (! attributes.containsKey(att.getCodi()))
					attributes.put(att.getCodi(), null);
				input.setBind("[@name='"+att.getCodi()+"']");
				input.setDataType( DataType.toDataType(att));
				input.setSclass(getSclass()+"_input");
				input.setReadonly(readonly);
				d.appendChild(input);
				try {
					input.createField();
				} catch (Exception e) {
					throw new UiException(e);
				};
			}
		}
	}

	
	public DataSource getDataSource() {
		return binder.getDataSource();
	}

	public String getXPath() {
		return binder.getXPath();
	}

}