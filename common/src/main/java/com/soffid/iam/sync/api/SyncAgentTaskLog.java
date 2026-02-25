//
// (C) 2020 Soffid
//
//

package com.soffid.iam.sync.api;
/**
 * ValueObject SyncAgentTaskLog
 **/
public class SyncAgentTaskLog

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute taskId

	 */
	private java.lang.Long taskId;

	/**
	 * Attribute taskDescription

	 */
	private java.lang.String taskDescription;

	/**
	 * Attribute agentCode

	 */
	private java.lang.String agentCode;

	/**
	 * Attribute complete

	 */
	private java.lang.String complete;

	/**
	 * Attribute message

	 */
	private java.lang.String message;

	/**
	 * Attribute creationDate

	 */
	private java.util.Calendar creationDate;

	/**
	 * Attribute lastExecution

	 */
	private java.lang.Long lastExecution;

	/**
	 * Attribute lastExecutionDate

	 */
	private java.util.Calendar lastExecutionDate;

	/**
	 * Attribute nextExecution

	 */
	private java.lang.Long nextExecution;

	/**
	 * Attribute nextExecutionDate

	 */
	private java.util.Calendar nextExecutionDate;

	/**
	 * Attribute executionsNumber

	 */
	private java.lang.Long executionsNumber;

	/**
	 * Attribute priority

	 */
	private java.lang.Long priority;

	/**
	 * Attribute stackTrace

	 */
	private java.lang.String stackTrace;

	/**
	 * Attribute server

	 */
	private java.lang.String server;

	public SyncAgentTaskLog()
	{
	}

	public SyncAgentTaskLog(java.lang.Long taskId, java.lang.String taskDescription, java.lang.String agentCode, java.lang.String complete, java.lang.String message, java.util.Calendar creationDate, java.lang.Long lastExecution, java.util.Calendar lastExecutionDate, java.lang.Long nextExecution, java.util.Calendar nextExecutionDate, java.lang.Long executionsNumber, java.lang.Long priority, java.lang.String stackTrace, java.lang.String server)
	{
		super();
		this.taskId = taskId;
		this.taskDescription = taskDescription;
		this.agentCode = agentCode;
		this.complete = complete;
		this.message = message;
		this.creationDate = creationDate;
		this.lastExecution = lastExecution;
		this.lastExecutionDate = lastExecutionDate;
		this.nextExecution = nextExecution;
		this.nextExecutionDate = nextExecutionDate;
		this.executionsNumber = executionsNumber;
		this.priority = priority;
		this.stackTrace = stackTrace;
		this.server = server;
	}

	public SyncAgentTaskLog(SyncAgentTaskLog otherBean)
	{
		this(otherBean.taskId, otherBean.taskDescription, otherBean.agentCode, otherBean.complete, otherBean.message, otherBean.creationDate, otherBean.lastExecution, otherBean.lastExecutionDate, otherBean.nextExecution, otherBean.nextExecutionDate, otherBean.executionsNumber, otherBean.priority, otherBean.stackTrace, otherBean.server);
	}

	/**
	 * Gets value for attribute taskId
	 */
	public java.lang.Long getTaskId() {
		return this.taskId;
	}

	/**
	 * Sets value for attribute taskId
	 */
	public void setTaskId(java.lang.Long taskId) {
		this.taskId = taskId;
	}

	/**
	 * Gets value for attribute taskDescription
	 */
	public java.lang.String getTaskDescription() {
		return this.taskDescription;
	}

	/**
	 * Sets value for attribute taskDescription
	 */
	public void setTaskDescription(java.lang.String taskDescription) {
		this.taskDescription = taskDescription;
	}

	/**
	 * Gets value for attribute agentCode
	 */
	public java.lang.String getAgentCode() {
		return this.agentCode;
	}

	/**
	 * Sets value for attribute agentCode
	 */
	public void setAgentCode(java.lang.String agentCode) {
		this.agentCode = agentCode;
	}

	/**
	 * Gets value for attribute complete
	 */
	public java.lang.String getComplete() {
		return this.complete;
	}

	/**
	 * Sets value for attribute complete
	 */
	public void setComplete(java.lang.String complete) {
		this.complete = complete;
	}

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
	 * Gets value for attribute creationDate
	 */
	public java.util.Calendar getCreationDate() {
		return this.creationDate;
	}

	/**
	 * Sets value for attribute creationDate
	 */
	public void setCreationDate(java.util.Calendar creationDate) {
		this.creationDate = creationDate;
	}

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
	 * Gets value for attribute lastExecutionDate
	 */
	public java.util.Calendar getLastExecutionDate() {
		return this.lastExecutionDate;
	}

	/**
	 * Sets value for attribute lastExecutionDate
	 */
	public void setLastExecutionDate(java.util.Calendar lastExecutionDate) {
		this.lastExecutionDate = lastExecutionDate;
	}

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
	 * Gets value for attribute nextExecutionDate
	 */
	public java.util.Calendar getNextExecutionDate() {
		return this.nextExecutionDate;
	}

	/**
	 * Sets value for attribute nextExecutionDate
	 */
	public void setNextExecutionDate(java.util.Calendar nextExecutionDate) {
		this.nextExecutionDate = nextExecutionDate;
	}

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
	 * Gets value for attribute priority
	 */
	public java.lang.Long getPriority() {
		return this.priority;
	}

	/**
	 * Sets value for attribute priority
	 */
	public void setPriority(java.lang.Long priority) {
		this.priority = priority;
	}

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
	 * Gets value for attribute server
	 */
	public java.lang.String getServer() {
		return this.server;
	}

	/**
	 * Sets value for attribute server
	 */
	public void setServer(java.lang.String server) {
		this.server = server;
	}

	/**
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[taskId: ");
		b.append (this.taskId);
		b.append (", taskDescription: ");
		b.append (this.taskDescription);
		b.append (", agentCode: ");
		b.append (this.agentCode);
		b.append (", complete: ");
		b.append (this.complete);
		b.append (", message: ");
		b.append (this.message);
		b.append (", creationDate: ");
		b.append (this.creationDate);
		b.append (", lastExecution: ");
		b.append (this.lastExecution);
		b.append (", lastExecutionDate: ");
		b.append (this.lastExecutionDate);
		b.append (", nextExecution: ");
		b.append (this.nextExecution);
		b.append (", nextExecutionDate: ");
		b.append (this.nextExecutionDate);
		b.append (", executionsNumber: ");
		b.append (this.executionsNumber);
		b.append (", priority: ");
		b.append (this.priority);
		b.append (", stackTrace: ");
		b.append (this.stackTrace);
		b.append (", server: ");
		b.append (this.server);
		b.append ("]");
		return b.toString();
	}

}
