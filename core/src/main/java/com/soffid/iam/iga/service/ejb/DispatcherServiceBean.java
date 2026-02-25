//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.service.ejb;
/**
 * @see <code>com.soffid.iam.iga.service.DispatcherService</code>,
 * @see <code>com.soffid.iam.iga.service.DispatcherService</code>,
 */
@jakarta.ejb.Stateless(name="DispatcherService", mappedName="openejb:/local/soffid.ejb.com.soffid.iam.iga.service.DispatcherService")
@jakarta.ejb.Local(com.soffid.iam.iga.service.ejb.DispatcherService.class)
@jakarta.ejb.TransactionManagement(value=jakarta.ejb.TransactionManagementType.CONTAINER)
@jakarta.ejb.TransactionAttribute(value=jakarta.ejb.TransactionAttributeType.SUPPORTS)
public class DispatcherServiceBean extends org.springframework.ejb.support.AbstractStatelessSessionBean
  implements com.soffid.iam.iga.service.ejb.DispatcherService
{
	private com.soffid.iam.iga.service.DispatcherService dispatcherService;

	/**
	 * @see com.soffid.iam.iga.service.DispatcherService#java.lang.String startVirtualSourceTransaction(boolean readonly, java.lang.String server)
	 */
	@jakarta.annotation.security.PermitAll
	public java.lang.String startVirtualSourceTransaction(
		final boolean readonly, 
		final java.lang.String server)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("agent:query"))
			throw new SecurityException("Unable to execute DispatcherService.startVirtualSourceTransaction. Required roles: [agent:query]");
		try
		{
			return this.dispatcherService.startVirtualSourceTransaction(readonly, server); 
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
	 * @see com.soffid.iam.iga.service.DispatcherService#void finishVirtualSourceTransaction(java.lang.String transactionId)
	 */
	@jakarta.annotation.security.PermitAll
	public void finishVirtualSourceTransaction(
		final java.lang.String transactionId)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("agent:query"))
			throw new SecurityException("Unable to execute DispatcherService.finishVirtualSourceTransaction. Required roles: [agent:query]");
		try
		{
			this.dispatcherService.finishVirtualSourceTransaction(transactionId); 
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
	 * @see com.soffid.iam.iga.service.DispatcherService#boolean isRegistrationTokenAlive(java.lang.String token)
	 */
	@jakarta.annotation.security.PermitAll
	public boolean isRegistrationTokenAlive(
		final java.lang.String token)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("server:manage:proxy")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("server:manage:server"))
			throw new SecurityException("Unable to execute DispatcherService.isRegistrationTokenAlive. Required roles: [server:manage:proxy, server_manage_server]");
		try
		{
			return this.dispatcherService.isRegistrationTokenAlive(token); 
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
	 * @see com.soffid.iam.iga.service.DispatcherService#com.soffid.iam.base.api.AsyncProcessTracker applyConfigurationAsync(com.soffid.iam.iga.api.System dispatcher)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.base.api.AsyncProcessTracker applyConfigurationAsync(
		final com.soffid.iam.iga.api.System dispatcher)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("agent:update"))
			throw new SecurityException("Unable to execute DispatcherService.applyConfigurationAsync. Required roles: [agent:update]");
		try
		{
			return this.dispatcherService.applyConfigurationAsync(dispatcher); 
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
	 * @see com.soffid.iam.iga.service.DispatcherService#com.soffid.iam.base.api.AsyncProcessTracker queryProcessStatus(com.soffid.iam.base.api.AsyncProcessTracker process)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.base.api.AsyncProcessTracker queryProcessStatus(
		final com.soffid.iam.base.api.AsyncProcessTracker process)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("agent:update"))
			throw new SecurityException("Unable to execute DispatcherService.queryProcessStatus. Required roles: [agent:update]");
		try
		{
			return this.dispatcherService.queryProcessStatus(process); 
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
	 * @see com.soffid.iam.iga.service.DispatcherService#com.soffid.iam.iga.api.AccessControl create(com.soffid.iam.iga.api.AccessControl controlAcces)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.AccessControl create(
		final com.soffid.iam.iga.api.AccessControl controlAcces)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("agent:accessControl:create"))
			throw new SecurityException("Unable to execute DispatcherService.create. Required roles: [agent:accessControl:create]");
		try
		{
			return this.dispatcherService.create(controlAcces); 
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
	 * @see com.soffid.iam.iga.service.DispatcherService#com.soffid.iam.iga.api.AccessControl update(com.soffid.iam.iga.api.AccessControl controlAcces)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.AccessControl update(
		final com.soffid.iam.iga.api.AccessControl controlAcces)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("agent:accessControl:create")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("agent:accessControl:update"))
			throw new SecurityException("Unable to execute DispatcherService.update. Required roles: [agent:accessControl:create, agent_accessControl_update]");
		try
		{
			return this.dispatcherService.update(controlAcces); 
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
	 * @see com.soffid.iam.iga.service.DispatcherService#com.soffid.iam.iga.api.AttributeMapping create(com.soffid.iam.iga.api.AttributeMapping mapping)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.AttributeMapping create(
		final com.soffid.iam.iga.api.AttributeMapping mapping)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("agent:update"))
			throw new SecurityException("Unable to execute DispatcherService.create. Required roles: [agent:update]");
		try
		{
			return this.dispatcherService.create(mapping); 
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
	 * @see com.soffid.iam.iga.service.DispatcherService#com.soffid.iam.iga.api.AttributeMapping update(com.soffid.iam.iga.api.AttributeMapping mapping)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.AttributeMapping update(
		final com.soffid.iam.iga.api.AttributeMapping mapping)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("agent:update"))
			throw new SecurityException("Unable to execute DispatcherService.update. Required roles: [agent:update]");
		try
		{
			return this.dispatcherService.update(mapping); 
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
	 * @see com.soffid.iam.iga.service.DispatcherService#com.soffid.iam.iga.api.ObjectMapping create(com.soffid.iam.iga.api.ObjectMapping om)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.ObjectMapping create(
		final com.soffid.iam.iga.api.ObjectMapping om)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("agent:update"))
			throw new SecurityException("Unable to execute DispatcherService.create. Required roles: [agent:update]");
		try
		{
			return this.dispatcherService.create(om); 
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
	 * @see com.soffid.iam.iga.service.DispatcherService#com.soffid.iam.iga.api.ObjectMapping update(com.soffid.iam.iga.api.ObjectMapping om)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.ObjectMapping update(
		final com.soffid.iam.iga.api.ObjectMapping om)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("agent:update"))
			throw new SecurityException("Unable to execute DispatcherService.update. Required roles: [agent:update]");
		try
		{
			return this.dispatcherService.update(om); 
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
	 * @see com.soffid.iam.iga.service.DispatcherService#com.soffid.iam.iga.api.ObjectMappingProperty create(com.soffid.iam.iga.api.ObjectMappingProperty omp)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.ObjectMappingProperty create(
		final com.soffid.iam.iga.api.ObjectMappingProperty omp)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("agent:update"))
			throw new SecurityException("Unable to execute DispatcherService.create. Required roles: [agent:update]");
		try
		{
			return this.dispatcherService.create(omp); 
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
	 * @see com.soffid.iam.iga.service.DispatcherService#com.soffid.iam.iga.api.ObjectMappingProperty update(com.soffid.iam.iga.api.ObjectMappingProperty om)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.ObjectMappingProperty update(
		final com.soffid.iam.iga.api.ObjectMappingProperty om)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("agent:update"))
			throw new SecurityException("Unable to execute DispatcherService.update. Required roles: [agent:update]");
		try
		{
			return this.dispatcherService.update(om); 
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
	 * @see com.soffid.iam.iga.service.DispatcherService#com.soffid.iam.iga.api.ObjectMappingTrigger create(com.soffid.iam.iga.api.ObjectMappingTrigger trigger)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.ObjectMappingTrigger create(
		final com.soffid.iam.iga.api.ObjectMappingTrigger trigger)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("agent:update"))
			throw new SecurityException("Unable to execute DispatcherService.create. Required roles: [agent:update]");
		try
		{
			return this.dispatcherService.create(trigger); 
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
	 * @see com.soffid.iam.iga.service.DispatcherService#com.soffid.iam.iga.api.ObjectMappingTrigger update(com.soffid.iam.iga.api.ObjectMappingTrigger trigger)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.ObjectMappingTrigger update(
		final com.soffid.iam.iga.api.ObjectMappingTrigger trigger)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("agent:update"))
			throw new SecurityException("Unable to execute DispatcherService.update. Required roles: [agent:update]");
		try
		{
			return this.dispatcherService.update(trigger); 
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
	 * @see com.soffid.iam.iga.service.DispatcherService#com.soffid.iam.iga.api.ReconcileTrigger create(com.soffid.iam.iga.api.ReconcileTrigger rp)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.ReconcileTrigger create(
		final com.soffid.iam.iga.api.ReconcileTrigger rp)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("agent:update"))
			throw new SecurityException("Unable to execute DispatcherService.create. Required roles: [agent:update]");
		try
		{
			return this.dispatcherService.create(rp); 
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
	 * @see com.soffid.iam.iga.service.DispatcherService#com.soffid.iam.iga.api.ReconcileTrigger update(com.soffid.iam.iga.api.ReconcileTrigger rp)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.ReconcileTrigger update(
		final com.soffid.iam.iga.api.ReconcileTrigger rp)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("agent:update"))
			throw new SecurityException("Unable to execute DispatcherService.update. Required roles: [agent:update]");
		try
		{
			return this.dispatcherService.update(rp); 
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
	 * @see com.soffid.iam.iga.service.DispatcherService#com.soffid.iam.iga.api.System create(com.soffid.iam.iga.api.System dispatcher)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.System create(
		final com.soffid.iam.iga.api.System dispatcher)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("agent:create"))
			throw new SecurityException("Unable to execute DispatcherService.create. Required roles: [agent:create]");
		try
		{
			return this.dispatcherService.create(dispatcher); 
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
	 * @see com.soffid.iam.iga.service.DispatcherService#com.soffid.iam.iga.api.System findDispatcherByName(java.lang.String codi)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.System findDispatcherByName(
		final java.lang.String codi)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("agent:query"))
			throw new SecurityException("Unable to execute DispatcherService.findDispatcherByName. Required roles: [agent:query]");
		try
		{
			return this.dispatcherService.findDispatcherByName(codi); 
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
	 * @see com.soffid.iam.iga.service.DispatcherService#com.soffid.iam.iga.api.System findSoffidDispatcher()
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.System findSoffidDispatcher()
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("agent:query")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("application:update"))
			throw new SecurityException("Unable to execute DispatcherService.findSoffidDispatcher. Required roles: [agent:query, application_update]");
		try
		{
			return this.dispatcherService.findSoffidDispatcher(); 
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
	 * @see com.soffid.iam.iga.service.DispatcherService#com.soffid.iam.iga.api.System update(com.soffid.iam.iga.api.System dispatcher)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.System update(
		final com.soffid.iam.iga.api.System dispatcher)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("agent:accessControl:create")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("agent:accessControl:delete")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("agent:accessControl:set")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("agent:accessControl:update")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("agent:update"))
			throw new SecurityException("Unable to execute DispatcherService.update. Required roles: [agent:accessControl:create, agent_accessControl_delete, agent_accessControl_set, agent_accessControl_update, agent_update]");
		try
		{
			return this.dispatcherService.update(dispatcher); 
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
	 * @see com.soffid.iam.iga.service.DispatcherService#com.soffid.iam.iga.api.SystemGroup create(com.soffid.iam.iga.api.SystemGroup grupDispatcher)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.SystemGroup create(
		final com.soffid.iam.iga.api.SystemGroup grupDispatcher)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("agent:create")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("agent:update"))
			throw new SecurityException("Unable to execute DispatcherService.create. Required roles: [agent:create, agent_update]");
		try
		{
			return this.dispatcherService.create(grupDispatcher); 
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
	 * @see com.soffid.iam.iga.service.DispatcherService#com.soffid.iam.iga.api.SystemGroup update(com.soffid.iam.iga.api.SystemGroup grupDispatcher)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.SystemGroup update(
		final com.soffid.iam.iga.api.SystemGroup grupDispatcher)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("agent:update"))
			throw new SecurityException("Unable to execute DispatcherService.update. Required roles: [agent:update]");
		try
		{
			return this.dispatcherService.update(grupDispatcher); 
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
	 * @see com.soffid.iam.iga.service.DispatcherService#com.soffid.iam.iga.api.UserTypeDispatcher create(com.soffid.iam.iga.api.UserTypeDispatcher tipusUsuari)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.UserTypeDispatcher create(
		final com.soffid.iam.iga.api.UserTypeDispatcher tipusUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("agent:create")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("agent:update"))
			throw new SecurityException("Unable to execute DispatcherService.create. Required roles: [agent:create, agent_update]");
		try
		{
			return this.dispatcherService.create(tipusUsuari); 
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
	 * @see com.soffid.iam.iga.service.DispatcherService#com.soffid.iam.iga.api.UserTypeDispatcher update(com.soffid.iam.iga.api.UserTypeDispatcher tipusUsuari)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.UserTypeDispatcher update(
		final com.soffid.iam.iga.api.UserTypeDispatcher tipusUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("agent:update"))
			throw new SecurityException("Unable to execute DispatcherService.update. Required roles: [agent:update]");
		try
		{
			return this.dispatcherService.update(tipusUsuari); 
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
	 * @see com.soffid.iam.iga.service.DispatcherService#com.soffid.iam.sync.api.DebugTaskResults testPropagateObject(java.lang.String dispatcher, com.soffid.iam.iga.api.SoffidObjectType type, java.lang.String object1, java.lang.String object2)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.sync.api.DebugTaskResults testPropagateObject(
		final java.lang.String dispatcher, 
		final com.soffid.iam.iga.api.SoffidObjectType type, 
		final java.lang.String object1, 
		final java.lang.String object2)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("agent:create")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("agent:update"))
			throw new SecurityException("Unable to execute DispatcherService.testPropagateObject. Required roles: [agent:create, agent_update]");
		try
		{
			return this.dispatcherService.testPropagateObject(dispatcher, type, object1, object2); 
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
	 * @see com.soffid.iam.iga.service.DispatcherService#com.soffid.iam.sync.api.GetObjectResults getNativeObject(java.lang.String dispatcher, com.soffid.iam.iga.api.SoffidObjectType type, java.lang.String object1, java.lang.String object2)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.sync.api.GetObjectResults getNativeObject(
		final java.lang.String dispatcher, 
		final com.soffid.iam.iga.api.SoffidObjectType type, 
		final java.lang.String object1, 
		final java.lang.String object2)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("agent:create")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("agent:queryObjects")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("agent:update"))
			throw new SecurityException("Unable to execute DispatcherService.getNativeObject. Required roles: [agent:create, agent_queryObjects, agent_update]");
		try
		{
			return this.dispatcherService.getNativeObject(dispatcher, type, object1, object2); 
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
	 * @see com.soffid.iam.iga.service.DispatcherService#com.soffid.iam.sync.api.GetObjectResults getSoffidObject(java.lang.String dispatcher, com.soffid.iam.iga.api.SoffidObjectType type, java.lang.String object1, java.lang.String object2)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.sync.api.GetObjectResults getSoffidObject(
		final java.lang.String dispatcher, 
		final com.soffid.iam.iga.api.SoffidObjectType type, 
		final java.lang.String object1, 
		final java.lang.String object2)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("agent:invoke")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("agent:queryObjects"))
			throw new SecurityException("Unable to execute DispatcherService.getSoffidObject. Required roles: [agent:invoke, agent_queryObjects]");
		try
		{
			return this.dispatcherService.getSoffidObject(dispatcher, type, object1, object2); 
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
	 * @see com.soffid.iam.iga.service.DispatcherService#com.soffid.iam.sync.api.GetObjectResults reconcile(java.lang.String dispatcher, java.lang.String accountName)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.sync.api.GetObjectResults reconcile(
		final java.lang.String dispatcher, 
		final java.lang.String accountName)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("agent:invoke"))
			throw new SecurityException("Unable to execute DispatcherService.reconcile. Required roles: [agent:invoke]");
		try
		{
			return this.dispatcherService.reconcile(dispatcher, accountName); 
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
	 * @see com.soffid.iam.iga.service.DispatcherService#com.soffid.iam.sync.api.Server create(com.soffid.iam.sync.api.Server server)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.sync.api.Server create(
		final com.soffid.iam.sync.api.Server server)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("server:manage:proxy")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("server:manage:server"))
			throw new SecurityException("Unable to execute DispatcherService.create. Required roles: [server:manage:proxy, server_manage_server]");
		try
		{
			return this.dispatcherService.create(server); 
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
	 * @see com.soffid.iam.iga.service.DispatcherService#com.soffid.iam.sync.api.Server update(com.soffid.iam.sync.api.Server server)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.sync.api.Server update(
		final com.soffid.iam.sync.api.Server server)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("server:manage:proxy")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("server:manage:server"))
			throw new SecurityException("Unable to execute DispatcherService.update. Required roles: [server:manage:proxy, server_manage_server]");
		try
		{
			return this.dispatcherService.update(server); 
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
	 * @see com.soffid.iam.iga.service.DispatcherService#com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.System> findSystems(com.soffid.zkdb.api.Query q)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.System> findSystems(
		final com.soffid.zkdb.api.Query q)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("agent:query"))
			throw new SecurityException("Unable to execute DispatcherService.findSystems. Required roles: [agent:query]");
		try
		{
			return this.dispatcherService.findSystems(q); 
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
	 * @see com.soffid.iam.iga.service.DispatcherService#java.lang.Long invokeAsync(java.lang.String dispatcher, java.lang.String verb, java.lang.String object, java.util.Map<java.lang.String,java.lang.Object> attributes)
	 */
	@jakarta.annotation.security.PermitAll
	public java.lang.Long invokeAsync(
		final java.lang.String dispatcher, 
		final java.lang.String verb, 
		final java.lang.String object, 
		final java.util.Map<java.lang.String,java.lang.Object> attributes)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("agent:invoke"))
			throw new SecurityException("Unable to execute DispatcherService.invokeAsync. Required roles: [agent:invoke]");
		try
		{
			return this.dispatcherService.invokeAsync(dispatcher, verb, object, attributes); 
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
	 * @see com.soffid.iam.iga.service.DispatcherService#java.lang.String generateChangesReport(com.soffid.iam.iga.api.System dispatcher)
	 */
	@jakarta.annotation.security.PermitAll
	public java.lang.String generateChangesReport(
		final com.soffid.iam.iga.api.System dispatcher)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("agent:update"))
			throw new SecurityException("Unable to execute DispatcherService.generateChangesReport. Required roles: [agent:update]");
		try
		{
			return this.dispatcherService.generateChangesReport(dispatcher); 
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
	 * @see com.soffid.iam.iga.service.DispatcherService#java.lang.String preRegisterServer(com.soffid.iam.sync.api.ServerRegistrationToken register)
	 */
	@jakarta.annotation.security.PermitAll
	public java.lang.String preRegisterServer(
		final com.soffid.iam.sync.api.ServerRegistrationToken register)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("server:manage:proxy")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("server:manage:server"))
			throw new SecurityException("Unable to execute DispatcherService.preRegisterServer. Required roles: [server:manage:proxy, server_manage_server]");
		try
		{
			return this.dispatcherService.preRegisterServer(register); 
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
	 * @see com.soffid.iam.iga.service.DispatcherService#java.lang.String[] getServerTenants(com.soffid.iam.sync.api.Server server)
	 */
	@jakarta.annotation.security.PermitAll
	public java.lang.String[] getServerTenants(
		final com.soffid.iam.sync.api.Server server)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("service:query"))
			throw new SecurityException("Unable to execute DispatcherService.getServerTenants. Required roles: [service:query]");
		try
		{
			return this.dispatcherService.getServerTenants(server); 
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
	 * @see com.soffid.iam.iga.service.DispatcherService#java.util.Collection<com.soffid.iam.iga.api.System> findAllActiveDispatchers()
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.iga.api.System> findAllActiveDispatchers()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("agent:query")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("user:role:create"))
			throw new SecurityException("Unable to execute DispatcherService.findAllActiveDispatchers. Required roles: [agent:query, user_role_create]");
		try
		{
			return this.dispatcherService.findAllActiveDispatchers(); 
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
	 * @see com.soffid.iam.iga.service.DispatcherService#java.util.Collection<com.soffid.iam.sync.api.Server> findAllServers()
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.sync.api.Server> findAllServers()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("server:query"))
			throw new SecurityException("Unable to execute DispatcherService.findAllServers. Required roles: [server:query]");
		try
		{
			return this.dispatcherService.findAllServers(); 
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
	 * @see com.soffid.iam.iga.service.DispatcherService#java.util.Collection<com.soffid.iam.iga.api.AttributeMapping> findAttributeMappingsByObject(java.lang.Long objectId)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.iga.api.AttributeMapping> findAttributeMappingsByObject(
		final java.lang.Long objectId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("agent:query"))
			throw new SecurityException("Unable to execute DispatcherService.findAttributeMappingsByObject. Required roles: [agent:query]");
		try
		{
			return this.dispatcherService.findAttributeMappingsByObject(objectId); 
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
	 * @see com.soffid.iam.iga.service.DispatcherService#java.util.Collection<com.soffid.iam.iga.api.AccessControl> findAccessControlByDispatcherName(java.lang.String codiAgent)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.iga.api.AccessControl> findAccessControlByDispatcherName(
		final java.lang.String codiAgent)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("agent:query"))
			throw new SecurityException("Unable to execute DispatcherService.findAccessControlByDispatcherName. Required roles: [agent:query]");
		try
		{
			return this.dispatcherService.findAccessControlByDispatcherName(codiAgent); 
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
	 * @see com.soffid.iam.iga.service.DispatcherService#java.util.Collection<com.soffid.iam.iga.api.ObjectMappingProperty> findObjectMappingPropertiesByObject(java.lang.Long objectId)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.iga.api.ObjectMappingProperty> findObjectMappingPropertiesByObject(
		final java.lang.Long objectId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("agent:query"))
			throw new SecurityException("Unable to execute DispatcherService.findObjectMappingPropertiesByObject. Required roles: [agent:query]");
		try
		{
			return this.dispatcherService.findObjectMappingPropertiesByObject(objectId); 
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
	 * @see com.soffid.iam.iga.service.DispatcherService#java.util.Collection<com.soffid.iam.iga.api.ObjectMappingTrigger> findObjectMappingTriggersByObject(java.lang.Long objectId)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.iga.api.ObjectMappingTrigger> findObjectMappingTriggersByObject(
		final java.lang.Long objectId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("agent:query"))
			throw new SecurityException("Unable to execute DispatcherService.findObjectMappingTriggersByObject. Required roles: [agent:query]");
		try
		{
			return this.dispatcherService.findObjectMappingTriggersByObject(objectId); 
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
	 * @see com.soffid.iam.iga.service.DispatcherService#java.util.Collection<com.soffid.iam.iga.api.ObjectMapping> findObjectMappingsByDispatcher(java.lang.Long dispatcherId)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.iga.api.ObjectMapping> findObjectMappingsByDispatcher(
		final java.lang.Long dispatcherId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("agent:query"))
			throw new SecurityException("Unable to execute DispatcherService.findObjectMappingsByDispatcher. Required roles: [agent:query]");
		try
		{
			return this.dispatcherService.findObjectMappingsByDispatcher(dispatcherId); 
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
	 * @see com.soffid.iam.iga.service.DispatcherService#java.util.Collection<com.soffid.iam.iga.api.ReconcileTrigger> findReconcileTriggersByDispatcher(java.lang.Long dispatcherId)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.iga.api.ReconcileTrigger> findReconcileTriggersByDispatcher(
		final java.lang.Long dispatcherId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("agent:query"))
			throw new SecurityException("Unable to execute DispatcherService.findReconcileTriggersByDispatcher. Required roles: [agent:query]");
		try
		{
			return this.dispatcherService.findReconcileTriggersByDispatcher(dispatcherId); 
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
	 * @see com.soffid.iam.iga.service.DispatcherService#java.util.Collection<com.soffid.iam.sync.api.ScheduledTask> findScheduledTasksByDispatcher(com.soffid.iam.iga.api.System dispatcher)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.sync.api.ScheduledTask> findScheduledTasksByDispatcher(
		final com.soffid.iam.iga.api.System dispatcher)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("agent:update"))
			throw new SecurityException("Unable to execute DispatcherService.findScheduledTasksByDispatcher. Required roles: [agent:update]");
		try
		{
			return this.dispatcherService.findScheduledTasksByDispatcher(dispatcher); 
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
	 * @see com.soffid.iam.iga.service.DispatcherService#java.util.Collection<com.soffid.iam.sync.api.Server> findTenantServers()
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.sync.api.Server> findTenantServers()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("server:query"))
			throw new SecurityException("Unable to execute DispatcherService.findTenantServers. Required roles: [server:query]");
		try
		{
			return this.dispatcherService.findTenantServers(); 
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
	 * @see com.soffid.iam.iga.service.DispatcherService#java.util.Collection<com.soffid.iam.iga.api.AccessControl> getAccessControl(com.soffid.iam.iga.api.System agent)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.iga.api.AccessControl> getAccessControl(
		final com.soffid.iam.iga.api.System agent)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("agent:query"))
			throw new SecurityException("Unable to execute DispatcherService.getAccessControl. Required roles: [agent:query]");
		try
		{
			return this.dispatcherService.getAccessControl(agent); 
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
	 * @see com.soffid.iam.iga.service.DispatcherService#java.util.Collection<com.soffid.iam.iga.api.SystemGroup> getDispatcherGroups(com.soffid.iam.iga.api.System agent)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.iga.api.SystemGroup> getDispatcherGroups(
		final com.soffid.iam.iga.api.System agent)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("agent:query"))
			throw new SecurityException("Unable to execute DispatcherService.getDispatcherGroups. Required roles: [agent:query]");
		try
		{
			return this.dispatcherService.getDispatcherGroups(agent); 
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
	 * @see com.soffid.iam.iga.service.DispatcherService#java.util.Collection<com.soffid.iam.iga.api.UserTypeDispatcher> getDispatcherUserTypes(com.soffid.iam.iga.api.System agent)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.iga.api.UserTypeDispatcher> getDispatcherUserTypes(
		final com.soffid.iam.iga.api.System agent)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("agent:query"))
			throw new SecurityException("Unable to execute DispatcherService.getDispatcherUserTypes. Required roles: [agent:query]");
		try
		{
			return this.dispatcherService.getDispatcherUserTypes(agent); 
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
	 * @see com.soffid.iam.iga.service.DispatcherService#java.util.Collection<java.util.Map<java.lang.String,java.lang.Object>> invoke(java.lang.String dispatcher, java.lang.String verb, java.lang.String object, java.util.Map<java.lang.String,java.lang.Object> attributes)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<java.util.Map<java.lang.String,java.lang.Object>> invoke(
		final java.lang.String dispatcher, 
		final java.lang.String verb, 
		final java.lang.String object, 
		final java.util.Map<java.lang.String,java.lang.Object> attributes)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("agent:invoke"))
			throw new SecurityException("Unable to execute DispatcherService.invoke. Required roles: [agent:invoke]");
		try
		{
			return this.dispatcherService.invoke(dispatcher, verb, object, attributes); 
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
	 * @see com.soffid.iam.iga.service.DispatcherService#java.util.Map<java.lang.String,java.lang.Object> testObjectMapping(java.util.Map<java.lang.String,java.lang.String> sentences, java.lang.String dispatcher, com.soffid.iam.iga.api.SoffidObjectType type, java.lang.String object1, java.lang.String object2)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Map<java.lang.String,java.lang.Object> testObjectMapping(
		final java.util.Map<java.lang.String,java.lang.String> sentences, 
		final java.lang.String dispatcher, 
		final com.soffid.iam.iga.api.SoffidObjectType type, 
		final java.lang.String object1, 
		final java.lang.String object2)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("agent:create")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("agent:update"))
			throw new SecurityException("Unable to execute DispatcherService.testObjectMapping. Required roles: [agent:create, agent_update]");
		try
		{
			return this.dispatcherService.testObjectMapping(sentences, dispatcher, type, object1, object2); 
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
	 * @see com.soffid.iam.iga.service.DispatcherService#void applyConfiguration(com.soffid.iam.iga.api.System dispatcher)
	 */
	@jakarta.annotation.security.PermitAll
	public void applyConfiguration(
		final com.soffid.iam.iga.api.System dispatcher)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("agent:update"))
			throw new SecurityException("Unable to execute DispatcherService.applyConfiguration. Required roles: [agent:update]");
		try
		{
			this.dispatcherService.applyConfiguration(dispatcher); 
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
	 * @see com.soffid.iam.iga.service.DispatcherService#void checkConnectivity(java.lang.String dispatcher)
	 */
	@jakarta.annotation.security.PermitAll
	public void checkConnectivity(
		final java.lang.String dispatcher)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("agent:query"))
			throw new SecurityException("Unable to execute DispatcherService.checkConnectivity. Required roles: [agent:query]");
		try
		{
			this.dispatcherService.checkConnectivity(dispatcher); 
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
	 * @see com.soffid.iam.iga.service.DispatcherService#void delete(com.soffid.iam.iga.api.AccessControl controlAcces)
	 */
	@jakarta.annotation.security.PermitAll
	public void delete(
		final com.soffid.iam.iga.api.AccessControl controlAcces)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("agent:accessControl:delete")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("agent:delete"))
			throw new SecurityException("Unable to execute DispatcherService.delete. Required roles: [agent:accessControl:delete, agent_delete]");
		try
		{
			this.dispatcherService.delete(controlAcces); 
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
	 * @see com.soffid.iam.iga.service.DispatcherService#void delete(com.soffid.iam.iga.api.AttributeMapping mapping)
	 */
	@jakarta.annotation.security.PermitAll
	public void delete(
		final com.soffid.iam.iga.api.AttributeMapping mapping)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("agent:update"))
			throw new SecurityException("Unable to execute DispatcherService.delete. Required roles: [agent:update]");
		try
		{
			this.dispatcherService.delete(mapping); 
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
	 * @see com.soffid.iam.iga.service.DispatcherService#void delete(com.soffid.iam.iga.api.ObjectMapping om)
	 */
	@jakarta.annotation.security.PermitAll
	public void delete(
		final com.soffid.iam.iga.api.ObjectMapping om)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("agent:update"))
			throw new SecurityException("Unable to execute DispatcherService.delete. Required roles: [agent:update]");
		try
		{
			this.dispatcherService.delete(om); 
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
	 * @see com.soffid.iam.iga.service.DispatcherService#void delete(com.soffid.iam.iga.api.ObjectMappingProperty omp)
	 */
	@jakarta.annotation.security.PermitAll
	public void delete(
		final com.soffid.iam.iga.api.ObjectMappingProperty omp)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("agent:update"))
			throw new SecurityException("Unable to execute DispatcherService.delete. Required roles: [agent:update]");
		try
		{
			this.dispatcherService.delete(omp); 
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
	 * @see com.soffid.iam.iga.service.DispatcherService#void delete(com.soffid.iam.iga.api.ObjectMappingTrigger tirger)
	 */
	@jakarta.annotation.security.PermitAll
	public void delete(
		final com.soffid.iam.iga.api.ObjectMappingTrigger tirger)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("agent:update"))
			throw new SecurityException("Unable to execute DispatcherService.delete. Required roles: [agent:update]");
		try
		{
			this.dispatcherService.delete(tirger); 
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
	 * @see com.soffid.iam.iga.service.DispatcherService#void delete(com.soffid.iam.iga.api.ReconcileTrigger rp)
	 */
	@jakarta.annotation.security.PermitAll
	public void delete(
		final com.soffid.iam.iga.api.ReconcileTrigger rp)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("agent:update"))
			throw new SecurityException("Unable to execute DispatcherService.delete. Required roles: [agent:update]");
		try
		{
			this.dispatcherService.delete(rp); 
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
	 * @see com.soffid.iam.iga.service.DispatcherService#void delete(com.soffid.iam.iga.api.System dispatcher)
	 */
	@jakarta.annotation.security.PermitAll
	public void delete(
		final com.soffid.iam.iga.api.System dispatcher)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("agent:delete"))
			throw new SecurityException("Unable to execute DispatcherService.delete. Required roles: [agent:delete]");
		try
		{
			this.dispatcherService.delete(dispatcher); 
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
	 * @see com.soffid.iam.iga.service.DispatcherService#void delete(com.soffid.iam.iga.api.SystemGroup grupDispatcher)
	 */
	@jakarta.annotation.security.PermitAll
	public void delete(
		final com.soffid.iam.iga.api.SystemGroup grupDispatcher)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("agent:delete")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("agent:update"))
			throw new SecurityException("Unable to execute DispatcherService.delete. Required roles: [agent:delete, agent_update]");
		try
		{
			this.dispatcherService.delete(grupDispatcher); 
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
	 * @see com.soffid.iam.iga.service.DispatcherService#void delete(com.soffid.iam.iga.api.UserTypeDispatcher tipusUsuari)
	 */
	@jakarta.annotation.security.PermitAll
	public void delete(
		final com.soffid.iam.iga.api.UserTypeDispatcher tipusUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("agent:delete")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("agent:update"))
			throw new SecurityException("Unable to execute DispatcherService.delete. Required roles: [agent:delete, agent_update]");
		try
		{
			this.dispatcherService.delete(tipusUsuari); 
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
	 * @see com.soffid.iam.iga.service.DispatcherService#void delete(com.soffid.iam.sync.api.Server server)
	 */
	@jakarta.annotation.security.PermitAll
	public void delete(
		final com.soffid.iam.sync.api.Server server)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("server:manage:proxy")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("server:manage:server"))
			throw new SecurityException("Unable to execute DispatcherService.delete. Required roles: [server:manage:proxy, server_manage_server]");
		try
		{
			this.dispatcherService.delete(server); 
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
	 * @see com.soffid.iam.iga.service.DispatcherService#void propagateGroupsForSystem(java.lang.String codiAgent, com.soffid.iam.sync.api.ScheduledTask task)
	 */
	@jakarta.annotation.security.PermitAll
	public void propagateGroupsForSystem(
		final java.lang.String codiAgent, 
		final com.soffid.iam.sync.api.ScheduledTask task)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("agent:refreshGroups"))
			throw new SecurityException("Unable to execute DispatcherService.propagateGroupsForSystem. Required roles: [agent:refreshGroups]");
		try
		{
			this.dispatcherService.propagateGroupsForSystem(codiAgent, task); 
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
	 * @see com.soffid.iam.iga.service.DispatcherService#void propagateRolesForSystem(java.lang.String codiAgent, com.soffid.iam.sync.api.ScheduledTask task)
	 */
	@jakarta.annotation.security.PermitAll
	public void propagateRolesForSystem(
		final java.lang.String codiAgent, 
		final com.soffid.iam.sync.api.ScheduledTask task)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("agent:refreshRoles"))
			throw new SecurityException("Unable to execute DispatcherService.propagateRolesForSystem. Required roles: [agent:refreshRoles]");
		try
		{
			this.dispatcherService.propagateRolesForSystem(codiAgent, task); 
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
	 * @see com.soffid.iam.iga.service.DispatcherService#void propagateUsersForSystem(java.lang.String codiAgent, com.soffid.iam.sync.api.ScheduledTask task)
	 */
	@jakarta.annotation.security.PermitAll
	public void propagateUsersForSystem(
		final java.lang.String codiAgent, 
		final com.soffid.iam.sync.api.ScheduledTask task)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("agent:refreshUsers"))
			throw new SecurityException("Unable to execute DispatcherService.propagateUsersForSystem. Required roles: [agent:refreshUsers]");
		try
		{
			this.dispatcherService.propagateUsersForSystem(codiAgent, task); 
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
	 * @see com.soffid.iam.iga.service.DispatcherService#void renameAccounts(com.soffid.iam.iga.api.System dispatcher)
	 */
	@jakarta.annotation.security.PermitAll
	public void renameAccounts(
		final com.soffid.iam.iga.api.System dispatcher)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("agent:update"))
			throw new SecurityException("Unable to execute DispatcherService.renameAccounts. Required roles: [agent:update]");
		try
		{
			this.dispatcherService.renameAccounts(dispatcher); 
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
	 * @see com.soffid.iam.iga.service.DispatcherService#void setDefaultMappingsByDispatcher(java.lang.Long dispatcherId)
	 */
	@jakarta.annotation.security.PermitAll
	public void setDefaultMappingsByDispatcher(
		final java.lang.Long dispatcherId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("agent:update"))
			throw new SecurityException("Unable to execute DispatcherService.setDefaultMappingsByDispatcher. Required roles: [agent:update]");
		try
		{
			this.dispatcherService.setDefaultMappingsByDispatcher(dispatcherId); 
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

		this.dispatcherService = (com.soffid.iam.iga.service.DispatcherService)
		getBeanFactory().getBean("com.soffid.iam.iga.service.DispatcherService");
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
