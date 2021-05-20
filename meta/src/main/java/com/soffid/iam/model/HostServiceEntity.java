package com.soffid.iam.model;

import com.soffid.iam.api.HostService;
import com.soffid.mda.annotation.Column;
import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Entity;
import com.soffid.mda.annotation.Identifier;
import com.soffid.mda.annotation.Nullable;

import es.caib.seycon.ng.model.AccountEntity;
import es.caib.seycon.ng.model.MaquinaEntity;

@Entity(table = "SC_MAQSER")
@Depends(HostService.class)
public class HostServiceEntity {
	@Column(name = "MAS_ID")
	@Nullable @Identifier Long id;
	
	@Column(name = "MAS_MAQ_ID", reverseAttribute = "services")
	MaquinaEntity host;
	
	@Column(name = "MAS_SERVICE")
	String service;
	
	@Column(name = "MAS_ACC_ID", reverseAttribute = "services")
	AccountEntity account;
}
