//
// (c) 2014 Soffid
//
//

package com.soffid.iam.am.model;

/**
 *  Entity HostEntity
 */

public abstract class HostEntity {

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
	 * Attribute name
	 */
	private java.lang.String name;
	/**
	 * Gets value for attribute name
	 */
	public java.lang.String getName() {
		return this.name;
	}
	/**
	 * Sets value for attribute name
	 */
	public void setName(java.lang.String name) {
		this.name = name;
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
	 * Attribute description
	 */
	private java.lang.String description;
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
	 * Attribute dhcp
	 */
	private java.lang.String dhcp;
	/**
	 * Gets value for attribute dhcp
	 */
	public java.lang.String getDhcp() {
		return this.dhcp;
	}
	/**
	 * Sets value for attribute dhcp
	 */
	public void setDhcp(java.lang.String dhcp) {
		this.dhcp = dhcp;
	}
	/**
	 * Attribute mail
	 */
	private java.lang.Boolean mail;
	/**
	 * Gets value for attribute mail
	 */
	public java.lang.Boolean getMail() {
		return this.mail;
	}
	/**
	 * Sets value for attribute mail
	 */
	public void setMail(java.lang.Boolean mail) {
		this.mail = mail;
	}
	/**
	 * Attribute folders
	 */
	private java.lang.Boolean folders;
	/**
	 * Gets value for attribute folders
	 */
	public java.lang.Boolean getFolders() {
		return this.folders;
	}
	/**
	 * Sets value for attribute folders
	 */
	public void setFolders(java.lang.Boolean folders) {
		this.folders = folders;
	}
	/**
	 * Attribute userProfiles
	 */
	private java.util.Collection<com.soffid.iam.base.model.UserEntity> userProfiles =  new java.util.HashSet<com.soffid.iam.base.model.UserEntity>();
	/**
	 * Gets value for attribute userProfiles
	 */
	public java.util.Collection<com.soffid.iam.base.model.UserEntity> getUserProfiles() {
		return this.userProfiles;
	}
	/**
	 * Sets value for attribute userProfiles
	 */
	public void setUserProfiles(java.util.Collection<com.soffid.iam.base.model.UserEntity> userProfiles) {
		this.userProfiles = userProfiles;
	}
	/**
	 * Attribute printers
	 */
	private java.util.Collection<com.soffid.iam.iga.model.PrinterEntity> printers =  new java.util.HashSet<com.soffid.iam.iga.model.PrinterEntity>();
	/**
	 * Gets value for attribute printers
	 */
	public java.util.Collection<com.soffid.iam.iga.model.PrinterEntity> getPrinters() {
		return this.printers;
	}
	/**
	 * Sets value for attribute printers
	 */
	public void setPrinters(java.util.Collection<com.soffid.iam.iga.model.PrinterEntity> printers) {
		this.printers = printers;
	}
	/**
	 * Attribute network
	 */
	private com.soffid.iam.am.model.NetworkEntity network;
	/**
	 * Gets value for attribute network
	 */
	public com.soffid.iam.am.model.NetworkEntity getNetwork() {
		return this.network;
	}
	/**
	 * Sets value for attribute network
	 */
	public void setNetwork(com.soffid.iam.am.model.NetworkEntity network) {
		this.network = network;
	}
	/**
	 * Attribute printersServer
	 */
	private java.lang.Boolean printersServer;
	/**
	 * Gets value for attribute printersServer
	 */
	public java.lang.Boolean getPrintersServer() {
		return this.printersServer;
	}
	/**
	 * Sets value for attribute printersServer
	 */
	public void setPrintersServer(java.lang.Boolean printersServer) {
		this.printersServer = printersServer;
	}
	/**
	 * Attribute mac
	 */
	private java.lang.String mac;
	/**
	 * Gets value for attribute mac
	 */
	public java.lang.String getMac() {
		return this.mac;
	}
	/**
	 * Sets value for attribute mac
	 */
	public void setMac(java.lang.String mac) {
		this.mac = mac;
	}
	/**
	 * Attribute hostAlias
	 */
	private java.util.Collection<com.soffid.iam.iga.model.HostAliasEntity> hostAlias =  new java.util.HashSet<com.soffid.iam.iga.model.HostAliasEntity>();
	/**
	 * Gets value for attribute hostAlias
	 */
	public java.util.Collection<com.soffid.iam.iga.model.HostAliasEntity> getHostAlias() {
		return this.hostAlias;
	}
	/**
	 * Sets value for attribute hostAlias
	 */
	public void setHostAlias(java.util.Collection<com.soffid.iam.iga.model.HostAliasEntity> hostAlias) {
		this.hostAlias = hostAlias;
	}
	/**
	 * Attribute administratorAuthorizationAccess
	 */
	private java.util.Collection<com.soffid.iam.pam.model.HostAdminEntity> administratorAuthorizationAccess =  new java.util.HashSet<com.soffid.iam.pam.model.HostAdminEntity>();
	/**
	 * Gets value for attribute administratorAuthorizationAccess
	 */
	public java.util.Collection<com.soffid.iam.pam.model.HostAdminEntity> getAdministratorAuthorizationAccess() {
		return this.administratorAuthorizationAccess;
	}
	/**
	 * Sets value for attribute administratorAuthorizationAccess
	 */
	public void setAdministratorAuthorizationAccess(java.util.Collection<com.soffid.iam.pam.model.HostAdminEntity> administratorAuthorizationAccess) {
		this.administratorAuthorizationAccess = administratorAuthorizationAccess;
	}
	/**
	 * Attribute administratorUser
	 */
	private java.lang.String administratorUser;
	/**
	 * Gets value for attribute administratorUser
	 */
	public java.lang.String getAdministratorUser() {
		return this.administratorUser;
	}
	/**
	 * Sets value for attribute administratorUser
	 */
	public void setAdministratorUser(java.lang.String administratorUser) {
		this.administratorUser = administratorUser;
	}
	/**
	 * Attribute administratorPassword
	 */
	private java.lang.String administratorPassword;
	/**
	 * Gets value for attribute administratorPassword
	 */
	public java.lang.String getAdministratorPassword() {
		return this.administratorPassword;
	}
	/**
	 * Sets value for attribute administratorPassword
	 */
	public void setAdministratorPassword(java.lang.String administratorPassword) {
		this.administratorPassword = administratorPassword;
	}
	/**
	 * Attribute administratorPasswordDate
	 */
	private java.util.Date administratorPasswordDate;
	/**
	 * Gets value for attribute administratorPasswordDate
	 */
	public java.util.Date getAdministratorPasswordDate() {
		return this.administratorPasswordDate;
	}
	/**
	 * Sets value for attribute administratorPasswordDate
	 */
	public void setAdministratorPasswordDate(java.util.Date administratorPasswordDate) {
		this.administratorPasswordDate = administratorPasswordDate;
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
	 * Attribute dynamicIp
	 */
	private java.lang.Boolean dynamicIp;
	/**
	 * Gets value for attribute dynamicIp
	 */
	public java.lang.Boolean getDynamicIp() {
		return this.dynamicIp;
	}
	/**
	 * Sets value for attribute dynamicIp
	 */
	public void setDynamicIp(java.lang.Boolean dynamicIp) {
		this.dynamicIp = dynamicIp;
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
	 * Attribute operatingSystem
	 */
	private com.soffid.iam.am.model.OsTypeEntity operatingSystem;
	/**
	 * Gets value for attribute operatingSystem
	 */
	public com.soffid.iam.am.model.OsTypeEntity getOperatingSystem() {
		return this.operatingSystem;
	}
	/**
	 * Sets value for attribute operatingSystem
	 */
	public void setOperatingSystem(com.soffid.iam.am.model.OsTypeEntity operatingSystem) {
		this.operatingSystem = operatingSystem;
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
	 * Attribute browsers

	 */
	private java.util.Collection<com.soffid.iam.am.model.BrowserEntity> browsers =  new java.util.HashSet<com.soffid.iam.am.model.BrowserEntity>();
	/**
	 * Gets value for attribute browsers
	 */
	public java.util.Collection<com.soffid.iam.am.model.BrowserEntity> getBrowsers() {
		return this.browsers;
	}
	/**
	 * Sets value for attribute browsers
	 */
	public void setBrowsers(java.util.Collection<com.soffid.iam.am.model.BrowserEntity> browsers) {
		this.browsers = browsers;
	}
	/**
	 * Attribute attributes

	 */
	private java.util.Collection<com.soffid.iam.am.model.HostAttributeEntity> attributes =  new java.util.HashSet<com.soffid.iam.am.model.HostAttributeEntity>();
	/**
	 * Gets value for attribute attributes
	 */
	public java.util.Collection<com.soffid.iam.am.model.HostAttributeEntity> getAttributes() {
		return this.attributes;
	}
	/**
	 * Sets value for attribute attributes
	 */
	public void setAttributes(java.util.Collection<com.soffid.iam.am.model.HostAttributeEntity> attributes) {
		this.attributes = attributes;
	}
	/**
	 * Attribute entryPoints

	 */
	private java.util.Collection<com.soffid.iam.am.model.HostEntryPointEntity> entryPoints =  new java.util.HashSet<com.soffid.iam.am.model.HostEntryPointEntity>();
	/**
	 * Gets value for attribute entryPoints
	 */
	public java.util.Collection<com.soffid.iam.am.model.HostEntryPointEntity> getEntryPoints() {
		return this.entryPoints;
	}
	/**
	 * Sets value for attribute entryPoints
	 */
	public void setEntryPoints(java.util.Collection<com.soffid.iam.am.model.HostEntryPointEntity> entryPoints) {
		this.entryPoints = entryPoints;
	}
	/**
	 * Attribute ports

	 */
	private java.util.Collection<com.soffid.iam.pam.model.HostPortEntity> ports =  new java.util.HashSet<com.soffid.iam.pam.model.HostPortEntity>();
	/**
	 * Gets value for attribute ports
	 */
	public java.util.Collection<com.soffid.iam.pam.model.HostPortEntity> getPorts() {
		return this.ports;
	}
	/**
	 * Sets value for attribute ports
	 */
	public void setPorts(java.util.Collection<com.soffid.iam.pam.model.HostPortEntity> ports) {
		this.ports = ports;
	}
	/**
	 * Attribute services

	 */
	private java.util.Collection<com.soffid.iam.pam.model.HostServiceEntity> services =  new java.util.HashSet<com.soffid.iam.pam.model.HostServiceEntity>();
	/**
	 * Gets value for attribute services
	 */
	public java.util.Collection<com.soffid.iam.pam.model.HostServiceEntity> getServices() {
		return this.services;
	}
	/**
	 * Sets value for attribute services
	 */
	public void setServices(java.util.Collection<com.soffid.iam.pam.model.HostServiceEntity> services) {
		this.services = services;
	}
	/**
	 * Attribute systems

	 */
	private java.util.Collection<com.soffid.iam.pam.model.HostSystemEntity> systems =  new java.util.HashSet<com.soffid.iam.pam.model.HostSystemEntity>();
	/**
	 * Gets value for attribute systems
	 */
	public java.util.Collection<com.soffid.iam.pam.model.HostSystemEntity> getSystems() {
		return this.systems;
	}
	/**
	 * Sets value for attribute systems
	 */
	public void setSystems(java.util.Collection<com.soffid.iam.pam.model.HostSystemEntity> systems) {
		this.systems = systems;
	}
	/**
	 * Attribute events

	 */
	private java.util.Collection<com.soffid.iam.rc.model.IssueHostEntity> events =  new java.util.HashSet<com.soffid.iam.rc.model.IssueHostEntity>();
	/**
	 * Gets value for attribute events
	 */
	public java.util.Collection<com.soffid.iam.rc.model.IssueHostEntity> getEvents() {
		return this.events;
	}
	/**
	 * Sets value for attribute events
	 */
	public void setEvents(java.util.Collection<com.soffid.iam.rc.model.IssueHostEntity> events) {
		this.events = events;
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
	 * Returns <code>true</code> if the argument is an HostEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof HostEntity))
		{
			return false;
		}
		final HostEntity that = (HostEntity)object;
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
