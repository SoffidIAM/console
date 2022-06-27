package com.soffid.iam.web.syncserver;

import com.soffid.iam.web.component.DynamicColumnsDatatable;


public class TasksGridDatatable extends DynamicColumnsDatatable {
	static String mandatory[] = new String[] {"task"}; 
	@Override
	public String[] getMandatoryColumns() throws Exception {
		return mandatory;
	}

}
