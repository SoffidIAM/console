//
// (C) 2020 Soffid
//
//

package com.soffid.iam.iga.api;
/**
 * ValueObject ExternalName
 **/
public class ExternalName

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute email

	 */
	private java.lang.String email;

	/**
	 * Attribute mailListName

	 */
	private java.lang.String mailListName;

	/**
	 * Attribute domainCode

	 */
	private java.lang.String domainCode;

	/**
	 * Attribute id

	 */
	private java.lang.Long id;

	public ExternalName()
	{
	}

	public ExternalName(java.lang.String email, java.lang.String mailListName, java.lang.String domainCode, java.lang.Long id)
	{
		super();
		this.email = email;
		this.mailListName = mailListName;
		this.domainCode = domainCode;
		this.id = id;
	}

	public ExternalName(java.lang.String email, java.lang.String mailListName)
	{
		super();
		this.email = email;
		this.mailListName = mailListName;
	}

	public ExternalName(ExternalName otherBean)
	{
		this(otherBean.email, otherBean.mailListName, otherBean.domainCode, otherBean.id);
	}

	/**
	 * Gets value for attribute email
	 */
	public java.lang.String getEmail() {
		return this.email;
	}

	/**
	 * Sets value for attribute email
	 */
	public void setEmail(java.lang.String email) {
		this.email = email;
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
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[email: ");
		b.append (this.email);
		b.append (", mailListName: ");
		b.append (this.mailListName);
		b.append (", domainCode: ");
		b.append (this.domainCode);
		b.append (", id: ");
		b.append (this.id);
		b.append ("]");
		return b.toString();
	}

}
