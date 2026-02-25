//
// (c) 2014 Soffid
//
//

package com.soffid.iam.iga.model;

/**
 *  Entity ReconcileRoleEntity
 */

public abstract class ReconcileRoleEntity {

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
	 * Attribute appName
	 */
	private java.lang.String appName;
	/**
	 * Gets value for attribute appName
	 */
	public java.lang.String getAppName() {
		return this.appName;
	}
	/**
	 * Sets value for attribute appName
	 */
	public void setAppName(java.lang.String appName) {
		this.appName = appName;
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
	 * Returns <code>true</code> if the argument is an ReconcileRoleEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof ReconcileRoleEntity))
		{
			return false;
		}
		final ReconcileRoleEntity that = (ReconcileRoleEntity)object;
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
