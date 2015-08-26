package es.caib.bpm.entity;

import org.jbpm.logging.log.CompositeLog;

public class AuthenticationLog extends CompositeLog {
	private String actorId;

	public String getActorId() {
		return actorId;
	}

	public void setActorId(String actorId) {
		this.actorId = actorId;
	}
}
