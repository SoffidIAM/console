//
// (c) 2014 Soffid
//
//

package com.soffid.iam.am.model;

/**
 *  Entity NetworkEntity
 */

public abstract class NetworkEntity {

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
	 * Attribute mask
	 */
	private java.lang.String mask;
	/**
	 * Gets value for attribute mask
	 */
	public java.lang.String getMask() {
		return this.mask;
	}
	/**
	 * Sets value for attribute mask
	 */
	public void setMask(java.lang.String mask) {
		this.mask = mask;
	}
	/**
	 * Attribute type
	 */
	private com.soffid.iam.am.api.NetworkType type;
	/**
	 * Gets value for attribute type
	 */
	public com.soffid.iam.am.api.NetworkType getType() {
		return this.type;
	}
	/**
	 * Sets value for attribute type
	 */
	public void setType(com.soffid.iam.am.api.NetworkType type) {
		this.type = type;
	}
	/**
	 * Attribute lanAccess
	 */
	private java.lang.Boolean lanAccess;
	/**
	 * Gets value for attribute lanAccess
	 */
	public java.lang.Boolean getLanAccess() {
		return this.lanAccess;
	}
	/**
	 * Sets value for attribute lanAccess
	 */
	public void setLanAccess(java.lang.Boolean lanAccess) {
		this.lanAccess = lanAccess;
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
	 * Attribute authorizations
	 */
	private java.util.Collection<com.soffid.iam.am.model.NetworkAuthorizationEntity> authorizations =  new java.util.HashSet<com.soffid.iam.am.model.NetworkAuthorizationEntity>();
	/**
	 * Gets value for attribute authorizations
	 */
	public java.util.Collection<com.soffid.iam.am.model.NetworkAuthorizationEntity> getAuthorizations() {
		return this.authorizations;
	}
	/**
	 * Sets value for attribute authorizations
	 */
	public void setAuthorizations(java.util.Collection<com.soffid.iam.am.model.NetworkAuthorizationEntity> authorizations) {
		this.authorizations = authorizations;
	}
	/**
	 * Attribute hosts
	 */
	private java.util.Collection<com.soffid.iam.am.model.HostEntity> hosts =  new java.util.HashSet<com.soffid.iam.am.model.HostEntity>();
	/**
	 * Gets value for attribute hosts
	 */
	public java.util.Collection<com.soffid.iam.am.model.HostEntity> getHosts() {
		return this.hosts;
	}
	/**
	 * Sets value for attribute hosts
	 */
	public void setHosts(java.util.Collection<com.soffid.iam.am.model.HostEntity> hosts) {
		this.hosts = hosts;
	}
	/**
	 * Attribute dchpSupport
	 */
	private boolean dchpSupport;
	/**
	 * Gets value for attribute dchpSupport
	 */
	public boolean isDchpSupport() {
		return this.dchpSupport;
	}
	/**
	 * Sets value for attribute dchpSupport
	 */
	public void setDchpSupport(boolean dchpSupport) {
		this.dchpSupport = dchpSupport;
	}
	/**
	 * Attribute loginRestriction
	 */
	private java.lang.Boolean loginRestriction;
	/**
	 * Gets value for attribute loginRestriction
	 */
	public java.lang.Boolean getLoginRestriction() {
		return this.loginRestriction;
	}
	/**
	 * Sets value for attribute loginRestriction
	 */
	public void setLoginRestriction(java.lang.Boolean loginRestriction) {
		this.loginRestriction = loginRestriction;
	}
	/**
	 * Attribute discovery
	 */
	private java.lang.Boolean discovery;
	/**
	 * Gets value for attribute discovery
	 */
	public java.lang.Boolean getDiscovery() {
		return this.discovery;
	}
	/**
	 * Sets value for attribute discovery
	 */
	public void setDiscovery(java.lang.Boolean discovery) {
		this.discovery = discovery;
	}
	/**
	 * Attribute discoveryServer
	 */
	private com.soffid.iam.sync.model.ServerEntity discoveryServer;
	/**
	 * Gets value for attribute discoveryServer
	 */
	public com.soffid.iam.sync.model.ServerEntity getDiscoveryServer() {
		return this.discoveryServer;
	}
	/**
	 * Sets value for attribute discoveryServer
	 */
	public void setDiscoveryServer(com.soffid.iam.sync.model.ServerEntity discoveryServer) {
		this.discoveryServer = discoveryServer;
	}
	/**
	 * Attribute countryCode
	 */
	private java.lang.String countryCode;
	/**
	 * Gets value for attribute countryCode
	 */
	public java.lang.String getCountryCode() {
		return this.countryCode;
	}
	/**
	 * Sets value for attribute countryCode
	 */
	public void setCountryCode(java.lang.String countryCode) {
		this.countryCode = countryCode;
	}
	/**
	 * Attribute ranges

	 */
	private java.util.Collection<com.soffid.iam.pam.model.NetworkDiscoverRangeEntity> ranges =  new java.util.HashSet<com.soffid.iam.pam.model.NetworkDiscoverRangeEntity>();
	/**
	 * Gets value for attribute ranges
	 */
	public java.util.Collection<com.soffid.iam.pam.model.NetworkDiscoverRangeEntity> getRanges() {
		return this.ranges;
	}
	/**
	 * Sets value for attribute ranges
	 */
	public void setRanges(java.util.Collection<com.soffid.iam.pam.model.NetworkDiscoverRangeEntity> ranges) {
		this.ranges = ranges;
	}
	/**
	 * Attribute accounts

	 */
	private java.util.Collection<com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity> accounts =  new java.util.HashSet<com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity>();
	/**
	 * Gets value for attribute accounts
	 */
	public java.util.Collection<com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity> getAccounts() {
		return this.accounts;
	}
	/**
	 * Sets value for attribute accounts
	 */
	public void setAccounts(java.util.Collection<com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity> accounts) {
		this.accounts = accounts;
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
	 * Returns <code>true</code> if the argument is an NetworkEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof NetworkEntity))
		{
			return false;
		}
		final NetworkEntity that = (NetworkEntity)object;
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
