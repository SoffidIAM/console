//
// (c) 2014 Soffid
//
//

package com.soffid.iam.pam.model;

/**
 *  Entity HostAdminEntity
 */

public abstract class HostAdminEntity {

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
	 * Attribute expirationDate
	 */
	private java.util.Date expirationDate;
	/**
	 * Gets value for attribute expirationDate
	 */
	public java.util.Date getExpirationDate() {
		return this.expirationDate;
	}
	/**
	 * Sets value for attribute expirationDate
	 */
	public void setExpirationDate(java.util.Date expirationDate) {
		this.expirationDate = expirationDate;
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
	 * Attribute host
	 */
	private com.soffid.iam.am.model.HostEntity host;
	/**
	 * Gets value for attribute host
	 */
	public com.soffid.iam.am.model.HostEntity getHost() {
		return this.host;
	}
	/**
	 * Sets value for attribute host
	 */
	public void setHost(com.soffid.iam.am.model.HostEntity host) {
		this.host = host;
	}
	/**
	 * Attribute processWFID
	 */
	private java.lang.Long processWFID;
	/**
	 * Gets value for attribute processWFID
	 */
	public java.lang.Long getProcessWFID() {
		return this.processWFID;
	}
	/**
	 * Sets value for attribute processWFID
	 */
	public void setProcessWFID(java.lang.Long processWFID) {
		this.processWFID = processWFID;
	}
	/**
	 * Attribute requestDate
	 */
	private java.util.Date requestDate;
	/**
	 * Gets value for attribute requestDate
	 */
	public java.util.Date getRequestDate() {
		return this.requestDate;
	}
	/**
	 * Sets value for attribute requestDate
	 */
	public void setRequestDate(java.util.Date requestDate) {
		this.requestDate = requestDate;
	}
	/**
	 * Returns <code>true</code> if the argument is an HostAdminEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof HostAdminEntity))
		{
			return false;
		}
		final HostAdminEntity that = (HostAdminEntity)object;
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
