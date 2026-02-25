//
// (C) 2020 Soffid
//
//

package com.soffid.iam.bpm.api;
/**
 * ValueObject ProcessLog
 **/
public class ProcessLog

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute processId

	 */
	private long processId;

	/**
	 * Attribute date

	 */
	private java.util.Date date;

	/**
	 * Attribute action

	 */
	private java.lang.String action;

	/**
	 * Attribute user

	 */
	private java.lang.String user;

	public ProcessLog()
	{
	}

	public ProcessLog(long processId, java.util.Date date, java.lang.String action, java.lang.String user)
	{
		super();
		this.processId = processId;
		this.date = date;
		this.action = action;
		this.user = user;
	}

	public ProcessLog(long processId)
	{
		super();
		this.processId = processId;
	}

	public ProcessLog(ProcessLog otherBean)
	{
		this(otherBean.processId, otherBean.date, otherBean.action, otherBean.user);
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
	 * Gets value for attribute date
	 */
	public java.util.Date getDate() {
		return this.date;
	}

	/**
	 * Sets value for attribute date
	 */
	public void setDate(java.util.Date date) {
		this.date = date;
	}

	/**
	 * Gets value for attribute action
	 */
	public java.lang.String getAction() {
		return this.action;
	}

	/**
	 * Sets value for attribute action
	 */
	public void setAction(java.lang.String action) {
		this.action = action;
	}

	/**
	 * Gets value for attribute user
	 */
	public java.lang.String getUser() {
		return this.user;
	}

	/**
	 * Sets value for attribute user
	 */
	public void setUser(java.lang.String user) {
		this.user = user;
	}

	/**
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[processId: ");
		b.append (this.processId);
		b.append (", date: ");
		b.append (this.date);
		b.append (", action: ");
		b.append (this.action);
		b.append (", user: ");
		b.append (this.user);
		b.append ("]");
		return b.toString();
	}

}
