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

import com.soffid.iam.api.Application;
import com.soffid.iam.api.AsyncList;
import com.soffid.iam.api.RoleAccount;
import com.soffid.iam.api.RoleGrant;
import com.soffid.iam.model.CustomDialect;
import com.soffid.iam.model.InformationSystemEntity;
import com.soffid.iam.model.Parameter;
import com.soffid.iam.model.RoleAccountEntity;
import com.soffid.iam.model.RoleDependencyEntity;
import com.soffid.iam.model.RoleEntity;
import com.soffid.iam.model.SoDRoleEntity;
import com.soffid.iam.model.SoDRuleEntity;
import com.soffid.iam.model.SoDRuleMatrixEntity;
import com.soffid.iam.model.UserAccountEntity;
import com.soffid.iam.model.UserEntity;
import com.soffid.iam.model.criteria.CriteriaSearchConfiguration;
import com.soffid.iam.utils.Security;
import com.soffid.iam.utils.TimeOutUtils;
import com.soffid.scimquery.EvalException;
import com.soffid.scimquery.HQLQuery;
import com.soffid.scimquery.expr.AbstractExpression;
import com.soffid.scimquery.parser.ExpressionParser;
import com.soffid.scimquery.parser.ParseException;

import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.comu.SoDRisk;
import com.soffid.iam.api.SoDRole;
import com.soffid.iam.api.SoDRule;
import com.soffid.iam.api.SoDRuleMatrix;
import com.soffid.iam.api.SodRuleType;

import es.caib.seycon.ng.exception.InternalErrorException;

