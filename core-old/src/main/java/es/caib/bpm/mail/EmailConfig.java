package es.caib.bpm.mail;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.jbpm.taskmgmt.exe.TaskInstance;

import es.caib.bpm.exception.BPMException;

public abstract class EmailConfig {
	
	protected String [] actorIds;
	protected TaskInstance task;
	
	public EmailConfig(TaskInstance task,String [] userIds) {
		this.actorIds=userIds;
		this.task=task;
	}

	public abstract String getText() throws Exception;
	
	public abstract String getSubject() throws Exception;
	
	public abstract String [] getEmails() throws Exception;
}
