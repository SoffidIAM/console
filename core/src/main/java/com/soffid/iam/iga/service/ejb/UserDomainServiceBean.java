//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.service.ejb;
/**
 * @see <code>com.soffid.iam.iga.service.UserDomainService</code>,
 * @see <code>com.soffid.iam.iga.service.UserDomainService</code>,
 */
@jakarta.ejb.Stateless(name="UserDomainService", mappedName="openejb:/local/soffid.ejb.com.soffid.iam.iga.service.UserDomainService")
@jakarta.ejb.Local(com.soffid.iam.iga.service.ejb.UserDomainService.class)
@jakarta.ejb.TransactionManagement(value=jakarta.ejb.TransactionManagementType.CONTAINER)
@jakarta.ejb.TransactionAttribute(value=jakarta.ejb.TransactionAttributeType.SUPPORTS)
public class UserDomainServiceBean extends org.springframework.ejb.support.AbstractStatelessSessionBean
  implements com.soffid.iam.iga.service.ejb.UserDomainService
{
	private com.soffid.iam.iga.service.UserDomainService userDomainService;

	/**
	 * @see com.soffid.iam.iga.service.UserDomainService#com.soffid.iam.am.api.ForbiddenWord create(com.soffid.iam.am.api.ForbiddenWord paraulaProhibida)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.am.api.ForbiddenWord create(
		final com.soffid.iam.am.api.ForbiddenWord paraulaProhibida)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("passwordDomain:create"))
			throw new SecurityException("Unable to execute UserDomainService.create. Required roles: [passwordDomain:create]");
		try
		{
			return this.userDomainService.create(paraulaProhibida); 
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
	 * @see com.soffid.iam.iga.service.UserDomainService#com.soffid.iam.am.api.ForbiddenWord update(com.soffid.iam.am.api.ForbiddenWord paraulaProhibida)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.am.api.ForbiddenWord update(
		final com.soffid.iam.am.api.ForbiddenWord paraulaProhibida)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("passwordDomain:update"))
			throw new SecurityException("Unable to execute UserDomainService.update. Required roles: [passwordDomain:update]");
		try
		{
			return this.userDomainService.update(paraulaProhibida); 
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
	 * @see com.soffid.iam.iga.service.UserDomainService#com.soffid.iam.am.api.PasswordDomain create(com.soffid.iam.am.api.PasswordDomain dominiContrasenya)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.am.api.PasswordDomain create(
		final com.soffid.iam.am.api.PasswordDomain dominiContrasenya)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("passwordDomain:create"))
			throw new SecurityException("Unable to execute UserDomainService.create. Required roles: [passwordDomain:create]");
		try
		{
			return this.userDomainService.create(dominiContrasenya); 
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
	 * @see com.soffid.iam.iga.service.UserDomainService#com.soffid.iam.am.api.PasswordDomain findPasswordDomainByName(java.lang.String codi)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.am.api.PasswordDomain findPasswordDomainByName(
		final java.lang.String codi)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("usersDomain:query"))
			throw new SecurityException("Unable to execute UserDomainService.findPasswordDomainByName. Required roles: [usersDomain:query]");
		try
		{
			return this.userDomainService.findPasswordDomainByName(codi); 
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
	 * @see com.soffid.iam.iga.service.UserDomainService#com.soffid.iam.am.api.PasswordDomain update(com.soffid.iam.am.api.PasswordDomain dominiContrasenya)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.am.api.PasswordDomain update(
		final com.soffid.iam.am.api.PasswordDomain dominiContrasenya)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("passwordDomain:update"))
			throw new SecurityException("Unable to execute UserDomainService.update. Required roles: [passwordDomain:update]");
		try
		{
			return this.userDomainService.update(dominiContrasenya); 
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
	 * @see com.soffid.iam.iga.service.UserDomainService#com.soffid.iam.am.api.PasswordPolicy create(com.soffid.iam.am.api.PasswordPolicy politicaContrasenyaDomini)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.am.api.PasswordPolicy create(
		final com.soffid.iam.am.api.PasswordPolicy politicaContrasenyaDomini)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("passwordDomain:create"))
			throw new SecurityException("Unable to execute UserDomainService.create. Required roles: [passwordDomain:create]");
		try
		{
			return this.userDomainService.create(politicaContrasenyaDomini); 
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
	 * @see com.soffid.iam.iga.service.UserDomainService#com.soffid.iam.am.api.PasswordPolicy findPolicyByTypeAndPasswordDomain(java.lang.String tipus, java.lang.String domini)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.am.api.PasswordPolicy findPolicyByTypeAndPasswordDomain(
		final java.lang.String tipus, 
		final java.lang.String domini)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("usersDomain:query"))
			throw new SecurityException("Unable to execute UserDomainService.findPolicyByTypeAndPasswordDomain. Required roles: [usersDomain:query]");
		try
		{
			return this.userDomainService.findPolicyByTypeAndPasswordDomain(tipus, domini); 
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
	 * @see com.soffid.iam.iga.service.UserDomainService#com.soffid.iam.am.api.PasswordPolicy update(com.soffid.iam.am.api.PasswordPolicy politicaContrasenyaDomini)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.am.api.PasswordPolicy update(
		final com.soffid.iam.am.api.PasswordPolicy politicaContrasenyaDomini)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("passwordDomain:update"))
			throw new SecurityException("Unable to execute UserDomainService.update. Required roles: [passwordDomain:update]");
		try
		{
			return this.userDomainService.update(politicaContrasenyaDomini); 
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
	 * @see com.soffid.iam.iga.service.UserDomainService#com.soffid.iam.am.api.PasswordPolicyForbbidenWord create(com.soffid.iam.am.api.PasswordPolicyForbbidenWord paraulaProhibidaContrasenya)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.am.api.PasswordPolicyForbbidenWord create(
		final com.soffid.iam.am.api.PasswordPolicyForbbidenWord paraulaProhibidaContrasenya)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("passwordDomain:create"))
			throw new SecurityException("Unable to execute UserDomainService.create. Required roles: [passwordDomain:create]");
		try
		{
			return this.userDomainService.create(paraulaProhibidaContrasenya); 
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
	 * @see com.soffid.iam.iga.service.UserDomainService#com.soffid.iam.am.api.PasswordPolicyForbbidenWord update(com.soffid.iam.am.api.PasswordPolicyForbbidenWord paraulaProhibidaContrasenya)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.am.api.PasswordPolicyForbbidenWord update(
		final com.soffid.iam.am.api.PasswordPolicyForbbidenWord paraulaProhibidaContrasenya)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("passwordDomain:update"))
			throw new SecurityException("Unable to execute UserDomainService.update. Required roles: [passwordDomain:update]");
		try
		{
			return this.userDomainService.update(paraulaProhibidaContrasenya); 
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
	 * @see com.soffid.iam.iga.service.UserDomainService#com.soffid.iam.iga.api.UserDomain create(com.soffid.iam.iga.api.UserDomain dominiUsuari)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.UserDomain create(
		final com.soffid.iam.iga.api.UserDomain dominiUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("usersDomain:create"))
			throw new SecurityException("Unable to execute UserDomainService.create. Required roles: [usersDomain:create]");
		try
		{
			return this.userDomainService.create(dominiUsuari); 
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
	 * @see com.soffid.iam.iga.service.UserDomainService#com.soffid.iam.iga.api.UserDomain findUserDomainByName(java.lang.String codiDominiUsuari)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.UserDomain findUserDomainByName(
		final java.lang.String codiDominiUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("usersDomain:query"))
			throw new SecurityException("Unable to execute UserDomainService.findUserDomainByName. Required roles: [usersDomain:query]");
		try
		{
			return this.userDomainService.findUserDomainByName(codiDominiUsuari); 
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
	 * @see com.soffid.iam.iga.service.UserDomainService#com.soffid.iam.iga.api.UserDomain update(com.soffid.iam.iga.api.UserDomain dominiUsuari)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.UserDomain update(
		final com.soffid.iam.iga.api.UserDomain dominiUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("usersDomain:update"))
			throw new SecurityException("Unable to execute UserDomainService.update. Required roles: [usersDomain:update]");
		try
		{
			return this.userDomainService.update(dominiUsuari); 
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
	 * @see com.soffid.iam.iga.service.UserDomainService#com.soffid.iam.iga.api.UserType create(com.soffid.iam.iga.api.UserType tipusUsuari)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.UserType create(
		final com.soffid.iam.iga.api.UserType tipusUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("userType:create"))
			throw new SecurityException("Unable to execute UserDomainService.create. Required roles: [userType:create]");
		try
		{
			return this.userDomainService.create(tipusUsuari); 
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
	 * @see com.soffid.iam.iga.service.UserDomainService#com.soffid.iam.iga.api.UserType update(com.soffid.iam.iga.api.UserType tipusUsuari)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.UserType update(
		final com.soffid.iam.iga.api.UserType tipusUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("userType:update"))
			throw new SecurityException("Unable to execute UserDomainService.update. Required roles: [userType:update]");
		try
		{
			return this.userDomainService.update(tipusUsuari); 
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
	 * @see com.soffid.iam.iga.service.UserDomainService#com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.UserType> findUserTypes(com.soffid.zkdb.api.Query q)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.UserType> findUserTypes(
		final com.soffid.zkdb.api.Query q)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("userType:query"))
			throw new SecurityException("Unable to execute UserDomainService.findUserTypes. Required roles: [userType:query]");
		try
		{
			return this.userDomainService.findUserTypes(q); 
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
	 * @see com.soffid.iam.iga.service.UserDomainService#java.util.Collection<com.soffid.iam.am.api.PasswordDomain> findAllPasswordDomain()
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.am.api.PasswordDomain> findAllPasswordDomain()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("usersDomain:query"))
			throw new SecurityException("Unable to execute UserDomainService.findAllPasswordDomain. Required roles: [usersDomain:query]");
		try
		{
			return this.userDomainService.findAllPasswordDomain(); 
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
	 * @see com.soffid.iam.iga.service.UserDomainService#java.util.Collection<com.soffid.iam.iga.api.UserDomain> findAllUserDomain()
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.iga.api.UserDomain> findAllUserDomain()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("usersDomain:query"))
			throw new SecurityException("Unable to execute UserDomainService.findAllUserDomain. Required roles: [usersDomain:query]");
		try
		{
			return this.userDomainService.findAllUserDomain(); 
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
	 * @see com.soffid.iam.iga.service.UserDomainService#java.util.Collection<com.soffid.iam.am.api.ForbiddenWord> findAllForbiddenWords()
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.am.api.ForbiddenWord> findAllForbiddenWords()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("passwordDomain:query"))
			throw new SecurityException("Unable to execute UserDomainService.findAllForbiddenWords. Required roles: [passwordDomain:query]");
		try
		{
			return this.userDomainService.findAllForbiddenWords(); 
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
	 * @see com.soffid.iam.iga.service.UserDomainService#java.util.Collection<com.soffid.iam.am.api.PasswordPolicy> findAllPasswordPolicyDomain(java.lang.String codiDominiContrasenya)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.am.api.PasswordPolicy> findAllPasswordPolicyDomain(
		final java.lang.String codiDominiContrasenya)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("passwordDomain:query"))
			throw new SecurityException("Unable to execute UserDomainService.findAllPasswordPolicyDomain. Required roles: [passwordDomain:query]");
		try
		{
			return this.userDomainService.findAllPasswordPolicyDomain(codiDominiContrasenya); 
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
	 * @see com.soffid.iam.iga.service.UserDomainService#java.util.Collection<com.soffid.iam.iga.api.UserType> findAllUserType()
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.iga.api.UserType> findAllUserType()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("sso:manageAccounts")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("userType:query"))
			throw new SecurityException("Unable to execute UserDomainService.findAllUserType. Required roles: [sso:manageAccounts, userType_query]");
		try
		{
			return this.userDomainService.findAllUserType(); 
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
	 * @see com.soffid.iam.iga.service.UserDomainService#java.util.Collection<java.lang.String> findNameGenerators()
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<java.lang.String> findNameGenerators()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("usersDomain:query"))
			throw new SecurityException("Unable to execute UserDomainService.findNameGenerators. Required roles: [usersDomain:query]");
		try
		{
			return this.userDomainService.findNameGenerators(); 
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
	 * @see com.soffid.iam.iga.service.UserDomainService#java.util.Collection<com.soffid.iam.am.api.PasswordPolicyForbbidenWord> findForbiddenWordsPasswordPolicy(com.soffid.iam.am.api.PasswordPolicy politicaContrasenya)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.am.api.PasswordPolicyForbbidenWord> findForbiddenWordsPasswordPolicy(
		final com.soffid.iam.am.api.PasswordPolicy politicaContrasenya)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("usersDomain:query"))
			throw new SecurityException("Unable to execute UserDomainService.findForbiddenWordsPasswordPolicy. Required roles: [usersDomain:query]");
		try
		{
			return this.userDomainService.findForbiddenWordsPasswordPolicy(politicaContrasenya); 
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
	 * @see com.soffid.iam.iga.service.UserDomainService#void delete(com.soffid.iam.am.api.ForbiddenWord paraulaProhibida)
	 */
	@jakarta.annotation.security.PermitAll
	public void delete(
		final com.soffid.iam.am.api.ForbiddenWord paraulaProhibida)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("passwordDomain:delete"))
			throw new SecurityException("Unable to execute UserDomainService.delete. Required roles: [passwordDomain:delete]");
		try
		{
			this.userDomainService.delete(paraulaProhibida); 
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
	 * @see com.soffid.iam.iga.service.UserDomainService#void delete(com.soffid.iam.am.api.PasswordDomain dominiContrasenya)
	 */
	@jakarta.annotation.security.PermitAll
	public void delete(
		final com.soffid.iam.am.api.PasswordDomain dominiContrasenya)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("passwordDomain:delete"))
			throw new SecurityException("Unable to execute UserDomainService.delete. Required roles: [passwordDomain:delete]");
		try
		{
			this.userDomainService.delete(dominiContrasenya); 
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
	 * @see com.soffid.iam.iga.service.UserDomainService#void delete(com.soffid.iam.am.api.PasswordPolicy politicaContrasenyaDomini)
	 */
	@jakarta.annotation.security.PermitAll
	public void delete(
		final com.soffid.iam.am.api.PasswordPolicy politicaContrasenyaDomini)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("passwordDomain:delete"))
			throw new SecurityException("Unable to execute UserDomainService.delete. Required roles: [passwordDomain:delete]");
		try
		{
			this.userDomainService.delete(politicaContrasenyaDomini); 
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
	 * @see com.soffid.iam.iga.service.UserDomainService#void delete(com.soffid.iam.am.api.PasswordPolicyForbbidenWord paraulaProhibidaContrasenya)
	 */
	@jakarta.annotation.security.PermitAll
	public void delete(
		final com.soffid.iam.am.api.PasswordPolicyForbbidenWord paraulaProhibidaContrasenya)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("passwordDomain:delete"))
			throw new SecurityException("Unable to execute UserDomainService.delete. Required roles: [passwordDomain:delete]");
		try
		{
			this.userDomainService.delete(paraulaProhibidaContrasenya); 
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
	 * @see com.soffid.iam.iga.service.UserDomainService#void delete(com.soffid.iam.iga.api.UserDomain dominiUsuari)
	 */
	@jakarta.annotation.security.PermitAll
	public void delete(
		final com.soffid.iam.iga.api.UserDomain dominiUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("usersDomain:delete"))
			throw new SecurityException("Unable to execute UserDomainService.delete. Required roles: [usersDomain:delete]");
		try
		{
			this.userDomainService.delete(dominiUsuari); 
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
	 * @see com.soffid.iam.iga.service.UserDomainService#void delete(com.soffid.iam.iga.api.UserType tipusUsuari)
	 */
	@jakarta.annotation.security.PermitAll
	public void delete(
		final com.soffid.iam.iga.api.UserType tipusUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("userType:delete"))
			throw new SecurityException("Unable to execute UserDomainService.delete. Required roles: [userType:delete]");
		try
		{
			this.userDomainService.delete(tipusUsuari); 
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

		this.userDomainService = (com.soffid.iam.iga.service.UserDomainService)
		getBeanFactory().getBean("com.soffid.iam.iga.service.UserDomainService");
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
