package com.soffid.iam.bpm.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.hibernate.engine.SessionImplementor;

public class IndexSession {
	Set documents = new HashSet();
	Set processes = new HashSet();
	boolean inFlush = false;
	
	public Set getDocuments() {
		return documents;
	}
	public void setDocuments(Set documents) {
		this.documents = documents;
	}
	public Set getProcesses() {
		return processes;
	}
	public void setProcesses(Set processes) {
		this.processes = processes;
	}
	public boolean isInFlush() {
		return inFlush;
	}
	public void setInFlush(boolean inFlush) {
		this.inFlush = inFlush;
	}
	
	private static Map m  = Collections.synchronizedMap(new HashMap ());
	public static IndexSession getSesion(SessionImplementor sessionImplementor)
	{
		IndexSession s = (IndexSession) m.get(sessionImplementor);
		if (s == null)
		{
			s = new IndexSession();
			m.put(sessionImplementor, s);
		}
		return s;
	}
	
	public void clear (SessionImplementor sessionImplementor)
	{
		inFlush = false;
		m.remove(sessionImplementor);
		processes.clear();
		documents.clear();
	}
}
