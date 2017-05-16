package com.soffid.iam.model;

import com.soffid.mda.annotation.Column;
import com.soffid.mda.annotation.Entity;
import com.soffid.mda.annotation.Identifier;

@Entity(table="SC_TEDIPE")
public class TenantDisabledPermissionEntity {
	@Column(name = "TDP_ID")
	@Identifier
	public java.lang.Long id;

	@Column(name = "TDP_TEN_ID", composition=true, reverseAttribute="disabledPermissions")
	@Identifier
	public TenantEntity appliesTo;

	@Column(name = "TDP_PERMISSION")
	public java.lang.String permission;

}
