//
// (C) 2020 Soffid
//
//

package com.soffid.iam.iga.api;
/**
 * ValueObject BpmProcess
 **/
public class BpmProcess

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute processName

	 */
	private java.lang.String processName;

	/**
	 * Attribute description

	 */
	private java.lang.String description;

	public BpmProcess()
	{
	}

	public BpmProcess(java.lang.String processName, java.lang.String description)
	{
		super();
		this.processName = processName;
		this.description = description;
	}

	public BpmProcess(BpmProcess otherBean)
	{
		this(otherBean.processName, otherBean.description);
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
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[processName: ");
		b.append (this.processName);
		b.append (", description: ");
		b.append (this.description);
		b.append ("]");
		return b.toString();
	}

}
