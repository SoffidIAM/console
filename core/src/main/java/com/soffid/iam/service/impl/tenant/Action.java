package com.soffid.iam.service.impl.tenant;

import java.io.Serializable;

import com.soffid.tools.db.schema.Table;

public class Action implements Serializable {
	public Action(Operation operation, Table table) {
		super();
		this.operation = operation;
		this.table = table;
	}
	
	enum Operation {
		EXPORT_FULL,
		EXPORT_NOFK,
		EXPORT_FK
	};
	
	Operation operation;
	
	Table table;
	
	public Table getTable() {
		return table;
	}
	public void setTable(Table table) {
		this.table = table;
	}
	public Operation getOperation() {
		return operation;
	}
	public void setOperation(Operation operation) {
		this.operation = operation;
	}
}
