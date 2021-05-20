package com.soffid.iam.model;

import com.soffid.iam.api.HostPort;
import com.soffid.mda.annotation.Column;
import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Entity;
import com.soffid.mda.annotation.Identifier;
import com.soffid.mda.annotation.Nullable;

import es.caib.seycon.ng.model.AccountEntity;
import es.caib.seycon.ng.model.MaquinaEntity;
import es.caib.seycon.ng.model.XarxaEntity;

@Entity(table = "SC_XARACC")
@Depends(HostPort.class)
public class NetworkDiscoveryAccountEntity {
	@Column(name = "NDA_ID")
	@Nullable @Identifier Long id;
	
	@Column(name = "NDA_XAR_ID", reverseAttribute = "accounts")
	XarxaEntity network;
	
	@Column(name = "NDA_ACC_ID", reverseAttribute = "networkDiscovery")
	AccountEntity account;
}
