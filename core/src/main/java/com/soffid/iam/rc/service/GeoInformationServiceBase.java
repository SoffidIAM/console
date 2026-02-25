//
// (C) 2013 Soffid
//
//

package com.soffid.iam.rc.service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p> 
 * Spring Service base class for <code>com.soffid.iam.rc.service.GeoInformationService</code>,
 * provides access to all services and entities referenced by this service. 
 * </p>
 * 
 * see com.soffid.iam.rc.service.GeoInformationService
 */
public abstract class GeoInformationServiceBase
	implements com.soffid.iam.rc.service.GeoInformationService
 {
	private com.soffid.iam.rc.model.GeoInformationEntityDao geoInformationEntityDao;

	/**
	 * Sets reference to <code>geoInformationEntityDao</code>.
	 */
	public void setGeoInformationEntityDao (com.soffid.iam.rc.model.GeoInformationEntityDao geoInformationEntityDao) {
		this.geoInformationEntityDao = geoInformationEntityDao;
	}

	/**
	 * Gets reference to <code>geoInformationEntityDao</code>.
	 */
	public com.soffid.iam.rc.model.GeoInformationEntityDao getGeoInformationEntityDao () {
		return geoInformationEntityDao;
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


	/**
	 * @see com.soffid.iam.rc.service.GeoInformationService#	 * @see com.soffid.iam.rc.service.GeoInformationService#com.soffid.iam.rc.api.GeoInformation getGeoInformation(java.lang.String ip)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.rc.api.GeoInformation getGeoInformation(
		final java.lang.String ip)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (ip == null || ip.trim().length() == 0) {
			throw new IllegalArgumentException("com.soffid.iam.rc.api.GeoInformation com.soffid.iam.rc.service.GeoInformationService.getGeoInformation(java.lang.String ip) - ip cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetGeoInformation(ip)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.rc.api.GeoInformation) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.rc.service.GeoInformationService.class).
			warn ("Error on GeoInformationService.getGeoInformation", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on GeoInformationService.getGeoInformation", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.rc.api.GeoInformation handleGetGeoInformation(java.lang.String ip) throws Exception;

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
