package com.soffid.iam.model;

import java.util.Date;

import com.soffid.iam.api.IssueUser;
import com.soffid.iam.api.EventUserAction;
import com.soffid.mda.annotation.Column;
import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Entity;
import com.soffid.mda.annotation.Identifier;
import com.soffid.mda.annotation.Nullable;

import es.caib.seycon.ng.model.UsuariEntity;

@Entity(table = "SC_USEEVE")
@Depends({IssueUser.class})
public class IssueUserEntity {
	@Nullable @Identifier @Column(name = "UEV_ID")
	Long id;
	
	@Column(name = "UVE_EVE_ID", reverseAttribute = "users")
	IssueEntity issue;

	@Nullable @Column(name = "UVE_USU_ID", reverseAttribute = "events")
	UsuariEntity user;
	
	@Nullable @Column(name = "UVE_ACTION")
	EventUserAction action;
	
	@Nullable @Column(name = "UVE_USENAM")
	String userName;
	
	@Nullable @Column(name = "UVE_EXTID")
	String externalId;
}
