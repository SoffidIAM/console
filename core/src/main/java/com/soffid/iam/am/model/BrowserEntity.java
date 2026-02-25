//
// (c) 2014 Soffid
//
//

package com.soffid.iam.am.model;

/**
 *  Entity BrowserEntity
 */

public abstract class BrowserEntity {

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
	 * Attribute ip
	 */
	private java.lang.String ip;
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
	 * Attribute serialNumber
	 */
	private java.lang.String serialNumber;
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
	 * Attribute lastSeen
	 */
	private java.util.Date lastSeen;
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
	 * Attribute created
	 */
	private java.util.Date created;
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
	 * Attribute deleted
	 */
	private java.lang.Boolean deleted;
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
	 * Attribute locked
	 */
	private java.lang.Boolean locked;
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
	 * Attribute deviceType
	 */
	private java.lang.String deviceType;
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
	 * Attribute operatingSystem
	 */
	private java.lang.String operatingSystem;
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
	 * Attribute browser
	 */
	private java.lang.String browser;
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
	 * Attribute cpu
	 */
	private java.lang.String cpu;
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
	 * Attribute lastUser
	 */
	private com.soffid.iam.base.model.UserEntity lastUser;
	/**
	 * Gets value for attribute lastUser
	 */
	public com.soffid.iam.base.model.UserEntity getLastUser() {
		return this.lastUser;
	}
	/**
	 * Sets value for attribute lastUser
	 */
	public void setLastUser(com.soffid.iam.base.model.UserEntity lastUser) {
		this.lastUser = lastUser;
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
	 * Attribute sessions

	 */
	private java.util.Collection<com.soffid.iam.am.model.SessionEntity> sessions =  new java.util.HashSet<com.soffid.iam.am.model.SessionEntity>();
	/**
	 * Gets value for attribute sessions
	 */
	public java.util.Collection<com.soffid.iam.am.model.SessionEntity> getSessions() {
		return this.sessions;
	}
	/**
	 * Sets value for attribute sessions
	 */
	public void setSessions(java.util.Collection<com.soffid.iam.am.model.SessionEntity> sessions) {
		this.sessions = sessions;
	}
	/**
	 * Attribute events

	 */
	private java.util.Collection<com.soffid.iam.rc.model.IssueBrowserEntity> events =  new java.util.HashSet<com.soffid.iam.rc.model.IssueBrowserEntity>();
	/**
	 * Gets value for attribute events
	 */
	public java.util.Collection<com.soffid.iam.rc.model.IssueBrowserEntity> getEvents() {
		return this.events;
	}
	/**
	 * Sets value for attribute events
	 */
	public void setEvents(java.util.Collection<com.soffid.iam.rc.model.IssueBrowserEntity> events) {
		this.events = events;
	}
	/**
	 * Attribute createdOn

	 */
	private java.util.Date createdOn;
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
	 * Attribute createdBy

	 */
	private java.lang.String createdBy;
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
	 * Attribute updatedOn

	 */
	private java.util.Date updatedOn;
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
	 * Attribute updatedBy

	 */
	private java.lang.String updatedBy;
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
	 * Attribute deletedOn

	 */
	private java.util.Date deletedOn;
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
	 * Attribute deletedBy

	 */
	private java.lang.String deletedBy;
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
	 * Returns <code>true</code> if the argument is an BrowserEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof BrowserEntity))
		{
			return false;
		}
		final BrowserEntity that = (BrowserEntity)object;
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
