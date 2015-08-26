/**
 * 
 */
/**
 * 
 */
package es.caib.seycon.ng.servei;

import com.soffid.iam.model.InformationSystemEntity;
import com.soffid.iam.model.RoleEntity;
import com.soffid.iam.model.SoDRoleEntity;
import com.soffid.iam.model.SoDRuleEntity;
import com.soffid.iam.model.UserEntity;
import es.caib.seycon.ng.comu.RolAccount;
import es.caib.seycon.ng.comu.RolGrant;
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
public class SoDRuleServiceImpl extends SoDRuleServiceBase
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
	protected SoDRule handleIsAllowed (RolAccount ra) throws Exception
	{
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
	protected void handleQualifyRolAccountList (List<RolAccount> ra) throws Exception
	{
		LinkedList<RolGrant> targetList = null;
		for (RolAccount rolAccount : ra) {
            RoleEntity role = getRoleEntityDao().findByNameAndSystem(rolAccount.getNomRol(), rolAccount.getBaseDeDades());
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

	private LinkedList<RolGrant> generateTargetList (List<RolAccount> ra,
					RolAccount rolAccount) throws InternalErrorException
	{
		LinkedList<RolGrant> targetList;
		targetList = new LinkedList<RolGrant>();
		if (rolAccount.getCodiUsuari() == null)
		{
			targetList.addAll( getAplicacioService().findEffectiveRolGrantByAccount(rolAccount.getAccountId()) );
		}
		else
		{
			UserEntity usuari = getUserEntityDao().findByUserName(rolAccount.getCodiUsuari());
			targetList. addAll (getAplicacioService().findEffectiveRolGrantByUser(usuari.getId()));
		}
		for (RolAccount rolAccount2: ra)
		{
			boolean found = false;
			for (RolGrant rolGrant: targetList)
			{
				if (rolGrant.getId() != null && rolGrant.getId().equals (rolAccount2.getId()))
				{
					found = true;
					break;
				}
			}
			if (! found)
			{
				RolGrant rolGrant = new RolGrant ();
				rolGrant.setDispatcher(rolAccount2.getBaseDeDades());
				rolGrant.setRolName(rolAccount2.getNomRol());
				rolGrant.setUser(rolAccount2.getCodiUsuari());
				rolGrant.setOwnerAccountName(rolAccount2.getAccountName());
				rolGrant.setOwnerDispatcher(rolAccount2.getAccountDispatcher());
				targetList.add(rolGrant);
			}
		}
		return targetList;
	}

	protected Collection<SoDRuleEntity> doFindAffectingRulesByRolAccount (RolAccount ra)
					throws Exception
	{
		RoleEntity role = getRoleEntityDao().findByNameAndSystem(ra.getNomRol(), ra.getBaseDeDades());
		if (role == null)
			return Collections.emptyList();
		else if (role.getSodRules().isEmpty())
			return Collections.emptyList();
		else
		{
			Collection<RolGrant> rols;
			if (ra.getCodiUsuari() == null)
			{
    			rols = getAplicacioService().findEffectiveRolGrantByAccount(ra.getAccountId());
			}
			else
			{
    			UserEntity usuari = getUserEntityDao().findByUserName(ra.getCodiUsuari());
    			if (usuari == null)
    				rols = new LinkedList<RolGrant>();
    			else
    				rols = getAplicacioService().findEffectiveRolGrantByUser(usuari.getId());
			}
			return doFindSodNonCompliances (role, rols);
		}
	}
	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.SoDRuleServiceBase#handleFindAffectingRulesByRolAccount(es.caib.seycon.ng.comu.RolAccount)
	 */
	@Override
	protected Collection<SoDRule> handleFindAffectingRulesByRolAccount (RolAccount ra)
					throws Exception
	{
		return getSoDRuleEntityDao().toSoDRuleList(doFindAffectingRulesByRolAccount(ra));
	}

	/**
	 * @param role
	 * @param rols
	 * @return
	 */
	private Collection<SoDRuleEntity> doFindSodNonCompliances(RoleEntity role, Collection<RolGrant> rols) {
		List<SoDRuleEntity> rules = new LinkedList<SoDRuleEntity>();
		for (SoDRoleEntity sourceSodRole : role.getSodRules()) {
            SoDRuleEntity rule = sourceSodRole.getRule();
            if (!rule.getRoles().isEmpty()) {
                boolean add = true;
                for (SoDRoleEntity targetSodRole : rule.getRoles()) {
                    if (targetSodRole.getId().equals(sourceSodRole.getId())) {
                    } else {
                        boolean found = false;
                        for (RolGrant rolGrant : rols) {
                            if (rolGrant.getRolName().equals(targetSodRole.getRole().getName()) && rolGrant.getDispatcher().equals(targetSodRole.getRole().getSystem().getName())) {
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
