package com.soffid.iam.service;

import java.io.UnsupportedEncodingException;

import org.json.JSONException;

import com.soffid.iam.api.User;
import com.soffid.iam.bpm.api.ProcessInstance;
import com.soffid.iam.model.AccountAttributeEntity;
import com.soffid.iam.model.ApplicationAttributeEntity;
import com.soffid.iam.model.CustomObjectAttributeEntity;
import com.soffid.iam.model.GroupAttributeEntity;
import com.soffid.iam.model.HostAttributeEntity;
import com.soffid.iam.model.MailListAttributeEntity;
import com.soffid.iam.model.RoleAttributeEntity;
import com.soffid.iam.model.RoleAttributeEntityImpl;
import com.soffid.iam.model.UserDataEntity;
import com.soffid.iam.model.UserEntity;
import com.soffid.iam.model.UserGroupAttributeEntity;
import com.soffid.iam.model.UserGroupEntity;
import com.soffid.scimquery.conf.AttributeConfig;
import com.soffid.scimquery.conf.ClassConfig;
import com.soffid.scimquery.conf.Configuration;

public class AdditionalDataJSONConfiguration {

	public static void registerVirtualAttribute(Class<?> additionalDataClass, String namePath, String valuePath, String parentEntity)
			throws UnsupportedEncodingException, ClassNotFoundException, JSONException {
		ClassConfig classConfig = Configuration.getClassConfig(additionalDataClass);
		if (classConfig == null) {
			classConfig = new ClassConfig();
			AttributeConfig attributeConfig = new AttributeConfig();
			attributeConfig.setVirtualAttribute(true);
			attributeConfig.setVirtualAttributeName(namePath);
			attributeConfig.setVirtualAttributeValue(valuePath);
			attributeConfig.setParentEntity(parentEntity);
			classConfig.setDefaultVirtualAttribute(attributeConfig);
			classConfig.setClazz(additionalDataClass.getName());
			classConfig.setHibernateClass(additionalDataClass.getCanonicalName());
			Configuration.registerClass(classConfig);
		}
	}

	public static void registerVirtualAttributes() throws UnsupportedEncodingException, ClassNotFoundException, JSONException {
		registerVirtualAttribute(GroupAttributeEntity.class, "metadata.name", "value", "group");
		registerVirtualAttribute(UserDataEntity.class, "dataType.name", "value", "user");
		registerVirtualAttribute(AccountAttributeEntity.class, "systemMetadata.name metadata.name", "value", "account");
		registerVirtualAttribute(ApplicationAttributeEntity.class, "metadata.name", "value", "informationSystem");
		registerVirtualAttribute(RoleAttributeEntity.class, "metadata.name", "value", "role");
		registerVirtualAttribute(CustomObjectAttributeEntity.class, "metadata.name", "value", "customObject");
		registerVirtualAttribute(MailListAttributeEntity.class, "metadata.name", "value", "mailList");
		registerVirtualAttribute(UserGroupAttributeEntity.class, "metadata.name", "value", "userGroup");
		registerVirtualAttribute(HostAttributeEntity.class, "metadata.name", "value", "host");
		ClassConfig classConfig = Configuration.getClassConfig(User.class);
		if (! classConfig.getAttributes().containsKey("userData")) {
			AttributeConfig attributeConfig = new AttributeConfig();
			attributeConfig.setVirtualAttribute(false);
			attributeConfig.setHibernateColumn("attributes");
			attributeConfig.setAttributeName("userData");
			classConfig.getAttributes().put("userData", attributeConfig);
		}
		classConfig = Configuration.getClassConfig(ProcessInstance.class);
		if (! classConfig.getHibernateClass().equals(org.jbpm.graph.exe.ProcessInstance.class.getName())) {
			classConfig.setHibernateClass(org.jbpm.graph.exe.ProcessInstance.class.getName());
		}
	}
}
