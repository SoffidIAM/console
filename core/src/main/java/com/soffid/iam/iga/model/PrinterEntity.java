//
// (c) 2014 Soffid
//
//

package com.soffid.iam.iga.model;

/**
 *  Entity PrinterEntity
 */

public abstract class PrinterEntity {

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
	 * Attribute model
	 */
	private java.lang.String model;
	/**
	 * Gets value for attribute model
	 */
	public java.lang.String getModel() {
		return this.model;
	}
	/**
	 * Sets value for attribute model
	 */
	public void setModel(java.lang.String model) {
		this.model = model;
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
	 * Attribute local
	 */
	private java.lang.Boolean local;
	/**
	 * Gets value for attribute local
	 */
	public java.lang.Boolean getLocal() {
		return this.local;
	}
	/**
	 * Sets value for attribute local
	 */
	public void setLocal(java.lang.Boolean local) {
		this.local = local;
	}
	/**
	 * Attribute server
	 */
	private com.soffid.iam.am.model.HostEntity server;
	/**
	 * Gets value for attribute server
	 */
	public com.soffid.iam.am.model.HostEntity getServer() {
		return this.server;
	}
	/**
	 * Sets value for attribute server
	 */
	public void setServer(com.soffid.iam.am.model.HostEntity server) {
		this.server = server;
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
	 * Attribute groups
	 */
	private java.util.Collection<com.soffid.iam.iga.model.PrinterGroupEntity> groups =  new java.util.HashSet<com.soffid.iam.iga.model.PrinterGroupEntity>();
	/**
	 * Gets value for attribute groups
	 */
	public java.util.Collection<com.soffid.iam.iga.model.PrinterGroupEntity> getGroups() {
		return this.groups;
	}
	/**
	 * Sets value for attribute groups
	 */
	public void setGroups(java.util.Collection<com.soffid.iam.iga.model.PrinterGroupEntity> groups) {
		this.groups = groups;
	}
	/**
	 * Attribute users
	 */
	private java.util.Collection<com.soffid.iam.iga.model.UserPrinterEntity> users =  new java.util.HashSet<com.soffid.iam.iga.model.UserPrinterEntity>();
	/**
	 * Gets value for attribute users
	 */
	public java.util.Collection<com.soffid.iam.iga.model.UserPrinterEntity> getUsers() {
		return this.users;
	}
	/**
	 * Sets value for attribute users
	 */
	public void setUsers(java.util.Collection<com.soffid.iam.iga.model.UserPrinterEntity> users) {
		this.users = users;
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
	 * Returns <code>true</code> if the argument is an PrinterEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof PrinterEntity))
		{
			return false;
		}
		final PrinterEntity that = (PrinterEntity)object;
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
