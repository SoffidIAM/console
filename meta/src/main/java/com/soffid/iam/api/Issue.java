package com.soffid.iam.api;

import java.util.Date;
import java.util.List;

import com.soffid.iam.model.IssueEntity;
import com.soffid.mda.annotation.Column;
import com.soffid.mda.annotation.Entity;
import com.soffid.mda.annotation.Identifier;
import com.soffid.mda.annotation.JsonAttribute;
import com.soffid.mda.annotation.JsonObject;
import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.ValueObject;

import es.caib.seycon.ng.comu.Account;
import es.caib.seycon.ng.comu.RolAccount;
import es.caib.seycon.ng.model.AccountEntity;
import es.caib.seycon.ng.model.DispatcherEntity;
import es.caib.seycon.ng.model.RolAccountEntity;

@ValueObject
@JsonObject(hibernateClass = IssueEntity.class)
public class Issue {
	@Nullable
	Long id;
	
	String type;
	
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
	RolAccount roleAccount;
	
	@Nullable
	String performedActions;

	@Nullable
	PamRule rule;
	
	@Nullable
	Account account;

	@Nullable
	String actor;
	
	@Nullable
	List<IssueHost> hosts;

	@Nullable
	List<IssueUser> users;
}
