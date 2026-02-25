//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.service.ejb;
/**
 * @see <code>com.soffid.iam.iga.service.MailListsService</code>,
 * @see <code>com.soffid.iam.iga.service.MailListsService</code>,
 */
@jakarta.ejb.Stateless(name="MailListsService", mappedName="openejb:/local/soffid.ejb.com.soffid.iam.iga.service.MailListsService")
@jakarta.ejb.Local(com.soffid.iam.iga.service.ejb.MailListsService.class)
@jakarta.ejb.TransactionManagement(value=jakarta.ejb.TransactionManagementType.CONTAINER)
@jakarta.ejb.TransactionAttribute(value=jakarta.ejb.TransactionAttributeType.SUPPORTS)
public class MailListsServiceBean extends org.springframework.ejb.support.AbstractStatelessSessionBean
  implements com.soffid.iam.iga.service.ejb.MailListsService
{
	private com.soffid.iam.iga.service.MailListsService mailListsService;

	/**
	 * @see com.soffid.iam.iga.service.MailListsService#com.soffid.iam.iga.api.ExternalName create(com.soffid.iam.iga.api.ExternalName correuExtern)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.ExternalName create(
		final com.soffid.iam.iga.api.ExternalName correuExtern)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("mail:create")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("mail:update"))
			throw new SecurityException("Unable to execute MailListsService.create. Required roles: [mail:create, mail_update]");
		try
		{
			return this.mailListsService.create(correuExtern); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.iga.service.MailListsService#com.soffid.iam.iga.api.ExternalName finExternalMailByEmail(java.lang.String adreca)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.ExternalName finExternalMailByEmail(
		final java.lang.String adreca)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("mail:query"))
			throw new SecurityException("Unable to execute MailListsService.finExternalMailByEmail. Required roles: [mail:query]");
		try
		{
			return this.mailListsService.finExternalMailByEmail(adreca); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.iga.service.MailListsService#com.soffid.iam.iga.api.MailDomain create(com.soffid.iam.iga.api.MailDomain dominiCorreu)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.MailDomain create(
		final com.soffid.iam.iga.api.MailDomain dominiCorreu)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("mail:create"))
			throw new SecurityException("Unable to execute MailListsService.create. Required roles: [mail:create]");
		try
		{
			return this.mailListsService.create(dominiCorreu); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.iga.service.MailListsService#com.soffid.iam.iga.api.MailDomain findMailDomainByName(java.lang.String codi)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.MailDomain findMailDomainByName(
		final java.lang.String codi)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("mail:query"))
			throw new SecurityException("Unable to execute MailListsService.findMailDomainByName. Required roles: [mail:query]");
		try
		{
			return this.mailListsService.findMailDomainByName(codi); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.iga.service.MailListsService#com.soffid.iam.iga.api.MailDomain update(com.soffid.iam.iga.api.MailDomain dominiCorreu)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.MailDomain update(
		final com.soffid.iam.iga.api.MailDomain dominiCorreu)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("mail:update"))
			throw new SecurityException("Unable to execute MailListsService.update. Required roles: [mail:update]");
		try
		{
			return this.mailListsService.update(dominiCorreu); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.iga.service.MailListsService#com.soffid.iam.iga.api.MailList create(com.soffid.iam.iga.api.MailList llistaCorreu)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.MailList create(
		final com.soffid.iam.iga.api.MailList llistaCorreu)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("mail:create")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("user:create")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("user:update"))
			throw new SecurityException("Unable to execute MailListsService.create. Required roles: [mail:create, user_create, user_update]");
		try
		{
			return this.mailListsService.create(llistaCorreu); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.iga.service.MailListsService#com.soffid.iam.iga.api.MailList findMailListByNameAndDomainName(java.lang.String nomLlistaCorreu, java.lang.String codiDomini)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.MailList findMailListByNameAndDomainName(
		final java.lang.String nomLlistaCorreu, 
		final java.lang.String codiDomini)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("mail:query")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("user:create")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("user:update"))
			throw new SecurityException("Unable to execute MailListsService.findMailListByNameAndDomainName. Required roles: [mail:query, user_create, user_update]");
		try
		{
			return this.mailListsService.findMailListByNameAndDomainName(nomLlistaCorreu, codiDomini); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.iga.service.MailListsService#com.soffid.iam.iga.api.MailList update(com.soffid.iam.iga.api.MailList llistaCorreu)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.MailList update(
		final com.soffid.iam.iga.api.MailList llistaCorreu)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("mail:delete")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("mail:update"))
			throw new SecurityException("Unable to execute MailListsService.update. Required roles: [mail:delete, mail_update]");
		try
		{
			return this.mailListsService.update(llistaCorreu); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.iga.service.MailListsService#com.soffid.iam.iga.api.MailListRelationship create(com.soffid.iam.iga.api.MailListRelationship relacioLlistaCorreu)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.MailListRelationship create(
		final com.soffid.iam.iga.api.MailListRelationship relacioLlistaCorreu)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("mail:create")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("mail:update"))
			throw new SecurityException("Unable to execute MailListsService.create. Required roles: [mail:create, mail_update]");
		try
		{
			return this.mailListsService.create(relacioLlistaCorreu); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.iga.service.MailListsService#com.soffid.iam.iga.api.MailListRelationship findRelationsMailListByNameAndBelongsMailListNameAndNameAndContainsMailListName(java.lang.String nomPertany, java.lang.String dominiCorreuPertany, java.lang.String nomConte, java.lang.String dominiCorreuConte)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.MailListRelationship findRelationsMailListByNameAndBelongsMailListNameAndNameAndContainsMailListName(
		final java.lang.String nomPertany, 
		final java.lang.String dominiCorreuPertany, 
		final java.lang.String nomConte, 
		final java.lang.String dominiCorreuConte)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("mail:query"))
			throw new SecurityException("Unable to execute MailListsService.findRelationsMailListByNameAndBelongsMailListNameAndNameAndContainsMailListName. Required roles: [mail:query]");
		try
		{
			return this.mailListsService.findRelationsMailListByNameAndBelongsMailListNameAndNameAndContainsMailListName(nomPertany, dominiCorreuPertany, nomConte, dominiCorreuConte); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.iga.service.MailListsService#com.soffid.iam.iga.api.MailListRoleMember subscribeRole(java.lang.String mailListName, java.lang.String mailListDomain, com.soffid.iam.iga.api.MailListRoleMember roleMember)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.MailListRoleMember subscribeRole(
		final java.lang.String mailListName, 
		final java.lang.String mailListDomain, 
		final com.soffid.iam.iga.api.MailListRoleMember roleMember)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("mail:create")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("mail:update"))
			throw new SecurityException("Unable to execute MailListsService.subscribeRole. Required roles: [mail:create, mail_update]");
		try
		{
			return this.mailListsService.subscribeRole(mailListName, mailListDomain, roleMember); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.iga.service.MailListsService#com.soffid.iam.iga.api.UserMailList create(com.soffid.iam.iga.api.UserMailList llistaCorreuUsuari)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.UserMailList create(
		final com.soffid.iam.iga.api.UserMailList llistaCorreuUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("mail:create")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("mail:update")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("user:create")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("user:delete")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("user:update"))
			throw new SecurityException("Unable to execute MailListsService.create. Required roles: [mail:create, mail_update, user_create, user_delete, user_update]");
		try
		{
			return this.mailListsService.create(llistaCorreuUsuari); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.iga.service.MailListsService#com.soffid.iam.iga.api.UserMailList findUserMailListByListNameAndDomainNameAndUserName(java.lang.String nomLlistaCorreu, java.lang.String codiDomini, java.lang.String codiUsuari)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.iga.api.UserMailList findUserMailListByListNameAndDomainNameAndUserName(
		final java.lang.String nomLlistaCorreu, 
		final java.lang.String codiDomini, 
		final java.lang.String codiUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("mail:query")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("user:create")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("user:delete")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("user:update"))
			throw new SecurityException("Unable to execute MailListsService.findUserMailListByListNameAndDomainNameAndUserName. Required roles: [mail:query, user_create, user_delete, user_update]");
		try
		{
			return this.mailListsService.findUserMailListByListNameAndDomainNameAndUserName(nomLlistaCorreu, codiDomini, codiUsuari); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.iga.service.MailListsService#com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.MailDomain> findMailDomains(com.soffid.zkdb.api.Query query)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.MailDomain> findMailDomains(
		final com.soffid.zkdb.api.Query query)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("mail:query"))
			throw new SecurityException("Unable to execute MailListsService.findMailDomains. Required roles: [mail:query]");
		try
		{
			return this.mailListsService.findMailDomains(query); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.iga.service.MailListsService#com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.MailList> findMailLists(com.soffid.zkdb.api.Query query)
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.MailList> findMailLists(
		final com.soffid.zkdb.api.Query query)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("mail:query"))
			throw new SecurityException("Unable to execute MailListsService.findMailLists. Required roles: [mail:query]");
		try
		{
			return this.mailListsService.findMailLists(query); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.iga.service.MailListsService#java.util.Collection<com.soffid.iam.iga.api.ExternalName> findExternalMailsByNameListAndDomainName(java.lang.String nomLlistaCorreu, java.lang.String codiDomini)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.iga.api.ExternalName> findExternalMailsByNameListAndDomainName(
		final java.lang.String nomLlistaCorreu, 
		final java.lang.String codiDomini)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("mail:query")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("user:update"))
			throw new SecurityException("Unable to execute MailListsService.findExternalMailsByNameListAndDomainName. Required roles: [mail:query, user_update]");
		try
		{
			return this.mailListsService.findExternalMailsByNameListAndDomainName(nomLlistaCorreu, codiDomini); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.iga.service.MailListsService#java.util.Collection<com.soffid.iam.iga.api.Group> findGroupMembers(java.lang.String nomLlistaCorreu, java.lang.String codiDomini)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.iga.api.Group> findGroupMembers(
		final java.lang.String nomLlistaCorreu, 
		final java.lang.String codiDomini)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("mail:query"))
			throw new SecurityException("Unable to execute MailListsService.findGroupMembers. Required roles: [mail:query]");
		try
		{
			return this.mailListsService.findGroupMembers(nomLlistaCorreu, codiDomini); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.iga.service.MailListsService#java.util.Collection<com.soffid.iam.iga.api.UserMailList> findUserMailListByUserName(java.lang.String codiUsuari)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.iga.api.UserMailList> findUserMailListByUserName(
		final java.lang.String codiUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("mail:query")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("user:delete"))
			throw new SecurityException("Unable to execute MailListsService.findUserMailListByUserName. Required roles: [mail:query, user_delete]");
		try
		{
			return this.mailListsService.findUserMailListByUserName(codiUsuari); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.iga.service.MailListsService#java.util.Collection<com.soffid.iam.iga.api.UserMailList> findUserMailListByListNameAndDomainName(java.lang.String nomLlistaCorreu, java.lang.String codiDomini)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.iga.api.UserMailList> findUserMailListByListNameAndDomainName(
		final java.lang.String nomLlistaCorreu, 
		final java.lang.String codiDomini)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("mail:query")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("user:update"))
			throw new SecurityException("Unable to execute MailListsService.findUserMailListByListNameAndDomainName. Required roles: [mail:query, user_update]");
		try
		{
			return this.mailListsService.findUserMailListByListNameAndDomainName(nomLlistaCorreu, codiDomini); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.iga.service.MailListsService#java.util.Collection<com.soffid.iam.iga.api.UserMailList> findUserMailListHistoryByUserName(java.lang.String codiUsuari)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.iga.api.UserMailList> findUserMailListHistoryByUserName(
		final java.lang.String codiUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("mail:query")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("user:delete"))
			throw new SecurityException("Unable to execute MailListsService.findUserMailListHistoryByUserName. Required roles: [mail:query, user_delete]");
		try
		{
			return this.mailListsService.findUserMailListHistoryByUserName(codiUsuari); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.iga.service.MailListsService#java.util.Collection<com.soffid.iam.iga.api.MailList> findMailListsByData(java.lang.String nom, java.lang.String domini, java.lang.String descripcio, java.lang.String membres)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.iga.api.MailList> findMailListsByData(
		final java.lang.String nom, 
		final java.lang.String domini, 
		final java.lang.String descripcio, 
		final java.lang.String membres)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("mail:query"))
			throw new SecurityException("Unable to execute MailListsService.findMailListsByData. Required roles: [mail:query]");
		try
		{
			return this.mailListsService.findMailListsByData(nom, domini, descripcio, membres); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.iga.service.MailListsService#java.util.Collection<com.soffid.iam.iga.api.MailListRelationship> findRelationsMailListByNameContainsMailListAndDomainName(java.lang.String nomLlistaCorreuConte, java.lang.String codiDomini)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.iga.api.MailListRelationship> findRelationsMailListByNameContainsMailListAndDomainName(
		final java.lang.String nomLlistaCorreuConte, 
		final java.lang.String codiDomini)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("mail:query")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("user:update"))
			throw new SecurityException("Unable to execute MailListsService.findRelationsMailListByNameContainsMailListAndDomainName. Required roles: [mail:query, user_update]");
		try
		{
			return this.mailListsService.findRelationsMailListByNameContainsMailListAndDomainName(nomLlistaCorreuConte, codiDomini); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.iga.service.MailListsService#java.util.Collection<com.soffid.iam.iga.api.MailListRelationship> findRelationsMailListByNameBelongsMailListAndDomainName(java.lang.String nomLlistaCorreuPertany, java.lang.String codiDomini)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.iga.api.MailListRelationship> findRelationsMailListByNameBelongsMailListAndDomainName(
		final java.lang.String nomLlistaCorreuPertany, 
		final java.lang.String codiDomini)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("mail:query")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("user:update"))
			throw new SecurityException("Unable to execute MailListsService.findRelationsMailListByNameBelongsMailListAndDomainName. Required roles: [mail:query, user_update]");
		try
		{
			return this.mailListsService.findRelationsMailListByNameBelongsMailListAndDomainName(nomLlistaCorreuPertany, codiDomini); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.iga.service.MailListsService#java.util.Collection<com.soffid.iam.iga.api.MailListRoleMember> findRoleMembers(java.lang.String nomLlistaCorreu, java.lang.String codiDomini)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.iga.api.MailListRoleMember> findRoleMembers(
		final java.lang.String nomLlistaCorreu, 
		final java.lang.String codiDomini)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("mail:query"))
			throw new SecurityException("Unable to execute MailListsService.findRoleMembers. Required roles: [mail:query]");
		try
		{
			return this.mailListsService.findRoleMembers(nomLlistaCorreu, codiDomini); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.iga.service.MailListsService#java.util.Collection<com.soffid.iam.base.api.User> findUsersByMailListNameAndDomainName(java.lang.String nomLlistaCorreu, java.lang.String codiDomini)
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.base.api.User> findUsersByMailListNameAndDomainName(
		final java.lang.String nomLlistaCorreu, 
		final java.lang.String codiDomini)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("mail:query")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("user:delete"))
			throw new SecurityException("Unable to execute MailListsService.findUsersByMailListNameAndDomainName. Required roles: [mail:query, user_delete]");
		try
		{
			return this.mailListsService.findUsersByMailListNameAndDomainName(nomLlistaCorreu, codiDomini); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.iga.service.MailListsService#java.util.Collection<com.soffid.iam.iga.api.MailDomain> getDomainMails()
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.iga.api.MailDomain> getDomainMails()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("mail:query"))
			throw new SecurityException("Unable to execute MailListsService.getDomainMails. Required roles: [mail:query]");
		try
		{
			return this.mailListsService.getDomainMails(); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.iga.service.MailListsService#java.util.Collection<com.soffid.iam.iga.api.MailList> getMailLists()
	 */
	@jakarta.annotation.security.PermitAll
	public java.util.Collection<com.soffid.iam.iga.api.MailList> getMailLists()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("mail:query"))
			throw new SecurityException("Unable to execute MailListsService.getMailLists. Required roles: [mail:query]");
		try
		{
			return this.mailListsService.getMailLists(); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.iga.service.MailListsService#void delete(com.soffid.iam.iga.api.ExternalName correuExtern)
	 */
	@jakarta.annotation.security.PermitAll
	public void delete(
		final com.soffid.iam.iga.api.ExternalName correuExtern)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("mail:delete")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("mail:update"))
			throw new SecurityException("Unable to execute MailListsService.delete. Required roles: [mail:delete, mail_update]");
		try
		{
			this.mailListsService.delete(correuExtern); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.iga.service.MailListsService#void delete(com.soffid.iam.iga.api.MailDomain dominiCorreu)
	 */
	@jakarta.annotation.security.PermitAll
	public void delete(
		final com.soffid.iam.iga.api.MailDomain dominiCorreu)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("mail:delete"))
			throw new SecurityException("Unable to execute MailListsService.delete. Required roles: [mail:delete]");
		try
		{
			this.mailListsService.delete(dominiCorreu); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.iga.service.MailListsService#void delete(com.soffid.iam.iga.api.MailList llistaCorreu)
	 */
	@jakarta.annotation.security.PermitAll
	public void delete(
		final com.soffid.iam.iga.api.MailList llistaCorreu)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("mail:delete")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("user:delete"))
			throw new SecurityException("Unable to execute MailListsService.delete. Required roles: [mail:delete, user_delete]");
		try
		{
			this.mailListsService.delete(llistaCorreu); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.iga.service.MailListsService#void delete(com.soffid.iam.iga.api.MailListRelationship relacioLlistaCorreu)
	 */
	@jakarta.annotation.security.PermitAll
	public void delete(
		final com.soffid.iam.iga.api.MailListRelationship relacioLlistaCorreu)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("mail:delete")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("mail:update"))
			throw new SecurityException("Unable to execute MailListsService.delete. Required roles: [mail:delete, mail_update]");
		try
		{
			this.mailListsService.delete(relacioLlistaCorreu); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.iga.service.MailListsService#void deleteUserMailList(com.soffid.iam.iga.api.UserMailList llistaCorreuUsuari)
	 */
	@jakarta.annotation.security.PermitAll
	public void deleteUserMailList(
		final com.soffid.iam.iga.api.UserMailList llistaCorreuUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("mail:delete")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("mail:update")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("user:delete"))
			throw new SecurityException("Unable to execute MailListsService.deleteUserMailList. Required roles: [mail:delete, mail_update, user_delete]");
		try
		{
			this.mailListsService.deleteUserMailList(llistaCorreuUsuari); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.iga.service.MailListsService#void deleteAtomic(com.soffid.iam.iga.api.ExternalName correuExtern)
	 */
	@jakarta.annotation.security.PermitAll
	public void deleteAtomic(
		final com.soffid.iam.iga.api.ExternalName correuExtern)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("mail:delete"))
			throw new SecurityException("Unable to execute MailListsService.deleteAtomic. Required roles: [mail:delete]");
		try
		{
			this.mailListsService.deleteAtomic(correuExtern); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.iga.service.MailListsService#void deleteAtomic(com.soffid.iam.iga.api.MailListRelationship relacioLlistaCorreu)
	 */
	@jakarta.annotation.security.PermitAll
	public void deleteAtomic(
		final com.soffid.iam.iga.api.MailListRelationship relacioLlistaCorreu)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("mail:delete"))
			throw new SecurityException("Unable to execute MailListsService.deleteAtomic. Required roles: [mail:delete]");
		try
		{
			this.mailListsService.deleteAtomic(relacioLlistaCorreu); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.iga.service.MailListsService#void deleteAtomic(com.soffid.iam.iga.api.UserMailList llistaCorreuUsuari)
	 */
	@jakarta.annotation.security.PermitAll
	public void deleteAtomic(
		final com.soffid.iam.iga.api.UserMailList llistaCorreuUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("mail:delete"))
			throw new SecurityException("Unable to execute MailListsService.deleteAtomic. Required roles: [mail:delete]");
		try
		{
			this.mailListsService.deleteAtomic(llistaCorreuUsuari); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.iga.service.MailListsService#void subscribeGroup(java.lang.String mailListName, java.lang.String mailListDomain, java.lang.String groupName)
	 */
	@jakarta.annotation.security.PermitAll
	public void subscribeGroup(
		final java.lang.String mailListName, 
		final java.lang.String mailListDomain, 
		final java.lang.String groupName)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("mail:create")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("mail:update"))
			throw new SecurityException("Unable to execute MailListsService.subscribeGroup. Required roles: [mail:create, mail_update]");
		try
		{
			this.mailListsService.subscribeGroup(mailListName, mailListDomain, groupName); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.iga.service.MailListsService#void unsubscribeGroup(java.lang.String mailListName, java.lang.String mailListDomain, java.lang.String groupName)
	 */
	@jakarta.annotation.security.PermitAll
	public void unsubscribeGroup(
		final java.lang.String mailListName, 
		final java.lang.String mailListDomain, 
		final java.lang.String groupName)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("mail:create")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("mail:update"))
			throw new SecurityException("Unable to execute MailListsService.unsubscribeGroup. Required roles: [mail:create, mail_update]");
		try
		{
			this.mailListsService.unsubscribeGroup(mailListName, mailListDomain, groupName); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.iga.service.MailListsService#void unsubscribeRole(java.lang.String mailListName, java.lang.String mailListDomain, com.soffid.iam.iga.api.MailListRoleMember roleMember)
	 */
	@jakarta.annotation.security.PermitAll
	public void unsubscribeRole(
		final java.lang.String mailListName, 
		final java.lang.String mailListDomain, 
		final com.soffid.iam.iga.api.MailListRoleMember roleMember)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		if (! com.soffid.iam.utils.Security.isUserInRole("mail:create")&&
			 ! com.soffid.iam.utils.Security.isUserInRole("mail:update"))
			throw new SecurityException("Unable to execute MailListsService.unsubscribeRole. Required roles: [mail:create, mail_update]");
		try
		{
			this.mailListsService.unsubscribeRole(mailListName, mailListDomain, roleMember); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
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

		this.mailListsService = (com.soffid.iam.iga.service.MailListsService)
		getBeanFactory().getBean("com.soffid.iam.iga.service.MailListsService");
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
