//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package com.soffid.iam.service;
import com.soffid.iam.api.RuleAssignedRole;
import com.soffid.mda.annotation.*;

import java.util.Collection;

import org.springframework.transaction.annotation.Transactional;

@Service ( translatedName="RulesService",
	 translatedPackage="com.soffid.iam.service")
@Depends ({com.soffid.iam.model.RuleEntity.class,
	com.soffid.iam.model.RuleAssignedRoleEntity.class,
	es.caib.seycon.ng.model.RolEntity.class,
	com.soffid.iam.service.RuleEvaluatorService.class,
	es.caib.seycon.ng.model.AuditoriaEntity.class})
public abstract class RulesService {

	@Operation ( grantees={roles.rule_admin.class})
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.api.Rule create(
		com.soffid.iam.api.Rule rule)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	
	@Operation ( grantees={roles.rule_admin.class})
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.api.Rule update(
		com.soffid.iam.api.Rule rule)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	
	@Operation ( grantees={roles.rule_admin.class})
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void delete(
		com.soffid.iam.api.Rule rule)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation ( grantees={roles.rule_query.class})
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.api.Rule> findRules(
		@Nullable java.lang.String description)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.rule_admin.class})
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.api.RuleAssignedRole create(
		com.soffid.iam.api.RuleAssignedRole ruleAssignment)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.rule_admin.class})
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.api.RuleAssignedRole update(
		com.soffid.iam.api.RuleAssignedRole ruleAssignment)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={roles.rule_admin.class})
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void delete(
		com.soffid.iam.api.RuleAssignedRole ruleAssignment)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation ( grantees={roles.rule_query.class})
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.api.RuleAssignedRole> findRuleAssignments(
		com.soffid.iam.api.Rule rule)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}

	@Operation ( grantees={roles.rule_admin.class})
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void apply(
		com.soffid.iam.api.Rule rule)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}

	@Operation ( grantees={roles.rule_admin.class})
	@Transactional(readOnly=true,noRollbackFor={java.lang.Exception.class})
	@Description("Generates an excel file with expected changes")
	public String generateChangesReport(
		com.soffid.iam.api.Rule rule,
		Collection<RuleAssignedRole> grants)
		throws es.caib.seycon.ng.exception.InternalErrorException { return null; }
}
