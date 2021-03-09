package com.soffid.iam.web.popup;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.zkoss.zk.ui.Executions;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.DataType;
import com.soffid.iam.api.MetadataScope;
import com.soffid.iam.web.component.DatatypeColumnsDatatable;
import com.soffid.iam.web.component.DynamicColumnsDatatable;
import com.soffid.iam.web.component.SearchDictionaryBuilder;

import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.seycon.ng.exception.InternalErrorException;


public class FinderDatatable extends DatatypeColumnsDatatable {
	private String className;

	public FinderDatatable() throws Exception {
		className = (String) Executions.getCurrent().getAttribute("className");
		if ( className.startsWith(SearchDictionaryBuilder.COM_SOFFID_IAM_API_CUSTOM_OBJECT) )
			className = className.substring(SearchDictionaryBuilder.COM_SOFFID_IAM_API_CUSTOM_OBJECT.length());
	}
	
	
	public void afterCompose () {
		super.afterCompose();
	}
	
	public Collection<DataType> getDataTypes() throws Exception {
		LinkedList<DataType> l = new LinkedList<DataType>( 
				EJBLocator.getAdditionalDataService().findDataTypesByObjectTypeAndName2(className, null) );
		for (Iterator<DataType> it = l.iterator(); it.hasNext();) {
			DataType dt = it.next();
			if (dt.getType() == TypeEnumeration.SEPARATOR)
				it.remove();
		}
		return l;
	}

	@Override
	public String[] getDefaultColumns() throws Exception {
		LinkedList<String> r = new LinkedList<>();
		LinkedList<DataType> l = new LinkedList<DataType>( 
				EJBLocator.getAdditionalDataService().findDataTypesByObjectTypeAndName2(className, null) );
		for (Iterator<DataType> it = l.iterator(); it.hasNext();) {
			DataType dt = it.next();
			if (dt.getSearchCriteria() != null && dt.getSearchCriteria().booleanValue())
			{
				r.add(dt.getName());
			}
		}
		if (r.isEmpty())
			r.add(l.get(0).getName());
		return r.toArray(new String[r.size()]);
	}


	
	public String getClassName() {
		return className;
	}


	
	public void setClassName(String className) {
		this.className = className;
		if ( className.startsWith(SearchDictionaryBuilder.COM_SOFFID_IAM_API_CUSTOM_OBJECT) )
			this.className = className.substring(SearchDictionaryBuilder.COM_SOFFID_IAM_API_CUSTOM_OBJECT.length());
	}

}
