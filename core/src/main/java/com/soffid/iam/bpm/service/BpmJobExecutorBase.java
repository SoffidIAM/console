//
// (C) 2013 Soffid
//
//

package com.soffid.iam.bpm.service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p> 
 * Spring Service base class for <code>com.soffid.iam.bpm.service.BpmJobExecutor</code>,
 * provides access to all services and entities referenced by this service. 
 * </p>
 * 
 * see com.soffid.iam.bpm.service.BpmJobExecutor
 */
public abstract class BpmJobExecutorBase
	implements com.soffid.iam.bpm.service.BpmJobExecutor
 {
	private com.soffid.iam.bpm.service.BpmConfigService bpmConfigService;

	/**
	 * Sets reference to <code>bpmConfigService</code>.
	 */
	public void setBpmConfigService (com.soffid.iam.bpm.service.BpmConfigService bpmConfigService) {
		this.bpmConfigService = bpmConfigService;
	}

	/**
	 * Gets reference to <code>bpmConfigService</code>.
	 */
	public com.soffid.iam.bpm.service.BpmConfigService getBpmConfigService () {
		return bpmConfigService;
	}

	private com.soffid.iam.bpm.service.BpmEngine bpmEngine;

	/**
	 * Sets reference to <code>bpmEngine</code>.
	 */
	public void setBpmEngine (com.soffid.iam.bpm.service.BpmEngine bpmEngine) {
		this.bpmEngine = bpmEngine;
	}

	/**
	 * Gets reference to <code>bpmEngine</code>.
	 */
	public com.soffid.iam.bpm.service.BpmEngine getBpmEngine () {
		return bpmEngine;
	}

	private com.soffid.iam.iga.service.MailService mailService;

	/**
	 * Sets reference to <code>mailService</code>.
	 */
	public void setMailService (com.soffid.iam.iga.service.MailService mailService) {
		this.mailService = mailService;
	}

	/**
	 * Gets reference to <code>mailService</code>.
	 */
	public com.soffid.iam.iga.service.MailService getMailService () {
		return mailService;
	}


	/**
	 * @see com.soffid.iam.bpm.service.BpmJobExecutor#	 * @see com.soffid.iam.bpm.service.BpmJobExecutor#boolean lockJob(long id, java.lang.String lockOwner)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRES_NEW, 
		rollbackFor={java.lang.Exception.class})
	public boolean lockJob(
		final long id, 
		final java.lang.String lockOwner)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, java.lang.Exception
	{
		if (lockOwner == null || lockOwner.trim().length() == 0) {
			throw new IllegalArgumentException("boolean com.soffid.iam.bpm.service.BpmJobExecutor.lockJob(long id, java.lang.String lockOwner) - lockOwner cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleLockJob(id, lockOwner)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return ((Boolean) __r[0]).booleanValue();
		if (__r[1] instanceof java.lang.Exception) 
			throw (java.lang.Exception) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmJobExecutor.class).
			warn ("Error on BpmJobExecutor.lockJob", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmJobExecutor.lockJob", (Throwable) __r[1]);
	}

	protected abstract boolean handleLockJob(long id, java.lang.String lockOwner) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmJobExecutor#	 * @see com.soffid.iam.bpm.service.BpmJobExecutor#java.util.Date getNextDueDate(java.lang.String lockOwner)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRES_NEW, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Date getNextDueDate(
		final java.lang.String lockOwner)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (lockOwner == null || lockOwner.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Date com.soffid.iam.bpm.service.BpmJobExecutor.getNextDueDate(java.lang.String lockOwner) - lockOwner cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetNextDueDate(lockOwner)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Date) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmJobExecutor.class).
			warn ("Error on BpmJobExecutor.getNextDueDate", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmJobExecutor.getNextDueDate", (Throwable) __r[1]);
	}

	protected abstract java.util.Date handleGetNextDueDate(java.lang.String lockOwner) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmJobExecutor#	 * @see com.soffid.iam.bpm.service.BpmJobExecutor#java.util.List getJobs(java.lang.String lockOwner)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRES_NEW, 
		rollbackFor={java.lang.Exception.class})
	public java.util.List getJobs(
		final java.lang.String lockOwner)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (lockOwner == null || lockOwner.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.List com.soffid.iam.bpm.service.BpmJobExecutor.getJobs(java.lang.String lockOwner) - lockOwner cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetJobs(lockOwner)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.List) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmJobExecutor.class).
			warn ("Error on BpmJobExecutor.getJobs", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmJobExecutor.getJobs", (Throwable) __r[1]);
	}

	protected abstract java.util.List handleGetJobs(java.lang.String lockOwner) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmJobExecutor#	 * @see com.soffid.iam.bpm.service.BpmJobExecutor#void anotateFailure(long id, java.lang.Exception e)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRES_NEW, 
		rollbackFor={java.lang.Exception.class})
	public void anotateFailure(
		final long id, 
		final java.lang.Exception e)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (e == null) {
			throw new IllegalArgumentException("void com.soffid.iam.bpm.service.BpmJobExecutor.anotateFailure(long id, java.lang.Exception e) - e cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleAnotateFailure(id, e);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmJobExecutor.class).
			warn ("Error on BpmJobExecutor.anotateFailure", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmJobExecutor.anotateFailure", (Throwable) __r[1]);
	}

	protected abstract void handleAnotateFailure(long id, java.lang.Exception e) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmJobExecutor#	 * @see com.soffid.iam.bpm.service.BpmJobExecutor#void executeJob(long id)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRES_NEW, 
		rollbackFor={java.lang.Exception.class})
	public void executeJob(
		final long id)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException, java.lang.Exception
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleExecuteJob(id);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof java.lang.Exception) 
			throw (java.lang.Exception) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmJobExecutor.class).
			warn ("Error on BpmJobExecutor.executeJob", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmJobExecutor.executeJob", (Throwable) __r[1]);
	}

	protected abstract void handleExecuteJob(long id) throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmJobExecutor#	 * @see com.soffid.iam.bpm.service.BpmJobExecutor#void indexPendingProcesses()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRES_NEW, 
		rollbackFor={java.lang.Exception.class})
	public void indexPendingProcesses()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleIndexPendingProcesses();
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmJobExecutor.class).
			warn ("Error on BpmJobExecutor.indexPendingProcesses", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmJobExecutor.indexPendingProcesses", (Throwable) __r[1]);
	}

	protected abstract void handleIndexPendingProcesses() throws Exception;

	/**
	 * @see com.soffid.iam.bpm.service.BpmJobExecutor#	 * @see com.soffid.iam.bpm.service.BpmJobExecutor#void unlockOverdueJobs(java.util.Date threshold)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRES_NEW, 
		rollbackFor={java.lang.Exception.class})
	public void unlockOverdueJobs(
		final java.util.Date threshold)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (threshold == null) {
			throw new IllegalArgumentException("void com.soffid.iam.bpm.service.BpmJobExecutor.unlockOverdueJobs(java.util.Date threshold) - threshold cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleUnlockOverdueJobs(threshold);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.bpm.service.BpmJobExecutor.class).
			warn ("Error on BpmJobExecutor.unlockOverdueJobs", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on BpmJobExecutor.unlockOverdueJobs", (Throwable) __r[1]);
	}

	protected abstract void handleUnlockOverdueJobs(java.util.Date threshold) throws Exception;

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
