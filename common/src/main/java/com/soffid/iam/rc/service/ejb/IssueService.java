//
// (C) 2013 Soffid
//
//

package com.soffid.iam.rc.service.ejb;
/**
 * EJB IssueService
 */
public interface IssueService

 {

	com.soffid.iam.rc.api.Issue create(
		final com.soffid.iam.rc.api.Issue event)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.rc.api.Issue notify(
		final com.soffid.iam.rc.api.Issue issue, 
		final java.lang.String address, 
		final java.lang.String subject, 
		final java.lang.String body)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.rc.api.Issue registerAction(
		final com.soffid.iam.rc.api.Issue issue, 
		final java.lang.String action)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.rc.api.Issue update(
		final com.soffid.iam.rc.api.Issue event)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.zkdb.api.PagedResult<com.soffid.iam.rc.api.Issue> findIssues(
		final com.soffid.zkdb.api.Query query)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.zkdb.api.PagedResult<com.soffid.iam.rc.api.Issue> findMyIssues(
		final com.soffid.zkdb.api.Query query)
	throws com.soffid.iam.exception.InternalErrorException;

	int countMyIssues()
	throws com.soffid.iam.exception.InternalErrorException;

	java.util.List<com.soffid.iam.rc.api.Issue> findIssuesByUser(
		final java.lang.String user)
	throws com.soffid.iam.exception.InternalErrorException;

	java.util.List<com.soffid.iam.rc.api.IssueActionDefinition> listManualActions()
	throws com.soffid.iam.exception.InternalErrorException;

	void delete(
		final com.soffid.iam.rc.api.Issue event)
	throws com.soffid.iam.exception.InternalErrorException;

}
