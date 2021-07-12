package com.soffid.iam.model;

import java.util.Date;
import java.util.List;

import com.soffid.iam.api.PamAction;
import com.soffid.iam.api.PamRuleType;
import com.soffid.mda.annotation.Column;
import com.soffid.mda.annotation.DaoFinder;
import com.soffid.mda.annotation.DaoOperation;
import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Entity;
import com.soffid.mda.annotation.Identifier;
import com.soffid.mda.annotation.Nullable;

@Entity(table = "SC_PAMACT")
@Depends({PamAction.class})
public class PamActionEntity {
	@Nullable
	@Identifier
	@Column(name="PAC_ID")
	Long id;
	
	@Column(name="PAC_PPO_ID", reverseAttribute = "actions")
	PamPolicyEntity policy;
	
	@Column(name="PAC_PRU_ID", reverseAttribute = "actions")
	PamRuleEntity rule;

	@Nullable
	@Column(name="PAC_AUTHOR", length = 100)
	String author;
	
	@Nullable
	@Column(name="PAC_DATE")
	Date date;

	@Column(name="PAC_TYPE", length=20)
	PamActionType type;

	@Column(name="PAC_TEN_ID")
	TenantEntity tenant;

	@DaoFinder("select a from com.soffid.iam.model.PamActionEntity as a where a.policy.name=:policy and a.rule.name=:rule")
	List<PamActionEntity> findByPolicyAndRule(String policy, String rule) {return null;}
	
	@DaoOperation()
	List<PamAction> getActionsByPolicy(String policy) {return null;}
	
	@DaoFinder("select a from com.soffid.iam.model.PamActionEntity as a where a.policy.name=:policy")
	List<PamActionEntity> findByPolicy(String policy) {return null;}
	
	@DaoOperation
	PamAction create (PamAction action) {return null;}

	@DaoOperation
	PamAction update (PamAction action) {return null;}

	@DaoOperation
	void remove(PamAction action) {}
}


