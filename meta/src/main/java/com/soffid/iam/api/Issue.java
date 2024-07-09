package com.soffid.iam.api;

import java.util.Date;
import java.util.List;

import com.soffid.iam.model.IssueEntity;
import com.soffid.mda.annotation.Attribute;
import com.soffid.mda.annotation.JsonAttribute;
import com.soffid.mda.annotation.JsonObject;
import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.ValueObject;

import es.caib.seycon.ng.comu.RolAccount;
import es.caib.seycon.ng.comu.SoDRisk;

@ValueObject
@JsonObject(hibernateClass = IssueEntity.class)
public class Issue {
	@Nullable 
	@Attribute(hidden = true)
	Long id;
	
	@Attribute(readonly = true, type = "NUMBER")
	@Nullable
	Long number;

	@Attribute(defaultValue = "new java.util.Date()")
	Date created;
	
	String type;

	@Attribute(multiline = true, readonly = true)
	@Nullable String description;

	@Attribute(defaultValue="0", type = "NUMBER", readonly = true)
	Integer times;
	
	@Attribute(defaultValue = "com.soffid.iam.api.IssueStatus.NEW")
	IssueStatus status;
	
	@Nullable
	Date acknowledged;
	
	@Nullable
	Date solved;
	
	@Nullable
	Double failedLoginPct;
	
	@Nullable
	Double humanConfidence;
	
	@Nullable @JsonAttribute(hibernateAttribute = "system.name")
	String system;

	@Nullable
	String otpDevice;
	
	@Nullable
	@Attribute(multiline = true)
	String exception;
	
	@Nullable
	SoDRisk risk;
	
	@Nullable
	@Attribute(customUiHandler = "com.soffid.iam.web.issue.RoleAccountFieldHandler", type = "STRING", multivalue = false)
	RolAccount roleAccount;
	
	@Nullable
	@Attribute(customUiHandler = "com.soffid.iam.web.issue.PamRuleFieldHandler", type = "STRING", multivalue = false)
	PamRule rule;
	
	@Nullable
	String jobName;
	
	@Nullable
	String country;
	
	@Nullable
	@Attribute(type = "ACCOUNT")
	String account;

	@Nullable
	String actor;
	
	@Nullable
	String loginName;

	@Nullable
	@Attribute(hidden = true)
	String hash;

	@Nullable
	@Attribute(customUiHandler = "com.soffid.iam.web.issue.HostFieldHandler", type = "HOST", multivalue = true)
	List<IssueHost> hosts;

	@Nullable
	@Attribute(customUiHandler = "com.soffid.iam.web.issue.UserFieldHandler", type = "USER", multivalue = true)
	List<IssueUser> users;

	@Nullable
	@Attribute(multiline = true)
	String performedActions;

	@JsonAttribute(hibernateAttribute = "requester.name")
	@Nullable @Attribute(readonly = true, type = "ACCOUNT_TYPE")
	String requester;

	@Nullable
	String breachedEmail;

	@Nullable
	String dataBreach;

	@Nullable
	@Attribute(type="HTML")
	String htmlDescription;
}
