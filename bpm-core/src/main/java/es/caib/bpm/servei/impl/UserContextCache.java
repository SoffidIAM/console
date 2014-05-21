package es.caib.bpm.servei.impl;

import java.io.File;
import java.io.OutputStream;
import java.util.Date;

import org.apache.commons.collections.map.LRUMap;

public class UserContextCache {
	static LRUMap map = new LRUMap(500);
	public static UserContextCache get (String user) {
		UserContextCache data = (UserContextCache) map.get(user);
		return data;
	}
	
	public static void  put (String user, UserContextCache ctx ) {
		map.put(user, ctx);
	}

	public UserContextCache() {
		super();
	}

	Date date;
	String roles [];
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
	public String[] getRoles() {
		return roles;
	}
	public void setRoles(String[] roles) {
		this.roles = roles;
	}

}
