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

import es.caib.seycon.ng.servei.*;

import com.soffid.iam.api.RoleAccount;
import com.soffid.iam.api.RoleGrant;
import com.soffid.iam.model.InformationSystemEntity;
import com.soffid.iam.model.RoleDependencyEntity;
import com.soffid.iam.model.RoleEntity;
import com.soffid.iam.model.SoDRoleEntity;
import com.soffid.iam.model.SoDRuleEntity;
import com.soffid.iam.model.UserEntity;

import es.caib.seycon.ng.comu.SoDRisk;
import es.caib.seycon.ng.comu.SoDRole;
import es.caib.seycon.ng.comu.SoDRule;
import es.caib.seycon.ng.exception.InternalErrorException;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author bubu
 *
 */
public class SoDRuleServiceImpl extends com.soffid.iam.service.SoDRuleServiceBase
{

	/**
	 * 
	 */
	public SoDRuleServiceImpl ()
	{
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.SoDRuleServiceBase#handleFindRuleByApplication(java.lang.Long)
	 */
	@Override
	protected Collection<SoDRule> handleFindRuleByApplication (Long applicationId)
					throws Exception
	{
		InformationSystemEntity app = getInformationSystemEntityDao().load(applicationId);
		return getSoDRuleEntityDao().toSoDRuleList(app.getSodRules());
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.SoDRuleServiceBase#handleFindRolesByRule(java.lang.Long)
	 */
	@SuppressWarnings ("unchecked")
	@Override
	protected Collection<SoDRole> handleFindRolesByRule (Long ruleId) throws Exception
	{
		SoDRuleEntity rule = getSoDRuleEntityDao().load(ruleId);
		if (rule == null)
			return Collections.EMPTY_LIST;
		else
			return getSoDRoleEntityDao().toSoDRoleList(rule.getRoles());
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.SoDRuleServiceBase#handleCreate(es.caib.seycon.ng.comu.SoDRule)
	 */
	@Override
	protected SoDRule handleCreate (SoDRule rule) throws Exception
	{
		SoDRuleEntity entity  = getSoDRuleEntityDao().soDRuleToEntity(rule);
		getSoDRuleEntityDao().create(entity);
		return getSoDRuleEntityDao().toSoDRule(entity);
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.SoDRuleServiceBase#handleUpdate(es.caib.seycon.ng.comu.SoDRule)
	 */
	@Override
	protected SoDRule handleUpdate (SoDRule rule) throws Exception
	{
		SoDRuleEntity entity  = getSoDRuleEntityDao().soDRuleToEntity(rule);
		getSoDRuleEntityDao().update(entity);
		return getSoDRuleEntityDao().toSoDRule(entity);
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.SoDRuleServiceBase#handleRemove(es.caib.seycon.ng.comu.SoDRule)
	 */
	@Override
	protected void handleRemove (SoDRule rule) throws Exception
	{
		SoDRuleEntity entity  = getSoDRuleEntityDao().load(rule.getId());
		for (SoDRoleEntity role: entity.getRoles())
		{
			getSoDRoleEntityDao().remove(role);
		}
		getSoDRuleEntityDao().remove(entity);
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.SoDRuleServiceBase#handleCreate(es.caib.seycon.ng.comu.SoDRole)
	 */
	@Override
	protected SoDRole handleCreate (SoDRole role) throws Exception
	{
		SoDRoleEntity entity  = getSoDRoleEntityDao().soDRoleToEntity(role);
		getSoDRoleEntityDao().create(entity);
		
		
		return getSoDRoleEntityDao().toSoDRole(entity);
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.SoDRuleServiceBase#handleRemove(es.caib.seycon.ng.comu.SoDRole)
	 */
	@Override
	protected void handleRemove (SoDRole role) throws Exception
	{
		SoDRoleEntity entity  = getSoDRoleEntityDao().load (role.getId());
		getSoDRoleEntityDao().remove(entity);
	}

	private boolean isGreater (SoDRisk first, SoDRisk second)
	{
		if (first == second)
			return false;
		if (first == SoDRisk.SOD_FORBIDDEN)
			return true;
		if (second == SoDRisk.SOD_FORBIDDEN)
			return false;
		if (first == SoDRisk.SOD_HIGH)
			return true;
		if (second == SoDRisk.SOD_HIGH)
			return false;
		if (first == SoDRisk.SOD_LOW)
			return true;
		return false;
	}
	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.SoDRuleServiceBase#handleIsAllowed(es.caib.seycon.ng.comu.RolAccount)
	 */
	@Override
    protected SoDRule handleIsAllowed(RoleAccount ra) throws Exception {
		SoDRuleEntity affectingRule = null;
		for (SoDRuleEntity rule: doFindAffectingRulesByRolAccount(ra))
		{
			if (rule.getRisk() == SoDRisk.SOD_FORBIDDEN)
			{
				affectingRule  = rule;
				break;
			}
			else if (rule.getRisk() == SoDRisk.SOD_HIGH)
			{
				affectingRule  = rule;
			}
			else if (rule.getRisk() == SoDRisk.SOD_LOW && affectingRule == null)
			{
				affectingRule  = rule;
			}
		}
		if (affectingRule == null)
			return null;
		else
			return getSoDRuleEntityDao().toSoDRule(affectingRule);
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.SoDRuleServiceBase#handleQualifyRolAccountList(java.util.List)
	 */
	@Override
    protected void handleQualifyRolAccountList(List<RoleAccount> ra) throws Exception {
		LinkedList<RoleGrant> targetList = null;
		for (RoleAccount rolAccount : ra) {
            RoleEntity role = getRoleEntityDao().findByNameAndSystem(rolAccount.getRoleName(), rolAccount.getSystem());
            if (role != null && !role.getSodRules().isEmpty()) {
                if (targetList == null) {
                    targetList = generateTargetList(ra, rolAccount);
                }
                SoDRisk risk = null;
                Collection<SoDRuleEntity> rules = doFindSodNonCompliances(role, targetList);
                for (SoDRuleEntity rule : rules) {
                    if (risk == null || isGreater(rule.getRisk(), risk)) risk = rule.getRisk();
                }
                rolAccount.setSodRisk(risk);
                rolAccount.setSodRules(getSoDRuleEntityDao().toSoDRuleList(rules));
            } else {
                rolAccount.setSodRisk(null);
                rolAccount.setSodRules(null);
            }
        }
	}

	private LinkedList<RoleGrant> generateTargetList(List<RoleAccount> ra, RoleAccount rolAccount) throws InternalErrorException {
		LinkedList<RoleGrant> targetList;
		targetList = new LinkedList<RoleGrant>();
		if (rolAccount.getUserCode() == null)
		{
			targetList.addAll(getApplicationService().findEffectiveRoleGrantByAccount(rolAccount.getAccountId()));
		}
		else
		{
			UserEntity usuari = getUserEntityDao().findByUserName(rolAccount.getUserCode());
			targetList.addAll(getApplicationService().findEffectiveRoleGrantByUser(usuari.getId()));
		}
		for (RoleAccount rolAccount2 : ra) {
            boolean found = false;
            for (RoleGrant rolGrant : targetList) {
                if (rolGrant.getId() != null && rolGrant.getId().equals(rolAccount2.getId())) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                RoleGrant rolGrant = new RoleGrant();
                rolGrant.setSystem(rolAccount2.getSystem());
                rolGrant.setRoleName(rolAccount2.getRoleName());
                rolGrant.setUser(rolAccount2.getUserCode());
                rolGrant.setOwnerAccountName(rolAccount2.getAccountName());
                rolGrant.setOwnerSystem(rolAccount2.getAccountSystem());
                targetList.add(rolGrant);
            }
        }
		return targetList;
	}

	protected Collection<SoDRuleEntity> doFindAffectingRulesByRolAccount(RoleAccount ra) throws Exception {
		RoleEntity role = getRoleEntityDao().findByNameAndSystem(ra.getRoleName(), ra.getSystem());
		if (role == null)
			return Collections.emptyList();
		else if (role.getSodRules().isEmpty())
			return Collections.emptyList();
		else
		{
			Collection<RoleGrant> rols;
			if (ra.getUserCode() == null)
			{
    			rols = getApplicationService().findEffectiveRoleGrantByAccount(ra.getAccountId());
			}
			else
			{
    			UserEntity usuari = getUserEntityDao().findByUserName(ra.getUserCode());
    			if (usuari == null)
    				rols = new LinkedList<RoleGrant>();
    			else
    				rols = getApplicationService().findEffectiveRoleGrantByUser(usuari.getId());
			}
			return doFindSodNonCompliances (role, rols);
		}
	}
	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.SoDRuleServiceBase#handleFindAffectingRulesByRolAccount(es.caib.seycon.ng.comu.RolAccount)
	 */
	@Override
    protected Collection<SoDRule> handleFindAffectingRulesByRolAccount(RoleAccount ra) throws Exception {
		return getSoDRuleEntityDao().toSoDRuleList(doFindAffectingRulesByRolAccount(ra));
	}

	/**
	 * @param role
	 * @param rols
	 * @return
	 */
	private Collection<SoDRuleEntity> doFindSodNonCompliances(RoleEntity role, Collection<RoleGrant> rols) {
		List<SoDRuleEntity> rules = new LinkedList<SoDRuleEntity>();
		for (SoDRoleEntity sourceSodRole : role.getSodRules()) {
            SoDRuleEntity rule = sourceSodRole.getRule();
            if (!rule.getRoles().isEmpty()) {
                boolean add = true;
                for (SoDRoleEntity targetSodRole : rule.getRoles()) {
                    if (targetSodRole.getId().equals(sourceSodRole.getId())) {
                    } else {
                        boolean found = false;
                        for (RoleGrant rolGrant : rols) {
                            if (rolGrant.getRoleName().equals(targetSodRole.getRole().getName()) && rolGrant.getSystem().equals(targetSodRole.getRole().getSystem().getName())) {
                                found = true;
                                break;
                            }
                        }
                        if (!found) {
                            add = false;
                            break;
                        }
                    }
                }
                if (add) {
                    rules.add(rule);
                }
            }
        }
		// Now check inherited rules
		for ( RoleDependencyEntity childRole: role.getContainedRoles()) 
		{
			rules.addAll(doFindSodNonCompliances(childRole.getContained(), rols));
		}
		return rules;
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.SoDRuleServiceBase#handleGerRuleById(java.lang.Long)
	 */
	@Override
	protected SoDRule handleGetRuleById (Long ruleId) throws Exception
	{
		SoDRuleEntity rule = getSoDRuleEntityDao().load(ruleId);
		if (rule == null)
			return null;
		return getSoDRuleEntityDao().toSoDRule(rule);
		
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.SoDRuleServiceBase#handleInternalRemovingRole(es.caib.seycon.ng.model.RolEntity)
	 */
	@Override
	protected void handleInternalRemovingRole (Long roleId) throws Exception
	{
		RoleEntity role = getRoleEntityDao().load(roleId);
        for (SoDRoleEntity sodRole: role.getSodRules())
        {
        	SoDRuleEntity sodRule = sodRole.getRule();
        	sodRule.setRisk(SoDRisk.SOD_NA);
        	getSoDRuleEntityDao().update(sodRule);
        	getSoDRoleEntityDao().remove(sodRole);
        }
	}

}
