//
// (C) 2013 Soffid
//
//

package com.soffid.iam.base.service.ejb;
/**
 * EJB GenAIProviderService
 */
public interface GenAIProviderService

 {

	java.util.List<java.lang.String> getGenAIMetadata(
		final java.lang.String engine)
	throws com.soffid.iam.exception.InternalErrorException;

	java.util.List<java.lang.String> getGenAISentences(
		final java.lang.String engine, 
		final java.lang.String[] context, 
		final java.lang.String sentence)
	throws com.soffid.iam.exception.InternalErrorException;

	void clearCache()
	throws com.soffid.iam.exception.InternalErrorException;

}
