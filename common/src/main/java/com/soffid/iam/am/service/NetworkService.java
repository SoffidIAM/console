//
// (C) 2013 Soffid
//
//

package com.soffid.iam.am.service;
/**
 * Service NetworkService
 */
public interface NetworkService {
	public final static String REMOTE_PATH = "/seycon/com.soffid.iam.am.service.NetworkService";

	public final static String SERVICE_NAME = "com.soffid.iam.am.service.NetworkService";

	/**
	 * Operation canLogin

	 * @param user 
	 * @param host 
	 * @return 
	 */
	boolean canLogin(
		final java.lang.String user, 
		final java.lang.String host)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation create

	 * @param maquina 
	 * @return 
	 */
	com.soffid.iam.am.api.Host create(
		final com.soffid.iam.am.api.Host maquina)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findHostById

	 * @param idMaquina 
	 * @return 
	 */
	com.soffid.iam.am.api.Host findHostById(
		final java.lang.Long idMaquina)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findHostByIp

	 * @param ip 
	 * @return 
	 */
	com.soffid.iam.am.api.Host findHostByIp(
		final java.lang.String ip)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findHostByName

	 * @param nom 
	 * @return 
	 */
	com.soffid.iam.am.api.Host findHostByName(
		final java.lang.String nom)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findHostBySerialNumber

	 * @param serialNumber 
	 * @return 
	 */
	com.soffid.iam.am.api.Host findHostBySerialNumber(
		final java.lang.String serialNumber)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation registerDynamicIP

	 * @param nomMaquina 
	 * @param ip 
	 * @param serialNumber 
	 * @return 
	 */
	com.soffid.iam.am.api.Host registerDynamicIP(
		final java.lang.String nomMaquina, 
		final java.lang.String ip, 
		final java.lang.String serialNumber)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.UnknownHostException, com.soffid.iam.exception.UnknownNetworkException;

	/**
	 * Operation create

	 * @param aliasMaquina 
	 * @return 
	 */
	com.soffid.iam.am.api.HostAlias create(
		final com.soffid.iam.am.api.HostAlias aliasMaquina)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation create

	 * @param xarxa 
	 * @return 
	 */
	com.soffid.iam.am.api.Network create(
		final com.soffid.iam.am.api.Network xarxa)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findNetworkByIpAddress

	 * @param ipAdress 
	 * @return 
	 */
	com.soffid.iam.am.api.Network findNetworkByIpAddress(
		final java.lang.String ipAdress)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findNetworkByName

	 * @param codi 
	 * @return 
	 */
	com.soffid.iam.am.api.Network findNetworkByName(
		final java.lang.String codi)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation create

	 * @param accessList 
	 * @return 
	 */
	com.soffid.iam.am.api.NetworkAuthorization create(
		final com.soffid.iam.am.api.NetworkAuthorization accessList)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findNetworkAuthorizationsByNetworkNameAndIdentityName

	 * @param codiXarxa 
	 * @param codiIdentitat 
	 * @return 
	 */
	com.soffid.iam.am.api.NetworkAuthorization findNetworkAuthorizationsByNetworkNameAndIdentityName(
		final java.lang.String codiXarxa, 
		final java.lang.String codiIdentitat)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation update

	 * @param accessList 
	 * @return 
	 */
	com.soffid.iam.am.api.NetworkAuthorization update(
		final com.soffid.iam.am.api.NetworkAuthorization accessList)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation create

	 * @param osType 
	 * @return 
	 */
	com.soffid.iam.am.api.OsType create(
		final com.soffid.iam.am.api.OsType osType)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findOSTypeById

