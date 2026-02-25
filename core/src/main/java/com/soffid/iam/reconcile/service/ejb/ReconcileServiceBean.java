//
// (C) 2013 Soffid
//
//

package com.soffid.iam.reconcile.service.ejb;
/**
 * @see <code>com.soffid.iam.reconcile.service.ReconcileService</code>,
 * @see <code>com.soffid.iam.reconcile.service.ReconcileService</code>,
 */
@jakarta.ejb.Stateless(name="ReconcileService", mappedName="openejb:/local/soffid.ejb.com.soffid.iam.reconcile.service.ReconcileService")
@jakarta.ejb.Local(com.soffid.iam.reconcile.service.ejb.ReconcileService.class)
@jakarta.ejb.TransactionManagement(value=jakarta.ejb.TransactionManagementType.CONTAINER)
@jakarta.ejb.TransactionAttribute(value=jakarta.ejb.TransactionAttributeType.SUPPORTS)
public class ReconcileServiceBean extends org.springframework.ejb.support.AbstractStatelessSessionBean
  implements com.soffid.iam.reconcile.service.ejb.ReconcileService
{
	private com.soffid.iam.reconcile.service.ReconcileService reconcileService;

	/**
	 * @see com.soffid.iam.reconcile.service.ReconcileService#boolean isPendingTasks(java.lang.Long processId, java.lang.Long taskId)
	 */
	@jakarta.annotation.security.PermitAll
	public boolean isPendingTasks(
		final java.lang.Long processId, 
		final java.lang.Long taskId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:reconcile"))
			throw new SecurityException("Unable to execute ReconcileService.isPendingTasks. Required roles: [user:reconcile]");
		try
		{
			return this.reconcileService.isPendingTasks(processId, taskId); 
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
	 * @see com.soffid.iam.reconcile.service.ReconcileService#com.soffid.iam.iga.api.ReconcileAccount addUser(com.soffid.iam.iga.api.ReconcileAccount userInfo)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.ReconcileAccount addUser(
		final com.soffid.iam.iga.api.ReconcileAccount userInfo)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:reconcile"))
			throw new SecurityException("Unable to execute ReconcileService.addUser. Required roles: [user:reconcile]");
		try
		{
			return this.reconcileService.addUser(userInfo); 
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
	 * @see com.soffid.iam.reconcile.service.ReconcileService#com.soffid.iam.iga.api.ReconcileAccount findReconAccountById(java.lang.Long accountId)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.ReconcileAccount findReconAccountById(
		final java.lang.Long accountId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:reconcile"))
			throw new SecurityException("Unable to execute ReconcileService.findReconAccountById. Required roles: [user:reconcile]");
		try
		{
			return this.reconcileService.findReconAccountById(accountId); 
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
	 * @see com.soffid.iam.reconcile.service.ReconcileService#com.soffid.iam.iga.api.ReconcileAssignment addAssignment(com.soffid.iam.iga.api.ReconcileAssignment assignmentInfo)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.ReconcileAssignment addAssignment(
		final com.soffid.iam.iga.api.ReconcileAssignment assignmentInfo)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:reconcile"))
			throw new SecurityException("Unable to execute ReconcileService.addAssignment. Required roles: [user:reconcile]");
		try
		{
			return this.reconcileService.addAssignment(assignmentInfo); 
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
	 * @see com.soffid.iam.reconcile.service.ReconcileService#com.soffid.iam.iga.api.ReconcileAssignment findReconAssignmentById(java.lang.Long assignId)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.ReconcileAssignment findReconAssignmentById(
		final java.lang.Long assignId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:reconcile"))
			throw new SecurityException("Unable to execute ReconcileService.findReconAssignmentById. Required roles: [user:reconcile]");
		try
		{
			return this.reconcileService.findReconAssignmentById(assignId); 
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
	 * @see com.soffid.iam.reconcile.service.ReconcileService#com.soffid.iam.iga.api.ReconcileRole addRole(com.soffid.iam.iga.api.ReconcileRole roleInfo)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.ReconcileRole addRole(
		final com.soffid.iam.iga.api.ReconcileRole roleInfo)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:reconcile"))
			throw new SecurityException("Unable to execute ReconcileService.addRole. Required roles: [user:reconcile]");
		try
		{
			return this.reconcileService.addRole(roleInfo); 
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
	 * @see com.soffid.iam.reconcile.service.ReconcileService#com.soffid.iam.iga.api.ReconcileRole findReconRoleById(java.lang.Long roleId)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.ReconcileRole findReconRoleById(
		final java.lang.Long roleId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:reconcile"))
			throw new SecurityException("Unable to execute ReconcileService.findReconRoleById. Required roles: [user:reconcile]");
		try
		{
			return this.reconcileService.findReconRoleById(roleId); 
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
	 * @see com.soffid.iam.reconcile.service.ReconcileService#java.util.List<com.soffid.iam.iga.api.ReconcileAccount> findAllReconAccounts(java.lang.Long processId)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<com.soffid.iam.iga.api.ReconcileAccount> findAllReconAccounts(
		final java.lang.Long processId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:reconcile"))
			throw new SecurityException("Unable to execute ReconcileService.findAllReconAccounts. Required roles: [user:reconcile]");
		try
		{
			return this.reconcileService.findAllReconAccounts(processId); 
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
	 * @see com.soffid.iam.reconcile.service.ReconcileService#java.util.List<com.soffid.iam.iga.api.ReconcileAssignment> findAllReconAssignment(java.lang.Long processId)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<com.soffid.iam.iga.api.ReconcileAssignment> findAllReconAssignment(
		final java.lang.Long processId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:reconcile"))
			throw new SecurityException("Unable to execute ReconcileService.findAllReconAssignment. Required roles: [user:reconcile]");
		try
		{
			return this.reconcileService.findAllReconAssignment(processId); 
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
	 * @see com.soffid.iam.reconcile.service.ReconcileService#java.util.List<com.soffid.iam.iga.api.ReconcileRole> findAllReconRole(java.lang.Long processId)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<com.soffid.iam.iga.api.ReconcileRole> findAllReconRole(
		final java.lang.Long processId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:reconcile"))
			throw new SecurityException("Unable to execute ReconcileService.findAllReconRole. Required roles: [user:reconcile]");
		try
		{
			return this.reconcileService.findAllReconRole(processId); 
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
	 * @see com.soffid.iam.reconcile.service.ReconcileService#void createReconcileTask(java.lang.Long processId, java.lang.String dispatcher)
	 */
	@jakarta.annotation.security.PermitAll
	public void createReconcileTask(
		final java.lang.Long processId, 
		final java.lang.String dispatcher)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:reconcile"))
			throw new SecurityException("Unable to execute ReconcileService.createReconcileTask. Required roles: [user:reconcile]");
		try
		{
			this.reconcileService.createReconcileTask(processId, dispatcher); 
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
	 * @see com.soffid.iam.reconcile.service.ReconcileService#void reconcileAssignment(java.lang.Long processId)
	 */
	@jakarta.annotation.security.PermitAll
	public void reconcileAssignment(
		final java.lang.Long processId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:reconcile"))
			throw new SecurityException("Unable to execute ReconcileService.reconcileAssignment. Required roles: [user:reconcile]");
		try
		{
			this.reconcileService.reconcileAssignment(processId); 
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
	 * @see com.soffid.iam.reconcile.service.ReconcileService#void reconcileData(java.lang.Long processId)
	 */
	@jakarta.annotation.security.PermitAll
	public void reconcileData(
		final java.lang.Long processId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:reconcile"))
			throw new SecurityException("Unable to execute ReconcileService.reconcileData. Required roles: [user:reconcile]");
		try
		{
			this.reconcileService.reconcileData(processId); 
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
	 * @see com.soffid.iam.reconcile.service.ReconcileService#void reconcileRoles(java.lang.Long processId)
	 */
	@jakarta.annotation.security.PermitAll
	public void reconcileRoles(
		final java.lang.Long processId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:reconcile"))
			throw new SecurityException("Unable to execute ReconcileService.reconcileRoles. Required roles: [user:reconcile]");
		try
		{
			this.reconcileService.reconcileRoles(processId); 
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
	 * @see com.soffid.iam.reconcile.service.ReconcileService#void reconcileUsers(java.lang.Long processId)
	 */
	@jakarta.annotation.security.PermitAll
	public void reconcileUsers(
		final java.lang.Long processId)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:reconcile"))
			throw new SecurityException("Unable to execute ReconcileService.reconcileUsers. Required roles: [user:reconcile]");
		try
		{
			this.reconcileService.reconcileUsers(processId); 
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
	 * @see com.soffid.iam.reconcile.service.ReconcileService#void updateAssignment(com.soffid.iam.iga.api.ReconcileAssignment assignInfo)
	 */
	@jakarta.annotation.security.PermitAll
	public void updateAssignment(
		final com.soffid.iam.iga.api.ReconcileAssignment assignInfo)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:reconcile"))
			throw new SecurityException("Unable to execute ReconcileService.updateAssignment. Required roles: [user:reconcile]");
		try
		{
			this.reconcileService.updateAssignment(assignInfo); 
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
	 * @see com.soffid.iam.reconcile.service.ReconcileService#void updateRole(com.soffid.iam.iga.api.ReconcileRole roleInfo)
	 */
	@jakarta.annotation.security.PermitAll
	public void updateRole(
		final com.soffid.iam.iga.api.ReconcileRole roleInfo)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:reconcile"))
			throw new SecurityException("Unable to execute ReconcileService.updateRole. Required roles: [user:reconcile]");
		try
		{
			this.reconcileService.updateRole(roleInfo); 
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
	 * @see com.soffid.iam.reconcile.service.ReconcileService#void updateUser(com.soffid.iam.iga.api.ReconcileAccount userInfo)
	 */
	@jakarta.annotation.security.PermitAll
	public void updateUser(
		final com.soffid.iam.iga.api.ReconcileAccount userInfo)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:reconcile"))
			throw new SecurityException("Unable to execute ReconcileService.updateUser. Required roles: [user:reconcile]");
		try
		{
			this.reconcileService.updateUser(userInfo); 
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

		this.reconcileService = (com.soffid.iam.reconcile.service.ReconcileService)
		getBeanFactory().getBean("com.soffid.iam.reconcile.service.ReconcileService");
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
