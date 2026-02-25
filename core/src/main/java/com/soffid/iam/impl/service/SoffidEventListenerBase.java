//
// (C) 2013 Soffid
//
//

package com.soffid.iam.impl.service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p> 
 * Spring Service base class for <code>com.soffid.iam.impl.service.SoffidEventListener</code>,
 * provides access to all services and entities referenced by this service. 
 * </p>
 * 
 * see com.soffid.iam.impl.service.SoffidEventListener
 */
public abstract class SoffidEventListenerBase
	implements com.soffid.iam.impl.service.SoffidEventListener
 {

	/**
	 * @see com.soffid.iam.impl.service.SoffidEventListener#	 * @see com.soffid.iam.impl.service.SoffidEventListener#void onGrant(com.soffid.iam.iga.model.RoleAccountEntity grant)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void onGrant(
		final com.soffid.iam.iga.model.RoleAccountEntity grant)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (grant == null) {
			throw new IllegalArgumentException("void com.soffid.iam.impl.service.SoffidEventListener.onGrant(com.soffid.iam.iga.model.RoleAccountEntity grant) - grant cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleOnGrant(grant);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.SoffidEventListener.class).
			warn ("Error on SoffidEventListener.onGrant", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SoffidEventListener.onGrant", (Throwable) __r[1]);
	}

	protected abstract void handleOnGrant(com.soffid.iam.iga.model.RoleAccountEntity grant) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.SoffidEventListener#	 * @see com.soffid.iam.impl.service.SoffidEventListener#void onRevoke(com.soffid.iam.iga.model.RoleAccountEntity grant)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void onRevoke(
		final com.soffid.iam.iga.model.RoleAccountEntity grant)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (grant == null) {
			throw new IllegalArgumentException("void com.soffid.iam.impl.service.SoffidEventListener.onRevoke(com.soffid.iam.iga.model.RoleAccountEntity grant) - grant cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleOnRevoke(grant);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.SoffidEventListener.class).
			warn ("Error on SoffidEventListener.onRevoke", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SoffidEventListener.onRevoke", (Throwable) __r[1]);
	}

	protected abstract void handleOnRevoke(com.soffid.iam.iga.model.RoleAccountEntity grant) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.SoffidEventListener#	 * @see com.soffid.iam.impl.service.SoffidEventListener#void onUserChange(com.soffid.iam.base.model.UserEntity user)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void onUserChange(
		final com.soffid.iam.base.model.UserEntity user)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (user == null) {
			throw new IllegalArgumentException("void com.soffid.iam.impl.service.SoffidEventListener.onUserChange(com.soffid.iam.base.model.UserEntity user) - user cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleOnUserChange(user);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.SoffidEventListener.class).
			warn ("Error on SoffidEventListener.onUserChange", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on SoffidEventListener.onUserChange", (Throwable) __r[1]);
	}

	protected abstract void handleOnUserChange(com.soffid.iam.base.model.UserEntity user) throws Exception;

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
