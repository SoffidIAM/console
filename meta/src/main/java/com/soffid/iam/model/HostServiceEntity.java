package com.soffid.iam.model;

import com.soffid.iam.api.HostService;
import com.soffid.mda.annotation.Column;
import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Description;
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
	
	@Column(name = "MAS_SERVICE", length = 150)
	String service;

	@Description("An operating system command to configure the user password in the subscribed applications")
	@Column(name = "MAS_CMD", length = 150)
	@Nullable
	String command;

	@Description("Service not discovered by the network discovery process")
	@Column(name="MAS_MANUAL")
	boolean manual;
	
	@Column(name = "MAS_ACC_ID", reverseAttribute = "services")
	AccountEntity account;
}
