//
// (C) 2013 Soffid
//
//

package com.soffid.iam.pam.service;
/**
 * Service PamPolicyService
 */
public interface PamPolicyService {
	public final static String SERVICE_NAME = "com.soffid.iam.pam.service.PamPolicyService";

	/**
	 * Operation updateAction

	 * @param action 
	 * @return 
	 */
	com.soffid.iam.pam.api.PamAction updateAction(
		final com.soffid.iam.pam.api.PamAction action)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation createPolicy

	 * @param policy 
	 * @return 
	 */
	com.soffid.iam.pam.api.PamPolicy createPolicy(
		final com.soffid.iam.pam.api.PamPolicy policy)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation updatePolicy

	 * @param policy 
	 * @return 
	 */
	com.soffid.iam.pam.api.PamPolicy updatePolicy(
		final com.soffid.iam.pam.api.PamPolicy policy)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation createRule

	 * @param rule 
	 * @return 
	 */
	com.soffid.iam.pam.api.PamRule createRule(
		final com.soffid.iam.pam.api.PamRule rule)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation updateRule

	 * @param rule 
	 * @return 
	 */
	com.soffid.iam.pam.api.PamRule updateRule(
		final com.soffid.iam.pam.api.PamRule rule)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findPolicies

	 * @param q 
	 * @return 
	 */
	com.soffid.zkdb.api.PagedResult<com.soffid.iam.pam.api.PamPolicy> findPolicies(
		final com.soffid.zkdb.api.Query q)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findRules

	 * @param q 
	 * @return 
	 */
	com.soffid.zkdb.api.PagedResult<com.soffid.iam.pam.api.PamRule> findRules(
		final com.soffid.zkdb.api.Query q)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findPolicyActions

	 * @param policy 
	 * @return 
	 */
	java.util.List<com.soffid.iam.pam.api.PamAction> findPolicyActions(
		final com.soffid.iam.pam.api.PamPolicy policy)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation applyRule
	 * Method invoked from PAM session

	 * @param sessionKey 
	 * @param policyName 
	 * @param ruleName 
	 */
	void applyRule(
		final java.lang.String sessionKey, 
		final java.lang.String policyName, 
		final java.lang.String ruleName)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation deletePolicy

	 * @param policy 
	 */
	void deletePolicy(
		final com.soffid.iam.pam.api.PamPolicy policy)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation deleteRule

	 * @param rule 
	 */
	void deleteRule(
		final com.soffid.iam.pam.api.PamRule rule)
			throws com.soffid.iam.exception.InternalErrorException;

}
