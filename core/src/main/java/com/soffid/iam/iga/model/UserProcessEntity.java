//
// (c) 2014 Soffid
//
//

package com.soffid.iam.iga.model;

/**
 *  Entity UserProcessEntity
 */

public abstract class UserProcessEntity {

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
	 * Attribute userName
	 */
	private java.lang.String userName;
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
	 * Attribute finished
	 */
	private java.lang.Boolean finished = false;
	/**
	 * Gets value for attribute finished
	 */
	public java.lang.Boolean getFinished() {
		return this.finished;
	}
	/**
	 * Sets value for attribute finished
	 */
	public void setFinished(java.lang.Boolean finished) {
		this.finished = finished;
	}
	/**
	 * Attribute nationalId
	 */
	private java.lang.String nationalId;
	/**
	 * Gets value for attribute nationalId
	 */
	public java.lang.String getNationalId() {
		return this.nationalId;
	}
	/**
	 * Sets value for attribute nationalId
	 */
	public void setNationalId(java.lang.String nationalId) {
		this.nationalId = nationalId;
	}
	/**
	 * Returns <code>true</code> if the argument is an UserProcessEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof UserProcessEntity))
		{
			return false;
		}
		final UserProcessEntity that = (UserProcessEntity)object;
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
