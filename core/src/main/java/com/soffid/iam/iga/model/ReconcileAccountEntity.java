//
// (c) 2014 Soffid
//
//

package com.soffid.iam.iga.model;

/**
 *  Entity ReconcileAccountEntity
 */

public abstract class ReconcileAccountEntity {

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
	 * Attribute accountName
	 */
	private java.lang.String accountName;
	/**
	 * Gets value for attribute accountName
	 */
	public java.lang.String getAccountName() {
		return this.accountName;
	}
	/**
	 * Sets value for attribute accountName
	 */
	public void setAccountName(java.lang.String accountName) {
		this.accountName = accountName;
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
	 * Attribute processId
	 */
	private java.lang.Long processId;
	/**
	 * Gets value for attribute processId
	 */
	public java.lang.Long getProcessId() {
		return this.processId;
	}
	/**
	 * Sets value for attribute processId
	 */
	public void setProcessId(java.lang.Long processId) {
		this.processId = processId;
	}
	/**
	 * Attribute newAccount
	 */
	private java.lang.Boolean newAccount;
	/**
	 * Gets value for attribute newAccount
	 */
	public java.lang.Boolean getNewAccount() {
		return this.newAccount;
	}
	/**
	 * Sets value for attribute newAccount
	 */
	public void setNewAccount(java.lang.Boolean newAccount) {
		this.newAccount = newAccount;
	}
	/**
	 * Attribute deletedAccount
	 */
	private java.lang.Boolean deletedAccount;
	/**
	 * Gets value for attribute deletedAccount
	 */
	public java.lang.Boolean getDeletedAccount() {
		return this.deletedAccount;
	}
	/**
	 * Sets value for attribute deletedAccount
	 */
	public void setDeletedAccount(java.lang.Boolean deletedAccount) {
		this.deletedAccount = deletedAccount;
	}
	/**
	 * Attribute proposedAction
	 */
	private com.soffid.iam.iga.api.AccountProposedAction proposedAction;
	/**
	 * Gets value for attribute proposedAction
	 */
	public com.soffid.iam.iga.api.AccountProposedAction getProposedAction() {
		return this.proposedAction;
	}
	/**
	 * Sets value for attribute proposedAction
	 */
	public void setProposedAction(com.soffid.iam.iga.api.AccountProposedAction proposedAction) {
		this.proposedAction = proposedAction;
	}
	/**
	 * Attribute dispatcher
	 */
	private java.lang.String dispatcher;
	/**
	 * Gets value for attribute dispatcher
	 */
	public java.lang.String getDispatcher() {
		return this.dispatcher;
	}
	/**
	 * Sets value for attribute dispatcher
	 */
	public void setDispatcher(java.lang.String dispatcher) {
		this.dispatcher = dispatcher;
	}
	/**
	 * Attribute userCode
	 */
	private java.lang.String userCode;
	/**
	 * Gets value for attribute userCode
	 */
	public java.lang.String getUserCode() {
		return this.userCode;
	}
	/**
	 * Sets value for attribute userCode
	 */
	public void setUserCode(java.lang.String userCode) {
		this.userCode = userCode;
	}
	/**
	 * Attribute primaryGroup
	 */
	private java.lang.String primaryGroup;
	/**
	 * Gets value for attribute primaryGroup
	 */
	public java.lang.String getPrimaryGroup() {
		return this.primaryGroup;
	}
	/**
	 * Sets value for attribute primaryGroup
	 */
	public void setPrimaryGroup(java.lang.String primaryGroup) {
		this.primaryGroup = primaryGroup;
	}
	/**
	 * Attribute userType
	 */
	private java.lang.String userType;
	/**
	 * Gets value for attribute userType
	 */
	public java.lang.String getUserType() {
		return this.userType;
	}
	/**
	 * Sets value for attribute userType
	 */
	public void setUserType(java.lang.String userType) {
		this.userType = userType;
	}
	/**
	 * Attribute userFullName
	 */
	private java.lang.String userFullName;
	/**
	 * Gets value for attribute userFullName
	 */
	public java.lang.String getUserFullName() {
		return this.userFullName;
	}
	/**
	 * Sets value for attribute userFullName
	 */
	public void setUserFullName(java.lang.String userFullName) {
		this.userFullName = userFullName;
	}
	/**
	 * Attribute accountType
	 */
	private com.soffid.iam.base.api.AccountType accountType;
	/**
	 * Gets value for attribute accountType
	 */
	public com.soffid.iam.base.api.AccountType getAccountType() {
		return this.accountType;
	}
	/**
	 * Sets value for attribute accountType
	 */
	public void setAccountType(com.soffid.iam.base.api.AccountType accountType) {
		this.accountType = accountType;
	}
	/**
	 * Attribute active
	 */
	private boolean active = true;
	/**
	 * Gets value for attribute active
	 */
	public boolean isActive() {
		return this.active;
	}
	/**
	 * Sets value for attribute active
	 */
	public void setActive(boolean active) {
		this.active = active;
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
	 * Attribute attributes

	 */
	private java.util.Collection<com.soffid.iam.iga.model.ReconcileAccountAttributesEntity> attributes =  new java.util.HashSet<com.soffid.iam.iga.model.ReconcileAccountAttributesEntity>();
	/**
	 * Gets value for attribute attributes
	 */
	public java.util.Collection<com.soffid.iam.iga.model.ReconcileAccountAttributesEntity> getAttributes() {
		return this.attributes;
	}
	/**
	 * Sets value for attribute attributes
	 */
	public void setAttributes(java.util.Collection<com.soffid.iam.iga.model.ReconcileAccountAttributesEntity> attributes) {
		this.attributes = attributes;
	}
	/**
	 * Returns <code>true</code> if the argument is an ReconcileAccountEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof ReconcileAccountEntity))
		{
			return false;
		}
		final ReconcileAccountEntity that = (ReconcileAccountEntity)object;
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
