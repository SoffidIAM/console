package es.caib.bpm.vo;

import java.util.Date;

public class ProcessLog {
	long processId;
	Date date;
	String action;
	String user;
	
	
	public long getProcessId() {
		return processId;
	}
	public void setProcessId(long processId) {
		this.processId = processId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getUser() {
		if (user == null)
			return "-"; //$NON-NLS-1$
		else
			return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
}
