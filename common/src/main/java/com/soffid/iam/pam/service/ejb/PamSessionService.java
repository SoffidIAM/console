//
// (C) 2013 Soffid
//
//

package com.soffid.iam.pam.service.ejb;
/**
 * EJB PamSessionService
 */
public interface PamSessionService

 {

	com.soffid.iam.pam.api.JumpServerGroup create(
		final com.soffid.iam.pam.api.JumpServerGroup jumpServerGroup)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.pam.api.JumpServerGroup update(
		final com.soffid.iam.pam.api.JumpServerGroup jumpServerGroup)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.pam.api.NewPamSession createJumpServerSession(
		final com.soffid.iam.base.api.Account account)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.pam.api.NewPamSession createJumpServerSession(
		final com.soffid.iam.base.api.Account account, 
		final java.lang.String entryPointPath, 
		final java.lang.String entryPointDescriptor)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.pam.api.NewPamSession createJumpServerSession(
		final com.soffid.iam.base.api.Account account, 
		final java.lang.String entryPointPath, 
		final java.lang.String entryPointDescriptor, 
		final java.lang.String pamPolicy)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.pam.api.NewPamSession createManualJumpServerSession(
		final java.lang.String accountName, 
		final com.soffid.iam.am.api.Password accountPassword, 
		final java.lang.String entryPointPath, 
		final java.lang.String entryPointDescriptor, 
		final java.lang.String pamPolicy)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.pam.api.PamSession findSession(
		final java.lang.String serverGroup, 
		final java.lang.String sessionId)
	throws com.soffid.iam.exception.InternalErrorException;

	java.util.List<com.soffid.iam.pam.api.JumpServerGroup> findJumpServerGroups()
	throws com.soffid.iam.exception.InternalErrorException;

	java.util.List<com.soffid.iam.pam.api.PamSession> search(
		final java.lang.String jumpServerGroup, 
		final java.lang.String url, 
		final java.lang.String text, 
		final java.lang.String screenshots, 
		final java.lang.String user, 
		final java.util.Date since, 
		final java.util.Date until)
	throws com.soffid.iam.exception.InternalErrorException;

	java.util.List<com.soffid.iam.pam.api.PamSession> search(
		final java.lang.String jumpServerGroup, 
		final java.lang.String url, 
		final java.lang.String text, 
		final java.lang.String user, 
		final java.util.Date since, 
		final java.util.Date until)
	throws com.soffid.iam.exception.InternalErrorException;

	long getVideoSize(
		final com.soffid.iam.pam.api.PamSession session, 
		final long chapter)
	throws com.soffid.iam.exception.InternalErrorException;

	void generateKeystrokes(
		final com.soffid.iam.pam.api.PamSession session, 
		final java.io.OutputStream stream)
	throws com.soffid.iam.exception.InternalErrorException;

	void generateVideo(
		final com.soffid.iam.pam.api.PamSession session, 
		final long chapter, 
		final java.io.OutputStream stream, 
		final long start, 
		final long end)
	throws com.soffid.iam.exception.InternalErrorException;

	void remove(
		final com.soffid.iam.pam.api.JumpServerGroup jumpServerGroup)
	throws com.soffid.iam.exception.InternalErrorException;

}
