//
// (C) 2013 Soffid
//
//

package com.soffid.iam.pam.service.ejb;
/**
 * EJB PamPolicyService
 */
public interface PamPolicyService

 {

	com.soffid.iam.pam.api.PamAction updateAction(
		final com.soffid.iam.pam.api.PamAction action)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.pam.api.PamPolicy createPolicy(
		final com.soffid.iam.pam.api.PamPolicy policy)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.pam.api.PamPolicy updatePolicy(
		final com.soffid.iam.pam.api.PamPolicy policy)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.pam.api.PamRule createRule(
		final com.soffid.iam.pam.api.PamRule rule)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.pam.api.PamRule updateRule(
		final com.soffid.iam.pam.api.PamRule rule)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.zkdb.api.PagedResult<com.soffid.iam.pam.api.PamPolicy> findPolicies(
		final com.soffid.zkdb.api.Query q)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.zkdb.api.PagedResult<com.soffid.iam.pam.api.PamRule> findRules(
		final com.soffid.zkdb.api.Query q)
	throws com.soffid.iam.exception.InternalErrorException;

	java.util.List<com.soffid.iam.pam.api.PamAction> findPolicyActions(
		final com.soffid.iam.pam.api.PamPolicy policy)
	throws com.soffid.iam.exception.InternalErrorException;

	void deletePolicy(
		final com.soffid.iam.pam.api.PamPolicy policy)
	throws com.soffid.iam.exception.InternalErrorException;

	void deleteRule(
		final com.soffid.iam.pam.api.PamRule rule)
	throws com.soffid.iam.exception.InternalErrorException;

}
