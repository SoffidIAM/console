//
// (C) 2020 Soffid
//
//

package com.soffid.iam.am.api;
/**
 * ValueObject Browser
 **/
public class Browser

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
	 * Attribute serialNumber

	 */
	private java.lang.String serialNumber;

	/**
	 * Attribute deviceType

	 */
	private java.lang.String deviceType;

	/**
	 * Attribute operatingSystem

	 */
	private java.lang.String operatingSystem;

	/**
	 * Attribute browser

	 */
	private java.lang.String browser;

	/**
	 * Attribute cpu

	 */
	private java.lang.String cpu;

	/**
	 * Attribute ip

	 */
	private java.lang.String ip;

	/**
	 * Attribute lastUser

	 */
	private java.lang.String lastUser;

	/**
	 * Attribute hostName

	 */
	private java.lang.String hostName;

	/**
	 * Attribute lastSeen

	 */
	private java.util.Date lastSeen;

	/**
	 * Attribute created

	 */
	private java.util.Date created;

	/**
	 * Attribute deleted

	 */
	private java.lang.Boolean deleted;

	/**
	 * Attribute locked

	 */
	private java.lang.Boolean locked;

	/**
	 * Attribute createdOn


	 */
	private java.util.Date createdOn;

	/**
	 * Attribute createdBy


	 */
	private java.lang.String createdBy;

	/**
	 * Attribute updatedOn


	 */
	private java.util.Date updatedOn;

	/**
	 * Attribute updatedBy


	 */
	private java.lang.String updatedBy;

	/**
	 * Attribute deletedOn


	 */
	private java.util.Date deletedOn;

	/**
	 * Attribute deletedBy


	 */
	private java.lang.String deletedBy;

	public Browser()
	{
	}

	public Browser(java.lang.Long id, java.lang.String serialNumber, java.lang.String deviceType, java.lang.String operatingSystem, java.lang.String browser, java.lang.String cpu, java.lang.String ip, java.lang.String lastUser, java.lang.String hostName, java.util.Date lastSeen, java.util.Date created, java.lang.Boolean deleted, java.lang.Boolean locked, java.util.Date createdOn, java.lang.String createdBy, java.util.Date updatedOn, java.lang.String updatedBy, java.util.Date deletedOn, java.lang.String deletedBy)
	{
		super();
		this.id = id;
		this.serialNumber = serialNumber;
		this.deviceType = deviceType;
		this.operatingSystem = operatingSystem;
		this.browser = browser;
		this.cpu = cpu;
		this.ip = ip;
		this.lastUser = lastUser;
		this.hostName = hostName;
		this.lastSeen = lastSeen;
		this.created = created;
		this.deleted = deleted;
		this.locked = locked;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
		this.updatedOn = updatedOn;
		this.updatedBy = updatedBy;
		this.deletedOn = deletedOn;
		this.deletedBy = deletedBy;
	}

	public Browser(java.lang.Long id, java.lang.String ip)
	{
		super();
		this.id = id;
		this.ip = ip;
	}

	public Browser(Browser otherBean)
	{
		this(otherBean.id, otherBean.serialNumber, otherBean.deviceType, otherBean.operatingSystem, otherBean.browser, otherBean.cpu, otherBean.ip, otherBean.lastUser, otherBean.hostName, otherBean.lastSeen, otherBean.created, otherBean.deleted, otherBean.locked, otherBean.createdOn, otherBean.createdBy, otherBean.updatedOn, otherBean.updatedBy, otherBean.deletedOn, otherBean.deletedBy);
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
	 * Gets value for attribute serialNumber
	 */
	public java.lang.String getSerialNumber() {
		return this.serialNumber;
	}

	/**
	 * Sets value for attribute serialNumber
	 */
	public void setSerialNumber(java.lang.String serialNumber) {
		this.serialNumber = serialNumber;
	}

	/**
	 * Gets value for attribute deviceType
	 */
	public java.lang.String getDeviceType() {
		return this.deviceType;
	}

	/**
	 * Sets value for attribute deviceType
	 */
	public void setDeviceType(java.lang.String deviceType) {
		this.deviceType = deviceType;
	}

	/**
	 * Gets value for attribute operatingSystem
	 */
	public java.lang.String getOperatingSystem() {
		return this.operatingSystem;
	}

	/**
	 * Sets value for attribute operatingSystem
	 */
	public void setOperatingSystem(java.lang.String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}

	/**
	 * Gets value for attribute browser
	 */
	public java.lang.String getBrowser() {
		return this.browser;
	}

	/**
	 * Sets value for attribute browser
	 */
	public void setBrowser(java.lang.String browser) {
		this.browser = browser;
	}

	/**
	 * Gets value for attribute cpu
	 */
	public java.lang.String getCpu() {
		return this.cpu;
	}

	/**
	 * Sets value for attribute cpu
	 */
	public void setCpu(java.lang.String cpu) {
		this.cpu = cpu;
	}

	/**
	 * Gets value for attribute ip
	 */
	public java.lang.String getIp() {
		return this.ip;
	}

	/**
	 * Sets value for attribute ip
	 */
	public void setIp(java.lang.String ip) {
		this.ip = ip;
	}

	/**
	 * Gets value for attribute lastUser
	 */
	public java.lang.String getLastUser() {
		return this.lastUser;
	}

	/**
	 * Sets value for attribute lastUser
	 */
	public void setLastUser(java.lang.String lastUser) {
		this.lastUser = lastUser;
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
	 * Gets value for attribute lastSeen
	 */
	public java.util.Date getLastSeen() {
		return this.lastSeen;
	}

	/**
	 * Sets value for attribute lastSeen
	 */
	public void setLastSeen(java.util.Date lastSeen) {
		this.lastSeen = lastSeen;
	}

	/**
	 * Gets value for attribute created
	 */
	public java.util.Date getCreated() {
		return this.created;
	}

	/**
	 * Sets value for attribute created
	 */
	public void setCreated(java.util.Date created) {
		this.created = created;
	}

	/**
	 * Gets value for attribute deleted
	 */
	public java.lang.Boolean getDeleted() {
		return this.deleted;
	}

	/**
	 * Sets value for attribute deleted
	 */
	public void setDeleted(java.lang.Boolean deleted) {
		this.deleted = deleted;
	}

	/**
	 * Gets value for attribute locked
	 */
	public java.lang.Boolean getLocked() {
		return this.locked;
	}

	/**
	 * Sets value for attribute locked
	 */
	public void setLocked(java.lang.Boolean locked) {
		this.locked = locked;
	}

	/**
	 * Gets value for attribute createdOn
	 */
	public java.util.Date getCreatedOn() {
		return this.createdOn;
	}

	/**
	 * Sets value for attribute createdOn
	 */
	public void setCreatedOn(java.util.Date createdOn) {
		this.createdOn = createdOn;
	}

	/**
	 * Gets value for attribute createdBy
	 */
	public java.lang.String getCreatedBy() {
		return this.createdBy;
	}

	/**
	 * Sets value for attribute createdBy
	 */
	public void setCreatedBy(java.lang.String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * Gets value for attribute updatedOn
	 */
	public java.util.Date getUpdatedOn() {
		return this.updatedOn;
	}

	/**
	 * Sets value for attribute updatedOn
	 */
	public void setUpdatedOn(java.util.Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	/**
	 * Gets value for attribute updatedBy
	 */
	public java.lang.String getUpdatedBy() {
		return this.updatedBy;
	}

	/**
	 * Sets value for attribute updatedBy
	 */
	public void setUpdatedBy(java.lang.String updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * Gets value for attribute deletedOn
	 */
	public java.util.Date getDeletedOn() {
		return this.deletedOn;
	}

	/**
	 * Sets value for attribute deletedOn
	 */
	public void setDeletedOn(java.util.Date deletedOn) {
		this.deletedOn = deletedOn;
	}

	/**
	 * Gets value for attribute deletedBy
	 */
	public java.lang.String getDeletedBy() {
		return this.deletedBy;
	}

	/**
	 * Sets value for attribute deletedBy
	 */
	public void setDeletedBy(java.lang.String deletedBy) {
		this.deletedBy = deletedBy;
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
		b.append (", serialNumber: ");
		b.append (this.serialNumber);
		b.append (", deviceType: ");
		b.append (this.deviceType);
		b.append (", operatingSystem: ");
		b.append (this.operatingSystem);
		b.append (", browser: ");
		b.append (this.browser);
		b.append (", cpu: ");
		b.append (this.cpu);
		b.append (", ip: ");
		b.append (this.ip);
		b.append (", lastUser: ");
		b.append (this.lastUser);
		b.append (", hostName: ");
		b.append (this.hostName);
		b.append (", lastSeen: ");
		b.append (this.lastSeen);
		b.append (", created: ");
		b.append (this.created);
		b.append (", deleted: ");
		b.append (this.deleted);
		b.append (", locked: ");
		b.append (this.locked);
		b.append (", createdOn: ");
		b.append (this.createdOn);
		b.append (", createdBy: ");
		b.append (this.createdBy);
		b.append (", updatedOn: ");
		b.append (this.updatedOn);
		b.append (", updatedBy: ");
		b.append (this.updatedBy);
		b.append (", deletedOn: ");
		b.append (this.deletedOn);
		b.append (", deletedBy: ");
		b.append (this.deletedBy);
		b.append ("]");
		return b.toString();
	}

}
