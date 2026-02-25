//
// (C) 2020 Soffid
//
//

package com.soffid.iam.am.api;
/**
 * ValueObject OtpChallengeProxy
 **/
public class OtpChallengeProxy

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute user

	 */
	private com.soffid.iam.base.api.User user;

	/**
	 * Attribute account

	 */
	private com.soffid.iam.base.api.Account account;

	/**
	 * Attribute otpHandler

	 */
	private java.lang.String otpHandler;

	/**
	 * Attribute cardNumber

	 */
	private java.lang.String cardNumber;

	/**
	 * Attribute cell

	 */
	private java.lang.String cell;

	/**
	 * Attribute value

	 */
	private java.lang.String value;

	public OtpChallengeProxy()
	{
	}

	public OtpChallengeProxy(com.soffid.iam.base.api.User user, com.soffid.iam.base.api.Account account, java.lang.String otpHandler, java.lang.String cardNumber, java.lang.String cell, java.lang.String value)
	{
		super();
		this.user = user;
		this.account = account;
		this.otpHandler = otpHandler;
		this.cardNumber = cardNumber;
		this.cell = cell;
		this.value = value;
	}

	public OtpChallengeProxy(OtpChallengeProxy otherBean)
	{
		this(otherBean.user, otherBean.account, otherBean.otpHandler, otherBean.cardNumber, otherBean.cell, otherBean.value);
	}

	/**
	 * Gets value for attribute user
	 */
	public com.soffid.iam.base.api.User getUser() {
		return this.user;
	}

	/**
	 * Sets value for attribute user
	 */
	public void setUser(com.soffid.iam.base.api.User user) {
		this.user = user;
	}

	/**
	 * Gets value for attribute account
	 */
	public com.soffid.iam.base.api.Account getAccount() {
		return this.account;
	}

	/**
	 * Sets value for attribute account
	 */
	public void setAccount(com.soffid.iam.base.api.Account account) {
		this.account = account;
	}

	/**
	 * Gets value for attribute otpHandler
	 */
	public java.lang.String getOtpHandler() {
		return this.otpHandler;
	}

	/**
	 * Sets value for attribute otpHandler
	 */
	public void setOtpHandler(java.lang.String otpHandler) {
		this.otpHandler = otpHandler;
	}

	/**
	 * Gets value for attribute cardNumber
	 */
	public java.lang.String getCardNumber() {
		return this.cardNumber;
	}

	/**
	 * Sets value for attribute cardNumber
	 */
	public void setCardNumber(java.lang.String cardNumber) {
		this.cardNumber = cardNumber;
	}

	/**
	 * Gets value for attribute cell
	 */
	public java.lang.String getCell() {
		return this.cell;
	}

	/**
	 * Sets value for attribute cell
	 */
	public void setCell(java.lang.String cell) {
		this.cell = cell;
	}

	/**
	 * Gets value for attribute value
	 */
	public java.lang.String getValue() {
		return this.value;
	}

	/**
	 * Sets value for attribute value
	 */
	public void setValue(java.lang.String value) {
		this.value = value;
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
		b.append (", account: ");
		b.append (this.account);
		b.append (", otpHandler: ");
		b.append (this.otpHandler);
		b.append (", cardNumber: ");
		b.append (this.cardNumber);
		b.append (", cell: ");
		b.append (this.cell);
		b.append (", value: ");
		b.append (this.value);
		b.append ("]");
		return b.toString();
	}

}
