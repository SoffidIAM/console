//
// (C) 2020 Soffid
//
//

package com.soffid.iam.am.api;
/**
 * ValueObject PasswordDomainStatus
 **/
public class PasswordDomainStatus

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute domainName

	 */
	private java.lang.String domainName;

	/**
	 * Attribute lockedUntil

	 */
	private java.util.Date lockedUntil;

	/**
	 * Attribute failures

	 */
	private java.lang.Integer failures;

	public PasswordDomainStatus()
	{
	}

	public PasswordDomainStatus(java.lang.String domainName, java.util.Date lockedUntil, java.lang.Integer failures)
	{
		super();
		this.domainName = domainName;
		this.lockedUntil = lockedUntil;
		this.failures = failures;
	}

	public PasswordDomainStatus(PasswordDomainStatus otherBean)
	{
		this(otherBean.domainName, otherBean.lockedUntil, otherBean.failures);
	}

	/**
	 * Gets value for attribute domainName
	 */
	public java.lang.String getDomainName() {
		return this.domainName;
	}

	/**
	 * Sets value for attribute domainName
	 */
	public void setDomainName(java.lang.String domainName) {
		this.domainName = domainName;
	}

	/**
	 * Gets value for attribute lockedUntil
	 */
	public java.util.Date getLockedUntil() {
		return this.lockedUntil;
	}

	/**
	 * Sets value for attribute lockedUntil
	 */
	public void setLockedUntil(java.util.Date lockedUntil) {
		this.lockedUntil = lockedUntil;
	}

	/**
	 * Gets value for attribute failures
	 */
	public java.lang.Integer getFailures() {
		return this.failures;
	}

	/**
	 * Sets value for attribute failures
	 */
	public void setFailures(java.lang.Integer failures) {
		this.failures = failures;
	}

	/**
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[domainName: ");
		b.append (this.domainName);
		b.append (", lockedUntil: ");
		b.append (this.lockedUntil);
		b.append (", failures: ");
		b.append (this.failures);
		b.append ("]");
		return b.toString();
	}

}
