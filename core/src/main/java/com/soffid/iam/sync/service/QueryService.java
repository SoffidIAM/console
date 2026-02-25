//
// (C) 2013 Soffid
//
//

package com.soffid.iam.sync.service;
/**
 * Service QueryService
 */
public interface QueryService {
	public final static String SERVICE_NAME = "com.soffid.iam.sync.service.QueryService";

	/**
	 * Operation queryHql

	 * @param path 
	 * @return 
	 */
	java.util.List queryHql(
		final java.lang.String path)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation query

	 * @param path 
	 * @param contentType 
	 * @param writer 
	 */
	void query(
		final java.lang.String path, 
		final java.lang.String contentType, 
		final java.io.Writer writer)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation query

	 * @param path 
	 * @param contentType 
	 * @param ipAddress 
	 * @param writer 
	 */
	void query(
		final java.lang.String path, 
		final java.lang.String contentType, 
		final java.lang.String ipAddress, 
		final java.io.Writer writer)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

}
