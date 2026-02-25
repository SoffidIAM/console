//
// (c) 2014 Soffid
//
//

package com.soffid.iam.pam.model;

/**
 *  Entity PamPolicyEntity
 */

public abstract class PamPolicyEntity {

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
	 * Attribute author
	 */
	private java.lang.String author;
	/**
	 * Gets value for attribute author
	 */
	public java.lang.String getAuthor() {
		return this.author;
	}
	/**
	 * Sets value for attribute author
	 */
	public void setAuthor(java.lang.String author) {
		this.author = author;
	}
	/**
	 * Attribute date
	 */
	private java.util.Date date;
	/**
	 * Gets value for attribute date
	 */
	public java.util.Date getDate() {
		return this.date;
	}
	/**
	 * Sets value for attribute date
	 */
	public void setDate(java.util.Date date) {
		this.date = date;
	}
	/**
	 * Attribute recordingDuration
	 */
	private java.lang.Integer recordingDuration;
	/**
	 * Gets value for attribute recordingDuration
	 */
	public java.lang.Integer getRecordingDuration() {
		return this.recordingDuration;
	}
	/**
	 * Sets value for attribute recordingDuration
	 */
	public void setRecordingDuration(java.lang.Integer recordingDuration) {
		this.recordingDuration = recordingDuration;
	}
	/**
	 * Attribute expression
	 */
	private java.lang.String expression;
	/**
	 * Gets value for attribute expression
	 */
	public java.lang.String getExpression() {
		return this.expression;
	}
	/**
	 * Sets value for attribute expression
	 */
	public void setExpression(java.lang.String expression) {
		this.expression = expression;
	}
	/**
	 * Attribute priority
	 */
	private java.lang.Integer priority;
	/**
	 * Gets value for attribute priority
	 */
	public java.lang.Integer getPriority() {
		return this.priority;
	}
	/**
	 * Sets value for attribute priority
	 */
	public void setPriority(java.lang.Integer priority) {
		this.priority = priority;
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
	 * Attribute vaultFolders

	 */
	private java.util.Collection<com.soffid.iam.am.model.VaultFolderEntity> vaultFolders =  new java.util.HashSet<com.soffid.iam.am.model.VaultFolderEntity>();
	/**
	 * Gets value for attribute vaultFolders
	 */
	public java.util.Collection<com.soffid.iam.am.model.VaultFolderEntity> getVaultFolders() {
		return this.vaultFolders;
	}
	/**
	 * Sets value for attribute vaultFolders
	 */
	public void setVaultFolders(java.util.Collection<com.soffid.iam.am.model.VaultFolderEntity> vaultFolders) {
		this.vaultFolders = vaultFolders;
	}
	/**
	 * Attribute actions

	 */
	private java.util.Collection<com.soffid.iam.pam.model.PamActionEntity> actions =  new java.util.HashSet<com.soffid.iam.pam.model.PamActionEntity>();
	/**
	 * Gets value for attribute actions
	 */
	public java.util.Collection<com.soffid.iam.pam.model.PamActionEntity> getActions() {
		return this.actions;
	}
	/**
	 * Sets value for attribute actions
	 */
	public void setActions(java.util.Collection<com.soffid.iam.pam.model.PamActionEntity> actions) {
		this.actions = actions;
	}
	/**
	 * Attribute justInTimePermissions

	 */
	private java.util.Collection<com.soffid.iam.pam.model.PamPolicyJITPermissionEntity> justInTimePermissions =  new java.util.HashSet<com.soffid.iam.pam.model.PamPolicyJITPermissionEntity>();
	/**
	 * Gets value for attribute justInTimePermissions
	 */
	public java.util.Collection<com.soffid.iam.pam.model.PamPolicyJITPermissionEntity> getJustInTimePermissions() {
		return this.justInTimePermissions;
	}
	/**
	 * Sets value for attribute justInTimePermissions
	 */
	public void setJustInTimePermissions(java.util.Collection<com.soffid.iam.pam.model.PamPolicyJITPermissionEntity> justInTimePermissions) {
		this.justInTimePermissions = justInTimePermissions;
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
	 * Returns <code>true</code> if the argument is an PamPolicyEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof PamPolicyEntity))
		{
			return false;
		}
		final PamPolicyEntity that = (PamPolicyEntity)object;
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
