//
// (c) 2014 Soffid
//
//

package com.soffid.iam.am.model;

/**
 *  Entity VaultFolderAccessEntity
 * Contains the access control list for a vault folder
 */

public abstract class VaultFolderAccessEntity {

	/**

	 * The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute role
	 * Grantee role
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
	 * Attribute group
	 * Grantee group
	 */
	private com.soffid.iam.iga.model.GroupEntity group;
	/**
	 * Gets value for attribute group
	 */
	public com.soffid.iam.iga.model.GroupEntity getGroup() {
		return this.group;
	}
	/**
	 * Sets value for attribute group
	 */
	public void setGroup(com.soffid.iam.iga.model.GroupEntity group) {
		this.group = group;
	}
	/**
	 * Attribute user
	 * Grantee user
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
	 * Attribute vault
	 * Granted folder
	 */
	private com.soffid.iam.am.model.VaultFolderEntity vault;
	/**
	 * Gets value for attribute vault
	 */
	public com.soffid.iam.am.model.VaultFolderEntity getVault() {
		return this.vault;
	}
	/**
	 * Sets value for attribute vault
	 */
	public void setVault(com.soffid.iam.am.model.VaultFolderEntity vault) {
		this.vault = vault;
	}
	/**
	 * Attribute level
	 * Access level
	 */
	private com.soffid.iam.base.api.AccountAccessLevelEnum level =  com.soffid.iam.base.api.AccountAccessLevelEnum.ACCESS_OWNER;
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
	 * Attribute id
	 * Identifier
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
	 * Returns <code>true</code> if the argument is an VaultFolderAccessEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof VaultFolderAccessEntity))
		{
			return false;
		}
		final VaultFolderAccessEntity that = (VaultFolderAccessEntity)object;
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
