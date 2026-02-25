//
// (C) 2013 Soffid
//
//

package com.soffid.iam.impl.service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p> 
 * Spring Service base class for <code>com.soffid.iam.impl.service.AccountNameGenerator</code>,
 * provides access to all services and entities referenced by this service. 
 * </p>
 * 
 * see com.soffid.iam.impl.service.AccountNameGenerator
 */
public abstract class AccountNameGeneratorBase
	implements com.soffid.iam.impl.service.AccountNameGenerator
 {

	/**
	 * @see com.soffid.iam.impl.service.AccountNameGenerator#	 * @see com.soffid.iam.impl.service.AccountNameGenerator#boolean needsAccount(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.iga.model.SystemEntity dispatcher)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public boolean needsAccount(
		final com.soffid.iam.base.model.UserEntity user, 
		final com.soffid.iam.iga.model.SystemEntity dispatcher)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (user == null) {
			throw new IllegalArgumentException("boolean com.soffid.iam.impl.service.AccountNameGenerator.needsAccount(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.iga.model.SystemEntity dispatcher) - user cannot be null");
		}
		if (dispatcher == null) {
			throw new IllegalArgumentException("boolean com.soffid.iam.impl.service.AccountNameGenerator.needsAccount(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.iga.model.SystemEntity dispatcher) - dispatcher cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleNeedsAccount(user, dispatcher)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return ((Boolean) __r[0]).booleanValue();
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.AccountNameGenerator.class).
			warn ("Error on AccountNameGenerator.needsAccount", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountNameGenerator.needsAccount", (Throwable) __r[1]);
	}

	protected abstract boolean handleNeedsAccount(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.iga.model.SystemEntity dispatcher) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.AccountNameGenerator#	 * @see com.soffid.iam.impl.service.AccountNameGenerator#java.lang.String getAccountName(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.iga.model.SystemEntity dispatcher, com.soffid.iam.iga.model.UserDomainEntity userDomain)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.lang.String getAccountName(
		final com.soffid.iam.base.model.UserEntity user, 
		final com.soffid.iam.iga.model.SystemEntity dispatcher, 
		final com.soffid.iam.iga.model.UserDomainEntity userDomain)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (user == null) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.impl.service.AccountNameGenerator.getAccountName(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.iga.model.SystemEntity dispatcher, com.soffid.iam.iga.model.UserDomainEntity userDomain) - user cannot be null");
		}
		if (dispatcher == null) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.impl.service.AccountNameGenerator.getAccountName(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.iga.model.SystemEntity dispatcher, com.soffid.iam.iga.model.UserDomainEntity userDomain) - dispatcher cannot be null");
		}
		if (userDomain == null) {
			throw new IllegalArgumentException("java.lang.String com.soffid.iam.impl.service.AccountNameGenerator.getAccountName(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.iga.model.SystemEntity dispatcher, com.soffid.iam.iga.model.UserDomainEntity userDomain) - userDomain cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetAccountName(user, dispatcher, userDomain)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.String) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.AccountNameGenerator.class).
			warn ("Error on AccountNameGenerator.getAccountName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AccountNameGenerator.getAccountName", (Throwable) __r[1]);
	}

	protected abstract java.lang.String handleGetAccountName(com.soffid.iam.base.model.UserEntity user, com.soffid.iam.iga.model.SystemEntity dispatcher, com.soffid.iam.iga.model.UserDomainEntity userDomain) throws Exception;

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
