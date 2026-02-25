//
// (C) 2013 Soffid
//
//

package com.soffid.iam.doc.service.ejb;
/**
 * @see <code>com.soffid.iam.doc.service.DocumentService</code>,
 * @see <code>com.soffid.iam.doc.service.DocumentService</code>,
 */
@jakarta.ejb.Stateful(name="DocumentService", mappedName="openejb:/local/soffid.ejb.com.soffid.iam.doc.service.DocumentService")
@jakarta.ejb.Local(com.soffid.iam.doc.service.ejb.DocumentService.class)
@jakarta.ejb.TransactionManagement(value=jakarta.ejb.TransactionManagementType.CONTAINER)
@jakarta.ejb.TransactionAttribute(value=jakarta.ejb.TransactionAttributeType.SUPPORTS)
public class DocumentServiceBean extends org.springframework.ejb.support.AbstractStatefulSessionBean
  implements com.soffid.iam.doc.service.ejb.DocumentService
{
	private com.soffid.iam.doc.service.DocumentService documentService;

	/**
	 * @see com.soffid.iam.doc.service.DocumentService#byte[] nextDownloadPackage(int length)
	 */
	@jakarta.annotation.security.PermitAll
	public byte[] nextDownloadPackage(
		final int length)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.doc.exception.DocumentBeanException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.documentService.nextDownloadPackage(length); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.doc.exception.DocumentBeanException)
				throw (com.soffid.iam.doc.exception.DocumentBeanException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.doc.service.DocumentService#com.soffid.iam.doc.api.DocumentReference getReference()
	 */
	@jakarta.annotation.security.PermitAll
	public com.soffid.iam.doc.api.DocumentReference getReference()
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.documentService.getReference(); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.doc.service.DocumentService#java.lang.String getExternalName()
	 */
	@jakarta.annotation.security.PermitAll
	public java.lang.String getExternalName()
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.documentService.getExternalName(); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.doc.service.DocumentService#java.lang.String getFsPath()
	 */
	@jakarta.annotation.security.PermitAll
	public java.lang.String getFsPath()
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.documentService.getFsPath(); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.doc.service.DocumentService#java.lang.String getMimeType()
	 */
	@jakarta.annotation.security.PermitAll
	public java.lang.String getMimeType()
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			return this.documentService.getMimeType(); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.doc.service.DocumentService#void closeDocument()
	 */
	@jakarta.annotation.security.PermitAll
	public void closeDocument()
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			this.documentService.closeDocument(); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.doc.service.DocumentService#void createDocument(java.lang.String mimeType, java.lang.String externalName, java.lang.String application)
	 */
	@jakarta.annotation.security.PermitAll
	public void createDocument(
		final java.lang.String mimeType, 
		final java.lang.String externalName, 
		final java.lang.String application)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			this.documentService.createDocument(mimeType, externalName, application); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.doc.service.DocumentService#void deleteDocument(com.soffid.iam.doc.api.DocumentReference reference)
	 */
	@jakarta.annotation.security.PermitAll
	public void deleteDocument(
		final com.soffid.iam.doc.api.DocumentReference reference)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			this.documentService.deleteDocument(reference); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.doc.service.DocumentService#void endDownloadTransfer()
	 */
	@jakarta.annotation.security.PermitAll
	public void endDownloadTransfer()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.doc.exception.DocumentBeanException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			this.documentService.endDownloadTransfer(); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.doc.exception.DocumentBeanException)
				throw (com.soffid.iam.doc.exception.DocumentBeanException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.doc.service.DocumentService#void endUploadTransfer()
	 */
	@jakarta.annotation.security.PermitAll
	public void endUploadTransfer()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.doc.exception.DocumentBeanException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			this.documentService.endUploadTransfer(); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.doc.exception.DocumentBeanException)
				throw (com.soffid.iam.doc.exception.DocumentBeanException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.doc.service.DocumentService#void exportDocuments(java.io.OutputStream out)
	 */
	@jakarta.annotation.security.PermitAll
	public void exportDocuments(
		final java.io.OutputStream out)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			this.documentService.exportDocuments(out); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.doc.service.DocumentService#void importDocuments(java.io.InputStream out)
	 */
	@jakarta.annotation.security.PermitAll
	public void importDocuments(
		final java.io.InputStream out)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			this.documentService.importDocuments(out); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.doc.service.DocumentService#void nextUploadPackage(byte[] filePackage, int length)
	 */
	@jakarta.annotation.security.PermitAll
	public void nextUploadPackage(
		final byte[] filePackage, 
		final int length)
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.doc.exception.DocumentBeanException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			this.documentService.nextUploadPackage(filePackage, length); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.doc.exception.DocumentBeanException)
				throw (com.soffid.iam.doc.exception.DocumentBeanException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.doc.service.DocumentService#void openDocument(com.soffid.iam.doc.api.DocumentReference reference)
	 */
	@jakarta.annotation.security.PermitAll
	public void openDocument(
		final com.soffid.iam.doc.api.DocumentReference reference)
		throws com.soffid.iam.exception.InternalErrorException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			this.documentService.openDocument(reference); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.doc.service.DocumentService#void openDownloadTransfer()
	 */
	@jakarta.annotation.security.PermitAll
	public void openDownloadTransfer()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.doc.exception.DocumentBeanException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			this.documentService.openDownloadTransfer(); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.doc.exception.DocumentBeanException)
				throw (com.soffid.iam.doc.exception.DocumentBeanException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * @see com.soffid.iam.doc.service.DocumentService#void openUploadTransfer()
	 */
	@jakarta.annotation.security.PermitAll
	public void openUploadTransfer()
		throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.doc.exception.DocumentBeanException
	{
		com.soffid.iam.PrincipalStore.set(super.getSessionContext().getCallerPrincipal());
		try
		{
			this.documentService.openUploadTransfer(); 
		}
		catch (Exception exception)
		{
			final Throwable cause = getRootCause(exception);
			if (cause instanceof com.soffid.iam.exception.InternalErrorException)
				throw (com.soffid.iam.exception.InternalErrorException) cause;
			if (cause instanceof com.soffid.iam.doc.exception.DocumentBeanException)
				throw (com.soffid.iam.doc.exception.DocumentBeanException) cause;
			if (exception instanceof RuntimeException)
				throw (RuntimeException)exception;
			throw new jakarta.ejb.EJBException (exception);
		}
	}
	/**
	 * Initizlizes been
	 *
	 * @see org.springframework.ejb.support.AbstractStatelessSessionBean#onEjbCreate()
	 */

	@jakarta.annotation.PostConstruct
	public void createBean() 
	{
		onEjbCreate();
	}


	protected void onEjbCreate()
	{

		loadBeanFactory();
		this.documentService = (com.soffid.iam.doc.service.DocumentService)
		getBeanFactory().getBean("com.soffid.iam.doc.service.DocumentService");
	}

	
	/**
	/**
	 * Every Session bean needs to implement this method
	 *
	 * @see jakarta.ejb.SessionBean#ejbPassivate()
	 */
	public void ejbPassivate()
	{
	}

	
	/**
	/**
	 * Every Session bean needs to implement this method
	 *
	 * @see jakarta.ejb.SessionBean#ejbActivate()
	 */
	public void ejbActivate()
	{
	}

	
	/**
	 * Override default BeanFactoryLocator implementation to
	 * provide singleton loading of the application context Bean factory.
	 *
	 * @see jakarta.ejb.SessionBean#setSessionContext(jakarta.ejb.SessionContext)
	 */
	public void setSessionContext(jakarta.ejb.SessionContext sessionContext)
	{
		super.setSessionContext(sessionContext);
		super.setBeanFactoryLocator(
		org.springframework.context.access.ContextSingletonBeanFactoryLocator.getInstance("beanRefFactory.xml"));
		super.setBeanFactoryLocatorKey("beanRefFactory");
	}

	org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog (getClass());

	/**
	 * Finds the root cause of the parent exception
	 * by traveling up the exception tree.
	 */	private static Throwable getRootCause(Throwable throwable)
	{
		if (throwable != null)
		{
			// Reflectively get any exception causes.
			try
			{
				Throwable targetException = null;
				// java.lang.reflect.InvocationTargetException
				String exceptionProperty = "targetException";
				if (org.apache.commons.beanutils.PropertyUtils.isReadable(throwable, exceptionProperty))
				{
					targetException = (Throwable)org.apache.commons.beanutils.PropertyUtils.getProperty(throwable, exceptionProperty);
				}
				else
				{
					exceptionProperty = "causedByException";
					//jakarta.ejb.EJBException
					if (org.apache.commons.beanutils.PropertyUtils.isReadable(throwable, exceptionProperty))
					{
						targetException = (Throwable)org.apache.commons.beanutils.PropertyUtils.getProperty(throwable, exceptionProperty);
					}
				}
				if (targetException != null)
				{
					throwable = targetException;
				}
			}
			catch (Exception exception)
			{
				// just print the exception and continue
				exception.printStackTrace();
			}
			if (throwable.getCause() != null)
			{
				throwable = throwable.getCause();
				throwable = getRootCause(throwable);
			}
		}
		return throwable;
	}
}
