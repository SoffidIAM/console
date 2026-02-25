//
// (C) 2013 Soffid
//
//

package com.soffid.iam.am.service;
/**
 * Service BrowserService
 */
public interface BrowserService {
	public final static String REMOTE_PATH = "/seycon/com.soffid.iam.am.service.BrowserService";

	public final static String SERVICE_NAME = "com.soffid.iam.am.service.BrowserService";

	/**
	 * Operation create

	 * @param browser 
	 * @return 
	 */
	com.soffid.iam.am.api.Browser create(
		final com.soffid.iam.am.api.Browser browser)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findByHost

	 * @param host 
	 * @return 
	 */
	com.soffid.iam.am.api.Browser findByHost(
		final com.soffid.iam.am.api.Host host)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findBySerialNumber

	 * @param serialNumber 
	 * @return 
	 */
	com.soffid.iam.am.api.Browser findBySerialNumber(
		final java.lang.String serialNumber)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation registerBrowser

	 * @param serialNumber 
	 * @param ipAddress 
	 * @param userAgent 
	 * @return 
	 */
	com.soffid.iam.am.api.Browser registerBrowser(
		final java.lang.String serialNumber, 
		final java.lang.String ipAddress, 
		final java.lang.String userAgent)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation update

	 * @param browser 
	 * @return 
	 */
	com.soffid.iam.am.api.Browser update(
		final com.soffid.iam.am.api.Browser browser)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findBrowsers

	 * @param q 
	 * @return 
	 */
	com.soffid.zkdb.api.PagedResult<com.soffid.iam.am.api.Browser> findBrowsers(
		final com.soffid.zkdb.api.Query q)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation handleFindSessionsByBrowserId

	 * @param browserId 
	 * @return 
	 */
	java.util.List<com.soffid.iam.am.api.Session> handleFindSessionsByBrowserId(
		final java.lang.Long browserId)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation delete

	 * @param browser 
	 */
	void delete(
		final com.soffid.iam.am.api.Browser browser)
			throws com.soffid.iam.exception.InternalErrorException;

}
