//
// (C) 2013 Soffid
//
//

package com.soffid.iam.doc.service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p> 
 * Spring Service base class for <code>com.soffid.iam.doc.service.DocumentService</code>,
 * provides access to all services and entities referenced by this service. 
 * </p>
 * 
 * see com.soffid.iam.doc.service.DocumentService
 */
public abstract class DocumentServiceBase
	implements com.soffid.iam.doc.service.DocumentService
 {
	private com.soffid.iam.doc.model.DocSignDao docSignDao;

	/**
	 * Sets reference to <code>docSignDao</code>.
	 */
	public void setDocSignDao (com.soffid.iam.doc.model.DocSignDao docSignDao) {
		this.docSignDao = docSignDao;
	}

	/**
	 * Gets reference to <code>docSignDao</code>.
	 */
	public com.soffid.iam.doc.model.DocSignDao getDocSignDao () {
		return docSignDao;
	}

	private com.soffid.iam.doc.model.DocumentEntityDao documentEntityDao;

	/**
	 * Sets reference to <code>documentEntityDao</code>.
	 */
	public void setDocumentEntityDao (com.soffid.iam.doc.model.DocumentEntityDao documentEntityDao) {
		this.documentEntityDao = documentEntityDao;
	}

	/**
	 * Gets reference to <code>documentEntityDao</code>.
	 */
	public com.soffid.iam.doc.model.DocumentEntityDao getDocumentEntityDao () {
		return documentEntityDao;
	}

	private com.soffid.iam.doc.model.FileSystemDao fileSystemDao;

	/**
	 * Sets reference to <code>fileSystemDao</code>.
	 */
	public void setFileSystemDao (com.soffid.iam.doc.model.FileSystemDao fileSystemDao) {
		this.fileSystemDao = fileSystemDao;
	}

	/**
	 * Gets reference to <code>fileSystemDao</code>.
	 */
	public com.soffid.iam.doc.model.FileSystemDao getFileSystemDao () {
		return fileSystemDao;
	}


	/**
	 * @see com.soffid.iam.doc.service.DocumentService#	 * @see com.soffid.iam.doc.service.DocumentService#byte[] nextDownloadPackage(int length)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		readOnly=true)
	public byte[] nextDownloadPackage(
		final int length)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.doc.exception.DocumentBeanException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleNextDownloadPackage(length)};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (byte[]) __r[0];
		if (__r[1] instanceof com.soffid.iam.doc.exception.DocumentBeanException) 
			throw (com.soffid.iam.doc.exception.DocumentBeanException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.doc.service.DocumentService.class).
			warn ("Error on DocumentService.nextDownloadPackage", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DocumentService.nextDownloadPackage", (Throwable) __r[1]);
	}

	protected abstract byte[] handleNextDownloadPackage(int length) throws Exception;

	/**
	 * @see com.soffid.iam.doc.service.DocumentService#	 * @see com.soffid.iam.doc.service.DocumentService#com.soffid.iam.doc.api.DocumentReference getReference()
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.doc.api.DocumentReference getReference()
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetReference()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (com.soffid.iam.doc.api.DocumentReference) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.doc.service.DocumentService.class).
			warn ("Error on DocumentService.getReference", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DocumentService.getReference", (Throwable) __r[1]);
	}

	protected abstract com.soffid.iam.doc.api.DocumentReference handleGetReference() throws Exception;

	/**
	 * @see com.soffid.iam.doc.service.DocumentService#	 * @see com.soffid.iam.doc.service.DocumentService#java.lang.String getExternalName()
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.lang.String getExternalName()
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetExternalName()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.String) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.doc.service.DocumentService.class).
			warn ("Error on DocumentService.getExternalName", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DocumentService.getExternalName", (Throwable) __r[1]);
	}

	protected abstract java.lang.String handleGetExternalName() throws Exception;

	/**
	 * @see com.soffid.iam.doc.service.DocumentService#	 * @see com.soffid.iam.doc.service.DocumentService#java.lang.String getFsPath()
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.lang.String getFsPath()
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetFsPath()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.String) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.doc.service.DocumentService.class).
			warn ("Error on DocumentService.getFsPath", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DocumentService.getFsPath", (Throwable) __r[1]);
	}

	protected abstract java.lang.String handleGetFsPath() throws Exception;

	/**
	 * @see com.soffid.iam.doc.service.DocumentService#	 * @see com.soffid.iam.doc.service.DocumentService#java.lang.String getMimeType()
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.lang.String getMimeType()
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					return new Object[] {handleGetMimeType()};
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r.length == 1 ) 
			return (java.lang.String) __r[0];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.doc.service.DocumentService.class).
			warn ("Error on DocumentService.getMimeType", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DocumentService.getMimeType", (Throwable) __r[1]);
	}

	protected abstract java.lang.String handleGetMimeType() throws Exception;

	/**
	 * @see com.soffid.iam.doc.service.DocumentService#	 * @see com.soffid.iam.doc.service.DocumentService#void closeDocument()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRES_NEW)
	public void closeDocument()
		throws com.soffid.iam.exception.InternalErrorException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleCloseDocument();
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.doc.service.DocumentService.class).
			warn ("Error on DocumentService.closeDocument", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DocumentService.closeDocument", (Throwable) __r[1]);
	}

	protected abstract void handleCloseDocument() throws Exception;

	/**
	 * @see com.soffid.iam.doc.service.DocumentService#	 * @see com.soffid.iam.doc.service.DocumentService#void createDocument(java.lang.String mimeType, java.lang.String externalName, java.lang.String application)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRES_NEW)
	public void createDocument(
		final java.lang.String mimeType, 
		final java.lang.String externalName, 
		final java.lang.String application)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (mimeType == null || mimeType.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.doc.service.DocumentService.createDocument(java.lang.String mimeType, java.lang.String externalName, java.lang.String application) - mimeType cannot be null");
		}
		if (externalName == null || externalName.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.doc.service.DocumentService.createDocument(java.lang.String mimeType, java.lang.String externalName, java.lang.String application) - externalName cannot be null");
		}
		if (application == null || application.trim().length() == 0) {
			throw new IllegalArgumentException("void com.soffid.iam.doc.service.DocumentService.createDocument(java.lang.String mimeType, java.lang.String externalName, java.lang.String application) - application cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleCreateDocument(mimeType, externalName, application);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.doc.service.DocumentService.class).
			warn ("Error on DocumentService.createDocument", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DocumentService.createDocument", (Throwable) __r[1]);
	}

	protected abstract void handleCreateDocument(java.lang.String mimeType, java.lang.String externalName, java.lang.String application) throws Exception;

	/**
	 * @see com.soffid.iam.doc.service.DocumentService#	 * @see com.soffid.iam.doc.service.DocumentService#void deleteDocument(com.soffid.iam.doc.api.DocumentReference reference)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRES_NEW, 
		noRollbackFor={com.soffid.iam.exception.InternalErrorException.class,com.soffid.iam.doc.exception.DocumentBeanException.class})
	public void deleteDocument(
		final com.soffid.iam.doc.api.DocumentReference reference)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (reference == null) {
			throw new IllegalArgumentException("void com.soffid.iam.doc.service.DocumentService.deleteDocument(com.soffid.iam.doc.api.DocumentReference reference) - reference cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleDeleteDocument(reference);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.doc.service.DocumentService.class).
			warn ("Error on DocumentService.deleteDocument", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DocumentService.deleteDocument", (Throwable) __r[1]);
	}

	protected abstract void handleDeleteDocument(com.soffid.iam.doc.api.DocumentReference reference) throws Exception;

	/**
	 * @see com.soffid.iam.doc.service.DocumentService#	 * @see com.soffid.iam.doc.service.DocumentService#void endDownloadTransfer()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		readOnly=true)
	public void endDownloadTransfer()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.doc.exception.DocumentBeanException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleEndDownloadTransfer();
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.doc.exception.DocumentBeanException) 
			throw (com.soffid.iam.doc.exception.DocumentBeanException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.doc.service.DocumentService.class).
			warn ("Error on DocumentService.endDownloadTransfer", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DocumentService.endDownloadTransfer", (Throwable) __r[1]);
	}

	protected abstract void handleEndDownloadTransfer() throws Exception;

	/**
	 * @see com.soffid.iam.doc.service.DocumentService#	 * @see com.soffid.iam.doc.service.DocumentService#void endUploadTransfer()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRES_NEW)
	public void endUploadTransfer()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.doc.exception.DocumentBeanException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleEndUploadTransfer();
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.doc.exception.DocumentBeanException) 
			throw (com.soffid.iam.doc.exception.DocumentBeanException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.doc.service.DocumentService.class).
			warn ("Error on DocumentService.endUploadTransfer", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DocumentService.endUploadTransfer", (Throwable) __r[1]);
	}

	protected abstract void handleEndUploadTransfer() throws Exception;

	/**
	 * @see com.soffid.iam.doc.service.DocumentService#	 * @see com.soffid.iam.doc.service.DocumentService#void exportDocuments(java.io.OutputStream out)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.NEVER, 
		readOnly=true)
	public void exportDocuments(
		final java.io.OutputStream out)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (out == null) {
			throw new IllegalArgumentException("void com.soffid.iam.doc.service.DocumentService.exportDocuments(java.io.OutputStream out) - out cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleExportDocuments(out);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.doc.service.DocumentService.class).
			warn ("Error on DocumentService.exportDocuments", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DocumentService.exportDocuments", (Throwable) __r[1]);
	}

	protected abstract void handleExportDocuments(java.io.OutputStream out) throws Exception;

	/**
	 * @see com.soffid.iam.doc.service.DocumentService#	 * @see com.soffid.iam.doc.service.DocumentService#void importDocuments(java.io.InputStream out)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.NEVER)
	public void importDocuments(
		final java.io.InputStream out)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (out == null) {
			throw new IllegalArgumentException("void com.soffid.iam.doc.service.DocumentService.importDocuments(java.io.InputStream out) - out cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleImportDocuments(out);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.doc.service.DocumentService.class).
			warn ("Error on DocumentService.importDocuments", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DocumentService.importDocuments", (Throwable) __r[1]);
	}

	protected abstract void handleImportDocuments(java.io.InputStream out) throws Exception;

	/**
	 * @see com.soffid.iam.doc.service.DocumentService#	 * @see com.soffid.iam.doc.service.DocumentService#void nextUploadPackage(byte[] filePackage, int length)
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRES_NEW)
	public void nextUploadPackage(
		final byte[] filePackage, 
		final int length)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.doc.exception.DocumentBeanException
	{
		if (filePackage == null ) {
			throw new IllegalArgumentException("void com.soffid.iam.doc.service.DocumentService.nextUploadPackage(byte[] filePackage, int length) - filePackage cannot be empty");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleNextUploadPackage(filePackage, length);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.doc.exception.DocumentBeanException) 
			throw (com.soffid.iam.doc.exception.DocumentBeanException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.doc.service.DocumentService.class).
			warn ("Error on DocumentService.nextUploadPackage", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DocumentService.nextUploadPackage", (Throwable) __r[1]);
	}

	protected abstract void handleNextUploadPackage(byte[] filePackage, int length) throws Exception;

	/**
	 * @see com.soffid.iam.doc.service.DocumentService#	 * @see com.soffid.iam.doc.service.DocumentService#void openDocument(com.soffid.iam.doc.api.DocumentReference reference)
	 */
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void openDocument(
		final com.soffid.iam.doc.api.DocumentReference reference)
		throws com.soffid.iam.exception.InternalErrorException
	{
		if (reference == null) {
			throw new IllegalArgumentException("void com.soffid.iam.doc.service.DocumentService.openDocument(com.soffid.iam.doc.api.DocumentReference reference) - reference cannot be null");
		}
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleOpenDocument(reference);
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.doc.service.DocumentService.class).
			warn ("Error on DocumentService.openDocument", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DocumentService.openDocument", (Throwable) __r[1]);
	}

	protected abstract void handleOpenDocument(com.soffid.iam.doc.api.DocumentReference reference) throws Exception;

	/**
	 * @see com.soffid.iam.doc.service.DocumentService#	 * @see com.soffid.iam.doc.service.DocumentService#void openDownloadTransfer()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRED, 
		readOnly=true)
	public void openDownloadTransfer()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.doc.exception.DocumentBeanException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleOpenDownloadTransfer();
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.doc.exception.DocumentBeanException) 
			throw (com.soffid.iam.doc.exception.DocumentBeanException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.doc.service.DocumentService.class).
			warn ("Error on DocumentService.openDownloadTransfer", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DocumentService.openDownloadTransfer", (Throwable) __r[1]);
	}

	protected abstract void handleOpenDownloadTransfer() throws Exception;

	/**
	 * @see com.soffid.iam.doc.service.DocumentService#	 * @see com.soffid.iam.doc.service.DocumentService#void openUploadTransfer()
	 */
	// Trasaction attribute 
	@Transactional(isolation=org.springframework.transaction.annotation.Isolation.DEFAULT,
		propagation=org.springframework.transaction.annotation.Propagation.REQUIRES_NEW)
	public void openUploadTransfer()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.doc.exception.DocumentBeanException
	{
		Object[] __r = (Object[]) java.security.AccessController.doPrivileged(new java.security.PrivilegedAction<Object>() {
			public Object run() {
				try {
					handleOpenUploadTransfer();
					return null;
				} catch (Throwable th) {
					return new Object[] {null,th};
				}
			}
		});
		if (__r == null) return;
		if (__r[1] instanceof com.soffid.iam.doc.exception.DocumentBeanException) 
			throw (com.soffid.iam.doc.exception.DocumentBeanException) __r[1];
		if (__r[1] instanceof com.soffid.iam.exception.InternalErrorException) 
			throw (com.soffid.iam.exception.InternalErrorException) __r[1];
		org.apache.commons.logging.LogFactory.getLog(com.soffid.iam.doc.service.DocumentService.class).
			warn ("Error on DocumentService.openUploadTransfer", (Throwable) __r[1]);
		throw new com.soffid.iam.exception.InternalErrorException(
			"Unexpected error on DocumentService.openUploadTransfer", (Throwable) __r[1]);
	}

	protected abstract void handleOpenUploadTransfer() throws Exception;

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
