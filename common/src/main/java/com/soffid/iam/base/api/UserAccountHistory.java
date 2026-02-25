//
// (C) 2020 Soffid
//
//

package com.soffid.iam.base.api;
/**
 * ValueObject UserAccountHistory
 **/
public class UserAccountHistory

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
	 * Attribute user

	 */
	private java.lang.String user;

	/**
	 * Attribute account

	 */
	private java.lang.String account;

	/**
	 * Attribute system

	 */
	private java.lang.String system;

	/**
	 * Attribute untilDate

	 */
	private java.util.Date untilDate;

	/**
	 * Attribute workflowId

	 */
	private java.lang.Long workflowId;

	/**
	 * Attribute approved

	 */
	private java.lang.Boolean approved;

	/**
	 * Attribute entryPoint

	 */
	private java.lang.String entryPoint;

	public UserAccountHistory()
	{
	}

	public UserAccountHistory(java.lang.Long id, java.lang.String user, java.lang.String account, java.lang.String system, java.util.Date untilDate, java.lang.Long workflowId, java.lang.Boolean approved, java.lang.String entryPoint)
	{
		super();
		this.id = id;
		this.user = user;
		this.account = account;
		this.system = system;
		this.untilDate = untilDate;
		this.workflowId = workflowId;
		this.approved = approved;
		this.entryPoint = entryPoint;
	}

	public UserAccountHistory(java.lang.Long id, java.lang.String user, java.lang.String account, java.lang.String system)
	{
		super();
		this.id = id;
		this.user = user;
		this.account = account;
		this.system = system;
	}

	public UserAccountHistory(UserAccountHistory otherBean)
	{
		this(otherBean.id, otherBean.user, otherBean.account, otherBean.system, otherBean.untilDate, otherBean.workflowId, otherBean.approved, otherBean.entryPoint);
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
	 * Gets value for attribute user
	 */
	public java.lang.String getUser() {
		return this.user;
	}

	/**
	 * Sets value for attribute user
	 */
	public void setUser(java.lang.String user) {
		this.user = user;
	}

	/**
	 * Gets value for attribute account
	 */
	public java.lang.String getAccount() {
		return this.account;
	}

	/**
	 * Sets value for attribute account
	 */
	public void setAccount(java.lang.String account) {
		this.account = account;
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
	 * Gets value for attribute untilDate
	 */
	public java.util.Date getUntilDate() {
		return this.untilDate;
	}

	/**
	 * Sets value for attribute untilDate
	 */
	public void setUntilDate(java.util.Date untilDate) {
		this.untilDate = untilDate;
	}

	/**
	 * Gets value for attribute workflowId
	 */
	public java.lang.Long getWorkflowId() {
		return this.workflowId;
	}

	/**
	 * Sets value for attribute workflowId
	 */
	public void setWorkflowId(java.lang.Long workflowId) {
		this.workflowId = workflowId;
	}

	/**
	 * Gets value for attribute approved
	 */
	public java.lang.Boolean getApproved() {
		return this.approved;
	}

	/**
	 * Sets value for attribute approved
	 */
	public void setApproved(java.lang.Boolean approved) {
		this.approved = approved;
	}

	/**
	 * Gets value for attribute entryPoint
	 */
	public java.lang.String getEntryPoint() {
		return this.entryPoint;
	}

	/**
	 * Sets value for attribute entryPoint
	 */
	public void setEntryPoint(java.lang.String entryPoint) {
		this.entryPoint = entryPoint;
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
		b.append (", user: ");
		b.append (this.user);
		b.append (", account: ");
		b.append (this.account);
		b.append (", system: ");
		b.append (this.system);
		b.append (", untilDate: ");
		b.append (this.untilDate);
		b.append (", workflowId: ");
		b.append (this.workflowId);
		b.append (", approved: ");
		b.append (this.approved);
		b.append (", entryPoint: ");
		b.append (this.entryPoint);
		b.append ("]");
		return b.toString();
	}

}
