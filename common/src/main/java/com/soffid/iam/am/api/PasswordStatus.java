//
// (C) 2020 Soffid
//
//

package com.soffid.iam.am.api;
/**
 * ValueObject PasswordStatus
 **/
public class PasswordStatus

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute user

	 */
	private java.lang.String user;

	/**
	 * Attribute PasswordDomain

	 */
	private java.lang.String PasswordDomain;

	/**
	 * Attribute dispatcher

	 */
	private java.lang.String dispatcher;

	/**
	 * Attribute accountName

	 */
	private java.lang.String accountName;

	/**
	 * Attribute date

	 */
	private java.util.Calendar date;

	/**
	 * Attribute expirationDate

	 */
	private java.util.Calendar expirationDate;

	/**
	 * Attribute expired

	 */
	private java.lang.Boolean expired;

	/**
	 * Attribute passwordPolicyType

	 */
	private java.lang.String passwordPolicyType;

	public PasswordStatus()
	{
	}

	public PasswordStatus(java.lang.String user, java.lang.String PasswordDomain, java.lang.String dispatcher, java.lang.String accountName, java.util.Calendar date, java.util.Calendar expirationDate, java.lang.Boolean expired, java.lang.String passwordPolicyType)
	{
		super();
		this.user = user;
		this.PasswordDomain = PasswordDomain;
		this.dispatcher = dispatcher;
		this.accountName = accountName;
		this.date = date;
		this.expirationDate = expirationDate;
		this.expired = expired;
		this.passwordPolicyType = passwordPolicyType;
	}

	public PasswordStatus(java.util.Calendar date, java.util.Calendar expirationDate, java.lang.String passwordPolicyType)
	{
		super();
		this.date = date;
		this.expirationDate = expirationDate;
		this.passwordPolicyType = passwordPolicyType;
	}

	public PasswordStatus(PasswordStatus otherBean)
	{
		this(otherBean.user, otherBean.PasswordDomain, otherBean.dispatcher, otherBean.accountName, otherBean.date, otherBean.expirationDate, otherBean.expired, otherBean.passwordPolicyType);
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
	 * Gets value for attribute PasswordDomain
	 */
	public java.lang.String getPasswordDomain() {
		return this.PasswordDomain;
	}

	/**
	 * Sets value for attribute PasswordDomain
	 */
	public void setPasswordDomain(java.lang.String PasswordDomain) {
		this.PasswordDomain = PasswordDomain;
	}

	/**
	 * Gets value for attribute dispatcher
	 */
	public java.lang.String getDispatcher() {
		return this.dispatcher;
	}

	/**
	 * Sets value for attribute dispatcher
	 */
	public void setDispatcher(java.lang.String dispatcher) {
		this.dispatcher = dispatcher;
	}

	/**
	 * Gets value for attribute accountName
	 */
	public java.lang.String getAccountName() {
		return this.accountName;
	}

	/**
	 * Sets value for attribute accountName
	 */
	public void setAccountName(java.lang.String accountName) {
		this.accountName = accountName;
	}

	/**
	 * Gets value for attribute date
	 */
	public java.util.Calendar getDate() {
		return this.date;
	}

	/**
	 * Sets value for attribute date
	 */
	public void setDate(java.util.Calendar date) {
		this.date = date;
	}

	/**
	 * Gets value for attribute expirationDate
	 */
	public java.util.Calendar getExpirationDate() {
		return this.expirationDate;
	}

	/**
	 * Sets value for attribute expirationDate
	 */
	public void setExpirationDate(java.util.Calendar expirationDate) {
		this.expirationDate = expirationDate;
	}

	/**
	 * Gets value for attribute expired
	 */
	public java.lang.Boolean getExpired() {
		return this.expired;
	}

	/**
	 * Sets value for attribute expired
	 */
	public void setExpired(java.lang.Boolean expired) {
		this.expired = expired;
	}

	/**
	 * Gets value for attribute passwordPolicyType
	 */
	public java.lang.String getPasswordPolicyType() {
		return this.passwordPolicyType;
	}

	/**
	 * Sets value for attribute passwordPolicyType
	 */
	public void setPasswordPolicyType(java.lang.String passwordPolicyType) {
		this.passwordPolicyType = passwordPolicyType;
	}

	/**
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[user: ");
		b.append (this.user);
		b.append (", PasswordDomain: ");
		b.append (this.PasswordDomain);
		b.append (", dispatcher: ");
		b.append (this.dispatcher);
		b.append (", accountName: ");
		b.append (this.accountName);
		b.append (", date: ");
		b.append (this.date);
		b.append (", expirationDate: ");
		b.append (this.expirationDate);
		b.append (", expired: ");
		b.append (this.expired);
		b.append (", passwordPolicyType: ");
		b.append (this.passwordPolicyType);
		b.append ("]");
		return b.toString();
	}

}
