package com.soffid.iam.bpm.job;

public class NotLoggedThread extends Thread{

	/**
	 * 
	 */
	public NotLoggedThread() {
		super();
	}

	/**
	 * @param target
	 * @param name
	 */
	public NotLoggedThread(Runnable target, String name) {
		super(target, name);
	}

	/**
	 * @param target
	 */
	public NotLoggedThread(Runnable target) {
		super(target);
	}

	/**
	 * @param name
	 */
	public NotLoggedThread(String name) {
		super(name);
	}

	/**
	 * @param group
	 * @param target
	 * @param name
	 * @param stackSize
	 */
	public NotLoggedThread(ThreadGroup group, Runnable target, String name, long stackSize) {
		super(group, target, name, stackSize);
	}

	/**
	 * @param group
	 * @param target
	 * @param name
	 */
	public NotLoggedThread(ThreadGroup group, Runnable target, String name) {
		super(group, target, name);
	}

	/**
	 * @param group
	 * @param target
	 */
	public NotLoggedThread(ThreadGroup group, Runnable target) {
		super(group, target);
	}

	/**
	 * @param group
	 * @param name
	 */
	public NotLoggedThread(ThreadGroup group, String name) {
		super(group, name);
	}

	
	
}
