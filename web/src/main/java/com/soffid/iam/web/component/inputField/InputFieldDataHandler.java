package com.soffid.iam.web.component.inputField;

import java.io.UnsupportedEncodingException;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.EventListener;

import com.soffid.iam.api.AsyncList;
import com.soffid.iam.api.DataType;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.Databox;

public abstract class InputFieldDataHandler<E> {
	protected DataType dataType;

	public InputFieldDataHandler(DataType dataType) {
		this.dataType = dataType;
	}
	
	public abstract E getObject(String name, String filter) throws Exception;
	public abstract String getDescription(String name, String filter) throws Exception;
	public abstract AsyncList<E> search(String text, String filter) throws Exception; 
	public abstract void openFinder(String filter, boolean multiple, Component databox, EventListener listener) throws Exception;
	public abstract String followLink(String value) throws UnsupportedEncodingException;
	
	String buildJsonFilter (String attribute, String value, String filter) {
		String q = attribute+" eq \""+escapeJson(value)+"\""; //$NON-NLS-1$ //$NON-NLS-2$
		if (filter == null || filter.trim().isEmpty())
			return q;
		else
		{
			q = q + " and ("+filter+")"; //$NON-NLS-1$ //$NON-NLS-2$
			return q;
		}
	}

	String buildJsonFilter (String attribute1, String value1, String attribute2, String value2, String filter) {
		String q = attribute1+" eq \""+escapeJson(value1)+"\" and "+ //$NON-NLS-1$ //$NON-NLS-2$
			attribute2+" eq \""+escapeJson(value2)+"\""; //$NON-NLS-1$ //$NON-NLS-2$
		if (filter == null || filter.trim().isEmpty())
			return q;
		else
		{
			q = q + " and ("+filter+")"; //$NON-NLS-1$ //$NON-NLS-2$
			return q;
		}
	}

	private String escapeJson (String s)
	{
		return s.replaceAll("\\\\", "\\\\\\\\").replaceAll("\"", "\\\\\""); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	}

	protected String quote(String name) {
		return name
				.replaceAll("\\\\", "\\\\\\\\")
				.replaceAll("\"", "\\\\\"");
	}

	public String[] objectToNameDescription(Object o) {
		return toNameDescription((E) o);
	}

	public abstract String[] toNameDescription(E o);
	

}
