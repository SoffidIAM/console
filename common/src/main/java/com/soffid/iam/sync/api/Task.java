//
// (C) 2020 Soffid
//
//

package com.soffid.iam.sync.api;
/**
 * ValueObject Task
 **/
public class Task

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
	 * Attribute user

	 */
	private java.lang.String user;

	/**
	 * Attribute password

	 */
	private java.lang.String password;

	/**
	 * Attribute passwordChange

	 */
	private java.lang.String passwordChange;

	/**
	 * Attribute folder

	 */
	private java.lang.String folder;

	/**
	 * Attribute folderType

	 */
	private java.lang.String folderType;

	/**
	 * Attribute printer

	 */
	private java.lang.String printer;

	/**
	 * Attribute host

	 */
	private java.lang.String host;

	/**
	 * Attribute subnet

	 */
	private java.lang.String subnet;

	/**
	 * Attribute message

	 */
	private java.lang.String message;

	/**
	 * Attribute status

	 */
	private java.lang.String status;

	/**
	 * Attribute taskDate

	 */
	private java.util.Calendar taskDate;

	/**
	 * Attribute transaction

	 */
	private java.lang.String transaction;

	/**
	 * Attribute group

	 */
	private java.lang.String group;

	/**
	 * Attribute alias

	 */
	private java.lang.String alias;

	/**
	 * Attribute mailDomain

	 */
	private java.lang.String mailDomain;

	/**
	 * Attribute role

	 */
	private java.lang.String role;

	/**
	 * Attribute database

	 */
	private java.lang.String database;

	/**
	 * Attribute systemName

	 */
	private java.lang.String systemName;

	/**
	 * Attribute server

	 */
	private java.lang.String server;

	/**
	 * Attribute serverInstance

	 */
	private java.lang.String serverInstance;

	/**
	 * Attribute userDomain

	 */
	private java.lang.String userDomain;

	/**
	 * Attribute passwordDomain

	 */
	private java.lang.String passwordDomain;

	/**
	 * Attribute hash

	 */
	private java.lang.String hash;

	/**
	 * Attribute expirationDate

	 */
	private java.util.Calendar expirationDate;

	/**
	 * Attribute entity

	 */
	private java.lang.String entity;

	/**
	 * Attribute primaryKeyValue

	 */
	private java.lang.Long primaryKeyValue;

	/**
	 * Attribute customObjectType

	 */
	private java.lang.String customObjectType;

	/**
	 * Attribute customObjectName

	 */
	private java.lang.String customObjectName;

	/**
	 * Attribute externalId

	 */
	private java.lang.String externalId;

	/**
	 * Attribute customTaskName

	 */
	private java.lang.String customTaskName;

	/**
	 * Attribute parameters
	 * Serialized JSON parameters

	 */
	private java.lang.String parameters;

	public Task()
	{
	}

	public Task(java.lang.Long id, java.lang.String user, java.lang.String password, java.lang.String passwordChange, java.lang.String folder, java.lang.String folderType, java.lang.String printer, java.lang.String host, java.lang.String subnet, java.lang.String message, java.lang.String status, java.util.Calendar taskDate, java.lang.String transaction, java.lang.String group, java.lang.String alias, java.lang.String mailDomain, java.lang.String role, java.lang.String database, java.lang.String systemName, java.lang.String server, java.lang.String serverInstance, java.lang.String userDomain, java.lang.String passwordDomain, java.lang.String hash, java.util.Calendar expirationDate, java.lang.String entity, java.lang.Long primaryKeyValue, java.lang.String customObjectType, java.lang.String customObjectName, java.lang.String externalId, java.lang.String customTaskName, java.lang.String parameters)
	{
		super();
		this.id = id;
		this.user = user;
		this.password = password;
		this.passwordChange = passwordChange;
		this.folder = folder;
		this.folderType = folderType;
		this.printer = printer;
		this.host = host;
		this.subnet = subnet;
		this.message = message;
		this.status = status;
		this.taskDate = taskDate;
		this.transaction = transaction;
		this.group = group;
		this.alias = alias;
		this.mailDomain = mailDomain;
		this.role = role;
		this.database = database;
		this.systemName = systemName;
		this.server = server;
		this.serverInstance = serverInstance;
		this.userDomain = userDomain;
		this.passwordDomain = passwordDomain;
		this.hash = hash;
		this.expirationDate = expirationDate;
		this.entity = entity;
		this.primaryKeyValue = primaryKeyValue;
		this.customObjectType = customObjectType;
		this.customObjectName = customObjectName;
		this.externalId = externalId;
		this.customTaskName = customTaskName;
		this.parameters = parameters;
	}

	public Task(java.util.Calendar taskDate, java.lang.String transaction)
	{
		super();
		this.taskDate = taskDate;
		this.transaction = transaction;
	}

	public Task(Task otherBean)
	{
		this(otherBean.id, otherBean.user, otherBean.password, otherBean.passwordChange, otherBean.folder, otherBean.folderType, otherBean.printer, otherBean.host, otherBean.subnet, otherBean.message, otherBean.status, otherBean.taskDate, otherBean.transaction, otherBean.group, otherBean.alias, otherBean.mailDomain, otherBean.role, otherBean.database, otherBean.systemName, otherBean.server, otherBean.serverInstance, otherBean.userDomain, otherBean.passwordDomain, otherBean.hash, otherBean.expirationDate, otherBean.entity, otherBean.primaryKeyValue, otherBean.customObjectType, otherBean.customObjectName, otherBean.externalId, otherBean.customTaskName, otherBean.parameters);
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
	 * Gets value for attribute user
	 */
	public java.lang.String getUser() {
		return this.user;
	}

	/**
	 * Sets value for attribute user
	 */
	public void setUser(java.lang.String user) {
		this.user = user;
	}

	/**
	 * Gets value for attribute password
	 */
	public java.lang.String getPassword() {
		return this.password;
	}

	/**
	 * Sets value for attribute password
	 */
	public void setPassword(java.lang.String password) {
		this.password = password;
	}

	/**
	 * Gets value for attribute passwordChange
	 */
	public java.lang.String getPasswordChange() {
		return this.passwordChange;
	}

	/**
	 * Sets value for attribute passwordChange
	 */
	public void setPasswordChange(java.lang.String passwordChange) {
		this.passwordChange = passwordChange;
	}

	/**
	 * Gets value for attribute folder
	 */
	public java.lang.String getFolder() {
		return this.folder;
	}

	/**
	 * Sets value for attribute folder
	 */
	public void setFolder(java.lang.String folder) {
		this.folder = folder;
	}

	/**
	 * Gets value for attribute folderType
	 */
	public java.lang.String getFolderType() {
		return this.folderType;
	}

	/**
	 * Sets value for attribute folderType
	 */
	public void setFolderType(java.lang.String folderType) {
		this.folderType = folderType;
	}

	/**
	 * Gets value for attribute printer
	 */
	public java.lang.String getPrinter() {
		return this.printer;
	}

	/**
	 * Sets value for attribute printer
	 */
	public void setPrinter(java.lang.String printer) {
		this.printer = printer;
	}

	/**
	 * Gets value for attribute host
	 */
	public java.lang.String getHost() {
		return this.host;
	}

	/**
	 * Sets value for attribute host
	 */
	public void setHost(java.lang.String host) {
		this.host = host;
	}

	/**
	 * Gets value for attribute subnet
	 */
	public java.lang.String getSubnet() {
		return this.subnet;
	}

	/**
	 * Sets value for attribute subnet
	 */
	public void setSubnet(java.lang.String subnet) {
		this.subnet = subnet;
	}

	/**
	 * Gets value for attribute message
	 */
	public java.lang.String getMessage() {
		return this.message;
	}

	/**
	 * Sets value for attribute message
	 */
	public void setMessage(java.lang.String message) {
		this.message = message;
	}

	/**
	 * Gets value for attribute status
	 */
	public java.lang.String getStatus() {
		return this.status;
	}

	/**
	 * Sets value for attribute status
	 */
	public void setStatus(java.lang.String status) {
		this.status = status;
	}

	/**
	 * Gets value for attribute taskDate
	 */
	public java.util.Calendar getTaskDate() {
		return this.taskDate;
	}

	/**
	 * Sets value for attribute taskDate
	 */
	public void setTaskDate(java.util.Calendar taskDate) {
		this.taskDate = taskDate;
	}

	/**
	 * Gets value for attribute transaction
	 */
	public java.lang.String getTransaction() {
		return this.transaction;
	}

	/**
	 * Sets value for attribute transaction
	 */
	public void setTransaction(java.lang.String transaction) {
		this.transaction = transaction;
	}

	/**
	 * Gets value for attribute group
	 */
	public java.lang.String getGroup() {
		return this.group;
	}

	/**
	 * Sets value for attribute group
	 */
	public void setGroup(java.lang.String group) {
		this.group = group;
	}

	/**
	 * Gets value for attribute alias
	 */
	public java.lang.String getAlias() {
		return this.alias;
	}

	/**
	 * Sets value for attribute alias
	 */
	public void setAlias(java.lang.String alias) {
		this.alias = alias;
	}

	/**
	 * Gets value for attribute mailDomain
	 */
	public java.lang.String getMailDomain() {
		return this.mailDomain;
	}

	/**
	 * Sets value for attribute mailDomain
	 */
	public void setMailDomain(java.lang.String mailDomain) {
		this.mailDomain = mailDomain;
	}

	/**
	 * Gets value for attribute role
	 */
	public java.lang.String getRole() {
		return this.role;
	}

	/**
	 * Sets value for attribute role
	 */
	public void setRole(java.lang.String role) {
		this.role = role;
	}

	/**
	 * Gets value for attribute database
	 */
	public java.lang.String getDatabase() {
		return this.database;
	}

	/**
	 * Sets value for attribute database
	 */
	public void setDatabase(java.lang.String database) {
		this.database = database;
	}

	/**
	 * Gets value for attribute systemName
	 */
	public java.lang.String getSystemName() {
		return this.systemName;
	}

	/**
	 * Sets value for attribute systemName
	 */
	public void setSystemName(java.lang.String systemName) {
		this.systemName = systemName;
	}

	/**
	 * Gets value for attribute server
	 */
	public java.lang.String getServer() {
		return this.server;
	}

	/**
	 * Sets value for attribute server
	 */
	public void setServer(java.lang.String server) {
		this.server = server;
	}

	/**
	 * Gets value for attribute serverInstance
	 */
	public java.lang.String getServerInstance() {
		return this.serverInstance;
	}

	/**
	 * Sets value for attribute serverInstance
	 */
	public void setServerInstance(java.lang.String serverInstance) {
		this.serverInstance = serverInstance;
	}

	/**
	 * Gets value for attribute userDomain
	 */
	public java.lang.String getUserDomain() {
		return this.userDomain;
	}

	/**
	 * Sets value for attribute userDomain
	 */
	public void setUserDomain(java.lang.String userDomain) {
		this.userDomain = userDomain;
	}

	/**
	 * Gets value for attribute passwordDomain
	 */
	public java.lang.String getPasswordDomain() {
		return this.passwordDomain;
	}

	/**
	 * Sets value for attribute passwordDomain
	 */
	public void setPasswordDomain(java.lang.String passwordDomain) {
		this.passwordDomain = passwordDomain;
	}

	/**
	 * Gets value for attribute hash
	 */
	public java.lang.String getHash() {
		return this.hash;
	}

	/**
	 * Sets value for attribute hash
	 */
	public void setHash(java.lang.String hash) {
		this.hash = hash;
	}

	/**
	 * Gets value for attribute expirationDate
	 */
	public java.util.Calendar getExpirationDate() {
		return this.expirationDate;
	}

	/**
	 * Sets value for attribute expirationDate
	 */
	public void setExpirationDate(java.util.Calendar expirationDate) {
		this.expirationDate = expirationDate;
	}

	/**
	 * Gets value for attribute entity
	 */
	public java.lang.String getEntity() {
		return this.entity;
	}

	/**
	 * Sets value for attribute entity
	 */
	public void setEntity(java.lang.String entity) {
		this.entity = entity;
	}

	/**
	 * Gets value for attribute primaryKeyValue
	 */
	public java.lang.Long getPrimaryKeyValue() {
		return this.primaryKeyValue;
	}

	/**
	 * Sets value for attribute primaryKeyValue
	 */
	public void setPrimaryKeyValue(java.lang.Long primaryKeyValue) {
		this.primaryKeyValue = primaryKeyValue;
	}

	/**
	 * Gets value for attribute customObjectType
	 */
	public java.lang.String getCustomObjectType() {
		return this.customObjectType;
	}

	/**
	 * Sets value for attribute customObjectType
	 */
	public void setCustomObjectType(java.lang.String customObjectType) {
		this.customObjectType = customObjectType;
	}

	/**
	 * Gets value for attribute customObjectName
	 */
	public java.lang.String getCustomObjectName() {
		return this.customObjectName;
	}

	/**
	 * Sets value for attribute customObjectName
	 */
	public void setCustomObjectName(java.lang.String customObjectName) {
		this.customObjectName = customObjectName;
	}

	/**
	 * Gets value for attribute externalId
	 */
	public java.lang.String getExternalId() {
		return this.externalId;
	}

	/**
	 * Sets value for attribute externalId
	 */
	public void setExternalId(java.lang.String externalId) {
		this.externalId = externalId;
	}

	/**
	 * Gets value for attribute customTaskName
	 */
	public java.lang.String getCustomTaskName() {
		return this.customTaskName;
	}

	/**
	 * Sets value for attribute customTaskName
	 */
	public void setCustomTaskName(java.lang.String customTaskName) {
		this.customTaskName = customTaskName;
	}

	/**
	 * Gets value for attribute parameters
	 */
	public java.lang.String getParameters() {
		return this.parameters;
	}

	/**
	 * Sets value for attribute parameters
	 */
	public void setParameters(java.lang.String parameters) {
		this.parameters = parameters;
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
		b.append (", user: ");
		b.append (this.user);
		b.append (", password: ");
		b.append (this.password);
		b.append (", passwordChange: ");
		b.append (this.passwordChange);
		b.append (", folder: ");
		b.append (this.folder);
		b.append (", folderType: ");
		b.append (this.folderType);
		b.append (", printer: ");
		b.append (this.printer);
		b.append (", host: ");
		b.append (this.host);
		b.append (", subnet: ");
		b.append (this.subnet);
		b.append (", message: ");
		b.append (this.message);
		b.append (", status: ");
		b.append (this.status);
		b.append (", taskDate: ");
		b.append (this.taskDate);
		b.append (", transaction: ");
		b.append (this.transaction);
		b.append (", group: ");
		b.append (this.group);
		b.append (", alias: ");
		b.append (this.alias);
		b.append (", mailDomain: ");
		b.append (this.mailDomain);
		b.append (", role: ");
		b.append (this.role);
		b.append (", database: ");
		b.append (this.database);
		b.append (", systemName: ");
		b.append (this.systemName);
		b.append (", server: ");
		b.append (this.server);
		b.append (", serverInstance: ");
		b.append (this.serverInstance);
		b.append (", userDomain: ");
		b.append (this.userDomain);
		b.append (", passwordDomain: ");
		b.append (this.passwordDomain);
		b.append (", hash: ");
		b.append (this.hash);
		b.append (", expirationDate: ");
		b.append (this.expirationDate);
		b.append (", entity: ");
		b.append (this.entity);
		b.append (", primaryKeyValue: ");
		b.append (this.primaryKeyValue);
		b.append (", customObjectType: ");
		b.append (this.customObjectType);
		b.append (", customObjectName: ");
		b.append (this.customObjectName);
		b.append (", externalId: ");
		b.append (this.externalId);
		b.append (", customTaskName: ");
		b.append (this.customTaskName);
		b.append (", parameters: ");
		b.append (this.parameters);
		b.append ("]");
		return b.toString();
	}

}
