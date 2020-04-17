package com.soffid.iam.service;

import java.io.UnsupportedEncodingException;

import org.json.JSONException;

import com.soffid.iam.model.AccountAttributeEntity;
import com.soffid.iam.model.ApplicationAttributeEntity;
import com.soffid.iam.model.CustomObjectAttributeEntity;
import com.soffid.iam.model.GroupAttributeEntity;
import com.soffid.iam.model.MailListAttributeEntity;
import com.soffid.iam.model.RoleAttributeEntity;
import com.soffid.iam.model.RoleAttributeEntityImpl;
import com.soffid.iam.model.UserDataEntity;
import com.soffid.scimquery.conf.AttributeConfig;
import com.soffid.scimquery.conf.ClassConfig;
import com.soffid.scimquery.conf.Configuration;

public class AdditionalDataJSONConfiguration {

	public static void registerVirtualAttribute(Class<?> additionalDataClass, String namePath, String valuePath)
			throws UnsupportedEncodingException, ClassNotFoundException, JSONException {
		ClassConfig classConfig = Configuration.getClassConfig(additionalDataClass);
		if (classConfig == null) {
			classConfig = new ClassConfig();
			AttributeConfig attributeConfig = new AttributeConfig();
			attributeConfig.setVirtualAttribute(true);
			attributeConfig.setVirtualAttributeName(namePath);
			attributeConfig.setVirtualAttributeValue(valuePath);
			classConfig.setDefaultVirtualAttribute(attributeConfig);
			classConfig.setClazz(additionalDataClass.getName());
			classConfig.setHibernateClass(additionalDataClass.getCanonicalName());
			Configuration.registerClass(classConfig);
		}
	}

	public static void registerVirtualAttributes() throws UnsupportedEncodingException, ClassNotFoundException, JSONException {
		registerVirtualAttribute(GroupAttributeEntity.class, "metadata.name", "value");
		registerVirtualAttribute(UserDataEntity.class, "dataType.name", "value");
		registerVirtualAttribute(AccountAttributeEntity.class, "metadata.name", "value");
		registerVirtualAttribute(ApplicationAttributeEntity.class, "metadata.name", "value");
		registerVirtualAttribute(RoleAttributeEntity.class, "metadata.name", "value");
		registerVirtualAttribute(CustomObjectAttributeEntity.class, "metadata.name", "value");
		registerVirtualAttribute(MailListAttributeEntity.class, "metadata.name", "value");
	}
}
