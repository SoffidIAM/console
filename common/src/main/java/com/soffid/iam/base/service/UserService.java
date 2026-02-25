//
// (C) 2013 Soffid
//
//

package com.soffid.iam.base.service;
/**
 * Service UserService
 */
public interface UserService {
	public final static String REMOTE_PATH = "/seycon/com.soffid.iam.base.service.UserService";

	public final static String SERVICE_NAME = "com.soffid.iam.base.service.UserService";

	/**
	 * Operation getESSORules

	 * @param user 
	 * @return 
	 */
	byte[] getESSORules(
		final java.lang.String user)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation createExtranetCard

	 * @param userName 
	 * @return 
	 */
@Deprecated
	com.soffid.iam.am.api.ExtranetCard createExtranetCard(
		final java.lang.String userName)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findExtranetCardByUserNameAndCardName

	 * @param userName 
	 * @param codiTargeta 
	 * @return 
	 */
@Deprecated
	com.soffid.iam.am.api.ExtranetCard findExtranetCardByUserNameAndCardName(
		final java.lang.String userName, 
		final java.lang.String codiTargeta)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation update

	 * @param targetaExtranet 
	 * @return 
	 */
@Deprecated
	com.soffid.iam.am.api.ExtranetCard update(
		final com.soffid.iam.am.api.ExtranetCard targetaExtranet)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findMailServerByUserName

	 * @param userName 
	 * @return 
	 */
@Deprecated
	com.soffid.iam.am.api.Host findMailServerByUserName(
		final java.lang.String userName)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findHomeServerByUserName

	 * @param userName 
	 * @return 
	 */
@Deprecated
	com.soffid.iam.am.api.Host findHomeServerByUserName(
		final java.lang.String userName)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findProfileServerByUserName

	 * @param userName 
	 * @return 
	 */
@Deprecated
	com.soffid.iam.am.api.Host findProfileServerByUserName(
		final java.lang.String userName)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation disableUsers
	 * Disable or notify users that has not been active for a while

	 * @param scimQuery 
	 * @param rules Actions to perform

	 * @return 
	 */
	com.soffid.iam.base.api.AsyncProcessTracker disableUsers(
		final java.lang.String scimQuery, 
		final java.util.List<com.soffid.iam.base.api.DisableObjectRule> rules)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation disableUsersPreview

	 * @param scimQuery 
	 * @param rules 
	 * @param actions 
	 * @return 
	 */
	com.soffid.iam.base.api.AsyncProcessTracker disableUsersPreview(
		final java.lang.String scimQuery, 
		final java.util.List<com.soffid.iam.base.api.DisableObjectRule> rules, 
		final java.util.List<java.lang.Object[]> actions)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation disableUser
	 * Disable a user

	 * @param userName 
	 * @return 
	 */
	com.soffid.iam.base.api.User disableUser(
		final java.lang.String userName)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation create

	 * @param usuari 
	 * @return 
	 */
	com.soffid.iam.base.api.User create(
		final com.soffid.iam.base.api.User usuari)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findUserByDataTypeNameAndDataTypeValue

	 * @param codiTipusDada 
	 * @param valorTipusDada 
	 * @return 
	 */
@Deprecated
	com.soffid.iam.base.api.User findUserByDataTypeNameAndDataTypeValue(
		final java.lang.String codiTipusDada, 
		final java.lang.String valorTipusDada)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findUserByUserName
	 * Retrieves a user by user name

	 * @param userName 
	 * @return 
	 */
	com.soffid.iam.base.api.User findUserByUserName(
		final java.lang.String userName)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findUserByUserId
	 * Retrieves a user by its user id

	 * @param id 
	 * @return 
	 */
	com.soffid.iam.base.api.User findUserByUserId(
		final java.lang.Long id)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findUserByUserNif

	 * @param nif 
	 * @return 
	 */
@Deprecated
	com.soffid.iam.base.api.User findUserByUserNif(
		final java.lang.String nif)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getCurrentUser

	 * @return 
	 */
	com.soffid.iam.base.api.User getCurrentUser()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getUserInfo

	 * @param user 
	 * @return 
	 */
	com.soffid.iam.base.api.User getUserInfo(
		final java.lang.String user)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.UnknownUserException;

	/**
	 * Operation setServersToUser

	 * @param userName 
	 * @param nomServidorPerfil 
	 * @param nomServidorCorreu 
	 * @param nomServidorHome 
	 * @return 
	 */
@Deprecated
	com.soffid.iam.base.api.User setServersToUser(
		final java.lang.String userName, 
		final java.lang.String nomServidorPerfil, 
		final java.lang.String nomServidorCorreu, 
		final java.lang.String nomServidorHome)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation update
	 * Update a user

	 * @param usuari 
	 * @return 
	 */
	com.soffid.iam.base.api.User update(
		final com.soffid.iam.base.api.User usuari)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findDataByUserAndCode

