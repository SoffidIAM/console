package com.soffid.iam.model;

import java.util.Collection;
import java.util.Date;

import com.soffid.iam.api.Issue;
import com.soffid.iam.api.IssueStatus;
import com.soffid.mda.annotation.Column;
import com.soffid.mda.annotation.DaoFinder;
import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Entity;
import com.soffid.mda.annotation.Identifier;
import com.soffid.mda.annotation.Nullable;

import es.caib.seycon.ng.comu.SoDRisk;
import es.caib.seycon.ng.model.AccountEntity;
import es.caib.seycon.ng.model.DispatcherEntity;
import es.caib.seycon.ng.model.RolAccountEntity;
import es.caib.seycon.ng.model.UsuariEntity;

@Entity(table = "SC_ISSUE")
@Depends({Issue.class, DispatcherEntity.class})
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
	
	@Nullable @Column(name = "EVE_COUNTR", length = 64000)
	String country;

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
	
	@Nullable @Column(name = "EVE_REQ_ID")
	AccountEntity requester;
	
	@Nullable @Column(name = "EVE_RISK")
	SoDRisk risk;

	@Column(name = "EVE_TEN_ID")
	TenantEntity tenant;
	
	@DaoFinder("select i from com.soffid.iam.model.IssueEntity as i "
			+ "join i.users as users "
			+ "join users.user as user "
			+ "where user.userName = :user and user.tenant.id=:tenantId")
	Collection<IssueEntity> findByUserName(String user) { return null;}
}
