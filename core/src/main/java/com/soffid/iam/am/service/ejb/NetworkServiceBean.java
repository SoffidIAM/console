//
// (C) 2013 Soffid
//
//

package com.soffid.iam.am.service.ejb;
/**
 * @see <code>com.soffid.iam.am.service.NetworkService</code>,
 * @see <code>com.soffid.iam.am.service.NetworkService</code>,
 */
@jakarta.ejb.Stateless(name="NetworkService", mappedName="openejb:/local/soffid.ejb.com.soffid.iam.am.service.NetworkService")
@jakarta.ejb.Local(com.soffid.iam.am.service.ejb.NetworkService.class)
@jakarta.ejb.TransactionManagement(value=jakarta.ejb.TransactionManagementType.CONTAINER)
@jakarta.ejb.TransactionAttribute(value=jakarta.ejb.TransactionAttributeType.SUPPORTS)
public class NetworkServiceBean extends org.springframework.ejb.support.AbstractStatelessSessionBean
  implements com.soffid.iam.am.service.ejb.NetworkService
{
	private com.soffid.iam.am.service.NetworkService networkService;

	/**
	 * @see com.soffid.iam.am.service.NetworkService#com.soffid.iam.am.api.Host create(com.soffid.iam.am.api.Host maquina)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.am.api.Host create(
		final com.soffid.iam.am.api.Host maquina)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.networkService.create(maquina); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.am.service.NetworkService#com.soffid.iam.am.api.Host findHostById(java.lang.Long idMaquina)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.am.api.Host findHostById(
		final java.lang.Long idMaquina)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.networkService.findHostById(idMaquina); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.am.service.NetworkService#com.soffid.iam.am.api.Host findHostByIp(java.lang.String ip)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.am.api.Host findHostByIp(
		final java.lang.String ip)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.networkService.findHostByIp(ip); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.am.service.NetworkService#com.soffid.iam.am.api.Host findHostByName(java.lang.String nom)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.am.api.Host findHostByName(
		final java.lang.String nom)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.networkService.findHostByName(nom); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.am.service.NetworkService#com.soffid.iam.am.api.Host registerDynamicIP(java.lang.String nomMaquina, java.lang.String ip, java.lang.String serialNumber)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.am.api.Host registerDynamicIP(
		final java.lang.String nomMaquina, 
		final java.lang.String ip, 
		final java.lang.String serialNumber)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.UnknownHostException, com.soffid.iam.exception.UnknownNetworkException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.networkService.registerDynamicIP(nomMaquina, ip, serialNumber); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.UnknownHostException)
				throw (com.soffid.iam.exception.UnknownHostException) cause;
			if (cause instanceof com.soffid.iam.exception.UnknownNetworkException)
				throw (com.soffid.iam.exception.UnknownNetworkException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.am.service.NetworkService#com.soffid.iam.am.api.HostAlias create(com.soffid.iam.am.api.HostAlias aliasMaquina)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.am.api.HostAlias create(
		final com.soffid.iam.am.api.HostAlias aliasMaquina)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.networkService.create(aliasMaquina); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.am.service.NetworkService#com.soffid.iam.am.api.Network create(com.soffid.iam.am.api.Network xarxa)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.am.api.Network create(
		final com.soffid.iam.am.api.Network xarxa)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("network:all:create"))
			throw new SecurityException("Unable to execute NetworkService.create. Required roles: [network:all:create]");
		try
		{
			return this.networkService.create(xarxa); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.am.service.NetworkService#com.soffid.iam.am.api.Network findNetworkByName(java.lang.String codi)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.am.api.Network findNetworkByName(
		final java.lang.String codi)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.networkService.findNetworkByName(codi); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.am.service.NetworkService#com.soffid.iam.am.api.NetworkAuthorization create(com.soffid.iam.am.api.NetworkAuthorization accessList)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.am.api.NetworkAuthorization create(
		final com.soffid.iam.am.api.NetworkAuthorization accessList)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("network:all:create")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("network:all:update"))
			throw new SecurityException("Unable to execute NetworkService.create. Required roles: [network:all:create, network_all_update]");
		try
		{
			return this.networkService.create(accessList); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.am.service.NetworkService#com.soffid.iam.am.api.NetworkAuthorization findNetworkAuthorizationsByNetworkNameAndIdentityName(java.lang.String codiXarxa, java.lang.String codiIdentitat)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.am.api.NetworkAuthorization findNetworkAuthorizationsByNetworkNameAndIdentityName(
		final java.lang.String codiXarxa, 
		final java.lang.String codiIdentitat)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("host:all:query")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("network:all:query"))
			throw new SecurityException("Unable to execute NetworkService.findNetworkAuthorizationsByNetworkNameAndIdentityName. Required roles: [host:all:query, network_all_query]");
		try
		{
			return this.networkService.findNetworkAuthorizationsByNetworkNameAndIdentityName(codiXarxa, codiIdentitat); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.am.service.NetworkService#com.soffid.iam.am.api.NetworkAuthorization update(com.soffid.iam.am.api.NetworkAuthorization accessList)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.am.api.NetworkAuthorization update(
		final com.soffid.iam.am.api.NetworkAuthorization accessList)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("network:all:create")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("network:all:update"))
			throw new SecurityException("Unable to execute NetworkService.update. Required roles: [network:all:create, network_all_update]");
		try
		{
			return this.networkService.update(accessList); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.am.service.NetworkService#com.soffid.iam.am.api.OsType create(com.soffid.iam.am.api.OsType osType)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.am.api.OsType create(
		final com.soffid.iam.am.api.OsType osType)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("operatingSystem:create"))
			throw new SecurityException("Unable to execute NetworkService.create. Required roles: [operatingSystem:create]");
		try
		{
			return this.networkService.create(osType); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.am.service.NetworkService#com.soffid.iam.am.api.OsType findOSTypeById(java.lang.Long osId)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.am.api.OsType findOSTypeById(
		final java.lang.Long osId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.networkService.findOSTypeById(osId); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.am.service.NetworkService#com.soffid.iam.base.api.Identity findIdentityByName(java.lang.String codi)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.base.api.Identity findIdentityByName(
		final java.lang.String codi)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("host:all:query")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("network:all:query"))
			throw new SecurityException("Unable to execute NetworkService.findIdentityByName. Required roles: [host:all:query, network_all_query]");
		try
		{
			return this.networkService.findIdentityByName(codi); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.am.service.NetworkService#com.soffid.iam.pam.api.HostAdmin create(com.soffid.iam.pam.api.HostAdmin autoritzacioAccesComAdministrador)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.pam.api.HostAdmin create(
		final com.soffid.iam.pam.api.HostAdmin autoritzacioAccesComAdministrador)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.networkService.create(autoritzacioAccesComAdministrador); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.am.service.NetworkService#com.soffid.iam.pam.api.HostAdmin revokeAdministratorAccessHost(com.soffid.iam.pam.api.HostAdmin autoritzacioAccesComAdministrador)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.pam.api.HostAdmin revokeAdministratorAccessHost(
		final com.soffid.iam.pam.api.HostAdmin autoritzacioAccesComAdministrador)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.networkService.revokeAdministratorAccessHost(autoritzacioAccesComAdministrador); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.am.service.NetworkService#com.soffid.zkdb.api.PagedResult<com.soffid.iam.am.api.Host> findHosts(com.soffid.zkdb.api.Query query)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.am.api.Host> findHosts(
		final com.soffid.zkdb.api.Query query)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("host:all:query")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("host:query"))
			throw new SecurityException("Unable to execute NetworkService.findHosts. Required roles: [host:all:query, host_query]");
		try
		{
			return this.networkService.findHosts(query); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.am.service.NetworkService#com.soffid.zkdb.api.PagedResult<com.soffid.iam.am.api.Network> findNetworks(com.soffid.zkdb.api.Query query)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.am.api.Network> findNetworks(
		final com.soffid.zkdb.api.Query query)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.networkService.findNetworks(query); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.am.service.NetworkService#java.lang.Boolean isManaged(java.lang.String codiXarxa)
	 */
	@jakarta.annotation.security.PermitAll
	public java.lang.Boolean isManaged(
		final java.lang.String codiXarxa)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("host:all:query")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("network:all:query"))
			throw new SecurityException("Unable to execute NetworkService.isManaged. Required roles: [host:all:query, network_all_query]");
		try
		{
			return this.networkService.isManaged(codiXarxa); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.am.service.NetworkService#java.lang.Boolean hasAnyACLNetworks(java.lang.String codiUsuari)
	 */
	@jakarta.annotation.security.PermitAll
	public java.lang.Boolean hasAnyACLNetworks(
		final java.lang.String codiUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.networkService.hasAnyACLNetworks(codiUsuari); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.am.service.NetworkService#java.lang.Boolean launchVNC(java.lang.Long sessioId)
	 */
	@jakarta.annotation.security.PermitAll
	public java.lang.Boolean launchVNC(
		final java.lang.Long sessioId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.networkService.launchVNC(sessioId); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.am.service.NetworkService#java.lang.Boolean hasNetworkAccess(java.lang.String codiUsuari, java.lang.String codiXarxa)
	 */
	@jakarta.annotation.security.PermitAll
	public java.lang.Boolean hasNetworkAccess(
		final java.lang.String codiUsuari, 
		final java.lang.String codiXarxa)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("host:all:query")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("network:all:query"))
			throw new SecurityException("Unable to execute NetworkService.hasNetworkAccess. Required roles: [host:all:query, network_all_query]");
		try
		{
			return this.networkService.hasNetworkAccess(codiUsuari, codiXarxa); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.am.service.NetworkService#java.lang.Boolean hasManagedNetwork()
	 */
	@jakarta.annotation.security.PermitAll
	public java.lang.Boolean hasManagedNetwork()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.networkService.hasManagedNetwork(); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.am.service.NetworkService#java.lang.Long findAccessLevelByHostNameAndNetworkName(java.lang.String nomMaquina, java.lang.String codiXarxa)
	 */
	@jakarta.annotation.security.PermitAll
	public java.lang.Long findAccessLevelByHostNameAndNetworkName(
		final java.lang.String nomMaquina, 
		final java.lang.String codiXarxa)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.networkService.findAccessLevelByHostNameAndNetworkName(nomMaquina, codiXarxa); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.am.service.NetworkService#java.lang.Long getAvailableIPs(java.lang.String codiXarxa)
	 */
	@jakarta.annotation.security.PermitAll
	public java.lang.Long getAvailableIPs(
		final java.lang.String codiXarxa)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.networkService.getAvailableIPs(codiXarxa); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.am.service.NetworkService#java.lang.Long getNotAvailableIPs(java.lang.String codiXarxa)
	 */
	@jakarta.annotation.security.PermitAll
	public java.lang.Long getNotAvailableIPs(
		final java.lang.String codiXarxa)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.networkService.getNotAvailableIPs(codiXarxa); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.am.service.NetworkService#java.lang.String getFirstAvailableIP(java.lang.String codiXarxa)
	 */
	@jakarta.annotation.security.PermitAll
	public java.lang.String getFirstAvailableIP(
		final java.lang.String codiXarxa)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.networkService.getFirstAvailableIP(codiXarxa); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.am.service.NetworkService#java.lang.String[] getTasks(java.lang.String nomMaquina)
	 */
	@jakarta.annotation.security.PermitAll
	public java.lang.String[] getTasks(
		final java.lang.String nomMaquina)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.networkService.getTasks(nomMaquina); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.am.service.NetworkService#java.lang.String[] getHostAdminUserAndPassword(java.lang.String nomMaquina)
	 */
	@jakarta.annotation.security.PermitAll
	public java.lang.String[] getHostAdminUserAndPassword(
		final java.lang.String nomMaquina)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.networkService.getHostAdminUserAndPassword(nomMaquina); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.am.service.NetworkService#java.util.List<com.soffid.iam.am.api.NetworkAuthorization> findAllNetworkAuthorizationsByUserName(java.lang.String codiUsuari)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<com.soffid.iam.am.api.NetworkAuthorization> findAllNetworkAuthorizationsByUserName(
		final java.lang.String codiUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.networkService.findAllNetworkAuthorizationsByUserName(codiUsuari); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.am.service.NetworkService#java.util.List<com.soffid.iam.am.api.HostAlias> findAliasByHostName(java.lang.String nomMaquina)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<com.soffid.iam.am.api.HostAlias> findAliasByHostName(
		final java.lang.String nomMaquina)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.networkService.findAliasByHostName(nomMaquina); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.am.service.NetworkService#java.util.List<com.soffid.iam.am.api.OsType> findAllOSTypes()
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<com.soffid.iam.am.api.OsType> findAllOSTypes()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.networkService.findAllOSTypes(); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.am.service.NetworkService#java.util.List<com.soffid.iam.pam.api.HostAdmin> findAuthorizationsToAccessHostWithAdministratorRigthsByHostsAndRequestDate(java.lang.String nomHost, java.lang.String dataPeticio, java.lang.String dataCaducitat)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<com.soffid.iam.pam.api.HostAdmin> findAuthorizationsToAccessHostWithAdministratorRigthsByHostsAndRequestDate(
		final java.lang.String nomHost, 
		final java.lang.String dataPeticio, 
		final java.lang.String dataCaducitat)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.networkService.findAuthorizationsToAccessHostWithAdministratorRigthsByHostsAndRequestDate(nomHost, dataPeticio, dataCaducitat); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.am.service.NetworkService#java.util.List<com.soffid.iam.am.api.Host> findHostsByNetwork_Discovery(com.soffid.iam.am.api.Network parent, java.lang.String text)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<com.soffid.iam.am.api.Host> findHostsByNetwork_Discovery(
		final com.soffid.iam.am.api.Network parent, 
		final java.lang.String text)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("network:all:query"))
			throw new SecurityException("Unable to execute NetworkService.findHostsByNetwork_Discovery. Required roles: [network:all:query]");
		try
		{
			return this.networkService.findHostsByNetwork_Discovery(parent, text); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.am.service.NetworkService#java.util.List<com.soffid.iam.base.api.Identity> findIdentitiesByName(java.lang.String codi)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<com.soffid.iam.base.api.Identity> findIdentitiesByName(
		final java.lang.String codi)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("host:all:query")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("network:all:query"))
			throw new SecurityException("Unable to execute NetworkService.findIdentitiesByName. Required roles: [host:all:query, network_all_query]");
		try
		{
			return this.networkService.findIdentitiesByName(codi); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.am.service.NetworkService#java.util.List<com.soffid.iam.am.api.NetworkAuthorization> findNetworkAuthorizationsByGroupName(java.lang.String codiGrup)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<com.soffid.iam.am.api.NetworkAuthorization> findNetworkAuthorizationsByGroupName(
		final java.lang.String codiGrup)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("host:all:query")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("network:all:query"))
			throw new SecurityException("Unable to execute NetworkService.findNetworkAuthorizationsByGroupName. Required roles: [host:all:query, network_all_query]");
		try
		{
			return this.networkService.findNetworkAuthorizationsByGroupName(codiGrup); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.am.service.NetworkService#java.util.List<com.soffid.iam.am.api.NetworkAuthorization> findNetworkAuthorizationsByUserName(java.lang.String codiUsuari)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<com.soffid.iam.am.api.NetworkAuthorization> findNetworkAuthorizationsByUserName(
		final java.lang.String codiUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("host:all:query")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("network:all:query"))
			throw new SecurityException("Unable to execute NetworkService.findNetworkAuthorizationsByUserName. Required roles: [host:all:query, network_all_query]");
		try
		{
			return this.networkService.findNetworkAuthorizationsByUserName(codiUsuari); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.am.service.NetworkService#java.util.List<com.soffid.iam.am.api.NetworkAuthorization> findNetworkAuthorizationsByRoleName(java.lang.String nomRol)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<com.soffid.iam.am.api.NetworkAuthorization> findNetworkAuthorizationsByRoleName(
		final java.lang.String nomRol)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("host:all:query")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("network:all:query"))
			throw new SecurityException("Unable to execute NetworkService.findNetworkAuthorizationsByRoleName. Required roles: [host:all:query, network_all_query]");
		try
		{
			return this.networkService.findNetworkAuthorizationsByRoleName(nomRol); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.am.service.NetworkService#java.util.List<com.soffid.iam.am.api.Network> findNetworkByText_Discovery(java.lang.String text)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<com.soffid.iam.am.api.Network> findNetworkByText_Discovery(
		final java.lang.String text)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("network:all:query"))
			throw new SecurityException("Unable to execute NetworkService.findNetworkByText_Discovery. Required roles: [network:all:query]");
		try
		{
			return this.networkService.findNetworkByText_Discovery(text); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.am.service.NetworkService#java.util.List<com.soffid.iam.am.api.Session> findSessionsByHostName(java.lang.String codiMaquina)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<com.soffid.iam.am.api.Session> findSessionsByHostName(
		final java.lang.String codiMaquina)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.networkService.findSessionsByHostName(codiMaquina); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.am.service.NetworkService#java.util.List<com.soffid.iam.am.api.NetworkAuthorization> getACL(com.soffid.iam.am.api.Network xarxa)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<com.soffid.iam.am.api.NetworkAuthorization> getACL(
		final com.soffid.iam.am.api.Network xarxa)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.networkService.getACL(xarxa); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.am.service.NetworkService#java.util.List<com.soffid.iam.am.api.Network> getNetworks()
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<com.soffid.iam.am.api.Network> getNetworks()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("host:all:query"))
			throw new SecurityException("Unable to execute NetworkService.getNetworks. Required roles: [host:all:query]");
		try
		{
			return this.networkService.getNetworks(); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.am.service.NetworkService#void delete(com.soffid.iam.am.api.Host maquina)
	 */
	@jakarta.annotation.security.PermitAll
	public void delete(
		final com.soffid.iam.am.api.Host maquina)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			this.networkService.delete(maquina); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.am.service.NetworkService#void delete(com.soffid.iam.am.api.HostAlias aliasMaquina)
	 */
	@jakarta.annotation.security.PermitAll
	public void delete(
		final com.soffid.iam.am.api.HostAlias aliasMaquina)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			this.networkService.delete(aliasMaquina); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.am.service.NetworkService#void delete(com.soffid.iam.am.api.Network xarxa)
	 */
	@jakarta.annotation.security.PermitAll
	public void delete(
		final com.soffid.iam.am.api.Network xarxa)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("network:all:delete"))
			throw new SecurityException("Unable to execute NetworkService.delete. Required roles: [network:all:delete]");
		try
		{
			this.networkService.delete(xarxa); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.am.service.NetworkService#void delete(com.soffid.iam.am.api.NetworkAuthorization accessList)
	 */
	@jakarta.annotation.security.PermitAll
	public void delete(
		final com.soffid.iam.am.api.NetworkAuthorization accessList)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("network:all:create")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("network:all:delete")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("network:all:update"))
			throw new SecurityException("Unable to execute NetworkService.delete. Required roles: [network:all:create, network_all_delete, network_all_update]");
		try
		{
			this.networkService.delete(accessList); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.am.service.NetworkService#void delete(com.soffid.iam.am.api.OsType osType)
	 */
	@jakarta.annotation.security.PermitAll
	public void delete(
		final com.soffid.iam.am.api.OsType osType)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("operatingSystem:delete"))
			throw new SecurityException("Unable to execute NetworkService.delete. Required roles: [operatingSystem:delete]");
		try
		{
			this.networkService.delete(osType); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.am.service.NetworkService#void setAdministratorPassword(java.lang.String nomMaquina, java.lang.String adminUser, java.lang.String adminPass)
	 */
	@jakarta.annotation.security.PermitAll
	public void setAdministratorPassword(
		final java.lang.String nomMaquina, 
		final java.lang.String adminUser, 
		final java.lang.String adminPass)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			this.networkService.setAdministratorPassword(nomMaquina, adminUser, adminPass); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.am.service.NetworkService#void update(com.soffid.iam.am.api.Host maquina)
	 */
	@jakarta.annotation.security.PermitAll
	public void update(
		final com.soffid.iam.am.api.Host maquina)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			this.networkService.update(maquina); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.am.service.NetworkService#void update(com.soffid.iam.am.api.HostAlias aliasMaquina)
	 */
	@jakarta.annotation.security.PermitAll
	public void update(
		final com.soffid.iam.am.api.HostAlias aliasMaquina)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			this.networkService.update(aliasMaquina); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.am.service.NetworkService#void update(com.soffid.iam.am.api.Network xarxa)
	 */
	@jakarta.annotation.security.PermitAll
	public void update(
		final com.soffid.iam.am.api.Network xarxa)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("network:all:update"))
			throw new SecurityException("Unable to execute NetworkService.update. Required roles: [network:all:update]");
		try
		{
			this.networkService.update(xarxa); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.am.service.NetworkService#void update(com.soffid.iam.am.api.OsType osType)
	 */
	@jakarta.annotation.security.PermitAll
	public void update(
		final com.soffid.iam.am.api.OsType osType)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("operatingSystem:update"))
			throw new SecurityException("Unable to execute NetworkService.update. Required roles: [operatingSystem:update]");
		try
		{
			this.networkService.update(osType); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * Initizlizes been
	 *
	 * @see org.springframework.ejb.support.AbstractStatelessSessionBean#onEjbCreate()
	 */

	@Override

	@jakarta.annotation.PostConstruct
	public void createBean() 
	{
		super.createBean();
	}


	protected void onEjbCreate()
	{

		this.networkService = (com.soffid.iam.am.service.NetworkService)
		getBeanFactory().getBean("com.soffid.iam.am.service.NetworkService");
	}

	
	/**
	 * Override default BeanFactoryLocator implementation to
	 * provide singleton loading of the application context Bean factory.
	 *
	 * @see jakarta.ejb.SessionBean#setSessionContext(jakarta.ejb.SessionContext)
	 */
	public void setSessionContext(jakarta.ejb.SessionContext sessionContext)
	{
		super.setSessionContext(sessionContext);
		super.setBeanFactoryLocator(
		org.springframework.context.access.ContextSingletonBeanFactoryLocator.getInstance("beanRefFactory.xml"));
		super.setBeanFactoryLocatorKey("beanRefFactory");
	}

	org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog (getClass());

	/**
	 * Finds the root cause of the parent exception
	 * by traveling up the exception tree.
	 */	private static Throwable getRootCause(Throwable throwable)
	{
		if (throwable != null)
		{
			// Reflectively get any exception causes.
			try
			{
				Throwable targetException = null;
				// java.lang.reflect.InvocationTargetException
				String exceptionProperty = "targetException";
				if (org.apache.commons.beanutils.PropertyUtils.isReadable(throwable, exceptionProperty))
				{
					targetException = (Throwable)org.apache.commons.beanutils.PropertyUtils.getProperty(throwable, exceptionProperty);
				}
				else
				{
					exceptionProperty = "causedByException";
					//jakarta.ejb.EJBException
					if (org.apache.commons.beanutils.PropertyUtils.isReadable(throwable, exceptionProperty))
					{
						targetException = (Throwable)org.apache.commons.beanutils.PropertyUtils.getProperty(throwable, exceptionProperty);
					}
				}
				if (targetException != null)
				{
					throwable = targetException;
				}
			}
			catch (Exception exception)
			{
				// just print the exception and continue
				exception.printStackTrace();
			}
			if (throwable.getCause() != null)
			{
				throwable = throwable.getCause();
				throwable = getRootCause(throwable);
			}
		}
		return throwable;
	}
}
