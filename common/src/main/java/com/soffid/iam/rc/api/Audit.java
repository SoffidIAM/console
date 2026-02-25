//
// (C) 2020 Soffid
//
//

package com.soffid.iam.rc.api;
/**
 * ValueObject Audit
 **/
public class Audit

		implements java.io.Serializable
 {

	/**
	 + The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = 1;
	/**
	 * Attribute object

	 */
	private java.lang.String object;

	/**
	 * Attribute action

	 */
	private java.lang.String action;

	/**
	 * Attribute description

	 */
	private java.lang.String description;

	/**
	 * Attribute author
	 * Person who has made the action

	 */
	private java.lang.String author;

	/**
	 * Attribute role

	 */
	private java.lang.String role;

	/**
	 * Attribute database

	 */
	private java.lang.String database;

	/**
	 * Attribute id

	 */
	private java.lang.Long id;

	/**
	 * Attribute printer

	 */
	private java.lang.String printer;

	/**
	 * Attribute application

	 */
	private java.lang.String application;

	/**
	 * Attribute mailList

	 */
	private java.lang.String mailList;

	/**
	 * Attribute group

	 */
	private java.lang.String group;

	/**
	 * Attribute user

	 */
	private java.lang.String user;

	/**
	 * Attribute mailDomain

	 */
	private java.lang.String mailDomain;

	/**
	 * Attribute host

	 */
	private java.lang.String host;

	/**
	 * Attribute network

	 */
	private java.lang.String network;

	/**
	 * Attribute file

	 */
	private java.lang.Long file;

	/**
	 * Attribute domainValue

	 */
	private java.lang.String domainValue;

	/**
	 * Attribute domain

	 */
	private java.lang.String domain;

	/**
	 * Attribute configurationParameter

	 */
	private java.lang.String configurationParameter;

	/**
	 * Attribute primaryGroupAuthor

	 */
	private java.lang.String primaryGroupAuthor;

	/**
	 * Attribute authorFullName

	 */
	private java.lang.String authorFullName;

	/**
	 * Attribute authorization

	 */
	private java.lang.String authorization;

	/**
	 * Attribute fileName

	 */
	private java.lang.String fileName;

	/**
	 * Attribute identityFederation

	 */
	private java.lang.String identityFederation;

	/**
	 * Attribute mailListBelong

	 */
	private java.lang.String mailListBelong;

	/**
	 * Attribute mailDomainBelogns

	 */
	private java.lang.String mailDomainBelogns;

	/**
	 * Attribute account

	 */
	private java.lang.String account;

	/**
	 * Attribute comment

	 */
	private java.lang.String comment;

	/**
	 * Attribute message

	 */
	private java.lang.String message;

	/**
	 * Attribute passwordDomain

	 */
	private java.lang.String passwordDomain;

	/**
	 * Attribute userDomain

	 */
	private java.lang.String userDomain;

	/**
	 * Attribute userType

	 */
	private java.lang.String userType;

	/**
	 * Attribute rule

	 */
	private java.lang.String rule;

	/**
	 * Attribute scheduledTask

	 */
	private java.lang.String scheduledTask;

	/**
	 * Attribute calendar

	 */
	private java.util.Calendar calendar;

	/**
	 * Attribute customObjectName

	 */
	private java.lang.String customObjectName;

	/**
	 * Attribute customObjectType

	 */
	private java.lang.String customObjectType;

	/**
	 * Attribute sourceIp

	 */
	private java.lang.String sourceIp;

	/**
	 * Attribute jumpServerGroup

	 */
	private java.lang.String jumpServerGroup;

	/**
	 * Attribute pamSessionId

	 */
	private java.lang.String pamSessionId;

	/**
	 * Attribute oldValue

	 */
	private java.lang.String oldValue;

	/**
	 * Attribute newValue

	 */
	private java.lang.String newValue;

	/**
	 * Attribute searchIndex
	 * Column to perfom fast searches

	 */
	private java.lang.String searchIndex;

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

	public Audit()
	{
	}

	public Audit(java.lang.String object, java.lang.String action, java.lang.String description, java.lang.String author, java.lang.String role, java.lang.String database, java.lang.Long id, java.lang.String printer, java.lang.String application, java.lang.String mailList, java.lang.String group, java.lang.String user, java.lang.String mailDomain, java.lang.String host, java.lang.String network, java.lang.Long file, java.lang.String domainValue, java.lang.String domain, java.lang.String configurationParameter, java.lang.String primaryGroupAuthor, java.lang.String authorFullName, java.lang.String authorization, java.lang.String fileName, java.lang.String identityFederation, java.lang.String mailListBelong, java.lang.String mailDomainBelogns, java.lang.String account, java.lang.String comment, java.lang.String message, java.lang.String passwordDomain, java.lang.String userDomain, java.lang.String userType, java.lang.String rule, java.lang.String scheduledTask, java.util.Calendar calendar, java.lang.String customObjectName, java.lang.String customObjectType, java.lang.String sourceIp, java.lang.String jumpServerGroup, java.lang.String pamSessionId, java.lang.String oldValue, java.lang.String newValue, java.lang.String searchIndex, java.util.Date createdOn, java.lang.String createdBy, java.util.Date updatedOn, java.lang.String updatedBy, java.util.Date deletedOn, java.lang.String deletedBy)
	{
		super();
		this.object = object;
		this.action = action;
		this.description = description;
		this.author = author;
		this.role = role;
		this.database = database;
		this.id = id;
		this.printer = printer;
		this.application = application;
		this.mailList = mailList;
		this.group = group;
		this.user = user;
		this.mailDomain = mailDomain;
		this.host = host;
		this.network = network;
		this.file = file;
		this.domainValue = domainValue;
		this.domain = domain;
		this.configurationParameter = configurationParameter;
		this.primaryGroupAuthor = primaryGroupAuthor;
		this.authorFullName = authorFullName;
		this.authorization = authorization;
		this.fileName = fileName;
		this.identityFederation = identityFederation;
		this.mailListBelong = mailListBelong;
		this.mailDomainBelogns = mailDomainBelogns;
		this.account = account;
		this.comment = comment;
		this.message = message;
		this.passwordDomain = passwordDomain;
		this.userDomain = userDomain;
		this.userType = userType;
		this.rule = rule;
		this.scheduledTask = scheduledTask;
		this.calendar = calendar;
		this.customObjectName = customObjectName;
		this.customObjectType = customObjectType;
		this.sourceIp = sourceIp;
		this.jumpServerGroup = jumpServerGroup;
		this.pamSessionId = pamSessionId;
		this.oldValue = oldValue;
		this.newValue = newValue;
		this.searchIndex = searchIndex;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
		this.updatedOn = updatedOn;
		this.updatedBy = updatedBy;
		this.deletedOn = deletedOn;
		this.deletedBy = deletedBy;
	}

	public Audit(java.lang.String action)
	{
		super();
		this.action = action;
	}

	public Audit(Audit otherBean)
	{
		this(otherBean.object, otherBean.action, otherBean.description, otherBean.author, otherBean.role, otherBean.database, otherBean.id, otherBean.printer, otherBean.application, otherBean.mailList, otherBean.group, otherBean.user, otherBean.mailDomain, otherBean.host, otherBean.network, otherBean.file, otherBean.domainValue, otherBean.domain, otherBean.configurationParameter, otherBean.primaryGroupAuthor, otherBean.authorFullName, otherBean.authorization, otherBean.fileName, otherBean.identityFederation, otherBean.mailListBelong, otherBean.mailDomainBelogns, otherBean.account, otherBean.comment, otherBean.message, otherBean.passwordDomain, otherBean.userDomain, otherBean.userType, otherBean.rule, otherBean.scheduledTask, otherBean.calendar, otherBean.customObjectName, otherBean.customObjectType, otherBean.sourceIp, otherBean.jumpServerGroup, otherBean.pamSessionId, otherBean.oldValue, otherBean.newValue, otherBean.searchIndex, otherBean.createdOn, otherBean.createdBy, otherBean.updatedOn, otherBean.updatedBy, otherBean.deletedOn, otherBean.deletedBy);
	}

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
	 * Gets value for attribute author
	 */
	public java.lang.String getAuthor() {
		return this.author;
	}

	/**
	 * Sets value for attribute author
	 */
	public void setAuthor(java.lang.String author) {
		this.author = author;
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
	 * Gets value for attribute application
	 */
	public java.lang.String getApplication() {
		return this.application;
	}

	/**
	 * Sets value for attribute application
	 */
	public void setApplication(java.lang.String application) {
		this.application = application;
	}

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
	 * Gets value for attribute file
	 */
	public java.lang.Long getFile() {
		return this.file;
	}

	/**
	 * Sets value for attribute file
	 */
	public void setFile(java.lang.Long file) {
		this.file = file;
	}

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
	 * Gets value for attribute primaryGroupAuthor
	 */
	public java.lang.String getPrimaryGroupAuthor() {
		return this.primaryGroupAuthor;
	}

	/**
	 * Sets value for attribute primaryGroupAuthor
	 */
	public void setPrimaryGroupAuthor(java.lang.String primaryGroupAuthor) {
		this.primaryGroupAuthor = primaryGroupAuthor;
	}

	/**
	 * Gets value for attribute authorFullName
	 */
	public java.lang.String getAuthorFullName() {
		return this.authorFullName;
	}

	/**
	 * Sets value for attribute authorFullName
	 */
	public void setAuthorFullName(java.lang.String authorFullName) {
		this.authorFullName = authorFullName;
	}

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
	 * Gets value for attribute fileName
	 */
	public java.lang.String getFileName() {
		return this.fileName;
	}

	/**
	 * Sets value for attribute fileName
	 */
	public void setFileName(java.lang.String fileName) {
		this.fileName = fileName;
	}

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
	 * Gets value for attribute mailListBelong
	 */
	public java.lang.String getMailListBelong() {
		return this.mailListBelong;
	}

	/**
	 * Sets value for attribute mailListBelong
	 */
	public void setMailListBelong(java.lang.String mailListBelong) {
		this.mailListBelong = mailListBelong;
	}

	/**
	 * Gets value for attribute mailDomainBelogns
	 */
	public java.lang.String getMailDomainBelogns() {
		return this.mailDomainBelogns;
	}

	/**
	 * Sets value for attribute mailDomainBelogns
	 */
	public void setMailDomainBelogns(java.lang.String mailDomainBelogns) {
		this.mailDomainBelogns = mailDomainBelogns;
	}

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
	 * Gets value for attribute calendar
	 */
	public java.util.Calendar getCalendar() {
		return this.calendar;
	}

	/**
	 * Sets value for attribute calendar
	 */
	public void setCalendar(java.util.Calendar calendar) {
		this.calendar = calendar;
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
		b.append ("[object: ");
		b.append (this.object);
		b.append (", action: ");
		b.append (this.action);
		b.append (", description: ");
		b.append (this.description);
		b.append (", author: ");
		b.append (this.author);
		b.append (", role: ");
		b.append (this.role);
		b.append (", database: ");
		b.append (this.database);
		b.append (", id: ");
		b.append (this.id);
		b.append (", printer: ");
		b.append (this.printer);
		b.append (", application: ");
		b.append (this.application);
		b.append (", mailList: ");
		b.append (this.mailList);
		b.append (", group: ");
		b.append (this.group);
		b.append (", user: ");
		b.append (this.user);
		b.append (", mailDomain: ");
		b.append (this.mailDomain);
		b.append (", host: ");
		b.append (this.host);
		b.append (", network: ");
		b.append (this.network);
		b.append (", file: ");
		b.append (this.file);
		b.append (", domainValue: ");
		b.append (this.domainValue);
		b.append (", domain: ");
		b.append (this.domain);
		b.append (", configurationParameter: ");
		b.append (this.configurationParameter);
		b.append (", primaryGroupAuthor: ");
		b.append (this.primaryGroupAuthor);
		b.append (", authorFullName: ");
		b.append (this.authorFullName);
		b.append (", authorization: ");
		b.append (this.authorization);
		b.append (", fileName: ");
		b.append (this.fileName);
		b.append (", identityFederation: ");
		b.append (this.identityFederation);
		b.append (", mailListBelong: ");
		b.append (this.mailListBelong);
		b.append (", mailDomainBelogns: ");
		b.append (this.mailDomainBelogns);
		b.append (", account: ");
		b.append (this.account);
		b.append (", comment: ");
		b.append (this.comment);
		b.append (", message: ");
		b.append (this.message);
		b.append (", passwordDomain: ");
		b.append (this.passwordDomain);
		b.append (", userDomain: ");
		b.append (this.userDomain);
		b.append (", userType: ");
		b.append (this.userType);
		b.append (", rule: ");
		b.append (this.rule);
		b.append (", scheduledTask: ");
		b.append (this.scheduledTask);
		b.append (", calendar: ");
		b.append (this.calendar);
		b.append (", customObjectName: ");
		b.append (this.customObjectName);
		b.append (", customObjectType: ");
		b.append (this.customObjectType);
		b.append (", sourceIp: ");
		b.append (this.sourceIp);
		b.append (", jumpServerGroup: ");
		b.append (this.jumpServerGroup);
		b.append (", pamSessionId: ");
		b.append (this.pamSessionId);
		b.append (", oldValue: ");
		b.append (this.oldValue);
		b.append (", newValue: ");
		b.append (this.newValue);
		b.append (", searchIndex: ");
		b.append (this.searchIndex);
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
