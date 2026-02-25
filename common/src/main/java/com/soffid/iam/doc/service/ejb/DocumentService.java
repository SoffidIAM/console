//
// (C) 2013 Soffid
//
//

package com.soffid.iam.doc.service.ejb;
/**
 * EJB DocumentService
 */
public interface DocumentService

 {

	byte[] nextDownloadPackage(
		final int length)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.doc.exception.DocumentBeanException;

	com.soffid.iam.doc.api.DocumentReference getReference()
	throws com.soffid.iam.exception.InternalErrorException;

	java.lang.String getExternalName()
	throws com.soffid.iam.exception.InternalErrorException;

	java.lang.String getFsPath()
	throws com.soffid.iam.exception.InternalErrorException;

	java.lang.String getMimeType()
	throws com.soffid.iam.exception.InternalErrorException;

	void closeDocument()
	throws com.soffid.iam.exception.InternalErrorException;

	void createDocument(
		final java.lang.String mimeType, 
		final java.lang.String externalName, 
		final java.lang.String application)
	throws com.soffid.iam.exception.InternalErrorException;

	void deleteDocument(
		final com.soffid.iam.doc.api.DocumentReference reference)
	throws com.soffid.iam.exception.InternalErrorException;

	void endDownloadTransfer()
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.doc.exception.DocumentBeanException;

	void endUploadTransfer()
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.doc.exception.DocumentBeanException;

	void exportDocuments(
		final java.io.OutputStream out)
	throws com.soffid.iam.exception.InternalErrorException;

	void importDocuments(
		final java.io.InputStream out)
	throws com.soffid.iam.exception.InternalErrorException;

	void nextUploadPackage(
		final byte[] filePackage, 
		final int length)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.doc.exception.DocumentBeanException;

	void openDocument(
		final com.soffid.iam.doc.api.DocumentReference reference)
	throws com.soffid.iam.exception.InternalErrorException;

	void openDownloadTransfer()
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.doc.exception.DocumentBeanException;

	void openUploadTransfer()
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.doc.exception.DocumentBeanException;

}
