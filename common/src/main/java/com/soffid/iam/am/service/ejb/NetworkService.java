//
// (C) 2013 Soffid
//
//

package com.soffid.iam.am.service.ejb;
/**
 * EJB NetworkService
 */
public interface NetworkService

 {

	com.soffid.iam.am.api.Host create(
		final com.soffid.iam.am.api.Host maquina)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.am.api.Host findHostById(
		final java.lang.Long idMaquina)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.am.api.Host findHostByIp(
		final java.lang.String ip)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.am.api.Host findHostByName(
		final java.lang.String nom)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.am.api.Host registerDynamicIP(
		final java.lang.String nomMaquina, 
		final java.lang.String ip, 
		final java.lang.String serialNumber)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.UnknownHostException, com.soffid.iam.exception.UnknownNetworkException;

	com.soffid.iam.am.api.HostAlias create(
		final com.soffid.iam.am.api.HostAlias aliasMaquina)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.am.api.Network create(
		final com.soffid.iam.am.api.Network xarxa)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.am.api.Network findNetworkByName(
		final java.lang.String codi)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.am.api.NetworkAuthorization create(
		final com.soffid.iam.am.api.NetworkAuthorization accessList)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.am.api.NetworkAuthorization findNetworkAuthorizationsByNetworkNameAndIdentityName(
		final java.lang.String codiXarxa, 
		final java.lang.String codiIdentitat)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.am.api.NetworkAuthorization update(
		final com.soffid.iam.am.api.NetworkAuthorization accessList)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.am.api.OsType create(
		final com.soffid.iam.am.api.OsType osType)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.am.api.OsType findOSTypeById(
		final java.lang.Long osId)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.base.api.Identity findIdentityByName(
		final java.lang.String codi)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.pam.api.HostAdmin create(
		final com.soffid.iam.pam.api.HostAdmin autoritzacioAccesComAdministrador)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.pam.api.HostAdmin revokeAdministratorAccessHost(
		final com.soffid.iam.pam.api.HostAdmin autoritzacioAccesComAdministrador)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.zkdb.api.PagedResult<com.soffid.iam.am.api.Host> findHosts(
		final com.soffid.zkdb.api.Query query)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.zkdb.api.PagedResult<com.soffid.iam.am.api.Network> findNetworks(
		final com.soffid.zkdb.api.Query query)
	throws com.soffid.iam.exception.InternalErrorException;

	java.lang.Boolean isManaged(
		final java.lang.String codiXarxa)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.lang.Boolean hasAnyACLNetworks(
		final java.lang.String codiUsuari)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.lang.Boolean launchVNC(
		final java.lang.Long sessioId)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.lang.Boolean hasNetworkAccess(
		final java.lang.String codiUsuari, 
		final java.lang.String codiXarxa)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.lang.Boolean hasManagedNetwork()
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.lang.Long findAccessLevelByHostNameAndNetworkName(
		final java.lang.String nomMaquina, 
		final java.lang.String codiXarxa)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.lang.Long getAvailableIPs(
		final java.lang.String codiXarxa)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.lang.Long getNotAvailableIPs(
		final java.lang.String codiXarxa)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.lang.String getFirstAvailableIP(
		final java.lang.String codiXarxa)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.lang.String[] getTasks(
		final java.lang.String nomMaquina)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.lang.String[] getHostAdminUserAndPassword(
		final java.lang.String nomMaquina)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.List<com.soffid.iam.am.api.NetworkAuthorization> findAllNetworkAuthorizationsByUserName(
		final java.lang.String codiUsuari)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.List<com.soffid.iam.am.api.HostAlias> findAliasByHostName(
		final java.lang.String nomMaquina)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.List<com.soffid.iam.am.api.OsType> findAllOSTypes()
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.List<com.soffid.iam.pam.api.HostAdmin> findAuthorizationsToAccessHostWithAdministratorRigthsByHostsAndRequestDate(
		final java.lang.String nomHost, 
		final java.lang.String dataPeticio, 
		final java.lang.String dataCaducitat)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.List<com.soffid.iam.am.api.Host> findHostsByNetwork_Discovery(
		final com.soffid.iam.am.api.Network parent, 
		final java.lang.String text)
	throws com.soffid.iam.exception.InternalErrorException;

	java.util.List<com.soffid.iam.base.api.Identity> findIdentitiesByName(
		final java.lang.String codi)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.List<com.soffid.iam.am.api.NetworkAuthorization> findNetworkAuthorizationsByGroupName(
		final java.lang.String codiGrup)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.List<com.soffid.iam.am.api.NetworkAuthorization> findNetworkAuthorizationsByUserName(
		final java.lang.String codiUsuari)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.List<com.soffid.iam.am.api.NetworkAuthorization> findNetworkAuthorizationsByRoleName(
		final java.lang.String nomRol)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.List<com.soffid.iam.am.api.Network> findNetworkByText_Discovery(
		final java.lang.String text)
	throws com.soffid.iam.exception.InternalErrorException;

	java.util.List<com.soffid.iam.am.api.Session> findSessionsByHostName(
		final java.lang.String codiMaquina)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.List<com.soffid.iam.am.api.NetworkAuthorization> getACL(
		final com.soffid.iam.am.api.Network xarxa)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.List<com.soffid.iam.am.api.Network> getNetworks()
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void delete(
		final com.soffid.iam.am.api.Host maquina)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void delete(
		final com.soffid.iam.am.api.HostAlias aliasMaquina)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void delete(
		final com.soffid.iam.am.api.Network xarxa)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void delete(
		final com.soffid.iam.am.api.NetworkAuthorization accessList)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void delete(
		final com.soffid.iam.am.api.OsType osType)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void setAdministratorPassword(
		final java.lang.String nomMaquina, 
		final java.lang.String adminUser, 
		final java.lang.String adminPass)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void update(
		final com.soffid.iam.am.api.Host maquina)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void update(
		final com.soffid.iam.am.api.HostAlias aliasMaquina)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void update(
		final com.soffid.iam.am.api.Network xarxa)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void update(
		final com.soffid.iam.am.api.OsType osType)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

}
