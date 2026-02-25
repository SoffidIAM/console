//
// (C) 2013 Soffid
//
//

package com.soffid.iam.rc.service;
/**
 * Service IssuePolicyService
 */
public interface IssuePolicyService {
	public final static String SERVICE_NAME = "com.soffid.iam.rc.service.IssuePolicyService";

	/**
	 * Operation update

	 * @param event 
	 * @return 
	 */
	com.soffid.iam.rc.api.IssuePolicy update(
		final com.soffid.iam.rc.api.IssuePolicy event)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findIssuePolicies

	 * @param q 
	 * @return 
	 */
	com.soffid.zkdb.api.PagedResult<com.soffid.iam.rc.api.IssuePolicy> findIssuePolicies(
		final com.soffid.zkdb.api.Query q)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation listAutomaticActions

	 * @return 
	 */
	java.util.List<com.soffid.iam.rc.api.IssueActionDefinition> listAutomaticActions()
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation createPolicies

	 */
	void createPolicies()
			throws com.soffid.iam.exception.InternalErrorException;

}
