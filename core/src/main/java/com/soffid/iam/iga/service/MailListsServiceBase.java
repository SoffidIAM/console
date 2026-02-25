//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p> 
 * Spring Service base class for <code>com.soffid.iam.iga.service.MailListsService</code>,
 * provides access to all services and entities referenced by this service. 
 * </p>
 * 
 * see com.soffid.iam.iga.service.MailListsService
 */
public abstract class MailListsServiceBase
	implements com.soffid.iam.iga.service.MailListsService
 {
	private com.soffid.iam.impl.service.AsyncRunnerService asyncRunnerService;

	/**
	 * Sets reference to <code>asyncRunnerService</code>.
	 */
	public void setAsyncRunnerService (com.soffid.iam.impl.service.AsyncRunnerService asyncRunnerService) {
		this.asyncRunnerService = asyncRunnerService;
	}

	/**
	 * Gets reference to <code>asyncRunnerService</code>.
	 */
	public com.soffid.iam.impl.service.AsyncRunnerService getAsyncRunnerService () {
		return asyncRunnerService;
	}

	private com.soffid.iam.service.impl.AttributeValidationService attributeValidationService;

	/**
	 * Sets reference to <code>attributeValidationService</code>.
	 */
	public void setAttributeValidationService (com.soffid.iam.service.impl.AttributeValidationService attributeValidationService) {
		this.attributeValidationService = attributeValidationService;
	}

	/**
	 * Gets reference to <code>attributeValidationService</code>.
	 */
	public com.soffid.iam.service.impl.AttributeValidationService getAttributeValidationService () {
		return attributeValidationService;
	}

	private com.soffid.iam.rc.model.AuditEntityDao auditEntityDao;

	/**
	 * Sets reference to <code>auditEntityDao</code>.
	 */
	public void setAuditEntityDao (com.soffid.iam.rc.model.AuditEntityDao auditEntityDao) {
		this.auditEntityDao = auditEntityDao;
	}

	/**
	 * Gets reference to <code>auditEntityDao</code>.
	 */
	public com.soffid.iam.rc.model.AuditEntityDao getAuditEntityDao () {
		return auditEntityDao;
	}

	private com.soffid.iam.iga.model.DomainValueEntityDao domainValueEntityDao;

	/**
	 * Sets reference to <code>domainValueEntityDao</code>.
	 */
	public void setDomainValueEntityDao (com.soffid.iam.iga.model.DomainValueEntityDao domainValueEntityDao) {
		this.domainValueEntityDao = domainValueEntityDao;
	}

	/**
	 * Gets reference to <code>domainValueEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.DomainValueEntityDao getDomainValueEntityDao () {
		return domainValueEntityDao;
	}

	private com.soffid.iam.iga.model.ExternalNameEntityDao externalNameEntityDao;

	/**
	 * Sets reference to <code>externalNameEntityDao</code>.
	 */
	public void setExternalNameEntityDao (com.soffid.iam.iga.model.ExternalNameEntityDao externalNameEntityDao) {
		this.externalNameEntityDao = externalNameEntityDao;
	}

	/**
	 * Gets reference to <code>externalNameEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.ExternalNameEntityDao getExternalNameEntityDao () {
		return externalNameEntityDao;
	}

	private com.soffid.iam.iga.model.GroupEntityDao groupEntityDao;

	/**
	 * Sets reference to <code>groupEntityDao</code>.
	 */
	public void setGroupEntityDao (com.soffid.iam.iga.model.GroupEntityDao groupEntityDao) {
		this.groupEntityDao = groupEntityDao;
	}

	/**
	 * Gets reference to <code>groupEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.GroupEntityDao getGroupEntityDao () {
		return groupEntityDao;
	}

	private com.soffid.iam.iga.model.InformationSystemEntityDao informationSystemEntityDao;

	/**
	 * Sets reference to <code>informationSystemEntityDao</code>.
	 */
	public void setInformationSystemEntityDao (com.soffid.iam.iga.model.InformationSystemEntityDao informationSystemEntityDao) {
		this.informationSystemEntityDao = informationSystemEntityDao;
	}

	/**
	 * Gets reference to <code>informationSystemEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.InformationSystemEntityDao getInformationSystemEntityDao () {
		return informationSystemEntityDao;
	}

	private com.soffid.iam.iga.model.MailDomainEntityDao mailDomainEntityDao;

	/**
	 * Sets reference to <code>mailDomainEntityDao</code>.
	 */
	public void setMailDomainEntityDao (com.soffid.iam.iga.model.MailDomainEntityDao mailDomainEntityDao) {
		this.mailDomainEntityDao = mailDomainEntityDao;
	}

	/**
	 * Gets reference to <code>mailDomainEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.MailDomainEntityDao getMailDomainEntityDao () {
		return mailDomainEntityDao;
	}

	private com.soffid.iam.iga.model.MailListAttributeEntityDao mailListAttributeEntityDao;

	/**
	 * Sets reference to <code>mailListAttributeEntityDao</code>.
	 */
	public void setMailListAttributeEntityDao (com.soffid.iam.iga.model.MailListAttributeEntityDao mailListAttributeEntityDao) {
		this.mailListAttributeEntityDao = mailListAttributeEntityDao;
	}

	/**
	 * Gets reference to <code>mailListAttributeEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.MailListAttributeEntityDao getMailListAttributeEntityDao () {
		return mailListAttributeEntityDao;
	}

	private com.soffid.iam.iga.model.MailListContainerEntityDao mailListContainerEntityDao;

	/**
	 * Sets reference to <code>mailListContainerEntityDao</code>.
	 */
	public void setMailListContainerEntityDao (com.soffid.iam.iga.model.MailListContainerEntityDao mailListContainerEntityDao) {
		this.mailListContainerEntityDao = mailListContainerEntityDao;
	}

	/**
	 * Gets reference to <code>mailListContainerEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.MailListContainerEntityDao getMailListContainerEntityDao () {
		return mailListContainerEntityDao;
	}

	private com.soffid.iam.iga.model.MailListEntityDao mailListEntityDao;

	/**
	 * Sets reference to <code>mailListEntityDao</code>.
	 */
	public void setMailListEntityDao (com.soffid.iam.iga.model.MailListEntityDao mailListEntityDao) {
		this.mailListEntityDao = mailListEntityDao;
	}

	/**
	 * Gets reference to <code>mailListEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.MailListEntityDao getMailListEntityDao () {
		return mailListEntityDao;
	}

	private com.soffid.iam.iga.model.MailListGroupMemberEntityDao mailListGroupMemberEntityDao;

	/**
	 * Sets reference to <code>mailListGroupMemberEntityDao</code>.
	 */
	public void setMailListGroupMemberEntityDao (com.soffid.iam.iga.model.MailListGroupMemberEntityDao mailListGroupMemberEntityDao) {
		this.mailListGroupMemberEntityDao = mailListGroupMemberEntityDao;
	}

	/**
	 * Gets reference to <code>mailListGroupMemberEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.MailListGroupMemberEntityDao getMailListGroupMemberEntityDao () {
		return mailListGroupMemberEntityDao;
	}

	private com.soffid.iam.iga.model.MailListRoleMemberEntityDao mailListRoleMemberEntityDao;

	/**
	 * Sets reference to <code>mailListRoleMemberEntityDao</code>.
	 */
	public void setMailListRoleMemberEntityDao (com.soffid.iam.iga.model.MailListRoleMemberEntityDao mailListRoleMemberEntityDao) {
		this.mailListRoleMemberEntityDao = mailListRoleMemberEntityDao;
	}

	/**
	 * Gets reference to <code>mailListRoleMemberEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.MailListRoleMemberEntityDao getMailListRoleMemberEntityDao () {
		return mailListRoleMemberEntityDao;
	}

	private com.soffid.iam.iga.model.MetaDataEntityDao metaDataEntityDao;

	/**
	 * Sets reference to <code>metaDataEntityDao</code>.
	 */
	public void setMetaDataEntityDao (com.soffid.iam.iga.model.MetaDataEntityDao metaDataEntityDao) {
		this.metaDataEntityDao = metaDataEntityDao;
	}

	/**
	 * Gets reference to <code>metaDataEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.MetaDataEntityDao getMetaDataEntityDao () {
		return metaDataEntityDao;
	}

	private com.soffid.iam.iga.model.RoleEntityDao roleEntityDao;

	/**
	 * Sets reference to <code>roleEntityDao</code>.
	 */
	public void setRoleEntityDao (com.soffid.iam.iga.model.RoleEntityDao roleEntityDao) {
		this.roleEntityDao = roleEntityDao;
	}

	/**
	 * Gets reference to <code>roleEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.RoleEntityDao getRoleEntityDao () {
		return roleEntityDao;
	}

	private com.soffid.iam.base.model.UserEntityDao userEntityDao;

	/**
	 * Sets reference to <code>userEntityDao</code>.
	 */
	public void setUserEntityDao (com.soffid.iam.base.model.UserEntityDao userEntityDao) {
		this.userEntityDao = userEntityDao;
	}

	/**
	 * Gets reference to <code>userEntityDao</code>.
	 */
	public com.soffid.iam.base.model.UserEntityDao getUserEntityDao () {
		return userEntityDao;
	}

	private com.soffid.iam.iga.model.UserMailEntityDao userMailEntityDao;

	/**
	 * Sets reference to <code>userMailEntityDao</code>.
	 */
	public void setUserMailEntityDao (com.soffid.iam.iga.model.UserMailEntityDao userMailEntityDao) {
		this.userMailEntityDao = userMailEntityDao;
	}

	/**
	 * Gets reference to <code>userMailEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.UserMailEntityDao getUserMailEntityDao () {
		return userMailEntityDao;
	}


	/**
	 * @see com.soffid.iam.iga.service.MailListsService#	 * @see com.soffid.iam.iga.service.MailListsService#com.soffid.iam.iga.api.ExternalName create(com.soffid.iam.iga.api.ExternalName correuExtern)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.ExternalName create(
		final com.soffid.iam.iga.api.ExternalName correuExtern)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (correuExtern == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.ExternalName com.soffid.iam.iga.service.MailListsService.create(com.soffid.iam.iga.api.ExternalName correuExtern) - correuExtern cannot be null");
		}
		if (correuExtern.getEmail() == null || correuExtern.getEmail().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.ExternalName com.soffid.iam.iga.service.MailListsService.create(com.soffid.iam.iga.api.ExternalName correuExtern) - correuExtern.email cannot be null");
		}
		if (correuExtern.getMailListName() == null || correuExtern.getMailListName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.ExternalName com.soffid.iam.iga.service.MailListsService.create(com.soffid.iam.iga.api.ExternalName correuExtern) - correuExtern.mailListName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreate(correuExtern)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.ExternalName) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.MailListsService.class).
			warn ("Error on MailListsService.create", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on MailListsService.create", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.ExternalName handleCreate(com.soffid.iam.iga.api.ExternalName correuExtern) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.MailListsService#	 * @see com.soffid.iam.iga.service.MailListsService#com.soffid.iam.iga.api.ExternalName finExternalMailByEmail(java.lang.String adreca)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.ExternalName finExternalMailByEmail(
		final java.lang.String adreca)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (adreca == null || adreca.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.ExternalName com.soffid.iam.iga.service.MailListsService.finExternalMailByEmail(java.lang.String adreca) - adreca cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFinExternalMailByEmail(adreca)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.ExternalName) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.MailListsService.class).
			warn ("Error on MailListsService.finExternalMailByEmail", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on MailListsService.finExternalMailByEmail", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.ExternalName handleFinExternalMailByEmail(java.lang.String adreca) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.MailListsService#	 * @see com.soffid.iam.iga.service.MailListsService#com.soffid.iam.iga.api.MailDomain create(com.soffid.iam.iga.api.MailDomain dominiCorreu)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.MailDomain create(
		final com.soffid.iam.iga.api.MailDomain dominiCorreu)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (dominiCorreu == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.MailDomain com.soffid.iam.iga.service.MailListsService.create(com.soffid.iam.iga.api.MailDomain dominiCorreu) - dominiCorreu cannot be null");
		}
		if (dominiCorreu.getName() == null || dominiCorreu.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.MailDomain com.soffid.iam.iga.service.MailListsService.create(com.soffid.iam.iga.api.MailDomain dominiCorreu) - dominiCorreu.name cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreate(dominiCorreu)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.MailDomain) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.MailListsService.class).
			warn ("Error on MailListsService.create", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on MailListsService.create", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.MailDomain handleCreate(com.soffid.iam.iga.api.MailDomain dominiCorreu) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.MailListsService#	 * @see com.soffid.iam.iga.service.MailListsService#com.soffid.iam.iga.api.MailDomain findMailDomainByName(java.lang.String codi)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.MailDomain findMailDomainByName(
		final java.lang.String codi)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codi == null || codi.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.MailDomain com.soffid.iam.iga.service.MailListsService.findMailDomainByName(java.lang.String codi) - codi cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindMailDomainByName(codi)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.MailDomain) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.MailListsService.class).
			warn ("Error on MailListsService.findMailDomainByName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on MailListsService.findMailDomainByName", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.MailDomain handleFindMailDomainByName(java.lang.String codi) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.MailListsService#	 * @see com.soffid.iam.iga.service.MailListsService#com.soffid.iam.iga.api.MailDomain update(com.soffid.iam.iga.api.MailDomain dominiCorreu)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.MailDomain update(
		final com.soffid.iam.iga.api.MailDomain dominiCorreu)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (dominiCorreu == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.MailDomain com.soffid.iam.iga.service.MailListsService.update(com.soffid.iam.iga.api.MailDomain dominiCorreu) - dominiCorreu cannot be null");
		}
		if (dominiCorreu.getName() == null || dominiCorreu.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.MailDomain com.soffid.iam.iga.service.MailListsService.update(com.soffid.iam.iga.api.MailDomain dominiCorreu) - dominiCorreu.name cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleUpdate(dominiCorreu)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.MailDomain) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.MailListsService.class).
			warn ("Error on MailListsService.update", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on MailListsService.update", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.MailDomain handleUpdate(com.soffid.iam.iga.api.MailDomain dominiCorreu) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.MailListsService#	 * @see com.soffid.iam.iga.service.MailListsService#com.soffid.iam.iga.api.MailList create(com.soffid.iam.iga.api.MailList llistaCorreu)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.MailList create(
		final com.soffid.iam.iga.api.MailList llistaCorreu)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (llistaCorreu == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.MailList com.soffid.iam.iga.service.MailListsService.create(com.soffid.iam.iga.api.MailList llistaCorreu) - llistaCorreu cannot be null");
		}
		if (llistaCorreu.getName() == null || llistaCorreu.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.MailList com.soffid.iam.iga.service.MailListsService.create(com.soffid.iam.iga.api.MailList llistaCorreu) - llistaCorreu.name cannot be null");
		}
		if (llistaCorreu.getDomainName() == null || llistaCorreu.getDomainName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.MailList com.soffid.iam.iga.service.MailListsService.create(com.soffid.iam.iga.api.MailList llistaCorreu) - llistaCorreu.domainName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreate(llistaCorreu)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.MailList) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.MailListsService.class).
			warn ("Error on MailListsService.create", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on MailListsService.create", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.MailList handleCreate(com.soffid.iam.iga.api.MailList llistaCorreu) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.MailListsService#	 * @see com.soffid.iam.iga.service.MailListsService#com.soffid.iam.iga.api.MailList findMailListByNameAndDomainName(java.lang.String nomLlistaCorreu, java.lang.String codiDomini)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.MailList findMailListByNameAndDomainName(
		final java.lang.String nomLlistaCorreu, 
		final java.lang.String codiDomini)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (nomLlistaCorreu == null || nomLlistaCorreu.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.MailList com.soffid.iam.iga.service.MailListsService.findMailListByNameAndDomainName(java.lang.String nomLlistaCorreu, java.lang.String codiDomini) - nomLlistaCorreu cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindMailListByNameAndDomainName(nomLlistaCorreu, codiDomini)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.MailList) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.MailListsService.class).
			warn ("Error on MailListsService.findMailListByNameAndDomainName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on MailListsService.findMailListByNameAndDomainName", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.MailList handleFindMailListByNameAndDomainName(java.lang.String nomLlistaCorreu, java.lang.String codiDomini) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.MailListsService#	 * @see com.soffid.iam.iga.service.MailListsService#com.soffid.iam.iga.api.MailList update(com.soffid.iam.iga.api.MailList llistaCorreu)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.MailList update(
		final com.soffid.iam.iga.api.MailList llistaCorreu)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (llistaCorreu == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.MailList com.soffid.iam.iga.service.MailListsService.update(com.soffid.iam.iga.api.MailList llistaCorreu) - llistaCorreu cannot be null");
		}
		if (llistaCorreu.getName() == null || llistaCorreu.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.MailList com.soffid.iam.iga.service.MailListsService.update(com.soffid.iam.iga.api.MailList llistaCorreu) - llistaCorreu.name cannot be null");
		}
		if (llistaCorreu.getDomainName() == null || llistaCorreu.getDomainName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.MailList com.soffid.iam.iga.service.MailListsService.update(com.soffid.iam.iga.api.MailList llistaCorreu) - llistaCorreu.domainName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleUpdate(llistaCorreu)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.MailList) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.MailListsService.class).
			warn ("Error on MailListsService.update", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on MailListsService.update", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.MailList handleUpdate(com.soffid.iam.iga.api.MailList llistaCorreu) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.MailListsService#	 * @see com.soffid.iam.iga.service.MailListsService#com.soffid.iam.iga.api.MailListRelationship create(com.soffid.iam.iga.api.MailListRelationship relacioLlistaCorreu)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.MailListRelationship create(
		final com.soffid.iam.iga.api.MailListRelationship relacioLlistaCorreu)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (relacioLlistaCorreu == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.MailListRelationship com.soffid.iam.iga.service.MailListsService.create(com.soffid.iam.iga.api.MailListRelationship relacioLlistaCorreu) - relacioLlistaCorreu cannot be null");
		}
		if (relacioLlistaCorreu.getMailListNameBelong() == null || relacioLlistaCorreu.getMailListNameBelong().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.MailListRelationship com.soffid.iam.iga.service.MailListsService.create(com.soffid.iam.iga.api.MailListRelationship relacioLlistaCorreu) - relacioLlistaCorreu.mailListNameBelong cannot be null");
		}
		if (relacioLlistaCorreu.getMailListNameIncluded() == null || relacioLlistaCorreu.getMailListNameIncluded().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.MailListRelationship com.soffid.iam.iga.service.MailListsService.create(com.soffid.iam.iga.api.MailListRelationship relacioLlistaCorreu) - relacioLlistaCorreu.mailListNameIncluded cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreate(relacioLlistaCorreu)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.MailListRelationship) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.MailListsService.class).
			warn ("Error on MailListsService.create", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on MailListsService.create", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.MailListRelationship handleCreate(com.soffid.iam.iga.api.MailListRelationship relacioLlistaCorreu) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.MailListsService#	 * @see com.soffid.iam.iga.service.MailListsService#com.soffid.iam.iga.api.MailListRelationship findRelationsMailListByNameAndBelongsMailListNameAndNameAndContainsMailListName(java.lang.String nomPertany, java.lang.String dominiCorreuPertany, java.lang.String nomConte, java.lang.String dominiCorreuConte)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.MailListRelationship findRelationsMailListByNameAndBelongsMailListNameAndNameAndContainsMailListName(
		final java.lang.String nomPertany, 
		final java.lang.String dominiCorreuPertany, 
		final java.lang.String nomConte, 
		final java.lang.String dominiCorreuConte)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (nomPertany == null || nomPertany.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.MailListRelationship com.soffid.iam.iga.service.MailListsService.findRelationsMailListByNameAndBelongsMailListNameAndNameAndContainsMailListName(java.lang.String nomPertany, java.lang.String dominiCorreuPertany, java.lang.String nomConte, java.lang.String dominiCorreuConte) - nomPertany cannot be null");
		}
		if (nomConte == null || nomConte.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.MailListRelationship com.soffid.iam.iga.service.MailListsService.findRelationsMailListByNameAndBelongsMailListNameAndNameAndContainsMailListName(java.lang.String nomPertany, java.lang.String dominiCorreuPertany, java.lang.String nomConte, java.lang.String dominiCorreuConte) - nomConte cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindRelationsMailListByNameAndBelongsMailListNameAndNameAndContainsMailListName(nomPertany, dominiCorreuPertany, nomConte, dominiCorreuConte)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.MailListRelationship) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.MailListsService.class).
			warn ("Error on MailListsService.findRelationsMailListByNameAndBelongsMailListNameAndNameAndContainsMailListName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on MailListsService.findRelationsMailListByNameAndBelongsMailListNameAndNameAndContainsMailListName", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.MailListRelationship handleFindRelationsMailListByNameAndBelongsMailListNameAndNameAndContainsMailListName(java.lang.String nomPertany, java.lang.String dominiCorreuPertany, java.lang.String nomConte, java.lang.String dominiCorreuConte) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.MailListsService#	 * @see com.soffid.iam.iga.service.MailListsService#com.soffid.iam.iga.api.MailListRoleMember subscribeRole(java.lang.String mailListName, java.lang.String mailListDomain, com.soffid.iam.iga.api.MailListRoleMember roleMember)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.MailListRoleMember subscribeRole(
		final java.lang.String mailListName, 
		final java.lang.String mailListDomain, 
		final com.soffid.iam.iga.api.MailListRoleMember roleMember)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (mailListName == null || mailListName.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.MailListRoleMember com.soffid.iam.iga.service.MailListsService.subscribeRole(java.lang.String mailListName, java.lang.String mailListDomain, com.soffid.iam.iga.api.MailListRoleMember roleMember) - mailListName cannot be null");
		}
		if (mailListDomain == null || mailListDomain.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.MailListRoleMember com.soffid.iam.iga.service.MailListsService.subscribeRole(java.lang.String mailListName, java.lang.String mailListDomain, com.soffid.iam.iga.api.MailListRoleMember roleMember) - mailListDomain cannot be null");
		}
		if (roleMember == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.MailListRoleMember com.soffid.iam.iga.service.MailListsService.subscribeRole(java.lang.String mailListName, java.lang.String mailListDomain, com.soffid.iam.iga.api.MailListRoleMember roleMember) - roleMember cannot be null");
		}
		if (roleMember.getRoleName() == null || roleMember.getRoleName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.MailListRoleMember com.soffid.iam.iga.service.MailListsService.subscribeRole(java.lang.String mailListName, java.lang.String mailListDomain, com.soffid.iam.iga.api.MailListRoleMember roleMember) - roleMember.roleName cannot be null");
		}
		if (roleMember.getDispatcherName() == null || roleMember.getDispatcherName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.MailListRoleMember com.soffid.iam.iga.service.MailListsService.subscribeRole(java.lang.String mailListName, java.lang.String mailListDomain, com.soffid.iam.iga.api.MailListRoleMember roleMember) - roleMember.dispatcherName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleSubscribeRole(mailListName, mailListDomain, roleMember)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.MailListRoleMember) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.MailListsService.class).
			warn ("Error on MailListsService.subscribeRole", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on MailListsService.subscribeRole", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.MailListRoleMember handleSubscribeRole(java.lang.String mailListName, java.lang.String mailListDomain, com.soffid.iam.iga.api.MailListRoleMember roleMember) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.MailListsService#	 * @see com.soffid.iam.iga.service.MailListsService#com.soffid.iam.iga.api.UserMailList create(com.soffid.iam.iga.api.UserMailList llistaCorreuUsuari)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.UserMailList create(
		final com.soffid.iam.iga.api.UserMailList llistaCorreuUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (llistaCorreuUsuari == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.UserMailList com.soffid.iam.iga.service.MailListsService.create(com.soffid.iam.iga.api.UserMailList llistaCorreuUsuari) - llistaCorreuUsuari cannot be null");
		}
		if (llistaCorreuUsuari.getMailListName() == null || llistaCorreuUsuari.getMailListName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.UserMailList com.soffid.iam.iga.service.MailListsService.create(com.soffid.iam.iga.api.UserMailList llistaCorreuUsuari) - llistaCorreuUsuari.mailListName cannot be null");
		}
		if (llistaCorreuUsuari.getUserCode() == null || llistaCorreuUsuari.getUserCode().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.UserMailList com.soffid.iam.iga.service.MailListsService.create(com.soffid.iam.iga.api.UserMailList llistaCorreuUsuari) - llistaCorreuUsuari.userCode cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreate(llistaCorreuUsuari)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.UserMailList) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.MailListsService.class).
			warn ("Error on MailListsService.create", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on MailListsService.create", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.UserMailList handleCreate(com.soffid.iam.iga.api.UserMailList llistaCorreuUsuari) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.MailListsService#	 * @see com.soffid.iam.iga.service.MailListsService#com.soffid.iam.iga.api.UserMailList findUserMailListByListNameAndDomainNameAndUserName(java.lang.String nomLlistaCorreu, java.lang.String codiDomini, java.lang.String codiUsuari)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.UserMailList findUserMailListByListNameAndDomainNameAndUserName(
		final java.lang.String nomLlistaCorreu, 
		final java.lang.String codiDomini, 
		final java.lang.String codiUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (nomLlistaCorreu == null || nomLlistaCorreu.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.UserMailList com.soffid.iam.iga.service.MailListsService.findUserMailListByListNameAndDomainNameAndUserName(java.lang.String nomLlistaCorreu, java.lang.String codiDomini, java.lang.String codiUsuari) - nomLlistaCorreu cannot be null");
		}
		if (codiUsuari == null || codiUsuari.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.UserMailList com.soffid.iam.iga.service.MailListsService.findUserMailListByListNameAndDomainNameAndUserName(java.lang.String nomLlistaCorreu, java.lang.String codiDomini, java.lang.String codiUsuari) - codiUsuari cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindUserMailListByListNameAndDomainNameAndUserName(nomLlistaCorreu, codiDomini, codiUsuari)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.UserMailList) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.MailListsService.class).
			warn ("Error on MailListsService.findUserMailListByListNameAndDomainNameAndUserName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on MailListsService.findUserMailListByListNameAndDomainNameAndUserName", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.UserMailList handleFindUserMailListByListNameAndDomainNameAndUserName(java.lang.String nomLlistaCorreu, java.lang.String codiDomini, java.lang.String codiUsuari) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.MailListsService#	 * @see com.soffid.iam.iga.service.MailListsService#com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.MailDomain> findMailDomains(com.soffid.zkdb.api.Query query)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.MailDomain> findMailDomains(
		final com.soffid.zkdb.api.Query query)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (query == null) {
			throw new IllegalArgumentException("com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.MailDomain> com.soffid.iam.iga.service.MailListsService.findMailDomains(com.soffid.zkdb.api.Query query) - query cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindMailDomains(query)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.MailDomain>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.MailListsService.class).
			warn ("Error on MailListsService.findMailDomains", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on MailListsService.findMailDomains", (Throwable) __r[1]);
	}

	protected abstract com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.MailDomain> handleFindMailDomains(com.soffid.zkdb.api.Query query) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.MailListsService#	 * @see com.soffid.iam.iga.service.MailListsService#com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.MailList> findMailLists(com.soffid.zkdb.api.Query query)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.MailList> findMailLists(
		final com.soffid.zkdb.api.Query query)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (query == null) {
			throw new IllegalArgumentException("com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.MailList> com.soffid.iam.iga.service.MailListsService.findMailLists(com.soffid.zkdb.api.Query query) - query cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindMailLists(query)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.MailList>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.MailListsService.class).
			warn ("Error on MailListsService.findMailLists", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on MailListsService.findMailLists", (Throwable) __r[1]);
	}

	protected abstract com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.MailList> handleFindMailLists(com.soffid.zkdb.api.Query query) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.MailListsService#	 * @see com.soffid.iam.iga.service.MailListsService#java.util.Collection<com.soffid.iam.iga.api.ExternalName> findExternalMailsByNameListAndDomainName(java.lang.String nomLlistaCorreu, java.lang.String codiDomini)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.ExternalName> findExternalMailsByNameListAndDomainName(
		final java.lang.String nomLlistaCorreu, 
		final java.lang.String codiDomini)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (nomLlistaCorreu == null || nomLlistaCorreu.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.ExternalName> com.soffid.iam.iga.service.MailListsService.findExternalMailsByNameListAndDomainName(java.lang.String nomLlistaCorreu, java.lang.String codiDomini) - nomLlistaCorreu cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindExternalMailsByNameListAndDomainName(nomLlistaCorreu, codiDomini)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.ExternalName>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.MailListsService.class).
			warn ("Error on MailListsService.findExternalMailsByNameListAndDomainName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on MailListsService.findExternalMailsByNameListAndDomainName", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.ExternalName> handleFindExternalMailsByNameListAndDomainName(java.lang.String nomLlistaCorreu, java.lang.String codiDomini) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.MailListsService#	 * @see com.soffid.iam.iga.service.MailListsService#java.util.Collection<com.soffid.iam.iga.api.Group> findGroupMembers(java.lang.String nomLlistaCorreu, java.lang.String codiDomini)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.Group> findGroupMembers(
		final java.lang.String nomLlistaCorreu, 
		final java.lang.String codiDomini)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (nomLlistaCorreu == null || nomLlistaCorreu.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.Group> com.soffid.iam.iga.service.MailListsService.findGroupMembers(java.lang.String nomLlistaCorreu, java.lang.String codiDomini) - nomLlistaCorreu cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindGroupMembers(nomLlistaCorreu, codiDomini)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.Group>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.MailListsService.class).
			warn ("Error on MailListsService.findGroupMembers", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on MailListsService.findGroupMembers", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.Group> handleFindGroupMembers(java.lang.String nomLlistaCorreu, java.lang.String codiDomini) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.MailListsService#	 * @see com.soffid.iam.iga.service.MailListsService#java.util.Collection<com.soffid.iam.iga.api.UserMailList> findUserMailListByUserName(java.lang.String codiUsuari)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.UserMailList> findUserMailListByUserName(
		final java.lang.String codiUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiUsuari == null || codiUsuari.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.UserMailList> com.soffid.iam.iga.service.MailListsService.findUserMailListByUserName(java.lang.String codiUsuari) - codiUsuari cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindUserMailListByUserName(codiUsuari)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.UserMailList>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.MailListsService.class).
			warn ("Error on MailListsService.findUserMailListByUserName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on MailListsService.findUserMailListByUserName", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.UserMailList> handleFindUserMailListByUserName(java.lang.String codiUsuari) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.MailListsService#	 * @see com.soffid.iam.iga.service.MailListsService#java.util.Collection<com.soffid.iam.iga.api.UserMailList> findUserMailListByListNameAndDomainName(java.lang.String nomLlistaCorreu, java.lang.String codiDomini)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.UserMailList> findUserMailListByListNameAndDomainName(
		final java.lang.String nomLlistaCorreu, 
		final java.lang.String codiDomini)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (nomLlistaCorreu == null || nomLlistaCorreu.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.UserMailList> com.soffid.iam.iga.service.MailListsService.findUserMailListByListNameAndDomainName(java.lang.String nomLlistaCorreu, java.lang.String codiDomini) - nomLlistaCorreu cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindUserMailListByListNameAndDomainName(nomLlistaCorreu, codiDomini)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.UserMailList>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.MailListsService.class).
			warn ("Error on MailListsService.findUserMailListByListNameAndDomainName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on MailListsService.findUserMailListByListNameAndDomainName", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.UserMailList> handleFindUserMailListByListNameAndDomainName(java.lang.String nomLlistaCorreu, java.lang.String codiDomini) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.MailListsService#	 * @see com.soffid.iam.iga.service.MailListsService#java.util.Collection<com.soffid.iam.iga.api.UserMailList> findUserMailListHistoryByUserName(java.lang.String codiUsuari)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.UserMailList> findUserMailListHistoryByUserName(
		final java.lang.String codiUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiUsuari == null || codiUsuari.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.UserMailList> com.soffid.iam.iga.service.MailListsService.findUserMailListHistoryByUserName(java.lang.String codiUsuari) - codiUsuari cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindUserMailListHistoryByUserName(codiUsuari)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.UserMailList>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.MailListsService.class).
			warn ("Error on MailListsService.findUserMailListHistoryByUserName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on MailListsService.findUserMailListHistoryByUserName", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.UserMailList> handleFindUserMailListHistoryByUserName(java.lang.String codiUsuari) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.MailListsService#	 * @see com.soffid.iam.iga.service.MailListsService#java.util.Collection<com.soffid.iam.iga.api.MailList> findMailListsByData(java.lang.String nom, java.lang.String domini, java.lang.String descripcio, java.lang.String membres)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.MailList> findMailListsByData(
		final java.lang.String nom, 
		final java.lang.String domini, 
		final java.lang.String descripcio, 
		final java.lang.String membres)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindMailListsByData(nom, domini, descripcio, membres)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.MailList>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.MailListsService.class).
			warn ("Error on MailListsService.findMailListsByData", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on MailListsService.findMailListsByData", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.MailList> handleFindMailListsByData(java.lang.String nom, java.lang.String domini, java.lang.String descripcio, java.lang.String membres) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.MailListsService#	 * @see com.soffid.iam.iga.service.MailListsService#java.util.Collection<com.soffid.iam.iga.api.MailListRelationship> findRelationsMailListByNameContainsMailListAndDomainName(java.lang.String nomLlistaCorreuConte, java.lang.String codiDomini)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.MailListRelationship> findRelationsMailListByNameContainsMailListAndDomainName(
		final java.lang.String nomLlistaCorreuConte, 
		final java.lang.String codiDomini)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (nomLlistaCorreuConte == null || nomLlistaCorreuConte.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.MailListRelationship> com.soffid.iam.iga.service.MailListsService.findRelationsMailListByNameContainsMailListAndDomainName(java.lang.String nomLlistaCorreuConte, java.lang.String codiDomini) - nomLlistaCorreuConte cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindRelationsMailListByNameContainsMailListAndDomainName(nomLlistaCorreuConte, codiDomini)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.MailListRelationship>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.MailListsService.class).
			warn ("Error on MailListsService.findRelationsMailListByNameContainsMailListAndDomainName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on MailListsService.findRelationsMailListByNameContainsMailListAndDomainName", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.MailListRelationship> handleFindRelationsMailListByNameContainsMailListAndDomainName(java.lang.String nomLlistaCorreuConte, java.lang.String codiDomini) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.MailListsService#	 * @see com.soffid.iam.iga.service.MailListsService#java.util.Collection<com.soffid.iam.iga.api.MailListRelationship> findRelationsMailListByNameBelongsMailListAndDomainName(java.lang.String nomLlistaCorreuPertany, java.lang.String codiDomini)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.MailListRelationship> findRelationsMailListByNameBelongsMailListAndDomainName(
		final java.lang.String nomLlistaCorreuPertany, 
		final java.lang.String codiDomini)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (nomLlistaCorreuPertany == null || nomLlistaCorreuPertany.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.MailListRelationship> com.soffid.iam.iga.service.MailListsService.findRelationsMailListByNameBelongsMailListAndDomainName(java.lang.String nomLlistaCorreuPertany, java.lang.String codiDomini) - nomLlistaCorreuPertany cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindRelationsMailListByNameBelongsMailListAndDomainName(nomLlistaCorreuPertany, codiDomini)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.MailListRelationship>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.MailListsService.class).
			warn ("Error on MailListsService.findRelationsMailListByNameBelongsMailListAndDomainName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on MailListsService.findRelationsMailListByNameBelongsMailListAndDomainName", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.MailListRelationship> handleFindRelationsMailListByNameBelongsMailListAndDomainName(java.lang.String nomLlistaCorreuPertany, java.lang.String codiDomini) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.MailListsService#	 * @see com.soffid.iam.iga.service.MailListsService#java.util.Collection<com.soffid.iam.iga.api.MailListRoleMember> findRoleMembers(java.lang.String nomLlistaCorreu, java.lang.String codiDomini)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.MailListRoleMember> findRoleMembers(
		final java.lang.String nomLlistaCorreu, 
		final java.lang.String codiDomini)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (nomLlistaCorreu == null || nomLlistaCorreu.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.MailListRoleMember> com.soffid.iam.iga.service.MailListsService.findRoleMembers(java.lang.String nomLlistaCorreu, java.lang.String codiDomini) - nomLlistaCorreu cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindRoleMembers(nomLlistaCorreu, codiDomini)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.MailListRoleMember>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.MailListsService.class).
			warn ("Error on MailListsService.findRoleMembers", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on MailListsService.findRoleMembers", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.MailListRoleMember> handleFindRoleMembers(java.lang.String nomLlistaCorreu, java.lang.String codiDomini) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.MailListsService#	 * @see com.soffid.iam.iga.service.MailListsService#java.util.Collection<com.soffid.iam.base.api.User> findUsersByMailListNameAndDomainName(java.lang.String nomLlistaCorreu, java.lang.String codiDomini)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.base.api.User> findUsersByMailListNameAndDomainName(
		final java.lang.String nomLlistaCorreu, 
		final java.lang.String codiDomini)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (nomLlistaCorreu == null || nomLlistaCorreu.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.base.api.User> com.soffid.iam.iga.service.MailListsService.findUsersByMailListNameAndDomainName(java.lang.String nomLlistaCorreu, java.lang.String codiDomini) - nomLlistaCorreu cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindUsersByMailListNameAndDomainName(nomLlistaCorreu, codiDomini)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.base.api.User>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.MailListsService.class).
			warn ("Error on MailListsService.findUsersByMailListNameAndDomainName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on MailListsService.findUsersByMailListNameAndDomainName", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.base.api.User> handleFindUsersByMailListNameAndDomainName(java.lang.String nomLlistaCorreu, java.lang.String codiDomini) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.MailListsService#	 * @see com.soffid.iam.iga.service.MailListsService#java.util.Collection<com.soffid.iam.iga.api.MailDomain> getDomainMails()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.MailDomain> getDomainMails()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetDomainMails()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.MailDomain>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.MailListsService.class).
			warn ("Error on MailListsService.getDomainMails", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on MailListsService.getDomainMails", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.MailDomain> handleGetDomainMails() throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.MailListsService#	 * @see com.soffid.iam.iga.service.MailListsService#java.util.Collection<com.soffid.iam.iga.api.MailList> getMailLists()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.MailList> getMailLists()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetMailLists()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.MailList>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.MailListsService.class).
			warn ("Error on MailListsService.getMailLists", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on MailListsService.getMailLists", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.MailList> handleGetMailLists() throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.MailListsService#	 * @see com.soffid.iam.iga.service.MailListsService#void delete(com.soffid.iam.iga.api.ExternalName correuExtern)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void delete(
		final com.soffid.iam.iga.api.ExternalName correuExtern)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (correuExtern == null) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailListsService.delete(com.soffid.iam.iga.api.ExternalName correuExtern) - correuExtern cannot be null");
		}
		if (correuExtern.getEmail() == null || correuExtern.getEmail().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailListsService.delete(com.soffid.iam.iga.api.ExternalName correuExtern) - correuExtern.email cannot be null");
		}
		if (correuExtern.getMailListName() == null || correuExtern.getMailListName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailListsService.delete(com.soffid.iam.iga.api.ExternalName correuExtern) - correuExtern.mailListName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDelete(correuExtern);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.MailListsService.class).
			warn ("Error on MailListsService.delete", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on MailListsService.delete", (Throwable) __r[1]);
	}

	protected abstract void handleDelete(com.soffid.iam.iga.api.ExternalName correuExtern) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.MailListsService#	 * @see com.soffid.iam.iga.service.MailListsService#void delete(com.soffid.iam.iga.api.MailDomain dominiCorreu)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void delete(
		final com.soffid.iam.iga.api.MailDomain dominiCorreu)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (dominiCorreu == null) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailListsService.delete(com.soffid.iam.iga.api.MailDomain dominiCorreu) - dominiCorreu cannot be null");
		}
		if (dominiCorreu.getName() == null || dominiCorreu.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailListsService.delete(com.soffid.iam.iga.api.MailDomain dominiCorreu) - dominiCorreu.name cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDelete(dominiCorreu);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.MailListsService.class).
			warn ("Error on MailListsService.delete", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on MailListsService.delete", (Throwable) __r[1]);
	}

	protected abstract void handleDelete(com.soffid.iam.iga.api.MailDomain dominiCorreu) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.MailListsService#	 * @see com.soffid.iam.iga.service.MailListsService#void delete(com.soffid.iam.iga.api.MailList llistaCorreu)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void delete(
		final com.soffid.iam.iga.api.MailList llistaCorreu)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (llistaCorreu == null) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailListsService.delete(com.soffid.iam.iga.api.MailList llistaCorreu) - llistaCorreu cannot be null");
		}
		if (llistaCorreu.getName() == null || llistaCorreu.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailListsService.delete(com.soffid.iam.iga.api.MailList llistaCorreu) - llistaCorreu.name cannot be null");
		}
		if (llistaCorreu.getDomainName() == null || llistaCorreu.getDomainName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailListsService.delete(com.soffid.iam.iga.api.MailList llistaCorreu) - llistaCorreu.domainName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDelete(llistaCorreu);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.MailListsService.class).
			warn ("Error on MailListsService.delete", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on MailListsService.delete", (Throwable) __r[1]);
	}

	protected abstract void handleDelete(com.soffid.iam.iga.api.MailList llistaCorreu) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.MailListsService#	 * @see com.soffid.iam.iga.service.MailListsService#void delete(com.soffid.iam.iga.api.MailListRelationship relacioLlistaCorreu)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void delete(
		final com.soffid.iam.iga.api.MailListRelationship relacioLlistaCorreu)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (relacioLlistaCorreu == null) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailListsService.delete(com.soffid.iam.iga.api.MailListRelationship relacioLlistaCorreu) - relacioLlistaCorreu cannot be null");
		}
		if (relacioLlistaCorreu.getMailListNameBelong() == null || relacioLlistaCorreu.getMailListNameBelong().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailListsService.delete(com.soffid.iam.iga.api.MailListRelationship relacioLlistaCorreu) - relacioLlistaCorreu.mailListNameBelong cannot be null");
		}
		if (relacioLlistaCorreu.getMailListNameIncluded() == null || relacioLlistaCorreu.getMailListNameIncluded().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailListsService.delete(com.soffid.iam.iga.api.MailListRelationship relacioLlistaCorreu) - relacioLlistaCorreu.mailListNameIncluded cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDelete(relacioLlistaCorreu);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.MailListsService.class).
			warn ("Error on MailListsService.delete", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on MailListsService.delete", (Throwable) __r[1]);
	}

	protected abstract void handleDelete(com.soffid.iam.iga.api.MailListRelationship relacioLlistaCorreu) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.MailListsService#	 * @see com.soffid.iam.iga.service.MailListsService#void deleteUserMailList(com.soffid.iam.iga.api.UserMailList llistaCorreuUsuari)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void deleteUserMailList(
		final com.soffid.iam.iga.api.UserMailList llistaCorreuUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (llistaCorreuUsuari == null) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailListsService.deleteUserMailList(com.soffid.iam.iga.api.UserMailList llistaCorreuUsuari) - llistaCorreuUsuari cannot be null");
		}
		if (llistaCorreuUsuari.getMailListName() == null || llistaCorreuUsuari.getMailListName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailListsService.deleteUserMailList(com.soffid.iam.iga.api.UserMailList llistaCorreuUsuari) - llistaCorreuUsuari.mailListName cannot be null");
		}
		if (llistaCorreuUsuari.getUserCode() == null || llistaCorreuUsuari.getUserCode().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailListsService.deleteUserMailList(com.soffid.iam.iga.api.UserMailList llistaCorreuUsuari) - llistaCorreuUsuari.userCode cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDeleteUserMailList(llistaCorreuUsuari);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.MailListsService.class).
			warn ("Error on MailListsService.deleteUserMailList", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on MailListsService.deleteUserMailList", (Throwable) __r[1]);
	}

	protected abstract void handleDeleteUserMailList(com.soffid.iam.iga.api.UserMailList llistaCorreuUsuari) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.MailListsService#	 * @see com.soffid.iam.iga.service.MailListsService#void deleteAtomic(com.soffid.iam.iga.api.ExternalName correuExtern)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void deleteAtomic(
		final com.soffid.iam.iga.api.ExternalName correuExtern)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (correuExtern == null) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailListsService.deleteAtomic(com.soffid.iam.iga.api.ExternalName correuExtern) - correuExtern cannot be null");
		}
		if (correuExtern.getEmail() == null || correuExtern.getEmail().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailListsService.deleteAtomic(com.soffid.iam.iga.api.ExternalName correuExtern) - correuExtern.email cannot be null");
		}
		if (correuExtern.getMailListName() == null || correuExtern.getMailListName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailListsService.deleteAtomic(com.soffid.iam.iga.api.ExternalName correuExtern) - correuExtern.mailListName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDeleteAtomic(correuExtern);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.MailListsService.class).
			warn ("Error on MailListsService.deleteAtomic", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on MailListsService.deleteAtomic", (Throwable) __r[1]);
	}

	protected abstract void handleDeleteAtomic(com.soffid.iam.iga.api.ExternalName correuExtern) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.MailListsService#	 * @see com.soffid.iam.iga.service.MailListsService#void deleteAtomic(com.soffid.iam.iga.api.MailListRelationship relacioLlistaCorreu)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void deleteAtomic(
		final com.soffid.iam.iga.api.MailListRelationship relacioLlistaCorreu)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (relacioLlistaCorreu == null) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailListsService.deleteAtomic(com.soffid.iam.iga.api.MailListRelationship relacioLlistaCorreu) - relacioLlistaCorreu cannot be null");
		}
		if (relacioLlistaCorreu.getMailListNameBelong() == null || relacioLlistaCorreu.getMailListNameBelong().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailListsService.deleteAtomic(com.soffid.iam.iga.api.MailListRelationship relacioLlistaCorreu) - relacioLlistaCorreu.mailListNameBelong cannot be null");
		}
		if (relacioLlistaCorreu.getMailListNameIncluded() == null || relacioLlistaCorreu.getMailListNameIncluded().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailListsService.deleteAtomic(com.soffid.iam.iga.api.MailListRelationship relacioLlistaCorreu) - relacioLlistaCorreu.mailListNameIncluded cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDeleteAtomic(relacioLlistaCorreu);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.MailListsService.class).
			warn ("Error on MailListsService.deleteAtomic", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on MailListsService.deleteAtomic", (Throwable) __r[1]);
	}

	protected abstract void handleDeleteAtomic(com.soffid.iam.iga.api.MailListRelationship relacioLlistaCorreu) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.MailListsService#	 * @see com.soffid.iam.iga.service.MailListsService#void deleteAtomic(com.soffid.iam.iga.api.UserMailList llistaCorreuUsuari)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void deleteAtomic(
		final com.soffid.iam.iga.api.UserMailList llistaCorreuUsuari)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (llistaCorreuUsuari == null) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailListsService.deleteAtomic(com.soffid.iam.iga.api.UserMailList llistaCorreuUsuari) - llistaCorreuUsuari cannot be null");
		}
		if (llistaCorreuUsuari.getMailListName() == null || llistaCorreuUsuari.getMailListName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailListsService.deleteAtomic(com.soffid.iam.iga.api.UserMailList llistaCorreuUsuari) - llistaCorreuUsuari.mailListName cannot be null");
		}
		if (llistaCorreuUsuari.getUserCode() == null || llistaCorreuUsuari.getUserCode().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailListsService.deleteAtomic(com.soffid.iam.iga.api.UserMailList llistaCorreuUsuari) - llistaCorreuUsuari.userCode cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDeleteAtomic(llistaCorreuUsuari);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.MailListsService.class).
			warn ("Error on MailListsService.deleteAtomic", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on MailListsService.deleteAtomic", (Throwable) __r[1]);
	}

	protected abstract void handleDeleteAtomic(com.soffid.iam.iga.api.UserMailList llistaCorreuUsuari) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.MailListsService#	 * @see com.soffid.iam.iga.service.MailListsService#void checkEmptyMailList(java.lang.String nomLlistaCorreu, java.lang.String codiDomini)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void checkEmptyMailList(
		final java.lang.String nomLlistaCorreu, 
		final java.lang.String codiDomini)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (nomLlistaCorreu == null || nomLlistaCorreu.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailListsService.checkEmptyMailList(java.lang.String nomLlistaCorreu, java.lang.String codiDomini) - nomLlistaCorreu cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleCheckEmptyMailList(nomLlistaCorreu, codiDomini);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.MailListsService.class).
			warn ("Error on MailListsService.checkEmptyMailList", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on MailListsService.checkEmptyMailList", (Throwable) __r[1]);
	}

	protected abstract void handleCheckEmptyMailList(java.lang.String nomLlistaCorreu, java.lang.String codiDomini) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.MailListsService#	 * @see com.soffid.iam.iga.service.MailListsService#void subscribeGroup(java.lang.String mailListName, java.lang.String mailListDomain, java.lang.String groupName)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void subscribeGroup(
		final java.lang.String mailListName, 
		final java.lang.String mailListDomain, 
		final java.lang.String groupName)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (mailListName == null || mailListName.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailListsService.subscribeGroup(java.lang.String mailListName, java.lang.String mailListDomain, java.lang.String groupName) - mailListName cannot be null");
		}
		if (mailListDomain == null || mailListDomain.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailListsService.subscribeGroup(java.lang.String mailListName, java.lang.String mailListDomain, java.lang.String groupName) - mailListDomain cannot be null");
		}
		if (groupName == null || groupName.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailListsService.subscribeGroup(java.lang.String mailListName, java.lang.String mailListDomain, java.lang.String groupName) - groupName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleSubscribeGroup(mailListName, mailListDomain, groupName);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.MailListsService.class).
			warn ("Error on MailListsService.subscribeGroup", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on MailListsService.subscribeGroup", (Throwable) __r[1]);
	}

	protected abstract void handleSubscribeGroup(java.lang.String mailListName, java.lang.String mailListDomain, java.lang.String groupName) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.MailListsService#	 * @see com.soffid.iam.iga.service.MailListsService#void unsubscribeGroup(java.lang.String mailListName, java.lang.String mailListDomain, java.lang.String groupName)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void unsubscribeGroup(
		final java.lang.String mailListName, 
		final java.lang.String mailListDomain, 
		final java.lang.String groupName)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (mailListName == null || mailListName.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailListsService.unsubscribeGroup(java.lang.String mailListName, java.lang.String mailListDomain, java.lang.String groupName) - mailListName cannot be null");
		}
		if (mailListDomain == null || mailListDomain.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailListsService.unsubscribeGroup(java.lang.String mailListName, java.lang.String mailListDomain, java.lang.String groupName) - mailListDomain cannot be null");
		}
		if (groupName == null || groupName.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailListsService.unsubscribeGroup(java.lang.String mailListName, java.lang.String mailListDomain, java.lang.String groupName) - groupName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleUnsubscribeGroup(mailListName, mailListDomain, groupName);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.MailListsService.class).
			warn ("Error on MailListsService.unsubscribeGroup", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on MailListsService.unsubscribeGroup", (Throwable) __r[1]);
	}

	protected abstract void handleUnsubscribeGroup(java.lang.String mailListName, java.lang.String mailListDomain, java.lang.String groupName) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.MailListsService#	 * @see com.soffid.iam.iga.service.MailListsService#void unsubscribeRole(java.lang.String mailListName, java.lang.String mailListDomain, com.soffid.iam.iga.api.MailListRoleMember roleMember)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void unsubscribeRole(
		final java.lang.String mailListName, 
		final java.lang.String mailListDomain, 
		final com.soffid.iam.iga.api.MailListRoleMember roleMember)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (mailListName == null || mailListName.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailListsService.unsubscribeRole(java.lang.String mailListName, java.lang.String mailListDomain, com.soffid.iam.iga.api.MailListRoleMember roleMember) - mailListName cannot be null");
		}
		if (mailListDomain == null || mailListDomain.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailListsService.unsubscribeRole(java.lang.String mailListName, java.lang.String mailListDomain, com.soffid.iam.iga.api.MailListRoleMember roleMember) - mailListDomain cannot be null");
		}
		if (roleMember == null) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailListsService.unsubscribeRole(java.lang.String mailListName, java.lang.String mailListDomain, com.soffid.iam.iga.api.MailListRoleMember roleMember) - roleMember cannot be null");
		}
		if (roleMember.getRoleName() == null || roleMember.getRoleName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailListsService.unsubscribeRole(java.lang.String mailListName, java.lang.String mailListDomain, com.soffid.iam.iga.api.MailListRoleMember roleMember) - roleMember.roleName cannot be null");
		}
		if (roleMember.getDispatcherName() == null || roleMember.getDispatcherName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.MailListsService.unsubscribeRole(java.lang.String mailListName, java.lang.String mailListDomain, com.soffid.iam.iga.api.MailListRoleMember roleMember) - roleMember.dispatcherName cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleUnsubscribeRole(mailListName, mailListDomain, roleMember);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.MailListsService.class).
			warn ("Error on MailListsService.unsubscribeRole", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on MailListsService.unsubscribeRole", (Throwable) __r[1]);
	}

	protected abstract void handleUnsubscribeRole(java.lang.String mailListName, java.lang.String mailListDomain, com.soffid.iam.iga.api.MailListRoleMember roleMember) throws Exception;

	/**
	 * Gets the current <code>principal</code> if one has been set,
	 * otherwise returns <code>null</code>.
	 *
	 * @return the current principal
	 */
	protected java.security.Principal getPrincipal()
	{
		return com.soffid.iam.PrincipalStore.get();
	}

}
