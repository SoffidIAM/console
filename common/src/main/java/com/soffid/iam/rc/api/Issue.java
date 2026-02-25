//
// (C) 2020 Soffid
//
//

package com.soffid.iam.rc.api;
/**
 * ValueObject Issue
 **/
public class Issue

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
	 * Attribute number

	 */
	private java.lang.Long number;

	/**
	 * Attribute requester

	 */
	private java.lang.String requester;

	/**
	 * Attribute type

	 */
	private java.lang.String type;

	/**
	 * Attribute description

	 */
	private java.lang.String description;

	/**
	 * Attribute times

	 */
	private java.lang.Integer times = 0;

	/**
	 * Attribute status

	 */
	private com.soffid.iam.rc.api.IssueStatus status = com.soffid.iam.rc.api.IssueStatus.NEW;

	/**
	 * Attribute failedLoginPct

	 */
	private java.lang.Double failedLoginPct;

	/**
	 * Attribute humanConfidence

	 */
	private java.lang.Double humanConfidence;

	/**
	 * Attribute system

	 */
	private java.lang.String system;

	/**
	 * Attribute otpDevice

	 */
	private java.lang.String otpDevice;

	/**
	 * Attribute exception

	 */
	private java.lang.String exception;

	/**
	 * Attribute risk

	 */
	private com.soffid.iam.rc.api.SoDRisk risk;

	/**
	 * Attribute roleAccount

	 */
	private com.soffid.iam.iga.api.RoleAccount roleAccount;

	/**
	 * Attribute rule

	 */
	private com.soffid.iam.pam.api.PamRule rule;

	/**
	 * Attribute jobName

	 */
	private java.lang.String jobName;

	/**
	 * Attribute ip

	 */
	private java.lang.String ip;

	/**
	 * Attribute country

	 */
	private java.lang.String country;

	/**
	 * Attribute account

	 */
	private java.lang.String account;

	/**
	 * Attribute loginName

	 */
	private java.lang.String loginName;

	/**
	 * Attribute hash

	 */
	private java.lang.String hash;

	/**
	 * Attribute hosts

	 */
	private java.util.List<com.soffid.iam.rc.api.IssueHost> hosts;

	/**
	 * Attribute browsers

	 */
	private java.util.List<com.soffid.iam.rc.api.IssueBrowser> browsers;

	/**
	 * Attribute users

	 */
	private java.util.List<com.soffid.iam.rc.api.IssueUser> users;

	/**
	 * Attribute breachedEmail

	 */
	private java.lang.String breachedEmail;

	/**
	 * Attribute dataBreach

	 */
	private java.lang.String dataBreach;

	/**
	 * Attribute htmlDescription

	 */
	private java.lang.String htmlDescription;

	/**
	 * Attribute created

	 */
	private java.util.Date created;

	/**
	 * Attribute acknowledged

	 */
	private java.util.Date acknowledged = new java.util.Date();

	/**
	 * Attribute solved

	 */
	private java.util.Date solved;

	/**
	 * Attribute actor

	 */
	private java.lang.String actor;

	/**
	 * Attribute performedActions

	 */
	private java.lang.String performedActions;

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

	public Issue()
	{
	}

	public Issue(java.lang.Long id, java.lang.Long number, java.lang.String requester, java.lang.String type, java.lang.String description, java.lang.Integer times, com.soffid.iam.rc.api.IssueStatus status, java.lang.Double failedLoginPct, java.lang.Double humanConfidence, java.lang.String system, java.lang.String otpDevice, java.lang.String exception, com.soffid.iam.rc.api.SoDRisk risk, com.soffid.iam.iga.api.RoleAccount roleAccount, com.soffid.iam.pam.api.PamRule rule, java.lang.String jobName, java.lang.String ip, java.lang.String country, java.lang.String account, java.lang.String loginName, java.lang.String hash, java.util.List<com.soffid.iam.rc.api.IssueHost> hosts, java.util.List<com.soffid.iam.rc.api.IssueBrowser> browsers, java.util.List<com.soffid.iam.rc.api.IssueUser> users, java.lang.String breachedEmail, java.lang.String dataBreach, java.lang.String htmlDescription, java.util.Date created, java.util.Date acknowledged, java.util.Date solved, java.lang.String actor, java.lang.String performedActions, java.util.Date updatedOn, java.lang.String updatedBy, java.util.Date deletedOn, java.lang.String deletedBy, java.util.Date createdOn)
	{
		super();
		this.id = id;
		this.number = number;
		this.requester = requester;
		this.type = type;
		this.description = description;
		this.times = times;
		this.status = status;
		this.failedLoginPct = failedLoginPct;
		this.humanConfidence = humanConfidence;
		this.system = system;
		this.otpDevice = otpDevice;
		this.exception = exception;
		this.risk = risk;
		this.roleAccount = roleAccount;
		this.rule = rule;
		this.jobName = jobName;
		this.ip = ip;
		this.country = country;
		this.account = account;
		this.loginName = loginName;
		this.hash = hash;
		this.hosts = hosts;
		this.browsers = browsers;
		this.users = users;
		this.breachedEmail = breachedEmail;
		this.dataBreach = dataBreach;
		this.htmlDescription = htmlDescription;
		this.created = created;
		this.acknowledged = acknowledged;
		this.solved = solved;
		this.actor = actor;
		this.performedActions = performedActions;
		this.updatedOn = updatedOn;
		this.updatedBy = updatedBy;
		this.deletedOn = deletedOn;
		this.deletedBy = deletedBy;
		this.createdOn = createdOn;
	}

	public Issue(java.lang.String type, java.lang.Integer times, com.soffid.iam.rc.api.IssueStatus status)
	{
		super();
		this.type = type;
		this.times = times;
		this.status = status;
	}

	public Issue(Issue otherBean)
	{
		this(otherBean.id, otherBean.number, otherBean.requester, otherBean.type, otherBean.description, otherBean.times, otherBean.status, otherBean.failedLoginPct, otherBean.humanConfidence, otherBean.system, otherBean.otpDevice, otherBean.exception, otherBean.risk, otherBean.roleAccount, otherBean.rule, otherBean.jobName, otherBean.ip, otherBean.country, otherBean.account, otherBean.loginName, otherBean.hash, otherBean.hosts, otherBean.browsers, otherBean.users, otherBean.breachedEmail, otherBean.dataBreach, otherBean.htmlDescription, otherBean.created, otherBean.acknowledged, otherBean.solved, otherBean.actor, otherBean.performedActions, otherBean.updatedOn, otherBean.updatedBy, otherBean.deletedOn, otherBean.deletedBy, otherBean.createdOn);
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
	 * Gets value for attribute number
	 */
	public java.lang.Long getNumber() {
		return this.number;
	}

	/**
	 * Sets value for attribute number
	 */
	public void setNumber(java.lang.Long number) {
		this.number = number;
	}

	/**
	 * Gets value for attribute requester
	 */
	public java.lang.String getRequester() {
		return this.requester;
	}

	/**
	 * Sets value for attribute requester
	 */
	public void setRequester(java.lang.String requester) {
		this.requester = requester;
	}

	/**
	 * Gets value for attribute type
	 */
	public java.lang.String getType() {
		return this.type;
	}

	/**
	 * Sets value for attribute type
	 */
	public void setType(java.lang.String type) {
		this.type = type;
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
	 * Gets value for attribute times
	 */
	public java.lang.Integer getTimes() {
		return this.times;
	}

	/**
	 * Sets value for attribute times
	 */
	public void setTimes(java.lang.Integer times) {
		this.times = times;
	}

	/**
	 * Gets value for attribute status
	 */
	public com.soffid.iam.rc.api.IssueStatus getStatus() {
		return this.status;
	}

	/**
	 * Sets value for attribute status
	 */
	public void setStatus(com.soffid.iam.rc.api.IssueStatus status) {
		this.status = status;
	}

	/**
	 * Gets value for attribute failedLoginPct
	 */
	public java.lang.Double getFailedLoginPct() {
		return this.failedLoginPct;
	}

	/**
	 * Sets value for attribute failedLoginPct
	 */
	public void setFailedLoginPct(java.lang.Double failedLoginPct) {
		this.failedLoginPct = failedLoginPct;
	}

	/**
	 * Gets value for attribute humanConfidence
	 */
	public java.lang.Double getHumanConfidence() {
		return this.humanConfidence;
	}

	/**
	 * Sets value for attribute humanConfidence
	 */
	public void setHumanConfidence(java.lang.Double humanConfidence) {
		this.humanConfidence = humanConfidence;
	}

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
	 * Gets value for attribute otpDevice
	 */
	public java.lang.String getOtpDevice() {
		return this.otpDevice;
	}

	/**
	 * Sets value for attribute otpDevice
	 */
	public void setOtpDevice(java.lang.String otpDevice) {
		this.otpDevice = otpDevice;
	}

	/**
	 * Gets value for attribute exception
	 */
	public java.lang.String getException() {
		return this.exception;
	}

	/**
	 * Sets value for attribute exception
	 */
	public void setException(java.lang.String exception) {
		this.exception = exception;
	}

	/**
	 * Gets value for attribute risk
	 */
	public com.soffid.iam.rc.api.SoDRisk getRisk() {
		return this.risk;
	}

	/**
	 * Sets value for attribute risk
	 */
	public void setRisk(com.soffid.iam.rc.api.SoDRisk risk) {
		this.risk = risk;
	}

	/**
	 * Gets value for attribute roleAccount
	 */
	public com.soffid.iam.iga.api.RoleAccount getRoleAccount() {
		return this.roleAccount;
	}

	/**
	 * Sets value for attribute roleAccount
	 */
	public void setRoleAccount(com.soffid.iam.iga.api.RoleAccount roleAccount) {
		this.roleAccount = roleAccount;
	}

	/**
	 * Gets value for attribute rule
	 */
	public com.soffid.iam.pam.api.PamRule getRule() {
		return this.rule;
	}

	/**
	 * Sets value for attribute rule
	 */
	public void setRule(com.soffid.iam.pam.api.PamRule rule) {
		this.rule = rule;
	}

	/**
	 * Gets value for attribute jobName
	 */
	public java.lang.String getJobName() {
		return this.jobName;
	}

	/**
	 * Sets value for attribute jobName
	 */
	public void setJobName(java.lang.String jobName) {
		this.jobName = jobName;
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
	 * Gets value for attribute loginName
	 */
	public java.lang.String getLoginName() {
		return this.loginName;
	}

	/**
	 * Sets value for attribute loginName
	 */
	public void setLoginName(java.lang.String loginName) {
		this.loginName = loginName;
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
	 * Gets value for attribute hosts
	 */
	public java.util.List<com.soffid.iam.rc.api.IssueHost> getHosts() {
		return this.hosts;
	}

	/**
	 * Sets value for attribute hosts
	 */
	public void setHosts(java.util.List<com.soffid.iam.rc.api.IssueHost> hosts) {
		this.hosts = hosts;
	}

	/**
	 * Gets value for attribute browsers
	 */
	public java.util.List<com.soffid.iam.rc.api.IssueBrowser> getBrowsers() {
		return this.browsers;
	}

	/**
	 * Sets value for attribute browsers
	 */
	public void setBrowsers(java.util.List<com.soffid.iam.rc.api.IssueBrowser> browsers) {
		this.browsers = browsers;
	}

	/**
	 * Gets value for attribute users
	 */
	public java.util.List<com.soffid.iam.rc.api.IssueUser> getUsers() {
		return this.users;
	}

	/**
	 * Sets value for attribute users
	 */
	public void setUsers(java.util.List<com.soffid.iam.rc.api.IssueUser> users) {
		this.users = users;
	}

	/**
	 * Gets value for attribute breachedEmail
	 */
	public java.lang.String getBreachedEmail() {
		return this.breachedEmail;
	}

	/**
	 * Sets value for attribute breachedEmail
	 */
	public void setBreachedEmail(java.lang.String breachedEmail) {
		this.breachedEmail = breachedEmail;
	}

	/**
	 * Gets value for attribute dataBreach
	 */
	public java.lang.String getDataBreach() {
		return this.dataBreach;
	}

	/**
	 * Sets value for attribute dataBreach
	 */
	public void setDataBreach(java.lang.String dataBreach) {
		this.dataBreach = dataBreach;
	}

	/**
	 * Gets value for attribute htmlDescription
	 */
	public java.lang.String getHtmlDescription() {
		return this.htmlDescription;
	}

	/**
	 * Sets value for attribute htmlDescription
	 */
	public void setHtmlDescription(java.lang.String htmlDescription) {
		this.htmlDescription = htmlDescription;
	}

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
	 * Gets value for attribute acknowledged
	 */
	public java.util.Date getAcknowledged() {
		return this.acknowledged;
	}

	/**
	 * Sets value for attribute acknowledged
	 */
	public void setAcknowledged(java.util.Date acknowledged) {
		this.acknowledged = acknowledged;
	}

	/**
	 * Gets value for attribute solved
	 */
	public java.util.Date getSolved() {
		return this.solved;
	}

	/**
	 * Sets value for attribute solved
	 */
	public void setSolved(java.util.Date solved) {
		this.solved = solved;
	}

	/**
	 * Gets value for attribute actor
	 */
	public java.lang.String getActor() {
		return this.actor;
	}

	/**
	 * Sets value for attribute actor
	 */
	public void setActor(java.lang.String actor) {
		this.actor = actor;
	}

	/**
	 * Gets value for attribute performedActions
	 */
	public java.lang.String getPerformedActions() {
		return this.performedActions;
	}

	/**
	 * Sets value for attribute performedActions
	 */
	public void setPerformedActions(java.lang.String performedActions) {
		this.performedActions = performedActions;
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
	 * Returns a string representation of the value object.
	 */
	public String toString()
	{
		StringBuffer b = new StringBuffer();
		b.append (getClass().getName());
		b.append ("[id: ");
		b.append (this.id);
		b.append (", number: ");
		b.append (this.number);
		b.append (", requester: ");
		b.append (this.requester);
		b.append (", type: ");
		b.append (this.type);
		b.append (", description: ");
		b.append (this.description);
		b.append (", times: ");
		b.append (this.times);
		b.append (", status: ");
		b.append (this.status);
		b.append (", failedLoginPct: ");
		b.append (this.failedLoginPct);
		b.append (", humanConfidence: ");
		b.append (this.humanConfidence);
		b.append (", system: ");
		b.append (this.system);
		b.append (", otpDevice: ");
		b.append (this.otpDevice);
		b.append (", exception: ");
		b.append (this.exception);
		b.append (", risk: ");
		b.append (this.risk);
		b.append (", roleAccount: ");
		b.append (this.roleAccount);
		b.append (", rule: ");
		b.append (this.rule);
		b.append (", jobName: ");
		b.append (this.jobName);
		b.append (", ip: ");
		b.append (this.ip);
		b.append (", country: ");
		b.append (this.country);
		b.append (", account: ");
		b.append (this.account);
		b.append (", loginName: ");
		b.append (this.loginName);
		b.append (", hash: ");
		b.append (this.hash);
		b.append (", hosts: ");
		b.append (this.hosts);
		b.append (", browsers: ");
		b.append (this.browsers);
		b.append (", users: ");
		b.append (this.users);
		b.append (", breachedEmail: ");
		b.append (this.breachedEmail);
		b.append (", dataBreach: ");
		b.append (this.dataBreach);
		b.append (", htmlDescription: ");
		b.append (this.htmlDescription);
		b.append (", created: ");
		b.append (this.created);
		b.append (", acknowledged: ");
		b.append (this.acknowledged);
		b.append (", solved: ");
		b.append (this.solved);
		b.append (", actor: ");
		b.append (this.actor);
		b.append (", performedActions: ");
		b.append (this.performedActions);
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
		b.append ("]");
		return b.toString();
	}

}
