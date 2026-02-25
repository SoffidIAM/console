//
// (C) 2020 Soffid
//
//

package com.soffid.iam.bpm.api;
/**
 * ValueObject TaskInstance
 **/
public class TaskInstance

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute id

	 */
	private long id;

	/**
	 * Attribute processClassLoader

	 */
	private transient com.soffid.iam.bpm.api.UIClassLoader processClassLoader;

	/**
	 * Attribute processName

	 */
	private java.lang.String processName;

	/**
	 * Attribute processId

	 */
	private long processId;

	/**
	 * Attribute processNumber

	 */
	private java.lang.Long processNumber;

	/**
	 * Attribute processDefinition

	 */
	private long processDefinition;

	/**
	 * Attribute dummyTask

	 */
	private boolean dummyTask;

	/**
	 * Attribute name

	 */
	private java.lang.String name;

	/**
	 * Attribute description

	 */
	private java.lang.String description;

	/**
	 * Attribute actorId

	 */
	private java.lang.String actorId;

	/**
	 * Attribute create

	 */
	private java.util.Date create;

	/**
	 * Attribute start

	 */
	private java.util.Date start;

	/**
	 * Attribute end

	 */
	private java.util.Date end;

	/**
	 * Attribute dueDate

	 */
	private java.util.Date dueDate;

	/**
	 * Attribute priority

	 */
	private int priority;

	/**
	 * Attribute cancelled

	 */
	private boolean cancelled;

	/**
	 * Attribute open

	 */
	private boolean open;

	/**
	 * Attribute signalling

	 */
	private boolean signalling;

	/**
	 * Attribute blocking

	 */
	private boolean blocking;

	/**
	 * Attribute swimlane

	 */
	private java.lang.String swimlane;

	/**
	 * Attribute pooledActors

	 */
	private java.util.Set<java.lang.String> pooledActors;

	/**
	 * Attribute variables

	 */
	private java.util.Map variables;

	/**
	 * Attribute transitions

	 */
	private java.lang.String[] transitions;

	public TaskInstance()
	{
	}

	public TaskInstance(long id, com.soffid.iam.bpm.api.UIClassLoader processClassLoader, java.lang.String processName, long processId, java.lang.Long processNumber, long processDefinition, boolean dummyTask, java.lang.String name, java.lang.String description, java.lang.String actorId, java.util.Date create, java.util.Date start, java.util.Date end, java.util.Date dueDate, int priority, boolean cancelled, boolean open, boolean signalling, boolean blocking, java.lang.String swimlane, java.util.Set<java.lang.String> pooledActors, java.util.Map variables, java.lang.String[] transitions)
	{
		super();
		this.id = id;
		this.processClassLoader = processClassLoader;
		this.processName = processName;
		this.processId = processId;
		this.processNumber = processNumber;
		this.processDefinition = processDefinition;
		this.dummyTask = dummyTask;
		this.name = name;
		this.description = description;
		this.actorId = actorId;
		this.create = create;
		this.start = start;
		this.end = end;
		this.dueDate = dueDate;
		this.priority = priority;
		this.cancelled = cancelled;
		this.open = open;
		this.signalling = signalling;
		this.blocking = blocking;
		this.swimlane = swimlane;
		this.pooledActors = pooledActors;
		this.variables = variables;
		this.transitions = transitions;
	}

	public TaskInstance(long id, long processDefinition, boolean dummyTask, boolean cancelled, boolean open, boolean signalling, boolean blocking)
	{
		super();
		this.id = id;
		this.processDefinition = processDefinition;
		this.dummyTask = dummyTask;
		this.cancelled = cancelled;
		this.open = open;
		this.signalling = signalling;
		this.blocking = blocking;
	}

	public TaskInstance(TaskInstance otherBean)
	{
		this(otherBean.id, otherBean.processClassLoader, otherBean.processName, otherBean.processId, otherBean.processNumber, otherBean.processDefinition, otherBean.dummyTask, otherBean.name, otherBean.description, otherBean.actorId, otherBean.create, otherBean.start, otherBean.end, otherBean.dueDate, otherBean.priority, otherBean.cancelled, otherBean.open, otherBean.signalling, otherBean.blocking, otherBean.swimlane, otherBean.pooledActors, otherBean.variables, otherBean.transitions);
	}

	/**
	 * Gets value for attribute id
	 */
	public long getId() {
		return this.id;
	}

	/**
	 * Sets value for attribute id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Gets value for attribute processClassLoader
	 */
	public com.soffid.iam.bpm.api.UIClassLoader getProcessClassLoader() {
		return this.processClassLoader;
	}

	/**
	 * Sets value for attribute processClassLoader
	 */
	public void setProcessClassLoader(com.soffid.iam.bpm.api.UIClassLoader processClassLoader) {
		this.processClassLoader = processClassLoader;
	}

	/**
	 * Gets value for attribute processName
	 */
	public java.lang.String getProcessName() {
		return this.processName;
	}

	/**
	 * Sets value for attribute processName
	 */
	public void setProcessName(java.lang.String processName) {
		this.processName = processName;
	}

	/**
	 * Gets value for attribute processId
	 */
	public long getProcessId() {
		return this.processId;
	}

	/**
	 * Sets value for attribute processId
	 */
	public void setProcessId(long processId) {
		this.processId = processId;
	}

	/**
	 * Gets value for attribute processNumber
	 */
	public java.lang.Long getProcessNumber() {
		return this.processNumber;
	}

	/**
	 * Sets value for attribute processNumber
	 */
	public void setProcessNumber(java.lang.Long processNumber) {
		this.processNumber = processNumber;
	}

	/**
	 * Gets value for attribute processDefinition
	 */
	public long getProcessDefinition() {
		return this.processDefinition;
	}

	/**
	 * Sets value for attribute processDefinition
	 */
	public void setProcessDefinition(long processDefinition) {
		this.processDefinition = processDefinition;
	}

	/**
	 * Gets value for attribute dummyTask
	 */
	public boolean isDummyTask() {
		return this.dummyTask;
	}

	/**
	 * Sets value for attribute dummyTask
	 */
	public void setDummyTask(boolean dummyTask) {
		this.dummyTask = dummyTask;
	}

	/**
	 * Gets value for attribute name
	 */
	public java.lang.String getName() {
		return this.name;
	}

	/**
	 * Sets value for attribute name
	 */
	public void setName(java.lang.String name) {
		this.name = name;
	}

	/**
	 * Gets value for attribute description
	 */
	public java.lang.String getDescription() {
		return this.description;
	}

	/**
	 * Sets value for attribute description
	 */
	public void setDescription(java.lang.String description) {
		this.description = description;
	}

	/**
	 * Gets value for attribute actorId
	 */
	public java.lang.String getActorId() {
		return this.actorId;
	}

	/**
	 * Sets value for attribute actorId
	 */
	public void setActorId(java.lang.String actorId) {
		this.actorId = actorId;
	}

	/**
	 * Gets value for attribute create
	 */
	public java.util.Date getCreate() {
		return this.create;
	}

	/**
	 * Sets value for attribute create
	 */
	public void setCreate(java.util.Date create) {
		this.create = create;
	}

	/**
	 * Gets value for attribute start
	 */
	public java.util.Date getStart() {
		return this.start;
	}

	/**
	 * Sets value for attribute start
	 */
	public void setStart(java.util.Date start) {
		this.start = start;
	}

	/**
	 * Gets value for attribute end
	 */
	public java.util.Date getEnd() {
		return this.end;
	}

	/**
	 * Sets value for attribute end
	 */
	public void setEnd(java.util.Date end) {
		this.end = end;
	}

	/**
	 * Gets value for attribute dueDate
	 */
	public java.util.Date getDueDate() {
		return this.dueDate;
	}

	/**
	 * Sets value for attribute dueDate
	 */
	public void setDueDate(java.util.Date dueDate) {
		this.dueDate = dueDate;
	}

	/**
	 * Gets value for attribute priority
	 */
	public int getPriority() {
		return this.priority;
	}

	/**
	 * Sets value for attribute priority
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}

	/**
	 * Gets value for attribute cancelled
	 */
	public boolean isCancelled() {
		return this.cancelled;
	}

	/**
	 * Sets value for attribute cancelled
	 */
	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

	/**
	 * Gets value for attribute open
	 */
	public boolean isOpen() {
		return this.open;
	}

	/**
	 * Sets value for attribute open
	 */
	public void setOpen(boolean open) {
		this.open = open;
	}

	/**
	 * Gets value for attribute signalling
	 */
	public boolean isSignalling() {
		return this.signalling;
	}

	/**
	 * Sets value for attribute signalling
	 */
	public void setSignalling(boolean signalling) {
		this.signalling = signalling;
	}

	/**
	 * Gets value for attribute blocking
	 */
	public boolean isBlocking() {
		return this.blocking;
	}

	/**
	 * Sets value for attribute blocking
	 */
	public void setBlocking(boolean blocking) {
		this.blocking = blocking;
	}

	/**
	 * Gets value for attribute swimlane
	 */
	public java.lang.String getSwimlane() {
		return this.swimlane;
	}

	/**
	 * Sets value for attribute swimlane
	 */
	public void setSwimlane(java.lang.String swimlane) {
		this.swimlane = swimlane;
	}

	/**
	 * Gets value for attribute pooledActors
	 */
	public java.util.Set<java.lang.String> getPooledActors() {
		return this.pooledActors;
	}

	/**
	 * Sets value for attribute pooledActors
	 */
	public void setPooledActors(java.util.Set<java.lang.String> pooledActors) {
		this.pooledActors = pooledActors;
	}

	/**
	 * Gets value for attribute variables
	 */
	public java.util.Map getVariables() {
		return this.variables;
	}

	/**
	 * Sets value for attribute variables
	 */
	public void setVariables(java.util.Map variables) {
		this.variables = variables;
	}

	/**
	 * Gets value for attribute transitions
	 */
	public java.lang.String[] getTransitions() {
		return this.transitions;
	}

	/**
	 * Sets value for attribute transitions
	 */
	public void setTransitions(java.lang.String[] transitions) {
		this.transitions = transitions;
	}

	/**
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[id: ");
		b.append (this.id);
		b.append (", processClassLoader: ");
		b.append (this.processClassLoader);
		b.append (", processName: ");
		b.append (this.processName);
		b.append (", processId: ");
		b.append (this.processId);
		b.append (", processNumber: ");
		b.append (this.processNumber);
		b.append (", processDefinition: ");
		b.append (this.processDefinition);
		b.append (", dummyTask: ");
		b.append (this.dummyTask);
		b.append (", name: ");
		b.append (this.name);
		b.append (", description: ");
		b.append (this.description);
		b.append (", actorId: ");
		b.append (this.actorId);
		b.append (", create: ");
		b.append (this.create);
		b.append (", start: ");
		b.append (this.start);
		b.append (", end: ");
		b.append (this.end);
		b.append (", dueDate: ");
		b.append (this.dueDate);
		b.append (", priority: ");
		b.append (this.priority);
		b.append (", cancelled: ");
		b.append (this.cancelled);
		b.append (", open: ");
		b.append (this.open);
		b.append (", signalling: ");
		b.append (this.signalling);
		b.append (", blocking: ");
		b.append (this.blocking);
		b.append (", swimlane: ");
		b.append (this.swimlane);
		b.append (", pooledActors: ");
		b.append (this.pooledActors);
		b.append (", variables: ");
		b.append (this.variables);
		b.append (", transitions: ");
		b.append (this.transitions);
		b.append ("]");
		return b.toString();
	}

}
