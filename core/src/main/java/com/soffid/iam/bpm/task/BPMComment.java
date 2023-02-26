package com.soffid.iam.bpm.task;

import org.jbpm.graph.exe.Comment;

public class BPMComment extends Comment {
	String node;

	public BPMComment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BPMComment(String actorId, String message) {
		super(actorId, message);
		// TODO Auto-generated constructor stub
	}

	public BPMComment(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}
}
