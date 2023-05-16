package com.soffid.iam.model;

import java.util.Collection;

import com.soffid.iam.api.IssuePolicy;
import com.soffid.mda.annotation.Column;
import com.soffid.mda.annotation.DaoFinder;
import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Entity;
import com.soffid.mda.annotation.Identifier;
import com.soffid.mda.annotation.Nullable;

@Entity(table = "SC_EVEPOL")
@Depends({IssuePolicy.class})
public class IssuePolicyEntity {
	@Nullable @Identifier @Column(name = "EVP_ID")
	Long id;
	
	@Column(name = "EVP_TYPE")
	String type;

	@Nullable @Column(name = "EVP_DESCRI", length = 250)
	String description;
	
	@Column(name = "EVP_ACTOR", length = 250)
	@Nullable
	String actor;
	
	@Column(name = "EVP_TEN_ID")
	TenantEntity tenant;
	
	@DaoFinder
	Collection<IssuePolicyEntity> findByType(String type) { return null;} 
}
