//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.service;
/**
 * Service RulesService
 */
public interface RulesService {
	public final static String REMOTE_PATH = "/seycon/com.soffid.iam.iga.service.RulesService";

	public final static String SERVICE_NAME = "com.soffid.iam.iga.service.RulesService";

	/**
	 * Operation applyAsync

	 * @param rule 
	 * @return 
	 */
	com.soffid.iam.base.api.AsyncProcessTracker applyAsync(
		final com.soffid.iam.iga.api.Rule rule)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation queryProcessStatus
	 * Query the rule process status

	 * @param process 
	 * @return 
	 */
	com.soffid.iam.base.api.AsyncProcessTracker queryProcessStatus(
		final com.soffid.iam.base.api.AsyncProcessTracker process)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation create

	 * @param rule 
	 * @return 
	 */
	com.soffid.iam.iga.api.Rule create(
		final com.soffid.iam.iga.api.Rule rule)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation update

	 * @param rule 
	 * @return 
	 */
	com.soffid.iam.iga.api.Rule update(
		final com.soffid.iam.iga.api.Rule rule)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation create

	 * @param ruleAssignment 
	 * @return 
	 */
	com.soffid.iam.iga.api.RuleAssignedRole create(
		final com.soffid.iam.iga.api.RuleAssignedRole ruleAssignment)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation update

	 * @param ruleAssignment 
	 * @return 
	 */
	com.soffid.iam.iga.api.RuleAssignedRole update(
		final com.soffid.iam.iga.api.RuleAssignedRole ruleAssignment)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation generateChangesReport
	 * Generates an excel file with expected changes

	 * @param rule 
	 * @param grants 
	 * @return 
	 */
	java.lang.String generateChangesReport(
		final com.soffid.iam.iga.api.Rule rule, 
		final java.util.Collection<com.soffid.iam.iga.api.RuleAssignedRole> grants)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findRuleAssignments

	 * @param rule 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.RuleAssignedRole> findRuleAssignments(
		final com.soffid.iam.iga.api.Rule rule)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findRules

	 * @param description 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.Rule> findRules(
		final java.lang.String description)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findRulesByRole

	 * @param roleId 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.Rule> findRulesByRole(
		final java.lang.Long roleId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation apply

	 * @param rule 
	 */
	void apply(
		final com.soffid.iam.iga.api.Rule rule)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation delete

	 * @param rule 
	 */
	void delete(
		final com.soffid.iam.iga.api.Rule rule)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation delete

	 * @param ruleAssignment 
	 */
	void delete(
		final com.soffid.iam.iga.api.RuleAssignedRole ruleAssignment)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

}
