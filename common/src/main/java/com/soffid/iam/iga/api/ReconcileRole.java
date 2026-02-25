//
// (C) 2020 Soffid
//
//

package com.soffid.iam.iga.api;
/**
 * ValueObject ReconcileRole
 **/
public class ReconcileRole

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
	 * Attribute roleName

	 */
	private java.lang.String roleName;

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
	private com.soffid.iam.iga.api.ProposedAction proposedAction;

	/**
	 * Attribute dispatcher

	 */
	private java.lang.String dispatcher;

	/**
	 * Attribute appName

	 */
	private java.lang.String appName;

	public ReconcileRole()
	{
	}

	public ReconcileRole(java.lang.Long id, java.lang.String roleName, java.lang.String description, java.lang.Long processId, com.soffid.iam.iga.api.ProposedAction proposedAction, java.lang.String dispatcher, java.lang.String appName)
	{
		super();
		this.id = id;
		this.roleName = roleName;
		this.description = description;
		this.processId = processId;
		this.proposedAction = proposedAction;
		this.dispatcher = dispatcher;
		this.appName = appName;
	}

	public ReconcileRole(java.lang.String roleName, java.lang.String description, java.lang.Long processId, com.soffid.iam.iga.api.ProposedAction proposedAction, java.lang.String dispatcher)
	{
		super();
		this.roleName = roleName;
		this.description = description;
		this.processId = processId;
		this.proposedAction = proposedAction;
		this.dispatcher = dispatcher;
	}

	public ReconcileRole(ReconcileRole otherBean)
	{
		this(otherBean.id, otherBean.roleName, otherBean.description, otherBean.processId, otherBean.proposedAction, otherBean.dispatcher, otherBean.appName);
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
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[id: ");
		b.append (this.id);
		b.append (", roleName: ");
		b.append (this.roleName);
		b.append (", description: ");
		b.append (this.description);
		b.append (", processId: ");
		b.append (this.processId);
		b.append (", proposedAction: ");
		b.append (this.proposedAction);
		b.append (", dispatcher: ");
		b.append (this.dispatcher);
		b.append (", appName: ");
		b.append (this.appName);
		b.append ("]");
		return b.toString();
	}

}
