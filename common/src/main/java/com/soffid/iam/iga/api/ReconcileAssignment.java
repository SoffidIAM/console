//
// (C) 2020 Soffid
//
//

package com.soffid.iam.iga.api;
/**
 * ValueObject ReconcileAssignment
 **/
public class ReconcileAssignment

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
	 * Attribute assignmentName

	 */
	private java.lang.String assignmentName;

	/**
	 * Attribute processId

	 */
	private java.lang.Long processId;

	/**
	 * Attribute accountName

	 */
	private java.lang.String accountName;

	/**
	 * Attribute roleName

	 */
	private java.lang.String roleName;

	/**
	 * Attribute proposedAction

	 */
	private com.soffid.iam.iga.api.ProposedAction proposedAction;

	/**
	 * Attribute dispatcher

	 */
	private java.lang.String dispatcher;

	/**
	 * Attribute domainValue

	 */
	private java.lang.String domainValue;

	public ReconcileAssignment()
	{
	}

	public ReconcileAssignment(java.lang.Long id, java.lang.String assignmentName, java.lang.Long processId, java.lang.String accountName, java.lang.String roleName, com.soffid.iam.iga.api.ProposedAction proposedAction, java.lang.String dispatcher, java.lang.String domainValue)
	{
		super();
		this.id = id;
		this.assignmentName = assignmentName;
		this.processId = processId;
		this.accountName = accountName;
		this.roleName = roleName;
		this.proposedAction = proposedAction;
		this.dispatcher = dispatcher;
		this.domainValue = domainValue;
	}

	public ReconcileAssignment(java.lang.String assignmentName, java.lang.Long processId, java.lang.String accountName, java.lang.String roleName, com.soffid.iam.iga.api.ProposedAction proposedAction, java.lang.String dispatcher)
	{
		super();
		this.assignmentName = assignmentName;
		this.processId = processId;
		this.accountName = accountName;
		this.roleName = roleName;
		this.proposedAction = proposedAction;
		this.dispatcher = dispatcher;
	}

	public ReconcileAssignment(ReconcileAssignment otherBean)
	{
		this(otherBean.id, otherBean.assignmentName, otherBean.processId, otherBean.accountName, otherBean.roleName, otherBean.proposedAction, otherBean.dispatcher, otherBean.domainValue);
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
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[id: ");
		b.append (this.id);
		b.append (", assignmentName: ");
		b.append (this.assignmentName);
		b.append (", processId: ");
		b.append (this.processId);
		b.append (", accountName: ");
		b.append (this.accountName);
		b.append (", roleName: ");
		b.append (this.roleName);
		b.append (", proposedAction: ");
		b.append (this.proposedAction);
		b.append (", dispatcher: ");
		b.append (this.dispatcher);
		b.append (", domainValue: ");
		b.append (this.domainValue);
		b.append ("]");
		return b.toString();
	}

}
