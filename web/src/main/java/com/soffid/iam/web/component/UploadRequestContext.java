package com.soffid.iam.web.component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.RequestContext;

public class UploadRequestContext implements RequestContext {

	private HttpServletRequest request;
	private String boundary;
	private File file;

	public UploadRequestContext(HttpServletRequest request, String boundary, File f) {
		this.request = request;
		this.boundary = boundary;
		this.file = f;
	}

	@Override
	public String getCharacterEncoding() {
		return request.getCharacterEncoding();
	}

	@Override
	public String getContentType() {
		return request.getContentType()+"; boundary="+boundary;
	}

	@Override
	public int getContentLength() {
		return request.getContentLength();
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return new FileInputStream(file);
	}

}
