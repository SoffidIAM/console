package com.soffid.iam.doc.api;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.util.Arrays;

import com.soffid.iam.doc.exception.DocumentBeanException;
import com.soffid.iam.doc.service.DocumentService;

import es.caib.seycon.ng.exception.InternalErrorException;

public class DocumentOutputStream extends OutputStream {
	byte buffer[] = new byte [4096];
	@Override
	public void flush() throws IOException {
		if (used > 0)
			try {
				documentService.nextUploadPackage(buffer, used);
			} catch (Exception e) {
				throw new IOException("Cannot upload document", e);
			}
		used = 0;
		super.flush();
	}


	int used = 0;
	private DocumentService documentService = null;
	private com.soffid.iam.doc.service.ejb.DocumentService ejbDocumentService = null;
	
	public DocumentOutputStream (DocumentService documentService) throws DocumentBeanException, InternalErrorException
	{
		this.documentService = documentService;
		documentService.openUploadTransfer();
	}

	public DocumentOutputStream (com.soffid.iam.doc.service.ejb.DocumentService ejbDocumentService) throws DocumentBeanException, InternalErrorException
	{
		this.ejbDocumentService = ejbDocumentService;
		ejbDocumentService.openUploadTransfer();
	}
	
	@Override
	public void write(byte[] b) throws IOException {
		write (b, 0, b.length);
	}


	@Override
	public void write(byte[] b, int off, int len) throws IOException {
		if (used + len > buffer.length)
			flush ();
		if (len > buffer.length)
		{
			byte newArray [] = Arrays.copyOfRange(b, off, off+len);
			try {
				if (documentService != null)
					documentService.nextUploadPackage(newArray, newArray.length);
				if (ejbDocumentService != null)
					ejbDocumentService.nextUploadPackage(newArray, newArray.length);
			} catch (Exception e) {
				throw new IOException("Cannot upload document", e);
			}
		}
		else
		{
			System.arraycopy(b, off, buffer, used, len);
			used += len;
		}
	}


	@Override
	public void close() throws IOException {
		flush ();
		try {
			if (documentService != null)
				documentService.endUploadTransfer();
			if (ejbDocumentService != null)
				ejbDocumentService.endUploadTransfer();
			
		} catch (InternalErrorException e) {
			throw new IOException("Cannot upload document", e);
		} catch (DocumentBeanException e) {
			throw new IOException("Cannot upload document", e);
		}
		super.close();
	}


	@Override
	public void write(int b) throws IOException {
		if (used >= buffer.length)
			flush();
		buffer[used++] = (byte) b;
	}

}
