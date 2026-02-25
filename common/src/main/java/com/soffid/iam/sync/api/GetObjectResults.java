//
// (C) 2020 Soffid
//
//

package com.soffid.iam.sync.api;
/**
 * ValueObject GetObjectResults
 **/
public class GetObjectResults

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
	 * Attribute object

	 */
	private java.util.Map<java.lang.String,java.lang.Object> object;

	public GetObjectResults()
	{
	}

	public GetObjectResults(java.lang.String status, java.lang.String log, java.util.Map<java.lang.String,java.lang.Object> object)
	{
		super();
		this.status = status;
		this.log = log;
		this.object = object;
	}

	public GetObjectResults(GetObjectResults otherBean)
	{
		this(otherBean.status, otherBean.log, otherBean.object);
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
	 * Gets value for attribute object
	 */
	public java.util.Map<java.lang.String,java.lang.Object> getObject() {
		return this.object;
	}

	/**
	 * Sets value for attribute object
	 */
	public void setObject(java.util.Map<java.lang.String,java.lang.Object> object) {
		this.object = object;
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
		b.append (", object: ");
		b.append (this.object);
		b.append ("]");
		return b.toString();
	}

}
