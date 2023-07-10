package com.soffid.iam.model;

import java.util.Date;

import com.soffid.iam.api.HostEventAction;
import com.soffid.iam.api.IssueHost;
import com.soffid.iam.api.EventUserAction;
import com.soffid.mda.annotation.Column;
import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Entity;
import com.soffid.mda.annotation.Identifier;
import com.soffid.mda.annotation.Nullable;

import es.caib.seycon.ng.model.MaquinaEntity;
import es.caib.seycon.ng.model.UsuariEntity;

@Entity(table = "SC_HOSEVE")
@Depends({IssueHost.class})
public class IssueHostEntity {
	@Nullable @Identifier @Column(name = "HEV_ID")
	Long id;
	
	@Column(name = "HVE_EVE_ID", reverseAttribute = "hosts")
	IssueEntity issue;

	@Nullable @Column(name = "HVE_MAQ_ID", reverseAttribute = "events")
	MaquinaEntity host;
	
	@Nullable @Column(name = "HVE_ACTION")
	HostEventAction action;
	
	@Nullable @Column(name = "HVE_HOSNAM")
	String hostName;

	@Nullable @Column(name = "HVE_HOSIP")
	String hostIp;
}
