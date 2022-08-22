/**
 * 
 */
/**
 * 
 */
/**
 * 
 */
package com.soffid.iam.service;

import com.soffid.iam.api.ApplyRuleProcess;
import com.soffid.iam.api.Audit;
import com.soffid.iam.api.Rule;
import com.soffid.iam.api.RuleAssignedRole;
import com.soffid.iam.model.RuleAssignedRoleEntity;
import com.soffid.iam.model.RuleEntity;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.comu.AccountType;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

/**
 * @author bubu
 *
 */
public class RulesServiceImpl extends RulesServiceBase
{

	/**
	 * 
	 */
	public RulesServiceImpl ()
	{
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.service.RulesServiceBase#handleCreate(com.soffid.iam.api.Rule)
	 */
	@Override
	protected Rule handleCreate (Rule rule) throws Exception
	{
		RuleEntity entity = getRuleEntityDao().ruleToEntity(rule);
		getRuleEntityDao().create(entity);
		auditRule("C", entity); //$NON-NLS-1$
		return getRuleEntityDao().toRule(entity);
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.service.RulesServiceBase#handleUpdate(com.soffid.iam.api.Rule)
	 */
	@Override
	protected Rule handleUpdate (Rule rule) throws Exception
	{
		RuleEntity entity = getRuleEntityDao().ruleToEntity(rule);
		getRuleEntityDao().update(entity);
		auditRule("U", entity); //$NON-NLS-1$
		return getRuleEntityDao().toRule(entity);
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.service.RulesServiceBase#handleDelete(com.soffid.iam.api.Rule)
	 */
	@Override
	protected void handleDelete (Rule rule) throws Exception
	{
		RuleEntity entity = getRuleEntityDao().ruleToEntity(rule);
		auditRule("D", entity); //$NON-NLS-1$
		getRuleEntityDao().remove(entity);
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.service.RulesServiceBase#handleFindRules(java.lang.String)
	 */
	@Override
	protected Collection<Rule> handleFindRules (String description) throws Exception
	{
		List<RuleEntity> list = getRuleEntityDao().findByDescription(description);
		return getRuleEntityDao().toRuleList(list);
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.service.RulesServiceBase#handleCreate(com.soffid.iam.api.RuleAssignedRole)
	 */
	@Override
	protected RuleAssignedRole handleCreate (RuleAssignedRole ruleAssignment)
					throws Exception
	{
		RuleAssignedRoleEntity entity = getRuleAssignedRoleEntityDao().ruleAssignedRoleToEntity(ruleAssignment);
		getRuleAssignedRoleEntityDao().create(entity);
		auditRuleRole("C", entity); //$NON-NLS-1$
		return getRuleAssignedRoleEntityDao().toRuleAssignedRole(entity);
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.service.RulesServiceBase#handleUpdate(com.soffid.iam.api.RuleAssignedRole)
	 */
	@Override
	protected RuleAssignedRole handleUpdate (RuleAssignedRole ruleAssignment)
					throws Exception
	{
		RuleAssignedRoleEntity entity = getRuleAssignedRoleEntityDao().ruleAssignedRoleToEntity(ruleAssignment);
		getRuleAssignedRoleEntityDao().update(entity);
		auditRuleRole("U", entity); //$NON-NLS-1$
		return getRuleAssignedRoleEntityDao().toRuleAssignedRole(entity);
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.service.RulesServiceBase#handleDelete(com.soffid.iam.api.RuleAssignedRole)
	 */
	@Override
	protected void handleDelete (RuleAssignedRole ruleAssignment) throws Exception
	{
		RuleAssignedRoleEntity entity = getRuleAssignedRoleEntityDao().ruleAssignedRoleToEntity(ruleAssignment);
		auditRuleRole("D", entity); //$NON-NLS-1$
		getRuleAssignedRoleEntityDao().remove(entity);
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.service.RulesServiceBase#handleFindRuleAssignements(com.soffid.iam.api.Rule)
	 */
	@Override
	protected Collection<RuleAssignedRole> handleFindRuleAssignments (Rule rule)
					throws Exception
	{
		RuleEntity ruleEntity = getRuleEntityDao().load(rule.getId());
		return getRuleAssignedRoleEntityDao().toRuleAssignedRoleList(ruleEntity.getRoles());
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.service.RulesServiceBase#handleApply(com.soffid.iam.api.Rule)
	 */
	@Override
	protected void handleApply (Rule rule) throws Exception
	{
		RuleEntity ruleEntity = getRuleEntityDao().load(rule.getId());
		getRuleEvaluatorService().apply (ruleEntity);
	}


	@Override
	protected ApplyRuleProcess handleApplyAsync (Rule rule) throws Exception
	{
		RuleEntity ruleEntity = getRuleEntityDao().load(rule.getId());
		return getRuleEvaluatorService().applyAsync(ruleEntity);
	}


	private void auditRule(String accio, RuleEntity rule) {
        String codiUsuari = Security.getCurrentAccount();
        Audit auditoria = new Audit();
        auditoria.setAction(accio);
        auditoria.setRule(rule.getDescription());
        auditoria.setAuthor(codiUsuari);
        auditoria.setObject("SC_RULE"); //$NON-NLS-1$
        getAuditEntityDao().create(getAuditEntityDao().auditToEntity(auditoria));
    }

    private void auditRuleRole(String accio, RuleAssignedRoleEntity role) {
        String codiUsuari = Security.getCurrentAccount();
        Audit auditoria = new Audit();
        auditoria.setAction(accio);
        auditoria.setRule(role.getRule().getDescription());
        auditoria.setRole(role.getRole().getName());
        auditoria.setDatabase(role.getRole().getSystem().getName());
        auditoria.setAuthor(codiUsuari);
        auditoria.setObject("SC_RULROL"); //$NON-NLS-1$
        getAuditEntityDao().create(getAuditEntityDao().auditToEntity(auditoria));
    }

	@Override
	protected String handleGenerateChangesReport(Rule rule, Collection<RuleAssignedRole> grants) throws Exception {
		RuleEntity ruleEntity = getRuleEntityDao().newRuleEntity();
		getRuleEntityDao().ruleToEntity(rule, ruleEntity, true);
		ruleEntity.setId(rule.getId());
		for (RuleAssignedRole rar: grants)
		{
			RuleAssignedRoleEntity rare = getRuleAssignedRoleEntityDao().newRuleAssignedRoleEntity();
			getRuleAssignedRoleEntityDao().ruleAssignedRoleToEntity(rar, rare, true);
			ruleEntity.getRoles().add(rare);
		}
		File f = getRuleEvaluatorService().dryRun(ruleEntity);
		return f.getAbsolutePath();
	}

	@Override
	protected ApplyRuleProcess handleQueryProcessStatus(ApplyRuleProcess process) throws Exception {
		return getRuleEvaluatorService().queryProcessStatus(process);
	}

}
