//
// (C) 2020 Soffid
//
//

package com.soffid.iam.am.api;
/**
 * ValueObject Host
 **/
public class Host

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
	 * Attribute description

	 */
	private java.lang.String description;

	/**
	 * Attribute network

	 */
	private java.lang.String network;

	/**
	 * Attribute ip

	 */
	private java.lang.String ip;

	/**
	 * Attribute dynamicIp

	 */
	private java.lang.Boolean dynamicIp = true;

	/**
	 * Attribute mail

	 */
	private java.lang.Boolean mail = false;

	/**
	 * Attribute folders

	 */
	private java.lang.Boolean folders = false;

	/**
	 * Attribute mac

	 */
	private java.lang.String mac;

	/**
	 * Attribute printersServer

	 */
	private java.lang.Boolean printersServer = false;

	/**
	 * Attribute serialNumber

	 */
	private java.lang.String serialNumber;

	/**
	 * Attribute os

	 */
	private java.lang.String os = "ALT";

	/**
	 * Attribute hostAlias

	 */
	private java.util.List<java.lang.String> hostAlias = new java.util.LinkedList<String>();

	/**
	 * Attribute dhcp

	 */
	private java.lang.String dhcp;

	/**
	 * Attribute lastSeen

	 */
	private java.util.Calendar lastSeen;

	/**
	 * Attribute created

	 */
	private java.util.Calendar created;

	/**
	 * Attribute locked

	 */
	private java.lang.Boolean locked;

	/**
	 * Attribute attributes
	 * Host custom attributes

	 */
	private java.util.Map<java.lang.String,java.lang.Object> attributes = new java.util.HashMap<String,Object>();

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

	/**
	 * Attribute createdOn


	 */
	private java.util.Date createdOn;

	/**
	 * Attribute deleted


	 */
	private java.lang.Boolean deleted;

	public Host()
	{
	}

	public Host(java.lang.Long id, java.lang.String name, java.lang.String description, java.lang.String network, java.lang.String ip, java.lang.Boolean dynamicIp, java.lang.Boolean mail, java.lang.Boolean folders, java.lang.String mac, java.lang.Boolean printersServer, java.lang.String serialNumber, java.lang.String os, java.util.List<java.lang.String> hostAlias, java.lang.String dhcp, java.util.Calendar lastSeen, java.util.Calendar created, java.lang.Boolean locked, java.util.Map<java.lang.String,java.lang.Object> attributes, java.lang.String createdBy, java.util.Date updatedOn, java.lang.String updatedBy, java.util.Date deletedOn, java.lang.String deletedBy, java.util.Date createdOn, java.lang.Boolean deleted)
	{
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.network = network;
		this.ip = ip;
		this.dynamicIp = dynamicIp;
		this.mail = mail;
		this.folders = folders;
		this.mac = mac;
		this.printersServer = printersServer;
		this.serialNumber = serialNumber;
		this.os = os;
		this.hostAlias = hostAlias;
		this.dhcp = dhcp;
		this.lastSeen = lastSeen;
		this.created = created;
		this.locked = locked;
		this.attributes = attributes;
		this.createdBy = createdBy;
		this.updatedOn = updatedOn;
		this.updatedBy = updatedBy;
		this.deletedOn = deletedOn;
		this.deletedBy = deletedBy;
		this.createdOn = createdOn;
		this.deleted = deleted;
	}

	public Host(java.lang.String name, java.lang.String network)
	{
		super();
		this.name = name;
		this.network = network;
	}

	public Host(Host otherBean)
	{
		this(otherBean.id, otherBean.name, otherBean.description, otherBean.network, otherBean.ip, otherBean.dynamicIp, otherBean.mail, otherBean.folders, otherBean.mac, otherBean.printersServer, otherBean.serialNumber, otherBean.os, otherBean.hostAlias, otherBean.dhcp, otherBean.lastSeen, otherBean.created, otherBean.locked, otherBean.attributes, otherBean.createdBy, otherBean.updatedOn, otherBean.updatedBy, otherBean.deletedOn, otherBean.deletedBy, otherBean.createdOn, otherBean.deleted);
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
	 * Gets value for attribute network
	 */
	public java.lang.String getNetwork() {
		return this.network;
	}

	/**
	 * Sets value for attribute network
	 */
	public void setNetwork(java.lang.String network) {
		this.network = network;
	}

	/**
	 * Gets value for attribute network
	 */
	public java.lang.String getNetworkCode() {
		return this.network;
	}

	/**
	 * Sets value for attribute network
	 */
	public void setNetworkCode(java.lang.String network) {
		this.network = network;
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
	 * Gets value for attribute folders
	 */
	public java.lang.Boolean getOffice() {
		return this.folders;
	}

	/**
	 * Sets value for attribute folders
	 */
	public void setOffice(java.lang.Boolean folders) {
		this.folders = folders;
	}

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
	 * Gets value for attribute os
	 */
	public java.lang.String getOs() {
		return this.os;
	}

	/**
	 * Sets value for attribute os
	 */
	public void setOs(java.lang.String os) {
		this.os = os;
	}

	/**
	 * Gets value for attribute hostAlias
	 */
	public java.util.List<java.lang.String> getHostAlias() {
		return this.hostAlias;
	}

	/**
	 * Sets value for attribute hostAlias
	 */
	public void setHostAlias(java.util.List<java.lang.String> hostAlias) {
		this.hostAlias = hostAlias;
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
	 * Gets value for attribute lastSeen
	 */
	public java.util.Calendar getLastSeen() {
		return this.lastSeen;
	}

	/**
	 * Sets value for attribute lastSeen
	 */
	public void setLastSeen(java.util.Calendar lastSeen) {
		this.lastSeen = lastSeen;
	}

	/**
	 * Gets value for attribute created
	 */
	public java.util.Calendar getCreated() {
		return this.created;
	}

	/**
	 * Sets value for attribute created
	 */
	public void setCreated(java.util.Calendar created) {
		this.created = created;
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
	 * Gets value for attribute attributes
	 */
	public java.util.Map<java.lang.String,java.lang.Object> getAttributes() {
		return this.attributes;
	}

	/**
	 * Sets value for attribute attributes
	 */
	public void setAttributes(java.util.Map<java.lang.String,java.lang.Object> attributes) {
		this.attributes = attributes;
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
		b.append (", description: ");
		b.append (this.description);
		b.append (", network: ");
		b.append (this.network);
		b.append (", ip: ");
		b.append (this.ip);
		b.append (", dynamicIp: ");
		b.append (this.dynamicIp);
		b.append (", mail: ");
		b.append (this.mail);
		b.append (", folders: ");
		b.append (this.folders);
		b.append (", mac: ");
		b.append (this.mac);
		b.append (", printersServer: ");
		b.append (this.printersServer);
		b.append (", serialNumber: ");
		b.append (this.serialNumber);
		b.append (", os: ");
		b.append (this.os);
		b.append (", hostAlias: ");
		b.append (this.hostAlias);
		b.append (", dhcp: ");
		b.append (this.dhcp);
		b.append (", lastSeen: ");
		b.append (this.lastSeen);
		b.append (", created: ");
		b.append (this.created);
		b.append (", locked: ");
		b.append (this.locked);
		b.append (", attributes: ");
		b.append (this.attributes);
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
		b.append (", createdOn: ");
		b.append (this.createdOn);
		b.append (", deleted: ");
		b.append (this.deleted);
		b.append ("]");
		return b.toString();
	}

}
