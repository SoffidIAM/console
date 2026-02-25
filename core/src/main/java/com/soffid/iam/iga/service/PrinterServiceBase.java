//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p> 
 * Spring Service base class for <code>com.soffid.iam.iga.service.PrinterService</code>,
 * provides access to all services and entities referenced by this service. 
 * </p>
 * 
 * see com.soffid.iam.iga.service.PrinterService
 */
public abstract class PrinterServiceBase
	implements com.soffid.iam.iga.service.PrinterService
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

	private com.soffid.iam.base.service.AuthorizationService authorizationService;

	/**
	 * Sets reference to <code>authorizationService</code>.
	 */
	public void setAuthorizationService (com.soffid.iam.base.service.AuthorizationService authorizationService) {
		this.authorizationService = authorizationService;
	}

	/**
	 * Gets reference to <code>authorizationService</code>.
	 */
	public com.soffid.iam.base.service.AuthorizationService getAuthorizationService () {
		return authorizationService;
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

	private com.soffid.iam.am.service.NetworkService networkService;

	/**
	 * Sets reference to <code>networkService</code>.
	 */
	public void setNetworkService (com.soffid.iam.am.service.NetworkService networkService) {
		this.networkService = networkService;
	}

	/**
	 * Gets reference to <code>networkService</code>.
	 */
	public com.soffid.iam.am.service.NetworkService getNetworkService () {
		return networkService;
	}

	private com.soffid.iam.iga.model.PrinterEntityDao printerEntityDao;

	/**
	 * Sets reference to <code>printerEntityDao</code>.
	 */
	public void setPrinterEntityDao (com.soffid.iam.iga.model.PrinterEntityDao printerEntityDao) {
		this.printerEntityDao = printerEntityDao;
	}

	/**
	 * Gets reference to <code>printerEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.PrinterEntityDao getPrinterEntityDao () {
		return printerEntityDao;
	}

	private com.soffid.iam.iga.model.PrinterGroupEntityDao printerGroupEntityDao;

	/**
	 * Sets reference to <code>printerGroupEntityDao</code>.
	 */
	public void setPrinterGroupEntityDao (com.soffid.iam.iga.model.PrinterGroupEntityDao printerGroupEntityDao) {
		this.printerGroupEntityDao = printerGroupEntityDao;
	}

	/**
	 * Gets reference to <code>printerGroupEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.PrinterGroupEntityDao getPrinterGroupEntityDao () {
		return printerGroupEntityDao;
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

	private com.soffid.iam.iga.model.UserPrinterEntityDao userPrinterEntityDao;

	/**
	 * Sets reference to <code>userPrinterEntityDao</code>.
	 */
	public void setUserPrinterEntityDao (com.soffid.iam.iga.model.UserPrinterEntityDao userPrinterEntityDao) {
		this.userPrinterEntityDao = userPrinterEntityDao;
	}

	/**
	 * Gets reference to <code>userPrinterEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.UserPrinterEntityDao getUserPrinterEntityDao () {
		return userPrinterEntityDao;
	}


	/**
	 * @see com.soffid.iam.iga.service.PrinterService#	 * @see com.soffid.iam.iga.service.PrinterService#com.soffid.iam.iga.api.Printer create(com.soffid.iam.iga.api.Printer impressora)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.Printer create(
		final com.soffid.iam.iga.api.Printer impressora)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (impressora == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Printer com.soffid.iam.iga.service.PrinterService.create(com.soffid.iam.iga.api.Printer impressora) - impressora cannot be null");
		}
		if (impressora.getName() == null || impressora.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Printer com.soffid.iam.iga.service.PrinterService.create(com.soffid.iam.iga.api.Printer impressora) - impressora.name cannot be null");
		}
		if (impressora.getDescription() == null || impressora.getDescription().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Printer com.soffid.iam.iga.service.PrinterService.create(com.soffid.iam.iga.api.Printer impressora) - impressora.description cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreate(impressora)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.Printer) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.PrinterService.class).
			warn ("Error on PrinterService.create", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on PrinterService.create", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.Printer handleCreate(com.soffid.iam.iga.api.Printer impressora) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.PrinterService#	 * @see com.soffid.iam.iga.service.PrinterService#com.soffid.iam.iga.api.Printer findPrinterByPrinterName(java.lang.String codiImpressora)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.Printer findPrinterByPrinterName(
		final java.lang.String codiImpressora)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiImpressora == null || codiImpressora.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Printer com.soffid.iam.iga.service.PrinterService.findPrinterByPrinterName(java.lang.String codiImpressora) - codiImpressora cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindPrinterByPrinterName(codiImpressora)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.Printer) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.PrinterService.class).
			warn ("Error on PrinterService.findPrinterByPrinterName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on PrinterService.findPrinterByPrinterName", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.Printer handleFindPrinterByPrinterName(java.lang.String codiImpressora) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.PrinterService#	 * @see com.soffid.iam.iga.service.PrinterService#com.soffid.iam.iga.api.Printer update(com.soffid.iam.iga.api.Printer impressora)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.Printer update(
		final com.soffid.iam.iga.api.Printer impressora)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (impressora == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Printer com.soffid.iam.iga.service.PrinterService.update(com.soffid.iam.iga.api.Printer impressora) - impressora cannot be null");
		}
		if (impressora.getName() == null || impressora.getName().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Printer com.soffid.iam.iga.service.PrinterService.update(com.soffid.iam.iga.api.Printer impressora) - impressora.name cannot be null");
		}
		if (impressora.getDescription() == null || impressora.getDescription().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.Printer com.soffid.iam.iga.service.PrinterService.update(com.soffid.iam.iga.api.Printer impressora) - impressora.description cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleUpdate(impressora)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.Printer) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.PrinterService.class).
			warn ("Error on PrinterService.update", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on PrinterService.update", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.Printer handleUpdate(com.soffid.iam.iga.api.Printer impressora) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.PrinterService#	 * @see com.soffid.iam.iga.service.PrinterService#com.soffid.iam.iga.api.PrinterGroup create(com.soffid.iam.iga.api.PrinterGroup grupImpressora)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.PrinterGroup create(
		final com.soffid.iam.iga.api.PrinterGroup grupImpressora)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (grupImpressora == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.PrinterGroup com.soffid.iam.iga.service.PrinterService.create(com.soffid.iam.iga.api.PrinterGroup grupImpressora) - grupImpressora cannot be null");
		}
		if (grupImpressora.getEnabledByDefault() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.PrinterGroup com.soffid.iam.iga.service.PrinterService.create(com.soffid.iam.iga.api.PrinterGroup grupImpressora) - grupImpressora.enabledByDefault cannot be null");
		}
		if (grupImpressora.getGroupCode() == null || grupImpressora.getGroupCode().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.PrinterGroup com.soffid.iam.iga.service.PrinterService.create(com.soffid.iam.iga.api.PrinterGroup grupImpressora) - grupImpressora.groupCode cannot be null");
		}
		if (grupImpressora.getPrinterCode() == null || grupImpressora.getPrinterCode().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.PrinterGroup com.soffid.iam.iga.service.PrinterService.create(com.soffid.iam.iga.api.PrinterGroup grupImpressora) - grupImpressora.printerCode cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreate(grupImpressora)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.PrinterGroup) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.PrinterService.class).
			warn ("Error on PrinterService.create", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on PrinterService.create", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.PrinterGroup handleCreate(com.soffid.iam.iga.api.PrinterGroup grupImpressora) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.PrinterService#	 * @see com.soffid.iam.iga.service.PrinterService#com.soffid.iam.iga.api.PrinterGroup findPrinterGroupByGroupNameAndPrinterName(java.lang.String codiGrup, java.lang.String codiImpressora)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.PrinterGroup findPrinterGroupByGroupNameAndPrinterName(
		final java.lang.String codiGrup, 
		final java.lang.String codiImpressora)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiGrup == null || codiGrup.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.PrinterGroup com.soffid.iam.iga.service.PrinterService.findPrinterGroupByGroupNameAndPrinterName(java.lang.String codiGrup, java.lang.String codiImpressora) - codiGrup cannot be null");
		}
		if (codiImpressora == null || codiImpressora.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.PrinterGroup com.soffid.iam.iga.service.PrinterService.findPrinterGroupByGroupNameAndPrinterName(java.lang.String codiGrup, java.lang.String codiImpressora) - codiImpressora cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindPrinterGroupByGroupNameAndPrinterName(codiGrup, codiImpressora)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.PrinterGroup) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.PrinterService.class).
			warn ("Error on PrinterService.findPrinterGroupByGroupNameAndPrinterName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on PrinterService.findPrinterGroupByGroupNameAndPrinterName", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.PrinterGroup handleFindPrinterGroupByGroupNameAndPrinterName(java.lang.String codiGrup, java.lang.String codiImpressora) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.PrinterService#	 * @see com.soffid.iam.iga.service.PrinterService#com.soffid.iam.iga.api.PrinterGroup update(com.soffid.iam.iga.api.PrinterGroup grupImpressora)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.PrinterGroup update(
		final com.soffid.iam.iga.api.PrinterGroup grupImpressora)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (grupImpressora == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.PrinterGroup com.soffid.iam.iga.service.PrinterService.update(com.soffid.iam.iga.api.PrinterGroup grupImpressora) - grupImpressora cannot be null");
		}
		if (grupImpressora.getEnabledByDefault() == null ) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.PrinterGroup com.soffid.iam.iga.service.PrinterService.update(com.soffid.iam.iga.api.PrinterGroup grupImpressora) - grupImpressora.enabledByDefault cannot be null");
		}
		if (grupImpressora.getGroupCode() == null || grupImpressora.getGroupCode().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.PrinterGroup com.soffid.iam.iga.service.PrinterService.update(com.soffid.iam.iga.api.PrinterGroup grupImpressora) - grupImpressora.groupCode cannot be null");
		}
		if (grupImpressora.getPrinterCode() == null || grupImpressora.getPrinterCode().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.PrinterGroup com.soffid.iam.iga.service.PrinterService.update(com.soffid.iam.iga.api.PrinterGroup grupImpressora) - grupImpressora.printerCode cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleUpdate(grupImpressora)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.PrinterGroup) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.PrinterService.class).
			warn ("Error on PrinterService.update", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on PrinterService.update", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.PrinterGroup handleUpdate(com.soffid.iam.iga.api.PrinterGroup grupImpressora) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.PrinterService#	 * @see com.soffid.iam.iga.service.PrinterService#com.soffid.iam.iga.api.PrinterUser create(com.soffid.iam.iga.api.PrinterUser usuariImpressora)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.PrinterUser create(
		final com.soffid.iam.iga.api.PrinterUser usuariImpressora)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (usuariImpressora == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.PrinterUser com.soffid.iam.iga.service.PrinterService.create(com.soffid.iam.iga.api.PrinterUser usuariImpressora) - usuariImpressora cannot be null");
		}
		if (usuariImpressora.getPrinter() == null || usuariImpressora.getPrinter().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.PrinterUser com.soffid.iam.iga.service.PrinterService.create(com.soffid.iam.iga.api.PrinterUser usuariImpressora) - usuariImpressora.printer cannot be null");
		}
		if (usuariImpressora.getUser() == null || usuariImpressora.getUser().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.PrinterUser com.soffid.iam.iga.service.PrinterService.create(com.soffid.iam.iga.api.PrinterUser usuariImpressora) - usuariImpressora.user cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleCreate(usuariImpressora)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.PrinterUser) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.PrinterService.class).
			warn ("Error on PrinterService.create", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on PrinterService.create", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.PrinterUser handleCreate(com.soffid.iam.iga.api.PrinterUser usuariImpressora) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.PrinterService#	 * @see com.soffid.iam.iga.service.PrinterService#com.soffid.iam.iga.api.PrinterUser findPrinterUserByUserNameAndPrinterName(java.lang.String codiUsuari, java.lang.String codiImpressora)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.PrinterUser findPrinterUserByUserNameAndPrinterName(
		final java.lang.String codiUsuari, 
		final java.lang.String codiImpressora)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiUsuari == null || codiUsuari.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.PrinterUser com.soffid.iam.iga.service.PrinterService.findPrinterUserByUserNameAndPrinterName(java.lang.String codiUsuari, java.lang.String codiImpressora) - codiUsuari cannot be null");
		}
		if (codiImpressora == null || codiImpressora.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.PrinterUser com.soffid.iam.iga.service.PrinterService.findPrinterUserByUserNameAndPrinterName(java.lang.String codiUsuari, java.lang.String codiImpressora) - codiImpressora cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindPrinterUserByUserNameAndPrinterName(codiUsuari, codiImpressora)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.PrinterUser) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.PrinterService.class).
			warn ("Error on PrinterService.findPrinterUserByUserNameAndPrinterName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on PrinterService.findPrinterUserByUserNameAndPrinterName", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.PrinterUser handleFindPrinterUserByUserNameAndPrinterName(java.lang.String codiUsuari, java.lang.String codiImpressora) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.PrinterService#	 * @see com.soffid.iam.iga.service.PrinterService#com.soffid.iam.iga.api.PrinterUser update(com.soffid.iam.iga.api.PrinterUser usuariImpressora)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.iga.api.PrinterUser update(
		final com.soffid.iam.iga.api.PrinterUser usuariImpressora)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (usuariImpressora == null) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.PrinterUser com.soffid.iam.iga.service.PrinterService.update(com.soffid.iam.iga.api.PrinterUser usuariImpressora) - usuariImpressora cannot be null");
		}
		if (usuariImpressora.getPrinter() == null || usuariImpressora.getPrinter().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.PrinterUser com.soffid.iam.iga.service.PrinterService.update(com.soffid.iam.iga.api.PrinterUser usuariImpressora) - usuariImpressora.printer cannot be null");
		}
		if (usuariImpressora.getUser() == null || usuariImpressora.getUser().trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.iga.api.PrinterUser com.soffid.iam.iga.service.PrinterService.update(com.soffid.iam.iga.api.PrinterUser usuariImpressora) - usuariImpressora.user cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleUpdate(usuariImpressora)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.iga.api.PrinterUser) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.PrinterService.class).
			warn ("Error on PrinterService.update", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on PrinterService.update", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.iga.api.PrinterUser handleUpdate(com.soffid.iam.iga.api.PrinterUser usuariImpressora) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.PrinterService#	 * @see com.soffid.iam.iga.service.PrinterService#com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.PrinterUser> findPrinterUsers(com.soffid.zkdb.api.Query q)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.PrinterUser> findPrinterUsers(
		final com.soffid.zkdb.api.Query q)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (q == null) {
			throw new IllegalArgumentException("com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.PrinterUser> com.soffid.iam.iga.service.PrinterService.findPrinterUsers(com.soffid.zkdb.api.Query q) - q cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindPrinterUsers(q)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.PrinterUser>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.PrinterService.class).
			warn ("Error on PrinterService.findPrinterUsers", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on PrinterService.findPrinterUsers", (Throwable) __r[1]);
	}

	protected abstract com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.PrinterUser> handleFindPrinterUsers(com.soffid.zkdb.api.Query q) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.PrinterService#	 * @see com.soffid.iam.iga.service.PrinterService#com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.Printer> findPrinters(com.soffid.zkdb.api.Query query)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.Printer> findPrinters(
		final com.soffid.zkdb.api.Query query)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (query == null) {
			throw new IllegalArgumentException("com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.Printer> com.soffid.iam.iga.service.PrinterService.findPrinters(com.soffid.zkdb.api.Query query) - query cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindPrinters(query)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.Printer>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.PrinterService.class).
			warn ("Error on PrinterService.findPrinters", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on PrinterService.findPrinters", (Throwable) __r[1]);
	}

	protected abstract com.soffid.zkdb.api.PagedResult<com.soffid.iam.iga.api.Printer> handleFindPrinters(com.soffid.zkdb.api.Query query) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.PrinterService#	 * @see com.soffid.iam.iga.service.PrinterService#java.util.Collection<com.soffid.iam.iga.api.PrinterGroup> findPrintersGroupByGroupName(java.lang.String codiGrup)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.PrinterGroup> findPrintersGroupByGroupName(
		final java.lang.String codiGrup)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiGrup == null || codiGrup.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.PrinterGroup> com.soffid.iam.iga.service.PrinterService.findPrintersGroupByGroupName(java.lang.String codiGrup) - codiGrup cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindPrintersGroupByGroupName(codiGrup)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.PrinterGroup>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.PrinterService.class).
			warn ("Error on PrinterService.findPrintersGroupByGroupName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on PrinterService.findPrintersGroupByGroupName", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.PrinterGroup> handleFindPrintersGroupByGroupName(java.lang.String codiGrup) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.PrinterService#	 * @see com.soffid.iam.iga.service.PrinterService#java.util.Collection<com.soffid.iam.iga.api.Printer> findPrintersByPrinterName(java.lang.String codiImpressora)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.Printer> findPrintersByPrinterName(
		final java.lang.String codiImpressora)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindPrintersByPrinterName(codiImpressora)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.Printer>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.PrinterService.class).
			warn ("Error on PrinterService.findPrintersByPrinterName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on PrinterService.findPrintersByPrinterName", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.Printer> handleFindPrintersByPrinterName(java.lang.String codiImpressora) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.PrinterService#	 * @see com.soffid.iam.iga.service.PrinterService#java.util.Collection<com.soffid.iam.iga.api.Printer> findPrintersByFilter(java.lang.String codi, java.lang.String model, java.lang.String local, java.lang.String maquina)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.Printer> findPrintersByFilter(
		final java.lang.String codi, 
		final java.lang.String model, 
		final java.lang.String local, 
		final java.lang.String maquina)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleFindPrintersByFilter(codi, model, local, maquina)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.Printer>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.PrinterService.class).
			warn ("Error on PrinterService.findPrintersByFilter", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on PrinterService.findPrintersByFilter", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.Printer> handleFindPrintersByFilter(java.lang.String codi, java.lang.String model, java.lang.String local, java.lang.String maquina) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.PrinterService#	 * @see com.soffid.iam.iga.service.PrinterService#java.util.Collection getPrintersGroupByPrinterName(java.lang.String codiImpressora)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection getPrintersGroupByPrinterName(
		final java.lang.String codiImpressora)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiImpressora == null || codiImpressora.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection com.soffid.iam.iga.service.PrinterService.getPrintersGroupByPrinterName(java.lang.String codiImpressora) - codiImpressora cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetPrintersGroupByPrinterName(codiImpressora)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.PrinterService.class).
			warn ("Error on PrinterService.getPrintersGroupByPrinterName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on PrinterService.getPrintersGroupByPrinterName", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection handleGetPrintersGroupByPrinterName(java.lang.String codiImpressora) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.PrinterService#	 * @see com.soffid.iam.iga.service.PrinterService#java.util.Collection<com.soffid.iam.iga.api.Printer> getPrinters()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.Printer> getPrinters()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetPrinters()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.Printer>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.PrinterService.class).
			warn ("Error on PrinterService.getPrinters", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on PrinterService.getPrinters", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.Printer> handleGetPrinters() throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.PrinterService#	 * @see com.soffid.iam.iga.service.PrinterService#java.util.Collection<com.soffid.iam.iga.api.PrinterUser> getUserPrintersByPrinterName(java.lang.String codiImpressora)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public java.util.Collection<com.soffid.iam.iga.api.PrinterUser> getUserPrintersByPrinterName(
		final java.lang.String codiImpressora)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (codiImpressora == null || codiImpressora.trim().length() == 0) {
			throw new IllegalArgumentException("java.util.Collection<com.soffid.iam.iga.api.PrinterUser> com.soffid.iam.iga.service.PrinterService.getUserPrintersByPrinterName(java.lang.String codiImpressora) - codiImpressora cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetUserPrintersByPrinterName(codiImpressora)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.util.Collection<com.soffid.iam.iga.api.PrinterUser>) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.PrinterService.class).
			warn ("Error on PrinterService.getUserPrintersByPrinterName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on PrinterService.getUserPrintersByPrinterName", (Throwable) __r[1]);
	}

	protected abstract java.util.Collection<com.soffid.iam.iga.api.PrinterUser> handleGetUserPrintersByPrinterName(java.lang.String codiImpressora) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.PrinterService#	 * @see com.soffid.iam.iga.service.PrinterService#void delete(com.soffid.iam.iga.api.Printer impressora)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void delete(
		final com.soffid.iam.iga.api.Printer impressora)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (impressora == null) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.PrinterService.delete(com.soffid.iam.iga.api.Printer impressora) - impressora cannot be null");
		}
		if (impressora.getName() == null || impressora.getName().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.PrinterService.delete(com.soffid.iam.iga.api.Printer impressora) - impressora.name cannot be null");
		}
		if (impressora.getDescription() == null || impressora.getDescription().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.PrinterService.delete(com.soffid.iam.iga.api.Printer impressora) - impressora.description cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDelete(impressora);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.PrinterService.class).
			warn ("Error on PrinterService.delete", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on PrinterService.delete", (Throwable) __r[1]);
	}

	protected abstract void handleDelete(com.soffid.iam.iga.api.Printer impressora) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.PrinterService#	 * @see com.soffid.iam.iga.service.PrinterService#void delete(com.soffid.iam.iga.api.PrinterGroup grupImpressora)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void delete(
		final com.soffid.iam.iga.api.PrinterGroup grupImpressora)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (grupImpressora == null) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.PrinterService.delete(com.soffid.iam.iga.api.PrinterGroup grupImpressora) - grupImpressora cannot be null");
		}
		if (grupImpressora.getEnabledByDefault() == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.PrinterService.delete(com.soffid.iam.iga.api.PrinterGroup grupImpressora) - grupImpressora.enabledByDefault cannot be null");
		}
		if (grupImpressora.getGroupCode() == null || grupImpressora.getGroupCode().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.PrinterService.delete(com.soffid.iam.iga.api.PrinterGroup grupImpressora) - grupImpressora.groupCode cannot be null");
		}
		if (grupImpressora.getPrinterCode() == null || grupImpressora.getPrinterCode().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.PrinterService.delete(com.soffid.iam.iga.api.PrinterGroup grupImpressora) - grupImpressora.printerCode cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDelete(grupImpressora);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.PrinterService.class).
			warn ("Error on PrinterService.delete", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on PrinterService.delete", (Throwable) __r[1]);
	}

	protected abstract void handleDelete(com.soffid.iam.iga.api.PrinterGroup grupImpressora) throws Exception;

	/**
	 * @see com.soffid.iam.iga.service.PrinterService#	 * @see com.soffid.iam.iga.service.PrinterService#void delete(com.soffid.iam.iga.api.PrinterUser usuariImpressora)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		rollbackFor={java.lang.Exception.class})
	public void delete(
		final com.soffid.iam.iga.api.PrinterUser usuariImpressora)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException
	{
		if (usuariImpressora == null) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.PrinterService.delete(com.soffid.iam.iga.api.PrinterUser usuariImpressora) - usuariImpressora cannot be null");
		}
		if (usuariImpressora.getPrinter() == null || usuariImpressora.getPrinter().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.PrinterService.delete(com.soffid.iam.iga.api.PrinterUser usuariImpressora) - usuariImpressora.printer cannot be null");
		}
		if (usuariImpressora.getUser() == null || usuariImpressora.getUser().trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.iga.service.PrinterService.delete(com.soffid.iam.iga.api.PrinterUser usuariImpressora) - usuariImpressora.user cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDelete(usuariImpressora);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.iga.service.PrinterService.class).
			warn ("Error on PrinterService.delete", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on PrinterService.delete", (Throwable) __r[1]);
	}

	protected abstract void handleDelete(com.soffid.iam.iga.api.PrinterUser usuariImpressora) throws Exception;

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
