package com.soffid.iam.web.popup;

import com.soffid.iam.api.DataType;

public class BulkActionAttribute {
	private DataType dataType;

	public BulkActionAttribute(String name, DataType dataType, BulkActionAttributeAction[] actions) {
		super();
		if (Boolean.TRUE.equals(dataType.getBuiltin()))
			this.name = name;
		else
			this.name = "ca$"+name;
		this.dataType = dataType;
		this.actions = actions;
	}

	String name;
	BulkActionAttributeAction actions[];
	
	public BulkActionAttributeAction[] getActions() {
		return actions;
	}
	
	public void setActions(BulkActionAttributeAction[] actions) {
		this.actions = actions;
	}

	
	public DataType getDataType() {
		return dataType;
	}

	
	public void setDataType(DataType dataType) {
		this.dataType = dataType;
	}

	
	public String getName() {
		return name;
	}

	
	public void setName(String name) {
		this.name = name;
	}

}