//
// (c) 2014 Soffid
//
//

package com.soffid.iam.iga.model;

/**
 *  Entity AuthoritativeChangeEntity
 * Contains pending authoritative changes
 */

public abstract class AuthoritativeChangeEntity {

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
	 * Attribute user
	 */
	private com.soffid.iam.base.model.UserEntity user;
	/**
	 * Gets value for attribute user
	 */
	public com.soffid.iam.base.model.UserEntity getUser() {
		return this.user;
	}
	/**
	 * Sets value for attribute user
	 */
	public void setUser(com.soffid.iam.base.model.UserEntity user) {
		this.user = user;
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
	 * Attribute employeeId
	 */
	private java.lang.String employeeId;
	/**
	 * Gets value for attribute employeeId
	 */
	public java.lang.String getEmployeeId() {
		return this.employeeId;
	}
	/**
	 * Sets value for attribute employeeId
	 */
	public void setEmployeeId(java.lang.String employeeId) {
		this.employeeId = employeeId;
	}
	/**
	 * Attribute changeId
	 */
	private java.lang.String changeId;
	/**
	 * Gets value for attribute changeId
	 */
	public java.lang.String getChangeId() {
		return this.changeId;
	}
	/**
	 * Sets value for attribute changeId
	 */
	public void setChangeId(java.lang.String changeId) {
		this.changeId = changeId;
	}
	/**
	 * Attribute changeDate
	 */
	private java.util.Date changeDate;
	/**
	 * Gets value for attribute changeDate
	 */
	public java.util.Date getChangeDate() {
		return this.changeDate;
	}
	/**
	 * Sets value for attribute changeDate
	 */
	public void setChangeDate(java.util.Date changeDate) {
		this.changeDate = changeDate;
	}
	/**
	 * Attribute dispatcher
	 */
	private com.soffid.iam.iga.model.SystemEntity dispatcher;
	/**
	 * Gets value for attribute dispatcher
	 */
	public com.soffid.iam.iga.model.SystemEntity getDispatcher() {
		return this.dispatcher;
	}
	/**
	 * Sets value for attribute dispatcher
	 */
	public void setDispatcher(com.soffid.iam.iga.model.SystemEntity dispatcher) {
		this.dispatcher = dispatcher;
	}
	/**
	 * Returns <code>true</code> if the argument is an AuthoritativeChangeEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof AuthoritativeChangeEntity))
		{
			return false;
		}
		final AuthoritativeChangeEntity that = (AuthoritativeChangeEntity)object;
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
