package com.soffid.iam.model;

import java.util.Date;

import com.soffid.iam.api.PamRule;
import com.soffid.iam.api.PamRuleType;
import com.soffid.mda.annotation.Column;
import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Entity;
import com.soffid.mda.annotation.Identifier;
import com.soffid.mda.annotation.Index;
import com.soffid.mda.annotation.Nullable;

@Entity(table = "SC_PAMRUL")
@Depends({PamRule.class})
public class PamRuleEntity {
	@Nullable
	@Identifier
	@Column(name="PRU_ID")
	Long id;
	
	@Column(name="PRU_NAME", length = 100)
	String name;
	
	@Column(name="PRU_DESCRIPTION", length = 100)
	@Nullable
	String description;

	@Nullable
	@Column(name="PRU_AUTHOR", length = 100)
	String author;
	
	@Nullable
	@Column(name="PRU_DATE")
	Date date;

	@Column(name="PRU_TYPE", length=20)
	PamRuleType type;
	
	@Nullable
	@Column(name="PRU_CONTEN", length=500)
	String content;
	
	@Nullable
	@Column(name="PRU_BLOB", length=50000)
	String blob;

	PamRuleEntity findByName (String name) { return null; }

	@Column(name="PRU_TEN_ID")
	TenantEntity tenant;
}

@Index(name = "SC_PAMRUL_UK", entity = PamRuleEntity.class, columns = {"PRU_TEN_ID, PRU_NAME"}, unique = true)
class PamRuelEntityUniqueKey {} 
