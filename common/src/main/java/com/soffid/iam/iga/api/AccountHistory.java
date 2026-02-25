//
// (C) 2020 Soffid
//
//

package com.soffid.iam.iga.api;
/**
 * ValueObject AccountHistory
 **/
public class AccountHistory

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute account

	 */
	private com.soffid.iam.base.api.Account account;

	/**
	 * Attribute level

	 */
	private com.soffid.iam.base.api.AccountAccessLevelEnum level;

	/**
	 * Attribute start

	 */
	private java.util.Date start;

	/**
	 * Attribute end

	 */
	private java.util.Date end;

	public AccountHistory()
	{
	}

	public AccountHistory(com.soffid.iam.base.api.Account account, com.soffid.iam.base.api.AccountAccessLevelEnum level, java.util.Date start, java.util.Date end)
	{
		super();
		this.account = account;
		this.level = level;
		this.start = start;
		this.end = end;
	}

	public AccountHistory(AccountHistory otherBean)
	{
		this(otherBean.account, otherBean.level, otherBean.start, otherBean.end);
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
	 * Gets value for attribute level
	 */
	public com.soffid.iam.base.api.AccountAccessLevelEnum getLevel() {
		return this.level;
	}

	/**
	 * Sets value for attribute level
	 */
	public void setLevel(com.soffid.iam.base.api.AccountAccessLevelEnum level) {
		this.level = level;
	}

	/**
	 * Gets value for attribute start
	 */
	public java.util.Date getStart() {
		return this.start;
	}

	/**
	 * Sets value for attribute start
	 */
	public void setStart(java.util.Date start) {
		this.start = start;
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
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[account: ");
		b.append (this.account);
		b.append (", level: ");
		b.append (this.level);
		b.append (", start: ");
		b.append (this.start);
		b.append (", end: ");
		b.append (this.end);
		b.append ("]");
		return b.toString();
	}

}
