//
// (C) 2013 Soffid
//
//

package com.soffid.iam.impl.service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p> 
 * Spring Service base class for <code>com.soffid.iam.impl.service.OTPValidationService</code>,
 * provides access to all services and entities referenced by this service. 
 * </p>
 * 
 * see com.soffid.iam.impl.service.OTPValidationService
 */
public abstract class OTPValidationServiceBase
	implements com.soffid.iam.impl.service.OTPValidationService
 {

	/**
	 * @see com.soffid.iam.impl.service.OTPValidationService#	 * @see com.soffid.iam.impl.service.OTPValidationService#boolean hasToken(java.lang.String user)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public boolean hasToken(
		final java.lang.String user)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (user == null || user.trim().length() == 0) {
			throw new IllegalArgumentException("boolean com.soffid.iam.impl.service.OTPValidationService.hasToken(java.lang.String user) - user cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleHasToken(user)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return ((Boolean) __r[0]).booleanValue();
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.OTPValidationService.class).
			warn ("Error on OTPValidationService.hasToken", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on OTPValidationService.hasToken", (Throwable) __r[1]);
	}

	protected abstract boolean handleHasToken(java.lang.String user) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.OTPValidationService#	 * @see com.soffid.iam.impl.service.OTPValidationService#boolean resetFailCount(java.lang.String account)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public boolean resetFailCount(
		final java.lang.String account)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (account == null || account.trim().length() == 0) {
			throw new IllegalArgumentException("boolean com.soffid.iam.impl.service.OTPValidationService.resetFailCount(java.lang.String account) - account cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleResetFailCount(account)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return ((Boolean) __r[0]).booleanValue();
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.OTPValidationService.class).
			warn ("Error on OTPValidationService.resetFailCount", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on OTPValidationService.resetFailCount", (Throwable) __r[1]);
	}

	protected abstract boolean handleResetFailCount(java.lang.String account) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.OTPValidationService#	 * @see com.soffid.iam.impl.service.OTPValidationService#boolean validatePin(com.soffid.iam.am.api.Challenge challenge, java.lang.String pin)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public boolean validatePin(
		final com.soffid.iam.am.api.Challenge challenge, 
		final java.lang.String pin)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (challenge == null) {
			throw new IllegalArgumentException("boolean com.soffid.iam.impl.service.OTPValidationService.validatePin(com.soffid.iam.am.api.Challenge challenge, java.lang.String pin) - challenge cannot be null");
		}
		if (pin == null || pin.trim().length() == 0) {
			throw new IllegalArgumentException("boolean com.soffid.iam.impl.service.OTPValidationService.validatePin(com.soffid.iam.am.api.Challenge challenge, java.lang.String pin) - pin cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleValidatePin(challenge, pin)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return ((Boolean) __r[0]).booleanValue();
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.OTPValidationService.class).
			warn ("Error on OTPValidationService.validatePin", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on OTPValidationService.validatePin", (Throwable) __r[1]);
	}

	protected abstract boolean handleValidatePin(com.soffid.iam.am.api.Challenge challenge, java.lang.String pin) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.OTPValidationService#	 * @see com.soffid.iam.impl.service.OTPValidationService#com.soffid.iam.am.api.Challenge resendToken(com.soffid.iam.am.api.Challenge challenge, boolean alternativeMethod)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.Challenge resendToken(
		final com.soffid.iam.am.api.Challenge challenge, 
		final boolean alternativeMethod)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (challenge == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Challenge com.soffid.iam.impl.service.OTPValidationService.resendToken(com.soffid.iam.am.api.Challenge challenge, boolean alternativeMethod) - challenge cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleResendToken(challenge, alternativeMethod)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.Challenge) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.OTPValidationService.class).
			warn ("Error on OTPValidationService.resendToken", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on OTPValidationService.resendToken", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.Challenge handleResendToken(com.soffid.iam.am.api.Challenge challenge, boolean alternativeMethod) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.OTPValidationService#	 * @see com.soffid.iam.impl.service.OTPValidationService#com.soffid.iam.am.api.Challenge selectToken(com.soffid.iam.am.api.Challenge challenge)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.am.api.Challenge selectToken(
		final com.soffid.iam.am.api.Challenge challenge)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (challenge == null) {
			throw new IllegalArgumentException("com.soffid.iam.am.api.Challenge com.soffid.iam.impl.service.OTPValidationService.selectToken(com.soffid.iam.am.api.Challenge challenge) - challenge cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleSelectToken(challenge)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.am.api.Challenge) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.OTPValidationService.class).
			warn ("Error on OTPValidationService.selectToken", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on OTPValidationService.selectToken", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.am.api.Challenge handleSelectToken(com.soffid.iam.am.api.Challenge challenge) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.OTPValidationService#	 * @see com.soffid.iam.impl.service.OTPValidationService#java.lang.String generateTypeForAudit(com.soffid.iam.am.api.Challenge challenge)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.lang.String generateTypeForAudit(
		final com.soffid.iam.am.api.Challenge challenge)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (challenge == null) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.impl.service.OTPValidationService.generateTypeForAudit(com.soffid.iam.am.api.Challenge challenge) - challenge cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGenerateTypeForAudit(challenge)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.String) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.OTPValidationService.class).
			warn ("Error on OTPValidationService.generateTypeForAudit", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on OTPValidationService.generateTypeForAudit", (Throwable) __r[1]);
	}

	protected abstract java.lang.String handleGenerateTypeForAudit(com.soffid.iam.am.api.Challenge challenge) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.OTPValidationService#	 * @see com.soffid.iam.impl.service.OTPValidationService#void registerOTPHandler(com.soffid.iam.service.impl.OTPHandler handler)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void registerOTPHandler(
		final com.soffid.iam.service.impl.OTPHandler handler)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (handler == null) {
			throw new IllegalArgumentException("void com.soffid.iam.impl.service.OTPValidationService.registerOTPHandler(com.soffid.iam.service.impl.OTPHandler handler) - handler cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleRegisterOTPHandler(handler);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.OTPValidationService.class).
			warn ("Error on OTPValidationService.registerOTPHandler", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on OTPValidationService.registerOTPHandler", (Throwable) __r[1]);
	}

	protected abstract void handleRegisterOTPHandler(com.soffid.iam.service.impl.OTPHandler handler) throws Exception;

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
