package com.soffid.iam.model;

import com.soffid.iam.api.PamPolicy;
import com.soffid.mda.annotation.Column;
import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Entity;
import com.soffid.mda.annotation.Identifier;
import com.soffid.mda.annotation.Nullable;

@Entity(table = "SC_PPOJTP")
public class PamPolicyJITPermissionEntity {
	@Nullable
	@Identifier
	@Column(name="PJT_ID")
	Long id;
	
	@Column(name="PJT_PPO_ID", reverseAttribute = "justInTimePermissions")
	PamPolicyEntity policy;
	
	@Column(name="PJT_NAME", length = 100)
	String name;
	
	@Column(name="PPO_TEN_ID")
	TenantEntity tenant;
}

