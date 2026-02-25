//
// (c) 2014 Soffid
//
//

package com.soffid.iam.sync.model;

/**
 *  Entity TaskLogEntity
 */

public abstract class TaskLogEntity {

	/**

	 * The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute completed
	 */
	private java.lang.String completed;
	/**
	 * Gets value for attribute completed
	 */
	public java.lang.String getCompleted() {
		return this.completed;
	}
	/**
	 * Sets value for attribute completed
	 */
	public void setCompleted(java.lang.String completed) {
		this.completed = completed;
	}
	/**
	 * Attribute message
	 */
	private java.lang.String message;
	/**
	 * Gets value for attribute message
	 */
	public java.lang.String getMessage() {
		return this.message;
	}
	/**
	 * Sets value for attribute message
	 */
	public void setMessage(java.lang.String message) {
		this.message = message;
	}
	/**
	 * Attribute creationDate
	 */
	private java.util.Date creationDate;
	/**
	 * Gets value for attribute creationDate
	 */
	public java.util.Date getCreationDate() {
		return this.creationDate;
	}
	/**
	 * Sets value for attribute creationDate
	 */
	public void setCreationDate(java.util.Date creationDate) {
		this.creationDate = creationDate;
	}
	/**
	 * Attribute lastExecution
	 */
	private java.lang.Long lastExecution;
	/**
	 * Gets value for attribute lastExecution
	 */
	public java.lang.Long getLastExecution() {
		return this.lastExecution;
	}
	/**
	 * Sets value for attribute lastExecution
	 */
	public void setLastExecution(java.lang.Long lastExecution) {
		this.lastExecution = lastExecution;
	}
	/**
	 * Attribute nextExecution
	 */
	private java.lang.Long nextExecution;
	/**
	 * Gets value for attribute nextExecution
	 */
	public java.lang.Long getNextExecution() {
		return this.nextExecution;
	}
	/**
	 * Sets value for attribute nextExecution
	 */
	public void setNextExecution(java.lang.Long nextExecution) {
		this.nextExecution = nextExecution;
	}
	/**
	 * Attribute executionsNumber
	 */
	private java.lang.Long executionsNumber;
	/**
	 * Gets value for attribute executionsNumber
	 */
	public java.lang.Long getExecutionsNumber() {
		return this.executionsNumber;
	}
	/**
	 * Sets value for attribute executionsNumber
	 */
	public void setExecutionsNumber(java.lang.Long executionsNumber) {
		this.executionsNumber = executionsNumber;
	}
	/**
	 * Attribute stackTrace
	 */
	private java.lang.String stackTrace;
	/**
	 * Gets value for attribute stackTrace
	 */
	public java.lang.String getStackTrace() {
		return this.stackTrace;
	}
	/**
	 * Sets value for attribute stackTrace
	 */
	public void setStackTrace(java.lang.String stackTrace) {
		this.stackTrace = stackTrace;
	}
	/**
	 * Attribute task
	 */
	private com.soffid.iam.sync.model.TaskEntity task;
	/**
	 * Gets value for attribute task
	 */
	public com.soffid.iam.sync.model.TaskEntity getTask() {
		return this.task;
	}
	/**
	 * Sets value for attribute task
	 */
	public void setTask(com.soffid.iam.sync.model.TaskEntity task) {
		this.task = task;
	}
	/**
	 * Attribute id
	 */
	private java.lang.Long id;
	/**
	 * Gets value for attribute id
	 */
	public java.lang.Long getId() {
		return this.id;
	}
	/**
	 * Sets value for attribute id
	 */
	public void setId(java.lang.Long id) {
		this.id = id;
	}
	/**
	 * Attribute system
	 */
	private com.soffid.iam.iga.model.SystemEntity system;
	/**
	 * Gets value for attribute system
	 */
	public com.soffid.iam.iga.model.SystemEntity getSystem() {
		return this.system;
	}
	/**
	 * Sets value for attribute system
	 */
	public void setSystem(com.soffid.iam.iga.model.SystemEntity system) {
		this.system = system;
	}
	/**
	 * Returns <code>true</code> if the argument is an TaskLogEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof TaskLogEntity))
		{
			return false;
		}
		final TaskLogEntity that = (TaskLogEntity)object;
		if (this.id == null || that.getId() == null || !this.id.equals(that.getId())) 
		{
			return false;
		}
		return true;
	}
	/**
	 * Returns a hash code based on this entity's identifiers.
	 */
	public int hashCode()
	{
		int hashCode = (id == null ? super.hashCode() : id.hashCode());
		return hashCode;
	}
}
