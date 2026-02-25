//
// (C) 2020 Soffid
//
//

package com.soffid.iam.iga.api;
/**
 * ValueObject RoleAccount
 **/
public class RoleAccount

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute id

	 */
	private java.lang.Long id;

	/**
	 * Attribute accountId

	 */
	private java.lang.Long accountId;

	/**
	 * Attribute accountName

	 */
	private java.lang.String accountName;

	/**
	 * Attribute accountSystem

	 */
	private java.lang.String accountSystem;

	/**
	 * Attribute roleName

	 */
	private java.lang.String roleName;

	/**
	 * Attribute roleId

	 */
	private java.lang.Long roleId;

	/**
	 * Attribute roleCategory

	 */
	private java.lang.String roleCategory;

	/**
	 * Attribute informationSystemName

	 */
	private java.lang.String informationSystemName;

	/**
	 * Attribute roleDescription

	 */
	private java.lang.String roleDescription;

	/**
	 * Attribute userFullName

	 */
	private java.lang.String userFullName;

	/**
	 * Attribute groupDescription

	 */
	private java.lang.String groupDescription;

	/**
	 * Attribute domainValue

	 */
	private com.soffid.iam.iga.api.DomainValue domainValue;

	/**
	 * Attribute system

	 */
	private java.lang.String system;

	/**
	 * Attribute userGroupCode

	 */
	private java.lang.String userGroupCode;

	/**
	 * Attribute bpmEnabled

	 */
	private java.lang.String bpmEnabled;

	/**
	 * Attribute userName

	 */
	private java.lang.String userName;

	/**
	 * Attribute ruleId
	 * Rule that has cretaed the role assignment

	 */
	private java.lang.Long ruleId;

	/**
	 * Attribute ruleDescription

	 */
	private java.lang.String ruleDescription;

	/**
	 * Attribute sodRisk

	 */
	private com.soffid.iam.rc.api.SoDRisk sodRisk;

	/**
	 * Attribute sodRules

	 */
	private java.util.Collection<com.soffid.iam.rc.api.SoDRule> sodRules;

	/**
	 * Attribute startDate
	 * Rol assignment start date. Null means since now

	 */
	private java.util.Date startDate;

	/**
	 * Attribute endDate
	 * Rol assignment end date. Null means forever

	 */
	private java.util.Date endDate;

	/**
	 * Attribute enabled
	 * True if the role is enabled or not. When the start/end date reaches, the flag should change

	 */
	private boolean enabled = true;

	/**
	 * Attribute approvalPending

	 */
	private boolean approvalPending = true;

	/**
	 * Attribute removalPending

	 */
	private java.lang.Boolean removalPending = false;

	/**
	 * Attribute holderGroup
	 * This attribute holds the group name that is bound to this the role assignment. Not applicable for shared accounts

	 */
	private java.lang.String holderGroup;

	/**
	 * Attribute approvalProcess
	 * When an aproval process is needed to enable this rol assignment

	 */
	private java.lang.Long approvalProcess;

	/**
	 * Attribute certificationDate
	 * Last certification date

	 */
	private java.util.Date certificationDate;

	/**
	 * Attribute parentGrant
	 * Parent grant id, for optinal role to role grant

	 */
	private java.lang.Long parentGrant;

	/**
	 * Attribute delegationStatus
	 * Delegation status

	 */
	private com.soffid.iam.iga.api.DelegationStatus delegationStatus;

	/**
	 * Attribute ownerAccount
	 * Entitled account who delegatse to delegateAccount

	 */
	private java.lang.String ownerAccount;

	/**
	 * Attribute delegateAccount
	 * Delegate account

	 */
	private java.lang.String delegateAccount;

	/**
	 * Attribute delegateSince
	 * Delegate since date

	 */
	private java.util.Date delegateSince;

	/**
	 * Attribute delegateUntil
	 * Delegate until date

	 */
	private java.util.Date delegateUntil;

	/**
	 * Attribute externalId

	 */
	private java.lang.String externalId;

	/**
	 * Attribute attributes
	 * Grant custom attributes

	 */
	private java.util.Map<java.lang.String,java.lang.Object> attributes = new java.util.HashMap<String,Object>();

	/**
	 * Attribute createdOn


	 */
	private java.util.Date createdOn;

	/**
	 * Attribute createdBy


	 */
	private java.lang.String createdBy;

	/**
	 * Attribute updatedOn


	 */
	private java.util.Date updatedOn;

	/**
	 * Attribute updatedBy


	 */
	private java.lang.String updatedBy;

	/**
	 * Attribute deletedOn


	 */
	private java.util.Date deletedOn;

	/**
	 * Attribute deletedBy


	 */
	private java.lang.String deletedBy;

	public RoleAccount()
	{
	}

	public RoleAccount(java.lang.Long id, java.lang.Long accountId, java.lang.String accountName, java.lang.String accountSystem, java.lang.String roleName, java.lang.Long roleId, java.lang.String roleCategory, java.lang.String informationSystemName, java.lang.String roleDescription, java.lang.String userFullName, java.lang.String groupDescription, com.soffid.iam.iga.api.DomainValue domainValue, java.lang.String system, java.lang.String userGroupCode, java.lang.String bpmEnabled, java.lang.String userName, java.lang.Long ruleId, java.lang.String ruleDescription, com.soffid.iam.rc.api.SoDRisk sodRisk, java.util.Collection<com.soffid.iam.rc.api.SoDRule> sodRules, java.util.Date startDate, java.util.Date endDate, boolean enabled, boolean approvalPending, java.lang.Boolean removalPending, java.lang.String holderGroup, java.lang.Long approvalProcess, java.util.Date certificationDate, java.lang.Long parentGrant, com.soffid.iam.iga.api.DelegationStatus delegationStatus, java.lang.String ownerAccount, java.lang.String delegateAccount, java.util.Date delegateSince, java.util.Date delegateUntil, java.lang.String externalId, java.util.Map<java.lang.String,java.lang.Object> attributes, java.util.Date createdOn, java.lang.String createdBy, java.util.Date updatedOn, java.lang.String updatedBy, java.util.Date deletedOn, java.lang.String deletedBy)
	{
		super();
		this.id = id;
		this.accountId = accountId;
		this.accountName = accountName;
		this.accountSystem = accountSystem;
		this.roleName = roleName;
		this.roleId = roleId;
		this.roleCategory = roleCategory;
		this.informationSystemName = informationSystemName;
		this.roleDescription = roleDescription;
		this.userFullName = userFullName;
		this.groupDescription = groupDescription;
		this.domainValue = domainValue;
		this.system = system;
		this.userGroupCode = userGroupCode;
		this.bpmEnabled = bpmEnabled;
		this.userName = userName;
		this.ruleId = ruleId;
		this.ruleDescription = ruleDescription;
		this.sodRisk = sodRisk;
		this.sodRules = sodRules;
		this.startDate = startDate;
		this.endDate = endDate;
		this.enabled = enabled;
		this.approvalPending = approvalPending;
		this.removalPending = removalPending;
		this.holderGroup = holderGroup;
		this.approvalProcess = approvalProcess;
		this.certificationDate = certificationDate;
		this.parentGrant = parentGrant;
		this.delegationStatus = delegationStatus;
		this.ownerAccount = ownerAccount;
		this.delegateAccount = delegateAccount;
		this.delegateSince = delegateSince;
		this.delegateUntil = delegateUntil;
		this.externalId = externalId;
		this.attributes = attributes;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
		this.updatedOn = updatedOn;
		this.updatedBy = updatedBy;
		this.deletedOn = deletedOn;
		this.deletedBy = deletedBy;
	}

	public RoleAccount(boolean enabled, boolean approvalPending)
	{
		super();
		this.enabled = enabled;
		this.approvalPending = approvalPending;
	}

	public RoleAccount(RoleAccount otherBean)
	{
		this(otherBean.id, otherBean.accountId, otherBean.accountName, otherBean.accountSystem, otherBean.roleName, otherBean.roleId, otherBean.roleCategory, otherBean.informationSystemName, otherBean.roleDescription, otherBean.userFullName, otherBean.groupDescription, otherBean.domainValue, otherBean.system, otherBean.userGroupCode, otherBean.bpmEnabled, otherBean.userName, otherBean.ruleId, otherBean.ruleDescription, otherBean.sodRisk, otherBean.sodRules, otherBean.startDate, otherBean.endDate, otherBean.enabled, otherBean.approvalPending, otherBean.removalPending, otherBean.holderGroup, otherBean.approvalProcess, otherBean.certificationDate, otherBean.parentGrant, otherBean.delegationStatus, otherBean.ownerAccount, otherBean.delegateAccount, otherBean.delegateSince, otherBean.delegateUntil, otherBean.externalId, otherBean.attributes, otherBean.createdOn, otherBean.createdBy, otherBean.updatedOn, otherBean.updatedBy, otherBean.deletedOn, otherBean.deletedBy);
	}

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
	 * Gets value for attribute accountId
	 */
	public java.lang.Long getAccountId() {
		return this.accountId;
	}

	/**
	 * Sets value for attribute accountId
	 */
	public void setAccountId(java.lang.Long accountId) {
		this.accountId = accountId;
	}

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
	 * Gets value for attribute accountSystem
	 */
	public java.lang.String getAccountSystem() {
		return this.accountSystem;
	}

	/**
	 * Sets value for attribute accountSystem
	 */
	public void setAccountSystem(java.lang.String accountSystem) {
		this.accountSystem = accountSystem;
	}

	/**
	 * Gets value for attribute roleName
	 */
	public java.lang.String getRoleName() {
		return this.roleName;
	}

	/**
	 * Sets value for attribute roleName
	 */
	public void setRoleName(java.lang.String roleName) {
		this.roleName = roleName;
	}

	/**
	 * Gets value for attribute roleId
	 */
	public java.lang.Long getRoleId() {
		return this.roleId;
	}

	/**
	 * Sets value for attribute roleId
	 */
	public void setRoleId(java.lang.Long roleId) {
		this.roleId = roleId;
	}

	/**
	 * Gets value for attribute roleCategory
	 */
	public java.lang.String getRoleCategory() {
		return this.roleCategory;
	}

	/**
	 * Sets value for attribute roleCategory
	 */
	public void setRoleCategory(java.lang.String roleCategory) {
		this.roleCategory = roleCategory;
	}

	/**
	 * Gets value for attribute informationSystemName
	 */
	public java.lang.String getInformationSystemName() {
		return this.informationSystemName;
	}

	/**
	 * Sets value for attribute informationSystemName
	 */
	public void setInformationSystemName(java.lang.String informationSystemName) {
		this.informationSystemName = informationSystemName;
	}

	/**
	 * Gets value for attribute roleDescription
	 */
	public java.lang.String getRoleDescription() {
		return this.roleDescription;
	}

	/**
	 * Sets value for attribute roleDescription
	 */
	public void setRoleDescription(java.lang.String roleDescription) {
		this.roleDescription = roleDescription;
	}

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
	 * Gets value for attribute groupDescription
	 */
	public java.lang.String getGroupDescription() {
		return this.groupDescription;
	}

	/**
	 * Sets value for attribute groupDescription
	 */
	public void setGroupDescription(java.lang.String groupDescription) {
		this.groupDescription = groupDescription;
	}

	/**
	 * Gets value for attribute domainValue
	 */
	public com.soffid.iam.iga.api.DomainValue getDomainValue() {
		return this.domainValue;
	}

	/**
	 * Sets value for attribute domainValue
	 */
	public void setDomainValue(com.soffid.iam.iga.api.DomainValue domainValue) {
		this.domainValue = domainValue;
	}

	/**
	 * Gets value for attribute system
	 */
	public java.lang.String getSystem() {
		return this.system;
	}

	/**
	 * Sets value for attribute system
	 */
	public void setSystem(java.lang.String system) {
		this.system = system;
	}

	/**
	 * Gets value for attribute userGroupCode
	 */
	public java.lang.String getUserGroupCode() {
		return this.userGroupCode;
	}

	/**
	 * Sets value for attribute userGroupCode
	 */
	public void setUserGroupCode(java.lang.String userGroupCode) {
		this.userGroupCode = userGroupCode;
	}

	/**
	 * Gets value for attribute bpmEnabled
	 */
	public java.lang.String getBpmEnabled() {
		return this.bpmEnabled;
	}

	/**
	 * Sets value for attribute bpmEnabled
	 */
	public void setBpmEnabled(java.lang.String bpmEnabled) {
		this.bpmEnabled = bpmEnabled;
	}

	/**
	 * Gets value for attribute bpmEnabled
	 */
	public java.lang.String getBpmEnforced() {
		return this.bpmEnabled;
	}

	/**
	 * Sets value for attribute bpmEnabled
	 */
	public void setBpmEnforced(java.lang.String bpmEnabled) {
		this.bpmEnabled = bpmEnabled;
	}

	/**
	 * Gets value for attribute userName
	 */
	public java.lang.String getUserName() {
		return this.userName;
	}

	/**
	 * Sets value for attribute userName
	 */
	public void setUserName(java.lang.String userName) {
		this.userName = userName;
	}

	/**
	 * Gets value for attribute ruleId
	 */
	public java.lang.Long getRuleId() {
		return this.ruleId;
	}

	/**
	 * Sets value for attribute ruleId
	 */
	public void setRuleId(java.lang.Long ruleId) {
		this.ruleId = ruleId;
	}

	/**
	 * Gets value for attribute ruleDescription
	 */
	public java.lang.String getRuleDescription() {
		return this.ruleDescription;
	}

	/**
	 * Sets value for attribute ruleDescription
	 */
	public void setRuleDescription(java.lang.String ruleDescription) {
		this.ruleDescription = ruleDescription;
	}

	/**
	 * Gets value for attribute sodRisk
	 */
	public com.soffid.iam.rc.api.SoDRisk getSodRisk() {
		return this.sodRisk;
	}

	/**
	 * Sets value for attribute sodRisk
	 */
	public void setSodRisk(com.soffid.iam.rc.api.SoDRisk sodRisk) {
		this.sodRisk = sodRisk;
	}

	/**
	 * Gets value for attribute sodRules
	 */
	public java.util.Collection<com.soffid.iam.rc.api.SoDRule> getSodRules() {
		return this.sodRules;
	}

	/**
	 * Sets value for attribute sodRules
	 */
	public void setSodRules(java.util.Collection<com.soffid.iam.rc.api.SoDRule> sodRules) {
		this.sodRules = sodRules;
	}

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
	 * Gets value for attribute holderGroup
	 */
	public java.lang.String getHolderGroup() {
		return this.holderGroup;
	}

	/**
	 * Sets value for attribute holderGroup
	 */
	public void setHolderGroup(java.lang.String holderGroup) {
		this.holderGroup = holderGroup;
	}

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
	 * Gets value for attribute parentGrant
	 */
	public java.lang.Long getParentGrant() {
		return this.parentGrant;
	}

	/**
	 * Sets value for attribute parentGrant
	 */
	public void setParentGrant(java.lang.Long parentGrant) {
		this.parentGrant = parentGrant;
	}

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
	 * Gets value for attribute ownerAccount
	 */
	public java.lang.String getOwnerAccount() {
		return this.ownerAccount;
	}

	/**
	 * Sets value for attribute ownerAccount
	 */
	public void setOwnerAccount(java.lang.String ownerAccount) {
		this.ownerAccount = ownerAccount;
	}

	/**
	 * Gets value for attribute delegateAccount
	 */
	public java.lang.String getDelegateAccount() {
		return this.delegateAccount;
	}

	/**
	 * Sets value for attribute delegateAccount
	 */
	public void setDelegateAccount(java.lang.String delegateAccount) {
		this.delegateAccount = delegateAccount;
	}

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
	 * Gets value for attribute attributes
	 */
	public java.util.Map<java.lang.String,java.lang.Object> getAttributes() {
		return this.attributes;
	}

	/**
	 * Sets value for attribute attributes
	 */
	public void setAttributes(java.util.Map<java.lang.String,java.lang.Object> attributes) {
		this.attributes = attributes;
	}

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
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[id: ");
		b.append (this.id);
		b.append (", accountId: ");
		b.append (this.accountId);
		b.append (", accountName: ");
		b.append (this.accountName);
		b.append (", accountSystem: ");
		b.append (this.accountSystem);
		b.append (", roleName: ");
		b.append (this.roleName);
		b.append (", roleId: ");
		b.append (this.roleId);
		b.append (", roleCategory: ");
		b.append (this.roleCategory);
		b.append (", informationSystemName: ");
		b.append (this.informationSystemName);
		b.append (", roleDescription: ");
		b.append (this.roleDescription);
		b.append (", userFullName: ");
		b.append (this.userFullName);
		b.append (", groupDescription: ");
		b.append (this.groupDescription);
		b.append (", domainValue: ");
		b.append (this.domainValue);
		b.append (", system: ");
		b.append (this.system);
		b.append (", userGroupCode: ");
		b.append (this.userGroupCode);
		b.append (", bpmEnabled: ");
		b.append (this.bpmEnabled);
		b.append (", userName: ");
		b.append (this.userName);
		b.append (", ruleId: ");
		b.append (this.ruleId);
		b.append (", ruleDescription: ");
		b.append (this.ruleDescription);
		b.append (", sodRisk: ");
		b.append (this.sodRisk);
		b.append (", sodRules: ");
		b.append (this.sodRules);
		b.append (", startDate: ");
		b.append (this.startDate);
		b.append (", endDate: ");
		b.append (this.endDate);
		b.append (", enabled: ");
		b.append (this.enabled);
		b.append (", approvalPending: ");
		b.append (this.approvalPending);
		b.append (", removalPending: ");
		b.append (this.removalPending);
		b.append (", holderGroup: ");
		b.append (this.holderGroup);
		b.append (", approvalProcess: ");
		b.append (this.approvalProcess);
		b.append (", certificationDate: ");
		b.append (this.certificationDate);
		b.append (", parentGrant: ");
		b.append (this.parentGrant);
		b.append (", delegationStatus: ");
		b.append (this.delegationStatus);
		b.append (", ownerAccount: ");
		b.append (this.ownerAccount);
		b.append (", delegateAccount: ");
		b.append (this.delegateAccount);
		b.append (", delegateSince: ");
		b.append (this.delegateSince);
		b.append (", delegateUntil: ");
		b.append (this.delegateUntil);
		b.append (", externalId: ");
		b.append (this.externalId);
		b.append (", attributes: ");
		b.append (this.attributes);
		b.append (", createdOn: ");
		b.append (this.createdOn);
		b.append (", createdBy: ");
		b.append (this.createdBy);
		b.append (", updatedOn: ");
		b.append (this.updatedOn);
		b.append (", updatedBy: ");
		b.append (this.updatedBy);
		b.append (", deletedOn: ");
		b.append (this.deletedOn);
		b.append (", deletedBy: ");
		b.append (this.deletedBy);
		b.append ("]");
		return b.toString();
	}

}
