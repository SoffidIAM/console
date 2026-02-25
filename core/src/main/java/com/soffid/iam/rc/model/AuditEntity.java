//
// (c) 2014 Soffid
//
//

package com.soffid.iam.rc.model;

/**
 *  Entity AuditEntity
 */

public abstract class AuditEntity {

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
	 * Attribute date
	 */
	private java.util.Date date;
	/**
	 * Gets value for attribute date
	 */
	public java.util.Date getDate() {
		return this.date;
	}
	/**
	 * Sets value for attribute date
	 */
	public void setDate(java.util.Date date) {
		this.date = date;
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
	 * Attribute action
	 */
	private java.lang.String action;
	/**
	 * Gets value for attribute action
	 */
	public java.lang.String getAction() {
		return this.action;
	}
	/**
	 * Sets value for attribute action
	 */
	public void setAction(java.lang.String action) {
		this.action = action;
	}
	/**
	 * Attribute object
	 */
	private java.lang.String object;
	/**
	 * Gets value for attribute object
	 */
	public java.lang.String getObject() {
		return this.object;
	}
	/**
	 * Sets value for attribute object
	 */
	public void setObject(java.lang.String object) {
		this.object = object;
	}
	/**
	 * Attribute group
	 */
	private com.soffid.iam.iga.model.GroupEntity group;
	/**
	 * Gets value for attribute group
	 */
	public com.soffid.iam.iga.model.GroupEntity getGroup() {
		return this.group;
	}
	/**
	 * Sets value for attribute group
	 */
	public void setGroup(com.soffid.iam.iga.model.GroupEntity group) {
		this.group = group;
	}
	/**
	 * Attribute domain
	 */
	private java.lang.String domain;
	/**
	 * Gets value for attribute domain
	 */
	public java.lang.String getDomain() {
		return this.domain;
	}
	/**
	 * Sets value for attribute domain
	 */
	public void setDomain(java.lang.String domain) {
		this.domain = domain;
	}
	/**
	 * Attribute domainValue
	 */
	private java.lang.String domainValue;
	/**
	 * Gets value for attribute domainValue
	 */
	public java.lang.String getDomainValue() {
		return this.domainValue;
	}
	/**
	 * Sets value for attribute domainValue
	 */
	public void setDomainValue(java.lang.String domainValue) {
		this.domainValue = domainValue;
	}
	/**
	 * Attribute configurationParameter
	 */
	private java.lang.String configurationParameter;
	/**
	 * Gets value for attribute configurationParameter
	 */
	public java.lang.String getConfigurationParameter() {
		return this.configurationParameter;
	}
	/**
	 * Sets value for attribute configurationParameter
	 */
	public void setConfigurationParameter(java.lang.String configurationParameter) {
		this.configurationParameter = configurationParameter;
	}
	/**
	 * Attribute database
	 */
	private java.lang.String database;
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
	 * Attribute informationSystem
	 */
	private java.lang.String informationSystem;
	/**
	 * Gets value for attribute informationSystem
	 */
	public java.lang.String getInformationSystem() {
		return this.informationSystem;
	}
	/**
	 * Sets value for attribute informationSystem
	 */
	public void setInformationSystem(java.lang.String informationSystem) {
		this.informationSystem = informationSystem;
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
	 * Attribute mailList
	 */
	private java.lang.String mailList;
	/**
	 * Gets value for attribute mailList
	 */
	public java.lang.String getMailList() {
		return this.mailList;
	}
	/**
	 * Sets value for attribute mailList
	 */
	public void setMailList(java.lang.String mailList) {
		this.mailList = mailList;
	}
	/**
	 * Attribute network
	 */
	private java.lang.String network;
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
	 * Attribute authorization
	 */
	private java.lang.String authorization;
	/**
	 * Gets value for attribute authorization
	 */
	public java.lang.String getAuthorization() {
		return this.authorization;
	}
	/**
	 * Sets value for attribute authorization
	 */
	public void setAuthorization(java.lang.String authorization) {
		this.authorization = authorization;
	}
	/**
	 * Attribute fileId
	 */
	private java.lang.Long fileId;
	/**
	 * Gets value for attribute fileId
	 */
	public java.lang.Long getFileId() {
		return this.fileId;
	}
	/**
	 * Sets value for attribute fileId
	 */
	public void setFileId(java.lang.Long fileId) {
		this.fileId = fileId;
	}
	/**
	 * Attribute identityFederation
	 */
	private java.lang.String identityFederation;
	/**
	 * Gets value for attribute identityFederation
	 */
	public java.lang.String getIdentityFederation() {
		return this.identityFederation;
	}
	/**
	 * Sets value for attribute identityFederation
	 */
	public void setIdentityFederation(java.lang.String identityFederation) {
		this.identityFederation = identityFederation;
	}
	/**
	 * Attribute belongsMailList
	 */
	private java.lang.String belongsMailList;
	/**
	 * Gets value for attribute belongsMailList
	 */
	public java.lang.String getBelongsMailList() {
		return this.belongsMailList;
	}
	/**
	 * Sets value for attribute belongsMailList
	 */
	public void setBelongsMailList(java.lang.String belongsMailList) {
		this.belongsMailList = belongsMailList;
	}
	/**
	 * Attribute belongsmailDomain
	 */
	private java.lang.String belongsmailDomain;
	/**
	 * Gets value for attribute belongsmailDomain
	 */
	public java.lang.String getBelongsmailDomain() {
		return this.belongsmailDomain;
	}
	/**
	 * Sets value for attribute belongsmailDomain
	 */
	public void setBelongsmailDomain(java.lang.String belongsmailDomain) {
		this.belongsmailDomain = belongsmailDomain;
	}
	/**
	 * Attribute account
	 */
	private java.lang.String account;
	/**
	 * Gets value for attribute account
	 */
	public java.lang.String getAccount() {
		return this.account;
	}
	/**
	 * Sets value for attribute account
	 */
	public void setAccount(java.lang.String account) {
		this.account = account;
	}
	/**
	 * Attribute accountAssoc
	 */
	private com.soffid.iam.base.model.AccountEntity accountAssoc;
	/**
	 * Gets value for attribute accountAssoc
	 */
	public com.soffid.iam.base.model.AccountEntity getAccountAssoc() {
		return this.accountAssoc;
	}
	/**
	 * Sets value for attribute accountAssoc
	 */
	public void setAccountAssoc(com.soffid.iam.base.model.AccountEntity accountAssoc) {
		this.accountAssoc = accountAssoc;
	}
	/**
	 * Attribute comment
	 */
	private java.lang.String comment;
	/**
	 * Gets value for attribute comment
	 */
	public java.lang.String getComment() {
		return this.comment;
	}
	/**
	 * Sets value for attribute comment
	 */
	public void setComment(java.lang.String comment) {
		this.comment = comment;
	}
	/**
	 * Attribute userType
	 */
	private java.lang.String userType;
	/**
	 * Gets value for attribute userType
	 */
	public java.lang.String getUserType() {
		return this.userType;
	}
	/**
	 * Sets value for attribute userType
	 */
	public void setUserType(java.lang.String userType) {
		this.userType = userType;
	}
	/**
	 * Attribute userDomain
	 */
	private java.lang.String userDomain;
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
	 * Attribute passwordDomain
	 */
	private java.lang.String passwordDomain;
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
	 * Attribute rule
	 */
	private java.lang.String rule;
	/**
	 * Gets value for attribute rule
	 */
	public java.lang.String getRule() {
		return this.rule;
	}
	/**
	 * Sets value for attribute rule
	 */
	public void setRule(java.lang.String rule) {
		this.rule = rule;
	}
	/**
	 * Attribute scheduledTask
	 */
	private java.lang.String scheduledTask;
	/**
	 * Gets value for attribute scheduledTask
	 */
	public java.lang.String getScheduledTask() {
		return this.scheduledTask;
	}
	/**
	 * Sets value for attribute scheduledTask
	 */
	public void setScheduledTask(java.lang.String scheduledTask) {
		this.scheduledTask = scheduledTask;
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
	 * Attribute sourceIp
	 */
	private java.lang.String sourceIp;
	/**
	 * Gets value for attribute sourceIp
	 */
	public java.lang.String getSourceIp() {
		return this.sourceIp;
	}
	/**
	 * Sets value for attribute sourceIp
	 */
	public void setSourceIp(java.lang.String sourceIp) {
		this.sourceIp = sourceIp;
	}
	/**
	 * Attribute jumpServerGroup
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
	 * Attribute pamSessionId
	 */
	private java.lang.String pamSessionId;
	/**
	 * Gets value for attribute pamSessionId
	 */
	public java.lang.String getPamSessionId() {
		return this.pamSessionId;
	}
	/**
	 * Sets value for attribute pamSessionId
	 */
	public void setPamSessionId(java.lang.String pamSessionId) {
		this.pamSessionId = pamSessionId;
	}
	/**
	 * Attribute oldValue
	 */
	private java.lang.String oldValue;
	/**
	 * Gets value for attribute oldValue
	 */
	public java.lang.String getOldValue() {
		return this.oldValue;
	}
	/**
	 * Sets value for attribute oldValue
	 */
	public void setOldValue(java.lang.String oldValue) {
		this.oldValue = oldValue;
	}
	/**
	 * Attribute newValue
	 */
	private java.lang.String newValue;
	/**
	 * Gets value for attribute newValue
	 */
	public java.lang.String getNewValue() {
		return this.newValue;
	}
	/**
	 * Sets value for attribute newValue
	 */
	public void setNewValue(java.lang.String newValue) {
		this.newValue = newValue;
	}
	/**
	 * Attribute searchIndex
	 * Column to perfom fast searches
	 */
	private java.lang.String searchIndex;
	/**
	 * Gets value for attribute searchIndex
	 */
	public java.lang.String getSearchIndex() {
		return this.searchIndex;
	}
	/**
	 * Sets value for attribute searchIndex
	 */
	public void setSearchIndex(java.lang.String searchIndex) {
		this.searchIndex = searchIndex;
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
	 * Returns <code>true</code> if the argument is an AuditEntity instance and all identifiers for this entity 
	 * equal the identifiers of the argument entity. Returns <code>false</code> otherwise.
	 */
	public boolean equals(Object object)
	{
		if (this == object)
		{
			return true;
		}
		if (!(object instanceof AuditEntity))
		{
			return false;
		}
		final AuditEntity that = (AuditEntity)object;
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
