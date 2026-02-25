//
// (C) 2020 Soffid
//
//

package com.soffid.iam.iga.api;
/**
 * ValueObject DomainUsersMember
 **/
public class DomainUsersMember

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute code

	 */
	private java.lang.String code;

	/**
	 * Attribute type

	 */
	private java.lang.String type;

	/**
	 * Attribute description

	 */
	private java.lang.String description;

	/**
	 * Attribute descriptionType

	 */
	private java.lang.String descriptionType;

	/**
	 * Attribute userDomain

	 */
	private com.soffid.iam.iga.api.UserDomain userDomain;

	/**
	 * Attribute passwordDomain

	 */
	private com.soffid.iam.am.api.PasswordDomain passwordDomain;

	/**
	 * Attribute passwordPolicy

	 */
	private com.soffid.iam.am.api.PasswordPolicy passwordPolicy;

	public DomainUsersMember()
	{
	}

	public DomainUsersMember(java.lang.String code, java.lang.String type, java.lang.String description, java.lang.String descriptionType, com.soffid.iam.iga.api.UserDomain userDomain, com.soffid.iam.am.api.PasswordDomain passwordDomain, com.soffid.iam.am.api.PasswordPolicy passwordPolicy)
	{
		super();
		this.code = code;
		this.type = type;
		this.description = description;
		this.descriptionType = descriptionType;
		this.userDomain = userDomain;
		this.passwordDomain = passwordDomain;
		this.passwordPolicy = passwordPolicy;
	}

	public DomainUsersMember(java.lang.String type)
	{
		super();
		this.type = type;
	}

	public DomainUsersMember(DomainUsersMember otherBean)
	{
		this(otherBean.code, otherBean.type, otherBean.description, otherBean.descriptionType, otherBean.userDomain, otherBean.passwordDomain, otherBean.passwordPolicy);
	}

	/**
	 * Gets value for attribute code
	 */
	public java.lang.String getCode() {
		return this.code;
	}

	/**
	 * Sets value for attribute code
	 */
	public void setCode(java.lang.String code) {
		this.code = code;
	}

	/**
	 * Gets value for attribute type
	 */
	public java.lang.String getType() {
		return this.type;
	}

	/**
	 * Sets value for attribute type
	 */
	public void setType(java.lang.String type) {
		this.type = type;
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
	 * Gets value for attribute descriptionType
	 */
	public java.lang.String getDescriptionType() {
		return this.descriptionType;
	}

	/**
	 * Sets value for attribute descriptionType
	 */
	public void setDescriptionType(java.lang.String descriptionType) {
		this.descriptionType = descriptionType;
	}

	/**
	 * Gets value for attribute userDomain
	 */
	public com.soffid.iam.iga.api.UserDomain getUserDomain() {
		return this.userDomain;
	}

	/**
	 * Sets value for attribute userDomain
	 */
	public void setUserDomain(com.soffid.iam.iga.api.UserDomain userDomain) {
		this.userDomain = userDomain;
	}

	/**
	 * Gets value for attribute passwordDomain
	 */
	public com.soffid.iam.am.api.PasswordDomain getPasswordDomain() {
		return this.passwordDomain;
	}

	/**
	 * Sets value for attribute passwordDomain
	 */
	public void setPasswordDomain(com.soffid.iam.am.api.PasswordDomain passwordDomain) {
		this.passwordDomain = passwordDomain;
	}

	/**
	 * Gets value for attribute passwordPolicy
	 */
	public com.soffid.iam.am.api.PasswordPolicy getPasswordPolicy() {
		return this.passwordPolicy;
	}

	/**
	 * Sets value for attribute passwordPolicy
	 */
	public void setPasswordPolicy(com.soffid.iam.am.api.PasswordPolicy passwordPolicy) {
		this.passwordPolicy = passwordPolicy;
	}

	/**
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[code: ");
		b.append (this.code);
		b.append (", type: ");
		b.append (this.type);
		b.append (", description: ");
		b.append (this.description);
		b.append (", descriptionType: ");
		b.append (this.descriptionType);
		b.append (", userDomain: ");
		b.append (this.userDomain);
		b.append (", passwordDomain: ");
		b.append (this.passwordDomain);
		b.append (", passwordPolicy: ");
		b.append (this.passwordPolicy);
		b.append ("]");
		return b.toString();
	}

}
