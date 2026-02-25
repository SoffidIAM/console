//
// (C) 2013 Soffid
//
//

package com.soffid.iam.service.impl;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p> 
 * Spring Service base class for <code>com.soffid.iam.service.impl.AttributeValidationService</code>,
 * provides access to all services and entities referenced by this service. 
 * </p>
 * 
 * see com.soffid.iam.service.impl.AttributeValidationService
 */
public abstract class AttributeValidationServiceBase
	implements com.soffid.iam.service.impl.AttributeValidationService
 {
	private com.soffid.iam.iga.model.CustomObjectEntityDao customObjectEntityDao;

	/**
	 * Sets reference to <code>customObjectEntityDao</code>.
	 */
	public void setCustomObjectEntityDao (com.soffid.iam.iga.model.CustomObjectEntityDao customObjectEntityDao) {
		this.customObjectEntityDao = customObjectEntityDao;
	}

	/**
	 * Gets reference to <code>customObjectEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.CustomObjectEntityDao getCustomObjectEntityDao () {
		return customObjectEntityDao;
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

	private com.soffid.iam.am.model.HostEntityDao hostEntityDao;

	/**
	 * Sets reference to <code>hostEntityDao</code>.
	 */
	public void setHostEntityDao (com.soffid.iam.am.model.HostEntityDao hostEntityDao) {
		this.hostEntityDao = hostEntityDao;
	}

	/**
	 * Gets reference to <code>hostEntityDao</code>.
	 */
	public com.soffid.iam.am.model.HostEntityDao getHostEntityDao () {
		return hostEntityDao;
	}

	private com.soffid.iam.iga.model.InformationSystemEntityDao informationSystemEntityDao;

	/**
	 * Sets reference to <code>informationSystemEntityDao</code>.
	 */
	public void setInformationSystemEntityDao (com.soffid.iam.iga.model.InformationSystemEntityDao informationSystemEntityDao) {
		this.informationSystemEntityDao = informationSystemEntityDao;
	}

	/**
	 * Gets reference to <code>informationSystemEntityDao</code>.
	 */
	public com.soffid.iam.iga.model.InformationSystemEntityDao getInformationSystemEntityDao () {
		return informationSystemEntityDao;
	}

	private com.soffid.iam.am.model.NetworkEntityDao networkEntityDao;

	/**
	 * Sets reference to <code>networkEntityDao</code>.
	 */
	public void setNetworkEntityDao (com.soffid.iam.am.model.NetworkEntityDao networkEntityDao) {
		this.networkEntityDao = networkEntityDao;
	}

	/**
	 * Gets reference to <code>networkEntityDao</code>.
	 */
	public com.soffid.iam.am.model.NetworkEntityDao getNetworkEntityDao () {
		return networkEntityDao;
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

	private com.soffid.iam.base.model.UserTypeEntityDao userTypeEntityDao;

	/**
	 * Sets reference to <code>userTypeEntityDao</code>.
	 */
	public void setUserTypeEntityDao (com.soffid.iam.base.model.UserTypeEntityDao userTypeEntityDao) {
		this.userTypeEntityDao = userTypeEntityDao;
	}

	/**
	 * Gets reference to <code>userTypeEntityDao</code>.
	 */
	public com.soffid.iam.base.model.UserTypeEntityDao getUserTypeEntityDao () {
		return userTypeEntityDao;
	}


	/**
	 * @see com.soffid.iam.service.impl.AttributeValidationService#	 * @see com.soffid.iam.service.impl.AttributeValidationService#void validate(com.soffid.iam.base.model.AccountMetadataEntity metadata, java.lang.Object value)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void validate(
		final com.soffid.iam.base.model.AccountMetadataEntity metadata, 
		final java.lang.Object value)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (metadata == null) {
			throw new IllegalArgumentException("void com.soffid.iam.service.impl.AttributeValidationService.validate(com.soffid.iam.base.model.AccountMetadataEntity metadata, java.lang.Object value) - metadata cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleValidate(metadata, value);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.service.impl.AttributeValidationService.class).
			warn ("Error on AttributeValidationService.validate", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AttributeValidationService.validate", (Throwable) __r[1]);
	}

	protected abstract void handleValidate(com.soffid.iam.base.model.AccountMetadataEntity metadata, java.lang.Object value) throws Exception;

	/**
	 * @see com.soffid.iam.service.impl.AttributeValidationService#	 * @see com.soffid.iam.service.impl.AttributeValidationService#void validate(com.soffid.iam.iga.model.MetaDataEntity metadata, java.lang.Object value)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void validate(
		final com.soffid.iam.iga.model.MetaDataEntity metadata, 
		final java.lang.Object value)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (metadata == null) {
			throw new IllegalArgumentException("void com.soffid.iam.service.impl.AttributeValidationService.validate(com.soffid.iam.iga.model.MetaDataEntity metadata, java.lang.Object value) - metadata cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleValidate(metadata, value);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.service.impl.AttributeValidationService.class).
			warn ("Error on AttributeValidationService.validate", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on AttributeValidationService.validate", (Throwable) __r[1]);
	}

	protected abstract void handleValidate(com.soffid.iam.iga.model.MetaDataEntity metadata, java.lang.Object value) throws Exception;

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
