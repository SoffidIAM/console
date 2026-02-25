//
// (C) 2020 Soffid
//
//

package com.soffid.iam.sync.api;
/**
 * ValueObject ScheduledTaskLog
 **/
public class ScheduledTaskLog

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute id

	 */
	private java.lang.Long id;

	/**
	 * Attribute time

	 */
	private java.util.Date time;

	/**
	 * Attribute end

	 */
	private java.util.Date end;

	/**
	 * Attribute logReferenceID

	 */
	private java.lang.String logReferenceID;

	/**
	 * Attribute error

	 */
	private boolean error = false;

	public ScheduledTaskLog()
	{
	}

	public ScheduledTaskLog(java.lang.Long id, java.util.Date time, java.util.Date end, java.lang.String logReferenceID, boolean error)
	{
		super();
		this.id = id;
		this.time = time;
		this.end = end;
		this.logReferenceID = logReferenceID;
		this.error = error;
	}

	public ScheduledTaskLog(boolean error)
	{
		super();
		this.error = error;
	}

	public ScheduledTaskLog(ScheduledTaskLog otherBean)
	{
		this(otherBean.id, otherBean.time, otherBean.end, otherBean.logReferenceID, otherBean.error);
	}

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
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[id: ");
		b.append (this.id);
		b.append (", time: ");
		b.append (this.time);
		b.append (", end: ");
		b.append (this.end);
		b.append (", logReferenceID: ");
		b.append (this.logReferenceID);
		b.append (", error: ");
		b.append (this.error);
		b.append ("]");
		return b.toString();
	}

}
