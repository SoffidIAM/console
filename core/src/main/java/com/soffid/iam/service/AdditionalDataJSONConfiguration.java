package com.soffid.iam.service;

import java.io.UnsupportedEncodingException;

import org.json.JSONException;

import com.soffid.scimquery.conf.AttributeConfig;
import com.soffid.scimquery.conf.ClassConfig;
import com.soffid.scimquery.conf.Configuration;

public class AdditionalDataJSONConfiguration {

	public static void registerVirtualAttribute(Class<?> additionalDataClass)
			throws UnsupportedEncodingException, ClassNotFoundException, JSONException {
		ClassConfig classConfig = Configuration.getClassConfig(additionalDataClass);
		if (classConfig == null) {
			classConfig = new ClassConfig();
			AttributeConfig attributeConfig = new AttributeConfig();
			attributeConfig.setVirtualAttribute(true);
			attributeConfig.setVirtualAttributeValue("value");
			attributeConfig.setVirtualAttributeName("attribute.name");
			classConfig.setDefaultVirtualAttribute(attributeConfig);
			classConfig.setClazz(additionalDataClass.getName());
			classConfig.setHibernateClass(additionalDataClass.getCanonicalName());
			Configuration.registerClass(classConfig);
		}
	}
}
