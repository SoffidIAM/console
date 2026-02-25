//
// (C) 2020 Soffid
//
//

package com.soffid.iam.base.api;
/**
 * ValueObject TasksGrid
 **/
public class TasksGrid

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute agents

	 */
	private java.util.List<java.lang.String> agents;

	/**
	 * Attribute tasks

	 */
	private java.util.List<com.soffid.iam.sync.api.SyncserverTask> tasks;

	public TasksGrid()
	{
	}

	public TasksGrid(java.util.List<java.lang.String> agents, java.util.List<com.soffid.iam.sync.api.SyncserverTask> tasks)
	{
		super();
		this.agents = agents;
		this.tasks = tasks;
	}

	public TasksGrid(TasksGrid otherBean)
	{
		this(otherBean.agents, otherBean.tasks);
	}

	/**
	 * Gets value for attribute agents
	 */
	public java.util.List<java.lang.String> getAgents() {
		return this.agents;
	}

	/**
	 * Sets value for attribute agents
	 */
	public void setAgents(java.util.List<java.lang.String> agents) {
		this.agents = agents;
	}

	/**
	 * Gets value for attribute tasks
	 */
	public java.util.List<com.soffid.iam.sync.api.SyncserverTask> getTasks() {
		return this.tasks;
	}

	/**
	 * Sets value for attribute tasks
	 */
	public void setTasks(java.util.List<com.soffid.iam.sync.api.SyncserverTask> tasks) {
		this.tasks = tasks;
	}

	/**
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[agents: ");
		b.append (this.agents);
		b.append (", tasks: ");
		b.append (this.tasks);
		b.append ("]");
		return b.toString();
	}

}
