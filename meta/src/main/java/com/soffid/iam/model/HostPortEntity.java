package com.soffid.iam.model;

import com.soffid.iam.api.HostPort;
import com.soffid.mda.annotation.Column;
import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Entity;
import com.soffid.mda.annotation.Identifier;
import com.soffid.mda.annotation.Nullable;

import es.caib.seycon.ng.model.MaquinaEntity;

@Entity(table = "SC_MAQPOR")
@Depends(HostPort.class)
public class HostPortEntity {
	@Column(name = "MPO_ID")
	@Nullable @Identifier Long id;
	
	@Column(name = "MPO_MAQ_ID", reverseAttribute = "ports")
	MaquinaEntity host;
	
	@Column(name = "MPO_PORT")
	String port;
	
	@Nullable
	@Column(name = "MPO_PORDES")
	String description;
}
