package com.soffid.iam.model;

import java.util.Date;

import com.soffid.iam.api.Issue;
import com.soffid.iam.api.IssueStatus;
import com.soffid.mda.annotation.Column;
import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Entity;
import com.soffid.mda.annotation.Identifier;
import com.soffid.mda.annotation.Nullable;

import es.caib.seycon.ng.model.AccountEntity;
import es.caib.seycon.ng.model.DispatcherEntity;
import es.caib.seycon.ng.model.RolAccountEntity;

@Entity(table = "SC_ISSUE")
@Depends({Issue.class})
public class IssueEntity {
	@Nullable @Identifier @Column(name = "EVE_ID")
	Long id;
	
	@Column(name = "EVE_TYPE")
	String type;

	@Column(name = "EVE_STATUS")
	IssueStatus status;
	
	@Column(name = "EVE_CREATE")
	Date created;
	
	@Nullable @Column(name = "EVE_ACKDAT")
	Date acknowledged;
	
	@Nullable @Column(name = "EVE_SOLDAT")
	Date solved;
	
	@Nullable @Column(name = "EVE_FALOPC") 
	Double failedLoginPct;
	
	@Nullable @Column(name = "EVE_HUMCON")
	Double humanConfidence;
	
	@Nullable @Column(name = "EVE_DIS_ID", reverseAttribute = "events")
	DispatcherEntity system;

	@Nullable @Column(name = "EVE_DEVICE")
	String otpDevice;
	
	@Nullable @Column(name="EVE_EXCEPT", length = 64000)
	String exception;
	
	@Nullable @Column(name = "EVE_RAC_ID", reverseAttribute = "events")
	RolAccountEntity roleAccount;
	
	@Nullable @Column(name = "EVE_HISTOR", length = 64000)
	String performedActions;

	@Nullable @Column(name = "EVE_PRU_ID", reverseAttribute = "events")
	PamRuleEntity rule;
	
	@Nullable @Column(name = "EVE_ACC_ID", reverseAttribute = "events")
	AccountEntity account;

	@Nullable @Column(name = "EVE_ACTOR")
	String actor;
	
	@Column(name = "EVE_TEN_ID")
	TenantEntity tenant;
}
