package com.soffid.iam.model;

import java.util.Date;

import com.soffid.iam.api.PamPolicy;
import com.soffid.mda.annotation.Column;
import com.soffid.mda.annotation.DaoFinder;
import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Entity;
import com.soffid.mda.annotation.Identifier;
import com.soffid.mda.annotation.Index;
import com.soffid.mda.annotation.Nullable;

@Entity(table = "SC_PAMPOL")
@Depends({PamPolicy.class})
public class PamPolicyEntity {
	@Nullable
	@Identifier
	@Column(name="PPO_ID")
	Long id;
	
	@Column(name="PPO_NAME", length = 100)
	String name;
	
	@Column(name="PPO_DESCR", length = 150)
	@Nullable
	String description;
	
	@Nullable
	@Column(name="PPO_AUTHOR", length = 100)
	String author;
	
	@Nullable
	@Column(name="PPO_DATE")
	Date date;

	@Nullable
	@Column(name="PPO_RECDUR")
	Integer recordingDuration;

	@Nullable
	@Column(name="PPO_EXPRES", length = 16000)
	String expression;

	@Nullable
	@Column(name="PPO_PRIORI")
	Integer priority;

	PamPolicyEntity findByName (String name) { return null; }
	
	@Column(name="PPO_TEN_ID")
	TenantEntity tenant;
}

@Index(name = "SC_PAMPOL_UK", entity = PamPolicyEntity.class, columns = {"PPO_TEN_ID", "PPO_NAME"}, unique = true)
class PamPolicyEntityUniqueKey {} 