	 * @param userName 
	 * @param codiTipusDada 
	 * @return 
	 */
@Deprecated
	com.soffid.iam.base.api.UserData findDataByUserAndCode(
		final java.lang.String userName, 
		final java.lang.String codiTipusDada)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation create
	 * Notifies the workflow is acting on a user

	 * @param userProcess 
	 * @return 
	 */
	com.soffid.iam.iga.api.BpmUserProcess create(
		final com.soffid.iam.iga.api.BpmUserProcess userProcess)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation update

	 * @param usuariWFProces 
	 * @return 
	 */
	com.soffid.iam.iga.api.BpmUserProcess update(
		final com.soffid.iam.iga.api.BpmUserProcess usuariWFProces)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findUsers

	 * @param q 
	 * @return 
	 */
	com.soffid.zkdb.api.PagedResult<com.soffid.iam.base.api.User> findUsers(
		final com.soffid.zkdb.api.Query q)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation isUpdatePendingExtended
	 * Identifies if there is any pending change. 0 means no change pending, 1 task is on hald, 2 means synchronization in progress, 3 means error

	 * @param userName 
	 * @return 
	 */
	int isUpdatePendingExtended(
		final java.lang.String userName)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation setTemporaryPassword
	 * Generates a random temporary password for a user

	 * @param userName 
	 * @param passwordDomain The password domain uses to be 'DEFAULT'

	 * @return 
	 */
	java.lang.String setTemporaryPassword(
		final java.lang.String userName, 
		final java.lang.String passwordDomain)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation createNewUserProcess
	 * Starts a new workflow far a user

	 * @param processName 
	 * @param userName 
	 * @param canviaAProces Must be true to retrieve the current task URL

	 * @return 
	 */
	java.lang.String createNewUserProcess(
		final java.lang.String processName, 
		final java.lang.String userName, 
		final boolean canviaAProces)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation generateRandomPassword

	 * @return 
	 */
@Deprecated
	java.lang.String generateRandomPassword()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getFollowingName

	 * @return 
	 */
@Deprecated
	java.lang.String getFollowingName()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation setPassword
	 * Generates a random password for a user

	 * @param userName 
	 * @param passwordDomain The password domain is usually "DEFAULT"

	 * @return 
	 */
	java.lang.String setPassword(
		final java.lang.String userName, 
		final java.lang.String passwordDomain)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getTasks
	 * Gets any pending task for the user

	 * @param userName 
	 * @return 
	 */
	java.lang.String[] getTasks(
		final java.lang.String userName)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation refreshChanges
	 * Synchronize the user with any target system

	 * @param userName 
	 * @return 
	 */
	java.lang.String[] refreshChanges(
		final java.lang.String userName)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findUserDataByUserName

	 * @param userName 
	 * @return 
	 */
@Deprecated
	java.util.Collection<com.soffid.iam.base.api.UserData> findUserDataByUserName(
		final java.lang.String userName)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findPrintersByUserName
	 * Fetches all printers assigned to a user

	 * @param userName 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.Printer> findPrintersByUserName(
		final java.lang.String userName)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findUserRolesHierachyByUserName

	 * @param userName 
	 * @return 
	 */
@Deprecated
	java.util.Collection<com.soffid.iam.iga.api.Role> findUserRolesHierachyByUserName(
		final java.lang.String userName)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findUserRolesHierachyByUserName

	 * @param userName 
	 * @param incloureRolsUsuariDirectes 
	 * @return 
	 */
@Deprecated
	java.util.Collection<com.soffid.iam.iga.api.Role> findUserRolesHierachyByUserName(
		final java.lang.String userName, 
		final java.lang.Boolean incloureRolsUsuariDirectes)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findBpmUserProcessInstanceByUserName

	 * @param userName 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.bpm.api.ProcessInstance> findBpmUserProcessInstanceByUserName(
		final java.lang.String userName)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findBpmUserProcessByUserName

	 * @param userName 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.BpmUserProcess> findBpmUserProcessByUserName(
		final java.lang.String userName)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findBpmUserProcessByProcessId

	 * @param idProces 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.BpmUserProcess> findBpmUserProcessByProcessId(
		final java.lang.Long idProces)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findBpmUserProcessByUserNif

	 * @param nifUsuari 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.BpmUserProcess> findBpmUserProcessByUserNif(
		final java.lang.String nifUsuari)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findSessionByUserName
	 * Retrieves all the sessions a user currently has

	 * @param userName 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.am.api.Session> findSessionByUserName(
		final java.lang.String userName)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findExtranetCardsByUserName

	 * @param userName 
	 * @param activa 
	 * @return 
	 */
@Deprecated
	java.util.Collection<com.soffid.iam.am.api.ExtranetCard> findExtranetCardsByUserName(
		final java.lang.String userName, 
		final java.lang.String activa)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findUserNames

	 * @return 
	 */
	java.util.Collection<java.lang.String> findUserNames()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findUserPrintersByUserName
	 * Retrieves the printers assigned to a user

	 * @param userName 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.PrinterUser> findUserPrintersByUserName(
		final java.lang.String userName)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findNetworksACByUserName
	 * Retrieves network authorizations granted to a user

