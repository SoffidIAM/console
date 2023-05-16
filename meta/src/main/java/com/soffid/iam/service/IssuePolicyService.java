package com.soffid.iam.service;

import java.util.List;

import com.soffid.iam.api.AsyncList;
import com.soffid.iam.api.Issue;
import com.soffid.iam.api.IssueActionDefinition;
import com.soffid.iam.api.IssuePolicy;
import com.soffid.iam.api.PagedResult;
import com.soffid.iam.model.IssueEntity;
import com.soffid.iam.model.IssueHostEntity;
import com.soffid.iam.model.IssuePolicyActionEntity;
import com.soffid.iam.model.IssuePolicyEntity;
import com.soffid.iam.model.IssueUserEntity;
import com.soffid.iam.model.PamRuleEntity;
import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.Operation;
import com.soffid.mda.annotation.Service;

import es.caib.seycon.ng.model.MaquinaEntity;
import es.caib.seycon.ng.model.UsuariEntity;
import es.caib.seycon.ng.servei.DadesAddicionalsService;
import roles.IssuePolicyQuery;

@Service
@Depends( { IssueEntity.class, IssuePolicyEntity.class, IssueUserEntity.class, IssueHostEntity.class, IssuePolicyActionEntity.class,
	UsuariEntity.class, MaquinaEntity.class, PamRuleEntity.class,
	DadesAddicionalsService.class,
	AsyncRunnerService.class})
public class IssuePolicyService {
	@Operation(grantees = { IssuePolicyQuery.class })
	AsyncList<IssuePolicy> findIssuePoliciesByJsonQueryAsync (@Nullable String query) { return null; }

	@Operation(grantees = { IssuePolicyQuery.class })
	PagedResult<IssuePolicy> findIssuePoliciesByJsonQuery (@Nullable String query, @Nullable Integer first, @Nullable Integer pageSize) { return null; }
	
	void createPolicies() {}
	
	@Operation(grantees = { IssuePolicyUpdate.class})
	IssuePolicy update(IssuePolicy event) {return null;}
	
	@Operation(grantees = { IssuePolicyQuery.class })
	List<IssueActionDefinition> listAutomaticActions() {return null;}	
}
