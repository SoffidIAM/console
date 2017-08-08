package com.soffid.scimquery.conf;

public class AttributeConfig {
	String attributeName;
	
	String hibernateColumn;
	
	Class<?> scimType;
	
	boolean virtualAttribute = false;
	String virtualAttributeName;
	String virtualAttributeValue;

	
	public boolean isVirtualAttribute() {
		return virtualAttribute;
	}

	public void setVirtualAttribute(boolean virtualAttribute) {
		this.virtualAttribute = virtualAttribute;
	}

	public String getVirtualAttributeName() {
		return virtualAttributeName;
	}

	public void setVirtualAttributeName(String hibernateJoin) {
		this.virtualAttributeName = hibernateJoin;
	}

	public String getVirtualAttributeValue() {
		return virtualAttributeValue;
	}

	public void setVirtualAttributeValue(String valueColumn) {
		this.virtualAttributeValue = valueColumn;
	}

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

	public Class<?> getScimType() {
		return scimType;
	}

	public void setScimType(Class<?> scimType) {
		this.scimType = scimType;
	}

}
