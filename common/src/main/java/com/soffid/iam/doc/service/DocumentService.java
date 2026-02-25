//
// (C) 2013 Soffid
//
//

package com.soffid.iam.doc.service;
/**
 * Service DocumentService
 */
public interface DocumentService {
	public final static String SERVICE_NAME = "com.soffid.iam.doc.service.DocumentService";

	/**
	 * Operation nextDownloadPackage

	 * @param length 
	 * @return 
	 */
	byte[] nextDownloadPackage(
		final int length)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.doc.exception.DocumentBeanException;

	/**
	 * Operation getReference

	 * @return 
	 */
	com.soffid.iam.doc.api.DocumentReference getReference()
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getExternalName

	 * @return 
	 */
	java.lang.String getExternalName()
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getFsPath

	 * @return 
	 */
	java.lang.String getFsPath()
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getMimeType

	 * @return 
	 */
	java.lang.String getMimeType()
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation closeDocument

	 */
	void closeDocument()
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation createDocument

	 * @param mimeType 
	 * @param externalName 
	 * @param application 
	 */
	void createDocument(
		final java.lang.String mimeType, 
		final java.lang.String externalName, 
		final java.lang.String application)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation deleteDocument

	 * @param reference 
	 */
	void deleteDocument(
		final com.soffid.iam.doc.api.DocumentReference reference)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation endDownloadTransfer

	 */
	void endDownloadTransfer()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.doc.exception.DocumentBeanException;

	/**
	 * Operation endUploadTransfer

	 */
	void endUploadTransfer()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.doc.exception.DocumentBeanException;

	/**
	 * Operation exportDocuments

	 * @param out 
	 */
	void exportDocuments(
		final java.io.OutputStream out)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation importDocuments

	 * @param out 
	 */
	void importDocuments(
		final java.io.InputStream out)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation nextUploadPackage

	 * @param filePackage 
	 * @param length 
	 */
	void nextUploadPackage(
		final byte[] filePackage, 
		final int length)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.doc.exception.DocumentBeanException;

	/**
	 * Operation openDocument

	 * @param reference 
	 */
	void openDocument(
		final com.soffid.iam.doc.api.DocumentReference reference)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation openDownloadTransfer

	 */
	void openDownloadTransfer()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.doc.exception.DocumentBeanException;

	/**
	 * Operation openUploadTransfer

	 */
	void openUploadTransfer()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.doc.exception.DocumentBeanException;

}
