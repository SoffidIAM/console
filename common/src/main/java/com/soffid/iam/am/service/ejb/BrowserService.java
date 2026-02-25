//
// (C) 2013 Soffid
//
//

package com.soffid.iam.am.service.ejb;
/**
 * EJB BrowserService
 */
public interface BrowserService

 {

	com.soffid.iam.am.api.Browser findByHost(
		final com.soffid.iam.am.api.Host host)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.am.api.Browser update(
		final com.soffid.iam.am.api.Browser browser)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.zkdb.api.PagedResult<com.soffid.iam.am.api.Browser> findBrowsers(
		final com.soffid.zkdb.api.Query q)
	throws com.soffid.iam.exception.InternalErrorException;

	void delete(
		final com.soffid.iam.am.api.Browser browser)
	throws com.soffid.iam.exception.InternalErrorException;

}
