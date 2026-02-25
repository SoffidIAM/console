//
// (C) 2013 Soffid
//
//

package com.soffid.iam.rc.service;
/**
 * Service SoDRuleService
 */
public interface SoDRuleService {
	public final static String SERVICE_NAME = "com.soffid.iam.rc.service.SoDRuleService";

	/**
	 * Operation isGreater

	 * @param first 
	 * @param second 
	 * @return 
	 */
	boolean isGreater(
		final com.soffid.iam.rc.api.SoDRisk first, 
		final com.soffid.iam.rc.api.SoDRisk second)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation qualifyUser

	 * @param ra 
	 * @return 
	 */
	com.soffid.iam.rc.api.SoDRisk qualifyUser(
		final java.util.Collection<com.soffid.iam.iga.api.RoleAccount> ra)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation create

	 * @param role 
	 * @return 
	 */
	com.soffid.iam.rc.api.SoDRole create(
		final com.soffid.iam.rc.api.SoDRole role)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation create

	 * @param rule 
	 * @return 
	 */
	com.soffid.iam.rc.api.SoDRule create(
		final com.soffid.iam.rc.api.SoDRule rule)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getRuleById

	 * @param ruleId 
	 * @return 
	 */
	com.soffid.iam.rc.api.SoDRule getRuleById(
		final java.lang.Long ruleId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation isAllowed

	 * @param ra 
	 * @return 
	 */
	com.soffid.iam.rc.api.SoDRule isAllowed(
		final com.soffid.iam.iga.api.RoleAccount ra)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation update

	 * @param rule 
	 * @return 
	 */
	com.soffid.iam.rc.api.SoDRule update(
		final com.soffid.iam.rc.api.SoDRule rule)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation create

	 * @param role 
	 * @return 
	 */
	com.soffid.iam.rc.api.SoDRuleMatrix create(
		final com.soffid.iam.rc.api.SoDRuleMatrix role)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation update

	 * @param role 
	 * @return 
	 */
	com.soffid.iam.rc.api.SoDRuleMatrix update(
		final com.soffid.iam.rc.api.SoDRuleMatrix role)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findSodRules

	 * @param query 
	 * @return 
	 */
	com.soffid.zkdb.api.PagedResult<com.soffid.iam.rc.api.SoDRule> findSodRules(
		final com.soffid.zkdb.api.Query query)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation generateChangesReport

	 * @param rule 
	 * @param grants 
	 * @param matrix 
	 * @return 
	 */
	java.lang.String generateChangesReport(
		final com.soffid.iam.rc.api.SoDRule rule, 
		final java.util.Collection<com.soffid.iam.rc.api.SoDRole> grants, 
		final java.util.Collection<com.soffid.iam.rc.api.SoDRuleMatrix> matrix)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findAffectingRulesByRolAccount

	 * @param ra 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.rc.api.SoDRule> findAffectingRulesByRolAccount(
		final com.soffid.iam.iga.api.RoleAccount ra)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findMatrixByRule

	 * @param ruleId 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.rc.api.SoDRuleMatrix> findMatrixByRule(
		final java.lang.Long ruleId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findRolesByRule

	 * @param ruleId 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.rc.api.SoDRole> findRolesByRule(
		final java.lang.Long ruleId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findRuleByApplication

	 * @param applicationId 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.rc.api.SoDRule> findRuleByApplication(
		final java.lang.Long applicationId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findViolotions

	 * @param applicationName 
	 * @param riskLevel 
	 * @return 
	 */
	java.util.List<com.soffid.iam.iga.api.RoleAccount> findViolotions(
		final java.lang.String applicationName, 
		final com.soffid.iam.rc.api.SoDRisk riskLevel)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation internalRemovingRole

	 * @param roleId 
	 */
	void internalRemovingRole(
		final java.lang.Long roleId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation qualifyRolAccountList

	 * @param ra 
	 */
	void qualifyRolAccountList(
		final java.util.List<com.soffid.iam.iga.api.RoleAccount> ra)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation remove

	 * @param role 
	 */
	void remove(
		final com.soffid.iam.rc.api.SoDRole role)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation remove

	 * @param rule 
	 */
	void remove(
		final com.soffid.iam.rc.api.SoDRule rule)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation remove

	 * @param role 
	 */
	void remove(
		final com.soffid.iam.rc.api.SoDRuleMatrix role)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

}
