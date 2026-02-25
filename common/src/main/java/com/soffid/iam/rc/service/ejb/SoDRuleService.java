//
// (C) 2013 Soffid
//
//

package com.soffid.iam.rc.service.ejb;
/**
 * EJB SoDRuleService
 */
public interface SoDRuleService

 {

	com.soffid.iam.rc.api.SoDRole create(
		final com.soffid.iam.rc.api.SoDRole role)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.rc.api.SoDRule create(
		final com.soffid.iam.rc.api.SoDRule rule)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.rc.api.SoDRule getRuleById(
		final java.lang.Long ruleId)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.rc.api.SoDRule isAllowed(
		final com.soffid.iam.iga.api.RoleAccount ra)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.rc.api.SoDRule update(
		final com.soffid.iam.rc.api.SoDRule rule)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.rc.api.SoDRuleMatrix create(
		final com.soffid.iam.rc.api.SoDRuleMatrix role)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.rc.api.SoDRuleMatrix update(
		final com.soffid.iam.rc.api.SoDRuleMatrix role)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.zkdb.api.PagedResult<com.soffid.iam.rc.api.SoDRule> findSodRules(
		final com.soffid.zkdb.api.Query query)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.lang.String generateChangesReport(
		final com.soffid.iam.rc.api.SoDRule rule, 
		final java.util.Collection<com.soffid.iam.rc.api.SoDRole> grants, 
		final java.util.Collection<com.soffid.iam.rc.api.SoDRuleMatrix> matrix)
	throws com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.rc.api.SoDRule> findAffectingRulesByRolAccount(
		final com.soffid.iam.iga.api.RoleAccount ra)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.rc.api.SoDRuleMatrix> findMatrixByRule(
		final java.lang.Long ruleId)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.rc.api.SoDRole> findRolesByRule(
		final java.lang.Long ruleId)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.rc.api.SoDRule> findRuleByApplication(
		final java.lang.Long applicationId)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.List<com.soffid.iam.iga.api.RoleAccount> findViolotions(
		final java.lang.String applicationName, 
		final com.soffid.iam.rc.api.SoDRisk riskLevel)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void qualifyRolAccountList(
		final java.util.List<com.soffid.iam.iga.api.RoleAccount> ra)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void remove(
		final com.soffid.iam.rc.api.SoDRole role)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void remove(
		final com.soffid.iam.rc.api.SoDRule rule)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void remove(
		final com.soffid.iam.rc.api.SoDRuleMatrix role)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

}