import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONException;

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
	 * @see es.caib.seycon.ng.servei.SoDRuleServiceBase#handleFindRolesByRule(java.lang.Long)
	 */
	@SuppressWarnings ("unchecked")
	@Override
	protected Collection<SoDRuleMatrix> handleFindMatrixByRule (Long ruleId) throws Exception
	{
		SoDRuleEntity rule = getSoDRuleEntityDao().load(ruleId);
		if (rule == null)
			return Collections.EMPTY_LIST;
		else
			return getSoDRuleMatrixEntityDao().toSoDRuleMatrixList(rule.getMatrixCells());
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
		SoDRisk risk = null;
		for (AppliedRule rule: doFindAffectingRulesByRolAccount(ra))
		{
			if (rule.cell != null) {
				if (rule.cell.getRisk() == SoDRisk.SOD_FORBIDDEN)
				{
					affectingRule  = rule.rule;
					risk = rule.cell.getRisk();
					break;
				}
				else if (rule.cell.getRisk() == SoDRisk.SOD_HIGH)
				{
					risk = rule.cell.getRisk();
					affectingRule  = rule.rule;
				}
				else if (rule.cell.getRisk() == SoDRisk.SOD_LOW && affectingRule == null)
				{
					risk = rule.cell.getRisk();
					affectingRule  = rule.rule;
				}
			}
			else if (rule.rule.getRisk() == SoDRisk.SOD_FORBIDDEN)
			{
				affectingRule  = rule.rule;
				risk = rule.rule.getRisk();
				break;
			}
			else if (rule.rule.getRisk() == SoDRisk.SOD_HIGH)
			{
				affectingRule  = rule.rule;
				risk = rule.rule.getRisk();
			}
			else if (rule.rule.getRisk() == SoDRisk.SOD_LOW && affectingRule == null)
			{
				affectingRule  = rule.rule;
				risk = rule.rule.getRisk();
			}
		}
		if (affectingRule == null)
			return null;
		else {
			final SoDRule soDRule = getSoDRuleEntityDao().toSoDRule(affectingRule);
			soDRule.setRisk(risk);
			return soDRule;
		}
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.SoDRuleServiceBase#handleQualifyRolAccountList(java.util.List)
	 */
	@Override
    protected void handleQualifyRolAccountList(List<RoleAccount> ra) throws Exception {
		LinkedList<RoleGrant> targetList = null;
		for (RoleAccount rolAccount : ra) {
            RoleEntity role = getRoleEntityDao().findByNameAndSystem(rolAccount.getRoleName(), rolAccount.getSystem());
            if (role != null) {
                if (targetList == null) {
                    targetList = generateTargetList(ra, rolAccount);
                }
                SoDRisk risk = null;
                Collection<AppliedRule> rules = doFindSodNonCompliances(role, targetList);
                LinkedList<SoDRule> l = new LinkedList<SoDRule>();
                for (AppliedRule rule : rules) {
                	SoDRisk ruleRisk = rule.cell != null ? rule.cell.getRisk() : rule.rule.getRisk();
                	if (ruleRisk != null && ruleRisk != SoDRisk.SOD_NA) {
                		final SoDRule soDRule = getSoDRuleEntityDao().toSoDRule(rule.rule);
                		soDRule.setRisk(ruleRisk);
						l.add(soDRule);
                		if (risk == null || isGreater(ruleRisk, risk)) 
                			risk = ruleRisk;
                	}
                }
                rolAccount.setSodRisk(risk);
                rolAccount.setSodRules(l);
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
                RoleEntity role = getRoleEntityDao().findByNameAndSystem(rolAccount2.getRoleName(), rolAccount2.getSystem());
                if (role != null)
                	rolGrant.setRoleId(role.getId());
                targetList.add(rolGrant);
            }
        }
		return targetList;
	}

	protected Collection<AppliedRule> doFindAffectingRulesByRolAccount(RoleAccount ra) throws Exception {
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
		Collection<SoDRule> rules = new LinkedList<>();
		for (AppliedRule appliedRule: doFindAffectingRulesByRolAccount(ra)) {
			rules.add( getSoDRuleEntityDao().toSoDRule(appliedRule.rule));
		}
		return rules;
	}

	/**
	 * @param role
	 * @param rols
	 * @return
	 */
	private Collection<AppliedRule> doFindSodNonCompliances(RoleEntity role, Collection<RoleGrant> rols) {
		List<AppliedRule> rules = new LinkedList<AppliedRule>();
		for (SoDRoleEntity sourceSodRole : role.getSodRules()) {
            SoDRuleEntity rule = sourceSodRole.getRule();
            if ((rule.getNumber() == null || rule.getNumber().intValue() > 0) && 
            		(!rule.getRoles().isEmpty())) {
            	int failures = rule.getNumber() == null ? 0 : rule.getRoles().size() - rule.getNumber().intValue();
                boolean add = true;
                
                if (rule.getType() == SodRuleType.MATCH_MATRIX) {
	                for (SoDRuleMatrixEntity cell : rule.getMatrixCells()) {
	                    if (cell.getRow().getId().equals(sourceSodRole.getId())) {
	                        for (RoleGrant rolGrant : rols) {
	                        	if (rolGrant.getRoleId() != null ?
	                        			rolGrant.getRoleId().equals(cell.getColumn().getRole().getId()) :
	                        		rolGrant.getSystem() != null && rolGrant.getRoleName() != null ?
	                        			rolGrant.getRoleName().equals(cell.getColumn().getRole().getName()) &&
	                        			rolGrant.getSystem().equals(cell.getColumn().getRole().getSystem().getName()):
	                        			false) 
								{
	                            	rules.add(new AppliedRule(rule, cell));
	                            }
	                        }	                    	
	                    }
	                    if (cell.getColumn().getId().equals(sourceSodRole.getId())) {
	                        for (RoleGrant rolGrant : rols) {
	                            if (rolGrant.getRoleId() != null ?
	                            		rolGrant.getRoleId().equals(cell.getRow().getRole().getId()) :
	                            			rolGrant.getRoleName().equals(cell.getRow().getRole().getName()) &&
		                        			rolGrant.getSystem().equals(cell.getRow().getRole().getSystem().getName())) {
	                            	rules.add(new AppliedRule(rule, cell));
	                            }
	                        }	                    	
	                    }
	                }
                } else {
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
	                        	if (failures > 0)
	                        		failures --;
	                        	else
	                        	{
		                            add = false;
		                            break;
	                        	}
	                        }
	                    }
	                }
	                if (add) {
	                    rules.add(new AppliedRule(rule, null));
	                }
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

	@Override
	protected List<SoDRule> handleFindSodRuleByJsonQuery(String query, Integer first, Integer pageSize) throws Exception {
		AsyncList<SoDRule> result = new AsyncList<SoDRule>();
		result.setTimeout(TimeOutUtils.getGlobalTimeOut());
		findSoDRuleByJsonQuery(result, query, first, pageSize);
		if (result.isCancelled())
			TimeOutUtils.generateException();
		result.done();
		return result.get();
	}

	@Override
	protected AsyncList<SoDRule> handleFindSodRuleByJsonQueryAsync(final String query) throws Exception {
		final AsyncList<SoDRule> result = new AsyncList<SoDRule>();
		getAsyncRunnerService().run(new Runnable() {
			public void run() {
				try {
					findSoDRuleByJsonQuery(result, query, null, null);
				} catch (Exception e) {
					result.cancel(e);
				}
			}
		}, result);
		return result;
	}

	protected void findSoDRuleByJsonQuery ( AsyncList<SoDRule> result, String query, Integer first, Integer pageSize) 
			throws EvalException, InternalErrorException, UnsupportedEncodingException, ClassNotFoundException, JSONException, ParseException
	{

		// Prepare query HQL
		AbstractExpression expr = ExpressionParser.parse(query);
		expr.setOracleWorkaround( new CustomDialect().isOracle());
		HQLQuery hql = expr.generateHSQLString(SoDRule.class);
		String qs = hql.getWhereString().toString();
		if (qs.isEmpty())
			qs = "o.application.tenant.id = :tenantId";
		else
			qs = "(" + qs + ") and o.application.tenant.id = :tenantId";
		hql.setWhereString(new StringBuffer(qs));

		// Include HQL parameters
		Map<String, Object> params = hql.getParameters();
		Parameter paramArray[] = new Parameter[params.size() + 1];
		int i = 0;
		for (String s : params.keySet())
			paramArray[i++] = new Parameter(s, params.get(s));
		paramArray[i++] = new Parameter("tenantId", Security.getCurrentTenantId());

		CriteriaSearchConfiguration cfg = new CriteriaSearchConfiguration();
		cfg.setFirstResult(first);
		cfg.setMaximumResultSize(pageSize);
		// Execute HQL and generate result
		for (SoDRuleEntity ruleEntity : getSoDRuleEntityDao().query(hql.toString(), paramArray, cfg )) {
			if (result.isCancelled())
				return;
			SoDRule rule = getSoDRuleEntityDao().toSoDRule(ruleEntity);
			if (!hql.isNonHQLAttributeUsed() || expr.evaluate(rule)) {
					result.add(rule);
			}
		}
	}

	@Override
	protected SoDRuleMatrix handleCreate(SoDRuleMatrix role) throws Exception {
		SoDRuleMatrixEntity entity  = getSoDRuleMatrixEntityDao().soDRuleMatrixToEntity(role);
		getSoDRuleMatrixEntityDao().create(entity);
		
		
		return getSoDRuleMatrixEntityDao().toSoDRuleMatrix(entity);
	}

	@Override
	protected SoDRuleMatrix handleUpdate(SoDRuleMatrix role) throws Exception {
		SoDRuleMatrixEntity entity  = getSoDRuleMatrixEntityDao().soDRuleMatrixToEntity(role);
		getSoDRuleMatrixEntityDao().update(entity);
		
		
		return getSoDRuleMatrixEntityDao().toSoDRuleMatrix(entity);
	}

	@Override
	protected void handleRemove(SoDRuleMatrix role) throws Exception {
		getSoDRuleMatrixEntityDao().remove(role.getId());
	}

	@Override
	protected List<RoleAccount> handleFindViolotions(String applicationName, SoDRisk riskLevel) throws Exception {
		ViolationHandler h = new ViolationHandler();
		for (InformationSystemEntity app: getInformationSystemEntityDao().loadAll()) {
			if (applicationName == null || 
					app.getName().equals(applicationName) ||
					app.getName().startsWith(applicationName+"/")) {
				h.analyze(applicationName, app, riskLevel);
			}
		}
		return h.results;
	}

	class ViolationHandler {
		List<RoleAccount> results = new LinkedList<>();
		Set<Long> users = new HashSet<>();
		Set<Long> accounts = new HashSet<>();
		
		void analyze(String applicationName, InformationSystemEntity app, SoDRisk level) throws InternalErrorException {
			for (SoDRuleEntity rule: app.getSodRules()) {
				boolean skip = true;
				if (rule.getType() == SodRuleType.MATCH_MATRIX) {
					for (SoDRuleMatrixEntity cell: rule.getMatrixCells()) {
						if (cell.getRisk() == level) {
							skip = false;
							break;
						}
					}
				}
				else
				{
					skip = rule.getRisk() != level;
				}
				if (!skip) {
					for (SoDRoleEntity sodRole: rule.getRoles()) {
						for (RoleAccountEntity grant: sodRole.getRole().getAccounts()) {
							if (grant.isEnabled() && !grant.getAccount().isDisabled()) {
								if (grant.getAccount().getType() == AccountType.USER) {
									for (UserAccountEntity ua: grant.getAccount().getUsers()) {
										UserEntity user = ua.getUser();
										if (!users.contains(user.getId())) {
											users.add(user.getId());
											Collection<RoleAccount> grants = getApplicationService().findUserRolesByUserName(user.getUserName());
											filter (applicationName, grants, level);
										}
									}
								} else {
									if (!accounts.contains(grant.getAccount().getId())) {
										Collection<RoleAccount> grants = getApplicationService().findRoleAccountByAccount(grant.getAccount().getId());
										filter(applicationName, grants, level);
									}
								}
							}
						}
					}
				}
			}
		}

		private void filter(String applicationName, Collection<RoleAccount> grants, SoDRisk level) {
			for (RoleAccount grant: grants) {
				if (grant.getSodRisk() == level) {
					if (applicationName == null ||
							grant.getInformationSystemName().equals(applicationName) ||
							grant.getInformationSystemName().startsWith(applicationName+"/"))
						results.add(grant);
				}
			}
		}
	}
}


class AppliedRule {
	SoDRuleEntity rule;
	SoDRuleMatrixEntity cell;
	public AppliedRule(SoDRuleEntity rule, SoDRuleMatrixEntity cell) {
		super();
		this.rule = rule;
		this.cell = cell;
	}
}

