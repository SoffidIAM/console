//
// (c) 2014 Soffid
//
//

package com.soffid.iam.iga.model;

/**
 *  Entity UserTypeSystemEntity
 */

public abstract class UserTypeSystemEntity {

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
	 * Attribute system
	 */
	private com.soffid.iam.iga.model.SystemEntity system;
	/**
	 * Gets value for attribute system
	 */
	public com.soffid.iam.iga.model.SystemEntity getSystem() {
		return this.system;
	}
	/**
	 * Sets value for attribute system
	 */
	public void setSystem(com.soffid.iam.iga.model.SystemEntity system) {
		this.system = system;
	}
	/**
	 * Attribute userType
	 */
	private com.soffid.iam.base.model.UserTypeEntity userType;
	/**
	 * Gets value for attribute userType
	 */
	public com.soffid.iam.base.model.UserTypeEntity getUserType() {
		return this.userType;
	}
	/**
	 * Sets value for attribute userType
	 */
	public void setUserType(com.soffid.iam.base.model.UserTypeEntity userType) {
		this.userType = userType;
	}
	/**
	 * Returns <code>true</code> if the argument is an UserTypeSystemEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof UserTypeSystemEntity))
		{
			return false;
		}
		final UserTypeSystemEntity that = (UserTypeSystemEntity)object;
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
