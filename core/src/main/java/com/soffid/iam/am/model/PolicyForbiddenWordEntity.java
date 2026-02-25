//
// (c) 2014 Soffid
//
//

package com.soffid.iam.am.model;

/**
 *  Entity PolicyForbiddenWordEntity
 */

public abstract class PolicyForbiddenWordEntity {

	/**

	 * The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute id
	 */
	private java.lang.Long id;
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
	 * Attribute forbiddenWord
	 */
	private com.soffid.iam.am.model.ForbiddenWordEntity forbiddenWord;
	/**
	 * Gets value for attribute forbiddenWord
	 */
	public com.soffid.iam.am.model.ForbiddenWordEntity getForbiddenWord() {
		return this.forbiddenWord;
	}
	/**
	 * Sets value for attribute forbiddenWord
	 */
	public void setForbiddenWord(com.soffid.iam.am.model.ForbiddenWordEntity forbiddenWord) {
		this.forbiddenWord = forbiddenWord;
	}
	/**
	 * Attribute passwordPolicy
	 */
	private com.soffid.iam.am.model.PasswordPolicyEntity passwordPolicy;
	/**
	 * Gets value for attribute passwordPolicy
	 */
	public com.soffid.iam.am.model.PasswordPolicyEntity getPasswordPolicy() {
		return this.passwordPolicy;
	}
	/**
	 * Sets value for attribute passwordPolicy
	 */
	public void setPasswordPolicy(com.soffid.iam.am.model.PasswordPolicyEntity passwordPolicy) {
		this.passwordPolicy = passwordPolicy;
	}
	/**
	 * Returns <code>true</code> if the argument is an PolicyForbiddenWordEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof PolicyForbiddenWordEntity))
		{
			return false;
		}
		final PolicyForbiddenWordEntity that = (PolicyForbiddenWordEntity)object;
		if (this.id == null || that.getId() == null || !this.id.equals(that.getId())) 
		{
			return false;
		}
		return true;
	}
	/**
	 * Returns a hash code based on this entity's identifiers.
	 */
	public int hashCode()
	{
		int hashCode = (id == null ? super.hashCode() : id.hashCode());
		return hashCode;
	}
}
