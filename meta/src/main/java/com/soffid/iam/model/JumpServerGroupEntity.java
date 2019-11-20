package com.soffid.iam.model;

import com.soffid.iam.api.JumpServerGroup;
import com.soffid.mda.annotation.Column;
import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Entity;
import com.soffid.mda.annotation.Identifier;
import com.soffid.mda.annotation.Nullable;

@Entity(table="SC_JUSEGR")
@Depends({JumpServerGroup.class})
public class JumpServerGroupEntity {
	@Nullable
	@Identifier
	@Column(name="JSG_ID")
	Long id;
	
	@Column(name="JSG_TEN_ID")
	TenantEntity tenant;
	

	@Column(name="JSG_NAME", length = 50)
	String name;
	
	@Nullable
	@Column(name="JSG_DESCRI", length = 250)
	String description;
	
	
	@Column(name="JSG_STORE", length = 100)
	String storeUrl;
	
	@Column(name="JSG_USER", length=100)
	String storeUserName;
	
	@Column(name="JSG_PASSWD", length=100)
	String password;
}
