//
// (C) 2013 Soffid
//
//

package com.soffid.iam.base.service.ejb;
/**
 * @see <code>com.soffid.iam.base.service.AdditionalDataService</code>,
 * @see <code>com.soffid.iam.base.service.AdditionalDataService</code>,
 */
@jakarta.ejb.Stateless(name="AdditionalDataService", mappedName="openejb:/local/soffid.ejb.com.soffid.iam.base.service.AdditionalDataService")
@jakarta.ejb.Local(com.soffid.iam.base.service.ejb.AdditionalDataService.class)
@jakarta.ejb.TransactionManagement(value=jakarta.ejb.TransactionManagementType.CONTAINER)
@jakarta.ejb.TransactionAttribute(value=jakarta.ejb.TransactionAttributeType.SUPPORTS)
public class AdditionalDataServiceBean extends org.springframework.ejb.support.AbstractStatelessSessionBean
  implements com.soffid.iam.base.service.ejb.AdditionalDataService
{
	private com.soffid.iam.base.service.AdditionalDataService additionalDataService;

	/**
	 * @see com.soffid.iam.base.service.AdditionalDataService#com.soffid.iam.base.api.AccountAccessLevelEnum getAccessLevel(com.soffid.iam.iga.api.CustomObjectType type)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.base.api.AccountAccessLevelEnum getAccessLevel(
		final com.soffid.iam.iga.api.CustomObjectType type)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.additionalDataService.getAccessLevel(type); 
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
	 * @see com.soffid.iam.base.service.AdditionalDataService#com.soffid.iam.base.api.DataType create(com.soffid.iam.base.api.DataType tipusDada)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.base.api.DataType create(
		final com.soffid.iam.base.api.DataType tipusDada)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("metadata:create"))
			throw new SecurityException("Unable to execute AdditionalDataService.create. Required roles: [metadata:create]");
		try
		{
			return this.additionalDataService.create(tipusDada); 
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
	 * @see com.soffid.iam.base.service.AdditionalDataService#com.soffid.iam.base.api.DataType findSystemDataType(java.lang.String system, java.lang.String name)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.base.api.DataType findSystemDataType(
		final java.lang.String system, 
		final java.lang.String name)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("metadata:query"))
			throw new SecurityException("Unable to execute AdditionalDataService.findSystemDataType. Required roles: [metadata:query]");
		try
		{
			return this.additionalDataService.findSystemDataType(system, name); 
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
	 * @see com.soffid.iam.base.service.AdditionalDataService#com.soffid.iam.base.api.DataType findDataTypeByName(java.lang.String codi)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.base.api.DataType findDataTypeByName(
		final java.lang.String codi)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.additionalDataService.findDataTypeByName(codi); 
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
	 * @see com.soffid.iam.base.service.AdditionalDataService#com.soffid.iam.base.api.DataType update(com.soffid.iam.base.api.DataType tipusDada)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.base.api.DataType update(
		final com.soffid.iam.base.api.DataType tipusDada)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("metadata:update"))
			throw new SecurityException("Unable to execute AdditionalDataService.update. Required roles: [metadata:update]");
		try
		{
			return this.additionalDataService.update(tipusDada); 
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
	 * @see com.soffid.iam.base.service.AdditionalDataService#com.soffid.iam.base.api.UserData create(com.soffid.iam.base.api.UserData dadaUsuari)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.base.api.UserData create(
		final com.soffid.iam.base.api.UserData dadaUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:custom:update")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("user:metadata:update"))
			throw new SecurityException("Unable to execute AdditionalDataService.create. Required roles: [user:custom:update, user_metadata_update]");
		try
		{
			return this.additionalDataService.create(dadaUsuari); 
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
	 * @see com.soffid.iam.base.service.AdditionalDataService#com.soffid.iam.base.api.UserData update(com.soffid.iam.base.api.UserData dadaUsuari)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.base.api.UserData update(
		final com.soffid.iam.base.api.UserData dadaUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:custom:update")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("user:metadata:update"))
			throw new SecurityException("Unable to execute AdditionalDataService.update. Required roles: [user:custom:update, user_metadata_update]");
		try
		{
			return this.additionalDataService.update(dadaUsuari); 
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
	 * @see com.soffid.iam.base.service.AdditionalDataService#com.soffid.iam.iga.api.CustomObjectType createCustomObjectType(com.soffid.iam.iga.api.CustomObjectType obj)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.CustomObjectType createCustomObjectType(
		final com.soffid.iam.iga.api.CustomObjectType obj)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("customObjectType:create"))
			throw new SecurityException("Unable to execute AdditionalDataService.createCustomObjectType. Required roles: [customObjectType:create]");
		try
		{
			return this.additionalDataService.createCustomObjectType(obj); 
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
	 * @see com.soffid.iam.base.service.AdditionalDataService#com.soffid.iam.iga.api.CustomObjectType findCustomObjectTypeByName(java.lang.String name)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.CustomObjectType findCustomObjectTypeByName(
		final java.lang.String name)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("customObjectType:query"))
			throw new SecurityException("Unable to execute AdditionalDataService.findCustomObjectTypeByName. Required roles: [customObjectType:query]");
		try
		{
			return this.additionalDataService.findCustomObjectTypeByName(name); 
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
	 * @see com.soffid.iam.base.service.AdditionalDataService#com.soffid.iam.iga.api.CustomObjectType updateCustomObjectType(com.soffid.iam.iga.api.CustomObjectType obj)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.CustomObjectType updateCustomObjectType(
		final com.soffid.iam.iga.api.CustomObjectType obj)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("customObjectType:update"))
			throw new SecurityException("Unable to execute AdditionalDataService.updateCustomObjectType. Required roles: [customObjectType:update]");
		try
		{
			return this.additionalDataService.updateCustomObjectType(obj); 
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
	 * @see com.soffid.iam.base.service.AdditionalDataService#com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.CustomObjectType> findCustomObjectType(com.soffid.zkdb.api.Query query)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.CustomObjectType> findCustomObjectType(
		final com.soffid.zkdb.api.Query query)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("customObjectType:query"))
			throw new SecurityException("Unable to execute AdditionalDataService.findCustomObjectType. Required roles: [customObjectType:query]");
		try
		{
			return this.additionalDataService.findCustomObjectType(query); 
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
	 * @see com.soffid.iam.base.service.AdditionalDataService#java.util.Collection<com.soffid.iam.base.api.DataType> findDataTypes(com.soffid.iam.base.api.MetadataScope scope)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.base.api.DataType> findDataTypes(
		final com.soffid.iam.base.api.MetadataScope scope)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.additionalDataService.findDataTypes(scope); 
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
	 * @see com.soffid.iam.base.service.AdditionalDataService#java.util.Collection<com.soffid.iam.base.api.DataType> findDataTypes2(com.soffid.iam.base.api.MetadataScope scope)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.base.api.DataType> findDataTypes2(
		final com.soffid.iam.base.api.MetadataScope scope)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.additionalDataService.findDataTypes2(scope); 
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
	 * @see com.soffid.iam.base.service.AdditionalDataService#java.util.Collection<com.soffid.iam.base.api.DataType> findDataTypesByObjectTypeAndName(java.lang.String objectType, java.lang.String codi)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.base.api.DataType> findDataTypesByObjectTypeAndName(
		final java.lang.String objectType, 
		final java.lang.String codi)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.additionalDataService.findDataTypesByObjectTypeAndName(objectType, codi); 
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
	 * @see com.soffid.iam.base.service.AdditionalDataService#java.util.Collection<com.soffid.iam.base.api.DataType> findDataTypesByObjectTypeAndName2(java.lang.String objectType, java.lang.String codi)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.base.api.DataType> findDataTypesByObjectTypeAndName2(
		final java.lang.String objectType, 
		final java.lang.String codi)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.additionalDataService.findDataTypesByObjectTypeAndName2(objectType, codi); 
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
	 * @see com.soffid.iam.base.service.AdditionalDataService#java.util.Collection<com.soffid.iam.base.api.DataType> findDataTypesByName(java.lang.String codi)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.base.api.DataType> findDataTypesByName(
		final java.lang.String codi)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.additionalDataService.findDataTypesByName(codi); 
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
	 * @see com.soffid.iam.base.service.AdditionalDataService#java.util.Collection<com.soffid.iam.base.api.DataType> findDataTypesByScopeAndName(com.soffid.iam.base.api.MetadataScope scope, java.lang.String codi)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.base.api.DataType> findDataTypesByScopeAndName(
		final com.soffid.iam.base.api.MetadataScope scope, 
		final java.lang.String codi)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.additionalDataService.findDataTypesByScopeAndName(scope, codi); 
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
	 * @see com.soffid.iam.base.service.AdditionalDataService#java.util.Collection<com.soffid.iam.base.api.DataType> getDataTypes()
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.base.api.DataType> getDataTypes()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.additionalDataService.getDataTypes(); 
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
	 * @see com.soffid.iam.base.service.AdditionalDataService#java.util.List<com.soffid.iam.iga.api.ExtensibleObjectRegister> findExtensibleObjectRegisters()
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<com.soffid.iam.iga.api.ExtensibleObjectRegister> findExtensibleObjectRegisters()
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.additionalDataService.findExtensibleObjectRegisters(); 
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
	 * @see com.soffid.iam.base.service.AdditionalDataService#java.util.List<com.soffid.iam.base.api.DataType> findSystemDataTypes(java.lang.String system)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<com.soffid.iam.base.api.DataType> findSystemDataTypes(
		final java.lang.String system)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("metadata:query"))
			throw new SecurityException("Unable to execute AdditionalDataService.findSystemDataTypes. Required roles: [metadata:query]");
		try
		{
			return this.additionalDataService.findSystemDataTypes(system); 
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
	 * @see com.soffid.iam.base.service.AdditionalDataService#java.util.List<com.soffid.iam.base.api.DataType> findSystemDataTypes2(java.lang.String system)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.List<com.soffid.iam.base.api.DataType> findSystemDataTypes2(
		final java.lang.String system)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("metadata:query"))
			throw new SecurityException("Unable to execute AdditionalDataService.findSystemDataTypes2. Required roles: [metadata:query]");
		try
		{
			return this.additionalDataService.findSystemDataTypes2(system); 
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
	 * @see com.soffid.iam.base.service.AdditionalDataService#void delete(com.soffid.iam.base.api.DataType tipusDada)
	 */
	@jakarta.annotation.security.PermitAll
	public void delete(
		final com.soffid.iam.base.api.DataType tipusDada)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("metadata:delete"))
			throw new SecurityException("Unable to execute AdditionalDataService.delete. Required roles: [metadata:delete]");
		try
		{
			this.additionalDataService.delete(tipusDada); 
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
	 * @see com.soffid.iam.base.service.AdditionalDataService#void delete(com.soffid.iam.base.api.UserData dadaUsuari)
	 */
	@jakarta.annotation.security.PermitAll
	public void delete(
		final com.soffid.iam.base.api.UserData dadaUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("user:custom:update")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("user:metadata:update"))
			throw new SecurityException("Unable to execute AdditionalDataService.delete. Required roles: [user:custom:update, user_metadata_update]");
		try
		{
			this.additionalDataService.delete(dadaUsuari); 
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
	 * @see com.soffid.iam.base.service.AdditionalDataService#void deleteCustomObjectType(com.soffid.iam.iga.api.CustomObjectType obj)
	 */
	@jakarta.annotation.security.PermitAll
	public void deleteCustomObjectType(
		final com.soffid.iam.iga.api.CustomObjectType obj)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("customObjectType:delete"))
			throw new SecurityException("Unable to execute AdditionalDataService.deleteCustomObjectType. Required roles: [customObjectType:delete]");
		try
		{
			this.additionalDataService.deleteCustomObjectType(obj); 
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
	 * @see com.soffid.iam.base.service.AdditionalDataService#void registerStandardObject(java.lang.String resourceName, com.soffid.iam.base.api.MetadataScope scope, boolean reset)
	 */
	@jakarta.annotation.security.PermitAll
	public void registerStandardObject(
		final java.lang.String resourceName, 
		final com.soffid.iam.base.api.MetadataScope scope, 
		final boolean reset)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("metadata:update"))
			throw new SecurityException("Unable to execute AdditionalDataService.registerStandardObject. Required roles: [metadata:update]");
		try
		{
			this.additionalDataService.registerStandardObject(resourceName, scope, reset); 
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

		this.additionalDataService = (com.soffid.iam.base.service.AdditionalDataService)
		getBeanFactory().getBean("com.soffid.iam.base.service.AdditionalDataService");
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