	 * @param osId 
	 * @return 
	 */
	com.soffid.iam.am.api.OsType findOSTypeById(
		final java.lang.Long osId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findOSTypeByName

	 * @param osName 
	 * @return 
	 */
	com.soffid.iam.am.api.OsType findOSTypeByName(
		final java.lang.String osName)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findIdentityByName

	 * @param codi 
	 * @return 
	 */
	com.soffid.iam.base.api.Identity findIdentityByName(
		final java.lang.String codi)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation create

	 * @param autoritzacioAccesComAdministrador 
	 * @return 
	 */
	com.soffid.iam.pam.api.HostAdmin create(
		final com.soffid.iam.pam.api.HostAdmin autoritzacioAccesComAdministrador)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation revokeAdministratorAccessHost

	 * @param autoritzacioAccesComAdministrador 
	 * @return 
	 */
	com.soffid.iam.pam.api.HostAdmin revokeAdministratorAccessHost(
		final com.soffid.iam.pam.api.HostAdmin autoritzacioAccesComAdministrador)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findHosts

	 * @param query 
	 * @return 
	 */
	com.soffid.zkdb.api.PagedResult<com.soffid.iam.am.api.Host> findHosts(
		final com.soffid.zkdb.api.Query query)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findNetworks

	 * @param query 
	 * @return 
	 */
	com.soffid.zkdb.api.PagedResult<com.soffid.iam.am.api.Network> findNetworks(
		final com.soffid.zkdb.api.Query query)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation isManaged

	 * @param codiXarxa 
	 * @return 
	 */
	java.lang.Boolean isManaged(
		final java.lang.String codiXarxa)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation hasAnyACLNetworks

	 * @param codiUsuari 
	 * @return 
	 */
	java.lang.Boolean hasAnyACLNetworks(
		final java.lang.String codiUsuari)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation launchVNC

	 * @param sessioId 
	 * @return 
	 */
	java.lang.Boolean launchVNC(
		final java.lang.Long sessioId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation hasNetworkAccess

	 * @param codiUsuari 
	 * @param codiXarxa 
	 * @return 
	 */
	java.lang.Boolean hasNetworkAccess(
		final java.lang.String codiUsuari, 
		final java.lang.String codiXarxa)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation hasManagedNetwork

	 * @return 
	 */
	java.lang.Boolean hasManagedNetwork()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findAccessLevelByHostNameAndNetworkName

	 * @param nomMaquina 
	 * @param codiXarxa 
	 * @return 
	 */
	java.lang.Long findAccessLevelByHostNameAndNetworkName(
		final java.lang.String nomMaquina, 
		final java.lang.String codiXarxa)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getAvailableIPs

	 * @param codiXarxa 
	 * @return 
	 */
	java.lang.Long getAvailableIPs(
		final java.lang.String codiXarxa)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getNotAvailableIPs

	 * @param codiXarxa 
	 * @return 
	 */
	java.lang.Long getNotAvailableIPs(
		final java.lang.String codiXarxa)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getFirstAvailableIP

	 * @param codiXarxa 
	 * @return 
	 */
	java.lang.String getFirstAvailableIP(
		final java.lang.String codiXarxa)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getTasks

	 * @param nomMaquina 
	 * @return 
	 */
	java.lang.String[] getTasks(
		final java.lang.String nomMaquina)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getHostAdminUserAndPassword

	 * @param nomMaquina 
	 * @return 
	 */
	java.lang.String[] getHostAdminUserAndPassword(
		final java.lang.String nomMaquina)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findAllNetworkAuthorizationsByUserName

	 * @param codiUsuari 
	 * @return 
	 */
	java.util.List<com.soffid.iam.am.api.NetworkAuthorization> findAllNetworkAuthorizationsByUserName(
		final java.lang.String codiUsuari)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findAliasByHostName

	 * @param nomMaquina 
	 * @return 
	 */
	java.util.List<com.soffid.iam.am.api.HostAlias> findAliasByHostName(
		final java.lang.String nomMaquina)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findAllOSTypes

	 * @return 
	 */
	java.util.List<com.soffid.iam.am.api.OsType> findAllOSTypes()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findAuthorizationsToAccessHostWithAdministratorRigthsByHostsAndRequestDate

	 * @param nomHost 
	 * @param dataPeticio 
	 * @param dataCaducitat 
	 * @return 
	 */
	java.util.List<com.soffid.iam.pam.api.HostAdmin> findAuthorizationsToAccessHostWithAdministratorRigthsByHostsAndRequestDate(
		final java.lang.String nomHost, 
		final java.lang.String dataPeticio, 
		final java.lang.String dataCaducitat)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findHostsByNetwork_Discovery
	 * Finds all hosts or search contained host by name or description or IP

	 * @param parent 
	 * @param text 
	 * @return 
	 */
	java.util.List<com.soffid.iam.am.api.Host> findHostsByNetwork_Discovery(
		final com.soffid.iam.am.api.Network parent, 
		final java.lang.String text)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findIdentitiesByName

	 * @param codi 
	 * @return 
	 */
	java.util.List<com.soffid.iam.base.api.Identity> findIdentitiesByName(
		final java.lang.String codi)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findNetworkAuthorizationsByGroupName

	 * @param codiGrup 
	 * @return 
	 */
	java.util.List<com.soffid.iam.am.api.NetworkAuthorization> findNetworkAuthorizationsByGroupName(
		final java.lang.String codiGrup)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findNetworkAuthorizationsByUserName

	 * @param codiUsuari 
	 * @return 
	 */
	java.util.List<com.soffid.iam.am.api.NetworkAuthorization> findNetworkAuthorizationsByUserName(
		final java.lang.String codiUsuari)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findNetworkAuthorizationsByRoleName

	 * @param nomRol 
	 * @return 
	 */
	java.util.List<com.soffid.iam.am.api.NetworkAuthorization> findNetworkAuthorizationsByRoleName(
		final java.lang.String nomRol)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findNetworkByText_Discovery
	 * Finds network by network name or network description or network IP or contained host name or description or IP

	 * @param text 
	 * @return 
	 */
	java.util.List<com.soffid.iam.am.api.Network> findNetworkByText_Discovery(
		final java.lang.String text)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findSessionsByHostName

	 * @param codiMaquina 
	 * @return 
	 */
	java.util.List<com.soffid.iam.am.api.Session> findSessionsByHostName(
		final java.lang.String codiMaquina)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getACL

	 * @param xarxa 
	 * @return 
	 */
	java.util.List<com.soffid.iam.am.api.NetworkAuthorization> getACL(
		final com.soffid.iam.am.api.Network xarxa)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getNetworks

	 * @return 
	 */
	java.util.List<com.soffid.iam.am.api.Network> getNetworks()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation delete

	 * @param maquina 
	 */
	void delete(
		final com.soffid.iam.am.api.Host maquina)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation delete

	 * @param aliasMaquina 
	 */
	void delete(
		final com.soffid.iam.am.api.HostAlias aliasMaquina)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation delete

	 * @param xarxa 
	 */
	void delete(
		final com.soffid.iam.am.api.Network xarxa)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation delete

	 * @param accessList 
	 */
	void delete(
		final com.soffid.iam.am.api.NetworkAuthorization accessList)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation delete

	 * @param osType 
	 */
	void delete(
		final com.soffid.iam.am.api.OsType osType)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation setAdministratorPassword

	 * @param nomMaquina 
	 * @param adminUser 
	 * @param adminPass 
	 */
	void setAdministratorPassword(
		final java.lang.String nomMaquina, 
		final java.lang.String adminUser, 
		final java.lang.String adminPass)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation update

	 * @param maquina 
	 */
	void update(
		final com.soffid.iam.am.api.Host maquina)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation update

	 * @param aliasMaquina 
	 */
	void update(
		final com.soffid.iam.am.api.HostAlias aliasMaquina)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation update

	 * @param xarxa 
	 */
	void update(
		final com.soffid.iam.am.api.Network xarxa)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation update

	 * @param osType 
	 */
	void update(
		final com.soffid.iam.am.api.OsType osType)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

}
