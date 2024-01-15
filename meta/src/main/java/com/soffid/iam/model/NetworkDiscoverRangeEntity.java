package com.soffid.iam.model;

import com.soffid.mda.annotation.Column;
import com.soffid.mda.annotation.Entity;
import com.soffid.mda.annotation.Identifier;
import com.soffid.mda.annotation.Nullable;

import es.caib.seycon.ng.model.XarxaEntity;

@Entity(table = "SC_NEDIRA")
public class NetworkDiscoverRangeEntity {
	@Nullable @Identifier @Column(name = "NDR_ID")
	Long id;
	
	@Column(name="NDR_XAR_ID", reverseAttribute = "ranges")
	XarxaEntity network;
	
	@Column(name="NDR_RANGE")
	String range;
}
