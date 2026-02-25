//
// (C) 2013 Soffid
//
//

package com.soffid.iam.base.service.ejb;
/**
 * EJB UserService
 */
public interface UserService

 {

	byte[] getESSORules(
		final java.lang.String user)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.base.api.AsyncProcessTracker disableUsers(
		final java.lang.String scimQuery, 
		final java.util.List<com.soffid.iam.base.api.DisableObjectRule> rules)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.base.api.AsyncProcessTracker disableUsersPreview(
		final java.lang.String scimQuery, 
		final java.util.List<com.soffid.iam.base.api.DisableObjectRule> rules, 
		final java.util.List<java.lang.Object[]> actions)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.base.api.User create(
		final com.soffid.iam.base.api.User usuari)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.base.api.User findUserByUserName(
		final java.lang.String userName)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.base.api.User findUserByUserId(
		final java.lang.Long id)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.base.api.User getCurrentUser()
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.base.api.User update(
		final com.soffid.iam.base.api.User usuari)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

@Deprecated
	com.soffid.iam.base.api.UserData findDataByUserAndCode(
		final java.lang.String userName, 
		final java.lang.String codiTipusDada)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.zkdb.api.PagedResult<com.soffid.iam.base.api.User> findUsers(
		final com.soffid.zkdb.api.Query q)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	int isUpdatePendingExtended(
		final java.lang.String userName)
	throws com.soffid.iam.exception.InternalErrorException;

	java.lang.String setTemporaryPassword(
		final java.lang.String userName, 
		final java.lang.String passwordDomain)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.lang.String createNewUserProcess(
		final java.lang.String processName, 
		final java.lang.String userName, 
		final boolean canviaAProces)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

@Deprecated
	java.lang.String getFollowingName()
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.lang.String[] getTasks(
		final java.lang.String userName)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.lang.String[] refreshChanges(
		final java.lang.String userName)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

@Deprecated
	java.util.Collection<com.soffid.iam.base.api.UserData> findUserDataByUserName(
		final java.lang.String userName)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.bpm.api.ProcessInstance> findBpmUserProcessInstanceByUserName(
		final java.lang.String userName)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.am.api.Session> findSessionByUserName(
		final java.lang.String userName)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.PrinterUser> findUserPrintersByUserName(
		final java.lang.String userName)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.am.api.NetworkAuthorization> findNetworksACByUserName(
		final java.lang.String userName)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.sync.api.SyncAgentTaskLog> getActiveTasks(
		final java.lang.String userName)
	throws com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.BpmProcess> getBpmUserProcessList()
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.List<com.soffid.iam.am.api.PasswordDomainStatus> findPasswordDomainStatus(
		final java.lang.String user)
	throws com.soffid.iam.exception.InternalErrorException;

@Deprecated
	java.util.Map<java.lang.String,java.lang.Object> findUserAttributes(
		final java.lang.String userName)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void delete(
		final com.soffid.iam.base.api.User user)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void merge(
		final java.lang.Long srcId, 
		final java.lang.Long targetId, 
		final java.lang.Long eventId)
	throws com.soffid.iam.exception.InternalErrorException;

	void sendPassword(
		final java.lang.String userName, 
		final java.lang.String passwordDomain)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void setPassword(
		final java.lang.String userName, 
		final java.lang.String passwordDomain, 
		final com.soffid.iam.am.api.Password newPassword)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.BadPasswordException;

	void setTemporaryPassword(
		final java.lang.String userName, 
		final java.lang.String passwordDomain, 
		final com.soffid.iam.am.api.Password newPassword)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.BadPasswordException;

	void unlockPasswordDomain(
		final java.lang.String user, 
		final java.lang.String passwordDomain)
	throws com.soffid.iam.exception.InternalErrorException;

@Deprecated
	void updateUserAttributes(
		final java.lang.String userName, 
		final java.util.Map<java.lang.String,java.lang.Object> attributes)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

}
