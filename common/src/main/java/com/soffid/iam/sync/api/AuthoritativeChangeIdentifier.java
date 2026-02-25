//
// (C) 2020 Soffid
//
//

package com.soffid.iam.sync.api;
/**
 * ValueObject AuthoritativeChangeIdentifier
 **/
public class AuthoritativeChangeIdentifier

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = -605755430186793407L;
	/**
	 * Attribute employeeId

	 */
	private java.lang.Object employeeId;

	/**
	 * Attribute date

	 */
	private java.util.Date date;

	/**
	 * Attribute changeId

	 */
	private java.lang.Object changeId;

	/**
	 * Attribute internalId

	 */
	private java.lang.Long internalId;

	public AuthoritativeChangeIdentifier()
	{
	}

	public AuthoritativeChangeIdentifier(java.lang.Object employeeId, java.util.Date date, java.lang.Object changeId, java.lang.Long internalId)
	{
		super();
		this.employeeId = employeeId;
		this.date = date;
		this.changeId = changeId;
		this.internalId = internalId;
	}

	public AuthoritativeChangeIdentifier(AuthoritativeChangeIdentifier otherBean)
	{
		this(otherBean.employeeId, otherBean.date, otherBean.changeId, otherBean.internalId);
	}

	/**
	 * Gets value for attribute employeeId
	 */
	public java.lang.Object getEmployeeId() {
		return this.employeeId;
	}

	/**
	 * Sets value for attribute employeeId
	 */
	public void setEmployeeId(java.lang.Object employeeId) {
		this.employeeId = employeeId;
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
	 * Gets value for attribute changeId
	 */
	public java.lang.Object getChangeId() {
		return this.changeId;
	}

	/**
	 * Sets value for attribute changeId
	 */
	public void setChangeId(java.lang.Object changeId) {
		this.changeId = changeId;
	}

	/**
	 * Gets value for attribute internalId
	 */
	public java.lang.Long getInternalId() {
		return this.internalId;
	}

	/**
	 * Sets value for attribute internalId
	 */
	public void setInternalId(java.lang.Long internalId) {
		this.internalId = internalId;
	}

	/**
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[employeeId: ");
		b.append (this.employeeId);
		b.append (", date: ");
		b.append (this.date);
		b.append (", changeId: ");
		b.append (this.changeId);
		b.append (", internalId: ");
		b.append (this.internalId);
		b.append ("]");
		return b.toString();
	}

}
