//
// (c) 2014 Soffid
//
//

package com.soffid.iam.iga.model;

/**
 *  Entity RoleAccountEntity
 */

public abstract class RoleAccountEntity {

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
	 * Attribute group
	 * Group that limits the scope of the role grant
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
	 * Attribute domainValue
	 * Value that limits the scope of the role grant
	 */
	private com.soffid.iam.iga.model.DomainValueEntity domainValue;
	/**
	 * Gets value for attribute domainValue
	 */
	public com.soffid.iam.iga.model.DomainValueEntity getDomainValue() {
		return this.domainValue;
	}
	/**
	 * Sets value for attribute domainValue
	 */
	public void setDomainValue(com.soffid.iam.iga.model.DomainValueEntity domainValue) {
		this.domainValue = domainValue;
	}
	/**
	 * Attribute domainType
	 */
	private java.lang.String domainType;
	/**
	 * Gets value for attribute domainType
	 */
	public java.lang.String getDomainType() {
		return this.domainType;
	}
	/**
	 * Sets value for attribute domainType
	 */
	public void setDomainType(java.lang.String domainType) {
		this.domainType = domainType;
	}
	/**
	 * Attribute informationSystem
	 * Information System that limits the scope of the role grant
	 */
	private com.soffid.iam.iga.model.InformationSystemEntity informationSystem;
	/**
	 * Gets value for attribute informationSystem
	 */
	public com.soffid.iam.iga.model.InformationSystemEntity getInformationSystem() {
		return this.informationSystem;
	}
	/**
	 * Sets value for attribute informationSystem
	 */
	public void setInformationSystem(com.soffid.iam.iga.model.InformationSystemEntity informationSystem) {
		this.informationSystem = informationSystem;
	}
	/**
	 * Attribute account
	 */
	private com.soffid.iam.base.model.AccountEntity account;
	/**
	 * Gets value for attribute account
	 */
	public com.soffid.iam.base.model.AccountEntity getAccount() {
		return this.account;
	}
	/**
	 * Sets value for attribute account
	 */
	public void setAccount(com.soffid.iam.base.model.AccountEntity account) {
		this.account = account;
	}
	/**
	 * Attribute rule
	 */
	private com.soffid.iam.iga.model.RuleEntity rule;
	/**
	 * Gets value for attribute rule
	 */
	public com.soffid.iam.iga.model.RuleEntity getRule() {
		return this.rule;
	}
	/**
	 * Sets value for attribute rule
	 */
	public void setRule(com.soffid.iam.iga.model.RuleEntity rule) {
		this.rule = rule;
	}
	/**
	 * Attribute startDate
	 */
	private java.util.Date startDate;
	/**
	 * Gets value for attribute startDate
	 */
	public java.util.Date getStartDate() {
		return this.startDate;
	}
	/**
	 * Sets value for attribute startDate
	 */
	public void setStartDate(java.util.Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * Attribute endDate
	 */
	private java.util.Date endDate;
	/**
	 * Gets value for attribute endDate
	 */
	public java.util.Date getEndDate() {
		return this.endDate;
	}
	/**
	 * Sets value for attribute endDate
	 */
	public void setEndDate(java.util.Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * Attribute enabled
	 */
	private boolean enabled = true;
	/**
	 * Gets value for attribute enabled
	 */
	public boolean isEnabled() {
		return this.enabled;
	}
	/**
	 * Sets value for attribute enabled
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	/**
	 * Attribute approvalPending
	 */
	private boolean approvalPending = false;
	/**
	 * Gets value for attribute approvalPending
	 */
	public boolean isApprovalPending() {
		return this.approvalPending;
	}
	/**
	 * Sets value for attribute approvalPending
	 */
	public void setApprovalPending(boolean approvalPending) {
		this.approvalPending = approvalPending;
	}
	/**
	 * Attribute removalPending
	 */
	private java.lang.Boolean removalPending = false;
	/**
	 * Gets value for attribute removalPending
	 */
	public java.lang.Boolean getRemovalPending() {
		return this.removalPending;
	}
	/**
	 * Sets value for attribute removalPending
	 */
	public void setRemovalPending(java.lang.Boolean removalPending) {
		this.removalPending = removalPending;
	}
	/**
	 * Attribute holderGroup
	 * This foreign key binds this the role assignment to the group membership that grants this role to the account. Not applicable for shared accounts
	 */
	private com.soffid.iam.iga.model.GroupEntity holderGroup;
	/**
	 * Gets value for attribute holderGroup
	 */
	public com.soffid.iam.iga.model.GroupEntity getHolderGroup() {
		return this.holderGroup;
	}
	/**
	 * Sets value for attribute holderGroup
	 */
	public void setHolderGroup(com.soffid.iam.iga.model.GroupEntity holderGroup) {
		this.holderGroup = holderGroup;
	}
	/**
	 * Attribute approvalProcess
	 * When an aproval process is needed to enable this rol assignment
	 */
	private java.lang.Long approvalProcess;
	/**
	 * Gets value for attribute approvalProcess
	 */
	public java.lang.Long getApprovalProcess() {
		return this.approvalProcess;
	}
	/**
	 * Sets value for attribute approvalProcess
	 */
	public void setApprovalProcess(java.lang.Long approvalProcess) {
		this.approvalProcess = approvalProcess;
	}
	/**
	 * Attribute certificationDate
	 * Last certification date
	 */
	private java.util.Date certificationDate;
	/**
	 * Gets value for attribute certificationDate
	 */
	public java.util.Date getCertificationDate() {
		return this.certificationDate;
	}
	/**
	 * Sets value for attribute certificationDate
	 */
	public void setCertificationDate(java.util.Date certificationDate) {
		this.certificationDate = certificationDate;
	}
	/**
	 * Attribute parent
	 * Parent grant. Used only in non mandatory rol to rol grants
	 */
	private com.soffid.iam.iga.model.RoleAccountEntity parent;
	/**
	 * Gets value for attribute parent
	 */
	public com.soffid.iam.iga.model.RoleAccountEntity getParent() {
		return this.parent;
	}
	/**
	 * Sets value for attribute parent
	 */
	public void setParent(com.soffid.iam.iga.model.RoleAccountEntity parent) {
		this.parent = parent;
	}
	/**
	 * Attribute delegationStatus
	 * Delegation status
	 */
	private com.soffid.iam.iga.api.DelegationStatus delegationStatus;
	/**
	 * Gets value for attribute delegationStatus
	 */
	public com.soffid.iam.iga.api.DelegationStatus getDelegationStatus() {
		return this.delegationStatus;
	}
	/**
	 * Sets value for attribute delegationStatus
	 */
	public void setDelegationStatus(com.soffid.iam.iga.api.DelegationStatus delegationStatus) {
		this.delegationStatus = delegationStatus;
	}
	/**
	 * Attribute ownerAccount
	 * Entitled account who delegatse to delegateAccount
	 */
	private com.soffid.iam.base.model.AccountEntity ownerAccount;
	/**
	 * Gets value for attribute ownerAccount
	 */
	public com.soffid.iam.base.model.AccountEntity getOwnerAccount() {
		return this.ownerAccount;
	}
	/**
	 * Sets value for attribute ownerAccount
	 */
	public void setOwnerAccount(com.soffid.iam.base.model.AccountEntity ownerAccount) {
		this.ownerAccount = ownerAccount;
	}
	/**
	 * Attribute delegateAccount
	 * Delegate account
	 */
	private com.soffid.iam.base.model.AccountEntity delegateAccount;
	/**
	 * Gets value for attribute delegateAccount
	 */
	public com.soffid.iam.base.model.AccountEntity getDelegateAccount() {
		return this.delegateAccount;
	}
	/**
	 * Sets value for attribute delegateAccount
	 */
	public void setDelegateAccount(com.soffid.iam.base.model.AccountEntity delegateAccount) {
		this.delegateAccount = delegateAccount;
	}
	/**
	 * Attribute delegateSince
	 * Delegate since date
	 */
	private java.util.Date delegateSince;
	/**
	 * Gets value for attribute delegateSince
	 */
	public java.util.Date getDelegateSince() {
		return this.delegateSince;
	}
	/**
	 * Sets value for attribute delegateSince
	 */
	public void setDelegateSince(java.util.Date delegateSince) {
		this.delegateSince = delegateSince;
	}
	/**
	 * Attribute delegateUntil
	 * Delegate until date
	 */
	private java.util.Date delegateUntil;
	/**
	 * Gets value for attribute delegateUntil
	 */
	public java.util.Date getDelegateUntil() {
		return this.delegateUntil;
	}
	/**
	 * Sets value for attribute delegateUntil
	 */
	public void setDelegateUntil(java.util.Date delegateUntil) {
		this.delegateUntil = delegateUntil;
	}
	/**
	 * Attribute externalId
	 */
	private java.lang.String externalId;
	/**
	 * Gets value for attribute externalId
	 */
	public java.lang.String getExternalId() {
		return this.externalId;
	}
	/**
	 * Sets value for attribute externalId
	 */
	public void setExternalId(java.lang.String externalId) {
		this.externalId = externalId;
	}
	/**
	 * Attribute attributes

	 */
	private java.util.Collection<com.soffid.iam.iga.model.RoleAccountAttributeEntity> attributes =  new java.util.HashSet<com.soffid.iam.iga.model.RoleAccountAttributeEntity>();
	/**
	 * Gets value for attribute attributes
	 */
	public java.util.Collection<com.soffid.iam.iga.model.RoleAccountAttributeEntity> getAttributes() {
		return this.attributes;
	}
	/**
	 * Sets value for attribute attributes
	 */
	public void setAttributes(java.util.Collection<com.soffid.iam.iga.model.RoleAccountAttributeEntity> attributes) {
		this.attributes = attributes;
	}
	/**
	 * Attribute children

	 */
	private java.util.Collection<com.soffid.iam.iga.model.RoleAccountEntity> children =  new java.util.HashSet<com.soffid.iam.iga.model.RoleAccountEntity>();
	/**
	 * Gets value for attribute children
	 */
	public java.util.Collection<com.soffid.iam.iga.model.RoleAccountEntity> getChildren() {
		return this.children;
	}
	/**
	 * Sets value for attribute children
	 */
	public void setChildren(java.util.Collection<com.soffid.iam.iga.model.RoleAccountEntity> children) {
		this.children = children;
	}
	/**
	 * Attribute events

	 */
	private java.util.Collection<com.soffid.iam.rc.model.IssueEntity> events =  new java.util.HashSet<com.soffid.iam.rc.model.IssueEntity>();
	/**
	 * Gets value for attribute events
	 */
	public java.util.Collection<com.soffid.iam.rc.model.IssueEntity> getEvents() {
		return this.events;
	}
	/**
	 * Sets value for attribute events
	 */
	public void setEvents(java.util.Collection<com.soffid.iam.rc.model.IssueEntity> events) {
		this.events = events;
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
	 * Operation isAllowed
	 * Returns true if the permission on this object is granted
	 * @param permission
	 * @return
	**/
	 public abstract boolean isAllowed(
		java.lang.String permission);

	/**
	 * Operation toString
	 * @return
	**/
	 public abstract java.lang.String toString();

	/**
	 * Operation customCache
	**/
	 public abstract void customCache();

	/**
	 * Returns <code>true</code> if the argument is an RoleAccountEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof RoleAccountEntity))
		{
			return false;
		}
		final RoleAccountEntity that = (RoleAccountEntity)object;
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
