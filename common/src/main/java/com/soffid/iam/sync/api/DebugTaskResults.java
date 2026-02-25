//
// (C) 2020 Soffid
//
//

package com.soffid.iam.sync.api;
/**
 * ValueObject DebugTaskResults
 **/
public class DebugTaskResults

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute status

	 */
	private java.lang.String status;

	/**
	 * Attribute log

	 */
	private java.lang.String log;

	/**
	 * Attribute exception

	 */
	private java.lang.Exception exception;

	public DebugTaskResults()
	{
	}

	public DebugTaskResults(java.lang.String status, java.lang.String log, java.lang.Exception exception)
	{
		super();
		this.status = status;
		this.log = log;
		this.exception = exception;
	}

	public DebugTaskResults(DebugTaskResults otherBean)
	{
		this(otherBean.status, otherBean.log, otherBean.exception);
	}

	/**
	 * Gets value for attribute status
	 */
	public java.lang.String getStatus() {
		return this.status;
	}

	/**
	 * Sets value for attribute status
	 */
	public void setStatus(java.lang.String status) {
		this.status = status;
	}

	/**
	 * Gets value for attribute log
	 */
	public java.lang.String getLog() {
		return this.log;
	}

	/**
	 * Sets value for attribute log
	 */
	public void setLog(java.lang.String log) {
		this.log = log;
	}

	/**
	 * Gets value for attribute exception
	 */
	public java.lang.Exception getException() {
		return this.exception;
	}

	/**
	 * Sets value for attribute exception
	 */
	public void setException(java.lang.Exception exception) {
		this.exception = exception;
	}

	/**
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[status: ");
		b.append (this.status);
		b.append (", log: ");
		b.append (this.log);
		b.append (", exception: ");
		b.append (this.exception);
		b.append ("]");
		return b.toString();
	}

}