	 * @param userName 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.am.api.NetworkAuthorization> findNetworksACByUserName(
		final java.lang.String userName)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getActiveTasks
	 * Retrieves current synchronization tasks for user

	 * @param userName 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.sync.api.SyncAgentTaskLog> getActiveTasks(
		final java.lang.String userName)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getApplicationsByUserName

	 * @param userName 
	 * @return 
	 */
@Deprecated
	java.util.Collection<com.soffid.iam.iga.api.InformationSystem> getApplicationsByUserName(
		final java.lang.String userName)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getBpmEnabledApplicationsByUserName

	 * @param userName 
	 * @return 
	 */
@Deprecated
	java.util.Collection<com.soffid.iam.iga.api.InformationSystem> getBpmEnabledApplicationsByUserName(
		final java.lang.String userName)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getPasswordsUserType

	 * @param dataInici 
	 * @param dataFi 
	 * @param tipusUsuari 
	 * @return 
	 */
@Deprecated
	java.util.Collection<com.soffid.iam.am.api.PasswordStatus> getPasswordsUserType(
		final java.util.Date dataInici, 
		final java.util.Date dataFi, 
		final java.lang.String tipusUsuari)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getApplicationRolesByuserNameAndApplicationName

	 * @param userName 
	 * @param codiAplicacio 
	 * @return 
	 */
@Deprecated
	java.util.Collection<com.soffid.iam.iga.api.Role> getApplicationRolesByuserNameAndApplicationName(
		final java.lang.String userName, 
		final java.lang.String codiAplicacio)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getDataType

	 * @return 
	 */
@Deprecated
	java.util.Collection<com.soffid.iam.base.api.DataType> getDataType()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getUserExplicitRoles

	 * @param userId 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.RoleGrant> getUserExplicitRoles(
		final long userId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.UnknownUserException;

	/**
	 * Operation getUserGroups

	 * @param userId 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.Group> getUserGroups(
		final long userId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getUserGroupsHierarchy

	 * @param userId 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.Group> getUserGroupsHierarchy(
		final long userId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.UnknownUserException;

	/**
	 * Operation getUserGroupsHierarchy

	 * @param userId 
	 * @param holderGroup 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.Group> getUserGroupsHierarchy(
		final long userId, 
		final java.lang.String holderGroup)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.UnknownUserException;

	/**
	 * Operation getUserRoles

	 * @param userId 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.RoleGrant> getUserRoles(
		final long userId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.UnknownUserException;

	/**
	 * Operation getBpmUserProcessList
	 * Gets the list of workflows that can be started for a user

	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.BpmProcess> getBpmUserProcessList()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findPasswordDomainStatus

	 * @param user 
	 * @return 
	 */
	java.util.List<com.soffid.iam.am.api.PasswordDomainStatus> findPasswordDomainStatus(
		final java.lang.String user)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findUserAttributes

	 * @param userName 
	 * @return 
	 */
@Deprecated
	java.util.Map<java.lang.String,java.lang.Object> findUserAttributes(
		final java.lang.String userName)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation delete
	 * Removes a user

	 * @param user 
	 */
	void delete(
		final com.soffid.iam.base.api.User user)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation delete

	 * @param usuariWFProces 
	 */
	void delete(
		final com.soffid.iam.iga.api.BpmUserProcess usuariWFProces)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation merge

	 * @param srcId 
	 * @param targetId 
	 * @param eventId 
	 */
	void merge(
		final java.lang.Long srcId, 
		final java.lang.Long targetId, 
		final java.lang.Long eventId)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation sendPassword
	 * Sends the current account password back to the target system

	 * @param userName 
	 * @param passwordDomain 
	 */
	void sendPassword(
		final java.lang.String userName, 
		final java.lang.String passwordDomain)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation setPassword
	 * Sets a password for a user

	 * @param userName 
	 * @param passwordDomain The password domain uses to be 'DEFAULT'

	 * @param newPassword 
	 */
	void setPassword(
		final java.lang.String userName, 
		final java.lang.String passwordDomain, 
		final com.soffid.iam.am.api.Password newPassword)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.BadPasswordException;

	/**
	 * Operation setTemporaryPassword
	 * Sets a temporary password for a user

	 * @param userName 
	 * @param passwordDomain The password domain uses to be 'DEFAULT'

	 * @param newPassword 
	 */
	void setTemporaryPassword(
		final java.lang.String userName, 
		final java.lang.String passwordDomain, 
		final com.soffid.iam.am.api.Password newPassword)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.BadPasswordException;

	/**
	 * Operation unlockPasswordDomain

	 * @param user 
	 * @param passwordDomain 
	 */
	void unlockPasswordDomain(
		final java.lang.String user, 
		final java.lang.String passwordDomain)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation updateUserAttributes

	 * @param userName 
	 * @param attributes 
	 */
@Deprecated
	void updateUserAttributes(
		final java.lang.String userName, 
		final java.util.Map<java.lang.String,java.lang.Object> attributes)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

}
