//
// (C) 2013 Soffid
//
//

package com.soffid.iam.base.service;
/**
 * Service RecycleBeanService
 */
public interface RecycleBeanService {
	public final static String SERVICE_NAME = "com.soffid.iam.base.service.RecycleBeanService";

	/**
	 * Operation purge

	 * @param date 
	 */
	void purge(
		final java.util.Date date)
			throws com.soffid.iam.exception.InternalErrorException;

}
