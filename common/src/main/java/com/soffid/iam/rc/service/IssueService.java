//
// (C) 2013 Soffid
//
//

package com.soffid.iam.rc.service;
/**
 * Service IssueService
 */
public interface IssueService {
	public final static String REMOTE_PATH = "/seycon/com.soffid.iam.rc.service.IssueService";

	public final static String SERVICE_NAME = "com.soffid.iam.rc.service.IssueService";

	/**
	 * Operation create

	 * @param event 
	 * @return 
	 */
	com.soffid.iam.rc.api.Issue create(
		final com.soffid.iam.rc.api.Issue event)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation createInternalIssue

	 * @param event 
	 * @return 
	 */
	com.soffid.iam.rc.api.Issue createInternalIssue(
		final com.soffid.iam.rc.api.Issue event)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation notify

	 * @param issue 
	 * @param address 
	 * @param subject 
	 * @param body 
	 * @return 
	 */
	com.soffid.iam.rc.api.Issue notify(
		final com.soffid.iam.rc.api.Issue issue, 
		final java.lang.String address, 
		final java.lang.String subject, 
		final java.lang.String body)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation registerAction

	 * @param issue 
	 * @param action 
	 * @return 
	 */
	com.soffid.iam.rc.api.Issue registerAction(
		final com.soffid.iam.rc.api.Issue issue, 
		final java.lang.String action)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation update

	 * @param event 
	 * @return 
	 */
	com.soffid.iam.rc.api.Issue update(
		final com.soffid.iam.rc.api.Issue event)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findIssues

	 * @param query 
	 * @return 
	 */
	com.soffid.zkdb.api.PagedResult<com.soffid.iam.rc.api.Issue> findIssues(
		final com.soffid.zkdb.api.Query query)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findMyIssues

	 * @param query 
	 * @return 
	 */
	com.soffid.zkdb.api.PagedResult<com.soffid.iam.rc.api.Issue> findMyIssues(
		final com.soffid.zkdb.api.Query query)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation countMyIssues

	 * @return 
	 */
	int countMyIssues()
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findIssuesByUser

	 * @param user 
	 * @return 
	 */
	java.util.List<com.soffid.iam.rc.api.Issue> findIssuesByUser(
		final java.lang.String user)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation listManualActions

	 * @return 
	 */
	java.util.List<com.soffid.iam.rc.api.IssueActionDefinition> listManualActions()
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation delete

	 * @param event 
	 */
	void delete(
		final com.soffid.iam.rc.api.Issue event)
			throws com.soffid.iam.exception.InternalErrorException;

}
