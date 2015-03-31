package com.soffid.scimquery.conf;

import java.util.Map;

public class ClassConfig {
	Class clazz;
	
	Class hibernateClass;
	
	public Class getHibernateClass() {
		return hibernateClass;
	}

	public void setHibernateClass(Class hibernateClass) {
		this.hibernateClass = hibernateClass;
	}

	Map<String, AttributeConfig> attributes;

	public Class getClazz() {
		return clazz;
	}

	public void setClazz(Class clazz) {
		this.clazz = clazz;
	}

	public Map<String, AttributeConfig> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, AttributeConfig> attributes) {
		this.attributes = attributes;
	}
}
