package com.soffid.iam.api;

import java.util.Date;
import java.util.List;

import com.soffid.iam.model.IssueEntity;
import com.soffid.mda.annotation.Attribute;
import com.soffid.mda.annotation.Column;
import com.soffid.mda.annotation.Entity;
import com.soffid.mda.annotation.Identifier;
import com.soffid.mda.annotation.JsonAttribute;
import com.soffid.mda.annotation.JsonObject;
import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.ValueObject;

import es.caib.seycon.ng.comu.Account;
import es.caib.seycon.ng.comu.RolAccount;
import es.caib.seycon.ng.comu.SoDRisk;
import es.caib.seycon.ng.model.AccountEntity;
import es.caib.seycon.ng.model.DispatcherEntity;
import es.caib.seycon.ng.model.RolAccountEntity;

@ValueObject
@JsonObject(hibernateClass = IssueEntity.class)
public class Issue {
	@Nullable 
	@Attribute(hidden = true)
	Long id;
	
	@Attribute(defaultValue = "new java.util.Date()")
	Date created;
	
	String type;

	@Attribute(multiline = true)
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
	@Attribute(customUiHandler = "com.soffid.iam.web.issue.RuleFieldHandler", type = "STRING", multivalue = false)
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

	@Nullable @Attribute(readonly = true, type = "ACCOUNT_TYPE")
	String requester;
}
