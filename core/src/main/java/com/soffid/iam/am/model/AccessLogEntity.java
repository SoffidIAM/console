//
// (c) 2014 Soffid
//
//

package com.soffid.iam.am.model;

/**
 *  Entity AccessLogEntity
 */

public abstract class AccessLogEntity {

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
	 * Attribute sessionId
	 */
	private java.lang.String sessionId;
	/**
	 * Gets value for attribute sessionId
	 */
	public java.lang.String getSessionId() {
		return this.sessionId;
	}
	/**
	 * Sets value for attribute sessionId
	 */
	public void setSessionId(java.lang.String sessionId) {
		this.sessionId = sessionId;
	}
	/**
	 * Attribute startDate
	 */
	private java.util.Date startDate;
	/**
	 * Gets value for attribute startDate
	 */
	public java.util.Date getStartDate() {
		return this.startDate;
	}
	/**
	 * Sets value for attribute startDate
	 */
	public void setStartDate(java.util.Date startDate) {
		this.startDate = startDate;
	}
	/**
	 * Attribute endDate
	 */
	private java.util.Date endDate;
	/**
	 * Gets value for attribute endDate
	 */
	public java.util.Date getEndDate() {
		return this.endDate;
	}
	/**
	 * Sets value for attribute endDate
	 */
	public void setEndDate(java.util.Date endDate) {
		this.endDate = endDate;
	}
	/**
	 * Attribute system
	 */
	private java.lang.String system;
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
	 * Attribute information
	 */
	private java.lang.String information;
	/**
	 * Gets value for attribute information
	 */
	public java.lang.String getInformation() {
		return this.information;
	}
	/**
	 * Sets value for attribute information
	 */
	public void setInformation(java.lang.String information) {
		this.information = information;
	}
	/**
	 * Attribute server
	 */
	private com.soffid.iam.am.model.HostEntity server;
	/**
	 * Gets value for attribute server
	 */
	public com.soffid.iam.am.model.HostEntity getServer() {
		return this.server;
	}
	/**
	 * Sets value for attribute server
	 */
	public void setServer(com.soffid.iam.am.model.HostEntity server) {
		this.server = server;
	}
	/**
	 * Attribute client
	 */
	private com.soffid.iam.am.model.HostEntity client;
	/**
	 * Gets value for attribute client
	 */
	public com.soffid.iam.am.model.HostEntity getClient() {
		return this.client;
	}
	/**
	 * Sets value for attribute client
	 */
	public void setClient(com.soffid.iam.am.model.HostEntity client) {
		this.client = client;
	}
	/**
	 * Attribute browser
	 */
	private com.soffid.iam.am.model.BrowserEntity browser;
	/**
	 * Gets value for attribute browser
	 */
	public com.soffid.iam.am.model.BrowserEntity getBrowser() {
		return this.browser;
	}
	/**
	 * Sets value for attribute browser
	 */
	public void setBrowser(com.soffid.iam.am.model.BrowserEntity browser) {
		this.browser = browser;
	}
	/**
	 * Attribute protocol
	 */
	private com.soffid.iam.am.model.ServiceEntity protocol;
	/**
	 * Gets value for attribute protocol
	 */
	public com.soffid.iam.am.model.ServiceEntity getProtocol() {
		return this.protocol;
	}
	/**
	 * Sets value for attribute protocol
	 */
	public void setProtocol(com.soffid.iam.am.model.ServiceEntity protocol) {
		this.protocol = protocol;
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
	 * Attribute accessType
	 */
	private java.lang.String accessType;
	/**
	 * Gets value for attribute accessType
	 */
	public java.lang.String getAccessType() {
		return this.accessType;
	}
	/**
	 * Sets value for attribute accessType
	 */
	public void setAccessType(java.lang.String accessType) {
		this.accessType = accessType;
	}
	/**
	 * Attribute clientAddress
	 */
	private java.lang.String clientAddress;
	/**
	 * Gets value for attribute clientAddress
	 */
	public java.lang.String getClientAddress() {
		return this.clientAddress;
	}
	/**
	 * Sets value for attribute clientAddress
	 */
	public void setClientAddress(java.lang.String clientAddress) {
		this.clientAddress = clientAddress;
	}
	/**
	 * Attribute clientHostName
	 */
	private java.lang.String clientHostName;
	/**
	 * Gets value for attribute clientHostName
	 */
	public java.lang.String getClientHostName() {
		return this.clientHostName;
	}
	/**
	 * Sets value for attribute clientHostName
	 */
	public void setClientHostName(java.lang.String clientHostName) {
		this.clientHostName = clientHostName;
	}
	/**
	 * Attribute hostAddress
	 */
	private java.lang.String hostAddress;
	/**
	 * Gets value for attribute hostAddress
	 */
	public java.lang.String getHostAddress() {
		return this.hostAddress;
	}
	/**
	 * Sets value for attribute hostAddress
	 */
	public void setHostAddress(java.lang.String hostAddress) {
		this.hostAddress = hostAddress;
	}
	/**
	 * Attribute hostName
	 */
	private java.lang.String hostName;
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
	 * Attribute country
	 */
	private java.lang.String country;
	/**
	 * Gets value for attribute country
	 */
	public java.lang.String getCountry() {
		return this.country;
	}
	/**
	 * Sets value for attribute country
	 */
	public void setCountry(java.lang.String country) {
		this.country = country;
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
	 * Attribute jumpServerGroup
	 * Jump server group used in PAM sessions
	 */
	private java.lang.String jumpServerGroup;
	/**
	 * Gets value for attribute jumpServerGroup
	 */
	public java.lang.String getJumpServerGroup() {
		return this.jumpServerGroup;
	}
	/**
	 * Sets value for attribute jumpServerGroup
	 */
	public void setJumpServerGroup(java.lang.String jumpServerGroup) {
		this.jumpServerGroup = jumpServerGroup;
	}
	/**
	 * Attribute accountName
	 * Account name used in PAM sessions
	 */
	private java.lang.String accountName;
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
	 * Attribute targetApplication
	 * Target application
	 */
	private java.lang.String targetApplication;
	/**
	 * Gets value for attribute targetApplication
	 */
	public java.lang.String getTargetApplication() {
		return this.targetApplication;
	}
	/**
	 * Sets value for attribute targetApplication
	 */
	public void setTargetApplication(java.lang.String targetApplication) {
		this.targetApplication = targetApplication;
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
	 * Operation isAllowed
	 * Returns true if the permission on this object is granted
	 * @param permission
	 * @return
	**/
	 public abstract boolean isAllowed(
		java.lang.String permission);

	/**
	 * Returns <code>true</code> if the argument is an AccessLogEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof AccessLogEntity))
		{
			return false;
		}
		final AccessLogEntity that = (AccessLogEntity)object;
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
