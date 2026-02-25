//
// (C) 2013 Soffid
//
//

package com.soffid.iam.am.service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p> 
 * Spring Service base class for <code>com.soffid.iam.am.service.SignalService</code>,
 * provides access to all services and entities referenced by this service. 
 * </p>
 * 
 * see com.soffid.iam.am.service.SignalService
 */
public abstract class SignalServiceBase
	implements com.soffid.iam.am.service.SignalService
 {

	/**
	 * @see com.soffid.iam.am.service.SignalService#	 * @see com.soffid.iam.am.service.SignalService#void signal(java.lang.String signal, java.lang.String[] attributes)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void signal(
		final java.lang.String signal, 
		final java.lang.String[] attributes)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (signal == null || signal.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.SignalService.signal(java.lang.String signal, java.lang.String[] attributes) - signal cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleSignal(signal, attributes);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.SignalService.class).
			warn ("Error on SignalService.signal", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SignalService.signal", (Throwable) __r[1]);
	}

	protected abstract void handleSignal(java.lang.String signal, java.lang.String[] attributes) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.SignalService#	 * @see com.soffid.iam.am.service.SignalService#void signalAccount(java.lang.String signal, java.lang.String account, java.lang.String system, java.lang.String[] attributes)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void signalAccount(
		final java.lang.String signal, 
		final java.lang.String account, 
		final java.lang.String system, 
		final java.lang.String[] attributes)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (signal == null || signal.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.SignalService.signalAccount(java.lang.String signal, java.lang.String account, java.lang.String system, java.lang.String[] attributes) - signal cannot be null");
		}
		if (account == null || account.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.SignalService.signalAccount(java.lang.String signal, java.lang.String account, java.lang.String system, java.lang.String[] attributes) - account cannot be null");
		}
		if (system == null || system.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.SignalService.signalAccount(java.lang.String signal, java.lang.String account, java.lang.String system, java.lang.String[] attributes) - system cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleSignalAccount(signal, account, system, attributes);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.SignalService.class).
			warn ("Error on SignalService.signalAccount", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SignalService.signalAccount", (Throwable) __r[1]);
	}

	protected abstract void handleSignalAccount(java.lang.String signal, java.lang.String account, java.lang.String system, java.lang.String[] attributes) throws Exception;

	/**
	 * @see com.soffid.iam.am.service.SignalService#	 * @see com.soffid.iam.am.service.SignalService#void signalUser(java.lang.String signal, java.lang.String user, java.lang.String[] attributes)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void signalUser(
		final java.lang.String signal, 
		final java.lang.String user, 
		final java.lang.String[] attributes)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (signal == null || signal.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.SignalService.signalUser(java.lang.String signal, java.lang.String user, java.lang.String[] attributes) - signal cannot be null");
		}
		if (user == null || user.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.am.service.SignalService.signalUser(java.lang.String signal, java.lang.String user, java.lang.String[] attributes) - user cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleSignalUser(signal, user, attributes);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.am.service.SignalService.class).
			warn ("Error on SignalService.signalUser", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SignalService.signalUser", (Throwable) __r[1]);
	}

	protected abstract void handleSignalUser(java.lang.String signal, java.lang.String user, java.lang.String[] attributes) throws Exception;

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
