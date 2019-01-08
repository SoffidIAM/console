package com.soffid.iam.bpm.service.impl;

import java.io.File;
import java.io.OutputStream;
import java.util.Date;

import org.apache.commons.collections.map.LRUMap;

public class UserContextCache {
	public UserContextCache() {
		super();
	}

	Date date;
	String messages [];
	File tempFile;
	public File getTempFile() {
		return tempFile;
	}

	public void setTempFile(File tempFile) {
		this.tempFile = tempFile;
	}

	public OutputStream getOutputStream() {
		return outputStream;
	}

	public void setOutputStream(OutputStream outputStream) {
		this.outputStream = outputStream;
	}

	OutputStream outputStream;
	
	
	public String[] getMessages() {
		return messages;
	}

	public void setMessages(String[] messages) {
		this.messages = messages;
	}

	public Date getExpirationDate() {
		return date;
	}
	public void setExpirationDate(Date date) {
		this.date = date;
	}
}
