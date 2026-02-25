//
// (c) 2014 Soffid
//
//

package com.soffid.iam.am.model;

/**
 *  Entity SecretEntity
 */

public abstract class SecretEntity {

	/**

	 * The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute server
	 */
	private com.soffid.iam.sync.model.ServerEntity server;
	/**
	 * Gets value for attribute server
	 */
	public com.soffid.iam.sync.model.ServerEntity getServer() {
		return this.server;
	}
	/**
	 * Sets value for attribute server
	 */
	public void setServer(com.soffid.iam.sync.model.ServerEntity server) {
		this.server = server;
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
	 * Attribute secrets
	 */
	private byte[] secrets;
	/**
	 * Gets value for attribute secrets
	 */
	public byte[] getSecrets() {
		return this.secrets;
	}
	/**
	 * Sets value for attribute secrets
	 */
	public void setSecrets(byte[] secrets) {
		this.secrets = secrets;
	}
	/**
	 * Returns <code>true</code> if the argument is an SecretEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof SecretEntity))
		{
			return false;
		}
		final SecretEntity that = (SecretEntity)object;
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
