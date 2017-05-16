package com.soffid.iam.model;

import com.soffid.mda.annotation.Column;
import com.soffid.mda.annotation.Entity;
import com.soffid.mda.annotation.Identifier;

import es.caib.seycon.ng.model.ServerEntity;

@Entity(table="SC_TENSER")
public class TenantServerEntity {
	@Column(name = "TNS_ID")
	@Identifier
	public java.lang.Long id;

	@Column(name = "TNS_TEN_ID", composition=true, reverseAttribute="servers")
	@Identifier
	public TenantEntity serverTenant;

	@Column(name = "TNS_SRV_ID", composition=true, reverseAttribute="tenants")
	public ServerEntity tenantServer;

}
