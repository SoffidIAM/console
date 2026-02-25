//
// (C) 2020 Soffid
//
//

package com.soffid.iam.bpm.api;
/**
 * ValueObject Job
 **/
public class Job

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute name

	 */
	private java.lang.String name;

	/**
	 * Attribute id

	 */
	private long id;

	/**
	 * Attribute dueDate

	 */
	private java.util.Date dueDate;

	/**
	 * Attribute locked

	 */
	private boolean locked;

	/**
	 * Attribute errorMessage

	 */
	private java.lang.String errorMessage;

	/**
	 * Attribute error

	 */
	private boolean error;

	/**
	 * Attribute failures

	 */
	private int failures;

	/**
	 * Attribute paused

	 */
	private boolean paused;

	/**
	 * Attribute processId

	 */
	private long processId;

	public Job()
	{
	}

	public Job(java.lang.String name, long id, java.util.Date dueDate, boolean locked, java.lang.String errorMessage, boolean error, int failures, boolean paused, long processId)
	{
		super();
		this.name = name;
		this.id = id;
		this.dueDate = dueDate;
		this.locked = locked;
		this.errorMessage = errorMessage;
		this.error = error;
		this.failures = failures;
		this.paused = paused;
		this.processId = processId;
	}

	public Job(Job otherBean)
	{
		this(otherBean.name, otherBean.id, otherBean.dueDate, otherBean.locked, otherBean.errorMessage, otherBean.error, otherBean.failures, otherBean.paused, otherBean.processId);
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
	 * Gets value for attribute locked
	 */
	public boolean isLocked() {
		return this.locked;
	}

	/**
	 * Sets value for attribute locked
	 */
	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	/**
	 * Gets value for attribute errorMessage
	 */
	public java.lang.String getErrorMessage() {
		return this.errorMessage;
	}

	/**
	 * Sets value for attribute errorMessage
	 */
	public void setErrorMessage(java.lang.String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * Gets value for attribute error
	 */
	public boolean isError() {
		return this.error;
	}

	/**
	 * Sets value for attribute error
	 */
	public void setError(boolean error) {
		this.error = error;
	}

	/**
	 * Gets value for attribute failures
	 */
	public int getFailures() {
		return this.failures;
	}

	/**
	 * Sets value for attribute failures
	 */
	public void setFailures(int failures) {
		this.failures = failures;
	}

	/**
	 * Gets value for attribute paused
	 */
	public boolean isPaused() {
		return this.paused;
	}

	/**
	 * Sets value for attribute paused
	 */
	public void setPaused(boolean paused) {
		this.paused = paused;
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
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[name: ");
		b.append (this.name);
		b.append (", id: ");
		b.append (this.id);
		b.append (", dueDate: ");
		b.append (this.dueDate);
		b.append (", locked: ");
		b.append (this.locked);
		b.append (", errorMessage: ");
		b.append (this.errorMessage);
		b.append (", error: ");
		b.append (this.error);
		b.append (", failures: ");
		b.append (this.failures);
		b.append (", paused: ");
		b.append (this.paused);
		b.append (", processId: ");
		b.append (this.processId);
		b.append ("]");
		return b.toString();
	}

}
