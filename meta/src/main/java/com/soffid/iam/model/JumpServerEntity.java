package com.soffid.iam.model;

import com.soffid.mda.annotation.Column;
import com.soffid.mda.annotation.Entity;
import com.soffid.mda.annotation.Identifier;
import com.soffid.mda.annotation.Nullable;

@Entity(table="SC_JUMSER")
public class JumpServerEntity {
	@Nullable
	@Identifier
	@Column(name="JSE_ID")
	Long id;
	
	@Column(name="JSE_url", length = 50)
	String url;
	
	@Column(name="JSE_JSG_ID", reverseAttribute = "jumpServers")
	JumpServerGroupEntity jumpServerGroup;
	

}
