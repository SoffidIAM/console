package com.soffid.iam.model;

import com.soffid.mda.annotation.Column;
import com.soffid.mda.annotation.Entity;
import com.soffid.mda.annotation.Identifier;
import com.soffid.mda.annotation.Nullable;

import es.caib.seycon.ng.model.DispatcherEntity;
import es.caib.seycon.ng.model.MaquinaEntity;
import es.caib.seycon.ng.model.PuntEntradaEntity;

@Entity(table = "SC_MAQPUE")
public class HostEntryPointEntity {
	@Column(name = "MAP_ID")
	@Nullable @Identifier Long id;
	
	@Column(name = "MAP_MAQ_ID", reverseAttribute = "entryPoints")
	MaquinaEntity host;
	
	@Column(name = "MAP_PUE_ID", reverseAttribute = "hosts")
	PuntEntradaEntity entryPoint;
}
