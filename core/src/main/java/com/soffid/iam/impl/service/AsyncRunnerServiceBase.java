//
// (C) 2013 Soffid
//
//

package com.soffid.iam.impl.service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p> 
 * Spring Service base class for <code>com.soffid.iam.impl.service.AsyncRunnerService</code>,
 * provides access to all services and entities referenced by this service. 
 * </p>
 * 
 * see com.soffid.iam.impl.service.AsyncRunnerService
 */
public abstract class AsyncRunnerServiceBase
	implements com.soffid.iam.impl.service.AsyncRunnerService
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


	/**
	 * @see com.soffid.iam.impl.service.AsyncRunnerService#	 * @see com.soffid.iam.impl.service.AsyncRunnerService#java.lang.Object runNewTransaction(com.soffid.iam.impl.TransactionalTask runnable)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRES_NEW, 
		rollbackFor={java.lang.Exception.class})
	public java.lang.Object runNewTransaction(
		final com.soffid.iam.impl.TransactionalTask runnable)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (runnable == null) {
			throw new IllegalArgumentException("java.lang.Object com.soffid.iam.impl.service.AsyncRunnerService.runNewTransaction(com.soffid.iam.impl.TransactionalTask runnable) - runnable cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleRunNewTransaction(runnable)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.Object) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.AsyncRunnerService.class).
			warn ("Error on AsyncRunnerService.runNewTransaction", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AsyncRunnerService.runNewTransaction", (Throwable) __r[1]);
	}

	protected abstract java.lang.Object handleRunNewTransaction(com.soffid.iam.impl.TransactionalTask runnable) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.AsyncRunnerService#	 * @see com.soffid.iam.impl.service.AsyncRunnerService#java.lang.Object runTransaction(com.soffid.iam.impl.TransactionalTask runnable)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.lang.Object runTransaction(
		final com.soffid.iam.impl.TransactionalTask runnable)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (runnable == null) {
			throw new IllegalArgumentException("java.lang.Object com.soffid.iam.impl.service.AsyncRunnerService.runTransaction(com.soffid.iam.impl.TransactionalTask runnable) - runnable cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleRunTransaction(runnable)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.Object) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.AsyncRunnerService.class).
			warn ("Error on AsyncRunnerService.runTransaction", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AsyncRunnerService.runTransaction", (Throwable) __r[1]);
	}

	protected abstract java.lang.Object handleRunTransaction(com.soffid.iam.impl.TransactionalTask runnable) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.AsyncRunnerService#	 * @see com.soffid.iam.impl.service.AsyncRunnerService#void run(java.lang.Runnable runnable, com.soffid.zkdb.api.PagedResult result)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		noRollbackFor={java.lang.Exception.class}, readOnly=true)
	public void run(
		final java.lang.Runnable runnable, 
		final com.soffid.zkdb.api.PagedResult result)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (runnable == null) {
			throw new IllegalArgumentException("void com.soffid.iam.impl.service.AsyncRunnerService.run(java.lang.Runnable runnable, com.soffid.zkdb.api.PagedResult result) - runnable cannot be null");
		}
		if (result == null) {
			throw new IllegalArgumentException("void com.soffid.iam.impl.service.AsyncRunnerService.run(java.lang.Runnable runnable, com.soffid.zkdb.api.PagedResult result) - result cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleRun(runnable, result);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.AsyncRunnerService.class).
			warn ("Error on AsyncRunnerService.run", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AsyncRunnerService.run", (Throwable) __r[1]);
	}

	protected abstract void handleRun(java.lang.Runnable runnable, com.soffid.zkdb.api.PagedResult result) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.AsyncRunnerService#	 * @see com.soffid.iam.impl.service.AsyncRunnerService#void runInternal(java.lang.Runnable runnable, com.soffid.zkdb.api.PagedResult result)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		noRollbackFor={java.lang.Exception.class}, readOnly=true)
	public void runInternal(
		final java.lang.Runnable runnable, 
		final com.soffid.zkdb.api.PagedResult result)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (runnable == null) {
			throw new IllegalArgumentException("void com.soffid.iam.impl.service.AsyncRunnerService.runInternal(java.lang.Runnable runnable, com.soffid.zkdb.api.PagedResult result) - runnable cannot be null");
		}
		if (result == null) {
			throw new IllegalArgumentException("void com.soffid.iam.impl.service.AsyncRunnerService.runInternal(java.lang.Runnable runnable, com.soffid.zkdb.api.PagedResult result) - result cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleRunInternal(runnable, result);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.AsyncRunnerService.class).
			warn ("Error on AsyncRunnerService.runInternal", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AsyncRunnerService.runInternal", (Throwable) __r[1]);
	}

	protected abstract void handleRunInternal(java.lang.Runnable runnable, com.soffid.zkdb.api.PagedResult result) throws Exception;

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
