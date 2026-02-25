//
// (C) 2013 Soffid
//
//

package com.soffid.iam.rc.service.ejb;
/**
 * EJB IssuePolicyService
 */
public interface IssuePolicyService

 {

	com.soffid.iam.rc.api.IssuePolicy update(
		final com.soffid.iam.rc.api.IssuePolicy event)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.zkdb.api.PagedResult<com.soffid.iam.rc.api.IssuePolicy> findIssuePolicies(
		final com.soffid.zkdb.api.Query q)
	throws com.soffid.iam.exception.InternalErrorException;

	java.util.List<com.soffid.iam.rc.api.IssueActionDefinition> listAutomaticActions()
	throws com.soffid.iam.exception.InternalErrorException;

}
