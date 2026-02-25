//
// (C) 2013 Soffid
//
//

package com.soffid.iam.base.service;
/**
 * Service GenAIProviderService
 */
public interface GenAIProviderService {
	public final static String SERVICE_NAME = "com.soffid.iam.base.service.GenAIProviderService";

	/**
	 * Operation getGenAIMetadata

	 * @param engine 
	 * @return 
	 */
	java.util.List<java.lang.String> getGenAIMetadata(
		final java.lang.String engine)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getGenAISentences

	 * @param engine 
	 * @param context 
	 * @param sentence 
	 * @return 
	 */
	java.util.List<java.lang.String> getGenAISentences(
		final java.lang.String engine, 
		final java.lang.String[] context, 
		final java.lang.String sentence)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation clearCache

	 */
	void clearCache()
			throws com.soffid.iam.exception.InternalErrorException;

}
