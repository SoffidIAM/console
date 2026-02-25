//
// (c) 2014 Soffid
//
//

package com.soffid.iam.iga.model;

/**
 *  Entity CustomObjectRoleEntity
 */

public abstract class CustomObjectRoleEntity {

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
	 * Attribute customObjectType
	 */
	private com.soffid.iam.iga.model.CustomObjectTypeEntity customObjectType;
	/**
	 * Gets value for attribute customObjectType
	 */
	public com.soffid.iam.iga.model.CustomObjectTypeEntity getCustomObjectType() {
		return this.customObjectType;
	}
	/**
	 * Sets value for attribute customObjectType
	 */
	public void setCustomObjectType(com.soffid.iam.iga.model.CustomObjectTypeEntity customObjectType) {
		this.customObjectType = customObjectType;
	}
	/**
	 * Attribute role
	 */
	private com.soffid.iam.iga.model.RoleEntity role;
	/**
	 * Gets value for attribute role
	 */
	public com.soffid.iam.iga.model.RoleEntity getRole() {
		return this.role;
	}
	/**
	 * Sets value for attribute role
	 */
	public void setRole(com.soffid.iam.iga.model.RoleEntity role) {
		this.role = role;
	}
	/**
	 * Attribute level
	 */
	private com.soffid.iam.base.api.AccountAccessLevelEnum level;
	/**
	 * Gets value for attribute level
	 */
	public com.soffid.iam.base.api.AccountAccessLevelEnum getLevel() {
		return this.level;
	}
	/**
	 * Sets value for attribute level
	 */
	public void setLevel(com.soffid.iam.base.api.AccountAccessLevelEnum level) {
		this.level = level;
	}
	/**
	 * Returns <code>true</code> if the argument is an CustomObjectRoleEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof CustomObjectRoleEntity))
		{
			return false;
		}
		final CustomObjectRoleEntity that = (CustomObjectRoleEntity)object;
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
