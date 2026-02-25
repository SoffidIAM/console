//
// (c) 2014 Soffid
//
//

package com.soffid.iam.am.model;

/**
 *  Entity EntryPointUserEntity
 */

public abstract class EntryPointUserEntity {

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
	 * Attribute authorizationLevel
	 */
	private java.lang.String authorizationLevel;
	/**
	 * Gets value for attribute authorizationLevel
	 */
	public java.lang.String getAuthorizationLevel() {
		return this.authorizationLevel;
	}
	/**
	 * Sets value for attribute authorizationLevel
	 */
	public void setAuthorizationLevel(java.lang.String authorizationLevel) {
		this.authorizationLevel = authorizationLevel;
	}
	/**
	 * Attribute entryPoint
	 */
	private com.soffid.iam.am.model.EntryPointEntity entryPoint;
	/**
	 * Gets value for attribute entryPoint
	 */
	public com.soffid.iam.am.model.EntryPointEntity getEntryPoint() {
		return this.entryPoint;
	}
	/**
	 * Sets value for attribute entryPoint
	 */
	public void setEntryPoint(com.soffid.iam.am.model.EntryPointEntity entryPoint) {
		this.entryPoint = entryPoint;
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
	 * Returns <code>true</code> if the argument is an EntryPointUserEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof EntryPointUserEntity))
		{
			return false;
		}
		final EntryPointUserEntity that = (EntryPointUserEntity)object;
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
