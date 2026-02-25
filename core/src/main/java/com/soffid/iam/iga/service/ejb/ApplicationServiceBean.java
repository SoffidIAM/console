//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.service.ejb;
/**
 * @see <code>com.soffid.iam.iga.service.ApplicationService</code>,
 * @see <code>com.soffid.iam.iga.service.ApplicationService</code>,
 */
@jakarta.ejb.Stateless(name="ApplicationService", mappedName="openejb:/local/soffid.ejb.com.soffid.iam.iga.service.ApplicationService")
@jakarta.ejb.Local(com.soffid.iam.iga.service.ejb.ApplicationService.class)
@jakarta.ejb.TransactionManagement(value=jakarta.ejb.TransactionManagementType.CONTAINER)
@jakarta.ejb.TransactionAttribute(value=jakarta.ejb.TransactionAttributeType.SUPPORTS)
public class ApplicationServiceBean extends org.springframework.ejb.support.AbstractStatelessSessionBean
  implements com.soffid.iam.iga.service.ejb.ApplicationService
{
	private com.soffid.iam.iga.service.ApplicationService applicationService;

	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.DomainValue> findDomainValueByText(com.soffid.iam.iga.api.Domain domain, java.lang.String text)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.DomainValue> findDomainValueByText(
		final com.soffid.iam.iga.api.Domain domain, 
		final java.lang.String text)
		throws com.soffid.iam.exception.InternalErrorException, java.lang.Exception
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("role:query"))
			throw new SecurityException("Unable to execute ApplicationService.findDomainValueByText. Required roles: [role:query]");
		try
		{
			return this.applicationService.findDomainValueByText(domain, text); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof java.lang.Exception)
				throw (java.lang.Exception) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.DomainValue> findDomainValueByTextAsync(com.soffid.iam.iga.api.Domain domain, java.lang.String text)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.DomainValue> findDomainValueByTextAsync(
		final com.soffid.iam.iga.api.Domain domain, 
		final java.lang.String text)
		throws com.soffid.iam.exception.InternalErrorException, java.lang.Exception
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("role:query"))
			throw new SecurityException("Unable to execute ApplicationService.findDomainValueByTextAsync. Required roles: [role:query]");
		try
		{
			return this.applicationService.findDomainValueByTextAsync(domain, text); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof java.lang.Exception)
				throw (java.lang.Exception) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.iga.service.ApplicationService#com.soffid.iam.base.api.AsyncProcessTracker removeRedundantRoles(java.lang.String query)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.base.api.AsyncProcessTracker removeRedundantRoles(
		final java.lang.String query)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:role:query"))
			throw new SecurityException("Unable to execute ApplicationService.removeRedundantRoles. Required roles: [user:role:query]");
		try
		{
			return this.applicationService.removeRedundantRoles(query); 
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
	 * @see com.soffid.iam.iga.service.ApplicationService#com.soffid.iam.iga.api.InformationSystem create(com.soffid.iam.iga.api.InformationSystem aplicacio)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.InformationSystem create(
		final com.soffid.iam.iga.api.InformationSystem aplicacio)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("application:create"))
			throw new SecurityException("Unable to execute ApplicationService.create. Required roles: [application:create]");
		try
		{
			return this.applicationService.create(aplicacio); 
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
	 * @see com.soffid.iam.iga.service.ApplicationService#com.soffid.iam.iga.api.InformationSystem findApplicationByApplicationNameUnrestricted(java.lang.String codiAplicacio)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.InformationSystem findApplicationByApplicationNameUnrestricted(
		final java.lang.String codiAplicacio)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("application:query"))
			throw new SecurityException("Unable to execute ApplicationService.findApplicationByApplicationNameUnrestricted. Required roles: [application:query]");
		try
		{
			return this.applicationService.findApplicationByApplicationNameUnrestricted(codiAplicacio); 
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
	 * @see com.soffid.iam.iga.service.ApplicationService#com.soffid.iam.iga.api.InformationSystem findApplicationByApplicationName(java.lang.String codiAplicacio)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.InformationSystem findApplicationByApplicationName(
		final java.lang.String codiAplicacio)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("application:query"))
			throw new SecurityException("Unable to execute ApplicationService.findApplicationByApplicationName. Required roles: [application:query]");
		try
		{
			return this.applicationService.findApplicationByApplicationName(codiAplicacio); 
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
	 * @see com.soffid.iam.iga.service.ApplicationService#com.soffid.iam.iga.api.InformationSystem findApplicationById(java.lang.Long id)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.InformationSystem findApplicationById(
		final java.lang.Long id)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("application:query"))
			throw new SecurityException("Unable to execute ApplicationService.findApplicationById. Required roles: [application:query]");
		try
		{
			return this.applicationService.findApplicationById(id); 
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
	 * @see com.soffid.iam.iga.service.ApplicationService#com.soffid.iam.iga.api.Role approveRoleDefinition(com.soffid.iam.iga.api.Role rol)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.Role approveRoleDefinition(
		final com.soffid.iam.iga.api.Role rol)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("application:update"))
			throw new SecurityException("Unable to execute ApplicationService.approveRoleDefinition. Required roles: [application:update]");
		try
		{
			return this.applicationService.approveRoleDefinition(rol); 
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
	 * @see com.soffid.iam.iga.service.ApplicationService#com.soffid.iam.iga.api.Role create(com.soffid.iam.iga.api.Role rol)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.Role create(
		final com.soffid.iam.iga.api.Role rol)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("application:create")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("role:query"))
			throw new SecurityException("Unable to execute ApplicationService.create. Required roles: [application:create, role_create]");
		try
		{
			return this.applicationService.create(rol); 
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
	 * @see com.soffid.iam.iga.service.ApplicationService#com.soffid.iam.iga.api.Role create2(com.soffid.iam.iga.api.Role rol)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.Role create2(
		final com.soffid.iam.iga.api.Role rol)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("application:create")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("application:update"))
			throw new SecurityException("Unable to execute ApplicationService.create2. Required roles: [application:create, application_update]");
		try
		{
			return this.applicationService.create2(rol); 
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
	 * @see com.soffid.iam.iga.service.ApplicationService#com.soffid.iam.iga.api.Role denyRoleDefinition(com.soffid.iam.iga.api.Role rol)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.Role denyRoleDefinition(
		final com.soffid.iam.iga.api.Role rol)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("application:update"))
			throw new SecurityException("Unable to execute ApplicationService.denyRoleDefinition. Required roles: [application:update]");
		try
		{
			return this.applicationService.denyRoleDefinition(rol); 
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
	 * @see com.soffid.iam.iga.service.ApplicationService#com.soffid.iam.iga.api.Role findRoleById(java.lang.Long rolId)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.Role findRoleById(
		final java.lang.Long rolId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("application:query")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("authorization:query"))
			throw new SecurityException("Unable to execute ApplicationService.findRoleById. Required roles: [application:query, authorization_query]");
		try
		{
			return this.applicationService.findRoleById(rolId); 
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
	 * @see com.soffid.iam.iga.service.ApplicationService#com.soffid.iam.iga.api.Role findRoleByRoleNameAndApplicationNameAndDispatcherName(java.lang.String nomRol, java.lang.String codiAplicacio, java.lang.String codiDispatcher)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.Role findRoleByRoleNameAndApplicationNameAndDispatcherName(
		final java.lang.String nomRol, 
		final java.lang.String codiAplicacio, 
		final java.lang.String codiDispatcher)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("application:query"))
			throw new SecurityException("Unable to execute ApplicationService.findRoleByRoleNameAndApplicationNameAndDispatcherName. Required roles: [application:query]");
		try
		{
			return this.applicationService.findRoleByRoleNameAndApplicationNameAndDispatcherName(nomRol, codiAplicacio, codiDispatcher); 
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
	 * @see com.soffid.iam.iga.service.ApplicationService#com.soffid.iam.iga.api.Role findRoleByNameAndSystem(java.lang.String name, java.lang.String system)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.Role findRoleByNameAndSystem(
		final java.lang.String name, 
		final java.lang.String system)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("application:query"))
			throw new SecurityException("Unable to execute ApplicationService.findRoleByNameAndSystem. Required roles: [application:query]");
		try
		{
			return this.applicationService.findRoleByNameAndSystem(name, system); 
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
	 * @see com.soffid.iam.iga.service.ApplicationService#com.soffid.iam.iga.api.Role findRoleByShortName(java.lang.String name)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.Role findRoleByShortName(
		final java.lang.String name)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("application:query"))
			throw new SecurityException("Unable to execute ApplicationService.findRoleByShortName. Required roles: [application:query]");
		try
		{
			return this.applicationService.findRoleByShortName(name); 
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
	 * @see com.soffid.iam.iga.service.ApplicationService#com.soffid.iam.iga.api.Role update(com.soffid.iam.iga.api.Role rol)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.Role update(
		final com.soffid.iam.iga.api.Role rol)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("role:update"))
			throw new SecurityException("Unable to execute ApplicationService.update. Required roles: [role:update]");
		try
		{
			return this.applicationService.update(rol); 
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
	 * @see com.soffid.iam.iga.service.ApplicationService#com.soffid.iam.iga.api.Role update2(com.soffid.iam.iga.api.Role rol)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.Role update2(
		final com.soffid.iam.iga.api.Role rol)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("application:update"))
			throw new SecurityException("Unable to execute ApplicationService.update2. Required roles: [application:update]");
		try
		{
			return this.applicationService.update2(rol); 
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
	 * @see com.soffid.iam.iga.service.ApplicationService#com.soffid.iam.iga.api.RoleAccount create(com.soffid.iam.iga.api.RoleAccount rolsUsuaris)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.RoleAccount create(
		final com.soffid.iam.iga.api.RoleAccount rolsUsuaris)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:role:create"))
			throw new SecurityException("Unable to execute ApplicationService.create. Required roles: [user:role:create]");
		try
		{
			return this.applicationService.create(rolsUsuaris); 
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
	 * @see com.soffid.iam.iga.service.ApplicationService#com.soffid.iam.iga.api.RoleAccount findRoleAccountById(long id)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.RoleAccount findRoleAccountById(
		final long id)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:role:query"))
			throw new SecurityException("Unable to execute ApplicationService.findRoleAccountById. Required roles: [user:role:query]");
		try
		{
			return this.applicationService.findRoleAccountById(id); 
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
	 * @see com.soffid.iam.iga.service.ApplicationService#com.soffid.iam.iga.api.RoleAccount updateAttributes(com.soffid.iam.iga.api.RoleAccount rolsUsuaris)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.RoleAccount updateAttributes(
		final com.soffid.iam.iga.api.RoleAccount rolsUsuaris)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:role:create"))
			throw new SecurityException("Unable to execute ApplicationService.updateAttributes. Required roles: [user:role:create]");
		try
		{
			return this.applicationService.updateAttributes(rolsUsuaris); 
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
	 * @see com.soffid.iam.iga.service.ApplicationService#com.soffid.iam.iga.api.RoleGrant create(com.soffid.iam.iga.api.RoleGrant grant)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.RoleGrant create(
		final com.soffid.iam.iga.api.RoleGrant grant)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("application:update"))
			throw new SecurityException("Unable to execute ApplicationService.create. Required roles: [application:update]");
		try
		{
			return this.applicationService.create(grant); 
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
	 * @see com.soffid.iam.iga.service.ApplicationService#com.soffid.iam.iga.api.RoleGrant update(com.soffid.iam.iga.api.RoleGrant grant)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.RoleGrant update(
		final com.soffid.iam.iga.api.RoleGrant grant)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("application:update"))
			throw new SecurityException("Unable to execute ApplicationService.update. Required roles: [application:update]");
		try
		{
			return this.applicationService.update(grant); 
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
	 * @see com.soffid.iam.iga.service.ApplicationService#com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.InformationSystem> findApplications(com.soffid.zkdb.api.Query query)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.InformationSystem> findApplications(
		final com.soffid.zkdb.api.Query query)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("application:query"))
			throw new SecurityException("Unable to execute ApplicationService.findApplications. Required roles: [application:query]");
		try
		{
			return this.applicationService.findApplications(query); 
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
	 * @see com.soffid.iam.iga.service.ApplicationService#com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.DomainValue> findDomainValues(com.soffid.zkdb.api.Query query)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.DomainValue> findDomainValues(
		final com.soffid.zkdb.api.Query query)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("application:query"))
			throw new SecurityException("Unable to execute ApplicationService.findDomainValues. Required roles: [application:query]");
		try
		{
			return this.applicationService.findDomainValues(query); 
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
	 * @see com.soffid.iam.iga.service.ApplicationService#com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.RoleAccount> findRedundantRoles(java.lang.String query)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.RoleAccount> findRedundantRoles(
		final java.lang.String query)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:role:query"))
			throw new SecurityException("Unable to execute ApplicationService.findRedundantRoles. Required roles: [user:role:query]");
		try
		{
			return this.applicationService.findRedundantRoles(query); 
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
	 * @see com.soffid.iam.iga.service.ApplicationService#com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.RoleAccount> findRoleAccounts(com.soffid.zkdb.api.Query query)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.RoleAccount> findRoleAccounts(
		final com.soffid.zkdb.api.Query query)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:role:query"))
			throw new SecurityException("Unable to execute ApplicationService.findRoleAccounts. Required roles: [user:role:query]");
		try
		{
			return this.applicationService.findRoleAccounts(query); 
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
	 * @see com.soffid.iam.iga.service.ApplicationService#com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.Role> findRoles(com.soffid.zkdb.api.Query query)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.Role> findRoles(
		final com.soffid.zkdb.api.Query query)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("application:query"))
			throw new SecurityException("Unable to execute ApplicationService.findRoles. Required roles: [application:query]");
		try
		{
			return this.applicationService.findRoles(query); 
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
	 * @see com.soffid.iam.iga.service.ApplicationService#java.lang.String generateChangesReport(com.soffid.iam.iga.api.Role rol)
	 */
	@jakarta.annotation.security.PermitAll
	public java.lang.String generateChangesReport(
		final com.soffid.iam.iga.api.Role rol)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("application:update"))
			throw new SecurityException("Unable to execute ApplicationService.generateChangesReport. Required roles: [application:update]");
		try
		{
			return this.applicationService.generateChangesReport(rol); 
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
	 * @see com.soffid.iam.iga.service.ApplicationService#java.lang.String generateChangesReport(com.soffid.iam.iga.api.Role rol, java.util.List<com.soffid.iam.iga.api.RoleAccount> grantsToAdd, java.util.List<com.soffid.iam.iga.api.RoleAccount> grantsToRemove)
	 */
	@jakarta.annotation.security.PermitAll
	public java.lang.String generateChangesReport(
		final com.soffid.iam.iga.api.Role rol, 
		final java.util.List<com.soffid.iam.iga.api.RoleAccount> grantsToAdd, 
		final java.util.List<com.soffid.iam.iga.api.RoleAccount> grantsToRemove)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("application:update"))
			throw new SecurityException("Unable to execute ApplicationService.generateChangesReport. Required roles: [application:update]");
		try
		{
			return this.applicationService.generateChangesReport(rol, grantsToAdd, grantsToRemove); 
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
	 * @see com.soffid.iam.iga.service.ApplicationService#java.util.Collection<com.soffid.iam.am.api.NetworkAuthorization> findNetworkACLRolesByRoleNameAndApplicationNameAndDispatcherName(java.lang.String nomRole, java.lang.String codiAplicacioRol, java.lang.String codiDispatcher)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.am.api.NetworkAuthorization> findNetworkACLRolesByRoleNameAndApplicationNameAndDispatcherName(
		final java.lang.String nomRole, 
		final java.lang.String codiAplicacioRol, 
		final java.lang.String codiDispatcher)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("application:query"))
			throw new SecurityException("Unable to execute ApplicationService.findNetworkACLRolesByRoleNameAndApplicationNameAndDispatcherName. Required roles: [application:query]");
		try
		{
			return this.applicationService.findNetworkACLRolesByRoleNameAndApplicationNameAndDispatcherName(nomRole, codiAplicacioRol, codiDispatcher); 
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
	 * @see com.soffid.iam.iga.service.ApplicationService#java.util.Collection<com.soffid.iam.iga.api.InformationSystem> findApplicationChildren(java.lang.String applicationName)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.iga.api.InformationSystem> findApplicationChildren(
		final java.lang.String applicationName)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("application:query"))
			throw new SecurityException("Unable to execute ApplicationService.findApplicationChildren. Required roles: [application:query]");
		try
		{
			return this.applicationService.findApplicationChildren(applicationName); 
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
	 * @see com.soffid.iam.iga.service.ApplicationService#java.util.Collection<com.soffid.iam.iga.api.Role> findApplicationManagementRoles()
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.iga.api.Role> findApplicationManagementRoles()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("application:query"))
			throw new SecurityException("Unable to execute ApplicationService.findApplicationManagementRoles. Required roles: [application:query]");
		try
		{
			return this.applicationService.findApplicationManagementRoles(); 
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
	 * @see com.soffid.iam.iga.service.ApplicationService#java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findApplicationManagers(java.lang.String informationSystem, java.lang.String roleName)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findApplicationManagers(
		final java.lang.String informationSystem, 
		final java.lang.String roleName)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("application:query"))
			throw new SecurityException("Unable to execute ApplicationService.findApplicationManagers. Required roles: [application:query]");
		try
		{
			return this.applicationService.findApplicationManagers(informationSystem, roleName); 
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
	 * @see com.soffid.iam.iga.service.ApplicationService#java.util.Collection<com.soffid.iam.base.api.AuthorizationRole> findRoleAuthorizationsByRoleNameAndApplicationNameAndDispatcherName(java.lang.String nomRole, java.lang.String codiAplicacioRol, java.lang.String codiDispatcher)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.base.api.AuthorizationRole> findRoleAuthorizationsByRoleNameAndApplicationNameAndDispatcherName(
		final java.lang.String nomRole, 
		final java.lang.String codiAplicacioRol, 
		final java.lang.String codiDispatcher)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("application:query"))
			throw new SecurityException("Unable to execute ApplicationService.findRoleAuthorizationsByRoleNameAndApplicationNameAndDispatcherName. Required roles: [application:query]");
		try
		{
			return this.applicationService.findRoleAuthorizationsByRoleNameAndApplicationNameAndDispatcherName(nomRole, codiAplicacioRol, codiDispatcher); 
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
	 * @see com.soffid.iam.iga.service.ApplicationService#java.util.Collection<com.soffid.iam.iga.api.RoleGrant> findEffectiveRoleGrantByAccount(long accountId)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.iga.api.RoleGrant> findEffectiveRoleGrantByAccount(
		final long accountId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("application:query"))
			throw new SecurityException("Unable to execute ApplicationService.findEffectiveRoleGrantByAccount. Required roles: [application:query]");
		try
		{
			return this.applicationService.findEffectiveRoleGrantByAccount(accountId); 
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
	 * @see com.soffid.iam.iga.service.ApplicationService#java.util.Collection<com.soffid.iam.iga.api.RoleGrant> findEffectiveRoleGrantByUser(long userId)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.iga.api.RoleGrant> findEffectiveRoleGrantByUser(
		final long userId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("application:query"))
			throw new SecurityException("Unable to execute ApplicationService.findEffectiveRoleGrantByUser. Required roles: [application:query]");
		try
		{
			return this.applicationService.findEffectiveRoleGrantByUser(userId); 
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
	 * @see com.soffid.iam.iga.service.ApplicationService#java.util.Collection<com.soffid.iam.iga.api.RoleGrant> findEffectiveRoleGrantByUserAndHolderGroup(long userId, long groupId)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.iga.api.RoleGrant> findEffectiveRoleGrantByUserAndHolderGroup(
		final long userId, 
		final long groupId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("application:query"))
			throw new SecurityException("Unable to execute ApplicationService.findEffectiveRoleGrantByUserAndHolderGroup. Required roles: [application:query]");
		try
		{
			return this.applicationService.findEffectiveRoleGrantByUserAndHolderGroup(userId, groupId); 
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
	 * @see com.soffid.iam.iga.service.ApplicationService#java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findEffectiveUserRolesByInformationSystem(java.lang.String informationSystem)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findEffectiveUserRolesByInformationSystem(
		final java.lang.String informationSystem)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("application:query")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("user:query"))
			throw new SecurityException("Unable to execute ApplicationService.findEffectiveUserRolesByInformationSystem. Required roles: [application:query, user_query]");
		try
		{
			return this.applicationService.findEffectiveUserRolesByInformationSystem(informationSystem); 
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
	 * @see com.soffid.iam.iga.service.ApplicationService#java.util.Collection<com.soffid.iam.iga.api.Role> findGroupManagementRoles()
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.iga.api.Role> findGroupManagementRoles()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("application:query"))
			throw new SecurityException("Unable to execute ApplicationService.findGroupManagementRoles. Required roles: [application:query]");
		try
		{
			return this.applicationService.findGroupManagementRoles(); 
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
	 * @see com.soffid.iam.iga.service.ApplicationService#java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findGroupManagers(java.lang.String group, java.lang.String roleName)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findGroupManagers(
		final java.lang.String group, 
		final java.lang.String roleName)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("application:query"))
			throw new SecurityException("Unable to execute ApplicationService.findGroupManagers. Required roles: [application:query]");
		try
		{
			return this.applicationService.findGroupManagers(group, roleName); 
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
	 * @see com.soffid.iam.iga.service.ApplicationService#java.util.Collection<com.soffid.iam.iga.api.Group> findRoleHoldersGroupsByRole(com.soffid.iam.iga.api.Role rol)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.iga.api.Group> findRoleHoldersGroupsByRole(
		final com.soffid.iam.iga.api.Role rol)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("application:query"))
			throw new SecurityException("Unable to execute ApplicationService.findRoleHoldersGroupsByRole. Required roles: [application:query]");
		try
		{
			return this.applicationService.findRoleHoldersGroupsByRole(rol); 
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
	 * @see com.soffid.iam.iga.service.ApplicationService#java.util.Collection<com.soffid.iam.am.api.AccessTreeAuthorization> findApplicationAccessTreeRolesByRoleNameAndRoleApplicationNameAndDispatcherName(java.lang.String nomRole, java.lang.String codiAplicacioRol, java.lang.String codiDispatcher)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.am.api.AccessTreeAuthorization> findApplicationAccessTreeRolesByRoleNameAndRoleApplicationNameAndDispatcherName(
		final java.lang.String nomRole, 
		final java.lang.String codiAplicacioRol, 
		final java.lang.String codiDispatcher)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("application:query"))
			throw new SecurityException("Unable to execute ApplicationService.findApplicationAccessTreeRolesByRoleNameAndRoleApplicationNameAndDispatcherName. Required roles: [application:query]");
		try
		{
			return this.applicationService.findApplicationAccessTreeRolesByRoleNameAndRoleApplicationNameAndDispatcherName(nomRole, codiAplicacioRol, codiDispatcher); 
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
	 * @see com.soffid.iam.iga.service.ApplicationService#java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findRoleAccountByAccount(long accountId)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findRoleAccountByAccount(
		final long accountId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("application:query"))
			throw new SecurityException("Unable to execute ApplicationService.findRoleAccountByAccount. Required roles: [application:query]");
		try
		{
			return this.applicationService.findRoleAccountByAccount(accountId); 
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
	 * @see com.soffid.iam.iga.service.ApplicationService#java.util.Collection<com.soffid.iam.iga.api.RoleGrant> findRoleGrantByAccount(java.lang.Long accountId)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.iga.api.RoleGrant> findRoleGrantByAccount(
		final java.lang.Long accountId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("application:query"))
			throw new SecurityException("Unable to execute ApplicationService.findRoleGrantByAccount. Required roles: [application:query]");
		try
		{
			return this.applicationService.findRoleGrantByAccount(accountId); 
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
	 * @see com.soffid.iam.iga.service.ApplicationService#java.util.Collection<com.soffid.iam.iga.api.RoleGrant> findRoleGrantByRole(java.lang.Long rolId, java.lang.Long numRegistres)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.iga.api.RoleGrant> findRoleGrantByRole(
		final java.lang.Long rolId, 
		final java.lang.Long numRegistres)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("application:query"))
			throw new SecurityException("Unable to execute ApplicationService.findRoleGrantByRole. Required roles: [application:query]");
		try
		{
			return this.applicationService.findRoleGrantByRole(rolId, numRegistres); 
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
	 * @see com.soffid.iam.iga.service.ApplicationService#java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findRoleAccountByAccountNoRule(long accountId)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findRoleAccountByAccountNoRule(
		final long accountId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("application:query"))
			throw new SecurityException("Unable to execute ApplicationService.findRoleAccountByAccountNoRule. Required roles: [application:query]");
		try
		{
			return this.applicationService.findRoleAccountByAccountNoRule(accountId); 
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
	 * @see com.soffid.iam.iga.service.ApplicationService#java.util.Collection<com.soffid.iam.iga.api.RoleGrantHierarchy> findRoleGrantHierarchyByAccount(long accountId)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.iga.api.RoleGrantHierarchy> findRoleGrantHierarchyByAccount(
		final long accountId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("application:query"))
			throw new SecurityException("Unable to execute ApplicationService.findRoleGrantHierarchyByAccount. Required roles: [application:query]");
		try
		{
			return this.applicationService.findRoleGrantHierarchyByAccount(accountId); 
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
	 * @see com.soffid.iam.iga.service.ApplicationService#java.util.Collection<com.soffid.iam.iga.api.RoleGrantHierarchy> findRoleGrantHierarchyByUser(long userId)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.iga.api.RoleGrantHierarchy> findRoleGrantHierarchyByUser(
		final long userId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("application:query"))
			throw new SecurityException("Unable to execute ApplicationService.findRoleGrantHierarchyByUser. Required roles: [application:query]");
		try
		{
			return this.applicationService.findRoleGrantHierarchyByUser(userId); 
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
	 * @see com.soffid.iam.iga.service.ApplicationService#java.util.Collection<com.soffid.iam.iga.api.RoleGrant> findRoleGrantsByGroup(com.soffid.iam.iga.api.Group grup)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.iga.api.RoleGrant> findRoleGrantsByGroup(
		final com.soffid.iam.iga.api.Group grup)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("application:query"))
			throw new SecurityException("Unable to execute ApplicationService.findRoleGrantsByGroup. Required roles: [application:query]");
		try
		{
			return this.applicationService.findRoleGrantsByGroup(grup); 
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
	 * @see com.soffid.iam.iga.service.ApplicationService#java.util.Collection<com.soffid.iam.iga.api.Role> findRolesByApplicationName(java.lang.String codiAplicacio)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.iga.api.Role> findRolesByApplicationName(
		final java.lang.String codiAplicacio)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.applicationService.findRolesByApplicationName(codiAplicacio); 
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
	 * @see com.soffid.iam.iga.service.ApplicationService#java.util.Collection<com.soffid.iam.iga.api.Role> findRolesByApplicationNameUnrestricted(java.lang.String codiAplicacio)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.iga.api.Role> findRolesByApplicationNameUnrestricted(
		final java.lang.String codiAplicacio)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("application:query"))
			throw new SecurityException("Unable to execute ApplicationService.findRolesByApplicationNameUnrestricted. Required roles: [application:query]");
		try
		{
			return this.applicationService.findRolesByApplicationNameUnrestricted(codiAplicacio); 
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
	 * @see com.soffid.iam.iga.service.ApplicationService#java.util.Collection<com.soffid.iam.iga.api.Role> findRolesByUserName(java.lang.String codiUsuari)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.iga.api.Role> findRolesByUserName(
		final java.lang.String codiUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("application:query"))
			throw new SecurityException("Unable to execute ApplicationService.findRolesByUserName. Required roles: [application:query]");
		try
		{
			return this.applicationService.findRolesByUserName(codiUsuari); 
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
	 * @see com.soffid.iam.iga.service.ApplicationService#java.util.Collection<com.soffid.iam.iga.api.Role> findRolesByDomainNameAndApplicationName(java.lang.String nomDomini, java.lang.String codiAplicacio)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.iga.api.Role> findRolesByDomainNameAndApplicationName(
		final java.lang.String nomDomini, 
		final java.lang.String codiAplicacio)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("application:query"))
			throw new SecurityException("Unable to execute ApplicationService.findRolesByDomainNameAndApplicationName. Required roles: [application:query]");
		try
		{
			return this.applicationService.findRolesByDomainNameAndApplicationName(nomDomini, codiAplicacio); 
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
	 * @see com.soffid.iam.iga.service.ApplicationService#java.util.Collection<com.soffid.iam.iga.api.Role> findGrantedRolesToGroupByGroup(com.soffid.iam.iga.api.Group grup)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.iga.api.Role> findGrantedRolesToGroupByGroup(
		final com.soffid.iam.iga.api.Group grup)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("application:query"))
			throw new SecurityException("Unable to execute ApplicationService.findGrantedRolesToGroupByGroup. Required roles: [application:query]");
		try
		{
			return this.applicationService.findGrantedRolesToGroupByGroup(grup); 
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
	 * @see com.soffid.iam.iga.service.ApplicationService#java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findUserRolesByRoleNameAndRoleApplicationNameAndDispatcherName(java.lang.String nomRole, java.lang.String codiAplicacioRol, java.lang.String codiDispatcher)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findUserRolesByRoleNameAndRoleApplicationNameAndDispatcherName(
		final java.lang.String nomRole, 
		final java.lang.String codiAplicacioRol, 
		final java.lang.String codiDispatcher)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("application:query"))
			throw new SecurityException("Unable to execute ApplicationService.findUserRolesByRoleNameAndRoleApplicationNameAndDispatcherName. Required roles: [application:query]");
		try
		{
			return this.applicationService.findUserRolesByRoleNameAndRoleApplicationNameAndDispatcherName(nomRole, codiAplicacioRol, codiDispatcher); 
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
	 * @see com.soffid.iam.iga.service.ApplicationService#java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findUserRolesByUserName(java.lang.String codiUsuari)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findUserRolesByUserName(
		final java.lang.String codiUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("application:query")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("user:query"))
			throw new SecurityException("Unable to execute ApplicationService.findUserRolesByUserName. Required roles: [application:query, user_query]");
		try
		{
			return this.applicationService.findUserRolesByUserName(codiUsuari); 
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
	 * @see com.soffid.iam.iga.service.ApplicationService#java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findUserRolesHistoryByUserName(java.lang.String codiUsuari)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findUserRolesHistoryByUserName(
		final java.lang.String codiUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("application:query")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("user:query"))
			throw new SecurityException("Unable to execute ApplicationService.findUserRolesHistoryByUserName. Required roles: [application:query, user_query]");
		try
		{
			return this.applicationService.findUserRolesHistoryByUserName(codiUsuari); 
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
	 * @see com.soffid.iam.iga.service.ApplicationService#java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findUserRolesByUserNameNoSoD(java.lang.String codiUsuari)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findUserRolesByUserNameNoSoD(
		final java.lang.String codiUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("application:query")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("user:query"))
			throw new SecurityException("Unable to execute ApplicationService.findUserRolesByUserNameNoSoD. Required roles: [application:query, user_query]");
		try
		{
			return this.applicationService.findUserRolesByUserNameNoSoD(codiUsuari); 
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
	 * @see com.soffid.iam.iga.service.ApplicationService#java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findUserRolesByInformationSystem(java.lang.String informationSystem)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findUserRolesByInformationSystem(
		final java.lang.String informationSystem)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("application:query")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("user:query"))
			throw new SecurityException("Unable to execute ApplicationService.findUserRolesByInformationSystem. Required roles: [application:query, user_query]");
		try
		{
			return this.applicationService.findUserRolesByInformationSystem(informationSystem); 
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
	 * @see com.soffid.iam.iga.service.ApplicationService#java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findUserRolesByUserNameNoRules(java.lang.String codiUsuari)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.iga.api.RoleAccount> findUserRolesByUserNameNoRules(
		final java.lang.String codiUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("application:query")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("user:query"))
			throw new SecurityException("Unable to execute ApplicationService.findUserRolesByUserNameNoRules. Required roles: [application:query, user_query]");
		try
		{
			return this.applicationService.findUserRolesByUserNameNoRules(codiUsuari); 
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
	 * @see com.soffid.iam.iga.service.ApplicationService#java.util.Collection<com.soffid.iam.base.api.User> findUsersByRoleNameAndRoleApplicationNameAndDispatcherName(java.lang.String nomRole, java.lang.String codiAplicacioRol, java.lang.String codiDispatcher)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.base.api.User> findUsersByRoleNameAndRoleApplicationNameAndDispatcherName(
		final java.lang.String nomRole, 
		final java.lang.String codiAplicacioRol, 
		final java.lang.String codiDispatcher)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("application:query"))
			throw new SecurityException("Unable to execute ApplicationService.findUsersByRoleNameAndRoleApplicationNameAndDispatcherName. Required roles: [application:query]");
		try
		{
			return this.applicationService.findUsersByRoleNameAndRoleApplicationNameAndDispatcherName(nomRole, codiAplicacioRol, codiDispatcher); 
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
	 * @see com.soffid.iam.iga.service.ApplicationService#java.util.Collection<com.soffid.iam.iga.api.Role> getRoles()
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.iga.api.Role> getRoles()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("application:query"))
			throw new SecurityException("Unable to execute ApplicationService.getRoles. Required roles: [application:query]");
		try
		{
			return this.applicationService.getRoles(); 
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
	 * @see com.soffid.iam.iga.service.ApplicationService#void delete(com.soffid.iam.iga.api.InformationSystem aplicacio)
	 */
	@jakarta.annotation.security.PermitAll
	public void delete(
		final com.soffid.iam.iga.api.InformationSystem aplicacio)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("application:delete"))
			throw new SecurityException("Unable to execute ApplicationService.delete. Required roles: [application:delete]");
		try
		{
			this.applicationService.delete(aplicacio); 
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
	 * @see com.soffid.iam.iga.service.ApplicationService#void delete(com.soffid.iam.iga.api.Role rol)
	 */
	@jakarta.annotation.security.PermitAll
	public void delete(
		final com.soffid.iam.iga.api.Role rol)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("role:delete"))
			throw new SecurityException("Unable to execute ApplicationService.delete. Required roles: [role:delete]");
		try
		{
			this.applicationService.delete(rol); 
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
	 * @see com.soffid.iam.iga.service.ApplicationService#void delete(com.soffid.iam.iga.api.RoleAccount rolsUsuaris)
	 */
	@jakarta.annotation.security.PermitAll
	public void delete(
		final com.soffid.iam.iga.api.RoleAccount rolsUsuaris)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("application:delete")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("user:role:delete"))
			throw new SecurityException("Unable to execute ApplicationService.delete. Required roles: [application:delete, user_role_delete]");
		try
		{
			this.applicationService.delete(rolsUsuaris); 
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
	 * @see com.soffid.iam.iga.service.ApplicationService#void delete(com.soffid.iam.iga.api.RoleGrant grant)
	 */
	@jakarta.annotation.security.PermitAll
	public void delete(
		final com.soffid.iam.iga.api.RoleGrant grant)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("application:update"))
			throw new SecurityException("Unable to execute ApplicationService.delete. Required roles: [application:update]");
		try
		{
			this.applicationService.delete(grant); 
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
	 * @see com.soffid.iam.iga.service.ApplicationService#void synchronizeRole(com.soffid.iam.iga.api.Role rol)
	 */
	@jakarta.annotation.security.PermitAll
	public void synchronizeRole(
		final com.soffid.iam.iga.api.Role rol)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("application:update"))
			throw new SecurityException("Unable to execute ApplicationService.synchronizeRole. Required roles: [application:update]");
		try
		{
			this.applicationService.synchronizeRole(rol); 
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
	 * @see com.soffid.iam.iga.service.ApplicationService#void update(com.soffid.iam.iga.api.InformationSystem aplicacio)
	 */
	@jakarta.annotation.security.PermitAll
	public void update(
		final com.soffid.iam.iga.api.InformationSystem aplicacio)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("application:update"))
			throw new SecurityException("Unable to execute ApplicationService.update. Required roles: [application:update]");
		try
		{
			this.applicationService.update(aplicacio); 
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

		this.applicationService = (com.soffid.iam.iga.service.ApplicationService)
		getBeanFactory().getBean("com.soffid.iam.iga.service.ApplicationService");
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
