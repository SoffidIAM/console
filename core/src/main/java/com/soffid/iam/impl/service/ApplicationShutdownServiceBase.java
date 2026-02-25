//
// (C) 2013 Soffid
//
//

package com.soffid.iam.impl.service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p> 
 * Spring Service base class for <code>com.soffid.iam.impl.service.ApplicationShutdownService</code>,
 * provides access to all services and entities referenced by this service. 
 * </p>
 * 
 * see com.soffid.iam.impl.service.ApplicationShutdownService
 */
public abstract class ApplicationShutdownServiceBase
	implements com.soffid.iam.impl.service.ApplicationShutdownService
 {

	/**
	 * @see com.soffid.iam.impl.service.ApplicationShutdownService#	 * @see com.soffid.iam.impl.service.ApplicationShutdownService#void consoleShutdown()
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void consoleShutdown()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleConsoleShutdown();
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.ApplicationShutdownService.class).
			warn ("Error on ApplicationShutdownService.consoleShutdown", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationShutdownService.consoleShutdown", (Throwable) __r[1]);
	}

	protected abstract void handleConsoleShutdown() throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.ApplicationShutdownService#	 * @see com.soffid.iam.impl.service.ApplicationShutdownService#void syncServerShutdown()
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void syncServerShutdown()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleSyncServerShutdown();
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.ApplicationShutdownService.class).
			warn ("Error on ApplicationShutdownService.syncServerShutdown", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on ApplicationShutdownService.syncServerShutdown", (Throwable) __r[1]);
	}

	protected abstract void handleSyncServerShutdown() throws Exception;

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
