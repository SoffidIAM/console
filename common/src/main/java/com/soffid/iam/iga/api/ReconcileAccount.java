//
// (C) 2020 Soffid
//
//

package com.soffid.iam.iga.api;
/**
 * ValueObject ReconcileAccount
 **/
public class ReconcileAccount

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
	 * Attribute accountName

	 */
	private java.lang.String accountName;

	/**
	 * Attribute description

	 */
	private java.lang.String description;

	/**
	 * Attribute processId

	 */
	private java.lang.Long processId;

	/**
	 * Attribute proposedAction

	 */
	private com.soffid.iam.iga.api.AccountProposedAction proposedAction;

	/**
	 * Attribute dispatcher

	 */
	private java.lang.String dispatcher;

	/**
	 * Attribute primaryGroup

	 */
	private java.lang.String primaryGroup;

	/**
	 * Attribute userCode

	 */
	private java.lang.String userCode;

	/**
	 * Attribute userType

	 */
	private java.lang.String userType;

	/**
	 * Attribute userFullName

	 */
	private java.lang.String userFullName;

	/**
	 * Attribute accountType

	 */
	private com.soffid.iam.base.api.AccountType accountType;

	/**
	 * Attribute active

	 */
	private boolean active = true;

	/**
	 * Attribute newAccount

	 */
	private java.lang.Boolean newAccount;

	/**
	 * Attribute deletedAccount

	 */
	private java.lang.Boolean deletedAccount;

	/**
	 * Attribute attributes

	 */
	private java.util.Map<java.lang.String,java.lang.Object> attributes;

	public ReconcileAccount()
	{
	}

	public ReconcileAccount(java.lang.Long id, java.lang.String accountName, java.lang.String description, java.lang.Long processId, com.soffid.iam.iga.api.AccountProposedAction proposedAction, java.lang.String dispatcher, java.lang.String primaryGroup, java.lang.String userCode, java.lang.String userType, java.lang.String userFullName, com.soffid.iam.base.api.AccountType accountType, boolean active, java.lang.Boolean newAccount, java.lang.Boolean deletedAccount, java.util.Map<java.lang.String,java.lang.Object> attributes)
	{
		super();
		this.id = id;
		this.accountName = accountName;
		this.description = description;
		this.processId = processId;
		this.proposedAction = proposedAction;
		this.dispatcher = dispatcher;
		this.primaryGroup = primaryGroup;
		this.userCode = userCode;
		this.userType = userType;
		this.userFullName = userFullName;
		this.accountType = accountType;
		this.active = active;
		this.newAccount = newAccount;
		this.deletedAccount = deletedAccount;
		this.attributes = attributes;
	}

	public ReconcileAccount(java.lang.String accountName, java.lang.String description, java.lang.Long processId, com.soffid.iam.iga.api.AccountProposedAction proposedAction, java.lang.String dispatcher, boolean active, java.lang.Boolean newAccount, java.lang.Boolean deletedAccount, java.util.Map<java.lang.String,java.lang.Object> attributes)
	{
		super();
		this.accountName = accountName;
		this.description = description;
		this.processId = processId;
		this.proposedAction = proposedAction;
		this.dispatcher = dispatcher;
		this.active = active;
		this.newAccount = newAccount;
		this.deletedAccount = deletedAccount;
		this.attributes = attributes;
	}

	public ReconcileAccount(ReconcileAccount otherBean)
	{
		this(otherBean.id, otherBean.accountName, otherBean.description, otherBean.processId, otherBean.proposedAction, otherBean.dispatcher, otherBean.primaryGroup, otherBean.userCode, otherBean.userType, otherBean.userFullName, otherBean.accountType, otherBean.active, otherBean.newAccount, otherBean.deletedAccount, otherBean.attributes);
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
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[id: ");
		b.append (this.id);
		b.append (", accountName: ");
		b.append (this.accountName);
		b.append (", description: ");
		b.append (this.description);
		b.append (", processId: ");
		b.append (this.processId);
		b.append (", proposedAction: ");
		b.append (this.proposedAction);
		b.append (", dispatcher: ");
		b.append (this.dispatcher);
		b.append (", primaryGroup: ");
		b.append (this.primaryGroup);
		b.append (", userCode: ");
		b.append (this.userCode);
		b.append (", userType: ");
		b.append (this.userType);
		b.append (", userFullName: ");
		b.append (this.userFullName);
		b.append (", accountType: ");
		b.append (this.accountType);
		b.append (", active: ");
		b.append (this.active);
		b.append (", newAccount: ");
		b.append (this.newAccount);
		b.append (", deletedAccount: ");
		b.append (this.deletedAccount);
		b.append (", attributes: ");
		b.append (this.attributes);
		b.append ("]");
		return b.toString();
	}

}
