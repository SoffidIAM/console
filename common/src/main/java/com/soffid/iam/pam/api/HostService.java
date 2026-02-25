//
// (C) 2020 Soffid
//
//

package com.soffid.iam.pam.api;
/**
 * ValueObject HostService
 **/
public class HostService

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
	 * Attribute hostId

	 */
	private java.lang.Long hostId;

	/**
	 * Attribute hostName

	 */
	private java.lang.String hostName;

	/**
	 * Attribute service

	 */
	private java.lang.String service;

	/**
	 * Attribute command
	 * An operating system command to configure the user password in the subscribed applications

	 */
	private java.lang.String command;

	/**
	 * Attribute accountId

	 */
	private java.lang.Long accountId;

	/**
	 * Attribute accountName

	 */
	private java.lang.String accountName;

	/**
	 * Attribute accountSystem

	 */
	private java.lang.String accountSystem;

	/**
	 * Attribute manual

	 */
	private boolean manual;

	public HostService()
	{
	}

	public HostService(java.lang.Long id, java.lang.Long hostId, java.lang.String hostName, java.lang.String service, java.lang.String command, java.lang.Long accountId, java.lang.String accountName, java.lang.String accountSystem, boolean manual)
	{
		super();
		this.id = id;
		this.hostId = hostId;
		this.hostName = hostName;
		this.service = service;
		this.command = command;
		this.accountId = accountId;
		this.accountName = accountName;
		this.accountSystem = accountSystem;
		this.manual = manual;
	}

	public HostService(java.lang.Long hostId, java.lang.String hostName, java.lang.String service, java.lang.Long accountId, java.lang.String accountName, java.lang.String accountSystem, boolean manual)
	{
		super();
		this.hostId = hostId;
		this.hostName = hostName;
		this.service = service;
		this.accountId = accountId;
		this.accountName = accountName;
		this.accountSystem = accountSystem;
		this.manual = manual;
	}

	public HostService(HostService otherBean)
	{
		this(otherBean.id, otherBean.hostId, otherBean.hostName, otherBean.service, otherBean.command, otherBean.accountId, otherBean.accountName, otherBean.accountSystem, otherBean.manual);
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
	 * Gets value for attribute hostId
	 */
	public java.lang.Long getHostId() {
		return this.hostId;
	}

	/**
	 * Sets value for attribute hostId
	 */
	public void setHostId(java.lang.Long hostId) {
		this.hostId = hostId;
	}

	/**
	 * Gets value for attribute hostName
	 */
	public java.lang.String getHostName() {
		return this.hostName;
	}

	/**
	 * Sets value for attribute hostName
	 */
	public void setHostName(java.lang.String hostName) {
		this.hostName = hostName;
	}

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
	 * Gets value for attribute accountId
	 */
	public java.lang.Long getAccountId() {
		return this.accountId;
	}

	/**
	 * Sets value for attribute accountId
	 */
	public void setAccountId(java.lang.Long accountId) {
		this.accountId = accountId;
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
	 * Gets value for attribute accountSystem
	 */
	public java.lang.String getAccountSystem() {
		return this.accountSystem;
	}

	/**
	 * Sets value for attribute accountSystem
	 */
	public void setAccountSystem(java.lang.String accountSystem) {
		this.accountSystem = accountSystem;
	}

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
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[id: ");
		b.append (this.id);
		b.append (", hostId: ");
		b.append (this.hostId);
		b.append (", hostName: ");
		b.append (this.hostName);
		b.append (", service: ");
		b.append (this.service);
		b.append (", command: ");
		b.append (this.command);
		b.append (", accountId: ");
		b.append (this.accountId);
		b.append (", accountName: ");
		b.append (this.accountName);
		b.append (", accountSystem: ");
		b.append (this.accountSystem);
		b.append (", manual: ");
		b.append (this.manual);
		b.append ("]");
		return b.toString();
	}

}
