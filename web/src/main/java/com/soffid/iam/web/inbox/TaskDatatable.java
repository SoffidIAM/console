package com.soffid.iam.web.inbox;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.DataType;
import com.soffid.iam.api.System;
import com.soffid.iam.bpm.api.ProcessInstance;
import com.soffid.iam.web.component.DatatypeColumnsDatatable;

import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.zkdb.yaml.Yaml2Json;


public class TaskDatatable extends DatatypeColumnsDatatable {
	HashMap<String, System> systems = new HashMap<>();
	String customColumns = null;

	static String[] defaultColumns = {
			"processId", "processName", "task", "date", "duedate", "actor"
	};
	
	public String getCustomColumns() throws Exception {
		if (customColumns == null)
			return null;
		else
			return new Yaml2Json().transform(customColumns);
	}

	public TaskDatatable() throws Exception {
	}
	
	public Collection<DataType> getDataTypes() throws Exception {
		HashSet<String> names = new HashSet<>();
		LinkedList<DataType> l = new LinkedList<DataType>( 
				EJBLocator.getAdditionalDataService().findDataTypesByObjectTypeAndName2(ProcessInstance.class.getName(), null));
		for (Iterator<DataType> it = l.iterator(); it.hasNext();) {
			DataType dt = it.next();
			names.add(dt.getName());
			if (dt.getType() == TypeEnumeration.SEPARATOR || Boolean.TRUE.equals(dt.getBuiltin()))
				it.remove();
		}
		
		
		return l;
	}

	@Override
	public String[] getDefaultColumns() throws Exception {
		return defaultColumns;
	}

	
	public void setCustomColumns(String customColumns) {
		this.customColumns = customColumns;
	}

}
