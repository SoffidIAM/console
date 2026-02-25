//
// (c) 2014 Soffid
//
//

package com.soffid.iam.iga.model;

/**
 *  Entity ReconcileAssignmentEntity
 */

public abstract class ReconcileAssignmentEntity {

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
	 * Attribute assignmentName
	 */
	private java.lang.String assignmentName;
	/**
	 * Gets value for attribute assignmentName
	 */
	public java.lang.String getAssignmentName() {
		return this.assignmentName;
	}
	/**
	 * Sets value for attribute assignmentName
	 */
	public void setAssignmentName(java.lang.String assignmentName) {
		this.assignmentName = assignmentName;
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
	 * Attribute roleName
	 */
	private java.lang.String roleName;
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
	 * Attribute proposedAction
	 */
	private com.soffid.iam.iga.api.ProposedAction proposedAction;
	/**
	 * Gets value for attribute proposedAction
	 */
	public com.soffid.iam.iga.api.ProposedAction getProposedAction() {
		return this.proposedAction;
	}
	/**
	 * Sets value for attribute proposedAction
	 */
	public void setProposedAction(com.soffid.iam.iga.api.ProposedAction proposedAction) {
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
	 * Attribute domainValue
	 */
	private java.lang.String domainValue;
	/**
	 * Gets value for attribute domainValue
	 */
	public java.lang.String getDomainValue() {
		return this.domainValue;
	}
	/**
	 * Sets value for attribute domainValue
	 */
	public void setDomainValue(java.lang.String domainValue) {
		this.domainValue = domainValue;
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
	 * Returns <code>true</code> if the argument is an ReconcileAssignmentEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof ReconcileAssignmentEntity))
		{
			return false;
		}
		final ReconcileAssignmentEntity that = (ReconcileAssignmentEntity)object;
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
