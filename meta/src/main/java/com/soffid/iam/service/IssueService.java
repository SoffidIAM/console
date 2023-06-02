package com.soffid.iam.service;

import java.util.List;
import java.util.Map;

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
import roles.IssueCreate;
import roles.IssueDelete;
import roles.IssueQuery;
import roles.IssueUpdate;
import roles.Tothom;
import roles.anonymous;

@Service()
@Depends( { IssueEntity.class, IssuePolicyEntity.class, IssueUserEntity.class, IssueHostEntity.class, IssuePolicyActionEntity.class,
	UsuariEntity.class, MaquinaEntity.class, PamRuleEntity.class,
	AsyncRunnerService.class,
	IssuePolicyService.class,
	MailService.class})
public class IssueService {
	@Operation(grantees = { Tothom.class })
	AsyncList<Issue> findMyIssuesByJsonQueryAsync (@Nullable String query) { return null; }

	@Operation(grantees = { Tothom.class })
	PagedResult<Issue> findMyIssuesByJsonQuery (@Nullable String query, @Nullable Integer first, @Nullable Integer pageSize) { return null; }

	@Operation(grantees = { IssueQuery.class })
	AsyncList<Issue> findIssuesByJsonQueryAsync (@Nullable String query) { return null; }

	@Operation(grantees = { IssueQuery.class })
	PagedResult<Issue> findIssuesByJsonQuery (@Nullable String query, @Nullable Integer first, @Nullable Integer pageSize) { return null; }

	Issue createInternalIssue(Issue event) { return null;}
	
	@Operation(grantees = { IssueCreate.class})
	Issue create(Issue event) {return null;}
	
	@Operation(grantees = { IssueUpdate.class})
	Issue update(Issue event) {return null;}
	
	@Operation(grantees = { IssueDelete.class})
	void delete(Issue event) {}
	
	@Operation(grantees = { IssueQuery.class})
	List<IssueActionDefinition> listManualActions() {return null;}
	
	@Operation(grantees = { IssueQuery.class})
	Issue notify(Issue issue, String address, String subject, String body) {return null;}

	@Operation(grantees = { IssueQuery.class})
	Issue registerAction(Issue issue, String action) {return null;}
}
