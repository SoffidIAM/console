//
// (c) 2014 Soffid
//
//

package com.soffid.iam.pam.model;

/**
 *  Entity HostServiceEntity
 */

public abstract class HostServiceEntity {

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
	 * Attribute service
	 */
	private java.lang.String service;
	/**
	 * Gets value for attribute service
	 */
	public java.lang.String getService() {
		return this.service;
	}
	/**
	 * Sets value for attribute service
	 */
	public void setService(java.lang.String service) {
		this.service = service;
	}
	/**
	 * Attribute command
	 * An operating system command to configure the user password in the subscribed applications
	 */
	private java.lang.String command;
	/**
	 * Gets value for attribute command
	 */
	public java.lang.String getCommand() {
		return this.command;
	}
	/**
	 * Sets value for attribute command
	 */
	public void setCommand(java.lang.String command) {
		this.command = command;
	}
	/**
	 * Attribute manual
	 * Service not discovered by the network discovery process
	 */
	private boolean manual;
	/**
	 * Gets value for attribute manual
	 */
	public boolean isManual() {
		return this.manual;
	}
	/**
	 * Sets value for attribute manual
	 */
	public void setManual(boolean manual) {
		this.manual = manual;
	}
	/**
	 * Attribute account
	 */
	private com.soffid.iam.base.model.AccountEntity account;
	/**
	 * Gets value for attribute account
	 */
	public com.soffid.iam.base.model.AccountEntity getAccount() {
		return this.account;
	}
	/**
	 * Sets value for attribute account
	 */
	public void setAccount(com.soffid.iam.base.model.AccountEntity account) {
		this.account = account;
	}
	/**
	 * Returns <code>true</code> if the argument is an HostServiceEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof HostServiceEntity))
		{
			return false;
		}
		final HostServiceEntity that = (HostServiceEntity)object;
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
