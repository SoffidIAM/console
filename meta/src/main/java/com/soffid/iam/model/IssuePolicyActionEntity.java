package com.soffid.iam.model;

import com.soffid.iam.api.IssuePolicyAction;
import com.soffid.mda.annotation.Column;
import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Entity;
import com.soffid.mda.annotation.Identifier;
import com.soffid.mda.annotation.Nullable;

@Entity(table = "SC_EVPOAC")
@Depends({IssuePolicyAction.class})
public class IssuePolicyActionEntity {
	@Nullable @Identifier @Column(name = "EPA_ID")
	Long id;
	
	@Column(name = "EPA_EVP_ID", reverseAttribute = "actions")
	IssuePolicyEntity issuePolicy;
	
	@Column(name = "EPA_ACTION", length=50)
	String action;
	
	@Column(name = "EPA_DESCRI", length=150)
	String description;
	
	@Nullable @Column(name="EPA_SUBJEC", length = 150)
	String subject;
	
	@Nullable @Column(name="EPA_BODY", length = 64000)
	String body;
	
	@Nullable @Column(name="EPA_ADDRES", length = 150)
	String emailAddress;

	@Nullable @Column(name = "EPA_PRODEF", length = 64000)
	String processDefinition;
	
	@Nullable @Column(name = "EPA_SCRIPT", length = 128000)
	String script;
	

}
