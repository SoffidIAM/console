package com.soffid.iam.bpm.job;

public class NotLoggedThread extends Thread{

	/**
	 * 
	 */
	public NotLoggedThread() {
		super();
		org.jboss.security.SecurityAssociation.clear();
		//org.jboss.security.SecurityAssociation.getSubject();
		//java.security.AccessController.getContext();
		//Thread.currentThread();
	}

	/**
	 * @param target
	 * @param name
	 */
	public NotLoggedThread(Runnable target, String name) {
		super(target, name);
		org.jboss.security.SecurityAssociation.clear();
		//org.jboss.security.SecurityAssociation.getSubject();
		//java.security.AccessController.getContext();
		//Thread.currentThread();
	}

	/**
	 * @param target
	 */
	public NotLoggedThread(Runnable target) {
		super(target);
		org.jboss.security.SecurityAssociation.clear();
		//org.jboss.security.SecurityAssociation.getSubject();
		//java.security.AccessController.getContext();
		//Thread.currentThread();
	}

	/**
	 * @param name
	 */
	public NotLoggedThread(String name) {
		super(name);
		org.jboss.security.SecurityAssociation.clear();
		//org.jboss.security.SecurityAssociation.getSubject();
		//java.security.AccessController.getContext();
		//Thread.currentThread();
	}

	/**
	 * @param group
	 * @param target
	 * @param name
	 * @param stackSize
	 */
	public NotLoggedThread(ThreadGroup group, Runnable target, String name, long stackSize) {
		super(group, target, name, stackSize);
		org.jboss.security.SecurityAssociation.clear();
		//org.jboss.security.SecurityAssociation.getSubject();
		//java.security.AccessController.getContext();
		//Thread.currentThread();
	}

	/**
	 * @param group
	 * @param target
	 * @param name
	 */
	public NotLoggedThread(ThreadGroup group, Runnable target, String name) {
		super(group, target, name);
		org.jboss.security.SecurityAssociation.clear();
		//org.jboss.security.SecurityAssociation.getSubject();
		//java.security.AccessController.getContext();
		//Thread.currentThread();
	}

	/**
	 * @param group
	 * @param target
	 */
	public NotLoggedThread(ThreadGroup group, Runnable target) {
		super(group, target);
		org.jboss.security.SecurityAssociation.clear();
		//org.jboss.security.SecurityAssociation.getSubject();
		//java.security.AccessController.getContext();
		//Thread.currentThread();
	}

	/**
	 * @param group
	 * @param name
	 */
	public NotLoggedThread(ThreadGroup group, String name) {
		super(group, name);
		org.jboss.security.SecurityAssociation.clear();
		//org.jboss.security.SecurityAssociation.getSubject();
		//java.security.AccessController.getContext();
		//Thread.currentThread();
	}

	
	
}
