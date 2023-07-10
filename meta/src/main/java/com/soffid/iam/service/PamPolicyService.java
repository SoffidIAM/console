package com.soffid.iam.service;

import java.util.List;

import com.soffid.iam.api.AsyncList;
import com.soffid.iam.api.PagedResult;
import com.soffid.iam.api.PamAction;
import com.soffid.iam.api.PamPolicy;
import com.soffid.iam.api.PamRule;
import com.soffid.iam.model.JumpServerEntity;
import com.soffid.iam.model.JumpServerGroupEntity;
import com.soffid.iam.model.PamActionEntity;
import com.soffid.iam.model.PamPolicyEntity;
import com.soffid.iam.model.PamRuleEntity;
import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Description;
import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.Operation;
import com.soffid.mda.annotation.Role;
import com.soffid.mda.annotation.Service;

import es.caib.seycon.ng.model.AccountEntity;
import es.caib.seycon.ng.model.AuditoriaEntity;
import es.caib.seycon.ng.model.MaquinaEntity;
import es.caib.seycon.ng.model.RegistreAccesEntity;
import es.caib.seycon.ng.model.ServeiEntity;
import es.caib.seycon.ng.model.SessioEntity;
import es.caib.seycon.ng.model.UsuariEntity;
import es.caib.seycon.ng.servei.AccountService;
import es.caib.seycon.ng.servei.DispatcherService;

@Service
@Depends({JumpServerGroupEntity.class, JumpServerEntity.class, PamPolicyEntity.class, PamRuleEntity.class, PamActionEntity.class,
	AuditoriaEntity.class,
	ServeiEntity.class,
	RegistreAccesEntity.class, SessioEntity.class, UsuariEntity.class,
	MaquinaEntity.class,
	AsyncRunnerService.class,
	PamSecurityHandlerService.class,
	AccountEntity.class,
	DispatcherService.class, MailService.class,
	IssueService.class})
public class PamPolicyService {
	@Operation(grantees = {PamPolicy_query.class})
	PagedResult<PamPolicy> findPolicyByJsonQuery(@Nullable String text, @Nullable String query, @Nullable Integer first, @Nullable Integer pageSize) { return null; }
	
	@Operation(grantees = {PamPolicy_query.class})
	AsyncList<PamPolicy> findPolicyByJsonQueryAsync(@Nullable String text, @Nullable String query) { return null; }

	@Operation(grantees = {PamPolicy_create.class})
	PamPolicy createPolicy(PamPolicy policy) { return null; }

	@Operation(grantees = {PamPolicy_update.class})
	PamPolicy updatePolicy(PamPolicy policy) { return null; }

	@Operation(grantees = {PamPolicy_delete.class})
	void deletePolicy(PamPolicy policy) { }

	@Operation(grantees = {PamPolicy_query.class})
	List<PamAction> findPolicyActions(PamPolicy policy) { return null; }

	@Operation(grantees = {PamPolicy_update.class})
	PamAction updateAction(PamAction action) { return null; }

	@Operation(grantees = {PamRule_query.class})
	PagedResult<PamRule> findRuleByJsonQuery(@Nullable String text, @Nullable String query, @Nullable Integer first, @Nullable Integer pageSize) { return null; }
	
	@Operation(grantees = {PamRule_query.class})
	AsyncList<PamRule> findRuleByJsonQueryAsync(@Nullable String text, @Nullable String query) { return null; }

	@Operation(grantees = {PamRule_create.class})
	PamRule createRule(PamRule rule) { return null; }

	@Operation(grantees = {PamRule_update.class})
	PamRule updateRule(PamRule rule) { return null; }

	@Operation(grantees = {PamRule_delete.class})
	void deleteRule(PamRule rule) { }
	
	@Description("Method invoked from PAM session")
	void applyRule(String sessionKey, String policyName, String ruleName) {}
}

@Role (name="pamPolicy:create" ) class PamPolicy_create { }

@Role (name="pamPolicy:update" ) class PamPolicy_update { }

@Role (name="pamPolicy:delete" ) class PamPolicy_delete { }

@Role (name="pamPolicy:query" ) class PamPolicy_query { }

@Role (name="pamRule:create" ) class PamRule_create { }

@Role (name="pamRule:update" ) class PamRule_update { }

@Role (name="pamRule:delete" ) class PamRule_delete { }

@Role (name="pamRule:query" ) class PamRule_query { }


