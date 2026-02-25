//
// (c) 2014 Soffid
//
//

package com.soffid.iam.sync.model;

/**
 *  Entity TaskEntity
 */

public abstract class TaskEntity {

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
	 * Attribute user
	 */
	private java.lang.String user;
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
	 * Attribute password
	 */
	private java.lang.String password;
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
	 * Attribute changePassword
	 */
	private java.lang.String changePassword;
	/**
	 * Gets value for attribute changePassword
	 */
	public java.lang.String getChangePassword() {
		return this.changePassword;
	}
	/**
	 * Sets value for attribute changePassword
	 */
	public void setChangePassword(java.lang.String changePassword) {
		this.changePassword = changePassword;
	}
	/**
	 * Attribute folder
	 */
	private java.lang.String folder;
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
	 * Attribute folderType
	 */
	private java.lang.String folderType;
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
	 * Attribute printer
	 */
	private java.lang.String printer;
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
	 * Attribute host
	 */
	private java.lang.String host;
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
	 * Attribute subnet
	 */
	private java.lang.String subnet;
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
	 * Attribute message
	 */
	private java.lang.String message;
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
	 * Attribute status
	 */
	private java.lang.String status = "P";
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
	 * Attribute date
	 */
	private java.sql.Timestamp date = new java.sql.Timestamp(System.currentTimeMillis());
	/**
	 * Gets value for attribute date
	 */
	public java.sql.Timestamp getDate() {
		return this.date;
	}
	/**
	 * Sets value for attribute date
	 */
	public void setDate(java.sql.Timestamp date) {
		this.date = date;
	}
	/**
	 * Attribute transaction
	 */
	private java.lang.String transaction;
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
	 * Attribute group
	 */
	private java.lang.String group;
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
	 * Attribute alias
	 */
	private java.lang.String alias;
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
	 * Attribute mailDomain
	 */
	private java.lang.String mailDomain;
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
	 * Attribute role
	 */
	private java.lang.String role;
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
	 * Attribute db
	 */
	private java.lang.String db;
	/**
	 * Gets value for attribute db
	 */
	public java.lang.String getDb() {
		return this.db;
	}
	/**
	 * Sets value for attribute db
	 */
	public void setDb(java.lang.String db) {
		this.db = db;
	}
	/**
	 * Attribute systemName
	 */
	private java.lang.String systemName;
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
	 * Attribute server
	 */
	private java.lang.String server;
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
	 * Attribute serverInstance
	 */
	private java.lang.String serverInstance;
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
	 * Attribute priority
	 */
	private java.lang.Long priority;
	/**
	 * Gets value for attribute priority
	 */
	public java.lang.Long getPriority() {
		return this.priority;
	}
	/**
	 * Sets value for attribute priority
	 */
	public void setPriority(java.lang.Long priority) {
		this.priority = priority;
	}
	/**
	 * Attribute usersDomain
	 */
	private java.lang.String usersDomain;
	/**
	 * Gets value for attribute usersDomain
	 */
	public java.lang.String getUsersDomain() {
		return this.usersDomain;
	}
	/**
	 * Sets value for attribute usersDomain
	 */
	public void setUsersDomain(java.lang.String usersDomain) {
		this.usersDomain = usersDomain;
	}
	/**
	 * Attribute passwordsDomain
	 */
	private java.lang.String passwordsDomain;
	/**
	 * Gets value for attribute passwordsDomain
	 */
	public java.lang.String getPasswordsDomain() {
		return this.passwordsDomain;
	}
	/**
	 * Sets value for attribute passwordsDomain
	 */
	public void setPasswordsDomain(java.lang.String passwordsDomain) {
		this.passwordsDomain = passwordsDomain;
	}
	/**
	 * Attribute hash
	 */
	private java.lang.String hash;
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
	 * Attribute logs
	 */
	private java.util.Collection<com.soffid.iam.sync.model.TaskLogEntity> logs =  new java.util.HashSet<com.soffid.iam.sync.model.TaskLogEntity>();
	/**
	 * Gets value for attribute logs
	 */
	public java.util.Collection<com.soffid.iam.sync.model.TaskLogEntity> getLogs() {
		return this.logs;
	}
	/**
	 * Sets value for attribute logs
	 */
	public void setLogs(java.util.Collection<com.soffid.iam.sync.model.TaskLogEntity> logs) {
		this.logs = logs;
	}
	/**
	 * Attribute expirationDate
	 */
	private java.util.Date expirationDate;
	/**
	 * Gets value for attribute expirationDate
	 */
	public java.util.Date getExpirationDate() {
		return this.expirationDate;
	}
	/**
	 * Sets value for attribute expirationDate
	 */
	public void setExpirationDate(java.util.Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	/**
	 * Attribute entity
	 */
	private java.lang.String entity;
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
	 * Attribute primaryKeyValue
	 */
	private java.lang.Long primaryKeyValue;
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
	 * Attribute customObjectType
	 */
	private java.lang.String customObjectType;
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
	 * Attribute customObjectName
	 */
	private java.lang.String customObjectName;
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
	 * Attribute customTaskName
	 */
	private java.lang.String customTaskName;
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
	 * Attribute parameters
	 * Serialized JSON parameters
	 */
	private java.lang.String parameters;
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
	 * Attribute externalId
	 */
	private java.lang.String externalId;
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
	 * Attribute sourceTransaction
	 */
	private java.lang.String sourceTransaction;
	/**
	 * Gets value for attribute sourceTransaction
	 */
	public java.lang.String getSourceTransaction() {
		return this.sourceTransaction;
	}
	/**
	 * Sets value for attribute sourceTransaction
	 */
	public void setSourceTransaction(java.lang.String sourceTransaction) {
		this.sourceTransaction = sourceTransaction;
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
	 * Attribute taskLogs

	 */
	private java.util.Collection<com.soffid.iam.sync.model.TaskLogEntity> taskLogs =  new java.util.HashSet<com.soffid.iam.sync.model.TaskLogEntity>();
	/**
	 * Gets value for attribute taskLogs
	 */
	public java.util.Collection<com.soffid.iam.sync.model.TaskLogEntity> getTaskLogs() {
		return this.taskLogs;
	}
	/**
	 * Sets value for attribute taskLogs
	 */
	public void setTaskLogs(java.util.Collection<com.soffid.iam.sync.model.TaskLogEntity> taskLogs) {
		this.taskLogs = taskLogs;
	}
	/**
	 * Operation toString
	 * @return
	**/
	 public abstract java.lang.String toString();

	/**
	 * Returns <code>true</code> if the argument is an TaskEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof TaskEntity))
		{
			return false;
		}
		final TaskEntity that = (TaskEntity)object;
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
