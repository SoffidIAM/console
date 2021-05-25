package com.soffid.iam.web.popup;

import com.soffid.iam.api.DataType;

public class MergeActionAttribute {
	private DataType dataType;
	private String[] users;
	private boolean allowMerge;

	public MergeActionAttribute(String name, String users[], boolean allowMerge) {
		super();
		this.name = name;
		this.users = users;
		this.allowMerge = allowMerge;
	}

	String name;
	
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


	
	public String[] getUsers() {
		return users;
	}


	
	public void setUsers(String[] users) {
		this.users = users;
	}


	
	public boolean isAllowMerge() {
		return allowMerge;
	}


	
	public void setAllowMerge(boolean allowMerge) {
		this.allowMerge = allowMerge;
	}

}