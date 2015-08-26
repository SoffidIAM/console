package com.soffid.iam.model;

public class Parameter {

	public java.lang.String getName() {
		return name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.Object getValue() {
		return value;
	}

	public void setValue(java.lang.Object value) {
		this.value = value;
	}

	public java.lang.String name;

	public java.lang.Object value;

	public Parameter(java.lang.String name, java.lang.Object value) {
		this.name = name;
		this.value = value;
	}

	public Parameter() {
	}
}
