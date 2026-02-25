//
// (c) 2014 Soffid
//
//

package com.soffid.iam.sync.model;

/**
 *  Entity ScheduledTaskLogEntity
 */

public abstract class ScheduledTaskLogEntity {

	/**

	 * The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
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
	 * Attribute task
	 */
	private com.soffid.iam.sync.model.ScheduledTaskEntity task;
	/**
	 * Gets value for attribute task
	 */
	public com.soffid.iam.sync.model.ScheduledTaskEntity getTask() {
		return this.task;
	}
	/**
	 * Sets value for attribute task
	 */
	public void setTask(com.soffid.iam.sync.model.ScheduledTaskEntity task) {
		this.task = task;
	}
	/**
	 * Attribute time
	 */
	private java.util.Date time;
	/**
	 * Gets value for attribute time
	 */
	public java.util.Date getTime() {
		return this.time;
	}
	/**
	 * Sets value for attribute time
	 */
	public void setTime(java.util.Date time) {
		this.time = time;
	}
	/**
	 * Attribute end
	 */
	private java.util.Date end;
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
	 * Attribute logReferenceID
	 */
	private java.lang.String logReferenceID;
	/**
	 * Gets value for attribute logReferenceID
	 */
	public java.lang.String getLogReferenceID() {
		return this.logReferenceID;
	}
	/**
	 * Sets value for attribute logReferenceID
	 */
	public void setLogReferenceID(java.lang.String logReferenceID) {
		this.logReferenceID = logReferenceID;
	}
	/**
	 * Attribute error
	 */
	private boolean error = false;
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
	 * Returns <code>true</code> if the argument is an ScheduledTaskLogEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof ScheduledTaskLogEntity))
		{
			return false;
		}
		final ScheduledTaskLogEntity that = (ScheduledTaskLogEntity)object;
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
