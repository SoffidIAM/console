//
// (C) 2013 Soffid
//
//

package com.soffid.iam.rc.service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p> 
 * Spring Service base class for <code>com.soffid.iam.rc.service.AuditService</code>,
 * provides access to all services and entities referenced by this service. 
 * </p>
 * 
 * see com.soffid.iam.rc.service.AuditService
 */
public abstract class AuditServiceBase
	implements com.soffid.iam.rc.service.AuditService
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


	/**
	 * @see com.soffid.iam.rc.service.AuditService#	 * @see com.soffid.iam.rc.service.AuditService#com.soffid.iam.rc.api.Audit create(com.soffid.iam.rc.api.Audit auditoria)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.rc.api.Audit create(
		final com.soffid.iam.rc.api.Audit auditoria)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (auditoria == null) {
			throw new IllegalArgumentException("com.soffid.iam.rc.api.Audit com.soffid.iam.rc.service.AuditService.create(com.soffid.iam.rc.api.Audit auditoria) - auditoria cannot be null");
		}
		if (auditoria.getAction() == null || auditoria.getAction().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.rc.api.Audit com.soffid.iam.rc.service.AuditService.create(com.soffid.iam.rc.api.Audit auditoria) - auditoria.action cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreate(auditoria)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.rc.api.Audit) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.rc.service.AuditService.class).
			warn ("Error on AuditService.create", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AuditService.create", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.rc.api.Audit handleCreate(com.soffid.iam.rc.api.Audit auditoria) throws Exception;

	/**
	 * @see com.soffid.iam.rc.service.AuditService#	 * @see com.soffid.iam.rc.service.AuditService#com.soffid.iam.rc.api.Audit findAuditById(java.lang.Long id)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.rc.api.Audit findAuditById(
		final java.lang.Long id)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (id == null) {
			throw new IllegalArgumentException("com.soffid.iam.rc.api.Audit com.soffid.iam.rc.service.AuditService.findAuditById(java.lang.Long id) - id cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindAuditById(id)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.rc.api.Audit) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.rc.service.AuditService.class).
			warn ("Error on AuditService.findAuditById", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AuditService.findAuditById", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.rc.api.Audit handleFindAuditById(java.lang.Long id) throws Exception;

	/**
	 * @see com.soffid.iam.rc.service.AuditService#	 * @see com.soffid.iam.rc.service.AuditService#com.soffid.zkdb.api.PagedResult<com.soffid.iam.rc.api.Audit> findAudits(com.soffid.zkdb.api.Query q)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.rc.api.Audit> findAudits(
		final com.soffid.zkdb.api.Query q)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (q == null) {
			throw new IllegalArgumentException("com.soffid.zkdb.api.PagedResult<com.soffid.iam.rc.api.Audit> com.soffid.iam.rc.service.AuditService.findAudits(com.soffid.zkdb.api.Query q) - q cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindAudits(q)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.zkdb.api.PagedResult<com.soffid.iam.rc.api.Audit>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.rc.service.AuditService.class).
			warn ("Error on AuditService.findAudits", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AuditService.findAudits", (Throwable) __r[1]);
	}

	protected abstract com.soffid.zkdb.api.PagedResult<com.soffid.iam.rc.api.Audit> handleFindAudits(com.soffid.zkdb.api.Query q) throws Exception;

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
