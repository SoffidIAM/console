//
// (C) 2013 Soffid
//
//

package com.soffid.iam.sync.service;
/**
 * Service ServerService
 */
public interface ServerService {
	public final static String REMOTE_PATH = "/seycon/com.soffid.iam.sync.service.ServerService";

	public final static String SERVICE_NAME = "com.soffid.iam.sync.service.ServerService";

	/**
	 * Operation findRemoteServerByUrl

	 * @param url 
	 * @return 
	 */
	com.soffid.iam.sync.api.Server findRemoteServerByUrl(
		final java.lang.String url)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation invoke

	 * @param agent 
	 * @param verb 
	 * @param command 
	 * @param params 
	 * @return 
	 */
	java.util.Collection<java.util.Map<java.lang.String,java.lang.Object>> invoke(
		final java.lang.String agent, 
		final java.lang.String verb, 
		final java.lang.String command, 
		final java.util.Map<java.lang.String,java.lang.Object> params)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation hasSupportAccessHost

	 * @param hostId 
	 * @param userId 
	 * @return 
	 */
	boolean hasSupportAccessHost(
		final long hostId, 
		final long userId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.UnknownUserException, com.soffid.iam.exception.UnknownHostException;

	/**
	 * Operation updateExpiredPasswords

	 * @param usuari 
	 * @param externalAuth 
	 * @return 
	 */
	boolean updateExpiredPasswords(
		final com.soffid.iam.base.api.User usuari, 
		final boolean externalAuth)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getAddonJar

	 * @param addon 
	 * @return 
	 */
	byte[] getAddonJar(
		final java.lang.String addon)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getPluginJar

	 * @param classname 
	 * @return 
	 */
	byte[] getPluginJar(
		final java.lang.String classname)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getUserMazingerRules

	 * @param userId 
	 * @param version 
	 * @return 
	 */
	byte[] getUserMazingerRules(
		final long userId, 
		final java.lang.String version)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.UnknownUserException;

	/**
	 * Operation getHostInfo

	 * @param hostName 
	 * @return 
	 */
	com.soffid.iam.am.api.Host getHostInfo(
		final java.lang.String hostName)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.UnknownHostException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getHostInfoByIP

	 * @param ip 
	 * @return 
	 */
	com.soffid.iam.am.api.Host getHostInfoByIP(
		final java.lang.String ip)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.UnknownHostException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getNetworkInfo

	 * @param network 
	 * @return 
	 */
	com.soffid.iam.am.api.Network getNetworkInfo(
		final java.lang.String network)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.UnknownNetworkException;

	/**
	 * Operation generateFakePassword

	 * @param passwordDomain 
	 * @return 
	 */
	com.soffid.iam.am.api.Password generateFakePassword(
		final java.lang.String passwordDomain)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation generateFakePassword

	 * @param account 
	 * @param dispatcherId 
	 * @return 
	 */
	com.soffid.iam.am.api.Password generateFakePassword(
		final java.lang.String account, 
		final java.lang.String dispatcherId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getAccountPassword

	 * @param account 
	 * @param dispatcher 
	 * @return 
	 */
	com.soffid.iam.am.api.Password getAccountPassword(
		final java.lang.String account, 
		final java.lang.String dispatcher)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getOrGenerateUserPassword

	 * @param account 
	 * @param dispatcherId 
	 * @return 
	 */
	com.soffid.iam.am.api.Password getOrGenerateUserPassword(
		final java.lang.String account, 
		final java.lang.String dispatcherId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getUserPolicy

	 * @param account 
	 * @param dispatcherId 
	 * @return 
	 */
	com.soffid.iam.am.api.PasswordPolicy getUserPolicy(
		final java.lang.String account, 
		final java.lang.String dispatcherId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation validatePassword

	 * @param account 
	 * @param dispatcherId 
	 * @param p 
	 * @return 
	 */
	com.soffid.iam.am.api.PasswordValidation validatePassword(
		final java.lang.String account, 
		final java.lang.String dispatcherId, 
		final com.soffid.iam.am.api.Password p)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InvalidPasswordException, com.soffid.iam.exception.UnknownUserException;

	/**
	 * Operation findAccountByLoginNameAndSystem

	 * @param loginName 
	 * @param system 
	 * @return 
	 */
	com.soffid.iam.base.api.Account findAccountByLoginNameAndSystem(
		final java.lang.String loginName, 
		final java.lang.String system)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getAccountInfo
	 * Retrieves an account from soffid database

	 * @param accountName 
	 * @param dispatcherId 
	 * @return 
	 */
	com.soffid.iam.base.api.Account getAccountInfo(
		final java.lang.String accountName, 
		final java.lang.String dispatcherId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getAccountInfoByExternalId
	 * Retrieves an account from soffid database

	 * @param externalId 
	 * @param dispatcherId 
	 * @return 
	 */
	com.soffid.iam.base.api.Account getAccountInfoByExternalId(
		final java.lang.String externalId, 
		final java.lang.String dispatcherId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation parseKerberosToken
	 * Parses a kerberos token.

	 * @param domain 
	 * @param serviceName 
	 * @param keytab 
	 * @param token 
	 * @return 
	 */
	com.soffid.iam.base.api.Account parseKerberosToken(
		final java.lang.String domain, 
		final java.lang.String serviceName, 
		final byte[] keytab, 
		final byte[] token)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getUserInfo

	 * @param account 
	 * @param dispatcherId 
	 * @return 
	 */
	com.soffid.iam.base.api.User getUserInfo(
		final java.lang.String account, 
		final java.lang.String dispatcherId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getUserInfo

	 * @param certs 
	 * @return 
	 */
	com.soffid.iam.base.api.User getUserInfo(
		final java.security.cert.X509Certificate[] certs)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getUserInfo

	 * @param userId 
	 * @return 
	 */
	com.soffid.iam.base.api.User getUserInfo(
		final long userId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getUserData

	 * @param userId 
	 * @param data 
	 * @return 
	 */
	com.soffid.iam.base.api.UserData getUserData(
		final long userId, 
		final java.lang.String data)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.UnknownUserException;

	/**
	 * Operation getCustomObject

	 * @param type 
	 * @param name 
	 * @return 
	 */
	com.soffid.iam.iga.api.CustomObject getCustomObject(
		final java.lang.String type, 
		final java.lang.String name)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getGroupInfo

	 * @param codi 
	 * @param dispatcherId 
	 * @return 
	 */
	com.soffid.iam.iga.api.Group getGroupInfo(
		final java.lang.String codi, 
		final java.lang.String dispatcherId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getMailList

	 * @param list 
	 * @param domain 
	 * @return 
	 */
	com.soffid.iam.iga.api.MailList getMailList(
		final java.lang.String list, 
		final java.lang.String domain)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.UnknownMailListException;

	/**
	 * Operation getMailListById

	 * @param id 
	 * @return 
	 */
	com.soffid.iam.iga.api.MailList getMailListById(
		final java.lang.Long id)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.UnknownMailListException;

	/**
	 * Operation getRoleInfo

	 * @param role 
	 * @param bd 
	 * @return 
	 */
	com.soffid.iam.iga.api.Role getRoleInfo(
		final java.lang.String role, 
		final java.lang.String bd)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getRoleInfoByExternalId

	 * @param externalId 
	 * @param bd 
	 * @return 
	 */
	com.soffid.iam.iga.api.Role getRoleInfoByExternalId(
		final java.lang.String externalId, 
		final java.lang.String bd)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getDispatcherInfo

	 * @param codi 
	 * @return 
	 */
	com.soffid.iam.iga.api.System getDispatcherInfo(
		final java.lang.String codi)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation updateSystem

	 * @param s 
	 * @return 
	 */
	com.soffid.iam.iga.api.System updateSystem(
		final com.soffid.iam.iga.api.System s)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getDispatcherAccessControl

	 * @param dispatcherId 
	 * @return 
	 */
	com.soffid.iam.iga.api.SystemAccessControl getDispatcherAccessControl(
		final java.lang.Long dispatcherId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation registerIssue

	 * @param e 
	 * @return 
	 */
	com.soffid.iam.rc.api.Issue registerIssue(
		final com.soffid.iam.rc.api.Issue e)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getPlugin

	 * @param className 
	 * @return 
	 */
	com.soffid.iam.sync.agent.Plugin getPlugin(
		final java.lang.String className)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getConfig

	 * @param param 
	 * @return 
	 */
	java.lang.String getConfig(
		final java.lang.String param)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getDefaultDispatcher

	 * @return 
	 */
	java.lang.String getDefaultDispatcher()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation reverseTranslate

	 * @param domain 
	 * @param column2 
	 * @return 
	 */
	java.lang.String reverseTranslate(
		final java.lang.String domain, 
		final java.lang.String column2)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation translate

	 * @param domain 
	 * @param column1 
	 * @return 
	 */
	java.lang.String translate(
		final java.lang.String domain, 
		final java.lang.String column1)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getAccountExplicitRoles

	 * @param account 
	 * @param dispatcherId 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.RoleGrant> getAccountExplicitRoles(
		final java.lang.String account, 
		final java.lang.String dispatcherId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getAccountRoles

	 * @param account 
	 * @param dispatcherId 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.RoleGrant> getAccountRoles(
		final java.lang.String account, 
		final java.lang.String dispatcherId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getExpiredPasswordDomains

	 * @param usuari 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.am.api.PasswordDomain> getExpiredPasswordDomains(
		final com.soffid.iam.base.api.User usuari)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getGroupChildren

	 * @param groupId 
	 * @param dispatcherId 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.Group> getGroupChildren(
		final long groupId, 
		final java.lang.String dispatcherId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getGroupExplicitRoles

	 * @param groupId 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.RoleGrant> getGroupExplicitRoles(
		final long groupId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getGroupUsers

	 * @param groupId 
	 * @param nomesUsuarisActius 
	 * @param dispatcherId 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.base.api.User> getGroupUsers(
		final long groupId, 
		final boolean nomesUsuarisActius, 
		final java.lang.String dispatcherId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.UnknownGroupException;

	/**
	 * Operation getHostsFromNetwork

	 * @param networkId 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.am.api.Host> getHostsFromNetwork(
		final long networkId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.UnknownNetworkException;

	/**
	 * Operation getMailListMembers

	 * @param mail 
	 * @param domainName 
	 * @return 
	 */
	java.util.Collection<java.lang.Object> getMailListMembers(
		final java.lang.String mail, 
		final java.lang.String domainName)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.UnknownMailListException;

	/**
	 * Operation getNetworksList

	 * @return 
	 */
	java.util.Collection<com.soffid.iam.am.api.Network> getNetworksList()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getRoleAccounts

	 * @param roleId 
	 * @param dispatcherId 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.base.api.Account> getRoleAccounts(
		final long roleId, 
		final java.lang.String dispatcherId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.UnknownRoleException;

	/**
	 * Operation getRoleActiveAccounts

	 * @param roleId 
	 * @param dispatcher 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.base.api.Account> getRoleActiveAccounts(
		final long roleId, 
		final java.lang.String dispatcher)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.UnknownRoleException;

	/**
	 * Operation getRoleExplicitRoles

	 * @param roleId 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.RoleGrant> getRoleExplicitRoles(
		final long roleId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.UnknownRoleException;

	/**
	 * Operation getServices

	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.System> getServices()
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getUserAccounts

	 * @param userId 
	 * @param dispatcherId 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.base.api.UserAccount> getUserAccounts(
		final long userId, 
		final java.lang.String dispatcherId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getUserData

	 * @param userId 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.base.api.UserData> getUserData(
		final long userId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.UnknownUserException;

	/**
	 * Operation getUserExplicitRoles

	 * @param userId 
	 * @param dispatcherId 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.RoleGrant> getUserExplicitRoles(
		final long userId, 
		final java.lang.String dispatcherId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.UnknownUserException;

	/**
	 * Operation getUserGroups

	 * @param userId 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.Group> getUserGroups(
		final java.lang.Long userId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getUserGroups

	 * @param accountName 
	 * @param dispatcherId 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.Group> getUserGroups(
		final java.lang.String accountName, 
		final java.lang.String dispatcherId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.UnknownUserException;

	/**
	 * Operation getUserGroupsHierarchy

	 * @param userId 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.Group> getUserGroupsHierarchy(
		final java.lang.Long userId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getUserGroupsHierarchy

	 * @param accountName 
	 * @param dispatcherId 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.Group> getUserGroupsHierarchy(
		final java.lang.String accountName, 
		final java.lang.String dispatcherId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.UnknownUserException;

	/**
	 * Operation getUserMemberships

	 * @param accountName 
	 * @param dispatcherId 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.UserGroup> getUserMemberships(
		final java.lang.String accountName, 
		final java.lang.String dispatcherId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.UnknownUserException;

	/**
	 * Operation getUserPrinters

	 * @param userId 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.PrinterUser> getUserPrinters(
		final java.lang.Long userId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.UnknownUserException;

	/**
	 * Operation getUserRoles

	 * @param userId 
	 * @param dispatcherid 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.RoleGrant> getUserRoles(
		final long userId, 
		final java.lang.String dispatcherid)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.UnknownUserException;

	/**
	 * Operation getUserSecrets

	 * @param userId 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.sync.api.Secret> getUserSecrets(
		final long userId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.UnknownUserException;

	/**
	 * Operation reverseTranslate2

	 * @param domain 
	 * @param column2 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.AttributeTranslation> reverseTranslate2(
		final java.lang.String domain, 
		final java.lang.String column2)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation translate2

	 * @param domain 
	 * @param column1 
	 * @return 
	 */
	java.util.Collection<com.soffid.iam.iga.api.AttributeTranslation> translate2(
		final java.lang.String domain, 
		final java.lang.String column1)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getAddonList

	 * @return 
	 */
	java.util.List<java.lang.String> getAddonList()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findActiveDirectoryDomains

	 * @return 
	 */
	java.util.Map<java.lang.String,java.lang.String> findActiveDirectoryDomains()
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getUserAttributes

	 * @param userId 
	 * @return 
	 */
	java.util.Map<java.lang.String,java.lang.Object> getUserAttributes(
		final long userId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.UnknownUserException;

	/**
	 * Operation propagateOBUser

	 * @param usuari 
	 * @return 
	 */
	java.util.Map propagateOBUser(
		final com.soffid.iam.base.api.User usuari)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getMyConfig

	 * @return 
	 */
	java.util.Properties getMyConfig()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.ServerRedirectException;

	/**
	 * Operation addCertificate

	 * @param cert 
	 */
	void addCertificate(
		final java.security.cert.X509Certificate cert)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation cancelTask

	 * @param taskid 
	 * @param taskHash 
	 */
	void cancelTask(
		final long taskid, 
		final java.lang.String taskHash)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation changePassword

	 * @param account 
	 * @param dispatcherId 
	 * @param p 
	 * @param mustChange 
	 */
	void changePassword(
		final java.lang.String account, 
		final java.lang.String dispatcherId, 
		final com.soffid.iam.am.api.Password p, 
		final boolean mustChange)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.BadPasswordException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation changePasswordSync

	 * @param account 
	 * @param dispatcherId 
	 * @param p 
	 * @param mustChange 
	 */
	void changePasswordSync(
		final java.lang.String account, 
		final java.lang.String dispatcherId, 
		final com.soffid.iam.am.api.Password p, 
		final boolean mustChange)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.BadPasswordException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation clientAgentStarted

	 * @param serverName 
	 */
	void clientAgentStarted(
		final java.lang.String serverName)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getMainJar

	 */
	void getMainJar()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation reconcileAccount
	 * Updates Soffid image of the reconciled account. Only triggers a UpdateUser for user accounts with new or removed roles.

	 * @param account 
	 * @param grants 
	 */
	void reconcileAccount(
		final com.soffid.iam.base.api.Account account, 
		final java.util.List<com.soffid.iam.iga.api.RoleAccount> grants)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation processAuthoritativeChange

	 * @param change 
	 * @param remove 
	 */
	void processAuthoritativeChange(
		final com.soffid.iam.sync.api.AuthoritativeChange change, 
		final boolean remove)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation reconcileAccount

	 * @param system 
	 * @param account 
	 */
	void reconcileAccount(
		final java.lang.String system, 
		final java.lang.String account)
			throws com.soffid.iam.exception.InternalErrorException;

}
