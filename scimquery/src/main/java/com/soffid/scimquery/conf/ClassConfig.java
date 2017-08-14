package com.soffid.scimquery.conf;

import java.util.HashMap;
import java.util.Map;

public class ClassConfig {
	String clazz;
	
	String hibernateClass;
	
	Class beanClass;
	
	Map<String, AttributeConfig> attributes = new HashMap<String, AttributeConfig>();

	AttributeConfig defaultVirtualAttribute;

	
	public AttributeConfig getAttribute (String attName)
	{
		if (attributes.containsKey(attName))
			return attributes.get(attName);
		else
			return defaultVirtualAttribute;
					
	}
	
	public String getHibernateClass() {
		return hibernateClass;
	}

	public void setHibernateClass(String hibernateClass) {
		this.hibernateClass = hibernateClass;
	}


	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public Map<String, AttributeConfig> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, AttributeConfig> attributes) {
		this.attributes = attributes;
	}
	public AttributeConfig getDefaultVirtualAttribute() {
		return defaultVirtualAttribute;
	}

	public void setDefaultVirtualAttribute(AttributeConfig defaultVirtualAttribute) {
		this.defaultVirtualAttribute = defaultVirtualAttribute;
	}

	public Class getBeanClass() {
		return beanClass;
	}

	public void setBeanClass(Class beanClass) {
		this.beanClass = beanClass;
	}

}
