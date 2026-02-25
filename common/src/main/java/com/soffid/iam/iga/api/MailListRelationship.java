//
// (C) 2020 Soffid
//
//

package com.soffid.iam.iga.api;
/**
 * ValueObject MailListRelationship
 **/
public class MailListRelationship

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute mailListNameBelong

	 */
	private java.lang.String mailListNameBelong;

	/**
	 * Attribute mailListNameIncluded

	 */
	private java.lang.String mailListNameIncluded;

	/**
	 * Attribute mailDomainBelongCode

	 */
	private java.lang.String mailDomainBelongCode;

	/**
	 * Attribute mailDomainAccountCode

	 */
	private java.lang.String mailDomainAccountCode;

	/**
	 * Attribute id

	 */
	private java.lang.Long id;

	public MailListRelationship()
	{
	}

	public MailListRelationship(java.lang.String mailListNameBelong, java.lang.String mailListNameIncluded, java.lang.String mailDomainBelongCode, java.lang.String mailDomainAccountCode, java.lang.Long id)
	{
		super();
		this.mailListNameBelong = mailListNameBelong;
		this.mailListNameIncluded = mailListNameIncluded;
		this.mailDomainBelongCode = mailDomainBelongCode;
		this.mailDomainAccountCode = mailDomainAccountCode;
		this.id = id;
	}

	public MailListRelationship(java.lang.String mailListNameBelong, java.lang.String mailListNameIncluded)
	{
		super();
		this.mailListNameBelong = mailListNameBelong;
		this.mailListNameIncluded = mailListNameIncluded;
	}

	public MailListRelationship(MailListRelationship otherBean)
	{
		this(otherBean.mailListNameBelong, otherBean.mailListNameIncluded, otherBean.mailDomainBelongCode, otherBean.mailDomainAccountCode, otherBean.id);
	}

	/**
	 * Gets value for attribute mailListNameBelong
	 */
	public java.lang.String getMailListNameBelong() {
		return this.mailListNameBelong;
	}

	/**
	 * Sets value for attribute mailListNameBelong
	 */
	public void setMailListNameBelong(java.lang.String mailListNameBelong) {
		this.mailListNameBelong = mailListNameBelong;
	}

	/**
	 * Gets value for attribute mailListNameIncluded
	 */
	public java.lang.String getMailListNameIncluded() {
		return this.mailListNameIncluded;
	}

	/**
	 * Sets value for attribute mailListNameIncluded
	 */
	public void setMailListNameIncluded(java.lang.String mailListNameIncluded) {
		this.mailListNameIncluded = mailListNameIncluded;
	}

	/**
	 * Gets value for attribute mailDomainBelongCode
	 */
	public java.lang.String getMailDomainBelongCode() {
		return this.mailDomainBelongCode;
	}

	/**
	 * Sets value for attribute mailDomainBelongCode
	 */
	public void setMailDomainBelongCode(java.lang.String mailDomainBelongCode) {
		this.mailDomainBelongCode = mailDomainBelongCode;
	}

	/**
	 * Gets value for attribute mailDomainAccountCode
	 */
	public java.lang.String getMailDomainAccountCode() {
		return this.mailDomainAccountCode;
	}

	/**
	 * Sets value for attribute mailDomainAccountCode
	 */
	public void setMailDomainAccountCode(java.lang.String mailDomainAccountCode) {
		this.mailDomainAccountCode = mailDomainAccountCode;
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
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[mailListNameBelong: ");
		b.append (this.mailListNameBelong);
		b.append (", mailListNameIncluded: ");
		b.append (this.mailListNameIncluded);
		b.append (", mailDomainBelongCode: ");
		b.append (this.mailDomainBelongCode);
		b.append (", mailDomainAccountCode: ");
		b.append (this.mailDomainAccountCode);
		b.append (", id: ");
		b.append (this.id);
		b.append ("]");
		return b.toString();
	}

}
