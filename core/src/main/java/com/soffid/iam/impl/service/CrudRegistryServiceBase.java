//
// (C) 2013 Soffid
//
//

package com.soffid.iam.impl.service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p> 
 * Spring Service base class for <code>com.soffid.iam.impl.service.CrudRegistryService</code>,
 * provides access to all services and entities referenced by this service. 
 * </p>
 * 
 * see com.soffid.iam.impl.service.CrudRegistryService
 */
public abstract class CrudRegistryServiceBase
	implements com.soffid.iam.impl.service.CrudRegistryService
 {

	/**
	 * @see com.soffid.iam.impl.service.CrudRegistryService#	 * @see com.soffid.iam.impl.service.CrudRegistryService#<E> com.soffid.zkdb.api.CrudHandler<E> getHandler(java.lang.Class<E> cl)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public <E> com.soffid.zkdb.api.CrudHandler<E> getHandler(
		final java.lang.Class<E> cl)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (cl == null) {
			throw new IllegalArgumentException("com.soffid.zkdb.api.CrudHandler<E> com.soffid.iam.impl.service.CrudRegistryService.getHandler(java.lang.Class<E> cl) - cl cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetHandler(cl)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.zkdb.api.CrudHandler<E>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.CrudRegistryService.class).
			warn ("Error on CrudRegistryService.getHandler", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on CrudRegistryService.getHandler", (Throwable) __r[1]);
	}

	protected abstract <E> com.soffid.zkdb.api.CrudHandler<E> handleGetHandler(java.lang.Class<E> cl) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.CrudRegistryService#	 * @see com.soffid.iam.impl.service.CrudRegistryService#<E> com.soffid.zkdb.api.CrudHandler<E> getHandler(java.lang.String className)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public <E> com.soffid.zkdb.api.CrudHandler<E> getHandler(
		final java.lang.String className)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (className == null || className.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.zkdb.api.CrudHandler<E> com.soffid.iam.impl.service.CrudRegistryService.getHandler(java.lang.String className) - className cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetHandler(className)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.zkdb.api.CrudHandler<E>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.CrudRegistryService.class).
			warn ("Error on CrudRegistryService.getHandler", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on CrudRegistryService.getHandler", (Throwable) __r[1]);
	}

	protected abstract <E> com.soffid.zkdb.api.CrudHandler<E> handleGetHandler(java.lang.String className) throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.CrudRegistryService#	 * @see com.soffid.iam.impl.service.CrudRegistryService#void registerDefaultHandlers()
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void registerDefaultHandlers()
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleRegisterDefaultHandlers();
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.CrudRegistryService.class).
			warn ("Error on CrudRegistryService.registerDefaultHandlers", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on CrudRegistryService.registerDefaultHandlers", (Throwable) __r[1]);
	}

	protected abstract void handleRegisterDefaultHandlers() throws Exception;

	/**
	 * @see com.soffid.iam.impl.service.CrudRegistryService#	 * @see com.soffid.iam.impl.service.CrudRegistryService#<E> void registerHandler(java.lang.Class<E> cl, com.soffid.zkdb.api.CrudHandler<E> handler)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public <E> void registerHandler(
		final java.lang.Class<E> cl, 
		final com.soffid.zkdb.api.CrudHandler<E> handler)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (cl == null) {
			throw new IllegalArgumentException("void com.soffid.iam.impl.service.CrudRegistryService.registerHandler(java.lang.Class<E> cl, com.soffid.zkdb.api.CrudHandler<E> handler) - cl cannot be null");
		}
		if (handler == null) {
			throw new IllegalArgumentException("void com.soffid.iam.impl.service.CrudRegistryService.registerHandler(java.lang.Class<E> cl, com.soffid.zkdb.api.CrudHandler<E> handler) - handler cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleRegisterHandler(cl, handler);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.impl.service.CrudRegistryService.class).
			warn ("Error on CrudRegistryService.registerHandler", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on CrudRegistryService.registerHandler", (Throwable) __r[1]);
	}

	protected abstract <E> void handleRegisterHandler(java.lang.Class<E> cl, com.soffid.zkdb.api.CrudHandler<E> handler) throws Exception;

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
