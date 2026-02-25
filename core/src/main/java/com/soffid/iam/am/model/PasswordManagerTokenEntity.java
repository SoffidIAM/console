//
// (c) 2014 Soffid
//
//

package com.soffid.iam.am.model;

/**
 *  Entity PasswordManagerTokenEntity
 */

public abstract class PasswordManagerTokenEntity {

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
	 * Attribute user
	 */
	private com.soffid.iam.base.model.UserEntity user;
	/**
	 * Gets value for attribute user
	 */
	public com.soffid.iam.base.model.UserEntity getUser() {
		return this.user;
	}
	/**
	 * Sets value for attribute user
	 */
	public void setUser(com.soffid.iam.base.model.UserEntity user) {
		this.user = user;
	}
	/**
	 * Attribute created
	 */
	private java.util.Date created;
	/**
	 * Gets value for attribute created
	 */
	public java.util.Date getCreated() {
		return this.created;
	}
	/**
	 * Sets value for attribute created
	 */
	public void setCreated(java.util.Date created) {
		this.created = created;
	}
	/**
	 * Attribute expires
	 */
	private java.util.Date expires;
	/**
	 * Gets value for attribute expires
	 */
	public java.util.Date getExpires() {
		return this.expires;
	}
	/**
	 * Sets value for attribute expires
	 */
	public void setExpires(java.util.Date expires) {
		this.expires = expires;
	}
	/**
	 * Attribute renew
	 */
	private java.util.Date renew;
	/**
	 * Gets value for attribute renew
	 */
	public java.util.Date getRenew() {
		return this.renew;
	}
	/**
	 * Sets value for attribute renew
	 */
	public void setRenew(java.util.Date renew) {
		this.renew = renew;
	}
	/**
	 * Attribute token
	 */
	private java.lang.String token;
	/**
	 * Gets value for attribute token
	 */
	public java.lang.String getToken() {
		return this.token;
	}
	/**
	 * Sets value for attribute token
	 */
	public void setToken(java.lang.String token) {
		this.token = token;
	}
	/**
	 * Attribute oldToken
	 */
	private java.lang.String oldToken;
	/**
	 * Gets value for attribute oldToken
	 */
	public java.lang.String getOldToken() {
		return this.oldToken;
	}
	/**
	 * Sets value for attribute oldToken
	 */
	public void setOldToken(java.lang.String oldToken) {
		this.oldToken = oldToken;
	}
	/**
	 * Returns <code>true</code> if the argument is an PasswordManagerTokenEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof PasswordManagerTokenEntity))
		{
			return false;
		}
		final PasswordManagerTokenEntity that = (PasswordManagerTokenEntity)object;
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
