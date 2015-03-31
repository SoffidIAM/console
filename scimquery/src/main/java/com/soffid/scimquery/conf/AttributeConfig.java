package com.soffid.scimquery.conf;

public class AttributeConfig {

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public String getHibernateColumn() {
		return hibernateColumn;
	}

	public void setHibernateColumn(String hibernateColumn) {
		this.hibernateColumn = hibernateColumn;
	}

	String attributeName;
	
	String hibernateColumn;
	
	Class<?> scimType;

	public Class<?> getScimType() {
		return scimType;
	}

	public void setScimType(Class<?> scimType) {
		this.scimType = scimType;
	}

}
