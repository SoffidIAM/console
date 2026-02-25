//
// (C) 2020 Soffid
//
//

package com.soffid.iam.iga.api;
/**
 * ValueObject UserMailList
 **/
public class UserMailList

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute mailListName

	 */
	private java.lang.String mailListName;

	/**
	 * Attribute mailListDescription

	 */
	private java.lang.String mailListDescription;

	/**
	 * Attribute userCode

	 */
	private java.lang.String userCode;

	/**
	 * Attribute fullName

	 */
	private java.lang.String fullName;

	/**
	 * Attribute domainCode

	 */
	private java.lang.String domainCode;

	/**
	 * Attribute id

	 */
	private java.lang.Long id;

	/**
	 * Attribute start

	 */
	private java.util.Date start;

	/**
	 * Attribute end

	 */
	private java.util.Date end;

	/**
	 * Attribute disabled

	 */
	private java.lang.Boolean disabled;

	public UserMailList()
	{
	}

	public UserMailList(java.lang.String mailListName, java.lang.String mailListDescription, java.lang.String userCode, java.lang.String fullName, java.lang.String domainCode, java.lang.Long id, java.util.Date start, java.util.Date end, java.lang.Boolean disabled)
	{
		super();
		this.mailListName = mailListName;
		this.mailListDescription = mailListDescription;
		this.userCode = userCode;
		this.fullName = fullName;
		this.domainCode = domainCode;
		this.id = id;
		this.start = start;
		this.end = end;
		this.disabled = disabled;
	}

	public UserMailList(java.lang.String mailListName, java.lang.String userCode)
	{
		super();
		this.mailListName = mailListName;
		this.userCode = userCode;
	}

	public UserMailList(UserMailList otherBean)
	{
		this(otherBean.mailListName, otherBean.mailListDescription, otherBean.userCode, otherBean.fullName, otherBean.domainCode, otherBean.id, otherBean.start, otherBean.end, otherBean.disabled);
	}

	/**
	 * Gets value for attribute mailListName
	 */
	public java.lang.String getMailListName() {
		return this.mailListName;
	}

	/**
	 * Sets value for attribute mailListName
	 */
	public void setMailListName(java.lang.String mailListName) {
		this.mailListName = mailListName;
	}

	/**
	 * Gets value for attribute mailListDescription
	 */
	public java.lang.String getMailListDescription() {
		return this.mailListDescription;
	}

	/**
	 * Sets value for attribute mailListDescription
	 */
	public void setMailListDescription(java.lang.String mailListDescription) {
		this.mailListDescription = mailListDescription;
	}

	/**
	 * Gets value for attribute userCode
	 */
	public java.lang.String getUserCode() {
		return this.userCode;
	}

	/**
	 * Sets value for attribute userCode
	 */
	public void setUserCode(java.lang.String userCode) {
		this.userCode = userCode;
	}

	/**
	 * Gets value for attribute fullName
	 */
	public java.lang.String getFullName() {
		return this.fullName;
	}

	/**
	 * Sets value for attribute fullName
	 */
	public void setFullName(java.lang.String fullName) {
		this.fullName = fullName;
	}

	/**
	 * Gets value for attribute domainCode
	 */
	public java.lang.String getDomainCode() {
		return this.domainCode;
	}

	/**
	 * Sets value for attribute domainCode
	 */
	public void setDomainCode(java.lang.String domainCode) {
		this.domainCode = domainCode;
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
	 * Gets value for attribute disabled
	 */
	public java.lang.Boolean getDisabled() {
		return this.disabled;
	}

	/**
	 * Sets value for attribute disabled
	 */
	public void setDisabled(java.lang.Boolean disabled) {
		this.disabled = disabled;
	}

	/**
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[mailListName: ");
		b.append (this.mailListName);
		b.append (", mailListDescription: ");
		b.append (this.mailListDescription);
		b.append (", userCode: ");
		b.append (this.userCode);
		b.append (", fullName: ");
		b.append (this.fullName);
		b.append (", domainCode: ");
		b.append (this.domainCode);
		b.append (", id: ");
		b.append (this.id);
		b.append (", start: ");
		b.append (this.start);
		b.append (", end: ");
		b.append (this.end);
		b.append (", disabled: ");
		b.append (this.disabled);
		b.append ("]");
		return b.toString();
	}

}
