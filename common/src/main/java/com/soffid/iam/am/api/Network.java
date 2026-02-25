//
// (C) 2020 Soffid
//
//

package com.soffid.iam.am.api;
/**
 * ValueObject Network
 **/
public class Network

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
	 * Attribute name

	 */
	private java.lang.String name;

	/**
	 * Attribute ip

	 */
	private java.lang.String ip;

	/**
	 * Attribute description

	 */
	private java.lang.String description;

	/**
	 * Attribute mask

	 */
	private java.lang.String mask;

	/**
	 * Attribute type

	 */
	private com.soffid.iam.am.api.NetworkType type;

	/**
	 * Attribute lanAccess

	 */
	private java.lang.Boolean lanAccess = true;

	/**
	 * Attribute dhcp

	 */
	private java.lang.String dhcp;

	/**
	 * Attribute dhcpSupport

	 */
	private boolean dhcpSupport;

	/**
	 * Attribute loginRestriction

	 */
	private java.lang.Boolean loginRestriction;

	/**
	 * Attribute discovery

	 */
	private java.lang.Boolean discovery;

	/**
	 * Attribute discoveryServer

	 */
	private java.lang.String discoveryServer;

	/**
	 * Attribute discoveryRanges

	 */
	private java.util.List<java.lang.String> discoveryRanges = new java.util.LinkedList();

	/**
	 * Attribute countryCode

	 */
	private java.lang.String countryCode;

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

	public Network()
	{
	}

	public Network(java.lang.Long id, java.lang.String name, java.lang.String ip, java.lang.String description, java.lang.String mask, com.soffid.iam.am.api.NetworkType type, java.lang.Boolean lanAccess, java.lang.String dhcp, boolean dhcpSupport, java.lang.Boolean loginRestriction, java.lang.Boolean discovery, java.lang.String discoveryServer, java.util.List<java.lang.String> discoveryRanges, java.lang.String countryCode, java.util.Date createdOn, java.lang.String createdBy, java.util.Date updatedOn, java.lang.String updatedBy, java.util.Date deletedOn, java.lang.String deletedBy)
	{
		super();
		this.id = id;
		this.name = name;
		this.ip = ip;
		this.description = description;
		this.mask = mask;
		this.type = type;
		this.lanAccess = lanAccess;
		this.dhcp = dhcp;
		this.dhcpSupport = dhcpSupport;
		this.loginRestriction = loginRestriction;
		this.discovery = discovery;
		this.discoveryServer = discoveryServer;
		this.discoveryRanges = discoveryRanges;
		this.countryCode = countryCode;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
		this.updatedOn = updatedOn;
		this.updatedBy = updatedBy;
		this.deletedOn = deletedOn;
		this.deletedBy = deletedBy;
	}

	public Network(java.lang.String name, java.lang.String ip, boolean dhcpSupport)
	{
		super();
		this.name = name;
		this.ip = ip;
		this.dhcpSupport = dhcpSupport;
	}

	public Network(Network otherBean)
	{
		this(otherBean.id, otherBean.name, otherBean.ip, otherBean.description, otherBean.mask, otherBean.type, otherBean.lanAccess, otherBean.dhcp, otherBean.dhcpSupport, otherBean.loginRestriction, otherBean.discovery, otherBean.discoveryServer, otherBean.discoveryRanges, otherBean.countryCode, otherBean.createdOn, otherBean.createdBy, otherBean.updatedOn, otherBean.updatedBy, otherBean.deletedOn, otherBean.deletedBy);
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
	 * Gets value for attribute name
	 */
	public java.lang.String getCode() {
		return this.name;
	}

	/**
	 * Sets value for attribute name
	 */
	public void setCode(java.lang.String name) {
		this.name = name;
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
	 * Gets value for attribute dhcpSupport
	 */
	public boolean isDhcpSupport() {
		return this.dhcpSupport;
	}

	/**
	 * Sets value for attribute dhcpSupport
	 */
	public void setDhcpSupport(boolean dhcpSupport) {
		this.dhcpSupport = dhcpSupport;
	}

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
	 * Gets value for attribute discoveryServer
	 */
	public java.lang.String getDiscoveryServer() {
		return this.discoveryServer;
	}

	/**
	 * Sets value for attribute discoveryServer
	 */
	public void setDiscoveryServer(java.lang.String discoveryServer) {
		this.discoveryServer = discoveryServer;
	}

	/**
	 * Gets value for attribute discoveryRanges
	 */
	public java.util.List<java.lang.String> getDiscoveryRanges() {
		return this.discoveryRanges;
	}

	/**
	 * Sets value for attribute discoveryRanges
	 */
	public void setDiscoveryRanges(java.util.List<java.lang.String> discoveryRanges) {
		this.discoveryRanges = discoveryRanges;
	}

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
		b.append (", name: ");
		b.append (this.name);
		b.append (", ip: ");
		b.append (this.ip);
		b.append (", description: ");
		b.append (this.description);
		b.append (", mask: ");
		b.append (this.mask);
		b.append (", type: ");
		b.append (this.type);
		b.append (", lanAccess: ");
		b.append (this.lanAccess);
		b.append (", dhcp: ");
		b.append (this.dhcp);
		b.append (", dhcpSupport: ");
		b.append (this.dhcpSupport);
		b.append (", loginRestriction: ");
		b.append (this.loginRestriction);
		b.append (", discovery: ");
		b.append (this.discovery);
		b.append (", discoveryServer: ");
		b.append (this.discoveryServer);
		b.append (", discoveryRanges: ");
		b.append (this.discoveryRanges);
		b.append (", countryCode: ");
		b.append (this.countryCode);
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
