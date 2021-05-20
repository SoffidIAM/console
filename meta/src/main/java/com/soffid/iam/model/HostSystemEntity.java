package com.soffid.iam.model;

import com.soffid.mda.annotation.Column;
import com.soffid.mda.annotation.Entity;
import com.soffid.mda.annotation.Identifier;
import com.soffid.mda.annotation.Nullable;

import es.caib.seycon.ng.model.DispatcherEntity;
import es.caib.seycon.ng.model.MaquinaEntity;

@Entity(table = "SC_MAQDIS")
public class HostSystemEntity {
	@Column(name = "MAD_ID")
	@Nullable @Identifier Long id;
	
	@Column(name = "MAD_MAQ_ID", reverseAttribute = "systems")
	MaquinaEntity host;
	
	@Column(name = "MAQ_DIS_ID", reverseAttribute = "hosts")
	DispatcherEntity system;
}
