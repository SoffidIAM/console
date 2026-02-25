//
// (C) 2013 Soffid
//
//

package com.soffid.iam.impl.service;
/**
 * Service LuceneIndexService
 */
public interface LuceneIndexService {
	public final static String SERVICE_NAME = "com.soffid.iam.impl.service.LuceneIndexService";

	/**
	 * Operation addDocument

	 * @param index 
	 * @param doc 
	 */
	void addDocument(
		final java.lang.String index, 
		final org.apache.lucene.document.Document doc)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation indexObject

	 * @param index 
	 * @param o 
	 */
	void indexObject(
		final java.lang.String index, 
		final java.lang.Object o)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation resetIndex

	 * @param index 
	 */
	void resetIndex(
		final java.lang.String index)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation search

	 * @param index 
	 * @param query 
	 * @param collector 
	 */
	void search(
		final java.lang.String index, 
		final java.lang.String query, 
		final org.apache.lucene.search.SimpleCollector collector)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation search

	 * @param index 
	 * @param query 
	 * @param collector 
	 */
	void search(
		final java.lang.String index, 
		final org.apache.lucene.search.Query query, 
		final org.apache.lucene.search.SimpleCollector collector)
			throws com.soffid.iam.exception.InternalErrorException;

}
