//
// (C) 2013 Soffid
//
//

package com.soffid.iam.am.service.ejb;
/**
 * @see <code>com.soffid.iam.am.service.EntryPointService</code>,
 * @see <code>com.soffid.iam.am.service.EntryPointService</code>,
 */
@jakarta.ejb.Stateless(name="EntryPointService", mappedName="openejb:/local/soffid.ejb.com.soffid.iam.am.service.EntryPointService")
@jakarta.ejb.Local(com.soffid.iam.am.service.ejb.EntryPointService.class)
@jakarta.ejb.TransactionManagement(value=jakarta.ejb.TransactionManagementType.CONTAINER)
@jakarta.ejb.TransactionAttribute(value=jakarta.ejb.TransactionAttributeType.SUPPORTS)
public class EntryPointServiceBean extends org.springframework.ejb.support.AbstractStatelessSessionBean
  implements com.soffid.iam.am.service.ejb.EntryPointService
{
	private com.soffid.iam.am.service.EntryPointService entryPointService;

	/**
	 * @see com.soffid.iam.am.service.EntryPointService#boolean canAdmin(com.soffid.iam.am.api.AccessTree puntEntrada)
	 */
	@jakarta.annotation.security.PermitAll
	public boolean canAdmin(
		final com.soffid.iam.am.api.AccessTree puntEntrada)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.entryPointService.canAdmin(puntEntrada); 
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
	 * @see com.soffid.iam.am.service.EntryPointService#boolean canExecute(com.soffid.iam.am.api.AccessTree puntEntrada)
	 */
	@jakarta.annotation.security.PermitAll
	public boolean canExecute(
		final com.soffid.iam.am.api.AccessTree puntEntrada)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.entryPointService.canExecute(puntEntrada); 
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
	 * @see com.soffid.iam.am.service.EntryPointService#boolean canQuery(com.soffid.iam.am.api.AccessTree puntEntrada)
	 */
	@jakarta.annotation.security.PermitAll
	public boolean canQuery(
		final com.soffid.iam.am.api.AccessTree puntEntrada)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.entryPointService.canQuery(puntEntrada); 
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
	 * @see com.soffid.iam.am.service.EntryPointService#boolean canView(com.soffid.iam.am.api.AccessTree puntEntrada)
	 */
	@jakarta.annotation.security.PermitAll
	public boolean canView(
		final com.soffid.iam.am.api.AccessTree puntEntrada)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.entryPointService.canView(puntEntrada); 
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
	 * @see com.soffid.iam.am.service.EntryPointService#boolean copyApplicationAccessLink(com.soffid.iam.am.api.AccessTree puntEntradaCopiar, com.soffid.iam.am.api.AccessTree puntEntradaMenuDesti)
	 */
	@jakarta.annotation.security.PermitAll
	public boolean copyApplicationAccessLink(
		final com.soffid.iam.am.api.AccessTree puntEntradaCopiar, 
		final com.soffid.iam.am.api.AccessTree puntEntradaMenuDesti)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.entryPointService.copyApplicationAccessLink(puntEntradaCopiar, puntEntradaMenuDesti); 
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
	 * @see com.soffid.iam.am.service.EntryPointService#boolean copyApplicationAccess(com.soffid.iam.am.api.AccessTree puntEntradaCopiar, com.soffid.iam.am.api.AccessTree puntEntradaMenuDesti)
	 */
	@jakarta.annotation.security.PermitAll
	public boolean copyApplicationAccess(
		final com.soffid.iam.am.api.AccessTree puntEntradaCopiar, 
		final com.soffid.iam.am.api.AccessTree puntEntradaMenuDesti)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.entryPointService.copyApplicationAccess(puntEntradaCopiar, puntEntradaMenuDesti); 
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
	 * @see com.soffid.iam.am.service.EntryPointService#boolean isAuthorized(com.soffid.iam.am.api.AccessTree puntEntrada, java.lang.String nivell)
	 */
	@jakarta.annotation.security.PermitAll
	public boolean isAuthorized(
		final com.soffid.iam.am.api.AccessTree puntEntrada, 
		final java.lang.String nivell)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.entryPointService.isAuthorized(puntEntrada, nivell); 
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
	 * @see com.soffid.iam.am.service.EntryPointService#boolean isAuthorized(java.lang.String codiUsuari, java.lang.Long idPuntEntrada, java.lang.String nivell)
	 */
	@jakarta.annotation.security.PermitAll
	public boolean isAuthorized(
		final java.lang.String codiUsuari, 
		final java.lang.Long idPuntEntrada, 
		final java.lang.String nivell)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.entryPointService.isAuthorized(codiUsuari, idPuntEntrada, nivell); 
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
	 * @see com.soffid.iam.am.service.EntryPointService#boolean applicationAccessTreeHasAnyACL(java.lang.String codiUsuari)
	 */
	@jakarta.annotation.security.PermitAll
	public boolean applicationAccessTreeHasAnyACL(
		final java.lang.String codiUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.entryPointService.applicationAccessTreeHasAnyACL(codiUsuari); 
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
	 * @see com.soffid.iam.am.service.EntryPointService#boolean moveApplicationAccessTreeMenu(com.soffid.iam.am.api.AccessTree puntEntradaMoure, com.soffid.iam.am.api.AccessTree puntEntradaMenuDesti)
	 */
	@jakarta.annotation.security.PermitAll
	public boolean moveApplicationAccessTreeMenu(
		final com.soffid.iam.am.api.AccessTree puntEntradaMoure, 
		final com.soffid.iam.am.api.AccessTree puntEntradaMenuDesti)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.entryPointService.moveApplicationAccessTreeMenu(puntEntradaMoure, puntEntradaMenuDesti); 
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
	 * @see com.soffid.iam.am.service.EntryPointService#boolean reorderApplicationAccess(com.soffid.iam.am.api.AccessTree puntEntradaOrdenar, com.soffid.iam.am.api.AccessTree puntEntradaSeguent)
	 */
	@jakarta.annotation.security.PermitAll
	public boolean reorderApplicationAccess(
		final com.soffid.iam.am.api.AccessTree puntEntradaOrdenar, 
		final com.soffid.iam.am.api.AccessTree puntEntradaSeguent)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.entryPointService.reorderApplicationAccess(puntEntradaOrdenar, puntEntradaSeguent); 
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
	 * @see com.soffid.iam.am.service.EntryPointService#com.soffid.iam.am.api.AccessTree create(com.soffid.iam.am.api.AccessTree puntEntrada)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.am.api.AccessTree create(
		final com.soffid.iam.am.api.AccessTree puntEntrada)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.entryPointService.create(puntEntrada); 
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
	 * @see com.soffid.iam.am.service.EntryPointService#com.soffid.iam.am.api.AccessTree findApplicationAccessById(long id)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.am.api.AccessTree findApplicationAccessById(
		final long id)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.entryPointService.findApplicationAccessById(id); 
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
	 * @see com.soffid.iam.am.service.EntryPointService#com.soffid.iam.am.api.AccessTree findRoot()
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.am.api.AccessTree findRoot()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.entryPointService.findRoot(); 
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
	 * @see com.soffid.iam.am.service.EntryPointService#com.soffid.iam.am.api.AccessTree update(com.soffid.iam.am.api.AccessTree puntEntrada)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.am.api.AccessTree update(
		final com.soffid.iam.am.api.AccessTree puntEntrada)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.entryPointService.update(puntEntrada); 
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
	 * @see com.soffid.iam.am.service.EntryPointService#com.soffid.iam.am.api.AccessTreeAuthorization createAuthorization(com.soffid.iam.am.api.AccessTree puntEntrada, com.soffid.iam.am.api.AccessTreeAuthorization autoritzacio)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.am.api.AccessTreeAuthorization createAuthorization(
		final com.soffid.iam.am.api.AccessTree puntEntrada, 
		final com.soffid.iam.am.api.AccessTreeAuthorization autoritzacio)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.entryPointService.createAuthorization(puntEntrada, autoritzacio); 
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
	 * @see com.soffid.iam.am.service.EntryPointService#com.soffid.iam.am.api.AccessTreeExecution createExecution(com.soffid.iam.am.api.AccessTree puntEntrada, com.soffid.iam.am.api.AccessTreeExecution execucio)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.am.api.AccessTreeExecution createExecution(
		final com.soffid.iam.am.api.AccessTree puntEntrada, 
		final com.soffid.iam.am.api.AccessTreeExecution execucio)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.entryPointService.createExecution(puntEntrada, execucio); 
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
	 * @see com.soffid.iam.am.service.EntryPointService#com.soffid.iam.am.api.AccessTreeExecution updateExecution(com.soffid.iam.am.api.AccessTree puntEntrada, com.soffid.iam.am.api.AccessTreeExecution execucio)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.am.api.AccessTreeExecution updateExecution(
		final com.soffid.iam.am.api.AccessTree puntEntrada, 
		final com.soffid.iam.am.api.AccessTreeExecution execucio)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.entryPointService.updateExecution(puntEntrada, execucio); 
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
	 * @see com.soffid.iam.am.service.EntryPointService#com.soffid.zkdb.api.PagedResult<com.soffid.iam.am.api.AccessTree> findAccessTrees(com.soffid.zkdb.api.Query q)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.am.api.AccessTree> findAccessTrees(
		final com.soffid.zkdb.api.Query q)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.entryPointService.findAccessTrees(q); 
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
	 * @see com.soffid.iam.am.service.EntryPointService#java.lang.String getScopeForAddress(java.lang.String address)
	 */
	@jakarta.annotation.security.PermitAll
	public java.lang.String getScopeForAddress(
		final java.lang.String address)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.entryPointService.getScopeForAddress(address); 
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
	 * @see com.soffid.iam.am.service.EntryPointService#java.lang.String validateXMLApplicationAccess(com.soffid.iam.am.api.AccessTree puntEntrada)
	 */
	@jakarta.annotation.security.PermitAll
	public java.lang.String validateXMLApplicationAccess(
		final com.soffid.iam.am.api.AccessTree puntEntrada)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.entryPointService.validateXMLApplicationAccess(puntEntrada); 
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
	 * @see com.soffid.iam.am.service.EntryPointService#java.util.Collection<com.soffid.iam.am.api.AccessTree> findChildren(com.soffid.iam.am.api.AccessTree puntEntrada)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.am.api.AccessTree> findChildren(
		final com.soffid.iam.am.api.AccessTree puntEntrada)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.entryPointService.findChildren(puntEntrada); 
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
	 * @see com.soffid.iam.am.service.EntryPointService#java.util.Collection<com.soffid.iam.am.api.AccessTree> findMenuChildren(com.soffid.iam.am.api.AccessTree puntEntrada)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.am.api.AccessTree> findMenuChildren(
		final com.soffid.iam.am.api.AccessTree puntEntrada)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.entryPointService.findMenuChildren(puntEntrada); 
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
	 * @see com.soffid.iam.am.service.EntryPointService#java.util.Collection<com.soffid.iam.am.api.AccessTree> findApplicationAccessByFilter(java.lang.String nomPUE, java.lang.String codiPUE, java.lang.String codiAplicacio, java.lang.String codiRol, java.lang.String codiGrup, java.lang.String codiUsuari)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.am.api.AccessTree> findApplicationAccessByFilter(
		final java.lang.String nomPUE, 
		final java.lang.String codiPUE, 
		final java.lang.String codiAplicacio, 
		final java.lang.String codiRol, 
		final java.lang.String codiGrup, 
		final java.lang.String codiUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.entryPointService.findApplicationAccessByFilter(nomPUE, codiPUE, codiAplicacio, codiRol, codiGrup, codiUsuari); 
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
	 * @see com.soffid.iam.am.service.EntryPointService#java.util.Collection<com.soffid.iam.am.api.AccessTreeExecutionType> getAllMimeTypeExecution()
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.am.api.AccessTreeExecutionType> getAllMimeTypeExecution()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.entryPointService.getAllMimeTypeExecution(); 
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
	 * @see com.soffid.iam.am.service.EntryPointService#java.util.Collection<java.lang.String> getReverseApplicationAccessTree(com.soffid.iam.am.api.AccessTree puntEntrada)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<java.lang.String> getReverseApplicationAccessTree(
		final com.soffid.iam.am.api.AccessTree puntEntrada)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.entryPointService.getReverseApplicationAccessTree(puntEntrada); 
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
	 * @see com.soffid.iam.am.service.EntryPointService#java.util.Collection<com.soffid.iam.am.api.AccessTreeAuthorization> getAuthorizationsApplicationAcessTree(com.soffid.iam.am.api.AccessTree puntEntrada)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.am.api.AccessTreeAuthorization> getAuthorizationsApplicationAcessTree(
		final com.soffid.iam.am.api.AccessTree puntEntrada)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.entryPointService.getAuthorizationsApplicationAcessTree(puntEntrada); 
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
	 * @see com.soffid.iam.am.service.EntryPointService#java.util.Collection<com.soffid.iam.am.api.AccessTreeExecution> getExecutions(com.soffid.iam.am.api.AccessTree puntEntrada)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.am.api.AccessTreeExecution> getExecutions(
		final com.soffid.iam.am.api.AccessTree puntEntrada)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.entryPointService.getExecutions(puntEntrada); 
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
	 * @see com.soffid.iam.am.service.EntryPointService#void delete(com.soffid.iam.am.api.AccessTree puntEntrada)
	 */
	@jakarta.annotation.security.PermitAll
	public void delete(
		final com.soffid.iam.am.api.AccessTree puntEntrada)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			this.entryPointService.delete(puntEntrada); 
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
	 * @see com.soffid.iam.am.service.EntryPointService#void deleteAuthorization(com.soffid.iam.am.api.AccessTree puntEntrada, com.soffid.iam.am.api.AccessTreeAuthorization autoritzacio)
	 */
	@jakarta.annotation.security.PermitAll
	public void deleteAuthorization(
		final com.soffid.iam.am.api.AccessTree puntEntrada, 
		final com.soffid.iam.am.api.AccessTreeAuthorization autoritzacio)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			this.entryPointService.deleteAuthorization(puntEntrada, autoritzacio); 
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
	 * @see com.soffid.iam.am.service.EntryPointService#void deleteExecution(com.soffid.iam.am.api.AccessTree puntEntrada, com.soffid.iam.am.api.AccessTreeExecution execucio)
	 */
	@jakarta.annotation.security.PermitAll
	public void deleteExecution(
		final com.soffid.iam.am.api.AccessTree puntEntrada, 
		final com.soffid.iam.am.api.AccessTreeExecution execucio)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			this.entryPointService.deleteExecution(puntEntrada, execucio); 
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
	 * @see com.soffid.iam.am.service.EntryPointService#void sortChildren(long entryPointId)
	 */
	@jakarta.annotation.security.PermitAll
	public void sortChildren(
		final long entryPointId)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			this.entryPointService.sortChildren(entryPointId); 
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

		this.entryPointService = (com.soffid.iam.am.service.EntryPointService)
		getBeanFactory().getBean("com.soffid.iam.am.service.EntryPointService");
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
