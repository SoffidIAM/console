package com.soffid.iam.doc.api;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.util.Arrays;

import com.soffid.iam.doc.exception.DocumentBeanException;
import com.soffid.iam.doc.service.DocumentService;

import es.caib.seycon.ng.exception.InternalErrorException;

public class DocumentInputStream extends InputStream {
	@Override
	public void close() throws IOException
	{
		if (documentService != null)
		{
			try
			{
				documentService.endDownloadTransfer();
			}
			catch (InternalErrorException e)
			{
				throw new IOException(e);
			}
			catch (DocumentBeanException e)
			{
				throw new IOException(e);
			}
			documentService = null;
		}
		if (ejbDocumentService != null)
		{
			try
			{
				ejbDocumentService.endDownloadTransfer();
			}
			catch (InternalErrorException e)
			{
				throw new IOException(e);
			}
			catch (DocumentBeanException e)
			{
				throw new IOException(e);
			}
			ejbDocumentService = null;
		}
	}

	byte buffer[] = null;
	int used = 0;
	int index = 0;
	private DocumentService documentService = null;
	private com.soffid.iam.doc.service.ejb.DocumentService ejbDocumentService = null;
	
	public DocumentInputStream (DocumentService documentService) throws DocumentBeanException, InternalErrorException
	{
		this.documentService = documentService;
		documentService.openDownloadTransfer();
	}

	public DocumentInputStream (com.soffid.iam.doc.service.ejb.DocumentService ejbDocumentService) throws DocumentBeanException, InternalErrorException
	{
		this.ejbDocumentService = ejbDocumentService;
		ejbDocumentService.openDownloadTransfer();
	}

	@Override
	public int read() throws IOException
	{
		if (documentService == null && ejbDocumentService == null)
			return -1;
		if (buffer == null || index >= buffer.length)
		{
			try
			{
				if (documentService != null)
					buffer = documentService.nextDownloadPackage(4096);
				if (ejbDocumentService != null)
					buffer = ejbDocumentService.nextDownloadPackage(4096);
			}
			catch (InternalErrorException e)
			{
				throw new IOException(e);
			}
			catch (DocumentBeanException e)
			{
				throw new IOException(e);
			}
			if (buffer == null || buffer.length == 0)
			{
				close ();
				return -1;
			}
			index = 0;
		}
		byte b = buffer[index++];
		return b >= 0 ? b : b + 256;
	}

	

}
