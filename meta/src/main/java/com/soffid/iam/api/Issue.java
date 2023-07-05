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
	
	String type;

	@Attribute(multiline = true)
	@Nullable String description;

	IssueStatus status;
	
	Date created;
	
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
	String exception;
	
	@Nullable
	SoDRisk risk;
	
	@Nullable
	RolAccount roleAccount;
	
	@Nullable
	PamRule rule;
	
	
	@Nullable
	String country;
	
	@Nullable
	Account account;

	@Nullable
	String actor;
	
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
