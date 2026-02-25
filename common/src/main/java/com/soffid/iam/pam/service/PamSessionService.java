//
// (C) 2013 Soffid
//
//

package com.soffid.iam.pam.service;
/**
 * Service PamSessionService
 */
public interface PamSessionService {
	public final static String SERVICE_NAME = "com.soffid.iam.pam.service.PamSessionService";

	/**
	 * Operation checkJumpServerSession
	 * Checks a server session is up

	 * @param sessio 
	 * @return 
	 */
	boolean checkJumpServerSession(
		final com.soffid.iam.am.api.Session sessio)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation create

	 * @param jumpServerGroup 
	 * @return 
	 */
	com.soffid.iam.pam.api.JumpServerGroup create(
		final com.soffid.iam.pam.api.JumpServerGroup jumpServerGroup)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation update

	 * @param jumpServerGroup 
	 * @return 
	 */
	com.soffid.iam.pam.api.JumpServerGroup update(
		final com.soffid.iam.pam.api.JumpServerGroup jumpServerGroup)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation createCustomJumpServerSession
	 * Creates a jump server session and returns the session URL. Internal method for SSH and RDP proxies

	 * @param account 
	 * @param sourceIp 
	 * @param type 
	 * @param info 
	 * @return 
	 */
	com.soffid.iam.pam.api.NewPamSession createCustomJumpServerSession(
		final com.soffid.iam.base.api.Account account, 
		final java.lang.String sourceIp, 
		final com.soffid.iam.am.api.SessionType type, 
		final java.lang.String info)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation createJumpServerSession
	 * Creates a jump server session and returns the session URL

	 * @param account 
	 * @return 
	 */
	com.soffid.iam.pam.api.NewPamSession createJumpServerSession(
		final com.soffid.iam.base.api.Account account)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation createJumpServerSession
	 * Creates a jump server session and returns the session URL

	 * @param account 
	 * @param entryPointPath 
	 * @param entryPointDescriptor 
	 * @return 
	 */
	com.soffid.iam.pam.api.NewPamSession createJumpServerSession(
		final com.soffid.iam.base.api.Account account, 
		final java.lang.String entryPointPath, 
		final java.lang.String entryPointDescriptor)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation createJumpServerSession
	 * Creates a jump server session and returns the session URL

	 * @param account 
	 * @param entryPointPath 
	 * @param entryPointDescriptor 
	 * @param pamPolicy 
	 * @return 
	 */
	com.soffid.iam.pam.api.NewPamSession createJumpServerSession(
		final com.soffid.iam.base.api.Account account, 
		final java.lang.String entryPointPath, 
		final java.lang.String entryPointDescriptor, 
		final java.lang.String pamPolicy)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation createManualJumpServerSession
	 * Creates a manual jump server session and returns the session URL

	 * @param accountName 
	 * @param accountPassword 
	 * @param entryPointPath 
	 * @param entryPointDescriptor 
	 * @param pamPolicy 
	 * @return 
	 */
	com.soffid.iam.pam.api.NewPamSession createManualJumpServerSession(
		final java.lang.String accountName, 
		final com.soffid.iam.am.api.Password accountPassword, 
		final java.lang.String entryPointPath, 
		final java.lang.String entryPointDescriptor, 
		final java.lang.String pamPolicy)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findSession
	 * Retrieves a pam session descriptor

	 * @param serverGroup 
	 * @param sessionId 
	 * @return 
	 */
	com.soffid.iam.pam.api.PamSession findSession(
		final java.lang.String serverGroup, 
		final java.lang.String sessionId)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getActiveSessions

	 * @param server 
	 * @return 
	 */
	java.lang.Integer getActiveSessions(
		final java.lang.String server)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getConsoleFreeSpace

	 * @param jumpServerGroup 
	 * @return 
	 */
	java.lang.Long getConsoleFreeSpace(
		final java.lang.String jumpServerGroup)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getConsoleUsedSpace

	 * @param jumpServerGroup 
	 * @return 
	 */
	java.lang.Long getConsoleUsedSpace(
		final java.lang.String jumpServerGroup)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findJumpServerGroups

	 * @return 
	 */
	java.util.List<com.soffid.iam.pam.api.JumpServerGroup> findJumpServerGroups()
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation search
	 * Searches in pam sessions log

	 * @param jumpServerGroup 
	 * @param url 
	 * @param text 
	 * @param screenshots 
	 * @param user 
	 * @param since 
	 * @param until 
	 * @return 
	 */
	java.util.List<com.soffid.iam.pam.api.PamSession> search(
		final java.lang.String jumpServerGroup, 
		final java.lang.String url, 
		final java.lang.String text, 
		final java.lang.String screenshots, 
		final java.lang.String user, 
		final java.util.Date since, 
		final java.util.Date until)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation search
	 * Searches in pam sessions log

	 * @param jumpServerGroup 
	 * @param url 
	 * @param text 
	 * @param user 
	 * @param since 
	 * @param until 
	 * @return 
	 */
	java.util.List<com.soffid.iam.pam.api.PamSession> search(
		final java.lang.String jumpServerGroup, 
		final java.lang.String url, 
		final java.lang.String text, 
		final java.lang.String user, 
		final java.util.Date since, 
		final java.util.Date until)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getVideoSize
	 * Retrieves a pam session video

	 * @param session 
	 * @param chapter 
	 * @return 
	 */
	long getVideoSize(
		final com.soffid.iam.pam.api.PamSession session, 
		final long chapter)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation generateKeystrokes
	 * Retrieves a pam session keystrokes

	 * @param session 
	 * @param stream 
	 */
	void generateKeystrokes(
		final com.soffid.iam.pam.api.PamSession session, 
		final java.io.OutputStream stream)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation generateVideo
	 * Retrieves a pam session video

	 * @param session 
	 * @param chapter 
	 * @param stream 
	 * @param start 
	 * @param end 
	 */
	void generateVideo(
		final com.soffid.iam.pam.api.PamSession session, 
		final long chapter, 
		final java.io.OutputStream stream, 
		final long start, 
		final long end)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation remove

	 * @param jumpServerGroup 
	 */
	void remove(
		final com.soffid.iam.pam.api.JumpServerGroup jumpServerGroup)
			throws com.soffid.iam.exception.InternalErrorException;

}
