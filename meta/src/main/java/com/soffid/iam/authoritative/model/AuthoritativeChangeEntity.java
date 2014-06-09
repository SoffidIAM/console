package com.soffid.iam.authoritative.model;

import java.util.Collection;
import java.util.Date;

import com.soffid.mda.annotation.Column;
import com.soffid.mda.annotation.DaoFinder;
import com.soffid.mda.annotation.Description;
import com.soffid.mda.annotation.Entity;
import com.soffid.mda.annotation.Identifier;
import com.soffid.mda.annotation.Nullable;

import es.caib.seycon.ng.model.DispatcherEntity;
import es.caib.seycon.ng.model.UsuariEntity;

@Description("Contains pending authoritative changes")
@Entity(table = "SC_AUTCHA")
public class AuthoritativeChangeEntity {
	@Column(name="PAU_ID")
	@Identifier
	Long id;
	
	@Column (name="PAU_USU_ID")
	@Nullable
	UsuariEntity user;
	
	@Column (name="PAU_PRO_ID")
	@Nullable
	Long processId;
	
	@Column (name="PAU_EMPLOY", length=128)
	@Nullable
	String employeeId;
	
	@Column (name="PAU_CHANGE", length=128)
	@Nullable
	String changeId;

	@Column (name="PAU_DATE")
	@Nullable
	Date changeDate;
	
	@Column (name="PAU_DIS_ID")
	DispatcherEntity dispatcher;
}
