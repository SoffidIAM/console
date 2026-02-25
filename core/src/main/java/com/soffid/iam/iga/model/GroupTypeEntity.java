//
// (c) 2014 Soffid
//
//

package com.soffid.iam.iga.model;

/**
 *  Entity GroupTypeEntity
 */

public abstract class GroupTypeEntity {

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
	 * Attribute name
	 */
	private java.lang.String name;
	/**
	 * Gets value for attribute name
	 */
	public java.lang.String getName() {
		return this.name;
	}
	/**
	 * Sets value for attribute name
	 */
	public void setName(java.lang.String name) {
		this.name = name;
	}
	/**
	 * Attribute description
	 */
	private java.lang.String description;
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
	 * Attribute roleHolder
	 * True if business units of this type can hold user roles
	 */
	private boolean roleHolder = false;
	/**
	 * Gets value for attribute roleHolder
	 */
	public boolean isRoleHolder() {
		return this.roleHolder;
	}
	/**
	 * Sets value for attribute roleHolder
	 */
	public void setRoleHolder(boolean roleHolder) {
		this.roleHolder = roleHolder;
	}
	/**
	 * Attribute tenant
	 */
	private com.soffid.iam.base.model.TenantEntity tenant;
	/**
	 * Gets value for attribute tenant
	 */
	public com.soffid.iam.base.model.TenantEntity getTenant() {
		return this.tenant;
	}
	/**
	 * Sets value for attribute tenant
	 */
	public void setTenant(com.soffid.iam.base.model.TenantEntity tenant) {
		this.tenant = tenant;
	}
	/**
	 * Attribute groupEntities
	 */
	private java.util.Collection<com.soffid.iam.iga.model.GroupEntity> groupEntities =  new java.util.HashSet<com.soffid.iam.iga.model.GroupEntity>();
	/**
	 * Gets value for attribute groupEntities
	 */
	public java.util.Collection<com.soffid.iam.iga.model.GroupEntity> getGroupEntities() {
		return this.groupEntities;
	}
	/**
	 * Sets value for attribute groupEntities
	 */
	public void setGroupEntities(java.util.Collection<com.soffid.iam.iga.model.GroupEntity> groupEntities) {
		this.groupEntities = groupEntities;
	}
	/**
	 * Attribute createdOn

	 */
	private java.util.Date createdOn;
	/**
	 * Gets value for attribute createdOn
	 */
	public java.util.Date getCreatedOn() {
		return this.createdOn;
	}
	/**
	 * Sets value for attribute createdOn
	 */
	public void setCreatedOn(java.util.Date createdOn) {
		this.createdOn = createdOn;
	}
	/**
	 * Attribute createdBy

	 */
	private java.lang.String createdBy;
	/**
	 * Gets value for attribute createdBy
	 */
	public java.lang.String getCreatedBy() {
		return this.createdBy;
	}
	/**
	 * Sets value for attribute createdBy
	 */
	public void setCreatedBy(java.lang.String createdBy) {
		this.createdBy = createdBy;
	}
	/**
	 * Attribute updatedOn

	 */
	private java.util.Date updatedOn;
	/**
	 * Gets value for attribute updatedOn
	 */
	public java.util.Date getUpdatedOn() {
		return this.updatedOn;
	}
	/**
	 * Sets value for attribute updatedOn
	 */
	public void setUpdatedOn(java.util.Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	/**
	 * Attribute updatedBy

	 */
	private java.lang.String updatedBy;
	/**
	 * Gets value for attribute updatedBy
	 */
	public java.lang.String getUpdatedBy() {
		return this.updatedBy;
	}
	/**
	 * Sets value for attribute updatedBy
	 */
	public void setUpdatedBy(java.lang.String updatedBy) {
		this.updatedBy = updatedBy;
	}
	/**
	 * Attribute deletedOn

	 */
	private java.util.Date deletedOn;
	/**
	 * Gets value for attribute deletedOn
	 */
	public java.util.Date getDeletedOn() {
		return this.deletedOn;
	}
	/**
	 * Sets value for attribute deletedOn
	 */
	public void setDeletedOn(java.util.Date deletedOn) {
		this.deletedOn = deletedOn;
	}
	/**
	 * Attribute deletedBy

	 */
	private java.lang.String deletedBy;
	/**
	 * Gets value for attribute deletedBy
	 */
	public java.lang.String getDeletedBy() {
		return this.deletedBy;
	}
	/**
	 * Sets value for attribute deletedBy
	 */
	public void setDeletedBy(java.lang.String deletedBy) {
		this.deletedBy = deletedBy;
	}
	/**
	 * Returns <code>true</code> if the argument is an GroupTypeEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof GroupTypeEntity))
		{
			return false;
		}
		final GroupTypeEntity that = (GroupTypeEntity)object;
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
