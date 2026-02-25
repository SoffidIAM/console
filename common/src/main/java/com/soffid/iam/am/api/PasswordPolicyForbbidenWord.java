//
// (C) 2020 Soffid
//
//

package com.soffid.iam.am.api;
/**
 * ValueObject PasswordPolicyForbbidenWord
 **/
public class PasswordPolicyForbbidenWord

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute id

	 */
	private java.lang.Long id;

	/**
	 * Attribute forbiddenWord

	 */
	private com.soffid.iam.am.api.ForbiddenWord forbiddenWord;

	/**
	 * Attribute passwordDomainPolicy

	 */
	private com.soffid.iam.am.api.PasswordPolicy passwordDomainPolicy;

	public PasswordPolicyForbbidenWord()
	{
	}

	public PasswordPolicyForbbidenWord(java.lang.Long id, com.soffid.iam.am.api.ForbiddenWord forbiddenWord, com.soffid.iam.am.api.PasswordPolicy passwordDomainPolicy)
	{
		super();
		this.id = id;
		this.forbiddenWord = forbiddenWord;
		this.passwordDomainPolicy = passwordDomainPolicy;
	}

	public PasswordPolicyForbbidenWord(PasswordPolicyForbbidenWord otherBean)
	{
		this(otherBean.id, otherBean.forbiddenWord, otherBean.passwordDomainPolicy);
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
	 * Gets value for attribute forbiddenWord
	 */
	public com.soffid.iam.am.api.ForbiddenWord getForbiddenWord() {
		return this.forbiddenWord;
	}

	/**
	 * Sets value for attribute forbiddenWord
	 */
	public void setForbiddenWord(com.soffid.iam.am.api.ForbiddenWord forbiddenWord) {
		this.forbiddenWord = forbiddenWord;
	}

	/**
	 * Gets value for attribute passwordDomainPolicy
	 */
	public com.soffid.iam.am.api.PasswordPolicy getPasswordDomainPolicy() {
		return this.passwordDomainPolicy;
	}

	/**
	 * Sets value for attribute passwordDomainPolicy
	 */
	public void setPasswordDomainPolicy(com.soffid.iam.am.api.PasswordPolicy passwordDomainPolicy) {
		this.passwordDomainPolicy = passwordDomainPolicy;
	}

	/**
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[id: ");
		b.append (this.id);
		b.append (", forbiddenWord: ");
		b.append (this.forbiddenWord);
		b.append (", passwordDomainPolicy: ");
		b.append (this.passwordDomainPolicy);
		b.append ("]");
		return b.toString();
	}

}
