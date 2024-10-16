package com.soffid.iam.web.bpm.process;

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


public class ProcessDatatable extends DatatypeColumnsDatatable {
	HashMap<String, System> systems = new HashMap<>();

	static String[] defaultColumns = {
			"id", "description", "currentTask", "currentTask", "start", "end"
	};
	
	public ProcessDatatable() throws Exception {
	}
	
	public Collection<DataType> getDataTypes() throws Exception {
		HashSet<String> names = new HashSet<>();
		LinkedList<DataType> l = new LinkedList<DataType>( 
				EJBLocator.getAdditionalDataService().findDataTypesByObjectTypeAndName2(ProcessInstance.class.getName(), null));
		for (Iterator<DataType> it = l.iterator(); it.hasNext();) {
			DataType dt = it.next();
			names.add(dt.getName());
			if (dt.getType() == TypeEnumeration.SEPARATOR)
				it.remove();
		}
		
		
		return l;
	}

	@Override
	public String[] getDefaultColumns() throws Exception {
		return defaultColumns;
	}

}
